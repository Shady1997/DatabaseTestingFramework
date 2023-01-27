package testcases;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import interfaces.SelectAndMatch;
import main.Base;

public class TC3_SelectOperation extends Base implements SelectAndMatch {

	@Test(description=" Check Select Operation on Database ",priority = 6, groups = "smoke")
	public void selectAndMatchData(String table_name, ArrayList<String> table_columns,
			ArrayList<String> expected_data) {
		// TODO select data from database
		try {
			rs = st.executeQuery("SELECT * FROM `users` WHERE id=(SELECT MAX(id) FROM `users`);");
			rs.next();
			Assert.assertEquals(rs.getString("name").isEmpty(), true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
