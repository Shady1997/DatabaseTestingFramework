package main;

import java.io.FileInputStream;
import java.sql.DriverManager;
import model.user;
import utility.ExcelUtility;

import java.sql.SQLException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Base extends ClassProperties {

	// TODO : Check connection to Mysql Database
	@BeforeSuite
	public void checkConnectionToMySqlDatabase() {
		try {
			// declare properties file to read from properties
			readProperty = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\properties\\generalProperties.properties");
			Properties prop = new Properties();
			prop.load(readProperty);
			// initialize connections
			myUrl = prop.getProperty("mysqlUrl");
			dbUser = prop.getProperty("username");
			dbPassword = prop.getProperty("password");
			// create our mysql database connection
			conn = DriverManager.getConnection(myUrl, dbUser, dbPassword);
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	// TODO : Check select operation to database
	@Test(priority = 1, groups = "smoke")
	public void checkSelectOperation() {
		try {
			// get data from mysql database
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of using "*"
			String query = ExcelUtility.getQuires(1);
			// create the java statement
			st = conn.createStatement();
			// execute the query, and get a java resultset
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO : Check data reterived from database stored locally in model
	@Test(priority = 2, groups = "smoke")
	public void addResultsToLocalModel() throws SQLException {
		// iterate through the java resultset
		try {
			while (rs.next()) {
				user user = new user();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getInt("phone"));
				user.setAddress(rs.getString("address"));
				user.setProfession(rs.getString("profession"));
				user.setGender(rs.getBoolean("gender"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Check if data retreived successfully
//		System.out.println(Integer.toString(userList.get(0).getId()));
	}

	// TODO : close connection at the end
	@AfterSuite
	public void terdown() {
		try {
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}
}
