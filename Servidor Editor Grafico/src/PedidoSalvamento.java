import bd.dbos.Desenho;

public class PedidoSalvamento extends Comunicado
{
    static final long serialVersionUID = 6777497023509502104L;
    private Desenho desenho;

    public PedidoSalvamento (Desenho des) throws Exception
    {
        if(des == null)
            throw new Exception("O desenho não pode ser nulo");

        this.desenho = des;
    }

    public Desenho getDesenho ()
    {
        return this.desenho;
    }

    @Override
    public String toString ()
    {
        return "PedidoSalvamento{" + "desenho=" + desenho + '}';
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (!(o instanceof PedidoSalvamento))
            return false;

        PedidoSalvamento ps = (PedidoSalvamento) o;

        if(!this.desenho.equals(ps.desenho))
            return false;

        return true;
    }

}