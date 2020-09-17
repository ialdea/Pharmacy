package finalProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Drug {
	
	int id_medicament;
	String medicament_name;
	String description;
	Country country;
	int quantity;
	Diagnosis diagnosis;
	Date expire_date;
	float price;
	
	public void setCountry(Country c) {this.country = c;}
	public void setDiagnosis(Diagnosis d) {this.diagnosis = d;}
	
	public Drug (int id_medicament, String medicament_name, String description, int quantity, Date expire_date, float price) {
		this.id_medicament = id_medicament;
		this.medicament_name = medicament_name;
		this.description = description;
		this.quantity = quantity;
		this.expire_date = expire_date;
		this.price = price;
	}
	
	public static Drug getDrugByName(String medicament_name, Connection con) throws Exception {
		Statement stmt = con.createStatement();
		String s = "select * from drug where medicament_name = '"+medicament_name+"'";
		ResultSet rs = stmt.executeQuery(s);
		if(rs.next()) {
			Drug drug = new Drug(rs.getInt("id_medicament"), rs.getString("medicament_name"), rs.getString("description"), rs.getInt("quantity"),
					rs.getDate("expire_date"), rs.getFloat("price"));
			Country c = Country.getCountryByID(rs.getInt("id_country"), con);
			Diagnosis diagnosis = Diagnosis.getDiagnosisByID(rs.getInt("id_diagnosis"), con);
			drug.setCountry(c);
			drug.setDiagnosis(diagnosis);
			return drug;
		}else {
			throw new Exception("No drug found!");
		}
	}

}
