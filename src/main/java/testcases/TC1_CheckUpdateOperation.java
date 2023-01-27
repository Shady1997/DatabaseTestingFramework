package testcases;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import interfaces.CheckUpdateOnDatabase;
import main.Base;
import model.user;

public class TC1_CheckUpdateOperation extends Base implements CheckUpdateOnDatabase {

	@Test(description=" Check update on Database ",priority = 4, groups = "smoke")
	public void updateToDatabase() {
		// update into database
		try {
			getUserData();
			System.out.println(userList.get(0).getName().toString());
			st.execute(
					"UPDATE `users` SET id=100 , name = \"khloud\" , gender = false , address = \"2 el maddi street - cairo - egypt\" , profession = \"Teacher\" WHERE id =("
							+ userList.get(0).getId() + ")");
			getUserData();
			System.out.println(userList.get(1).getName().toString());
			Assert.assertEquals(userList.get(1).getName().toString(), "khloud");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getUserData() {
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
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
