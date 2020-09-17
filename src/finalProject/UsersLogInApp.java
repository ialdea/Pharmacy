package finalProject;

import java.awt.EventQueue;
import java.util.logging.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UsersLogInApp {

	private JFrame frame;
	private JTextField textFieldLoginUsername;
	private JPasswordField passwordFieldLogin;
	private JTextField textField_Name;
	private JTextField textField_Surname;
	private JTextField textField_Username;
	private JPasswordField passwordField_forInsert;
	private JTextField textField_Address;
	private JTextField textField_Phone;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			Logger logger = Log.getLogger("C:\\users\\40740\\Desktop\\Work\\Files\\LoginUsersLogging.txt");
			logger.setLevel(Level.INFO);
			logger.info("UsersLoginApp is open");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersLogInApp window = new UsersLogInApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public UsersLogInApp() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpharmacy", "root", "root");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 538, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 502, 70);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginUsername = new JLabel("Username");
		lblLoginUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoginUsername.setBounds(39, 21, 95, 14);
		panel.add(lblLoginUsername);
		
		textFieldLoginUsername = new JTextField();
		textFieldLoginUsername.setBounds(10, 39, 162, 20);
		panel.add(textFieldLoginUsername);
		textFieldLoginUsername.setColumns(10);
		
		JLabel lblLoginPassword = new JLabel("Password");
		lblLoginPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoginPassword.setBounds(218, 21, 81, 14);
		panel.add(lblLoginPassword);
		
		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBounds(182, 39, 162, 20);
		panel.add(passwordFieldLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = con.createStatement();
					String s = "select * from users where username = '"+textFieldLoginUsername.getText()+"' and `password` = '"+
							passwordFieldLogin.getText()+"'";
					ResultSet rs = stmt.executeQuery(s);
					if(rs.next()) {
						frame.dispose();
						Pharmacy pharmacy = new Pharmacy();
						pharmacy.setVisible(true);
						
						Logger logger = Log.getLogger("C:\\users\\40740\\Desktop\\Work\\Files\\LoginUsersLogging.txt");
						logger.setLevel(Level.INFO);
						logger.info(textFieldLoginUsername.getText()+" is logged in PharmacyApp!");
					}else {
						JOptionPane.showMessageDialog(null, "Invalid username or password! Try again!");
						
						Logger logger = Log.getLogger("C:\\users\\40740\\Desktop\\Work\\Files\\LoginUsersLogging.txt");
						logger.setLevel(Level.INFO);
						logger.info("Invalid username or password");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(354, 38, 138, 23);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Create new account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 92, 502, 229);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(10, 32, 46, 14);
		panel_1.add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(74, 30, 143, 20);
		panel_1.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSurname.setBounds(10, 75, 59, 14);
		panel_1.add(lblSurname);
		
		textField_Surname = new JTextField();
		textField_Surname.setBounds(74, 73, 143, 20);
		panel_1.add(textField_Surname);
		textField_Surname.setColumns(10);
		
		JLabel lblNewUsername = new JLabel("Username:");
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewUsername.setBounds(10, 127, 59, 14);
		panel_1.add(lblNewUsername);
		
		textField_Username = new JTextField();
		textField_Username.setBounds(74, 125, 143, 20);
		panel_1.add(textField_Username);
		textField_Username.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewPassword.setBounds(10, 176, 59, 14);
		panel_1.add(lblNewPassword);
		
		passwordField_forInsert = new JPasswordField();
		passwordField_forInsert.setBounds(74, 174, 143, 20);
		panel_1.add(passwordField_forInsert);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddress.setBounds(255, 33, 66, 14);
		panel_1.add(lblAddress);
		
		textField_Address = new JTextField();
		textField_Address.setBounds(331, 30, 143, 20);
		panel_1.add(textField_Address);
		textField_Address.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhone.setBounds(255, 76, 46, 14);
		panel_1.add(lblPhone);
		
		textField_Phone = new JTextField();
		textField_Phone.setBounds(331, 73, 143, 20);
		panel_1.add(textField_Phone);
		textField_Phone.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCountry.setBounds(255, 128, 66, 14);
		panel_1.add(lblCountry);
		
		JComboBox comboBox = new JComboBox();
		List<String> countryList = Country.getAllCountries(con);
		comboBox.setModel(new DefaultComboBoxModel(countryList.toArray(new String[0])));
		comboBox.setBounds(331, 124, 143, 22);
		panel_1.add(comboBox);
		
		JButton btnInsertUser = new JButton("Insert User");
		btnInsertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = con.createStatement();
					Country c = Country.getCountryByName(String.valueOf(comboBox.getSelectedItem()), con);
					String s = "insert into users (name, surname, username, `password`, address, phone, id_country) values ('"+textField_Name.getText()+
							"', '"+textField_Surname.getText()+"', '"+textField_Username.getText()+"', '"+passwordField_forInsert.getText()+
							"', '"+textField_Address.getText()+"', '"+textField_Phone.getText()+"', "+c.id_country+")";
					stmt.executeUpdate(s);
					
					Logger logger = Log.getLogger("C:\\users\\40740\\Desktop\\Work\\Files\\LoginUsersLogging.txt");
					logger.setLevel(Level.INFO);
					logger.info(textField_Name.getText()+" "+textField_Surname.getText()+" is a new user in PharmacyApp");
					
					JOptionPane.showMessageDialog(null, "Successful insert!");
					
					textField_Name.setText("    ");
					textField_Surname.setText("   ");
					textField_Username.setText("    ");
					//passwordField_forInsert.setText("    ");
					textField_Address.setText("   ");
					textField_Phone.setText("   ");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnInsertUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInsertUser.setBounds(331, 173, 143, 23);
		panel_1.add(btnInsertUser);
	}
}
