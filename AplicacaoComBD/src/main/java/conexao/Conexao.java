
package conexao;

import javax.swing.*;
import java.sql.*;


public class Conexao {

    public Statement stmt;
    public ResultSet resultSet;
    private Connection conexao;

    public static Connection conectar(){
        
        Connection conexao = null;
        String url = "jdbc:mysql://localhost:3306/bancousuarios?characterEncoding=utf-8";
        String user = "root";
        String senha = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, senha);
            JOptionPane.showMessageDialog(null, "Conexão estabelecida" +conexao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            return conexao;
        }catch (ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null, "Driver Não localizado: "+Driver,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException Fonte){
            JOptionPane.showMessageDialog(null, "Fonte de dados não localizada"+Fonte,"Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return conexao;
    }
    
}
 

