import bd.dbos.Desenho;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaDeDesenhos extends JFrame
{
    protected static final long serialVersionUID = 1L;
    protected Desenho desenhoEscolhido = null;
    protected Janela janela;
    protected DefaultListModel<String> listModel;

    protected Container cntForm;
    protected JButton btnAbrir   = new JButton("Abrir"),
                      btnDeletar = new JButton("Deletar");
    protected JPanel botoes;


    private JList<String> listaDeDesenhos;
    private ArrayList<Desenho> desenhos;


    public JanelaDeDesenhos (ArrayList<Desenho> desenhos, Janela janela) throws Exception
    {
        super("Escolha de Desenhos");
		
        if(desenhos == null)
            throw new Exception("O vetor esta nulo");

        this.desenhos = desenhos;
        
        this.cntForm = this.getContentPane();

        this.janela = janela;

		String[] vetorParaMostrar = new String[this.desenhos.size()];
		
		for (int i = 0; i < this.desenhos.size(); i++)
		{
		    Desenho desenho = desenhos.get(i);
			vetorParaMostrar[i] = "Nome: " + desenho.getNome() + "             Data Criacao: " + desenho.getDataCriacao() + "              Data da ultima atualizacao: " + desenho.getDataUltimaAtualizacao();
		}

		this.listModel = new DefaultListModel<String>();
		for(int i=0 ; i<vetorParaMostrar.length ; i++)
        {
            String x = vetorParaMostrar[i];
            this.listModel.addElement(x);
        }

		this.listaDeDesenhos = new JList<String>();
		this.listaDeDesenhos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.listaDeDesenhos.setModel(this.listModel);

        this.botoes = new JPanel();
        FlowLayout flwbotao = new FlowLayout();
        botoes.setLayout(flwbotao);

        botoes.add(this.btnAbrir);
        botoes.add(this.btnDeletar);

        cntForm.setLayout(new BorderLayout());
        cntForm.add(listaDeDesenhos,  BorderLayout.CENTER);
        cntForm.add(botoes, BorderLayout.SOUTH);

       btnAbrir.addActionListener(new AbrirDesenho());
       btnDeletar.addActionListener(new DeletarDesenho());
        
        this.setSize(700,500);
        this.setVisible(true);
    }

    protected class AbrirDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            try
            {
                janela.carregarDesenho(desenhos.get(listaDeDesenhos.getSelectedIndex())); // pegamos qual desenho é o certo a partir do index que é equivalente a posição no vetor
            }
            catch (Exception erro)
            {
                JOptionPane.showMessageDialog(getContentPane(), "Desenho invalido");
            }

            JanelaDeDesenhos.this.dispose();
        }
    }

    protected class DeletarDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            int index = listaDeDesenhos.getSelectedIndex();
            Desenho desenho = desenhos.get(index);
            desenhos.remove(index);
            listModel.remove(index);

            try
            {
                janela.deletarDesenho(desenho.getNome(), desenho.getIpDono());
            }
            catch(Exception erro)
            {
                //sei que passei os parametros certos
            }

            JOptionPane.showMessageDialog(getContentPane(),"Desenho deletado com sucesso");
        }
    }

}








