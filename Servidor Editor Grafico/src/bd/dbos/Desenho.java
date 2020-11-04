package bd.dbos;

import java.io.Serializable;
import java.util.ArrayList;

public class Desenho implements Serializable, Cloneable
{
    private String nome;
    private String dataCriacao;
    private String dataUltimaAtualizacao;
    private ArrayList<String> conteudo;
    private String ipDono;

    public Desenho (String nom, String dtCri, String dtUltAtu, String ipDono) throws Exception
    {
        if(nom == null)
            throw new Exception("Nome não informado");

        if(dtCri == null)
            throw new Exception("Data de criação não informada");

        if(dtUltAtu == null)
            throw new Exception("Data de atualização não informada");

        if(ipDono == null)
            throw new Exception("Ip do dono não informado");


        this.nome = nom;
        this.dataCriacao = dtCri;
        this.dataUltimaAtualizacao = dtUltAtu;
        this.conteudo = new ArrayList<String>();
        this.ipDono = ipDono;

    }
    // fig vai ter como forma o retorno do metodo toString de alguma das classes
    // herdadas da classe Figura por exemplo, r:11:22:33:44:55:66:77, para uma
    // linha que vai do ponto com coordenada 11,22 ao ponto com coordenada 33,44
    // e com cor 55:66:77 (55 de red, 66 de green e 77 de blue).
    public void addFigura(String fig)
    {
        this.conteudo.add(fig);
    }

    public void limparVetorDeFiguras ()
    {
        this.conteudo.clear();
    }

    public int getQtd ()
    {
        return this.conteudo.size();
    }

    public String getFigura (int i)
    {
        return this.conteudo.get(i);
    }

    public String getDataCriacao ()
    {
        return this.dataCriacao;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getConteudo ()
    {
        String retorno = "";

        for(String elemento: this.conteudo)
        {
            retorno += elemento + "+";
        }

        return retorno;
    }

    public void setNome (String nome) throws Exception
    {
        if(nome == null)
            throw new Exception("O nome não pode ser nulo");

        this.nome = nome;
    }

    public String getIpDono ()
    {
        return this.ipDono;
    }

    public String getDataUltimaAtualizacao ()
    {
        return this.dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao (String dataUltimaAtualizacao) throws Exception
    {
        if(dataUltimaAtualizacao == null)
            throw new Exception("A data não pode ser nula");

        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    @Override
    public String toString ()
    {
        String vetor = "";
        for(String elemento: this.conteudo)
        {
            vetor += elemento + "\n";
        }
        return   "O nome é: " + this.nome + ", a data de criação é: " + this.dataCriacao + ", a data do ultimo salvamento é: " + this.dataUltimaAtualizacao + ", o ip do dono é: " + this.ipDono
                + ", os valores do desenho são: \n" + vetor;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Desenho d = (Desenho) obj;

        if(!this.nome.equals(d.nome) || !this.dataCriacao.equals(d.dataCriacao) || !this.dataUltimaAtualizacao.equals(d.dataUltimaAtualizacao) ||
           !this.conteudo.equals(d.conteudo) || !this.ipDono.equals(d.ipDono))
            return false;

        return true;
    }
}