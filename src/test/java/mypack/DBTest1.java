package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest1
{
	public static void main(String[] args) throws Exception
	{
		//Connect to DB  
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/empdb","root","magnitia");   
		//Assign a SQL query to DB and display result
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from employees;");
		while(rs.next())  
		{
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+" "+
		                                                                 rs.getFloat(4));
		}
		//disconnect from DB
		con.close(); 
	}
}






