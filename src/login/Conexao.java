
package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexao {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_login";
    private final String usuario = "root";
    private final String senha = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    
    public Connection abrirConexao(){
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(servidor, usuario, senha);
            this.stmt = this.con.createStatement();
            System.out.println("Conexão aberta com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao acessar banco de dados, verifique! " + e.getMessage());
        }
        return this.con;
    }   


    public void fecharConexao () {
        try {
            this.con.close();
            System.out.println("Conexão finalizada com sucesso");
        }catch (SQLException e){
            System.out.println("Erro ao encerrar conexão " + e.getMessage());
        }
    }
}
