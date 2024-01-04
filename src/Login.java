import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Label;
import java.awt.Canvas;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Window.Type;

public class Login extends JFrame {

	private JFrame frmDevence;
	private JTextField inputUsuario;
	private JPasswordField inputSenha;
	private Registro registro;
	private UsuarioDAO usuarioDAO;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmDevence.setVisible(true);

					window.usuarioDAO.testarConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
		usuarioDAO = new UsuarioDAO();
	}

	private void initialize() {
		frmDevence = new JFrame();
		frmDevence.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Img/miniLogo.png")));
		frmDevence.setTitle("Login | DeVence");
		frmDevence.setResizable(false);
		frmDevence.setBounds(100, 100, 800, 500);
		frmDevence.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Dimension frameSize = frmDevence.getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		frmDevence.setLocation(x, y);
		frmDevence.setVisible(true);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(245, 255, 250));
		frmDevence.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setBounds(0, 0, 400, 461);
		panelPrincipal.add(panelLeft);
		panelLeft.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(10, 181, 380, 98);
		panelLeft.add(logo);
		logo.setIcon(new ImageIcon(Login.class.getResource("/Img/logo.png")));

		JLabel lblNewLabel_2 = new JLabel("Copyright © DeVence | Todos os Direitos Reservados");
		lblNewLabel_2.setFont(new Font("Gramatika-Black", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 407, 380, 27);
		panelLeft.add(lblNewLabel_2);

		JLabel miniLogo = new JLabel("");
		miniLogo.setHorizontalAlignment(SwingConstants.CENTER);
		miniLogo.setIcon(new ImageIcon(Login.class.getResource("/Img/miniLogo.png")));
		miniLogo.setBounds(172, 11, 68, 47);
		panelLeft.add(miniLogo);

		JLabel loginTitle = new JLabel("ENTRAR");
		loginTitle.setForeground(new Color(8, 79, 73));
		loginTitle.setFont(new Font("Gramatika-Black", Font.PLAIN, 25));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setBounds(410, 45, 364, 62);
		panelPrincipal.add(loginTitle);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(447, 118, 46, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(447, 198, 46, 14);
		panelPrincipal.add(lblSenha);

		inputUsuario = new JTextField();
		inputUsuario.setToolTipText("Usuário");
		inputUsuario.setColumns(10);
		inputUsuario.setBounds(447, 143, 300, 30);
		panelPrincipal.add(inputUsuario);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBackground(new Color(23, 121, 112));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = inputUsuario.getText();
				String password = new String(inputSenha.getPassword());

				if (usuarioDAO.validarUsuario(username, password)) {
					JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
					inputUsuario.setText(null);
					inputSenha.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					inputUsuario.setText(null);
					inputSenha.setText(null);
				}
			}
		});
		btnEntrar.setBounds(447, 278, 89, 30);
		panelPrincipal.add(btnEntrar);

		inputSenha = new JPasswordField();
		inputSenha.setToolTipText("Senha");
		inputSenha.setBounds(447, 223, 300, 30);
		panelPrincipal.add(inputSenha);

		JLabel lblNewLabel_1 = new JLabel("Não possui uma conta?");
		lblNewLabel_1.setBounds(447, 396, 146, 14);
		panelPrincipal.add(lblNewLabel_1);

		JButton btnTelaRegistrar = new JButton("Registrar");
		btnTelaRegistrar.setBackground(UIManager.getColor("Button.disabledShadow"));
		btnTelaRegistrar.setForeground(Color.RED);
		btnTelaRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaRegistro();
			}
		});
		btnTelaRegistrar.setBounds(590, 392, 89, 23);
		panelPrincipal.add(btnTelaRegistrar);
	}

	private void abrirTelaRegistro() {
		frmDevence.dispose();
		registro = new Registro();
	}

}
