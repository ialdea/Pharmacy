package finalProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Pharmacy extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearchByName;
	private JTable table;
	private Connection con;
	private JTextField textFieldDrug;
	private JTable table_1;
	private Object[] selectedDrugToSale;
	private JTextField textField;
	private JTextField textField_medName;
	private JTextField textField_Description;
	private JTextField textField_ExpireDate;
	private JTextField textField_medNameForUpdate;
	private JTextField textField_expireDateUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pharmacy frame = new Pharmacy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Pharmacy() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpharmacy", "root", "root");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(10, 23, 590, 330);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search in stock", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Drugs by Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 25, 130, 14);
		panel.add(lblNewLabel);
		
		textFieldSearchByName = new JTextField();
		textFieldSearchByName.setBounds(138, 23, 136, 20);
		panel.add(textFieldSearchByName);
		textFieldSearchByName.setColumns(10);
		
		JButton btnNewButton = new JButton("View Drug by Name");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("id_medicament");
				model.addColumn("medicament_name");
				model.addColumn("description");
				model.addColumn("name_ro");
				model.addColumn("quantity");
				model.addColumn("diagnosis_name");
				model.addColumn("expire_date");
				model.addColumn("price");
				try {
					Statement stmt = con.createStatement();
					String s = "select * from drug where medicament_name = '"+textFieldSearchByName.getText()+"'";
					ResultSet rs = stmt.executeQuery(s);
					while(rs.next()) {
						int id_medicament = rs.getInt("id_medicament");
						String medicament_name = rs.getString("medicament_name");
						String description = rs.getString("description");
						Country country = Country.getCountryByID(rs.getInt("id_country"), con);
						int quantity = rs.getInt("quantity");
						Diagnosis diagnosis = Diagnosis.getDiagnosisByID(rs.getInt("id_diagnosis"), con);
						Date expire_date = rs.getDate("expire_date");
						float price = rs.getFloat("price");
						System.out.println(id_medicament+" "+medicament_name+" "+description+" "+country.name_ro+" "+quantity+" "
								+diagnosis.diagnosis_name+" "+expire_date+" "+price);
						
						Object[] obj = {id_medicament, medicament_name, description, country.name_ro, quantity, diagnosis.diagnosis_name,
								expire_date, price};
						model.addRow(obj);
					}
					table.setModel(model);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(284, 21, 151, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View All Drugs");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("id_medicament");
				model.addColumn("medicament_name");
				model.addColumn("description");
				model.addColumn("name_ro");
				model.addColumn("quantity");
				model.addColumn("diagnosis_name");
				model.addColumn("expire_date");
				model.addColumn("price");
				try {
					Statement stmt = con.createStatement();
					String s = "select  id_medicament, medicament_name, description, id_country, quantity, id_diagnosis, expire_date, price "
							+ "from drug";
					ResultSet rs = stmt.executeQuery(s);
					while(rs.next()) {
						int id_medicament = rs.getInt("id_medicament");
						String medicament_name = rs.getString("medicament_name");
						String description = rs.getString("description");
						Country country = Country.getCountryByID(rs.getInt("id_country"), con);
						int quantity = rs.getInt("quantity");
						Diagnosis diagnosis = Diagnosis.getDiagnosisByID(rs.getInt("id_diagnosis"), con);
						Date expire_date = rs.getDate("expire_date");
						float price = rs.getFloat("price");
						System.out.println(id_medicament+" "+medicament_name+" "+description+" "+country.name_ro+" "+quantity+" "
								+diagnosis.diagnosis_name+" "+expire_date+" "+price);
						
						Object[] obj = { id_medicament, medicament_name, description, country.name_ro, quantity, diagnosis.diagnosis_name,
								expire_date, price};
						model.addRow(obj);
					}
					table.setModel(model);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(445, 22, 130, 23);
		panel.add(btnNewButton_1);
		
		table = new JTable();
		table.setBounds(10, 71, 565, 183);
		panel.add(table);
		
		
		JButton btnNewButton_4 = new JButton("Sale");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0)+"");
				String name = table.getValueAt(table.getSelectedRow(), 1)+"";
				int quantity = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4)+"");
				float price = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 7)+"");
				selectedDrugToSale = new Object[]{id, name, quantity, price};
				textFieldDrug.setText(name);
				
			}
		});
		btnNewButton_4.setBounds(473, 265, 89, 23);
		panel.add(btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Drugs sale and payment", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Drug:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 11, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		textFieldDrug = new JTextField();
		textFieldDrug.setBounds(60, 9, 171, 20);
		panel_1.add(textFieldDrug);
		textFieldDrug.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(253, 11, 61, 14);
		panel_1.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(324, 11, 30, 20);
		panel_1.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Compensation:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(376, 13, 101, 14);
		panel_1.add(lblNewLabel_4);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_1.setBounds(483, 13, 41, 20);
		panel_1.add(spinner_1);
		
		JLabel lblNewLabel_3 = new JLabel("%");
		lblNewLabel_3.setBounds(529, 14, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("The buyer HAS prescription for this drug or NOT");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(20, 45, 313, 14);
		panel_1.add(lblNewLabel_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Shopping List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(20, 98, 539, 161);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		table_1 = new JTable();
		DefaultTableModel defModel = new DefaultTableModel();
		defModel.addColumn("Drug name");
		defModel.addColumn("Compensare");
		defModel.addColumn("Receipt");
		defModel.addColumn("Quantity");
		defModel.addColumn("Price");
		table_1.setModel(defModel);
		table_1.setBounds(10, 21, 519, 136);
		panel_4.add(table_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NO", "YES"}));
		comboBox.setBounds(324, 42, 52, 22);
		panel_1.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("Add to the List");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int percentDiscount = Integer.parseInt(spinner_1.getValue()+"");
				int quantity = Integer.parseInt(spinner.getValue()+"");
				float originalPrice = (Float.parseFloat(selectedDrugToSale[3]+"")) * quantity;
				float price = originalPrice - (percentDiscount/100f)*originalPrice;
				Object[] soldDrug = new Object[]{selectedDrugToSale[1], percentDiscount, comboBox.getSelectedItem(), quantity, price};
				defModel.addRow(soldDrug);
			}
		});
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("");
		textField.setBounds(136, 272, 61, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(233, 70, 143, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Total:");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float sum = 0.0f;
				for(int i = 0; i < table_1.getRowCount(); i++) {
					sum = sum + Float.parseFloat(table_1.getValueAt(i, 4).toString());
				}
				textField.setText(Float.toString(sum));
			}
		});
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(53, 270, 77, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("Pay products");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("0.0")) {
					JOptionPane.showMessageDialog(null, "Choose products to sale!");
				}else {
					try {
						int quantityList = 0;
						String medicamentNameList = "";
						for(int i = 0; i < table_1.getRowCount(); i++) {
							quantityList = Integer.parseInt(table_1.getValueAt(i, 3).toString());
							medicamentNameList = table_1.getValueAt(i, 0).toString();
						}
						
						Statement stmt = con.createStatement();
						String s = "update drug set quantity = quantity-"+quantityList+" where medicament_name = '"+medicamentNameList+"'";
						stmt.executeUpdate(s);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Payment done!");
				}
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5.setBounds(379, 268, 145, 23);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Delete from the List");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get table model
				DefaultTableModel tblModel = (DefaultTableModel) table_1.getModel();
				if(table_1.getSelectedRowCount() == 1) {
					tblModel.removeRow(table_1.getSelectedRow());
				}else {
					if(table_1.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Table is empty!");
					}else {
						JOptionPane.showMessageDialog(null, "Select just ONE row!");
					}
				}
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_6.setBounds(26, 70, 205, 23);
		panel_1.add(btnNewButton_6);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Add in stock", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Medicament name:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(23, 47, 109, 14);
		panel_3.add(lblNewLabel_5);
		
		textField_medName = new JTextField();
		textField_medName.setBounds(137, 45, 143, 20);
		panel_3.add(textField_medName);
		textField_medName.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Description:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(23, 105, 82, 14);
		panel_3.add(lblNewLabel_7);
		
		textField_Description = new JTextField();
		textField_Description.setBounds(137, 103, 143, 20);
		panel_3.add(textField_Description);
		textField_Description.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Country:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(23, 169, 59, 14);
		panel_3.add(lblNewLabel_8);
		
		JComboBox comboBox_Country = new JComboBox();
		List<String> country_list = Country.getAllCountries(con);
		comboBox_Country.setModel(new DefaultComboBoxModel(country_list.toArray(new String[0])));
		comboBox_Country.setBounds(137, 166, 143, 22);
		panel_3.add(comboBox_Country);
		
		JLabel lblNewLabel_9 = new JLabel("Quantity:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(23, 226, 59, 14);
		panel_3.add(lblNewLabel_9);
		
		JSpinner spinner_Quantity = new JSpinner();
		spinner_Quantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinner_Quantity.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		spinner_Quantity.setBounds(137, 223, 59, 20);
		panel_3.add(spinner_Quantity);
		
		JLabel lblNewLabel_10 = new JLabel("Diagnosis:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(313, 48, 64, 14);
		panel_3.add(lblNewLabel_10);
		
		JComboBox comboBox_Diagnosis = new JComboBox();
		List<String> diagnosis_list = Diagnosis.getAllDiagnosis(con);
		comboBox_Diagnosis.setModel(new DefaultComboBoxModel(diagnosis_list.toArray(new String[0])));
		comboBox_Diagnosis.setBounds(393, 44, 147, 22);
		panel_3.add(comboBox_Diagnosis);
		
		JLabel lblNewLabel_11 = new JLabel("Expire date:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(313, 106, 76, 14);
		panel_3.add(lblNewLabel_11);
		
		textField_ExpireDate = new JTextField();
		textField_ExpireDate.setBounds(393, 104, 147, 20);
		panel_3.add(textField_ExpireDate);
		textField_ExpireDate.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Price:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(313, 170, 64, 14);
		panel_3.add(lblNewLabel_12);
		
		JSpinner spinner_Price = new JSpinner();
		spinner_Price.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spinner_Price.setBounds(393, 167, 80, 20);
		panel_3.add(spinner_Price);
		
		JButton btnNewButton_7 = new JButton("Insert Medicament");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = con.createStatement();
					Country c = Country.getCountryByName(String.valueOf(comboBox_Country.getSelectedItem()), con);
					Diagnosis d = Diagnosis.getDiagnosisByName(String.valueOf(comboBox_Diagnosis.getSelectedItem()), con);
					String s = "insert into drug (medicament_name, description, id_country, quantity, id_diagnosis, expire_date, price) values('"
							+textField_medName.getText()+"', '"+textField_Description.getText()+"', "+c.id_country+", "
							+spinner_Quantity.getValue()+", "+d.id_diagnosis+", '"+textField_ExpireDate.getText()+"', "
							+spinner_Price.getValue()+")";
					stmt.executeUpdate(s);
					
					JOptionPane.showMessageDialog(null, "Insertion done!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_7.setBounds(393, 256, 147, 23);
		panel_3.add(btnNewButton_7);
		
		JLabel lblNewLabel_13 = new JLabel("If the product already exist, go to UPDATE!!! ");
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13.setBounds(23, 11, 471, 14);
		panel_3.add(lblNewLabel_13);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Update products", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("Medicament name:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_14.setBounds(29, 31, 113, 14);
		panel_2.add(lblNewLabel_14);
		
		textField_medNameForUpdate = new JTextField();
		textField_medNameForUpdate.setBounds(152, 29, 145, 20);
		panel_2.add(textField_medNameForUpdate);
		textField_medNameForUpdate.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Country name:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_15.setBounds(29, 78, 113, 14);
		panel_2.add(lblNewLabel_15);
		
		JComboBox comboBox_countryNameUpdate = new JComboBox();
		List<String> l = Country.getAllCountries(con);
		comboBox_countryNameUpdate.setModel(new DefaultComboBoxModel(l.toArray(new String[0])));
		comboBox_countryNameUpdate.setBounds(152, 75, 145, 22);
		panel_2.add(comboBox_countryNameUpdate);
		
		JLabel lblNewLabel_16 = new JLabel("Quantity");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(29, 128, 78, 14);
		panel_2.add(lblNewLabel_16);
		
		JSpinner spinner_quantityUpdate = new JSpinner();
		spinner_quantityUpdate.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		spinner_quantityUpdate.setBounds(152, 126, 50, 20);
		panel_2.add(spinner_quantityUpdate);
		
		JLabel lblNewLabel_17 = new JLabel("Expire date:");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_17.setBounds(325, 31, 78, 14);
		panel_2.add(lblNewLabel_17);
		
		textField_expireDateUpdate = new JTextField();
		textField_expireDateUpdate.setBounds(413, 29, 151, 20);
		panel_2.add(textField_expireDateUpdate);
		textField_expireDateUpdate.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Price:");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_18.setBounds(325, 78, 58, 14);
		panel_2.add(lblNewLabel_18);
		
		JSpinner spinner_priceUpdate = new JSpinner();
		spinner_priceUpdate.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spinner_priceUpdate.setBounds(413, 76, 58, 20);
		panel_2.add(spinner_priceUpdate);
		
		JButton btnNewButton_8 = new JButton("Update");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = con.createStatement();
					Country c = Country.getCountryByName(String.valueOf(comboBox_countryNameUpdate.getSelectedItem()), con);
					String s = "update drug set id_country = "+c.id_country+", quantity = quantity + "+spinner_quantityUpdate.getValue()
						+", expire_date = '"+textField_expireDateUpdate.getText()+"', price = "+spinner_priceUpdate.getValue()
						+" where medicament_name = '"+textField_medNameForUpdate.getText()+"'";
					stmt.executeUpdate(s);
					
					JOptionPane.showMessageDialog(null, "Update successfully done!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_8.setBounds(413, 246, 89, 23);
		panel_2.add(btnNewButton_8);
	}
	
}
