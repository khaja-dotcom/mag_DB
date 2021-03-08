package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest2 {

	public static void main(String[] args) throws Exception
	{
		//Connect to DB  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","magnitia");   
		//Assign a SQL query to DB and display result
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select emp_id, emp_name, e.performance, salary, percentage from employees e, merits m where e.performance=m.performance order by emp_id;");
		while(rs.next())  
		{
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+" "+rs.getFloat(4)+" "+rs.getFloat(5));
		}
		//disconnect from DB
		con.close(); 
	}
}
