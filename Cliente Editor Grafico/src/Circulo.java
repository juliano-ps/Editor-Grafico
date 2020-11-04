import java.awt.*;
import java.util.*;
 
public class Circulo extends FiguraPreenchida
{
    protected Ponto centro;
    protected int raio;
    
    public Circulo (int x,int y, int raio) throws Exception
    {
		this (x,y,raio, Color.BLACK, Color.BLACK);

    }
	
    public Circulo (int x, int y, int raio, Color cor, Color corPreenchida) throws Exception
    {
        super(cor,corPreenchida);
        
        if(raio < 0)
			throw new Exception ("O raio nao pode ser negativo");

        this.centro = new Ponto (x,y,cor);
        this.raio = raio;
    }

    public Circulo (String s) throws Exception
    {
		
		
        StringTokenizer quebrador = new StringTokenizer(s,":");
        
        quebrador.nextToken();

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y = Integer.parseInt(quebrador.nextToken());

        int   raio  = Integer.parseInt(quebrador.nextToken());
		if(raio < 0)
			throw new Exception("O raio não pode ser menor ou igual a zero");
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
         
        Color corPreenchida = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro  = new Ponto (x,y,cor);
        this.raio  = raio;
        this.cor = cor;
        this.corPreenchida = corPreenchida;
    }

    public void setRaio (int raio) throws Exception
    {
		if(raio < 0)
			throw new Exception ("O valor não deve ser menor ou igual a 0");
			
        this.raio = raio;
    }

    public void setCentro (int x, int y) throws Exception
    {
		if(x < 0 || y < 0)
			throw new Exception ("O valor não deve ser menor ou igual a 0");
			
        this.centro = new Ponto (x,y,this.getCor());
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corPreenchida);
		g.fillOval(this.centro.getX()-this.raio, this.centro.getY()-this.raio,   // ponto inicial
                   2*this.raio,2*this.raio);
        g.setColor(this.cor);
        g.drawOval(this.centro.getX()-this.raio, this.centro.getY()-this.raio,   // ponto inicial
                   2*this.raio,2*this.raio);  // ponto final
    }

	@Override
    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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
    public boolean equals(Object obj)
    {
		if(this==obj)
			return true;
			
		if(obj==null)
			return false;
			
		if(this.getClass() != obj.getClass())
			return false;
			
		Circulo c = (Circulo) obj;
		
		if(this.raio != c.raio || !this.centro.equals(c.centro) || !this.cor.equals(c.cor) || !this.corPreenchida.equals(c.corPreenchida))
			return false;
			
		return true;
	}
}

