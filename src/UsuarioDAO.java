import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/loginsystem";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private String nomeUsuarioLogado = "";

	public String getNomeUsuarioLogado() {
		return nomeUsuarioLogado;
	}

	public void setNomeUsuarioLogado(String nomeUsuarioLogado) {
		this.nomeUsuarioLogado = nomeUsuarioLogado;
	}

	private boolean usuarioExiste(String usuario, Connection connection) throws SQLException {
		String sql = "SELECT * FROM users WHERE usuario = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario);

		ResultSet resultSet = statement.executeQuery();

		return resultSet.next();
	}

	public boolean cadastrarUsuario(String usuario, String senha) {
		try {

			if (usuario.length() < 3 || senha.length() < 3) {
				System.out.println("Usuário ou senha com menos de 3 caracteres");
				return false;
			}

			Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);

			if (usuarioExiste(usuario, connection)) {
				System.out.println("Usuário já existe");
				connection.close();
				return false;
			}

			String sql = "INSERT INTO users (usuario, senha) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario);
			statement.setString(2, senha);

			int rowsAffected = statement.executeUpdate();
			connection.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validarUsuario(String usuario, String senha) {
		try {
			Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);

			String sql = "SELECT * FROM users WHERE usuario = ? AND senha = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario);
			statement.setString(2, senha);

			ResultSet resultSet = statement.executeQuery();
			boolean isValid = resultSet.next();

			if (isValid) {
				nomeUsuarioLogado = resultSet.getString("usuario");
			}

			connection.close();

			return isValid;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void testarConexao() {
		try {
			Connection connection = ConexaoBD.obterConexao();
			System.out.println("Conexão bem-sucedida!");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erro ao conectar ao banco de dados.");
		}
	}
}
