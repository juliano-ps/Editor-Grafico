import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class Quadrado extends FiguraPreenchida
{

	protected Ponto canto;

	protected Ponto canto2;

	public Quadrado (int x, int y, int x2, int y2) throws Exception
	{
		this (x,y,x2,y2,Color.BLACK,Color.BLACK);
	}

	public Quadrado (int x, int y, int x2, int y2, Color cor, Color corPreenchida) throws Exception
	{
		super(cor, corPreenchida);

		this.canto = new Ponto (x, y, cor);
		this.canto2 = new Ponto (x2, y2, cor);
	}

	public Quadrado (String s) throws Exception
    {

        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y = Integer.parseInt(quebrador.nextToken());

		int   x2 = Integer.parseInt(quebrador.nextToken());
		int   y2 = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

		Color corPreenchida = new Color (Integer.parseInt(quebrador.nextToken()),  // R
										 Integer.parseInt(quebrador.nextToken()),  // G
										 Integer.parseInt(quebrador.nextToken())); // B

        this.canto  = new Ponto (x,y,cor);
        this.canto2 = new Ponto (x2, y2, cor);
        this.cor = cor;
        this.corPreenchida = corPreenchida;
    }

    public void setCanto (int x, int y) throws Exception
    {
		this.canto = new Ponto (x,y);
	}

	public void setCanto2 (int x2, int y2) throws Exception
	{
		this.canto2 = new Ponto (x2, y2);
	}

	public Ponto getCanto()
	{
		return this.canto;
	}

	public Ponto getCanto2()
	{
		return this.canto2;
	}

	public void torneSeVisivel (Graphics g)
    {
		int altura,largura,lado;
		largura = Math.abs(canto.getX() - canto2.getX());
		altura = Math.abs(canto.getY() - canto2.getY());

		if (largura > altura)
			lado = altura;
		else
			lado = largura;

		if (canto2.getX() > canto.getX() && canto2.getY() > canto.getY())
		{
			g.setColor(this.corPreenchida);
			g.fillRect(this.canto.getX(), this.canto.getY(), lado, lado);
			g.setColor(this.cor);
			g.drawRect(this.canto.getX(), this.canto.getY(), lado, lado);
		}

		if (canto2.getX() > canto.getX() && canto2.getY() < canto.getY())
		{
			g.setColor(this.corPreenchida);
			g.fillRect(this.canto.getX(), this.canto.getY()-lado, lado, lado);
			g.setColor(this.cor);
			g.drawRect(this.canto.getX(), this.canto.getY()-lado, lado, lado);
		}

		if (canto2.getX() < canto.getX() && canto2.getY() > canto.getY())
		{
			g.setColor(this.corPreenchida);
			g.fillRect(this.canto.getX()-lado, this.canto.getY(), lado, lado);
			g.setColor(this.cor);
			g.drawRect(this.canto.getX()-lado, this.canto.getY(), lado, lado);
		}

		if (canto2.getX() < canto.getX() && canto2.getY() < canto.getY())
		{
			g.setColor(this.corPreenchida);
			g.fillRect(this.canto.getX()-lado, this.canto.getY()-lado, lado, lado);
			g.setColor(this.cor);
			g.drawRect(this.canto.getX()-lado, this.canto.getY()-lado, lado, lado);
		}
    }

	@Override
	public String toString()
    {
        return "q:" +
               this.canto.getX() +
               ":" +
               this.canto.getY() +
               ":" +
               this.canto2.getX() +
               ":" +
               this.canto2.getY() +
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
		if (this == obj)
			return true;

		if (this == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Quadrado q = (Quadrado) obj;

		if (!this.canto2.equals(q.canto2) || !this.canto.equals(q.canto) || !this.cor.equals(q.cor) || !this.corPreenchida.equals(q.corPreenchida))
			return false;

		return true;
	}
}


												
