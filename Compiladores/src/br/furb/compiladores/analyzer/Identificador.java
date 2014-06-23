package br.furb.compiladores.analyzer;

/**
 * Armazena os atributos de um identificador.
 * 
 * @author Vivian Panzenhagen
 * @author William Leander Seefeld
 * 
 */
public class Identificador {

	private final String lexema, tipo;

	public Identificador(String lexema, String tipo) {
		this.lexema = lexema;
		this.tipo = tipo;
	}

	public final String getLexema() {
		return lexema;
	}

	public final String getTipo() {
		return tipo;
	}

}
