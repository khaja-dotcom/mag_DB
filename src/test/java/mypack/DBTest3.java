package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest3
{
	public static void main(String[] args) throws Exception
	{
		//Connect to DB 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","magnitia");   
		//Assign a SQL query to DB and display result
		Statement stmt=con.createStatement();  
		stmt.executeUpdate("update employees set salary=0.0 where emp_id=5;");
		//Assign a SQL query to DB and display result 
		ResultSet rs=stmt.executeQuery("select * from employees where emp_id=5;");
		while(rs.next())  
		{
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+" "+rs.getFloat(4));
		}
		//disconnect from DB
		con.close(); 
	}
}
