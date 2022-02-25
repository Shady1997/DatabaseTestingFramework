package main;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import model.user;

public class ClassProperties {
	// declare class properties to establish connection to database
	protected static Connection conn;
	protected static Statement st;
	protected static ResultSet rs;
	protected static SoftAssert softAssert = new SoftAssert();
	protected static List<user> userList = new ArrayList<user>();
	protected static FileInputStream readProperty;

	// Declare Connection Properties
	protected static String myUrl;
	protected static String dbUser;
	protected static String dbPassword;

}
