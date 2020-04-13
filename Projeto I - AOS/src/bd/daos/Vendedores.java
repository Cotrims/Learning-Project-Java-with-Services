package bd.daos;

import java.sql.*;
import bd.*;
import bd.dbos.*;
import bd.core.MeuResultSet;

public class Vendedores
{

    public static boolean cadastrado (int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM VENDEDORES " +
                    "WHERE id = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar vendedor");
        }

        return retorno;
    }

    public static boolean cadastrado (String cpf) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM VENDEDORES " +
                    "WHERE CPF = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, cpf);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar vendedor");
        }

        return retorno;
    }

    public static void incluir (Vendedor vendedor) throws Exception
    {
        if (vendedor==null)
            throw new Exception ("Vendedor nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO VENDEDORES " +
                    "(NOME,EMAIL,TELEFONE,CPF,PRODUTO,CEP,NUMERO,COMPLEMENTO) " +
                    "VALUES " +
                    "(?,?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, vendedor.getNome());
            BDSQLServer.COMANDO.setString (2, vendedor.getEmail());
            BDSQLServer.COMANDO.setString (3, vendedor.getTelefone());
            BDSQLServer.COMANDO.setString (4, vendedor.getCpf());
            BDSQLServer.COMANDO.setString (5, vendedor.getProduto());
            BDSQLServer.COMANDO.setInt  (6, vendedor.getCep());
            BDSQLServer.COMANDO.setShort  (7, vendedor.getNumero());
            BDSQLServer.COMANDO.setString (8, vendedor.getComplemento());


            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            //bd.BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao inserir vendedor");
        }
    }

    public static void excluir (int id) throws Exception
    {
        if (!cadastrado(id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM VENDEDORES " +
                    "WHERE ID=?";

            BDSQLServer.COMANDO.prepareStatement (sql);


            BDSQLServer.COMANDO.setInt (1, id);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir vendedor");
        }
    }

    public static void alterar (Vendedor vendedor) throws Exception
    {
        if (vendedor==null)
            throw new Exception ("Vendedos nao fornecido");

        if (!cadastrado(vendedor.getId()))
            throw new Exception ("Nao cadastrado" + vendedor.getId());

        try
        {
            String sql;

            sql = "UPDATE VENDEDORES " +
                    "SET NOME=?, " +
                    "EMAIL=?, " +
                    "TELEFONE=?, " +
                    "CPF=?, " +
                    "PRODUTO=?, " +
                    "CEP=?, " +
                    "NUMERO=?," +
                    "COMPLEMENTO=? " +
                    "WHERE ID = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, vendedor.getNome());
            BDSQLServer.COMANDO.setString (2, vendedor.getEmail());
            BDSQLServer.COMANDO.setString (3, vendedor.getTelefone());
            BDSQLServer.COMANDO.setString (4, vendedor.getCpf());
            BDSQLServer.COMANDO.setString (5, vendedor.getProduto());
            BDSQLServer.COMANDO.setInt  (6, vendedor.getCep());
            BDSQLServer.COMANDO.setShort  (7, vendedor.getNumero());
            BDSQLServer.COMANDO.setString (8, vendedor.getComplemento());
            BDSQLServer.COMANDO.setInt    (9, vendedor.getId());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do vendedor");
        }
    }

    public static Vendedor getVendedor (int id) throws Exception
    {
        Vendedor vendedor = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM VENDEDORES " +
                    "WHERE ID = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            vendedor = new Vendedor(resultado.getInt("ID"),
                                        resultado.getInt("CEP"),
                                        resultado.getShort("NUMERO"),
                                        resultado.getString("NOME"),
                                        resultado.getString("EMAIL"),
                                        resultado.getString("TELEFONE"),
                                        resultado.getString("CPF"),
                                        resultado.getString("COMPLEMENTO"),
                                        resultado.getString("PRODUTO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar vendedor");
        }

        return vendedor;
    }

    public static Vendedor getVendedor (String cpf) throws Exception
    {
        Vendedor vendedor = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM VENDEDORES " +
                    "WHERE CPF = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cpf);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            vendedor = new Vendedor(resultado.getInt("ID"),
                    resultado.getInt("CEP"),
                    resultado.getShort("NUMERO"),
                    resultado.getString("NOME"),
                    resultado.getString("EMAIL"),
                    resultado.getString("TELEFONE"),
                    resultado.getString("CPF"),
                    resultado.getString("COMPLEMENTO"),
                    resultado.getString("PRODUTO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar vendedor");
        }

        return vendedor;
    }

    public static MeuResultSet getVendedores () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM VENDEDORES";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar vendedor");
        }

        return resultado;
    }
}
