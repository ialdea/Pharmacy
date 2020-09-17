package finalProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Diagnosis {
	
	int id_diagnosis;
	String diagnosis_name;
	String description;
	
	public Diagnosis (int id_diagnosis, String diagnosis_name, String description) {
		this.id_diagnosis = id_diagnosis;
		this.diagnosis_name = diagnosis_name;
		this.description = description;
	}
	
	public static Diagnosis getDiagnosisByID(int id_diagnosis, Connection con) throws Exception {
		Statement stmt = con.createStatement();
		String s = "select * from diagnosis where id_diagnosis = "+id_diagnosis;
		ResultSet rs = stmt.executeQuery(s);
		if(rs.next()) {
			return new Diagnosis(rs.getInt("id_diagnosis"), rs.getString("diagnosis_name"), rs.getString("description"));
		}else {
			throw new Exception("No diagnosis found!");
		}
	}

	public static Diagnosis getDiagnosisByName(String diagnosis_name, Connection con) throws Exception {
		Statement stmt = con.createStatement();
		String s = "select * from diagnosis where diagnosis_name = '"+diagnosis_name+"'";
		ResultSet rs = stmt.executeQuery(s);
		if(rs.next()) {
			return new Diagnosis(rs.getInt("id_diagnosis"), rs.getString("diagnosis_name"), rs.getString("description"));
		}else {
			throw new Exception("No diagnosis found!");
		}
		
	}

	public static List<String> getAllDiagnosis(Connection con) throws Exception {
		Statement stmt = con.createStatement();
		String s = "select diagnosis_name from diagnosis";
		ResultSet rs = stmt.executeQuery(s);
		List<String> list = new ArrayList<>();
		while(rs.next()) {
			list.add(rs.getString("diagnosis_name"));
		}
		return list;
	}

}
