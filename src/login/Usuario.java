
package login;

import java.sql.SQLException; //Tratar as exceções no banco de dados




public class Usuario {
    //criação dos atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;
    
    static String nomeUsuario;
    static String usuarioSistema;
    static boolean resultAlteracao; 
    static boolean resultExclusao;
        
    //atributo que armazenará o retorno do banco de dados
    private boolean resultUsuario; 
    private boolean resultCadastro;
     
    
    //criação dos getters e setters
    public String getUsuario(){
        return usuario;
    }
    
   public void setUsuario (String usuario){
       this.usuario = usuario;
   }
   
   public String getNome(){
       return nome;
   }
   
   public void setNome (String nome){
       this.nome = nome;
   }
   
   public String getSenha(){
       return senha;
   }
   
   public void setSenha (String senha){
       this.senha = senha;
   }
   
   //metodo de verificação da autenticiodade do usuário
   public boolean verificaUsuario(String usuario, String senha){
       //fazer a instancia da conexão com o banco de dados
       Conexao banco = new Conexao();
       
       try{
           //abro a conxão com o banco de dados
           banco.abrirConexao();
           
           //criando parametros de retorno
           banco.stmt = banco.con.createStatement();
           
           //executando a consulta no banco de dados
           banco.resultset = 
                   banco.stmt.executeQuery("SELECT * FROM usuario "
                           + "WHERE usuario = '" + usuario + "'" 
                           + "AND senha = md5('" + senha + "')");
           
           //verificando se existe retorno de dados no namco
           if (banco.resultset.next()){
               //caso tenha
               resultUsuario = true;
               
               //setters em Nome e Usuario
               setUsuario (banco.resultset.getString(1));
               setNome (banco.resultset.getString(2));
               
               //Nos atributis estasticos, realizo as atrinuições
               nomeUsuario = getNome();
               usuarioSistema = getUsuario();
               
           }else {
               //caso não tenha
               resultUsuario = false;
           }
           
           banco.fecharConexao(); //fecha nossa conexão com o banco de dados
                              
       }catch (SQLException ec){
           System.out.println("Erro ao consultar usuario " + ec.getMessage());
       }
       
       return resultUsuario;
   }
   
   
   //Metodo de verificação do usuário, estamos qui fazendo uma sobrecarga de metodo
   public boolean verificaUsuario (String usuario){
       //fazer a instancia da conexão com o banco de dados
       Conexao banco = new Conexao();
       
       try{
           //abro a conexão com o banco de dados
           banco.abrirConexao();
           
           //criando parâmetro de retorno
           banco.stmt = banco.con.createStatement(); // <- adicionei essa
           
           //Executando a consulta no banco de dados
           banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario "
                                                    + "WHERE usuario = '" + usuario +"'");
           
           //verificando se existe retorno de dados no banco
           if (banco.resultset.next()){
               //caso tenha
               resultUsuario = true;
           }else {
               //caso não tenha
               resultUsuario = false;
           }
           
           banco.fecharConexao(); // fecha nossa conexão com o banco de dados
       
       }catch (SQLException ec){
           System.out.println("Erro ao consultar usuario " + ec.getMessage());
       }
       return resultUsuario;
   }
           
           
           
   
    //metodo para cadastro de usuário em nosso sistema
    public boolean cadastraUsuario (String nome, String usuario, String senha){
        //Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();
        
        try{
            //Abertura da conexão com o banco de dados
            banco.abrirConexao();
            
            //Criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a inserção dos dados
            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)" + "VALUES ('" + nome + "','" + usuario + "', md5('" + senha + "'))");
            
        resultCadastro = true;
                
        }catch(SQLException ec){
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        }
        return resultCadastro;
}    
    
    //metodo para alteração dos dados em nosso sistema
    public boolean alteraUsuario (String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        
        try{
            //abre a conexão com o banco de dados
            banco.abrirConexao();
            
            //Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executandoa alteração no banco de dados
            banco.stmt.execute ("UPDATE usuario SET nome = '" + nome +
            				    "', senha = md5 '" + senha + "' WHERE usuario = '" + usuario + "'");
        }catch (SQLException ec){
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }
        banco.fecharConexao();
        
        return resultAlteracao;
    }
    
        //metodo para exclusão dos dados em nosso sistema
    public boolean excluiUsuario (String usuario) {
        //fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();
        
        try{
            //abro a conexão com o banco de dados
            banco.abrirConexao();
            
            //Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executandoa alteração no banco de dados
            banco.stmt.execute ("DELETE FROM usuario WHERE usuario = '" + usuario +"'");
            
            //caso exclua
            resultExclusao = true;
            
        }catch (SQLException ec){
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultAlteracao = false;
        }
        banco.fecharConexao();
        
        return resultExclusao;
    }
}
