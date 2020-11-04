package bd;

import bd.core.MeuPreparedStatement;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://GAMA\\SQLEXPRESS;databasename=ProjetoEditorGrafico",
            "juliano2", "minhasenha");
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}