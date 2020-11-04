import java.util.Objects;

public class PedidoExclusao extends Comunicado
{
    String nomeDesenho;
    String ipDono;

    public PedidoExclusao (String nomeDesenho, String ipDono) throws Exception
    {
        if(nomeDesenho == null)
            throw new Exception("O nome do desenho não pode ser nulo");

        if(ipDono == null)
            throw new Exception("O ip dp dono não pode ser nulo");

        this.nomeDesenho = nomeDesenho;
        this.ipDono = ipDono;
    }

    public void setNomeDesenho (String nomeDesenho) throws Exception
    {
        if(nomeDesenho == null)
            throw new Exception("O nome do desenho não pode ser nulo");

        this.nomeDesenho = nomeDesenho;
    }

    public void setIpDono (String ipDono) throws Exception
    {
        if(ipDono == null)
            throw new Exception("O ip dp dono não pode ser nulo");

        this.ipDono = ipDono;
    }

    public String getNomeDesenho ()
    {
        return this.nomeDesenho;
    }

    public String getIpDono ()
    {
        return this.ipDono;
    }

    @Override
    public String toString ()
    {
        return "PedidoExclusao{" + "nomeDesenho='" + nomeDesenho + '\'' + ", ipDono='" + ipDono + '\'' + '}';
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof PedidoExclusao))
            return false;

        PedidoExclusao that = (PedidoExclusao) o;

        if(!this.nomeDesenho.equals(that.nomeDesenho) || !this.ipDono.equals(that.ipDono))
            return false;

        return true;
    }

}
