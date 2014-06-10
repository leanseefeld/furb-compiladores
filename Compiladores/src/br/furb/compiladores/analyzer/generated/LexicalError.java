package br.furb.compiladores.analyzer.generated;

@SuppressWarnings("serial")
public class LexicalError extends AnalysisError implements ScannerConstants{

	private int endPosition;
	private int lastState;

	public LexicalError(String msg, int startPosition, int endPosition) {
		super(msg, startPosition);
		this.endPosition = endPosition;
	}
	
	public LexicalError(String msg,int lastState, int startPosition, int endPosition) {
		super(msg, startPosition);
		this.endPosition = endPosition;
		this.lastState = lastState;
	}
	
	//Usar esse construtor
	public LexicalError(int lastState, int position,char c) {
		super(geraMensagem(lastState, c), position);
		this.endPosition = endPosition;
		this.lastState = lastState;
	}
	
	//Constroi a mensagem de erro
	public static String geraMensagem(int lastState, char c){
		if(lastState == 0){
			return "Erro na linha %d - " + c + " " + SCANNER_ERROR[lastState];
		}else{
			return "Erro na linha %d - " + SCANNER_ERROR[lastState];
		}
	}
	
	
	public LexicalError(String msg, int position) {
		super(msg, position);
	}

	public LexicalError(String msg) {
		super(msg);
	}

	public int getEndPosition() {
		return endPosition;
	}
}
