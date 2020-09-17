package finalProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Country {
	
	int id_country;
	String name_en;
	String name_ro;
	
	public Country(int id_country, String name_en, String name_ro) {
		this.id_country = id_country;
		this.name_en = name_en;
		this.name_ro = name_ro;
	}
	
	public static List<String> getAllCountries(Connection con) throws Exception{
		Statement stmt = con.createStatement();
		String s = "select name_ro from country";
		ResultSet rs = stmt.executeQuery(s);
		List<String> list = new ArrayList<>();
		while(rs.next()) {
			list.add(rs.getString("name_ro"));
		}
		return list;
	}

	public static Country getCountryByName(String name, Connection con) throws Exception {
		Statement stmt = con.createStatement();
		String s = "select * from country where name_ro = '"+name+"'";
		ResultSet rs = stmt.executeQuery(s);
		if(rs.next()) {
			return new Country(rs.getInt("id_country"), rs.getString("name_en"), rs.getString("name_ro"));
		}else {
			throw new Exception("No country found!");
		}	
	}

	public static Country getCountryByID(int id_country, Connection con) throws Exception {
		Statement stmt = con.createStatement();	
		String s = "select * from country where id_country = "+id_country;
		ResultSet rs = stmt.executeQuery(s);
		if(rs.next()) {
			return new Country(rs.getInt("id_country"), rs.getString("name_en"), rs.getString("name_ro"));
		}else {
			throw new Exception("No country found!");
		}
	
	}
}
