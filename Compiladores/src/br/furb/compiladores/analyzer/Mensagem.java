package br.furb.compiladores.analyzer;

public class Mensagem {

	private int linha;
	private String mensagem;

	public Mensagem(int linha, String mensagem) {
		this.linha = linha;
		this.mensagem = mensagem;
	}

	public final int getLinha() {
		return linha;
	}

	public final String getMensagem() {
		return mensagem;
	}

	public String getErro(){
		return String.format(this.mensagem, linha);
	}
	
}
