package testcases;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import interfaces.CheckDeleteFromDatabase;
import main.Base;

public class TC2_CheckDeleteOperation extends Base implements CheckDeleteFromDatabase {

	@Test(description=" Check delete from Database ",priority = 5, groups = "smoke")
	public void deleteFromDatabase() {
		// TODO delete operation on database
		// update into database
		try {
			// execute the query, and get a java resultset
//			rs = st.executeQuery("SELECT * FROM `users` WHERE id=(SELECT MAX(id) FROM `users`);");
//			while (rs.next()) {
//				user user = new user();
//				user.setId(rs.getInt("id"));
//				user.setName(rs.getString("name"));
//				user.setPhone(rs.getInt("phone"));
//				user.setAddress(rs.getString("address"));
//				user.setProfession(rs.getString("profession"));
//				user.setGender(rs.getBoolean("gender"));
//				userList.add(user);
//			}
//			st.execute("DELETE FROM `users` WHERE id=(SELECT MAX(id) FROM `users`);");
			st.execute("DELETE FROM `users` WHERE id =(" + userList.get(1).getId() + ")");
			rs = st.executeQuery("SELECT * FROM `users` WHERE id=(SELECT MAX(id) FROM `users`);");
			rs.next();
			Assert.assertNotEquals(rs.getString("name").isEmpty(), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
