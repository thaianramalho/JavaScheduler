import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class Dashboard {

	private JFrame frame;
	private UsuarioDAO usuarioDAO;
	private String nomeUsuarioLogado = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					Dashboard window = new Dashboard(usuarioDAO);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Dashboard(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
		if (usuarioDAO != null) {
			this.nomeUsuarioLogado = usuarioDAO.getNomeUsuarioLogado();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/Img/miniLogo.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Dimension frameSize = frame.getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		frame.setLocation(x, y);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		JPanel background = new JPanel();
		background.setBackground(new Color(255, 255, 255));
		background.setBounds(0, 0, 1264, 681);
		frame.getContentPane().add(background);
		background.setLayout(null);

		JPanel sidebar = new JPanel();
		sidebar.setBackground(new Color(245, 255, 250));
		sidebar.setBounds(0, 0, 192, 681);
		background.add(sidebar);
		sidebar.setLayout(null);

		JLabel lblInicio = new JLabel("Início");
		lblInicio.setForeground(new Color(0, 0, 0));
		lblInicio.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblInicio.setBounds(0, 144, 172, 21);
		sidebar.add(lblInicio);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(0, 131, 192, 2);
		sidebar.add(separator);

		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Adicione o código desejado quando o rótulo "Início" for clicado
				// por exemplo, abrir uma nova tela ou realizar alguma ação específica
			}
		});

		JPanel topbar = new JPanel();
		topbar.setBackground(new Color(245, 255, 250));
		topbar.setBounds(191, 0, 1073, 48);
		background.add(topbar);
		topbar.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setToolTipText("DeVence.com.br");
		lblLogo.setIcon(new ImageIcon(Dashboard.class.getResource("/Img/miniLogo.png")));
		lblLogo.setBounds(506, 0, 58, 47);
		topbar.add(lblLogo);

		JLabel bemVindoUsuario = new JLabel("Olá, " + usuarioDAO.getNomeUsuarioLogado() + "!");
		bemVindoUsuario.setFont(new Font("Roboto", Font.PLAIN, 14));
		bemVindoUsuario.setBounds(925, 15, 138, 14);
		topbar.add(bemVindoUsuario);

		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("http://devence.com.br"));
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(191, 48, 1073, 633);
		background.add(panel);
	}
}
