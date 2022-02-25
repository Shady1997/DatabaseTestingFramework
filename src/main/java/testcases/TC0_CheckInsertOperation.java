package testcases;

import java.sql.SQLException;

import org.testng.annotations.Test;

import interfaces.CheckInsertToDatabase;
import main.Base;
import model.user;
import utility.ExcelUtility;

public class TC0_CheckInsertOperation extends Base implements CheckInsertToDatabase {

	@Test(priority = 3, groups = "smoke")
	public void insertToDatabase() {
		// TODO code to check insert to database
		// insert into database
		try {
			st.execute(ExcelUtility.getQuires(2));
			// execute the query, and get a java resultset
			rs = st.executeQuery("SELECT * FROM `users` WHERE id=(SELECT MAX(id) FROM `users`);");
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
			softAssert.assertEquals(userList.get(0).getName().toString(), "hassan");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
