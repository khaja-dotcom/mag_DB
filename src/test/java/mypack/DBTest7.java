package mypack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DBTest7
{
	public static void main(String[] args) throws Exception
	{
		//Connect to DB 
		Connection con=DriverManager.getConnection(
		         "jdbc:mysql://localhost:3306/empdb","root","magnitia");   
		Statement stmt=con.createStatement(); 
		//Execute select statement to get data from DB into Result set
		ResultSet rs=stmt.executeQuery("select * from employees;");
		//Open excel file in read mode
		File f=new File("E:\\restbatch\\org.magnitia.DB\\Book1.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		//Goto Sheet2(Empty sheet/Unused sheet)
		Sheet sh=wb.getSheet("Sheet2");
		//Create headers for columns in 1st row
		sh.createRow(0);
		sh.getRow(0).createCell(0).setCellValue("Employee ID");
		sh.getRow(0).createCell(1).setCellValue("Employee Name");
		sh.getRow(0).createCell(2).setCellValue("Performance");
		sh.getRow(0).createCell(3).setCellValue("Salary");
		//looping to copy data from result set and paste in excel
		int rowindex=1; //2nd row
		while(rs.next())
		{
			sh.createRow(rowindex);
			sh.getRow(rowindex).createCell(0).setCellValue(rs.getInt(1));
			sh.getRow(rowindex).createCell(1).setCellValue(rs.getString(2));
			sh.getRow(rowindex).createCell(2).setCellValue(rs.getInt(3));
			sh.getRow(rowindex).createCell(3).setCellValue(rs.getFloat(4));
			rowindex++;
		}
		//Open same excel file in Write mode and Save that file
		FileOutputStream fo=new FileOutputStream(f);
		sh.autoSizeColumn(0);
		sh.autoSizeColumn(1);
		sh.autoSizeColumn(2);
		sh.autoSizeColumn(3);
		wb.write(fo); //save
		wb.close();
		fo.close();
		fi.close();
	}
}









