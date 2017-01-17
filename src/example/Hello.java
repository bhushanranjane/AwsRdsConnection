package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<String, String> {
	Database database = new Database();
	Statement statement;
	ResultSet resultSet;

	@Override
	public String handleRequest(String input, Context context) {
		
		//String abc=database.database(input);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://employee1.cl3m2q8sxt0y.us-west-2.rds.amazonaws.com/Employee1", "Employee1",
					"Employee1");
			// here sonoo is database name, root is username and password
			context.getLogger().log("Test Started");
			String query = "select * from timepass where id=" + input;
			Statement statement = con.createStatement();
			resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				String output = "Hello, " + resultSet.getString("name") + "!";
				// System.out.println(resultSet.getString("id")+"===="+resultSet.getString("name")+"======");
				context.getLogger().log(resultSet.getString("id") + "====" + resultSet.getString("name") + "======");
				return output;
			}
		}  catch (SQLException e) { // TODO Auto-generated catch block
			  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
			  // TODO	  Auto-generated  catch block 
			  e.printStackTrace();
			  } 
		/*
		 * String query="select name from employeenew where id="+input+";";
		 * String abc="12345"; context.getLogger().log("Student Id:-"
		 * +input+"context"+context+"----");
		 */
		return null;
	}

	public String test(String input){ 
		//String query = null;
		try {
	 
	  String query = "select * from timepass where id="+input;
	 Class.forName("com.mysql.jdbc.Driver"); 
	 String url = System.getenv("DB_URL");//"jdbc:mysql://timepass.cl3m2q8sxt0y.us-west-2.rds.amazonaws.com/employee1";
	 
	 Connection con = DriverManager.getConnection(
				"jdbc:mysql://employee1.cl3m2q8sxt0y.us-west-2.rds.amazonaws.com/Employee1", "Employee1",
				"Employee1"); 
	 //here sonoo is	  database name, root is username and password 
	 System.out.println("Test Started"); 
	 Statement statement=con.createStatement();
	  resultSet=statement.executeQuery(query); if(resultSet.next()){
	  System.out.println(resultSet.getString("id")+"===="+resultSet.getString(
	  "name")+"======"); return resultSet.getString("name"); }
	  
	  } catch (SQLException e) { // TODO Auto-generated catch block
	  e.printStackTrace();
	  } catch (ClassNotFoundException e) {
		  // TODO	  Auto-generated  catch block 
		  e.printStackTrace();
		  } 
	return null; }

	public static void main(String args[]) {
		Hello hello = new Hello();
		System.out.println("database connection Established");
		String id = "1";
		String abc = hello.test(id);
		System.out.println("Successfull:-" + abc);
	}

}
