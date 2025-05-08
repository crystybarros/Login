
package login;

/* Importação de todas as bibliotecas*/





import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastro  extends JFrame{
    //Declaração dos atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;
    //Validação de usuario e cadastro
    private boolean usuarioValido;
    private boolean cadastroValido;
    
    //string de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;

    /*Metodo construtor da classe*/
    public TelaCadastro(){
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setBounds (500, 200, 426, 230);
        
        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);
        
        //adicionar elementos na tela//
        JLabel lblIdentificacao = new JLabel ("Informar campos para cadastro");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel ("Nome");
        lblNome.setBounds (24, 50, 70, 15);
        tela.add (lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds (120, 50 , 219, 19);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblUsuario = new JLabel ("Usuario");
        lblUsuario.setBounds (24, 75, 70, 15);
        tela.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 75, 219, 19);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JLabel lblsenha = new JLabel ("Senha");
        lblsenha.setBounds(24, 100, 70, 15);
        tela.add(lblsenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds (120, 100, 219, 19);
        tela.add(passSenha);
        
        JLabel lblconfsenha = new JLabel ("Confirmar Senha");
        lblconfsenha.setBounds (24, 125, 100, 15);
        tela.add(lblconfsenha);
        
        passConfSenha = new JPasswordField();
        passConfSenha.setBounds (120, 125, 219, 19);
        tela.add(passConfSenha);
        
        JButton btnCadastrar = new JButton ("Cadastrar");
        btnCadastrar.setBounds (200, 156, 117, 25);
        tela.add(btnCadastrar);
        
        JButton btnCancelar = new JButton ("Cancelar");
        btnCancelar.setBounds (50, 156, 117, 25);
        tela.add (btnCancelar);
        
    
    
    //Ação do botão cadastrar usuario na base de dados
    btnCadastrar.addActionListener ((ActionEvent e) -> {
        try {
            //instancio o objeto Usuario
            Usuario usu = new Usuario();

        //Realizando os setters dos dados de tela
        usu.setNome (txtNome.getText());
        usu.setUsuario (txtUsuario.getText());
        usu.setSenha (passSenha.getText());
        
        //validações de preechimento dos dados
        if("".equals(usu.getUsuario())){
            mensagemJOption = "Campo nome do usuário precida ser informado!";
            mensagemTipo = 0;
        }else if("".equals(usu.getUsuario())){
            mensagemJOption = "Campo usuário precisa ser informado";
        }else if("".equals(usu.getSenha())){
            mensagemJOption = "Campo senha precisa ser informado!";
            mensagemTipo = 0;
        }else if(!usu.getSenha().equals(passConfSenha.getText())){
            mensagemJOption = "Campos senha e confirmação de senha não coincidem!";
            mensagemTipo = 0;
        }else {
            //verifico se somente o usuário consta no banco
            //neste caso, faremos uma sobrecarga de método
            usuarioValido = usu.verificaUsuario(usu.getUsuario());
            
            if (usuarioValido == true){
                //caso exista, não pode ser colocado na base
                mensagemJOption = "Usuário já existente na base de dados.";
                mensagemTipo = 0;
            }else {
                cadastroValido = usu.cadastraUsuario(usu.getNome(), 
                                                    usu.getUsuario(), 
                                                    usu.getSenha());
                
                if (cadastroValido == true){
                    //Usuário cadatrado na base de dados
                    mensagemJOption = "Usuário cadastrado corretamente!";
                    mensagemTipo = 1;
                    
                }else {
                    //Algum erro aconteceu
                    mensagemJOption = "Problemas ao inserir o usuário!";
                    mensagemTipo = 0;
                }
            }
        }
        
        //Mostrar a mensagem referida
        JOptionPane.showMessageDialog(null, 
                mensagemJOption, "Atenção!", mensagemTipo );
        if (mensagemTipo == 1){
            //voltamos para a tela de login
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            
            //fecha a tela de cadastro
                    dispose();
        }        
    } catch (HeadlessException ec){
        System.out.println("Erro no cadastro do usuário " 
                        + ec.getMessage()); 
    } 
});
        
    btnCancelar.addActionListener((ActionEvent e) -> {
    TelaLogin tLogin = new TelaLogin();
    dispose();
    }); 
}
    
    public void abreTela(){
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible (true);
    }         
}


       