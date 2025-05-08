package login;







import java.awt.Font; //trabalhar com fontes
import java.awt.SystemColor; //trabalhar com cores
import java.awt.event.ActionEvent; //trabalhar com evento
import javax.swing.JButton; //trabalhar com botões
import javax.swing.JFrame; //trabalhar com frames
import javax.swing.JLabel; //trabalhar com labels
import javax.swing.JOptionPane; //trabalhar com mensagens
import javax.swing.JPanel; //trabalhar com paineis
import javax.swing.JPasswordField; //trabalhar com campos de senha
import javax.swing.JTextField; //trabalhar com campos de textos
  

public class TelaLogin extends JFrame {
    //tela objeto JPanel (tela em si)
    private final JPanel panelTela;
    
    //txtusuario objeto JTextField (campo na tela)
    private final JTextField txtUsuario;
    
    //pswSenha objeto PasswordField (campo na tela)
    private final JPasswordField pswSenha;
    
    //validar se o usuario é correto
    private boolean usuarioValido;
    
//Método construtor
public TelaLogin(){
        //coloca o objeto na referencia do centro da tela
        setLocationRelativeTo(null);                    
        
        //não permite que o objeto seja expandido
        setResizable(false);                            
        
        //coloca título na caixa JFrame
        setTitle("Login - Fatec São Roque");                              
        
        //Quando clicado no X eu encerro todo o programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //define posionamento e tamanho
        //         x    y  width height
        setBounds(500, 200, 400, 300);
        
        //Crio um objeto JPanel e atribuo ele a variável tela
        panelTela = new JPanel();     
        
        //Define a cor de fundo do JPanel (tela)
        panelTela.setBackground(SystemColor.MAGENTA); 
        setContentPane(panelTela);
        
        //Vou utilizar o meu panel sem utilizar o padrão
        panelTela.setLayout(null);         
               
        //Adicionando elementos na tela:
        // Criando um objeto do tipo JLabel e atribuindo o valor ao atributo         
        JLabel lblIdentificacao = new JLabel("IDENTIFICAÇÃO"); 
        
        //Localização na tela
        lblIdentificacao.setBounds(50, 0, 160, 39);
        
        //Definindo a Fonte
        lblIdentificacao.setFont(new Font("Arial", 3, 20));
        
        //Adicionando o meu label ao meu Panel
        panelTela.add(lblIdentificacao);   
        
        //Identificação e Posicionamento dos labels        
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50, 65, 70, 15);
        panelTela.add(lblUsuario);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(50, 92, 70, 15);
        panelTela.add(lblSenha);
        
        //Identificação e Posicionamento dos texts fields        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);
                   
        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        panelTela.add(pswSenha);
        
        //Identificação e Posicionamento dos botões
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 150, 117, 25);
        panelTela.add(btnEntrar);  
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 150, 117, 25);
        panelTela.add(btnCadastrar);
                
        //ação no botão de entrar no sistema
        btnEntrar.addActionListener ((ActionEvent e) ->{
            //instancio a classe usuario
            Usuario usu = new Usuario();
            
            //utilizo o setter de usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());
            
            if ("".equals(txtUsuario.getText())){
                //vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null, 
                        "Campo usuário precisa ser informado!", 
                        "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                //volta o cursor para o campo txtUsuario
                txtUsuario.grabFocus();                
            }else if("".equals(pswSenha.getText())) {

                //vamos dar um mensagem na tela
                JOptionPane.showMessageDialog(null, 
                        "Campo senha precisa ser informado!", 
                        "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                //voltar o cursor para o campo txtUsuario
                pswSenha.grabFocus();
        }else{
                //verifico se o usuario consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(), 
                        usu.getSenha());
                
                if (usuarioValido == true) {
                //usuario e senha bateram no banco estão corretos
                JOptionPane.showMessageDialog(null, 
                        "Usuário válido em nossa base de dados", 
                        "Atenção", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                TelaInicio telaInicio = new TelaInicio ();
                telaInicio.abreTela();
                dispose();
                
                }else{
                    //usuario e senha bateram no banco estão incorretos
                    JOptionPane.showMessageDialog(null,
                        "Usuário inválido ou inexistente", 
                        "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                    
                //metodo para limpar os textos
                limpaText();
                
                //Mando o foco para o campo usuário
                txtUsuario.grabFocus();
                }   
            }                
        });
    }
          
    public void abreTela(){
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }
    
    public void limpaText(){
        txtUsuario.setText("");
        pswSenha.setText("");
    }   
}