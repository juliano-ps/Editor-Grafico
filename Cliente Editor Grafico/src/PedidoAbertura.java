import bd.dbos.Desenho;

import java.io.Serializable;
import java.util.ArrayList;

public class PedidoAbertura extends Comunicado
{
    private String idCliente; // ip do usuario

    public PedidoAbertura (String idCliente) throws Exception
    {
        if(idCliente == null)
            throw new Exception("IP não pode ser nulo");

        this.idCliente = idCliente;
    }

    public void setIdCliente (String idCliente) throws Exception
    {
        if(idCliente == null)
            throw new Exception("ID não pode ser nulo");

        this.idCliente = idCliente;
    }

    public String getIdCliente ()
    {
        return idCliente;
    }

    @Override
    public String toString ()
    {
        return "PedidoAbertura{" + "idCliente=" + idCliente + '}';
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof PedidoAbertura))
            return false;

        PedidoAbertura that = (PedidoAbertura) o;

        return idCliente.equals(that.idCliente);
    }
}
