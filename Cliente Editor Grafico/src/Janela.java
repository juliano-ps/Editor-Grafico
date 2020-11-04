import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.net.InetAddress;
import java.util.*;

import bd.dbos.Desenho;
import paint.*;
import javax.swing.filechooser.*;
 
public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto     = new JButton ("Ponto"),
                      btnLinha     = new JButton ("Linha"),
                      btnCirculo   = new JButton ("Círculo"),
                      btnElipse    = new JButton ("Elipse"),
                      btnQuadrado  = new JButton ("Quadrado"),
                      btnRetangulo = new JButton ("Retângulo"),
                      btnPoligono  = new JButton ("Poligono"),
                      btnParar     = new JButton ("Parar"),
                      btnCores     = new JButton ("Cor Contorno"),
                      btnCorPren   = new JButton ("Cor Preenchimento"),
                      btnTexto	   = new JButton ("Texto"),
                      btnFonte	   = new JButton ("Fonte"),
                      btnAbrir     = new JButton ("Abrir"),
                      btnAbrRemoto = new JButton ("Abrir Remoto"),
                      btnSalvar    = new JButton ("Salvar"),
                      btnSalRemoto = new JButton("Salvar Remoto"),
                      btnApagar    = new JButton ("Apagar"),
                      btnSair      = new JButton ("Sair");
                      

    protected MeuJPanel pnlDesenho = new MeuJPanel();
	protected MeuJPanel pnlDesenhoTemp = new MeuJPanel();
	protected MeuJPanel pnlDesenhos = new MeuJPanel();
	protected CardLayout crdDesenhos = new CardLayout(1,0);
	protected Desenho desenhoEscolhido = null;
	
	protected Container cntForm;
    
    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:");

	protected Parceiro servidor;

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaRaio, esperaCentro, esperaInicioQuadrado, esperaFimQuadrado, esperaInicioRetangulo, esperaFimRetangulo,
					  esperaInicioElipse, esperaFimElipse, esperaInicioPoligono, esperaFimPoligono, esperaInicioTexto, esperaFimTexto;

    protected Color corAtual = Color.BLACK;
    protected Color corPreenchida = Color.BLACK;
    protected Font fonte = new Font ("Arial", Font.PLAIN, 12);
    protected Ponto p1;
    protected Vector<Integer> armazenadorX = new Vector<Integer> (1,1);
    protected Vector<Integer> armazenadorY = new Vector<Integer> (1,1);
    protected Vector<Figura> figuras = new Vector<Figura>();
    protected String localSalvamento = "c:\\Desktop";

    public Janela (Parceiro servidor) throws Exception
    {
        super("Editor Gráfico");

        if (servidor == null)
            throw new Exception ("A conexão não pode ser nula");

        this.servidor = servidor;

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnCorPrenIm = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorPren.setIcon(new ImageIcon(btnCorPrenIm));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }


        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try
        {
            Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        try
        {
            Image btnTextoImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }


        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo());
        btnQuadrado.addActionListener (new DesenhoDeQuadrado());
        btnRetangulo.addActionListener (new DesenhoDeRetangulo());
        btnElipse.addActionListener (new DesenhoDeElipse());
        btnPoligono.addActionListener (new DesenhoDePoligono());
        btnCores.addActionListener (new EscolhaDeCor());
        btnCorPren.addActionListener (new EscolhaDeCorPreenchimento());
        btnTexto.addActionListener (new EscreverTexto());
        btnFonte.addActionListener (new EscolhaFonte());
        btnParar.addActionListener (new PararPoligono());
        btnSalvar.addActionListener (new SalvarDesenho());
        btnSalRemoto.addActionListener (new SalvarRemoto());
        btnAbrir.addActionListener (new AbrirArquivo());
        btnAbrRemoto.addActionListener (new AbrirRemoto());
        btnApagar.addActionListener (new ApagarDesenho());

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnAbrRemoto);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnSalRemoto);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnPoligono);
        pnlBotoes.add (btnTexto);
        pnlBotoes.add (btnFonte);
        pnlBotoes.add (btnCores);
        pnlBotoes.add (btnCorPren);
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnSair);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,3);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(btnParar);
        pnlStatus.add(statusBar2);
        pnlDesenhos.setLayout(crdDesenhos);
        pnlDesenhos.add(pnlDesenho);
        pnlDesenhos.add(pnlDesenhoTemp);

        cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenhos, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (700,500);
        this.setVisible (true);
    }

    protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener
    {
		public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e)
        {
            if (esperaPonto)
            {
				try
				{
					figuras.add (new Ponto (e.getX(), e.getY(), corAtual));
					figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
				}
				catch(Exception erroPonto)
				{
					System.err.println(erroPonto.getMessage());
				}
            }
            else
                if (esperaInicioReta)
                {
					try
					{
						p1 = new Ponto (e.getX(), e.getY(), corAtual);
						figuras.add (new Linha (e.getX(), e.getY(), e.getX(), e.getY(), corAtual));
						figuras.get (figuras.size() - 1).torneSeVisivel(pnlDesenhos.getGraphics());
                    }
                    catch(Exception erroPonto)
					{
						System.err.println(erroPonto.getMessage());
					}
                    
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Mensagem: clique o ponto final da reta");    
                 }
                else
                    if (esperaCentro)
                    {
                        try
                        {
                            p1 = new Ponto (e.getX(), e.getY(), corAtual);
                            figuras.add (new Circulo(p1.getX(), p1.getY(), 0, corAtual , corPreenchida));
                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                        }
                        catch(Exception erroPonto)
                        {
                            System.err.println(erroPonto.getMessage());
                        }

                        esperaCentro = false;
                        esperaRaio = true;
                        statusBar1.setText("Mensagem: clique num ponto do circulo");
                    }
                    else
                        if (esperaInicioRetangulo)
                        {
                            try
                            {
                                p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                figuras.add (new Retangulo (0, 0, e.getX(), e.getY(), corAtual, corPreenchida));
                                                        figuras.get (figuras.size() - 1).torneSeVisivel(pnlDesenhos.getGraphics());
                            }
                            catch(Exception erroPonto)
                            {
                                System.err.println(erroPonto.getMessage());
                            }
                            esperaInicioRetangulo = false;
                            esperaFimRetangulo = true;
                            statusBar1.setText("Mensagem: clique no ponto final do retangulo");
                        }
                        else
                            if (esperaInicioElipse)
                            {
                                try
                                {
                                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                    figuras.add (new Elipse (e.getX(), e.getY(), 0, 0, corAtual, corPreenchida));
                                                    figuras.get (figuras.size() - 1).torneSeVisivel(pnlDesenhos.getGraphics());
                                }
                                catch(Exception erroPonto)
                                {
                                    System.err.println(erroPonto.getMessage());
                                }
                                esperaInicioElipse = false;
                                esperaFimElipse = true;
                                statusBar1.setText("Mensagem: clique no ponto final da elipse");
                            }
                            else
                                if(esperaInicioPoligono)
                                {
                                    try
                                    {
                                        armazenadorX.add(e.getX());
                                        armazenadorY.add(e.getY());
                                        armazenadorX.add(e.getX());
                                        armazenadorY.add(e.getY());
                                        Vector<Integer> vetorXPronto = (Vector<Integer>)armazenadorX.clone();
                                        Vector<Integer> vetorYPronto = (Vector<Integer>)armazenadorY.clone();
                                        figuras.add (new Poligono(vetorXPronto, vetorYPronto,
                                                         corAtual,corPreenchida));
                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());

                                    }
                                    catch(Exception erroPonto)
                                    {
                                        System.err.println(erroPonto.getMessage());
                                    }

                                    esperaFimPoligono = true;
                                    esperaInicioPoligono = false;

                                    statusBar1.setText("Mensagem: Quando quiser parar clique no bot�o parar");
                                }
                                else
                                    if(esperaInicioTexto)
                                    {
                                        try
                                        {
                                            figuras.add(new Texto (JOptionPane.showInputDialog("Digite o que deseja: "), e.getX(), e.getY(), fonte, corAtual));
                                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());

                                        }
                                        catch(Exception erro)
                                        {
                                            System.err.println(erro.getMessage());
                                        }
                                        esperaInicioTexto = true;
                                    }
                                    else
                                        if (esperaInicioQuadrado)
                                        {
                                            try
                                            {
                                                p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                                figuras.add (new Quadrado (e.getX(), e.getY(), e.getX(), e.getY(), corAtual, corPreenchida));
                                                figuras.get (figuras.size() - 1).torneSeVisivel(pnlDesenhos.getGraphics());
                                            }
                                            catch(Exception erroPonto)
                                            {
                                                System.err.println(erroPonto.getMessage());
                                            }
                                            esperaInicioQuadrado = false;
                                            esperaFimQuadrado = true;
                                            statusBar1.setText("Mensagem: clique no ponto final do quadrado");
                                        }
        }

        public void mouseReleased (MouseEvent e)
        {
			if (esperaFimReta)
			{
				Linha l = (Linha)figuras.remove(figuras.size() - 1);
				
				try
				{
					l.setP2(e.getX(), e.getY());
					figuras.add (l);
					crdDesenhos.last(pnlDesenhos);
					figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
					cntForm.revalidate();
					cntForm.repaint();
				}
				catch(Exception erroPonto)
				{	
					System.err.println(erroPonto.getMessage());
				}
				statusBar1.setText("Mensagem: clique no ponto inicial da reta");    
				esperaInicioReta = true;
			}
			else
			    if (esperaFimQuadrado)
			    {
                    Quadrado q = (Quadrado)figuras.remove(figuras.size() - 1);
                    try
                    {
                        q.setCanto2(e.getX(), e.getY());
                        figuras.add(q);

                        crdDesenhos.last(pnlDesenhos);
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                        cntForm.revalidate();
                        cntForm.repaint();
                    }
                    catch (Exception erroQuadrado)
                    {
                        System.err.println("Deu ruim");
                    }
                    esperaInicioQuadrado = true;
                    esperaFimQuadrado = false;
                    statusBar1.setText("Arraste até o final do quadrado");
			    }
			    else
			        if (esperaFimRetangulo)
                    {
                        int x,y;

                        if(e.getX()<p1.getX())
                            x = e.getX();
                        else
                            x = p1.getX();
                        if (e.getY()<p1.getY())
                            y = e.getY();
                        else
                            y = p1.getY();

                        Retangulo r = (Retangulo)figuras.remove(figuras.size() - 1);
                        try
                        {
                            r.setCanto(x, y);
                            r.setBase(Math.abs(e.getX()-p1.getX()));
                            r.setAltura(Math.abs(e.getY()-p1.getY()));
                            figuras.add(r);
                            crdDesenhos.last(pnlDesenhos);
                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                            cntForm.revalidate();
                            cntForm.repaint();
                        }
                        catch (Exception erroRetangulo)
                        {
                            System.err.println(erroRetangulo.getMessage());
                        }
                        esperaFimRetangulo = false;
                        esperaInicioRetangulo = true;
                        statusBar1.setText("Mensagem: clique no ponto inicial do retangulo ");
                    }
                    else
                        if (esperaRaio)
                        {
                            int raio = (int)(Math.round(Math.sqrt(Math.pow(e.getY()-p1.getY(),2)+Math.pow(e.getX()-p1.getX(),2))));

                            Circulo c = (Circulo)figuras.remove(figuras.size() - 1);
                            try
                            {
                                c.setRaio(raio);
                                figuras.add (c);
                                crdDesenhos.last(pnlDesenhos);
                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                cntForm.revalidate();
                                cntForm.repaint();
                            }
                            catch (Exception erroCirculo)
                            {
                                System.err.println(erroCirculo.getMessage());
                            }

                            esperaRaio = false;
                            esperaCentro = true;
                            statusBar1.setText("Mensagem: clique no centro do circulo");
                        }
                        else
                            if(esperaFimElipse)
                            {
                                int x,y;

                                if(e.getX()<p1.getX())
                                    x = e.getX();
                                else
                                    x = p1.getX();
                                if (e.getY()<p1.getY())
                                    y = e.getY();
                                else
                                    y = p1.getY();

                                Elipse el = (Elipse)figuras.remove(figuras.size() - 1);
                                try
                                {
                                    el.setCanto(x, y);
                                    el.setBase(Math.abs(e.getX()-p1.getX()));
                                    el.setAltura(Math.abs(e.getY()-p1.getY()));
                                    figuras.add (el);
                                    crdDesenhos.last(pnlDesenhos);
                                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                    cntForm.revalidate();
                                    cntForm.repaint();
                                }
                                catch(Exception erroElipse)
                                {
                                    System.err.println(erroElipse.getMessage());
                                }
                                esperaFimElipse = false;
                                esperaInicioElipse = true;
                                statusBar1.setText("Mensagem: clique no ponto inicial da elipse ");
                            }
                            else
                                if  (esperaFimPoligono)
                                {
                                    Poligono pl = (Poligono)figuras.remove(figuras.size() - 1);
                                    try
                                    {
                                        Vector<Integer> plX = pl.getVetorX();
                                        plX.remove(plX.size() - 1);
                                        plX.add(e.getX());
                                        plX.add(e.getX());
                                        Vector<Integer> plY = pl.getVetorY();
                                        plY.remove(plY.size() - 1);
                                        plY.add(e.getY());
                                        plY.add(e.getY());
                                        pl.setVetorX(plX);
                                        pl.setVetorY(plY);
                                        figuras.add (pl);
                                        crdDesenhos.last(pnlDesenhos);
                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                        cntForm.revalidate();
                                        cntForm.repaint();
                                    }
                                    catch(Exception erroPoligono)
                                    {
                                        System.err.println(erroPoligono.getMessage());
                                    }

                                    esperaFimPoligono = true;
                                    esperaInicioPoligono = false;
                                    statusBar1.setText("Mensagem: Clique no bot�o parar quando quiser parar");
                                }
		}
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {
			if (esperaFimReta)
            {
                Linha l = (Linha)figuras.remove(figuras.size() - 1);
                try
                {
                    l.setP2(e.getX(), e.getY());
                    figuras.add (l);
                    crdDesenhos.last(pnlDesenhos);
                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                    cntForm.revalidate();
                    cntForm.repaint();
                }
                catch(Exception erroPonto)
                {
                    System.err.println(erroPonto.getMessage());
                }
                statusBar1.setText("Mensagem: clique no ponto inicial da reta");
                esperaInicioReta = false;
                esperaFimReta = true;
            }
			else
			    if (esperaFimQuadrado)
                {

                    Quadrado q = (Quadrado)figuras.remove(figuras.size() - 1);
                    try
                    {
                        q.setCanto2(e.getX(), e.getY());
                        figuras.add(q);

                        crdDesenhos.last(pnlDesenhos);
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                        cntForm.revalidate();
                        cntForm.repaint();
                    }
                    catch (Exception erroQuadrado)
                    {
                        System.err.println("Deu ruim");
                    }
                    esperaInicioQuadrado = false;
                    esperaFimQuadrado = true;
                    statusBar1.setText("Arraste até o final do quadrado");
                }
                else
                    if (esperaFimRetangulo)
                    {
                        int x,y;

                        if(e.getX()<p1.getX())
                            x = e.getX();
                        else
                            x = p1.getX();
                        if (e.getY()<p1.getY())
                            y = e.getY();
                        else
                            y = p1.getY();

                        Retangulo r = (Retangulo)figuras.remove(figuras.size() - 1);
                        try
                        {
                            r.setCanto(x, y);
                            r.setBase(Math.abs(e.getX()-p1.getX()));
                            r.setAltura(Math.abs(e.getY()-p1.getY()));
                            figuras.add(r);
                            crdDesenhos.last(pnlDesenhos);
                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                            cntForm.revalidate();
                            cntForm.repaint();
                        }
                        catch (Exception erroRetangulo)
                        {
                            System.err.println(erroRetangulo.getMessage());
                        }
                        esperaFimRetangulo = true;
                        esperaInicioRetangulo = false;
                        statusBar1.setText("Mensagem: clique no ponto inicial do retangulo ");
                    }
                    else
                        if (esperaRaio)
                        {
                            int raio = (int)(Math.round(Math.sqrt(Math.pow(e.getY()-p1.getY(),2)+Math.pow(e.getX()-p1.getX(),2))));

                            Circulo c = (Circulo)figuras.remove(figuras.size() - 1);
                            try
                            {
                                c.setRaio(raio);
                                figuras.add (c);
                                crdDesenhos.last(pnlDesenhos);
                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                cntForm.revalidate();
                                cntForm.repaint();
                            }
                            catch (Exception erroCirculo)
                            {
                                System.err.println(erroCirculo.getMessage());
                            }

                            esperaRaio = true;
                            esperaCentro = false;
                            statusBar1.setText("Mensagem: clique no centro do circulo");
                        }
                        else
                            if(esperaFimElipse)
                            {
                                int x,y;

                                if(e.getX()<p1.getX())
                                    x = e.getX();
                                else
                                    x = p1.getX();
                                if (e.getY()<p1.getY())
                                    y = e.getY();
                                else
                                    y = p1.getY();

                                Elipse el = (Elipse)figuras.remove(figuras.size() - 1);
                                try
                                {
                                    el.setCanto(x, y);
                                    el.setBase(Math.abs(e.getX()-p1.getX()));
                                    el.setAltura(Math.abs(e.getY()-p1.getY()));
                                    figuras.add (el);
                                    crdDesenhos.last(pnlDesenhos);
                                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                    cntForm.revalidate();
                                    cntForm.repaint();
                                }
                                catch(Exception erroElipse)
                                {
                                    System.err.println(erroElipse.getMessage());
                                }

                                esperaFimElipse = true;
                                esperaInicioElipse = false;
                                statusBar1.setText("Mensagem: clique no ponto inicial da elipse ");

                            }
                            else
                                if  (esperaFimPoligono)
                                {
                                    Poligono pl = (Poligono)figuras.remove(figuras.size() - 1);
                                    try
                                    {
                                        Vector<Integer> plX = pl.getVetorX();
                                        plX.remove(plX.size() - 1);
                                        plX.add(e.getX());
                                        Vector<Integer> plY = pl.getVetorY();
                                        plY.remove(plY.size() - 1);
                                        plY.add(e.getY());
                                        pl.setVetorX(plX);
                                        pl.setVetorY(plY);
                                        figuras.add (pl);
                                        crdDesenhos.last(pnlDesenhos);
                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
                                        cntForm.revalidate();
                                        cntForm.repaint();
                                    }
                                    catch(Exception erroPoligono)
                                    {
                                        System.err.println(erroPoligono.getMessage());
                                    }

                                    esperaFimPoligono = true;
                                    esperaInicioPoligono = false;
                                    statusBar1.setText("Mensagem: Clique no bot�o parar quando quiser parar");
                                }
		}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      		= true;
              esperaInicioReta 		= false;
              esperaFimReta    		= false;
			  esperaCentro 	  		= false;
              esperaRaio       	    = false;
              esperaInicioQuadrado  = false;
              esperaFimQuadrado	    = false;
              esperaInicioRetangulo = false;
              esperaFimRetangulo 	= false;
              esperaInicioElipse	= false;
              esperaFimElipse       = false;
              esperaInicioPoligono  = false;
              esperaFimPoligono     = false;
              esperaInicioTexto     = false;
              esperaFimTexto		= false;

              statusBar1.setText("Mensagem: clique o local do ponto desejado");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto           = false;
            esperaInicioReta      = true;
            esperaFimReta         = false;
			esperaCentro 	      = false;
            esperaRaio            = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo    = false;
            esperaInicioElipse	  = false;
            esperaFimElipse       = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
    
    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto     	  = false;
            esperaInicioReta	  = false;
            esperaFimReta  		  = false;
            esperaCentro 		  = true;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse       = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: clique no centro do circulo ");
        }
    }
    
    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto     	  = false;
            esperaInicioReta	  = false;
            esperaFimReta  		  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = true;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse       = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: clique no ponto inicial do quadrado ");
        }
    }
    
    protected class DesenhoDeRetangulo implements ActionListener
    {
		public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      	  = false;
            esperaInicioReta 	  = false;
            esperaFimReta    	  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = true;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse       = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: clique no ponto inicial do retangulo ");
        }
	}
	
	protected class DesenhoDeElipse implements ActionListener
    {
		public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      	  = false;
            esperaInicioReta 	  = false;
            esperaFimReta    	  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = true;
            esperaFimElipse		  = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: clique no ponto inicial da elipse ");
        }
	}
	
	protected class DesenhoDePoligono implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			esperaPonto      	  = false;
            esperaInicioReta 	  = false;
            esperaFimReta    	  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse		  = false;
            esperaInicioPoligono  = true;
            esperaFimPoligono     = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;

            statusBar1.setText("Mensagem: Clique nos v�rtices do pol�gono");
		}
	}
	
	protected class PararPoligono implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			esperaPonto      	  = false;
            esperaInicioReta 	  = false;
            esperaFimReta    	  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse		  = false;
            esperaInicioTexto     = false;
            esperaFimTexto		  = false;
            esperaFimPoligono	  = false;
            esperaInicioPoligono  = true;
            
            armazenadorX.clear();
            armazenadorY.clear();
		}   
	}	
	
	protected class EscreverTexto implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			esperaPonto      	  = false;
            esperaInicioReta 	  = false;
            esperaFimReta    	  = false;
            esperaCentro 		  = false;
            esperaRaio       	  = false;
            esperaInicioQuadrado  = false;
            esperaFimQuadrado	  = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo 	  = false;
            esperaInicioElipse	  = false;
            esperaFimElipse		  = false;
            esperaInicioPoligono  = false;
            esperaFimPoligono     = false;
            esperaInicioTexto     = true;
            esperaFimTexto		  = false;
            
            statusBar1.setText("Mensagem: clique no ponto em que deseja escrever");
            
		}
	}
	
	protected class EscolhaFonte implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			JFontChooser fonteEscolhida = new JFontChooser();
			if(fonteEscolhida.showDialog(getContentPane()) == JFontChooser.OK_OPTION)
			{
				fonte = fonteEscolhida.getSelectedFont();
			}
			
		}
	}
	
	
	protected class EscolhaDeCor implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			corAtual = JColorChooser.showDialog(getContentPane(), "Selecione a cor do contorno", Color.BLACK);
			
		}
	}
	
	protected class EscolhaDeCorPreenchimento implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			corPreenchida = JColorChooser.showDialog(getContentPane(), "Selecione a cor que deseja preencher", Color.BLACK);
		}
	}
	
	protected class SalvarDesenho implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			try
			{
				JFileChooser salvadorDeArquivos = new JFileChooser();
				salvadorDeArquivos.setFileFilter(new FileNameExtensionFilter("Desenhos","jlp"));
				salvadorDeArquivos.showSaveDialog(getContentPane());
				localSalvamento = salvadorDeArquivos.getSelectedFile().getParent();
				PrintWriter salvadorDeDesenhos = new PrintWriter(salvadorDeArquivos.getSelectedFile().getPath()+".jlp");
				for (Figura figura: figuras)
				{
					salvadorDeDesenhos.println(figura);
				}
				salvadorDeDesenhos.close();
			}
			catch(Exception erro)
			{
				JOptionPane.showMessageDialog(null/* sem janela mãe */,
                    "Não foi possivel salvar o seu desenho, tente novamente", "Erro de salvamento",
                    JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected class SalvarRemoto implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            try
            {
                Desenho desenhoTerminado;

                if(desenhoEscolhido == null)
                {
                    Date dataCricao = new Date();
                    desenhoTerminado = new Desenho (JOptionPane.showInputDialog("Digite o nome do desenho: "), dataCricao.toString(), dataCricao.toString(), InetAddress.getLocalHost().getHostAddress());
                }
                else
                {
                    desenhoTerminado = desenhoEscolhido;
                    desenhoTerminado.setDataUltimaAtualizacao(new Date().toString());
                    desenhoTerminado.limparVetorDeFiguras();
                }

                for(Figura elemento: figuras)
                {
                    desenhoTerminado.addFigura(elemento.toString());
                }

                servidor.receba(new PedidoSalvamento(desenhoTerminado));

                JOptionPane.showMessageDialog(getContentPane(), "Desenho salvo com sucesso");
            }
            catch (Exception erro)
            {
                JOptionPane.showMessageDialog(getContentPane(), "Não foi possivel salvar remotamente");
            }

        }
    }

	protected class AbrirRemoto implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
            try
            {
                servidor.receba(new PedidoAbertura(InetAddress.getLocalHost().getHostAddress()));
                Comunicado comunicado = servidor.envie();
                if (comunicado instanceof PedidoRetorno)
                {
                    PedidoRetorno pedidoRetornado = (PedidoRetorno) comunicado;

                    ArrayList<Desenho> desenhosCarregados = pedidoRetornado.getListaDeDesenhos();

                    JanelaDeDesenhos janelaDeDesenhos = new JanelaDeDesenhos(desenhosCarregados, Janela.this);
                }
            }
            catch(Exception erro)
            {
                System.err.println(erro.getMessage());
            }
		}
	}

	public void carregarDesenho (Desenho desenho) throws Exception
    {
        if(desenho == null)
            throw new Exception("O desenho não pode ser nulo");

        figuras.clear();
        cntForm.repaint();//limpa a tela quando for carregar um desenho da nuvem
        this.desenhoEscolhido = desenho;

        if(desenho.getConteudo() == null)
            JOptionPane.showMessageDialog(getContentPane(),"O desenho não tem figuras portanto não pode ser carrego");
        else
        {
            int qtdFiguras = desenho.getQtd();
            for(int i=0; i<qtdFiguras; i++)
            {
                String figuraLida = desenho.getFigura(i);
                char letra = figuraLida.charAt(0);
                switch (letra)
                {
                    case 'p':
                        figuras.add(new Ponto(figuraLida));
                        break;
                    case 'l':
                        figuras.add(new Linha(figuraLida));
                        break;
                    case 'c':
                        figuras.add(new Circulo(figuraLida));
                        break;
                    case 'e':
                        figuras.add(new Elipse(figuraLida));
                        break;
                    case 'q':
                        figuras.add(new Quadrado(figuraLida));
                        break;
                    case 'r':
                        figuras.add(new Retangulo(figuraLida));
                        break;
                    case 'g':
                        figuras.add(new Poligono(figuraLida));
                        break;
                    case 't':
                        figuras.add(new Texto(figuraLida));
                        break;
                    default:
                        break;
                }
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
            }
            statusBar1.setText("Mensagem: Arquivo aberto com sucesso");
        }

    }

	protected class AbrirArquivo implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{

			JFileChooser abridorDeArquivos = new JFileChooser();
			abridorDeArquivos.setCurrentDirectory(new File(localSalvamento));
			abridorDeArquivos.setFileFilter(new FileNameExtensionFilter("Desenhos","jlp"));
			int verificador = abridorDeArquivos.showOpenDialog(getContentPane());
			if(verificador == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					String localDoArquivo = abridorDeArquivos.getSelectedFile().getPath();
					BufferedReader leitor = new BufferedReader(new FileReader(localDoArquivo));//salvando o arquivo em leito
					String figuraLida;
					while ((figuraLida = leitor.readLine()) != null )
					{
						char letra = figuraLida.charAt(0);
						switch (letra)
						{
							case 'p':
								figuras.add(new Ponto(figuraLida));
								break;
							case 'l':
								figuras.add(new Linha(figuraLida));
								break;
							case 'c':
								figuras.add(new Circulo(figuraLida));
								break;
							case 'e':
								figuras.add(new Elipse(figuraLida));
								break;
							case 'q':
								figuras.add(new Quadrado(figuraLida));
								break;
							case 'r':
								figuras.add(new Retangulo(figuraLida));
								break;
							case 'g':
								figuras.add(new Poligono(figuraLida));
								break;
							case 't':
								figuras.add(new Texto(figuraLida));
								break;
							default:
								break;
						}
						figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenhos.getGraphics());
					}
					statusBar1.setText("Mensagem: Arquivo carregado com sucesso");
				}
				catch(Exception erro)
				{
					System.err.println(erro.getMessage());
				}
			}
			else
			{
				statusBar1.setText("Mensagem: Arquivo não encontrado");
			}
		}
	}

	//apaga o desenho do banco de dados
	public void deletarDesenho (String nome, String ipDono) throws Exception
    {
        if(nome == null)
            throw new Exception("O nome não pode ser nulo");

        if(ipDono == null)
            throw new Exception("O ip do dono não pode ser nulo");

        servidor.receba(new PedidoExclusao(nome, ipDono));
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            try
            {
                servidor.receba(new ComunicadoDeDesligamento());
                servidor.adeus();
            }
            catch (Exception erro)
            {} //se não pode ser fechada é por que ja esta fechada

            System.exit(0);
            
        }
    }
}
