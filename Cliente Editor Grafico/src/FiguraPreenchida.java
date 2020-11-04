import java.awt.*;
public abstract class FiguraPreenchida extends Figura
{
	protected Color corPreenchida;
	
	protected FiguraPreenchida ()
    {
        this (Color.BLACK,Color.BLACK);
    }
	  
    protected FiguraPreenchida (Color cor , Color corPreenchida)
    {
		super(cor);
        this.corPreenchida = corPreenchida;
    }
	  
    public void setCorPreenchida (Color cor)
    {
        this.corPreenchida = cor;
    }
	  
    public Color getCorPreenchida()
    {
    	return this.corPreenchida;
    }
    
    @Override
    public String toString()
    {		
		return super.toString()+" e a figura tem a cor de preenchimento: "+"R:"+corPreenchida.getRed()+", G:"+corPreenchida.getGreen()+", B:"+corPreenchida.getBlue();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
			
		if(obj==null)
			return false;
			
		if(this.getClass() != obj.getClass())
			return false;
			
		FiguraPreenchida fp = (FiguraPreenchida) obj;
		
		if(!this.cor.equals(fp.cor) || !this.corPreenchida.equals(fp.corPreenchida))
			return false;
			
		return true;
	}
		

}
