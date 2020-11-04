import java.awt.*;

public abstract class Figura
{
    protected Color cor;
	  
    protected Figura ()
    {
        this (Color.BLACK);
    }
	  
    protected Figura (Color cor)
    {
        this.cor = cor;
    }
	  
    public void setCor (Color cor)
    {
        this.cor = cor;
    }
	  
    public Color getCor()
    {
    	return this.cor;
    }
    
    
    
	@Override
	public boolean equals (Object obj)
	{
		if(this==obj)
			return true;
		
		if(obj==null)
			return false;
			
		if(this.getClass() != obj.getClass())
			return false;
			
		Figura f = (Figura) obj;
		
		if(!this.cor.equals(f.cor))
			return false;
			
		return true;
	}
  //public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    @Override
    public String toString()
    {	
		return "A figura tem a cor de contorno: "+"R:"+cor.getRed()+", G:"+cor.getGreen()+", B:"+cor.getBlue();
	}
    public abstract void    torneSeVisivel (Graphics g);
}
