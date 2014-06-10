package br.furb.compiladores.analyzer.generated;

import java.util.Stack;

public class Semantico implements Constants {
	private StringBuilder instrucao = new StringBuilder();
	private Stack<String> pilhaTipo;
	private String operadorRelacional;

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
			acaoSemantica17(token);
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
		instrucao.append("   ldstr ").append(tok).append("\n");

	}

	private void acaoSemantica21(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!tipo1.equals(tipo2)) {
			throw new SemanticError("Tipos incompatíveis na expressão da linha %d");
		}

		pilhaTipo.push("bool");

		if (operadorRelacional.equals("=")) {
			instrucao.append("   ceq").append("\n");
		} else if (operadorRelacional.equals("!=")) {
			instrucao.append("   ").append("\n");
		} else if (operadorRelacional.equals("<")) {
			instrucao.append("   clt").append("\n");
		} else if (operadorRelacional.equals("<=")) {
			instrucao.append("   ").append("\n");
		} else if (operadorRelacional.equals(">")) {
			instrucao.append("   cgt").append("\n");
		} else if (operadorRelacional.equals(">=")) {
			instrucao.append("   ").append("\n");
		}

	}

	private void acaoSemantica20(Token token) {
		operadorRelacional = token.getLexeme();

	}

	private void acaoSemantica19(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError("Tipos incompatíveis na expressao da linha %d");
		}
		instrucao.append("   and").append("\n");

		pilhaTipo.push("bool");

	}

	private void acaoSemantica18(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError("Tipos incompatíveis na expressao da linha %d");
		}
		instrucao.append("   or").append("\n");

		pilhaTipo.push("bool");

	}

	private void acaoSemantica17(Token token) {
		// TODO Auto-generated method stub

	}

	private void acaoSemantica16(Token token) {
		// TODO Auto-generated method stub

	}

	private void acaoSemantica15(Token token) {
		// TODO Auto-generated method stub

	}

	private void acaoSemantica14(Token token) {
		// TODO Auto-generated method stub

	}

	private void acaoSemantica13(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();

		if (!tipo1.equals("bool")) {
			throw new SemanticError("Tipos incompativeis na expressao da linha %d");
		}

		pilhaTipo.push("bool");
		instrucao.append("   ldc.i4.1").append("\n");
		instrucao.append("   xor").append("\n");

	}

	private void acaoSemantica12(Token token) {
		if (token.getLexeme().equals("true")) {

			instrucao.append("   ldc.i4.1").append("\n");

			pilhaTipo.push("bool");
		}
		if (token.getLexeme().equals("false")) {

			instrucao.append("   ldc.i4.0").append("\n");

			pilhaTipo.push("bool");

		}

	}

	private void acaoSemantica11(Token token) {
		if (token.getLexeme().equals("true")) {

			instrucao.append("   ldc.i4.1").append("\n");

			pilhaTipo.push("bool");
		}
		if (token.getLexeme().equals("false")) {

			instrucao.append("   ldc.i4.0").append("\n");

			pilhaTipo.push("bool");

		}

	}

	private void acaoSemantica07(Token token) {
		instrucao.append("   ldc.i8 -1").append("\n");
		instrucao.append("   mul").append("\n");

	}

	private void acaoSemantica06(Token token) {
		pilhaTipo.push("float64");
		instrucao.append("   ldc.r8 " + token.getLexeme().replace(",", ".")).append("\n");
	}

	private void acaoSemantica05(Token token) {
		pilhaTipo.push("int64");
		instrucao.append("   ldc.i8 " + token.getLexeme().replace(",", ".")).append("\n");

	}

	private void acaoSemantica04(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (!tipo1.equals(tipo2)) {
			throw new SemanticError("Tipos incompatíveis na expressão da linha  %d");
		}

		pilhaTipo.push("float64");
		instrucao.append("   div").append("\n");

	}

	private void acaoSemantica03(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.append("   mul").append("\n");
	}

	private void acaoSemantica02(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.append("   sub").append("\n");

	}

	private void acaoSemantica01(Token token) {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1.equals("float64") && tipo2.equals("float64")) {
			pilhaTipo.push("float64");
		} else {
			pilhaTipo.push("int64");
		}
		instrucao.append("   add").append("\n");

	}

}