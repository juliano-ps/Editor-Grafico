package bd.daos;

import bd.*;
import bd.dbos.*;
import java.sql.*;
import bd.core.*;

public class Desenhos
{
    public static boolean verificarSalvo (String ipDono, String nome) throws Exception
    {
        boolean retorno  = false;

        try
        {
            String sql;

            sql = "SELECT * FROM Desenhos " +
                  "WHERE ipDono=? " + "AND " + "nome=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, ipDono);
            BDSQLServer.COMANDO.setString(2, nome);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception("Não foi possivel consultar o desenho");
        }

        return retorno;
    }

    public static void incluir (Desenho desenho) throws Exception
    {
        if(desenho == null)
            throw new Exception("O desenho não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO DESENHOS " + "(nome, dataCriacao, dataUltimaAtualizacao, ipDono, conteudo) " + "VALUES " + "(?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, desenho.getNome());
            BDSQLServer.COMANDO.setString(2, desenho.getDataCriacao());
            BDSQLServer.COMANDO.setString(3, desenho.getDataUltimaAtualizacao());
            BDSQLServer.COMANDO.setString(4, desenho.getIpDono());
            BDSQLServer.COMANDO.setString(5, desenho.getConteudo());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

        }
        catch(SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();

            throw new Exception("Erro ao inserir desenho");
        }
    }

    public static void excluir (String nome, String ipDono) throws Exception
    {
        if(nome == null)
            throw new Exception("Nome não fornecido");

        if(ipDono == null)
            throw new Exception("ip do dono não fornecido");

        try
        {
            String sql;

            sql = "DELETE FROM DESENHOS " + "WHERE ipDono=? and NOME=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, ipDono);
            BDSQLServer.COMANDO.setString(2, nome);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

        }
        catch(SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao excluir desenho");
        }
    }

    public static void atualizar (Desenho desenho) throws Exception
    {
        if(desenho == null)
            throw new Exception("O desenho não fornecido");

        try
        {
            String sql;

            sql = "UPDATE Desenhos " + "SET dataUltimaAtualizacao=? " + ", conteudo=? " + "WHERE NOME=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, desenho.getDataUltimaAtualizacao());
            BDSQLServer.COMANDO.setString(2, desenho.getConteudo());
            BDSQLServer.COMANDO.setString(3, desenho.getNome());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();

        }
        catch(SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao atualizar desenho");
        }
    }

    // metodo para pegar todos os desenhos
    public static MeuResultSet getDesenhos (String ipDono) throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " + "FROM Desenhos " + "WHERE ipDono=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, ipDono);

            resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }
        catch(SQLException erro)
        {
            throw new Exception("Erro ao recuperar desenhos");
        }

        return resultado;
    }


}