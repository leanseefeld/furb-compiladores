package br.furb.compiladores.analyzer.generated;

public class Token
{
    private int id;
    private String lexeme;
    private int position;
    private boolean fimProg;

    public Token(int id, String lexeme, int position)
    {
        this.id = id;
        this.lexeme = lexeme;
        this.position = position;
    }
    
    public Token(int id, String lexeme, int position, boolean fimProg) {
 		this.id = id;
 		this.lexeme = lexeme;
 		this.position = position;
 		this.fimProg = fimProg;
 	}

    public final int getId()
    {
        return id;
    }

    public final String getLexeme()
    {
        return lexeme;
    }

    public final int getPosition()
    {
        return position;
    }
    
    public boolean isFimProg(){
    	return fimProg;
    }

    public String toString()
    {
        return id+" ( "+lexeme+" ) @ "+position;
    }
    
    public String getClassificacao(){
    	
    	if(this.id == 2){
    		return "identificador (" + this.lexeme + ")";
    	}
    	if(this.id == 3){
    		return "integer (" + this.lexeme + ")";
    	}
    	if(this.id == 4){
    		return "float (" + this.lexeme + ")";
    	}
    	
    	return lexeme;
    }
}
