import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.net.*;
public class Editor
{
	public static final String HOST_PADRAO = "localhost";
	public static final int PORTA_PADRAO = 12345;

    public static void main (String args[])
    {
    	if (args.length > 2)
		{
			System.err.println("Uso esperado: java Cliente: [HOST [PORTA]]\n");
			return;
		}

		Socket conexao = null;
		ObjectOutputStream transmissor = null;
		ObjectInputStream receptor = null;
		Parceiro servidor = null;
    	try
		{
			String host = Editor.HOST_PADRAO;
			int porta = Editor.PORTA_PADRAO;

			if(args.length > 0)
				host = args[0];

			if(args.length == 2)
				porta = Integer.parseInt(args[1]);

			conexao = new Socket(host, porta);
			transmissor = new ObjectOutputStream(conexao.getOutputStream());
			receptor = new ObjectInputStream(conexao.getInputStream());
			servidor = new Parceiro(conexao, receptor, transmissor);

		}
    	catch (Exception erro)
		{
			System.err.println ("Indique o servidor e a porta");
			return;
		}

    	try
		{
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
       // handle exception
		}
		catch (InstantiationException e) {
       // handle exception
		}
		catch (IllegalAccessException e) {
       // handle exception
		}
		try
		{
			new Janela (servidor);
		}
		catch (Exception erro)
		{
			System.err.println(erro.getMessage());
		}

    }
}

