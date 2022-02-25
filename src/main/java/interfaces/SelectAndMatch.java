package interfaces;

import java.util.ArrayList;

public interface SelectAndMatch {

	//prototype for select operation
	public void selectAndMatchData(String table_name, ArrayList<String> table_columns, ArrayList<String> expected_data);
}
