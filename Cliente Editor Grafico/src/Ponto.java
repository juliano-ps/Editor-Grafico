import java.awt.*;
import java.util.*;
 
public class Ponto extends Figura
{
    protected int x,  y;

    public Ponto (int x, int y) throws Exception 
    {
        this (x, y, Color.BLACK);
    }
	  
    public Ponto (int x, int y, Color cor) throws Exception
    {
        super (cor);
	
		if(x <=0 || y <=0)
			throw new Exception("O(s) Valor(es) nao pode(m) ser menor ou igual a zero");
		
        this.x = x;
        this.y = y;
    }

    public Ponto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        this.x = Integer.parseInt(quebrador.nextToken());
        this.y = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                              Integer.parseInt(quebrador.nextToken()),  // G
                              Integer.parseInt(quebrador.nextToken())); // B
    }

    public void setX (int x) throws Exception 
    {
		if(x <= 0)
			throw new Exception ("X nao pode ser menor ou igual a zero");
		
        this.x = x;
    }
	  
    public void setY (int y) throws Exception
    {
		if(y <= 0)
			throw new Exception ("Y nao pode ser menor ou igual a zero");
		
        this.y = y;
    }
	  
    public int getX ()
    {
        return this.x;
    }
	  
    public int getY ()
    {
    	return this.y;
    }
	  
    public void torneSeVisivel (Graphics g)
    {
    	g.setColor (this.cor);
    	g.drawLine (this.x,this.y,this.x,this.y);
    }

	@Override
    public String toString()
    {
        return "p:" +
               this.x +
               ":" +
               this.y +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
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
			
		Ponto p = (Ponto) obj;
		
		if(this.x != p.x || this.y != p.y || !this.cor.equals(p.cor) )
			return false;
		
		return true;
	}
		  
}
