package view;

import bd.daos.Vendedores;
import bd.dbos.Vendedor;
import services.ClienteWS;
import services.Logradouro;
import java.awt.event.InputMethodListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class Janela {
    private JPanel panel1;
    private JButton btnConsultar;
    private JButton btnEditar;
    private JButton btnAdicionar;
    private JButton btnExcluir;
    private JLabel lbTitulo;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JComboBox cbStatus;
    private JTextField txtEmail;
    private JTextField txtCpf;
    private JTextField txtCep;
    private JTextField txtRua;
    private JLabel lbRua;
    private JTextField txtEstado;
    private JTextField txtCidade;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JLabel lbEstado;
    private JLabel lbBairro;
    private JLabel lnNumero;
    private JLabel lbCidade;
    private JLabel lbProduto;
    private JLabel lbCpf;
    private JTextField txtProduto;
    Logradouro logradouro = null;

    public Janela() {

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!txtId.isEditable() || txtId.getText().equals(""))
                {
                    tornarNaoEditaable(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero, txtComplemento);
                    btnAdicionar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                    btnEditar.setEnabled(false);
                    txtId.setEditable(true);
                    txtId.setText("");
                    txtId.grabFocus();
                }
                else
                {
                    int id = new Integer(txtId.getText());

                    Vendedor vend = null;

                    try {
                        vend = Vendedores.getVendedor(id);

                        txtNome.setText(vend.getNome());
                        txtEmail.setText(vend.getEmail());
                        txtTelefone.setText(vend.getTelefone());
                        txtCpf.setText(vend.getCpf());
                        txtProduto.setText(vend.getProduto());
                        txtComplemento.setText(vend.getComplemento());
                        txtNumero.setText(vend.getNumero() + "");

                        if((vend.getCep()+"").length() != 8)
                            for(int i = (vend.getCep()+"").length(); i <= 8; i++)
                                txtCep.setText("0" + vend.getCep());
                        else txtCep.setText(vend.getCep() + "");

                        txtCep.setEditable(true);
                        txtCep.setEditable(false);
                        btnEditar.setEnabled(true);
                        btnAdicionar.setEnabled(true);
                        btnExcluir.setEnabled(true);
                        txtId.setEditable(false);
                    }
                    catch (Exception err){
                        JOptionPane.showMessageDialog(null,err.getMessage());
                        txtId.setEditable(false);
                        txtId.setText("");
                    }
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtId.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Consulte ou adicione antes de aditar!");
                else
                {
                    if(txtNome.isEditable())
                    {
                        try {
                            Vendedor editado = new Vendedor(new Integer(txtId.getText()),
                                    new Integer(txtCep.getText()), new Short(txtNumero.getText()),
                                    txtNome.getText(), txtEmail.getText(),
                                    txtTelefone.getText(), txtCpf.getText(),
                                    txtComplemento.getText(), txtProduto.getText());

                            Vendedores.alterar(editado);

                            JOptionPane.showMessageDialog(null, "Vendedor editado com sucesso");
                        }catch (Exception err){
                            JOptionPane.showMessageDialog(null, err.toString());
                        }

                        tornarNaoEditaable(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero, txtComplemento);
                        btnConsultar.setEnabled(true);
                        btnAdicionar.setEnabled(true);
                        btnExcluir.setEnabled(true);
                    }
                    else
                    {
                        tornarEditable(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero, txtComplemento);
                        btnConsultar.setEnabled(false);
                        btnAdicionar.setEnabled(false);
                        btnExcluir.setEnabled(false);
                    }
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtId.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Consulte um vendedor antes de excluir");
                else
                {
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?","Exclusão", JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION)
                    {
                        int id = new Integer(txtId.getText());

                        try
                        {
                            Vendedores.excluir(id);
                            JOptionPane.showMessageDialog(null, "Vendedor exluido!");
                            limparCampos();
                        }
                        catch (Exception err){
                            JOptionPane.showMessageDialog(null, err.toString());
                        }
                    }
                }
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnConsultar.isEnabled())
                {
                    limparCampos();
                    btnConsultar.setEnabled(false);
                    btnEditar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                    tornarEditable(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero, txtComplemento);
                }
                else{
                    btnConsultar.setEnabled(false);
                    btnEditar.setEnabled(false);
                    btnExcluir.setEnabled(false);

                    try {
                        hasCamposVazios(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero);

                        Vendedor novo = new Vendedor(1,
                                new Integer(txtCep.getText()), new Short(txtNumero.getText()),
                                txtNome.getText(), txtEmail.getText(),
                                txtTelefone.getText(), txtCpf.getText(),
                                txtComplemento.getText(), txtProduto.getText());

                        Vendedores.incluir(novo);
                        JOptionPane.showMessageDialog(null, "Vendedor incluido com sucesso");

                        txtId.setText(Vendedores.getVendedor(novo.getCpf()).getId() + "");

                        tornarNaoEditaable(txtNome, txtEmail, txtTelefone, txtCpf, txtProduto, txtCep, txtNumero, txtComplemento);

                        btnConsultar.setEnabled(true);
                        btnEditar.setEnabled(true);
                        btnExcluir.setEnabled(true);
                    }
                    catch (Exception err){
                        JOptionPane.showMessageDialog(null, err.toString());
                    }
                }
            }
        });

        txtCep.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                    if (!txtCep.equals("") && txtCep.getText().length() == 8)
                    {
                        logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", txtCep.getText());
                        txtRua.setText(logradouro.getLogradouro());
                        txtBairro.setText(logradouro.getBairro());
                        txtCidade.setText(logradouro.getCidade());
                        txtEstado.setText(logradouro.getEstado());
                    }
                }
        });

        txtCep.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (!txtCep.equals(""))
                {
                    logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "http://api.postmon.com.br/v1/cep", txtCep.getText());

                    if (logradouro == null)
                    {
                        JOptionPane.showMessageDialog(null, "CEP invalido");
                        txtCep.grabFocus();
                    }
                    else
                    {
                        txtRua.setText(logradouro.getLogradouro());
                        txtBairro.setText(logradouro.getBairro());
                        txtCidade.setText(logradouro.getCidade());
                        txtEstado.setText(logradouro.getEstado());
                    }
                }
            }
        });

        txtCpf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Vendedor ven;
                boolean existe;

                try
                {
                    existe = Vendedores.cadastrado(txtCpf.getText());

                    if(existe)
                    {
                        ven = Vendedores.getVendedor(txtCpf.getText());

                        if (ven.getId() != new Integer(txtId.getText()))
                        {
                            txtCpf.grabFocus();
                            throw new Exception("Ja existe um vendedor com o cpf digitado");
                        }
                    }
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null, err.toString());
                }
            }
        });
    }

    private void hasCamposVazios(JTextField... comp) throws Exception
    {
        for (int i = 0; i < comp.length; i++)
            if(comp[i].getText().equals(""))
                throw new Exception("Por favor, digite o " + comp[i].getName());
    }

    private void limparCampos()
    {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCpf.setText("");
        txtProduto.setText("");
        txtCep.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtEstado.setText("");
        txtRua.setText("");
        txtCidade.setText("");
    }

    private void tornarEditable(JTextField... comp)
    {
        for (int i = 0; i < comp.length; i++)
            comp[i].setEditable(true);
    }

    private void tornarNaoEditaable(JTextField... comp)
    {
        for (int i = 0; i < comp.length; i++)
            comp[i].setEditable(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Projeto - Arquitetura Orientada a Servicos");
        frame.setContentPane(new Janela().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
