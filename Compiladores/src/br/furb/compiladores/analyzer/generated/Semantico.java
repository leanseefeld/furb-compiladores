package br.furb.compiladores.analyzer.generated;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import br.furb.compiladores.analyzer.Identificador;
import br.furb.compiladores.analyzer.IndentedCodeBuilder;
import br.furb.compiladores.analyzer.TabelaSimbolos;

public class Semantico implements Constants {

	private static final String MSG_TIPOS_INCOMPATIVEIS = "Tipos incompatíveis na expressão da linha %d";
	private static final String MSG_TIPO_INCOMPATIVEL = "Tipo incompatível na expressão da linha %d";
	private static final String MSIL_BOOL = "bool";
	private static final String MSIL_STRING = "string";
	private static final String MSIL_INT64 = "int64";
	private static final String MSIL_FLOAT64 = "float64";
	private static final Identificador ID_LEFT_VALUE = new Identificador("__le", MSIL_FLOAT64);
	private static final Identificador ID_RIGHT_VALUE = new Identificador("__re", MSIL_FLOAT64);

	private final IndentedCodeBuilder instrucao;
	private final Stack<String> pilhaTipo;
	private final TabelaSimbolos simbolos;

	private String operadorRelacional;
	private String tipoDeclarado;
	private String fileName;
	private boolean isDeclarando;
	private Identificador identificador;
	private Map<String, Stack<String>> labels;
	private int proxRotulo = 1;

	private String DO_WHILE = "DO_WHILE";
	private String IF = "IF";
	private String FOR = "FOR";
	private boolean possuiHelperRelacional = false;

	public Semantico(String fileName) {
		this.fileName = fileName;
		instrucao = new IndentedCodeBuilder();
		pilhaTipo = new Stack<>();
		simbolos = new TabelaSimbolos();
		labels = new HashMap<String, Stack<String>>();
		labels.put(DO_WHILE, new Stack<String>());
		labels.put(IF, new Stack<String>());
		labels.put(FOR, new Stack<String>());
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
			case 10:
				acaoSemantica10(token);
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
			case 99:
				throw new SemanticError("Construção e uso de módulos não suportados");
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

	/**
	 * Fim do comando de repetição {@code "do-while"}.
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica35(Token token) {
		// TODO Vivian
		Stack<String> dowhile = labels.get(DO_WHILE);
		String label = dowhile.pop();
		// Faz o uso do label retirado para gerar o codigo para este label
		instrucao.append("brfalse ").appendln(label);
	}

	/**
	 * Início do comando de repetição {@code "do-while"}.
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica34(Token token) {
		String label = "r" + String.valueOf(proxRotulo);
		proxRotulo++;
		Stack<String> dowhile = labels.get(DO_WHILE);
		dowhile.push(label);
		// Gerar o codigo do label aqui?
	}

	/**
	 * Comando de seleção {@code "else"}
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica33(Token token) {
		// TODO Vivian
	}

	/**
	 * Fim do comando de seleção ({@code "end"}).
	 * 
	 * @param token
	 *            desnecessário.
	 * @throws SemanticError
	 */
	private void acaoSemantica32(Token token) {
		instrucao.dec();
		instrucao.append(labels.get(IF).pop()).append(":");
		instrucao.inc();
	}

	/**
	 * Comando de seleção {@code "if"}.
	 * 
	 * @param token
	 *            desnecessário.
	 * @throws SemanticError
	 */
	private void acaoSemantica31(Token token) throws SemanticError {
		String tipo = pilhaTipo.pop();
		if (tipo != MSIL_BOOL) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS);
		}
	}

	/**
	 * Atribui um valor inicial para todos os identificadores de uma lista
	 * recém-declarada.
	 * 
	 * @param token
	 *            token do valor inicial.
	 * @throws SemanticError
	 */
	private void acaoSemantica30(Token token) throws SemanticError {
		// FIXME: validar o tipo do token com o tipo declarado na lista
		List<Identificador> listaIds = simbolos.getListaIds();
		String tipo = listaIds.get(0).getTipo();
		String lexeme = token.getLexeme();
		String valor = prepararValor(lexeme, tipo);
		for (Identificador id : listaIds) {
			atribuiId(id, valor);
		}
	}

	/**
	 * Atribuição de variável.
	 * 
	 * @param token
	 *            expressão sendo atribuída.
	 */
	private void acaoSemantica29(Token token) {
		instrucao.append("stloc ").appendln(identificador.getLexema());
	}

	/**
	 * Identificador sendo usado como fator.
	 * 
	 * @param token
	 *            identificador.
	 */
	private void acaoSemantica28(Token token) throws SemanticError {
		Identificador id = simbolos.recuperar(token.getLexeme());
		instrucao.append("ldloc ").appendln(id.getLexema());
		pilhaTipo.push(id.getTipo());
	}

	/**
	 * Finalização da lista de identificadores para o comando de entrada.
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica27(Token token) {
		instrucao.appendln("call string [mscorlib]System.Console::ReadLine()");
		// duplica o valor de acordo com a quantidade de variáveis - 1
		List<Identificador> ids = this.simbolos.getListaIds();
		for (int i = 1; i < ids.size(); i++) {
			instrucao.appendln("dup");
		}
		// converte para cada tipo de variável
		for (Identificador id : ids) {
			String tipo = id.getTipo();
			if (tipo != MSIL_STRING) {
				instrucao.appendln(String.format("call %s [mscorlib]System.%s::Parse(string)", tipo, classeParaTipo(tipo)));
			}
			instrucao.append("stloc ").appendln(id.getLexema());
		}
	}

	/**
	 * Completa o reconhecimento de uma lista de declaração de identificadores.
	 * 
	 * @param token
	 *            desnecessário.
	 */
	private void acaoSemantica26(Token token) {
		tipoDeclarado = null;
		isDeclarando = false;
		simbolos.setLista(false);
		List<Identificador> listaIds = simbolos.getListaIds();
		String tipo = listaIds.get(0).getTipo();
		declararIds(listaIds, tipo);
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
		String lexema = token.getLexeme();
		if (isDeclarando) {
			simbolos.inserir(new Identificador(lexema, tipoDeclarado));
		} else {
			this.identificador = simbolos.recuperar(lexema);
		}
	}

	/**
	 * Reconhece o tipo de uma variável.
	 * 
	 * @param token
	 *            tipo da variável.
	 */
	private void acaoSemantica24(Token token) {
		final String lexeme = token.getLexeme().toLowerCase().intern();
		switch (lexeme) {
		case "float":
			tipoDeclarado = MSIL_FLOAT64;
			break;
		case "string":
			tipoDeclarado = MSIL_STRING;
			break;
		case "boolean":
			tipoDeclarado = MSIL_BOOL;
			break;
		case "integer":
			tipoDeclarado = MSIL_INT64;
			break;
		default:
			throw new IllegalArgumentException("tipo não suportado: " + lexeme);
		}
		isDeclarando = true;
		simbolos.clearLista();
		simbolos.setLista(true);
	}

	/**
	 * Fator precedido pelo símbolo de positivo ("+").
	 */
	private void acaoSemantica23(Token token) throws SemanticError {
		String tipo = pilhaTipo.peek();
		if (!isNumerico(tipo)) {
			throw new SemanticError(MSG_TIPO_INCOMPATIVEL);
		}
	}

	/**
	 * Reconhecimento de literal como fator.
	 */
	private void acaoSemantica22(Token token) {
		pilhaTipo.push(MSIL_STRING);
		String lexeme = prepararValor(token.getLexeme(), MSIL_STRING);
		instrucao.append("ldstr ").appendln(lexeme);
	}

	/**
	 * Efetivação do operador relacional reconhecido na
	 * {@link #acaoSemantica20(Token) ação 20}.
	 */
	private void acaoSemantica21(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();

		if (tipo1 != tipo2 || (!isNumerico(tipo1) && tipo1 != MSIL_STRING)) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS);
		}

		pilhaTipo.push(MSIL_BOOL);

		switch (operadorRelacional) {
		case "==":
			instrucao.append("ceq");
			break;
		case "!=":
			instrucao.append("ceq");
			negar();
			break;
		case "<":
			instrucao.appendln("clt");
			break;
		case "<=":
			dupToId(ID_RIGHT_VALUE);
			instrucao.appendln("clt");
			loadId(ID_LEFT_VALUE);
			loadId(ID_RIGHT_VALUE);
			instrucao.appendln("ceq");
			instrucao.appendln("or");
			break;
		case ">":
			instrucao.appendln("cgt");
			break;
		case ">=":
			dupToId(ID_RIGHT_VALUE);
			instrucao.appendln("cgt");
			loadId(ID_LEFT_VALUE);
			loadId(ID_RIGHT_VALUE);
			instrucao.appendln("ceq");
			instrucao.appendln("or");
			break;
		default:
			throw new SemanticError("Operador não reconhecido: " + operadorRelacional);
		}
	}

	/**
	 * Reconhecimento de operador relacional.
	 */
	private void acaoSemantica20(Token token) {
		operadorRelacional = token.getLexeme();
		if (operadorRelacional.equals("<=") || operadorRelacional.equals(">=")) {
			criarHelperRelacional();
			dupToId(ID_LEFT_VALUE);
		}
	}

	/**
	 * Operador relacional "e" ("&&").
	 */
	private void acaoSemantica19(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		String tipo2 = pilhaTipo.pop();
		if (tipo1 != MSIL_BOOL || tipo2 != MSIL_BOOL) {
			throw new SemanticError(MSG_TIPOS_INCOMPATIVEIS);
		}
		instrucao.appendln("and");

		pilhaTipo.push(MSIL_BOOL);
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

		pilhaTipo.push(MSIL_BOOL);
	}

	/**
	 * Imprimir quebra de linha.
	 */
	private void acaoSemantica17() {
		instrucao.append("ldstr ").appendln("\"\\n\"");
		print(MSIL_STRING);
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
	 * Negação lógica.
	 */
	private void acaoSemantica13(Token token) throws SemanticError {
		String tipo1 = pilhaTipo.pop();
		if (tipo1 != MSIL_BOOL) {
			throw new SemanticError(MSG_TIPO_INCOMPATIVEL);
		}
		pilhaTipo.push(MSIL_BOOL);
		negar();
	}

	/**
	 * Reconhecimento da constante lógica <code>false<code>.
	 */
	private void acaoSemantica12(Token token) {
		instrucao.appendln("ldc.i4.0");
		pilhaTipo.push(MSIL_BOOL);
	}

	/**
	 * Reconhecimento da constante lógica <code>true<code>.
	 */
	private void acaoSemantica11(Token token) {
		instrucao.appendln("ldc.i4.1");
		pilhaTipo.push(MSIL_BOOL);
	}

	/**
	 * Inicia o reconhecimento dos identificadores em um comando de leitura.
	 */
	private void acaoSemantica10(Token token) {
		simbolos.clearLista();
		simbolos.setLista(true);
	}

	/**
	 * Fator precedido pelo símbolo de negativo ("-").
	 */
	private void acaoSemantica07(Token token) throws SemanticError {
		String tipo = pilhaTipo.peek();
		if (!isNumerico(tipo)) {
			throw new SemanticError(MSG_TIPO_INCOMPATIVEL);
		}

		instrucao.appendln("ldc.i8 -1");
		instrucao.appendln("mul");
	}

	/**
	 * Reconhecimento de real como fator.
	 */
	private void acaoSemantica06(Token token) {
		pilhaTipo.push(MSIL_FLOAT64);
		instrucao.appendln("ldc.r8 " + token.getLexeme().replace(',', '.'));
	}

	/**
	 * Reconhecimento de inteiro como fator.
	 */
	private void acaoSemantica05(Token token) {
		pilhaTipo.push(MSIL_INT64);
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

		if (tipo1 == MSIL_FLOAT64 || tipo2 == MSIL_FLOAT64) {
			pilhaTipo.push(MSIL_FLOAT64);
		} else {
			pilhaTipo.push(MSIL_INT64);
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

		if (tipo1 == MSIL_FLOAT64 || tipo2 == MSIL_FLOAT64) {
			pilhaTipo.push(MSIL_FLOAT64);
		} else {
			pilhaTipo.push(MSIL_INT64);
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

		if (tipo1 == MSIL_FLOAT64 || tipo2 == MSIL_FLOAT64) {
			pilhaTipo.push(MSIL_FLOAT64);
		} else {
			pilhaTipo.push(MSIL_INT64);
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

		if (tipo1 == MSIL_FLOAT64 || tipo2 == MSIL_FLOAT64) {
			pilhaTipo.push(MSIL_FLOAT64);
		} else {
			pilhaTipo.push(MSIL_INT64);
		}
		instrucao.appendln("add");
	}

	private void criarHelperRelacional() {
		if (!possuiHelperRelacional) {
			declararIds(Arrays.asList(ID_LEFT_VALUE, ID_RIGHT_VALUE), ID_LEFT_VALUE.getTipo());
			possuiHelperRelacional = true;
		}
	}

	private void declararIds(List<Identificador> listaIds, String tipo) {
		instrucao.append(".locals (");
		Iterator<Identificador> it = listaIds.iterator();
		while (true) {
			instrucao.append(tipo).append(" ").append(it.next().getLexema());
			if (it.hasNext()) {
				instrucao.append(", ");
				continue;
			}
			break;
		}
		instrucao.appendln(")");
	}

	private void atribuiId(Identificador id, String valor) throws SemanticError {
		String loadCmd;
		switch (id.getTipo()) {
		case MSIL_STRING:
			loadCmd = "ldstr " + valor;
			break;
		case MSIL_FLOAT64:
			loadCmd = "ldc.r8 " + valor;
			break;
		case MSIL_INT64:
			loadCmd = "ldc.i8 " + valor;
			break;
		case MSIL_BOOL:
			loadCmd = "ldc.i4.";
			if ("true".equalsIgnoreCase(valor)) {
				loadCmd += "1";
			} else {
				loadCmd += "0";
			}
			break;
		default:
			throw new SemanticError("tipo não suportado: " + id.getTipo());
		}
		instrucao.appendln(loadCmd);
		instrucao.append("stloc ").appendln(id.getLexema());
	}

	private void print(String tipo) {
		instrucao.appendln(String.format("call void [mscorlib]System.Console::Write(%s)", tipo));
	}

	private void negar() {
		instrucao.appendln("ldc.i4.1");
		instrucao.appendln("xor");
	}

	public String getInstrucoes() {
		return instrucao.toString();
	}

	private void dupToId(Identificador id) {
		instrucao.appendln("dup");
		instrucao.append("stloc ").appendln(id.getLexema());
	}

	private void loadId(Identificador id) {
		instrucao.append("ldloc ").appendln(id.getLexema());
	}

	private static boolean isNumerico(String... tipos) {
		for (String tipo : tipos) {
			if (tipo != MSIL_INT64 && tipo != MSIL_FLOAT64) {
				return false;
			}
		}
		return true;
	}

	private static boolean isLogico(String... tipos) {
		for (String tipo : tipos) {
			if (tipo != MSIL_BOOL) {
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
		if (tipo == MSIL_FLOAT64) {
			return lexeme.replace(',', '.');
		}
		return lexeme;
	}

	private static String classeParaTipo(String tipo) {
		return String.valueOf(tipo.charAt(0)).toUpperCase().concat(tipo.substring(1).toLowerCase());
	}
}
