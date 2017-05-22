package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries(int anno) {
		
		List<Country> countries = new ArrayList<Country>();

		String sql = "SELECT DISTINCT StateAbb AS abb, CCode AS codice, StateNme AS nome " +
                     "FROM contiguity, country " +
                     "WHERE contiguity.state1no = country.CCode AND year<=? AND conttype = '1'";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getInt("codice"),rs.getString("abb"),rs.getString("nome"));
				countries.add(c);			
			}

			conn.close();
			return countries;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		List<Border> countryPairs = new ArrayList<Border>();
		
		String sql = "SELECT C1.CCode AS codice1, C1.StateNme AS nome1, C1.StateAbb AS abb1, C2.CCode AS codice2, C2.StateNme AS nome2, C2.StateAbb AS abb2 " +
                     "FROM contiguity, country AS C1, country AS C2 " +
                     "WHERE contiguity.state1no = C1.CCode AND contiguity.state2no = C2.CCode AND year<=? AND conttype = '1'";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Country c1 = new Country(rs.getInt("codice1"), rs.getString("abb1"), rs.getString("nome1"));
				Country c2 = new Country(rs.getInt("codice2"), rs.getString("abb2"), rs.getString("nome2"));
				
				Border b1 = new Border(c1,c2);
				Border b2 = new Border(c2,c1);
				
				if(!countryPairs.contains(b1) && !countryPairs.contains(b2))
					countryPairs.add(b1);
			}

			conn.close();
			return countryPairs;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database Error");
		}
	}
}
