package testcases;

import java.sql.SQLException;

import org.testng.annotations.Test;

import interfaces.CheckUpdateOnDatabase;
import main.Base;
import model.user;

public class TC1_CheckUpdateOperation extends Base implements CheckUpdateOnDatabase {

	@Test(priority = 4, groups = "smoke")
	public void updateToDatabase() {
		// update into database
		try {
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
			st.execute(
					"UPDATE `users` SET id=100 , name = \"khloud\" , gender = false , address = \"2 el maddi street - cairo - egypt\" , profession = \"Teacher\" WHERE id =("+userList.get(0).getId()+")");
			
			softAssert.assertEquals(userList.get(0).getName().toString(), "khloud");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
