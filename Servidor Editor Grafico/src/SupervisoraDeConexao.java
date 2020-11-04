import bd.core.MeuResultSet;
import bd.daos.*;
import bd.dbos.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SupervisoraDeConexao extends Thread
{
    private final Socket conexao;
    private final ArrayList<Parceiro> usuarios;
    private Parceiro usuario;

    public SupervisoraDeConexao (Socket conexao, ArrayList<Parceiro> usuarios) throws Exception
    {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (usuarios == null)
            throw new Exception("Usuarios ausentes");

        this.conexao = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {
        ObjectOutputStream transmissor;
        try
        {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }

        ObjectInputStream receptor = null;
        try
        {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {
            } // so tentando fechar antes de acabar a thread

            return;
        }

        try
        {
            this.usuario = new Parceiro(this.conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
        } // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add(this.usuario);
            }

            for (; ; )
            {
                Comunicado comunicado = this.usuario.envie();

                if (comunicado == null)
                    return;

                if(comunicado instanceof ComunicadoDeDesligamento)
                {
                    this.usuario.adeus();

                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove(this.usuario);
                    }
                    break;
                }
                else
                    if (comunicado instanceof PedidoSalvamento)
                    {
                        try
                        {
                            Desenho desenho = ((PedidoSalvamento) comunicado).getDesenho();
                            if(Desenhos.verificarSalvo(desenho.getIpDono(), desenho.getNome()))
                                Desenhos.atualizar(desenho);
                            else
                                Desenhos.incluir(desenho);
                        }
                        catch (Exception erro)
                        {
                            //sei que não vai dar erro
                        }
                    }
                    else
                        if (comunicado instanceof PedidoAbertura)
                        {
                            PedidoAbertura resposta = (PedidoAbertura) comunicado;

                            MeuResultSet desenhos = Desenhos.getDesenhos(resposta.getIdCliente());

                            ArrayList<Desenho> listaDesenhos = new ArrayList<Desenho>();

                            while(desenhos.next() != false)
                            {
                                Desenho desenho = new Desenho(desenhos.getString("nome"),
                                                              desenhos.getString("dataCriacao"),
                                                              desenhos.getString("dataUltimaAtualizacao"),
                                                              desenhos.getString("ipDono"));

                                StringTokenizer conteudo = new StringTokenizer(desenhos.getString("conteudo"), "+");

                                while(conteudo.hasMoreTokens())
                                {
                                    desenho.addFigura(conteudo.nextToken());
                                }

                                listaDesenhos.add(desenho);
                            }
                            PedidoRetorno retorno = new PedidoRetorno(listaDesenhos);
                            this.usuario.receba(retorno);
                        }
                        else
                            if(comunicado instanceof PedidoExclusao)
                            {
                                Desenhos.excluir(((PedidoExclusao) comunicado).getNomeDesenho(), ((PedidoExclusao) comunicado).getIpDono());
                            }
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close();
                receptor.close();
            }
            catch (Exception falha)
            {
            } // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
