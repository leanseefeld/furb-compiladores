package br.furb.compiladores.analyzer;

public class Mensagem {

	private int linha;
	private String mensagem;
	private boolean isErro;

	public Mensagem(int linha, String mensagem) {
		this(linha, mensagem, false);
	}

	public Mensagem(int linha, String mensagem, boolean isErro) {
		this.linha = linha;
		this.mensagem = mensagem;
		this.isErro = isErro;
	}

	public final int getLinha() {
		return linha;
	}

	public final String getMensagem() {
		return mensagem;
	}

	public boolean isErro() {
		return isErro;
	}

	public String getErro() {
		return String.format(this.mensagem, linha);
	}

}
