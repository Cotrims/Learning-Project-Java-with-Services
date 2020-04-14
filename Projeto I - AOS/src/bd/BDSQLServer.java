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
            "jdbc:sqlserver://Regulus.cotuca.unicamp.br:1433;databasename=BD19040",
            "BD19040", "Cotrimpd19");
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