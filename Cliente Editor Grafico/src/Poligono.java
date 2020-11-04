import java.awt.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Poligono extends FiguraPreenchida
{
	protected Vector<Integer> vetorX, vetorY;
	
	public Poligono (Vector<Integer> vetorX, Vector<Integer> vetorY) throws Exception
	{
		this (vetorX,vetorY,Color.BLACK,Color.BLACK);
	}
	
	public Poligono (Vector<Integer> vetorX, Vector<Integer> vetorY, Color cor,Color corPreenchida) throws Exception
	{
		super(cor,corPreenchida);
		if(vetorX == null || vetorY == null)
		{
			throw new Exception ("O vetor não pode ser null");
		}
		
		this.vetorX = vetorX;
		this.vetorY = vetorY;
	}
	
	public Vector<Integer> getVetorX()
    {
		return this.vetorX;
	}
	
	public Vector<Integer> getVetorY()
	{
		return this.vetorY;
	}
	
	public void setVetorX (Vector<Integer> vetorX) throws Exception
	{
		if(vetorX == null)
		{
			throw new Exception("O vetor não pode ser nulo");
		}
		
		this.vetorX = vetorX;
	}
	
	public void setVetorY (Vector<Integer> vetorY) throws Exception
	{
		if(vetorY == null)
		{
			throw new Exception("O vetor não pode ser nulo");
		}
		
		this.vetorY = vetorY;
	}
	
	public void torneSeVisivel (Graphics g)
	{
		
		int vectorX[] = new int [this.vetorX.size()];
		int vectorY[] = new int [this.vetorX.size()];
		for(int i=0 ; i< vectorX.length ; i++)
		{
			vectorX[i]=vetorX.get(i);
			vectorY[i]=vetorY.get(i);
		}
		g.setColor(this.corPreenchida);
		g.fillPolygon(vectorX, vectorY, vectorY.length);
		g.setColor(this.cor);
		g.drawPolygon(vectorX, vectorY, vectorY.length);
	}
	
	public Poligono (String s)
    {
		
        
        StringTokenizer quebrador = new StringTokenizer(s,":");
        
        
        
        quebrador.nextToken();
        
		String tamanhoX = quebrador.nextToken();
		String tamanhoY = quebrador.nextToken();
         
        Vector<Integer> vetorX = new Vector<Integer> (1,1);
        Vector<Integer> vetorY = new Vector<Integer> (1,1);
        String subX = tamanhoX.substring(1, tamanhoX.length()-1);
        String subY = tamanhoY.substring(1, tamanhoY.length()-1);
		StringTokenizer quebradorDeVetorX = new StringTokenizer(subX,", ");
		StringTokenizer quebradorDeVetorY = new StringTokenizer(subY,", ");
		
	    while(quebradorDeVetorX.hasMoreTokens())
	    {
			vetorX.add(Integer.parseInt(quebradorDeVetorX.nextToken()));
			vetorY.add(Integer.parseInt(quebradorDeVetorY.nextToken()));
		}
		 
		
		
		
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
         
        Color corPreenchida = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

    
        
        this.vetorX = vetorX;
        this.vetorY = vetorY;
        this.cor = cor;
        this.corPreenchida = corPreenchida;
    }
	
	@Override
    public String toString()
    {
        return "g:" +
               this.vetorX.toString() +
               ":" +
               this.vetorY.toString() +
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
			
		Poligono p = (Poligono) obj;
		
		if(!this.vetorX.equals(p.vetorX) || !this.vetorY.equals(p.vetorY))
			return false;
			
		return true;
		
	}	
	
	
	
}
