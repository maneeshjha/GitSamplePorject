package com.psa.mj.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.struts.upload.FormFile;

import com.psa.mj.DBConnection.DBConnection;

public class CommonBean {

	
	public static boolean checkDuplicate(String tablename, String fieldname, String fieldvalue)
	{
		DBConnection	dbConn = new DBConnection();
		
		Connection con = dbConn.connect();
		try	
		{
			String 		query	=	"select " + fieldname + " from " + tablename + " where " + fieldname + "='"+ fieldvalue + "'";
			Statement 	st		=	con.createStatement();
			ResultSet 	result	=	st.executeQuery(query);
			while(result.next()) 
			{
				if((result.getString(fieldname)).equals(fieldvalue))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonBean.checkDuplicate():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.checkDuplicate():- "+s);
			}
		}		
		return true;
	}
	
	
	public static boolean checkDuplicateForModification(String tablename, String field1name, String field1value, String field2name, String field2value)
	{
		DBConnection	dbConn 	= new DBConnection();
		Connection 		con 	= dbConn.connect();
		try	
		{
			
			String 		query	=	"	select	" + field1name + " " +
									"	from	" + tablename + " " +
									"	where	" + field1name + "	=	'"+ field1value + "'" +
									"	and		" + field2name + "	<>	'"+ field2value + "'";
			Statement 	st		=	con.createStatement();
			ResultSet 	result	=	st.executeQuery(query);
			
			while(result.next()) 
			{
				if((result.getString(field1name)).equals(field1value))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonBean.checkDuplicateForModification : "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.checkDuplicateForModification : "+s);
			}
		}		
		return true;
	}
	
	public  static	int getCountFromTable(String tableName, String retfieldName,String fieldName,String fieldValue)
	{
		DBConnection	dbConn 	= new DBConnection();
		Connection 		con 	= dbConn.connect();
		int 			retVal 	= 0;
		try	
		{
			
			String 		query	=	"select count(" + retfieldName + ") from " + tableName + " where "+fieldName+" = "+fieldValue;
			Statement 	st		=	con.createStatement();
			ResultSet 	result	=	st.executeQuery(query);
			while(result.next()) 
			{
				retVal	=	result.getInt(1); 
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonBean.getCountFromTable :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.getCountFromTable : "+s);
			}
		}		
		return retVal;
	}
	
	public  static	int getMaxFromTable(String tableName, String retfieldName)
	{
		DBConnection	dbConn 	= new DBConnection();
		Connection 		con 	= dbConn.connect();
		int 			retVal 	= 0;
		try	
		{
			
			String 		query	=	"select max(" + retfieldName + ") from " + tableName;
			Statement 	st		=	con.createStatement();
			ResultSet 	result	=	st.executeQuery(query);
			while(result.next()) 
			{
				retVal	=	result.getInt(1); 
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonBean.getMaxFromTable :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.getMaxFromTable : "+s);
			}
		}		
		return retVal;
	}
	/**
	 * Method Name	: 	applyFormatter
	 * purpose		: 	To apply Formatter
	 * @author  	:	Ashay
	 */
	public static String applyFormatter(String value)
	{
		String retVal = value;
		NumberFormat formatter 	= 	new DecimalFormat("#0.00");
		if (value !=null && value.length()>0 )
		{
			try
			{
				 double temp  		= 	Double.valueOf(value.trim()).doubleValue();					
				 retVal				=	formatter.format(temp); 
			}catch(Exception e)
			{	
				retVal = value;
			}
		}else
		{
			retVal	=	"-";
		}
		
		return retVal;
	}
	
	public  static	boolean containValue(String value,String condition)
	{
		boolean flag	=	false;
		try
		{
			flag	=	value.contains(condition); 			
		}
		catch(Exception e)
		{
			flag	=	false;
		}
		return flag;
	}
	public static String getProperDateFormat(String inDate)
	{
		
		if ((inDate!=null && inDate.length()==34) || (inDate!=null && inDate.length()==28))
		{
			inDate=inDate.substring(4,10) +" "+ inDate.substring(inDate.length()-4,inDate.length());
		}else
		{
			inDate="";
		}
		
		return inDate;
	}
	
	
	/**
	 * Method Name 	: getCellValue
	 * Purpose		: To read Cell Value Of Excel File
	 * @author		: Ashay
	 * @param cell		: HSSFCell cell
	 * @param format 	: expected format values are : string , date , long , double (these are expected format if we want to read string value from cell then pass string to this function)
	 * Note : this function will return value in desired format but if format mismatched then it will return original value witch had read. 
	 */
	
	public static String getExcelCellValue(HSSFCell cell, String format)
	{
		String 	value	=	"";
		int 	type	=	0;
		try
		{
			type	=	cell.getCellType();
			
			switch(type)
			{
				//( 0 )
				case HSSFCell.CELL_TYPE_NUMERIC :  
						 if(format != null && format.equalsIgnoreCase("date"))
						 {
							 value=""+cell.getDateCellValue();
						 }else
						 {
							value=""+cell.getNumericCellValue();
						 }
						 break;
				//( 1 )
				case HSSFCell.CELL_TYPE_STRING  : value=""+cell.getStringCellValue();
				  		 break;
				//( 3 )
				case HSSFCell.CELL_TYPE_BLANK   :  value="";
						 break;
				//( 4 )
				case HSSFCell.CELL_TYPE_BOOLEAN : value = ""+cell.getBooleanCellValue();
						 break;		 
								 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		value = value == null ? "" : value.trim();
		
		if(value != null && value.length() > 0)
		{
			// if we need alpha numeric format
			if(format != null && format.equalsIgnoreCase("string"))
			{
				try {
					// i.e. cell value may be double 
					double val = Double.parseDouble(value);
					value = CommonBean.applyFormatter(""+val);
					
					// remove if .0*  
					if(value.contains(".")) //contains . 
					{	
						// find value after last point 
						String pointValue	=	value.substring(value.indexOf(".")+1,value.length());	
						
						// if that value is 0 so after last point there is all 0's
						if(Long.parseLong(pointValue)== 0)
						{
							//remove last 0's
							value	=	value.substring(0, value.indexOf("."));			
						}						
					}
				} catch (Exception e) {
					
				}
			}
			
			if(format != null && format.equalsIgnoreCase("excatString"))
			{
				try {
						if(type != HSSFCell.CELL_TYPE_STRING)
							value	=	"";	
				} catch (Exception e) {
					
				}
			}
			// if we need date format value
			if(format != null && format.equalsIgnoreCase("date"))			
			{
				String oldVal = value;
				try {
					SimpleDateFormat sdf			=	new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdf1			=	new SimpleDateFormat("MMM/dd/yyyy");
					
					
					if(type == 0)
					{
						value	=	CommonBean.getProperDateFormat(value);
						Date dt	=	new Date(value);
						value	=	sdf.format(dt);
					}else
					{
						if(value.length()==34 || value.length()==28)
						{
							value	=	CommonBean.getProperDateFormat(value);
							value	=	value.replace(" ", "/");
							Date dt	=	sdf1.parse(value);
							value	=	sdf.format(dt);
						}else if(!value.equals(""))
						{
							value	=	value.replace(".", "/");
							Date dt	=	sdf.parse(value);
							value	=	sdf.format(dt);
						}
					}
				} catch (ParseException e) {
					value = oldVal;
				}
			}
			
			// if we need whole number format value
			if(format != null && format.equalsIgnoreCase("whole"))			
			{
				String pointValue;
				try {
					
					
					double doubleVal = Double.parseDouble(value);
					value = ""+doubleVal;	
					value	=	applyFormatter(value);
					// remove .0  
					if(value.contains(".")) 
					{		
						pointValue	=	value.substring(value.indexOf(".")+1,value.length());	
						if(Long.parseLong(pointValue)== 0)
						{
							value	=	value.substring(0, value.indexOf("."));			
						}				
					}	
					//BigDecimal val = BigDecimal.valueOf(Double.parseDouble(value));
					BigDecimal val = BigDecimal.valueOf(Long.parseLong(value));  // Change By Vitthal Double Is Replaced By Long.
					value = ""+val;
					
				} catch (Exception e) {
					System.out.println("Exception In Whole Number Case Of Get Excel Cell Value :"+ e);
					
				}
			}
			// if we need double format value
			if(format != null && format.equalsIgnoreCase("double"))			
			{
				try {
					
					double val = Double.parseDouble(value);
					value = CommonBean.applyFormatter(""+val);
					
				} catch (Exception e) {
					
				}
			}
			
		}
		
		
		return value;
	}
	
	/**
	 * Method Name 	: isProperFormat
	 * Purpose		: To check that value is of desired format or not 
	 * @author		: Ashay
	 * @param value  : value to compare format
	 * @param format : format for comparison 
	 * Date          : 16/06/2011
	 * Note : this function is useful to compare format of given value if it is in desired format then it will return true otherwise false 
	 * expected format values are ( string , date , long , double )
	 */
	
	public static boolean	isProperFormat(String value,String format)
	{
		boolean	result	=	false;
		String pointValue	;
		
		if(format != null && format.equalsIgnoreCase("whole"))
		{
			try
			{
				double doubleVal = Double.parseDouble(value);
				value = ""+doubleVal;		
				value	=	applyFormatter(value);
				// remove .0  
				if(value.contains(".")) 
				{	
					pointValue	=	value.substring(value.indexOf(".")+1,value.length());	
					if(Long.parseLong(pointValue)== 0)
					{
						value	=	value.substring(0, value.indexOf("."));			
					}
				}				
				BigDecimal val = BigDecimal.valueOf(Long.parseLong(value));    // Change bY Vitthal Double is Replaced By Long
				result	=	true;
				
			}catch (Exception e) {
				result	=	false;
			}
			
		}else if(format != null && format.equalsIgnoreCase("double"))
		{
			try
			{
				double	val		=	Double.parseDouble(value);
						result	=	true;
				
			}catch (Exception e) {
				result	=	false;
			}
		}else if(format != null && format.equalsIgnoreCase("date"))
		{
			try
			{
				Date dt = new Date(value);
				result	=	true;
				
			}catch (Exception e) {
				result	=	false;
			}
		}
		
		return	result;
	}
	
	/**
	 * Method Name 	: 	getDate
	 * Purpose		: 	To get Today's Date 
	 * @author		:	Ashay
	 * Date			:	25/07/2011
	 */
	public static String getDate()
	{		
		Date 		date 		= Calendar.getInstance().getTime(); 
		DateFormat 	formatter 	= new SimpleDateFormat("dd/MM/yyyy");		
		return formatter.format(date);
	}
	
	/**
	 * Method Name 	: 	getDataOnOneCond
	 * Purpose		: 	To getDataOnOneCond
	 * @author		:	Vitthal
	 * Date			:	11/08/2011
	 */
	public static  String getDataOnOneCond(String tableName,String fieldName,String fieldValue, String retfieldName)
	{
		DBConnection	dbConn = new DBConnection();
		
		Connection con =null;
		String retVal = "";
		try	
		{
			con = dbConn.connect();
			String query="select " + retfieldName + " from " + tableName + " where " + fieldName + "='"+ fieldValue + "'";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			while(result.next()) 
			{
				retVal=result.getString(1);
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonBean.getDataOnOneCond():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.getDataOnOneCond():- "+s);
			}
		}		
		return retVal;
	}	
	
	/**
	 * Method Name 	: 	getDataOnOneCond
	 * Purpose		: 	To getDataOnOneCond
	 * @author		:	Ashay
	 * Date			:	24/08/2012
	 */
	public static  String getDataOnTwoCond(String tableName,String fieldName, String fieldValue, String fieldName1, String fieldValue1, String retfieldName)
	{
		DBConnection	dbConn 	= 	new DBConnection();		
		Connection 		con 	=	null;
		String 			retVal 	= 	"";
		try	
		{
			con = dbConn.connect();
			String query =	"	SELECT " + retfieldName + " " +
							"	FROM " + tableName + " " +
							"	WHERE " + fieldName + "='"+ fieldValue + "'" +
							"	AND " + fieldName1 + "='"+ fieldValue1 + "'";
			
			Statement st		=	con.createStatement();
			ResultSet result	=	st.executeQuery(query);
			
			if(result.next()) 
			{
				retVal	=	result.getString(1);
			}
		}catch(Exception e)	
		{
			retVal 	= 	"";
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonBean.getDataOnTwoCond : "+s);
			}
		}		
		return retVal;
	}
	
	public static int getRandomNo()
	{
		Random 	randomGenerator  = new Random();
		int 	randomInt           = randomGenerator.nextInt(99999);
		return	randomInt;	
	}
	
	public static boolean uploadFile(FormFile uploadedFile, String destPath)
	 {
		boolean	result	=	false;
		if(uploadedFile != null)
		 {
				try
				{	
					
					InputStream		inputStream			=	uploadedFile.getInputStream();
					OutputStream	fileOutputStream	=	new FileOutputStream(destPath);
					byte[] b	=	new byte[50000];				
			    	int len;
			    	while ((len = inputStream.read(b)) > 0)
			    	{
			    		fileOutputStream.write(b, 0, len);
			    	}
			    	inputStream.close();
			      	fileOutputStream.close();	
			      	
			      	result	=	true;
				}catch (Exception e) {					
					result	=	false;
					System.out.println("Error in closing connection in CommonMethods.uploadFile():- "+e);
				}
		}
	    return result;
	 }
	
	/**
	 * Method Name 	: 	createFolderStructureFirstTime()
	 * Purpose		: 	To createFolderStructureFirstTime
	 * Created By	:	vitthal
	 * Created On	:	01/08/2011
	 */
		public static boolean	createFolderStructureFirstTime(String mainFolder,String indentQuotationFolder)
		{
				
			boolean	result			=	true;
			try 
			{
				File	directory		= 	new File(mainFolder)	;
				if (!directory.exists())
				{
					directory.mkdir();			
				} 
				// Creating  Directory of name folderName
				String	folderPath				=	mainFolder+"/"+indentQuotationFolder;
				File	projfolder		= 	new File(folderPath)	;
				if (!projfolder.exists())
				{
					projfolder.mkdir();			
				}
								
			} 
			catch (Exception e) 
			{ 
				result	=	false;
				System.out.println("Exception in CommonBean.createFolderStructureFirstTime() :- "+e);

			}			
			return result;
		}
		
		public static boolean copyfile(String srFile, String dtFile)
		 {
			boolean	result	=	false;
		    try{
				      File f1 = new File(srFile);				      
				      File f2 = new File(dtFile);
				      InputStream in = new FileInputStream(f1);		      
				      OutputStream out = new FileOutputStream(f2);
				
				      byte[] buf = new byte[50000];
				      int len;
				      while ((len = in.read(buf)) > 0){
				        out.write(buf, 0, len);
				      }
				      in.close();
				      out.close();
				      result	=	true;
				      
		    } catch(IOException e){
		    	result	=	false;
		      System.out.println(e.getMessage());      
		    }
		    return result;
		 }
	
		/**
		 * Method Name 	: 	isDataPresent
		 * Purpose		: 	To isDataPresent
		 * @author		:	Gaurav
		 * Date			:	19/12/2011
		 */
		public static  boolean isDataPresent(String tableName,String fieldName,String fieldValue, String retfieldName)
		{
			DBConnection	dbConn  	=   new DBConnection();			
			Connection 		con 		=   null;
			boolean 		retVal  	=   false;
			try	
			{
				con 			 =  dbConn.connect();
				String query	 =  "	select " + retfieldName +
									" 	from   " + tableName + 
									"   where  " + fieldName + "='"+ fieldValue + "'";
				Statement st	 =  con.createStatement();
				ResultSet result =  st.executeQuery(query);
				while(result.next())
				{
					retVal		=	true;
				}
			}
			catch(Exception e)	
			{
				System.out.println("Error in CommonBean.isDataPresent():- "+e);
			}
			finally 
			{
				try
				{
					if(con != null)
						con.close();
				}
				catch(Exception s)
				{
					System.out.println("Error in closing connection in CommonBean.isDataPresent():- "+s);
				}
			}		
			return retVal;
		}	
}
