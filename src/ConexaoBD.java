import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	private static final String URL = "jdbc:mysql://localhost:3306/loginsystem";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
