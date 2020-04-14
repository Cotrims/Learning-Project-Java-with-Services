package bd.dbos;

import java.util.Objects;

/**
 * A classe Vendedor representa uma instância de vendedor
 * com todos os campos correspondentes a taleba vendedor no
 * banco de dados.
 * @author Vinicius Martins Cotrim.
 * @since 2020
 */
public class Vendedor implements Cloneable
{
    /**
     * A identificação unica de cada vendedor
     */
    private int id;

    /**
     * O cep que é a chave para recuperação do endereço completo
     */
    private int cep;

    /**
     * O número da casa ou estabelecimento do vendedor
     */
    private short numero;

    /**
     * O nome do vendedor
     */
    private String nome;

    /**
     * O Email do vendedor
     */
    private String email;

    /**
     * O número de telefone do vendedor
     */
    private String telefone;

    /**
     * O cpf do vendedor
     */
    private String cpf;

    /**
     * O complemento do endereço, caso seja um condomínio,
     * guarda o bloco e o apartamento, por exemplo.
     */
    private String complemento;

    /**
     * O que o vendedor vende
     */
    private String produto;

    /**
     * Recupera o valor do id que this possui
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Seta um valor para o id em this
     * @param id o valor a ser colocado
     * @throws Exception Caso o valor seja menor que zero
     */
    public void setId(int id) throws Exception
    {
        if (id < 0) throw new Exception("Id inválido");
        this.id = id;
    }

    /**
     * Recupera o valor do cep que this possui
     * @return cep
     */
    public int getCep() {
        return cep;
    }

    /**
     * Seta em this o valor do cep
     * @param cep O valor a ser setado
     * @throws Exception Caso o valor seja inválido, menor que zero
     */
    public void setCep(int cep) throws Exception
    {
        if (cep < 0) throw new Exception("Cep invalido");
        this.cep = cep;
    }

    /**
     * Recupera o valor do numero que this possui
     * @return numero
     */
    public short getNumero() {
        return numero;
    }

    /**
     * Seta em this o valor do numero
     * @param numero O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setNumero(short numero) throws Exception
    {
        if (numero < 0) throw new Exception("Número inválido");
        this.numero = numero;
    }

    /**
     * Recupera o valor do nome que this possui
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Seta em this o valor do nome
     * @param nome O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setNome(String nome) throws Exception
    {
        if (nome.equals("") || nome == null) throw new Exception("Nome inválido");
        this.nome = nome;
    }

    /**
     * Recupera o valor do email que this possui
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Seta em this o valor do email
     * @param email O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setEmail(String email) throws Exception
    {
        if (email.equals("") || email == null) throw new Exception("Email inválido");
        this.email = email;
    }

    /**
     * Recupera o valor do telefone que this possui
     * @return telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Seta em this o valor do telefone
     * @param telefone O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setTelefone(String telefone) throws Exception
    {
        if (telefone.equals("") || telefone == null) throw new Exception("Telefone inválido");
        this.telefone = telefone;
    }

    /**
     * Recupera o valor do cpf que this possui
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Seta em this o valor do cpf
     * @param cpf O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setCpf(String cpf) throws Exception
    {
        if (cpf.equals("") || cpf == null) throw new Exception("CPF inválido");
        this.cpf = cpf;
    }

    /**
     * Recupera o valor do complemento que this possui
     * @return complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Seta em this o valor do complemento
     * @param complemento O valor a ser setado
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Recupera o valor do produto que this possui
     * @return produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * Seta em this o valor do produto
     * @param produto O valor a ser setado
     * @throws Exception Caso o valor seja inválido, nulo ou vazio
     */
    public void setProduto(String produto) throws Exception
    {
        if (produto.equals("") || produto == null) throw new Exception("Produto inválido");
        this.produto = produto;
    }

    /**
     * Construtor da classe, seta todos os valores passados na instancia atual
     * @param id O id do vendedor
     * @param cep O cep do vendedor
     * @param numero O númeor do vendedor
     * @param nome O Nome do vendedor
     * @param email O email do vendedor
     * @param telefone O telefone do vendedor
     * @param cpf O cpf do vendedor
     * @param complemento O complemento do endereço do vendedor
     * @param produto O produto vendido pelo vendedor
     * @throws Exception Lançadas pelos setters
     */
    public Vendedor(int    id  , int  cep        , short  numero  ,
                      String nome, String email      , String telefone,
                      String cpf, String complemento , String produto) throws Exception {
        this.setId(id);
        this.setCep(cep);
        this.setNumero(numero);
        this.setNome(nome);
        this.setEmail(email);
        this.setTelefone(telefone);
        this.setCpf(cpf);
        this.setComplemento(complemento);
        this.setProduto(produto);
    }

    /**
     * Gera uma representação visual de todos os valores dos
     * atributos contidos em this.
     * @return Uma String representando visualmente a classe Vendedor
     */
    public String toString() {
        String ret = "";

        ret = "Vendedor{" +
                "id=" + id +
                ", cep=" + cep +
                ", numero=" + numero +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", complemento='" + complemento + '\'' +
                ", produto=" +produto+
                '}';

        return ret;
    }

    /**
     * Compara a instancia de dois objetos da classe Vendedor,
     * verificando sua iguialdade, entre suas instâncias, atributos etc.
     * @param obj O objeto a ser comparado
     * @return true de são iguais, false se são diferentes
     */
    public boolean equals(Object obj)
    {
        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Vendedor)) return false;

        Vendedor forn = (Vendedor) obj;

        if(getId() != forn.getId()) return false;

        if (getCep() != forn.getCep()) return false;

        if (getNumero() != forn.getNumero()) return false;

        if (getProduto() != forn.getProduto()) return false;

        if (!getNome().equals(forn.getNome())) return false;

        if (!getEmail().equals(forn.getEmail())) return false;

        if (!getTelefone().equals(forn.getTelefone())) return false;

        if (!getCpf().equals(forn.getCpf())) return false;

        if (!getComplemento().equals(forn.getComplemento())) return false;

        return true;
    }

    /**
     * Calcula o valor do hashcode que this possui
     * armazenando todos os valores dos hash's de seus
     * atributos
     * @return O valor do hashcode total
     */
    public int hashCode()
    {
        int ret = 0;

        ret =  Objects.hash(getId(),
                            getCep(),
                            getNumero(),
                            getNome(),
                            getEmail(),
                            getTelefone(),
                            getCpf(),
                            getComplemento(),
                            getProduto());

        if(ret<0) ret=-ret;

        return ret;
    }

    /**
     * Retorna um instancia de Object que representa a copia
     * de this, com todos os seus atributos
     * @return Um copia exata de this
     */
    public Object clone()
    {
        Vendedor ret = null;

        try
        {
            ret = new Vendedor(this);
        }
        catch (Exception err){}

        return ret;
    }

    /**
     * Um construitor de copia usado no método de clone
     * @param modelo O vendedor a ser copiado
     * @throws Exception O modelo passado seja nulo
     */

    public Vendedor (Vendedor modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo para copia vazio");

        this.id = modelo.id;
        this.cep = modelo.cep;
        this.cpf = modelo.cpf;
        this.complemento = modelo.complemento;
        this.email = modelo.email;
        this.nome = modelo.nome;
        this.numero = modelo.numero;
        this.produto = modelo.produto;
        this.telefone = modelo.telefone;
    }
}
