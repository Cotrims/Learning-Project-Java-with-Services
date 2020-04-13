package bd.dbos;

import java.util.Objects;

public class Vendedor implements Cloneable
{
    private int id;
    private int cep;
    private short numero;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String complemento;
    private String produto;


    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception
    {
        if (id < 0) throw new Exception("Id inválido");
        this.id = id;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) throws Exception
    {
        if (cep < 0) throw new Exception("Cep invalido");
        this.cep = cep;
    }

    public short getNumero() {
        return numero;
    }

    public void setNumero(short numero) throws Exception
    {
        if (numero < 0) throw new Exception("Número inválido");
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception
    {
        if (nome.equals("") || nome == null) throw new Exception("Nome inválido");
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception
    {
        if (email.equals("") || email == null) throw new Exception("Email inválido");
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws Exception
    {
        if (telefone.equals("") || telefone == null) throw new Exception("Telefone inválido");
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws Exception
    {
        if (cpf.equals("") || cpf == null) throw new Exception("CPF inválido");
        this.cpf = cpf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) throws Exception
    {
        if (produto.equals("") || produto == null) throw new Exception("Produto inválido");
        this.produto = produto;
    }

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

        return ret;
    }

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
