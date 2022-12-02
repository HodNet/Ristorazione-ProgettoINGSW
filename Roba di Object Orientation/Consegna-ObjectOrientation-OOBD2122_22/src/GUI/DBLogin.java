package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.k33ptoo.components.KGradientPanel;

import controller.Controller;

public class DBLogin extends JFrame {

	private static final int width = 650;
	private static final int height = 425;
	private static final int x = Controller.screenWidth/2 - width/2;
	private static final int y = Controller.screenHeight/2 - height/2;
	
	private JPanel contentPane;
	private JTextField url;
	private JTextField username;
	private JPasswordField password;

	private JCheckBox ricordaPassword;
	public static final String DBinfoFilePath = System.getProperty("user.dir") + File.separator + "src\\saves\\DBinfo.txt";
	
	public DBLogin() {
		setTitle("Tracciamento Covid-19 per ristoranti");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		KGradientPanel gradientPanel = new KGradientPanel();
		gradientPanel.kBorderRadius = 0;
		gradientPanel.setBounds(0, 0, 246, 386);
		gradientPanel.kEndColor = Color.GRAY;
		gradientPanel.kStartColor = Color.BLACK;
		contentPane.add(gradientPanel);
		gradientPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 305, 224, 70);
		Controller.scaleImage(lblNewLabel, "PostegreSQL Logo.png");
		gradientPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Using:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 279, 50, 15);
		gradientPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Accesso al Database");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(256, 11, 183, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("URL:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(256, 59, 30, 22);
		contentPane.add(lblNewLabel_3);
		
		url = new JTextField();
		url.setText(Controller.getUrlFromFile());
		url.setBounds(256, 92, 291, 20);
		contentPane.add(url);
		url.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Username:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(256, 150, 66, 22);
		contentPane.add(lblNewLabel_4);
		
		username = new JTextField();
		username.setText(Controller.getUsernameFromFile());
		username.setBounds(256, 183, 291, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(256, 244, 66, 22);
		contentPane.add(lblNewLabel_5);
		
		password = new JPasswordField();
		password.setText(Controller.getPasswordFromFile());
		password.setBounds(256, 277, 291, 20);
		contentPane.add(password);
		
		ricordaPassword = new JCheckBox("Ricorda password");
		ricordaPassword.setSelected(true);
		ricordaPassword.setBackground(Color.WHITE);
		ricordaPassword.setBounds(252, 304, 187, 23);
		contentPane.add(ricordaPassword);
		
		JButton btnNewButton = new JButton("Avanti");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.checkDataBase();
			}
		});
		btnNewButton.setBounds(458, 340, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	public String getUrl() {
		return url.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		return String.valueOf(password.getPassword());
	}

	public boolean isRicordaPasswordSelected() {
		return ricordaPassword.isSelected();
	}
}
