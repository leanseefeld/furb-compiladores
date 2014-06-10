package br.furb.compiladores.analyzer.generated;

public class SyntaticError extends AnalysisError implements ParserConstants
{
    public SyntaticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SyntaticError(String msg)
    {
        super(msg);
    }
    
    public SyntaticError(int lastState, int position, Token token){
    	super(geraMensagem(lastState, token),position);
    }
    
    public static String geraMensagem(int lastState, Token token){
		if(token.isFimProg()){
			return "Erro na linha %d - encontrado fim de programa " + PARSER_ERROR[lastState];
		}
    	return "Erro na linha %d - encontrado " + token.getClassificacao() + " " + PARSER_ERROR[lastState];
    }
}
