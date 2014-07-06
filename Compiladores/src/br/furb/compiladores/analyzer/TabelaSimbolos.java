package br.furb.compiladores.analyzer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.furb.compiladores.analyzer.generated.SemanticError;

/**
 * Tipo abstrato de dados para realizar a verificação de uso de identificadores.
 * 
 * @author Vivian Panzenhagen
 * @author William Leander Seefeld
 * 
 */
public class TabelaSimbolos {

	private final Map<String, Identificador> identificadores;
	private boolean isLista = false;
	private final List<Identificador> listaIds;

	/**
	 * Cria uma tabela de símbolos vazia.
	 */
	public TabelaSimbolos() {
		identificadores = new LinkedHashMap<>();
		listaIds = new LinkedList<>();
	}

	/**
	 * Insere um identificador na tabela de símbolos.
	 * <p/>
	 * Para isso, verifica a existência do identificador na tabela. Em caso
	 * afirmativo, indica condição de erro: {@code identificador já declarado};
	 * em caso negativo, insere as informações (tupla) correspondentes ao
	 * identificador na tabela de símbolos.
	 * 
	 * @param identificador
	 *            identificador.
	 * @throws SemanticError
	 *             caso o identificador já tenha sido declarado.
	 */
	public void inserir(Identificador identificador) throws SemanticError {
		String lexema = identificador.getLexema().toLowerCase();
		if (identificadores.containsKey(lexema)) {
			throw new SemanticError("identificador já declarado: " + identificador.getLexema());
		}
		identificadores.put(lexema, identificador);
		if (isLista()) {
			listaIds.add(identificador);
		}
	}

	/**
	 * Recupera atributos associados a um identificador da tabela de
	 * símbolos.</p> Para isso, verifica a existência do identificador na
	 * tabela. Em caso afirmativo, recupera as informações correspondentes ao
	 * identificador; em caso negativo, indica condição de erro:
	 * {@code identificador não declarado}.
	 * 
	 * @param lexema
	 *            lexema do identificador.
	 * @return identificador e seus atributos.
	 * @throws SemanticError
	 *             se o lexema não for encontrado.
	 */
	public Identificador recuperar(String lexema) throws SemanticError {
		Identificador identificador = identificadores.get(lexema.toLowerCase());
		if (identificador == null) {
			throw new SemanticError("identificador não declarado: " + lexema);
		}
		if (isLista()) {
			listaIds.add(identificador);
		}
		return identificador;
	}

	/**
	 * Remove todas as entradas da tabela de símbolos.
	 */
	public void destruir() {
		identificadores.clear();
		listaIds.clear();
	}

	/**
	 * Verifica a existência de um identificador na tabela.
	 * 
	 * @return se o lexema corresponde a algum identificador armazenado.
	 */
	public boolean pesquisar(String lexema) {
		return identificadores.containsKey(lexema);
	}

	/**
	 * Verifica se os identificadores estão sendo armazenados em um conjunto
	 * separado que representa uma declaração em lista.
	 * 
	 * @return se os identificadores estão sendo armazenados em um conjunto
	 *         separado que representa uma declaração em lista.
	 */
	public final boolean isLista() {
		return isLista;
	}

	/**
	 * Define se os identificadores devem ser armazenados em um conjunto
	 * separado que representa uma declaração em lista.
	 * 
	 * @param isLista
	 *            se os identificadores devem ser armazenados em um conjunto
	 *            separado que representa uma declaração em lista.
	 */
	public final void setLista(boolean isLista) {
		this.isLista = isLista;
	}

	/**
	 * Retorna uma cópia da lista de identificadores reconhecidos em sequência
	 * enquanto houve o {@link #setLista(boolean) reconhecimento em lista}.
	 * 
	 * @return cópia da lista de identificadores reconhecidos em declaração em
	 *         lista.
	 */
	public List<Identificador> getListaIds() {
		return new ArrayList<>(listaIds);
	}

	/**
	 * Limpa a lista de símbolos armazenados até o momento.
	 */
	public void clearLista() {
		listaIds.clear();
	}

}
