package mypack;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DBTest6
{
	public static void main(String[] args) throws Exception
	{
		//Open excel file in Read mode
		File f=new File("E:\\restbatch\\org.magnitia.DB\\Book1.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		//Go to Sheet1
		Sheet sh=wb.getSheet("Sheet1");
		//Get count of used rows and columns
		int nour=sh.getPhysicalNumberOfRows();
		int nouc=sh.getRow(0).getLastCellNum();
		//Connect to DB 
		Connection con=DriverManager.getConnection(
		         "jdbc:mysql://localhost:3306/empdb","root","magnitia");   
		Statement stmt=con.createStatement();  
		//looping from 2nd row(1st row had names of columns)
		for(int i=1;i<nour;i++)
		{
			int x=(int) sh.getRow(i).getCell(0).getNumericCellValue();  //for emp_id
			String y=sh.getRow(i).getCell(1).getStringCellValue(); //for emp_name
			int z=(int)sh.getRow(i).getCell(2).getNumericCellValue(); //for performance
			float w=(float)sh.getRow(i).getCell(3).getNumericCellValue(); //for percentage
			stmt.executeUpdate("insert into employees values("+x+",'"+y+"',"+z+","+w+");");
		}
		//Get and display data in DB table
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
