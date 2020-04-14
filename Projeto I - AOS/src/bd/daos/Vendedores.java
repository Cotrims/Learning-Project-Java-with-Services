package bd.daos;

import java.sql.*;
import bd.*;
import bd.dbos.*;
import bd.core.MeuResultSet;

/**
 * A classe Vendedores é a camada lógica da classe vendedor.
 * É ela a responssável por realizar os métodos de CRUD no bando
 * de dados, retornando instancias da classe Vendedor.
 * @author Vinícius Martins Cotrim
 * @since 2020
 */
public class Vendedores
{

    /**
     * Verifica a existência de um vendendor no banco de dados, buscando pelo seu id
     * @param id O id do vendedor a ser verificado
     * @return Um boolean indicando a existência do vendendor
     * @throws Exception Caso ocorra erros na consulta
     */
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

    /**
     * Verifica a existência de um vendendor no banco de dados, buscando pelo seu cpf
     * @param cpf O cpf do vendedor a ser verificado
     * @return Um boolean indicando a existência do vendendor
     * @throws Exception Caso ocorra erros na consulta
     */
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

    /**
     * Incluir um novo vendedor no bando de dados.
     * @param vendedor Uma instancia da classe Vendedor
     * @throws Exception Caso o vendedor passado como parâmetro seja nulo
     * ou ocorra algum erro na coxeção com o banco de dados.
     */
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

    /**
     * Exclui um vendedos no bando de dados, atravaz do id passado
     * como parâmetro.
     * @param id
     * @throws Exception Caso nao exista vendedores com o id passado, ou caso algum
     * erro ocorra na conexão com o banco.
     */
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

    /**
     * Altera um vendedor já existente no banco de dados.
     * @param vendedor Um vendedor que contenha as novas informações
     * @throws Exception Caso o vendedor passado seja nulo. Caso o vendedor não exista.
     * Ou caso ocorra algum erro na conexão com o banco de dados.
     */
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

    /**
     * Recupera um vendedor do banco de dados atraves do id passado como parâmetro
     * @param id O id do vendedor a ser buscado
     * @return Uma instância da classe vendedor, com os mesmos valores do vendedor
     * no banco de dados.
     * @throws Exception Caso ocorra algum erro de coneção na busca pelo vendedor.
     */
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

    /**
     * Recupera um vendedor do banco de dados atraves do cpf passado como parâmetro
     * @param cpf O cpf do vendedor a ser buscado
     * @return Uma instância da classe vendedor, com os mesmos valores do vendedor
     * no banco de dados.
     * @throws Exception Caso ocorra algum erro de coneção na busca pelo vendedor.
     */
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

    /**
     * Busca e retorna todos os vendedores presentes no bando de dados
     * @return Um instância da classe MeuResultSet contendo todos os vendedores
     * @throws Exception Caso ocorra erros na busca ou na conexão.
     */
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
