import bd.dbos.Desenho;

import java.util.ArrayList;
import java.util.Objects;

public class PedidoRetorno extends Comunicado
{
    private ArrayList<Desenho> listaDeDesenhos;

    public PedidoRetorno (ArrayList<Desenho> listaDeDesenhos) throws  Exception
    {
        if(listaDeDesenhos == null)
            throw new Exception("A lista de desenhos não pode estar vazia");

        this.listaDeDesenhos = listaDeDesenhos;
    }

    public void setListaDeDesenhos (ArrayList<Desenho> listaDeDesenhos) throws Exception
    {
        if(listaDeDesenhos == null)
            throw new Exception("O vetor não pode estar vazio");

        this.listaDeDesenhos = listaDeDesenhos;
    }

    public ArrayList<Desenho> getListaDeDesenhos ()
    {
        return listaDeDesenhos;
    }

    @Override
    public String toString ()
    {
        return "PedidoRetorno{" + "listaDeDesenhos=" + listaDeDesenhos + '}';
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

        PedidoRetorno pr = (PedidoRetorno) obj;

        if(!this.listaDeDesenhos.equals(pr.listaDeDesenhos))
            return false;

        return true;
    }
}
