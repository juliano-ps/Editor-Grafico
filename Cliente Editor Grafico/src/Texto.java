import java.awt.*;
import java.util.*;
public class Texto extends Figura
{	
	protected String escrita;
	protected int x, y;
	protected Font fonte;
	
	public Texto(String escrita, int x, int y, Font fonte) throws Exception
	{
		this (escrita, x, y, fonte, Color.BLACK);
	}
	
	public Texto (String escrita, int x, int y, Font fonte, Color cor) throws Exception
	{
		super (cor);
		if(escrita == null)
		{
			throw new Exception ("A String não pode ser nula");
		}
		
		if(x < 0 || y < 0)
		{
			throw new Exception ("Os valores não podem ser nulos");
		}
		
		if(fonte == null)
		{
			throw new Exception ("A fonte não pode ser nula");
		}
		
		this.escrita = escrita;
		this.x = x;
		this.y = y;
		this.fonte = fonte;
	}
	
	public Texto (String s) throws Exception
    {
		
		
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
        
        String teste;
		
		short tamanhoEscrita = (Short.parseShort(quebrador.nextToken()));
		
		teste = quebrador.nextToken();
		
        if(teste.length() != tamanhoEscrita)
			do
			{
				teste = teste + ":" + quebrador.nextToken(); 
				
			}while(teste.length() != tamanhoEscrita);
			
		this.x = Integer.parseInt(quebrador.nextToken());
		this.y = Integer.parseInt(quebrador.nextToken());

		if(teste == null)
					throw new Exception ("A String não pode ser nula");

		Font fonte = new Font(quebrador.nextToken(),
							  Integer.parseInt(quebrador.nextToken()),
							  Integer.parseInt(quebrador.nextToken()));

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
                               
                               
        this.escrita = teste;
        this.cor = cor;
        this.fonte = fonte;
	}
	
	public String getEscrita ()
	{
		return this.escrita;
	}
	
	public void setEscrita (String escrita) throws Exception
	{
		if(escrita == null)
		{
			throw new Exception ("A string não pode ser nula");
		}
		
		this.escrita = escrita;
	}
	
	public int getX ()
	{
		return this.x;
	}
	
	public void setX (int x) throws Exception
	{
		if(x < 0)
		{
			throw new Exception ("O valor não pode ser menor ou igual a zero");
		}
		
		this.x = x;
	}
	
	public int getY ()
	{
		return this.y;
	}
	
	public void setY (int y) throws Exception
	{
		if(y < 0)
		{
			throw new Exception ("O valor não pode ser menor ou igual a zero");
		}
		
		this.y = y;
	}
	
	public Font getFonte()
	{
		return this.fonte;	
	}	
	
	public void setFonte(Font fonte) throws Exception
	{
		if(fonte == null)
		{
			throw new Exception ("A Fonte não pode ser nula");
		}
		
		this.fonte = fonte;
	}
	
	public void torneSeVisivel (Graphics g)
	{
		g.setColor(this.cor);
		g.setFont(this.fonte);
		g.drawString(this.escrita, this.x, this.y);
		
	}
	
	@Override
	public boolean equals (Object obj)
	{
		if(this == obj)
			return true;
			
		if(obj == null)
			return false;
			
		if(this.getClass() != obj.getClass())
			return false;
			
		Texto t = (Texto) obj;
		
		if(this.escrita.equals(t.escrita))
			return false;
			
		return true;
	}
	
	@Override
    public String toString()
    {
        return "t:" +
			   this.escrita.length()+
			   ":" +
               this.escrita +
               ":" +
               this.x +
               ":" +
               this.y +
               ":" +
			   this.fonte.getName() +
			   ":" +
			   Integer.toString(this.fonte.getStyle()) +
			   ":" +
			   Integer.toString(this.fonte.getSize()) +
			   ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
	
	
		
}
