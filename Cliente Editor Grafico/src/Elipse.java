import java.awt.*;
import java.util.*;
 
public class Elipse extends FiguraPreenchida
{
    protected Ponto canto;
    int base, altura;
    
    public Elipse (int x,int y, int base, int altura) throws Exception
    {
        this (x, y, base, altura, Color.BLACK, Color.BLACK);
    }
	
    public Elipse (int x, int y, int base, int altura, Color cor, Color corPreenchida) throws Exception
    {
        super(cor,corPreenchida);
        
        if(base < 0 || altura < 0)
			throw new Exception ("O(s) valor(es) nao pode(m) ser negativo(s)");

        this.canto = new Ponto (x,y,cor);
        this.base = base;
        this.altura = altura;
        
    }

    public Elipse (String s) throws Exception
    {
		
        StringTokenizer quebrador = new StringTokenizer(s,":");
        
        quebrador.nextToken();

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y = Integer.parseInt(quebrador.nextToken());

		int   base = Integer.parseInt(quebrador.nextToken());
        int   altura  = Integer.parseInt(quebrador.nextToken());
        
        if(base < 0 || altura < 0)
			throw new Exception ("O(s) valor(es) nao pode(m) ser negativo(s)");

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
         
        Color corPreenchida = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.canto  = new Ponto (x,y,cor);
        this.base  = base;
        this.altura = altura;
        this.cor = cor;
        this.corPreenchida = corPreenchida;
    }

    public void setCanto (int x, int y) throws Exception
    {
		if(x < 0 || y < 0)
			throw new Exception ("O valor não deve ser menor ou igual a 0");
			
        this.canto = new Ponto (x,y,this.getCor());
    }
    
    public void setBase (int base) throws Exception
	{
		if(base < 0)
			throw new Exception ("A base nao pode ser menor ou igual a 0");
			
		this.base = base;
	}
	
	public void setAltura (int altura) throws Exception
	{
		if(altura < 0)
			throw new Exception ("A altura nao pode ser menor ou igual a 0");
		
		this.altura = altura;
	}
	
	public int getAltura()
	{
		return this.altura;
	}
	
	public int getBase()
	{
		return this.base;
	}

    public Ponto getCanto ()
    {
        return this.canto;
    }

	
    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corPreenchida);
		g.fillOval(this.canto.getX(), this.canto.getY(),this.base,this.altura);
        g.setColor(this.cor);
        g.drawOval(this.canto.getX(), this.canto.getY(),this.base,this.altura);
    }

	@Override
    public String toString()
    {
        return "e:" +
               this.canto.getX() +
               ":" +
               this.canto.getY() +
               ":" +
               this.base +
               ":" +
               this.altura +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
               ":" +
               this.getCorPreenchida().getRed() +
               ":" +
               this.getCorPreenchida().getGreen() +
               ":" +
               this.getCorPreenchida().getBlue();
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
			
		Elipse e = (Elipse) obj;
		
		if(this.base != e.base || this.altura != e.altura || !this.canto.equals(e.canto) || !this.cor.equals(e.cor) || !this.corPreenchida.equals(e.corPreenchida))
			return false;
			
		return true;
	}
}
