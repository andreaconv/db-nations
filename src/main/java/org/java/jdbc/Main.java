package org.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

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
		
		//Le sequenze di escape "\r\n" inserite alla fine di ogni riga sono caratteri speciali che rappresentano un ritorno a capo (line feed) e un avanzamento del cursore all'inizio della riga successiva (carriage return).
		
		final String sql = " SELECT c.country_id, c.name, r.name, c2.name \r\n"
				+ " FROM countries c \r\n"
				+ " JOIN regions r \r\n"
				+ " ON r.region_id = c.region_id \r\n"
				+ " JOIN continents c2 \r\n"
				+ " ON c2.continent_id = r.continent_id \r\n"
				+ " WHERE c.name LIKE ?;";
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Inserire una nazione: ");
		String userNazione = sc.nextLine();

		final String parametro = "%" + userNazione +  "%";
		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, parametro);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int countryId = rs.getInt("c.country_id");
				String nameCounty = rs.getString("c.name");
				String nameRegion = rs.getString("r.name");
				String nameContinents = rs.getString("c2.name");
				
				System.out.println("Id continente: " + countryId + "\n");
				System.out.println("Nome nazione: " + nameCounty);
				System.out.println("Nome regione: " + nameRegion);
				System.out.println("Nome continente: " + nameContinents);
				
				System.out.println("\n------------------------------\n");
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
}
