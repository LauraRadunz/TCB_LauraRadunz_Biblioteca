package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;

    private ConnectionFactory() {
    }
    
    public static Connection getConnection() {
        try {
            if (conexao == null) {
                //jdbc:gdbd://ip do servidor de BD:porta/database
                String url = "jdbc:mysql://localhost:3306/biblioteca";
                String user = "root";
                String password = "root";
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso!");            
            } 
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return conexao;
    }
}
