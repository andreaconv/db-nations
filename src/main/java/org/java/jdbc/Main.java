package org.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		
		final String url = "jdbc:mysql://localhost:3306/db_nations";
		final String user = "root";
		final String password = "";
		
		try (Connection con = DriverManager.getConnection(url, user, password)) {

			//inserire il nome del METODO
			
			readNations(con);
			
		} catch (Exception e) {
			System.out.println("Errore di connessione: " + e.getMessage());
		}
		System.out.println("\n----------------------------------\n");
		System.out.println("The end");
	}
	
	private static final void readNations(Connection con) {
		
		final String sql = " SELECT c.country_id, c.name, r.name, c2.name"
				+ " FROM countries c "
				+ " JOIN regions r "
				+ " ON r.region_id = c.region_id"
				+ " JOIN continents c2 "
				+ " ON c2.continent_id = r.continent_id "
				+ " ORDER BY c.name;";
		
		

	}
	
	
}
