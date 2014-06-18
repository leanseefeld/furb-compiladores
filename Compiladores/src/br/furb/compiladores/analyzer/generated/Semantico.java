package br.furb.compiladores.analyzer.generated;

import java.util.Stack;

import br.furb.compiladores.analyzer.IndentedCodeBuilder;

public class Semantico implements Constants {

	private static final String BOOL = "bool";
	private static final String STRING = "string";
	private static final String INT64 = "int64";
	private static final String FLOAT64 = "float64";

	private IndentedCodeBuilder instrucao = new IndentedCodeBuilder();
	private Stack<String> pilhaTipo = new Stack<>();
	private String operadorRelacional;
	private String fileName;

	public Semantico(String fileName) {
		this.fileName = fileName;
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
			}
		} catch (SemanticError se) {
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemanticError("Erro reconhecendo o programa: " + e.toString());
		}

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
		String lexeme = token.getLexeme().replace("'", "\""); // FIXME: isso torna impossível usar apóstrofos
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
			throw new SemanticError("Tipos incompatíveis na expressão da linha %d");
		}

		pilhaTipo.push(BOOL);

		String command = null;
		switch (operadorRelacional) {
		case "=":
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
		case ">":
			command = "cgt";
			break;
		case ">=":
			command = "   "; // TODO
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
			throw new SemanticError("Tipos incompatíveis na expressão da linha %d");
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
		if (tipo1 != BOOL || tipo2 != BOOL) {
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

	private void print(String tipo) {
		instrucao.appendln(String.format("call void [mscorlib]System.Console::Write(%s)", tipo));
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
		// TODO: verificação de tipo
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
			throw new SemanticError("Tipos incompatíveis na expressão da linha %d", token.getPosition());
		}

		pilhaTipo.push(FLOAT64);
		instrucao.appendln("div");
	}

	private static boolean isNumerico(String... tipos) {
		for (String tipo : tipos) {
			if (tipo != INT64 && tipo != FLOAT64) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Multiplicação.
	 */
	private void acaoSemantica03(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals(FLOAT64) && tipo2.equals(FLOAT64)) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("mul");
	}

	/**
	 * Subtração
	 */
	private void acaoSemantica02(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1 == FLOAT64 && tipo2 == FLOAT64) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("sub");
	}

	/**
	 * Soma
	 */
	private void acaoSemantica01(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals(FLOAT64) && tipo2.equals(FLOAT64)) {
			pilhaTipo.push(FLOAT64);
		} else {
			pilhaTipo.push(INT64);
		}
		instrucao.appendln("add");
	}

	public String getInstrucoes() {
		return instrucao.toString();
	}

}
