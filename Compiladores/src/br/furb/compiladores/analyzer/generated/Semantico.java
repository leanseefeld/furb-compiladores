package br.furb.compiladores.analyzer.generated;

import java.util.Arrays;
import java.util.Stack;

public class Semantico implements Constants {

	private static final int STRING = Arrays.asList(
			ScannerConstants.SPECIAL_CASES_KEYS).indexOf("string");

	// TODO: usar algo menos gambioso...
	private TempStrBuilder instrucao = new TempStrBuilder();
	private Stack<String> pilhaTipo;
	private String operadorRelacional;
	private String fileName;

	private static class TempStrBuilder {

		StringBuilder builder = new StringBuilder();

		public TempStrBuilder appendln(Object obj) {
			builder.append(obj).append('\n');
			//TODO: debug
			System.out.println(obj);
			return this;
		}

		@Override
		public String toString() {
			return builder.toString();
		}
	}

	public Semantico(String fileName) {
		this.fileName = fileName;
	}

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação#" + action + ", Token: " + token);

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
			acaoSemantica14(token);
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

	}

	private void acaoSemantica23(Token token) {

	}

	private void acaoSemantica22(Token token) {
		pilhaTipo.push("string");
		String tok = token.getLexeme();
		for (int i = 0; i < tok.length(); i++) {
			tok = tok.replace("'", "\"");

		}
		instrucao.appendln("   ldstr ").appendln(tok);

	}

	private void acaoSemantica21(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!tipo1.equals(tipo2)) {
			throw new SemanticError(
					"Tipos incompatíveis na expressão da linha %d");
		}

		pilhaTipo.push("bool");

		if (operadorRelacional.equals("=")) {
			instrucao.appendln("   ceq");
		} else if (operadorRelacional.equals("!=")) {
			instrucao.appendln("   ");
		} else if (operadorRelacional.equals("<")) {
			instrucao.appendln("   clt");
		} else if (operadorRelacional.equals("<=")) {
			instrucao.appendln("   ");
		} else if (operadorRelacional.equals(">")) {
			instrucao.appendln("   cgt");
		} else if (operadorRelacional.equals(">=")) {
			instrucao.appendln("   ");
		}

	}

	private void acaoSemantica20(Token token) {
		operadorRelacional = token.getLexeme();

	}

	private void acaoSemantica19(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError(
					"Tipos incompatíveis na expressao da linha %d");
		}
		instrucao.appendln("   and");

		pilhaTipo.push("bool");

	}

	private void acaoSemantica18(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError(
					"Tipos incompatíveis na expressao da linha %d");
		}
		instrucao.appendln("   or");

		pilhaTipo.push("bool");

	}

	/**
	 * Imprimir quebra de linha.
	 */
	private void acaoSemantica17() {
		print(new Token(STRING, "\r\n", -1));
	}

	private void print(Token token) {
		String tipo = pilhaTipo.pop();
		instrucao.appendln(String.format(
				"   call void [mscorlib]System.Console::Write(%s)", tipo));
	}

	private void acaoSemantica16(Token token) {
		instrucao.appendln("     ret");
		instrucao.appendln("  }");
		instrucao.appendln("}");

		System.out.println("Códgigo gerado:");
		System.out.println("============================");
		System.out.println(instrucao);
	}

	private void acaoSemantica15(Token token) {
		instrucao.appendln(".assembly extern mscorlib{}");
		instrucao.appendln(".assembly " + fileName + "{}");
		instrucao.appendln(".module " + fileName + ".exe");
		instrucao.appendln("");
		instrucao.appendln(".class public " + fileName + " {");
		instrucao.appendln("  .method public static void _principal ()");
		instrucao.appendln("  {");
		instrucao.appendln("     .entrypoint");
	}

	/**
	 * Comandos <code>print</code> e <code>println</code>.
	 * 
	 * @param token
	 *            token a ser impresso.
	 */
	private void acaoSemantica14(Token token) {
		print(token);
	}

	private void acaoSemantica13(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();

		if (!tipo1.equals("bool")) {
			throw new SemanticError(
					"Tipos incompativeis na expressao da linha %d");
		}

		pilhaTipo.push("bool");
		instrucao.appendln("   ldc.i4.1");
		instrucao.appendln("   xor");

	}

	private void acaoSemantica12(Token token) {
		if (token.getLexeme().equals("true")) {

			instrucao.appendln("   ldc.i4.1");

			pilhaTipo.push("bool");
		}
		if (token.getLexeme().equals("false")) {

			instrucao.appendln("   ldc.i4.0");

			pilhaTipo.push("bool");

		}

	}

	private void acaoSemantica11(Token token) {
		if (token.getLexeme().equals("true")) {

			instrucao.appendln("   ldc.i4.1");

			pilhaTipo.push("bool");
		}
		if (token.getLexeme().equals("false")) {

			instrucao.appendln("   ldc.i4.0");

			pilhaTipo.push("bool");

		}

	}

	private void acaoSemantica07(Token token) {
		instrucao.appendln("   ldc.i8 -1");
		instrucao.appendln("   mul");

	}

	private void acaoSemantica06(Token token) {
		pilhaTipo.push("float64");
		instrucao.appendln("   ldc.r8 " + token.getLexeme().replace(",", "."));
	}

	private void acaoSemantica05(Token token) {
		pilhaTipo.push("int64");
		instrucao.appendln("   ldc.i8 " + token.getLexeme().replace(",", "."));

	}

	private void acaoSemantica04(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!tipo1.equals(tipo2)) {
			throw new SemanticError(
					"Tipos incompatíveis na expressão da linha  %d");
		}

		pilhaTipo.push("float64");
		instrucao.appendln("   div");

	}

	private void acaoSemantica03(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.appendln("   mul");
	}

	private void acaoSemantica02(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.appendln("   sub");

	}

	private void acaoSemantica01(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.appendln("   add");

	}

}
