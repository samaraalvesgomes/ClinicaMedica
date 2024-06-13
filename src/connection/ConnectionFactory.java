package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.logging.Level;
// import java.util.logging.Logger;

    public class ConnectionFactory {

        private static final String DRIVER = "com.mysql.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/clinica_medica";
        private static final String USER = "root";
        private static final String PASS = "root";

        public static Connection getConnection() {
            try {
                Class.forName(DRIVER);
                //System.out.println("conexão criada com sucesso");
                return DriverManager.getConnection(URL, USER, PASS);

            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException("Erro na conexão: ", ex);
            }
        }
        
}
