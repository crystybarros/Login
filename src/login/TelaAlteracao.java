package login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static login.Usuario.usuarioSistema;
import java.awt.HeadlessException;


public class TelaAlteracao extends JFrame {
    //Criando meus atributos globais
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;
    private boolean atualizacaoValida;
    
    public TelaAlteracao(){
        
        setLocationRelativeTo(null);
        setResizable (false);
        setTitle ("Alteração - Fatec São Roque");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);
        
        tela = new JPanel();
        tela.setBackground(SystemColor.YELLOW);
        setContentPane(tela);
        tela.setLayout(null);
        
        //Adicionando elementos na tela
        JLabel lblIdentificacao = new JLabel ("Informe campos para alteração");
        lblIdentificacao.setBounds (60, 0, 500, 39);
        lblIdentificacao.setFont(new Font ("Arial", 3, 19));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel ("Nome");
        lblNome.setBounds(24, 35, 100, 15);
        tela.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds (120, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblSenhaAtual = new JLabel ("Senha Atual");
        lblSenhaAtual.setBounds(24, 60, 70, 15);
        tela.add(lblSenhaAtual);
        
        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        tela.add(passAtual);
        
        JLabel lblnovaSenha = new JLabel ("Nova Senha");
        lblnovaSenha.setBounds(24, 85, 70, 15);
        tela.add(lblnovaSenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds (120, 85, 219, 19);
        tela.add(passSenha);
        
        JLabel lblConfSenha = new JLabel ("Confirmar Senha");
        lblConfSenha.setBounds(24, 110, 100,15);
        tela.add(lblConfSenha);
        
        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        tela.add(confPassSenha);
        
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        tela.add(btnAlterar);
        
        JButton btnCancelar = new JButton ("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        tela.add(btnCancelar);

        btnCancelar.addActionListener ((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose ();            
    });
        
        //Botão de alteração
        btnAlterar.addActionListener((ActionEvent e) -> {
            try{
                //instancio a classe usuario
                Usuario usu = new Usuario();
                
                //Validação antes de efetivar a alteração
                //setando a senha e usuario
                usu.setSenha(confPassSenha.getText());
                usu.setUsuario(Usuario.usuarioSistema);
  
                
                //Nome vazio
                if ("".equals(usu.getNome())){
                    //vamos dar uma mensagem na tela
                    JOptionPane.showMessageDialog(null, "Campo nome do usuário percisa ser informado!", "Atenção", JOptionPane.ERROR_MESSAGE);
                   //voltar o cursor para o campo txtNome
                    txtNome.grabFocus();
                    
                    //Senha vazia                    
                }else if("".equals(usu.getSenha())) {
                    //vamos dar uma mensagem na tela
                    JOptionPane.showMessageDialog(null,"Campo senha precisa ser informado!", "Atenção", JOptionPane.ERROR_MESSAGE);
                    
                    //voltar o cursor para o campo PassSenha
                    passSenha.grabFocus();
                    
                }else if(usu.verificaUsuario(usu.getUsuario(), 
                        passAtual.getText()) == false){
                        //vamos dar uma msg na tela
                        JOptionPane.showMessageDialog(null, "Senha inválida, verifique!", "Atenção", JOptionPane.ERROR_MESSAGE);
                        passSenha.grabFocus();
                        
                }else if(!passSenha.getText().equals(confPassSenha.getText())) {
                        JOptionPane.showMessageDialog(null,"Campos de Senha e Confirmação não são iguais!", "Atenção", JOptionPane.ERROR_MESSAGE);
                        passSenha.grabFocus();
                        
                }else {
                        atualizacaoValida = usu.alteraUsuario(txtNome.getText(), usu.getUsuario(), usu.getSenha());
                        
                        if (atualizacaoValida){
                            JOptionPane.showMessageDialog(null, "Dado(s) do usuário alterado(s) retornaremos " + "a tela de login.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                           //abri a tala de login novamente
                            TelaLogin tLogin = new TelaLogin();
                            tLogin.abreTela();
                            //fecha a tela de cadastro
                            dispose();
                            
                        }else{
                            //usuario cadastrado na base de dados
                            JOptionPane.showMessageDialog(null, "Problemas ao atualizar o usuário", "Atenção", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            }catch (HeadlessException ec){
                    System.out.println("Erro ao alterar usuário " + ec.getMessage());    
            }
        });
        
        //atribuir o atributo global ao objeto
        txtNome.setText(Usuario.nomeUsuario);
        }
    
    public void abreTela(){
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
}
