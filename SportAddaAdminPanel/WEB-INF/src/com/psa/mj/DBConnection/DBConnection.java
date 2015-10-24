package com.psa.mj.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
	public Connection con	=	null;
/* ----------------------------------------------------------------------------------- 
	Method		:- connect()
	Return Type :- Connection 
	Purpose		:- Connection to Database with related userName and password
 ----------------------------------------------------------------------------------- */
public Connection connect()
{
	//For Live use
	//String userName="";
	//String password="";
	
	// For Local use
	String userName="root";
	String password="";
	try 
	{
		//String driverName = "org.gjt.mm.mysql.Driver";
		String driverName	=	"com.mysql.jdbc.Driver";
		Class.forName(driverName);
	}
	 catch(Exception e) 
	{
	 	System.out.println("error in loading driver "+e);
	}
	 
	try
	{
		String serverName = "localhost:3306";
		//String serverName = "208.88.225.129:3306";
		//String serverName = "jdz2.dailyrazor.com:3306";
		String mydatabase = "sportaddadb";						
		
		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
		
		con = DriverManager.getConnection(url, userName, password);
		
	}
	catch (Exception e)
	{
		System.out.println("Error in DBConnect.connect() :- " + e);
	}
	return con;
}

/* ----------------------------------------------------------------------------------- 
	
	Method		:- Disconnect
	Return Type :- void
	Purpose		:- Disconnect the Connection with Database.


 ----------------------------------------------------------------------------------- */

public  void Disconnect()
{

	try
	{
		if(con != null)
		{
			con.close();
		}
	}catch(SQLException s)
	{
		System.out.println("Error in DBConnect.Disconnect() :- "+s);
	}
}

}
	