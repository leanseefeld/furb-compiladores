package br.furb.compiladores.analyzer;

import wseefeld.utils.StringUtils;

/**
 * Construtor de código com mecanismo prático de indentação.
 * 
 * @author William Leander Seefeld
 * 
 */
public class IndentedCodeBuilder {

	public static final String DEFAULT_INDENT_TOKEN = "    ";

	private int indentLevel = 0;
	private String indentToken = DEFAULT_INDENT_TOKEN;
	private String indentStr = "";
	private StringBuilder builder = new StringBuilder();
	private boolean fAddIndent = false;

	public IndentedCodeBuilder append(Object obj) {
		checkIndent();
		builder.append(obj);
		fAddIndent = false;
		return this;
	}

	public IndentedCodeBuilder appendln() {
		return appendln("");
	}

	public IndentedCodeBuilder appendln(Object obj) {
		checkIndent();
		builder.append(obj).append("\r\n");
		fAddIndent = true;
		return this;
	}

	private void checkIndent() {
		if (fAddIndent) {
			builder.append(indentStr);
		}
	}

	public final int getIndentLevel() {
		return indentLevel;
	}

	public final void setIndentLevel(int indentLevel) {
		this.indentLevel = Math.max(0, indentLevel);
		updateIndentStr();
	}

	/**
	 * Aumenta a identação em 1 nível.
	 */
	public void inc() {
		setIndentLevel(indentLevel + 1);
	}

	/**
	 * Diminui a identação em 1 nível.
	 */
	public void dec() {
		setIndentLevel(indentLevel - 1);
	}

	public final String getIndentToken() {
		return indentToken;
	}

	public final void setIndentToken(String indentToken) {
		if (StringUtils.isEmpty(indentToken)) {
			this.indentToken = DEFAULT_INDENT_TOKEN;
		} else {
			this.indentToken = indentToken;
		}
		updateIndentStr();
	}

	private void updateIndentStr() {
		StringBuilder indentBuilder = new StringBuilder(indentToken.length() * indentLevel);
		for (int i = 0; i < indentLevel; i++) {
			indentBuilder.append(indentToken);
		}
		this.indentStr = indentBuilder.toString();
	}

	public final StringBuilder getBuilder() {
		return builder;
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
