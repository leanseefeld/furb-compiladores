package br.furb.compiladores.controller;

import java.util.ArrayList;
import java.util.List;

import br.furb.compiladores.analyzer.Mensagem;
import br.furb.compiladores.analyzer.generated.LexicalError;
import br.furb.compiladores.analyzer.generated.Lexico;
import br.furb.compiladores.analyzer.generated.SemanticError;
import br.furb.compiladores.analyzer.generated.Semantico;
import br.furb.compiladores.analyzer.generated.Sintatico;
import br.furb.compiladores.analyzer.generated.SyntaticError;

public class Compilador {

	public static final Mensagem MSG_SUCESSO = new Mensagem(-1, "Programa compilado com sucesso.");
	private static List<Integer> lineFeedsPosition;
	private static int line = -1;

	public static Mensagem compilar(String input) {
		Lexico lexico = new Lexico(input);

		carregarLinhas(input);

		Semantico semantico = new Semantico("NOME_DO_ARQUIVO"); // TODO: receber via parametro
		Sintatico sintatico = new Sintatico();
		try {
			sintatico.parse(lexico, semantico);
			return MSG_SUCESSO;
		} catch (LexicalError e) {
			line = getNumLinha(lineFeedsPosition, e.getPosition());
			return criarMsgErroLexico(e);
		} catch (SyntaticError e) {
			line = getNumLinha(lineFeedsPosition, e.getPosition());
			return criarMsgErroSintatico(e);
		} catch (SemanticError e) {
			line = getNumLinha(lineFeedsPosition, e.getPosition());
			return criarMsgErroSemantico(e);
		}
	}

	private static Mensagem criarMsgErroSemantico(SemanticError e) {
		return new Mensagem(line, e.getMessage(), true);
	}

	private static Mensagem criarMsgErroSintatico(SyntaticError e) {
		return new Mensagem(line, e.getMessage(), true);
	}

	private static Mensagem criarMsgErroLexico(LexicalError e) {
		return new Mensagem(line, e.getMessage(), true);
	}

	private static int getNumLinha(List<Integer> linesPosition, int position) {
		int line = 1;
		for (Integer linePosition : linesPosition) {
			if (position <= linePosition) {
				return line;
			}
			line++;
		}
		throw new RuntimeException("posição não mapeada: " + position);
	}

	private static void carregarLinhas(String input) {
		List<Integer> linesPosition = new ArrayList<>();
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '\n') {
				linesPosition.add(i);
			}
		}
		linesPosition.add(chars.length);
		Compilador.lineFeedsPosition = linesPosition;
	}

}
