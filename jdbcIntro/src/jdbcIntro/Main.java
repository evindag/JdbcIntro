 package jdbcIntro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement = null;
		ResultSet resultSet;
		
		try {	
			connection = helper.getConnection();
			String sql = "delete from film_text where film_id =?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 1003);
			int result=statement.executeUpdate();
			System.out.println("Deleted");
		}catch(SQLException exception) {
			helper.showErrorMessage(exception);
		}
		finally { 
			connection.close();
		}
	}
		
		public static void selectDemo() throws SQLException{
			Connection connection = null;
			DbHelper helper = new DbHelper();
			Statement statement = null;
			ResultSet resultSet;
			
			try {	
				connection = helper.getConnection();
				statement = connection.createStatement();
				resultSet =statement.executeQuery("select film_id,title,description from film_text");
				ArrayList<Film> films = new ArrayList<Film>();
				while(resultSet.next()) {
					 films.add(new Film(
							 resultSet.getString("film_id"),
							 resultSet.getString("title"),
							 resultSet.getString("description")));
				}
				System.out.println(films.size());
			}catch(SQLException exception) {
				helper.showErrorMessage(exception);
			}
			finally {
				connection.close();
			}

		}
		
		public static void insertData() throws SQLException{
			Connection connection = null;
			DbHelper helper = new DbHelper();
			PreparedStatement statement = null;
			ResultSet resultSet;
			
			try {	
				connection = helper.getConnection();
				String sql = "insert into film_text(film_id, title,description) values(?,?,?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, "1003");
				statement.setString(2, "xxxx");
				statement.setString(3, "yyyy");
				int result=statement.executeUpdate();
				System.out.println("Added");
			}catch(SQLException exception) {
				helper.showErrorMessage(exception);
			}
			finally { 
				connection.close();
			}
		}
		
		public void updateData()throws SQLException{
			Connection connection = null;
			DbHelper helper = new DbHelper();
			PreparedStatement statement = null;
			ResultSet resultSet;
			
			try {	
				connection = helper.getConnection();
				String sql = "update film_text set title='a nice movie :)' where film_id =?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, 1001);
				int result=statement.executeUpdate();
				System.out.println("Updated");
			}catch(SQLException exception) {
				helper.showErrorMessage(exception);
			}
			finally { 
				connection.close();
			}
		}
		
	}


