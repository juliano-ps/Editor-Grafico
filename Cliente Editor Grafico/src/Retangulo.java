import java.awt.*;
import java.util.*;

public class Retangulo extends FiguraPreenchida
{
	protected int base,
				  altura;
				  
	protected Ponto canto;
	
	public Retangulo (int base, int altura, int x, int y) throws Exception
	{
		this (base,altura,x,y,Color.BLACK,Color.BLACK);
	}
	
	public Retangulo (int base, int altura, int x, int y, Color cor, Color corPreenchida) throws Exception
	{
		super(cor, corPreenchida);
		
		if(base < 0 || altura < 0)
			throw new Exception ("O(s) valor(es) nao pode(m) ser negativo(s)");
		
		this.canto = new Ponto (x,y,cor);
		this.altura = altura;
		this.base = base;
	}
	
	public Retangulo (String s) throws Exception
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

		Color corPreenchida = new Color (Integer.parseInt(quebrador.nextToken()), // R
										 Integer.parseInt(quebrador.nextToken()), // G
										 Integer.parseInt(quebrador.nextToken()));// B

        this.canto  = new Ponto (x,y,cor);
        this.base = base;
        this.altura = altura;
        this.cor = cor;
        this.corPreenchida = corPreenchida;
    }
    
    public void setCanto (int x, int y) throws Exception
    {
		if(x < 0 || y< 0)
			throw new Exception ("Os valores nao podem ser iguais ou menores que zero");
			
		this.canto = new Ponto (x,y);
	}
	
	public void setBase (int base) throws Exception
	{
		if(base < 0)
			throw new Exception ("A base nao pode ser menor ou igual a zero");
		
		this.base = base;
	}
	
	public void setAltura (int altura) throws Exception
	{
		if(altura < 0)
			throw new Exception ("A altura nao pode ser menor ou igual a zero");
		
		this.altura = altura;
	}
	
	public Ponto getCanto()
	{
		return this.canto;
	}
	
	public int getAltura()
	{
		return this.altura;
	}
	
	public int getBase()
	{
		return this.base;
	}
	
	public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corPreenchida);
		g.fillRect(this.canto.getX(), this.canto.getY(),this.base,this.altura);
        g.setColor(this.cor);
        g.drawRect(this.canto.getX(), this.canto.getY(),this.base,this.altura);
    }			
	
	@Override
	public String toString()
    {
        return "r:" +
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
			
		Retangulo r = (Retangulo) obj;
		
		if(this.base != r.base || this.altura != r.altura || this.canto.equals(r.canto) || !this.cor.equals(r.cor) || !this.corPreenchida.equals(r.corPreenchida))
			return false;
			
		return true;
	}
}
	

