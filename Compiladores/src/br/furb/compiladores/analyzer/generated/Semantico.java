package br.furb.compiladores.analyzer.generated;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import br.furb.compiladores.analyzer.Identificador;
import br.furb.compiladores.analyzer.IndentedCodeBuilder;
import br.furb.compiladores.analyzer.TabelaSimbolos;

public class Semantico implements Constants {

	private static final String MSG_TIPOS_INCOMPATIVEIS = "Tipos incompatíveis na expressão da linha %d";
	private static final String BOOL = "bool";
	private static final String STRING = "string";
	private static final String INT64 = "int64";
	private static final String FLOAT64 = "float64";

	private final IndentedCodeBuilder instrucao;
	private final Stack<String> pilhaTipo;
	private final TabelaSimbolos simbolos;

	private String operadorRelacional;
	private String tipoDeclarado;
	private String fileName;

	public Semantico(String fileName) {
		this.fileName = fileName;
		instrucao = new IndentedCodeBuilder();
		pilhaTipo = new Stack<>();
		simbolos = new TabelaSimbolos();
	}

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação#" + action + ", Token: " + token);

		try {
			switch (action) {
			case 1:
				acaoSemantica01(token);
				break;
			case 2:
				acaoSemantica02(token);
				break;
			case 3:
				acaoSemantica03(token);
				break;
			case 4:
				acaoSemantica04(token);
				break;
			case 5:
				acaoSemantica05(token);
				break;
			case 6:
				acaoSemantica06(token);
				break;
			case 7:
				acaoSemantica07(token);
				break;
			case 11:
				acaoSemantica11(token);
				break;
			case 12:
				acaoSemantica12(token);
				break;
			case 13:
				acaoSemantica13(token);
				break;
			case 14:
				acaoSemantica14();
				break;
			case 15:
				acaoSemantica15(token);
				break;
			case 16:
				acaoSemantica16(token);
				break;
			case 17:
				acaoSemantica17();
				break;
			case 18:
				acaoSemantica18(token);
				break;
			case 19:
				acaoSemantica19(token);
				break;
			case 20:
				acaoSemantica20(token);
				break;
			case 21:
				acaoSemantica21(token);
				break;
			case 22:
				acaoSemantica22(token);
				break;
			case 23:
				acaoSemantica23(token);
				break;
			case 24:
				acaoSemantica24(token);
				break;
			case 25:
				acaoSemantica25(token);
				break;
			case 26:
				acaoSemantica26(token);
				break;
			case 27:
				acaoSemantica27(token);
				break;
			case 28:
				acaoSemantica28(token);
				break;
			case 29:
				acaoSemantica29(token);
				break;
			case 30:
				acaoSemantica30(token);
				break;
			case 31:
				acaoSemantica31(token);
				break;
			case 32:
				acaoSemantica32(token);
				break;
			case 33:
				acaoSemantica33(token);
				break;
			case 34:
				acaoSemantica34(token);
				break;
			case 35:
				acaoSemantica35(token);
				break;
			default:
				throw new IllegalArgumentException("ação não suportada: " + action);
			}
		} catch (SemanticError se) {
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemanticError("Erro reconhecendo o programa: " + e.toString());
		}

	}

	private void acaoSemantica35(Token token) {
		// TODO
	}

	private void acaoSemantica34(Token token) {
		// TODO
	}

	private void acaoSemantica33(Token token) {
		// TODO
	}

	private void acaoSemantica32(Token token) {
		// TODO
	}

	private void acaoSemantica31(Token token) {
		// TODO
	}

	/**
	 * Atribui um valor inicial para todos os identificadores de uma lista
	 * recém-declarada.
	 * 
	 * @param token
	 *            token do valor inicial.
	 */
	private void acaoSemantica30(Token token) {
		// FIXME: validar o tipo do token com o tipo declarado na lista
		List<Identificador> listaIds = simbolos.getListaIds();
		String tipo = listaIds.get(0).getTipo();
		String lexeme = token.getLexeme();
		String valor = prepararValor(lexeme, tipo);
		declararIds(listaIds, tipo);
		for (Identificador id : listaIds) {
			atribuiId(id, valor);
		}
	}

	private void acaoSemantica29(Token token) {
		// TODO
	}

	private void acaoSemantica28(Token token) {
		// TODO
	}

	private void acaoSemantica27(Token token) {
		// TODO
	}

	/**
	 * Completa o reconhecimento de uma lista de identificadores.
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica26(Token token) {
		tipoDeclarado = null;
		simbolos.setLista(false);
	}

	/**
	 * Completa o reconhecimento de um identificador em uma lista.
	 * 
	 * @param token
	 *            token do identificador.
	 * @throws SemanticError
	 *             caso o identificador já tenha sido declarado.
	 */
	private void acaoSemantica25(Token token) throws SemanticError {
		Identificador identificador = new Identificador(token.getLexeme(), tipoDeclarado);
		simbolos.inserir(identificador);
	}

	private void acaoSemantica24(Token token) {
		final String lexeme = token.getLexeme().toLowerCase().intern();
		switch (lexeme) {
		case "float":
			tipoDeclarado = FLOAT64;
			break;
		case "string":
			tipoDeclarado = STRING;
			break;
		case "boolean":
			tipoDeclarado = BOOL;
			break;
		case "int":
			tipoDeclarado = INT64;
			break;
		default:
			throw new IllegalArgumentException("tipo não suportado: " + lexeme);
		}
		simbolos.setLista(true);
	}

	/**
	 * Fator positivo precedido pelo símbolo de positivo ("+").
	 */
	private void acaoSemantica23(Token token) {
		// TODO: verificar tipo
	}

	/**
	 * Reconhecimento de literal.
	 */
	private void acaoSemantica22(Token token) {
		pilhaTipo.push(STRING);
		String lexeme = prepararValor(token.getLexeme(), STRING);
		instrucao.append("ldstr ").appendln(lexeme);
	}

	/**
	 * Efetivação do operador relacional reconhecido na
	 * {@link #acaoSemantica20(Token) ação 20}.
	 */
	private void acaoSemantica21(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1 != tipo2) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS);
		}

		pilhaTipo.push(BOOL);

		String command = null;
		switch (operadorRelacional) {
		case "==":
			command = "ceq";
			break;
		case "!=":
			command = "   "; // TODO
			break;
		case "<":
			command = "clt";
			break;
		case "<=":
			command = "   "; // TODO
			break;
		case ">":
			command = "cgt";
			break;
		case ">=":
			command = "   "; // TODO
			break;
		default:
			throw new SemanticError("Operador não reconhecido: " + operadorRelacional);
		}

		instrucao.appendln(command);
	}

	/**
	 * Reconhecimento de operador relacional.
	 */
	private void acaoSemantica20(Token token) {
		operadorRelacional = token.getLexeme();
	}

	/**
	 * Operador relacional "e" ("&&").
	 */
	private void acaoSemantica19(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (tipo1 != BOOL || tipo2 != BOOL) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS);
		}
		instrucao.appendln("and");

		pilhaTipo.push(BOOL);
	}

	/**
	 * Operador relacional "ou" ("||").
	 */
	private void acaoSemantica18(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (!isLogico(tipo1, tipo2)) {
			throw new SemanticError("Tipo incompatível na expressão da linha %d");
		}
		instrucao.appendln("or");

		pilhaTipo.push(BOOL);
	}

	/**
	 * Imprimir quebra de linha.
	 */
	private void acaoSemantica17() {
		print(STRING);
	}

	/**
	 * Fim de programa.
	 */
	private void acaoSemantica16(Token token) {
		instrucao.appendln("ret");
		instrucao.dec();
		instrucao.appendln("}");
		instrucao.dec();
		instrucao.appendln("}");
		instrucao.dec();

		System.out.println();
		System.out.println("A pilha de tipos terminou " + (pilhaTipo.isEmpty() ? "vazia" : "com os seguintes tipos: " + pilhaTipo.toString()));
		System.out.println("Códgigo gerado:");
		System.out.println("============================");
		System.out.println(instrucao);
	}

	/**
	 * Início de programa.
	 */
	private void acaoSemantica15(Token token) {
		instrucao.appendln(".assembly extern mscorlib{}");
		instrucao.appendln(".assembly " + fileName + "{}");
		instrucao.appendln(".module " + fileName + ".exe");
		instrucao.appendln();
		instrucao.appendln(".class public " + fileName + "");
		instrucao.appendln("{");
		instrucao.inc();
		instrucao.appendln(".method public static void _principal ()");
		instrucao.appendln("{");
		instrucao.inc();
		instrucao.appendln(".entrypoint");
	}

	/**
	 * Comandos <code>print</code> e <code>println</code>.
	 */
	private void acaoSemantica14() {
		String tipo = pilhaTipo.pop();
		print(tipo);
	}

	/**
	 * Operador de negação de um elemento.
	 */
	private void acaoSemantica13(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		if (tipo1 != BOOL) {
			throw new SemanticError("Tipos incompativeis na expressão da linha %d");
		}
		pilhaTipo.push(BOOL);
		instrucao.appendln("ldc.i4.1");
		instrucao.appendln("xor");
	}

	/**
	 * Reconhecimento da constante lógica <code>false<code>.
	 */
	private void acaoSemantica12(Token token) {
		if (token.getLexeme().equals("true")) { // TODO: isso realmente é possível, Arnaldo? oO
			instrucao.appendln("ldc.i4.1");
			pilhaTipo.push(BOOL);
		} else if (token.getLexeme().equals("false")) {
			instrucao.appendln("ldc.i4.0");
			pilhaTipo.push(BOOL);
		}
	}

	/**
	 * Reconhecimento da constante lógica <code>true<code>.
	 */
	private void acaoSemantica11(Token token) {
		if (token.getLexeme().equals("true")) {
			instrucao.appendln("ldc.i4.1");
			pilhaTipo.push(BOOL);
		} else if (token.getLexeme().equals("false")) { // TODO: isso realmente é possível, Arnaldo? oO
			instrucao.appendln("ldc.i4.0");
			pilhaTipo.push(BOOL);
		}
	}

	/**
	 * Fator precedido pelo símbolo de negativo ("-").
	 */
	private void acaoSemantica07(Token token) {
		instrucao.appendln("ldc.i8 -1");
		instrucao.appendln("mul");
	}

	/**
	 * Reconhecimento de real.
	 */
	private void acaoSemantica06(Token token) {
		pilhaTipo.push(FLOAT64);
		instrucao.appendln("ldc.r8 " + token.getLexeme().replace(',', '.'));
	}

	/**
	 * Reconhecimento de inteiro.
	 */
	private void acaoSemantica05(Token token) {
		pilhaTipo.push(INT64);
		instrucao.appendln("ldc.i8 " + token.getLexeme());
	}

	/**
	 * Divisão.
	 */
	private void acaoSemantica04(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!isNumerico(tipo1, tipo2)) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS, token.getPosition());
		}

		if (tipo1 == FLOAT64 || tipo2 == FLOAT64) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}

		instrucao.appendln("div");
	}

	/**
	 * Multiplicação.
	 */
	private void acaoSemantica03(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!isNumerico(tipo1, tipo2)) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS, token.getPosition());
		}

		if (tipo1 == FLOAT64 || tipo2 == FLOAT64) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("mul");
	}

	/**
	 * Subtração
	 */
	private void acaoSemantica02(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!isNumerico(tipo1, tipo2)) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS, token.getPosition());
		}

		if (tipo1 == FLOAT64 || tipo2 == FLOAT64) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("sub");
	}

	/**
	 * Soma
	 */
	private void acaoSemantica01(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!isNumerico(tipo1, tipo2)) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS, token.getPosition());
		}

		if (tipo1 == FLOAT64 || tipo2 == FLOAT64) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("add");
	}

	private void declararIds(List<Identificador> listaIds, String tipo) {
		instrucao.append(".locals (");
		Iterator<Identificador> it = listaIds.iterator();
		while (true) {
			instrucao.append(tipo).append(" ").append(it.next());
			if (it.hasNext()) {
				instrucao.append(", ");
				continue;
			}
			break;
		}
		instrucao.appendln(")");
	}

	private void atribuiId(Identificador id, String valor) {
		// TODO Auto-generated method stub

	}

	private void print(String tipo) {
		instrucao.appendln(String.format("call void [mscorlib]System.Console::Write(%s)", tipo));
	}

	public String getInstrucoes() {
		return instrucao.toString();
	}

	private static boolean isNumerico(String... tipos) {
		for (String tipo : tipos) {
			if (tipo != INT64 && tipo != FLOAT64) {
				return false;
			}
		}
		return true;
	}

	private static boolean isLogico(String... tipos) {
		for (String tipo : tipos) {
			if (tipo != BOOL) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Trata o lexema de acordo com o tipo declarado para que seja interpretado
	 * pelo MSIL corretamente.
	 * 
	 * @param lexeme
	 *            representação original do valor.
	 * @param tipo
	 *            tipo do valor.
	 * @return valor representado para o MSIL.
	 */
	private static String prepararValor(String lexeme, String tipo) {
		switch (tipo) {
		case FLOAT64:
			return lexeme.replace(',', '.');
		case STRING:
		case INT64:
		case BOOL:
		default:
			return lexeme;
		}
	}

}
