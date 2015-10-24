package com.psa.mj.Bean;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.struts.upload.FormFile;

import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DBConnection.DBConnection;

public class CommonMethodBean
{
		public static String dbusername;	//uname;
		public static String dbuserpass;	//upass;
		public String username;				//uname;
		public static String userpass;		//upass;
		public static String usertype;		//upass;
		public static String userid;		//upass;
		DBConnection dbConn = new DBConnection();
		
		/**
		 * 	Method	=	getStatusWiseCountryName
		 * 	Purpose	=	To getStatusWiseCountryName
		 * 	Author	=	Vitthal	
		 * 	Date	=	24/01/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatusWiseCountryName(String countryName,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT     COUNTRYID,COUNTRY_NAME " +
						"	FROM       EXP_COUNTRYMST " +
						"	WHERE      0 = 0 ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND        STATUS       =   '"+status+"'	";
			}
			if(countryName != null && countryName.length()>0)
			{
				query	+=	"	AND        COUNTRY_NAME     LIKE    '"+countryName+"%'	";
			}
			query	+=	"	ORDER BY   COUNTRY_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));
				commonForm.setField2(rs.getString(2));
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatusWiseCountryName");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatusWiseCountryName");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		/**
		 * 	Method	=	getStatus_CountryWiseStateName
		 * 	Purpose	=	To getStatus_CountryWiseStateName
		 * 	Author	=	Vitthal	
		 * 	Date	=	31/01/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatus_CountryWiseStateName(String stateName, String countryId,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      SM.STATEID,SM.STATE_NAME " +
						"	FROM        EXP_STATEMST    SM " +
						"	WHERE		0 = 0 ";
			if(countryId != null && countryId.length()>0)
			{
				query	+=	"	AND SM.COUNTRYID    =   '"+countryId+"'	";
			}
			if(stateName != null && stateName.length()>0)
			{
				query	+=	"	AND SM.STATE_NAME   LIKE    '"+stateName+"%'	";
			}
			if(status != null && status.length()>0)
			{
				query	+=	"	AND SM.STATUS       =   '"+status+"'	";
			}
		
			query	+=	"	ORDER BY    STATE_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//SM.STATEID
				commonForm.setField2(rs.getString(2));//SM.STATE_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatus_CountryWiseStateName");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatus_CountryWiseStateName");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		/**
		 * 	Method	=	getStatus_Country_StateWiseCityName
		 * 	Purpose	=	To getStatus_Country_StateWiseCityName
		 * 	Author	=	Vitthal	
		 * 	Date	=	01/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatus_Country_StateWiseCityName(String cityName, String stateId, String countryId,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT  CIM.CITYID,CIM.CITY_NAME " +
						"	FROM    EXP_CITYMST     CIM " +
						"	WHERE   0 = 0 ";
			if(countryId != null && countryId.length()>0)
			{
				query	+=	"	AND CIM.COUNTRYID    =   '"+countryId+"'	";
			}
			if(stateId != null && stateId.length()>0)
			{
				query	+=	"	AND CIM.STATEID      =   '"+stateId+"'	";
			}else
			{
				query	+=	"	AND CIM.STATEID      IS     NULL";
			}
			if(cityName != null && cityName.length()>0)
			{
				query	+=	"	AND CIM.CITY_NAME   LIKE    '"+cityName+"%'	";
			}
			if(status != null && status.length()>0)
			{
				query	+=	"	AND CIM.STATUS       =   '"+status+"'	";
			}
		
			query	+=	"	ORDER BY    CIM.CITY_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//CIM.CITYID
				commonForm.setField2(rs.getString(2));//CIM.CITY_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatus_Country_StateWiseCityName");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatus_Country_StateWiseCityName");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		/**
		 * 	Method	=	getStatusWiseCurrencyName
		 * 	Purpose	=	To getStatusWiseCurrencyName
		 * 	Author	=	Vitthal	
		 * 	Date	=	02/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatusWiseCurrencyName(String currencyName,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      CURRENCYID,CURRENCY_NAME " +
						"	FROM        EXP_CURRENCYMST " +
						"	WHERE       0=0 ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND        STATUS       =   '"+status+"'	";
			}
			if(currencyName != null && currencyName.length()>0)
			{
				query	+=	"	AND        CURRENCY_NAME     LIKE    '"+currencyName+"%'	";
			}
			query	+=	"	ORDER BY   CURRENCY_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//CURRENCYID
				commonForm.setField2(rs.getString(2));//CURRENCY_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatusWiseCurrencyName");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatusWiseCountryName");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		
		
	/**
	 * Action Name 	: getStatusWiseRoleNameList
	 * Purpose		: To Get Status Wise Role Name List
	 * @author		: Ashay
	 * Date			: 02/02/2012
	 */	
	public ArrayList<CommonForm> getStatusWiseRoleNameList(String roleName, String status) 
	{
		ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
		Connection 				con			=	null;
		Statement 				st			=	null;
		ResultSet 				rs			=	null;
		String 					query		=	"";
		try
		{
			
			query		=	"	SELECT RLM.ROLEID,RLM.ROLE_NAME" +
							"	FROM    EXP_ROLEMST RLM" +
							"	WHERE   0=0";
			
			if(status != null && !status.equals("ALL"))
			{
				query	+=	"	AND     RLM.STATUS      =	'"+status+"'";
			}
			if(roleName != null && roleName.length() > 0)
			{
				query	+=	"	AND     RLM.ROLE_NAME   LIKE	'"+roleName+"%'";
			}						
			query		+=	"	ORDER BY RLM.ROLE_NAME";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);		
			while(rs.next())
			{				
				CommonForm cForm	=	new CommonForm();				
				cForm.setField1(rs.getString(1));//ROLEID
				cForm.setField2(rs.getString(2));//ROLE_NAME
				
				dataList.add(cForm);
			}
			
		}catch (Exception e) {
			System.out.println("Error in CommonMethodBean.getStatusWiseRoleNameList : "+e);
		}finally
		{
			try {
				if(con!=null)
				{
					con.close();
					con=null;
				}
			} catch (Exception e2) {
				System.out.println("Error in CommonMethodBean.getStatusWiseRoleNameList : "+e2);
			}
		}
		return dataList;
	}
		
		
		/**
		 * 	Method	=	getStatusWiseAgentName
		 * 	Purpose	=	To getStatusWiseAgentName
		 * 	Author	=	Vitthal	
		 * 	Date	=	02/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatusWiseAgentName(String agentName,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      AGENTID, AGENT_NAME " +
						"	FROM        EXP_AGENTMST " +
						"	WHERE       0=0 ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND        STATUS       =   '"+status+"'	";
			}
			if(agentName != null && agentName.length()>0)
			{
				query	+=	"	AND        AGENT_NAME     LIKE    '"+agentName+"%'	";
			}
			query	+=	"	ORDER BY   AGENT_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//AGENTID
				commonForm.setField2(rs.getString(2));//AGENT_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatusWiseAgentName");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatusWiseAgentName");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		/**
		 * 	Method	=	getStatusWiseGroupNameList
		 * 	Purpose	=	To getStatusWiseGroupNameList
		 * 	Author	=	Vitthal	
		 * 	Date	=	03/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatusWiseGroupNameList(String groupName,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      GROUPID,GROUP_NAME " +
						"	FROM        EXP_GROUPMST " +
						"	WHERE       0=0 ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND        GROUP_STATUS       =   '"+status+"'	";
			}
			if(groupName != null && groupName.length()>0)
			{
				query	+=	"	AND        GROUP_NAME     LIKE    '"+groupName+"%'	";
			}
			query	+=	"	ORDER BY   GROUP_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//GROUPID
				commonForm.setField2(rs.getString(2));//GROUP_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatusWiseGroupNameList");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatusWiseGroupNameList");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		/**
		 * 	Method	=	getStatus_GroupWiseGroupValue
		 * 	Purpose	=	To getStatus_GroupWiseGroupValue
		 * 	Author	=	Vitthal	
		 * 	Date	=	03/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatus_GroupWiseGroupValue(String groupValue ,String groupId,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      CM.GROUPVALUEID, CM.GROUPVALUE_NAME " +
						"	FROM        EXP_COMMONMST CM " +
						"	WHERE       0 = 0 ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND         CM.STATUS       	=   	'"+status+"'	";
			}
			if(groupId != null && groupId.length()>0)
			{
				query	+=	"	AND 		CM.GROUPID          =       '"+groupId+"'	";
			}
			if(groupValue != null && groupValue.length()>0)
			{
				query	+=	"	AND        CM.GROUPVALUE_NAME     LIKE    '"+groupValue+"%'	";
			}
			query	+=	"	ORDER BY   CM.GROUPVALUE_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//CM.GROUPVALUEID
				commonForm.setField2(rs.getString(2));//CM.GROUPVALUE_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatus_GroupWiseGroupValue");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatus_GroupWiseGroupValue");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		
		/**
		 * 	Method	=	getGroupDataType
		 * 	Purpose	=	To getGroupDataType
		 * 	Author	=	Vitthal	
		 * 	Date	=	03/02/2012
		 */
/**************************************************************************************************************************************/		
		public String 	getGroupDataType(String groupId)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			
			try
			{
			
			query	=	"	SELECT      GM.GROUP_DATATYPE " +
						"	FROM        EXP_GROUPMST GM " +
						"	WHERE       GM.GROUPID  =   '"+groupId+"' ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			if(rs.next())
			{
				outputResult	=	rs.getString(1) != null && rs.getString(1).length()>0 ? rs.getString(1) : "";//GM.GROUP_DATATYPE
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getGroupDataType");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getGroupDataType");
					e.printStackTrace();
				}
			}
			
			return outputResult;
		}
		
		/**
		 * Method Name 	: getStatusWiseUserNameList
		 * Purpose		: To Get Status Wise User Name List
		 * @author		: Ashay
		 * Date			: 03/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseUserNameList(String userName,String status) {
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				
				query		=	"	SELECT      USER_ID,USER_NAME" +
								"	FROM        EXP_USERMST" +
								"	WHERE       0=0";
				
				if(status != null && !status.equals("ALL"))
				{
					query	+=	"	AND         UM.STATUS       =		'"+status+"'";
				}
				if(userName != null && userName.length() > 0)
				{
					query	+=	"	AND         USER_NAME   	LIKE	'"+userName+"%'";
				}
				query		+=	"	ORDER BY    USER_NAME";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					
					CommonForm cForm	=	new CommonForm();									
					cForm.setField1(rs.getString(1));//USER_ID		
					cForm.setField2(rs.getString(2));//USER_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseUserNameList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseUserNameList : "+e2);
				}
			}
			return dataList;
		}
		
		/**
		 * 	Method	=	getStatus_GroupNameWiseGroupValue
		 * 	Purpose	=	To getStatus_GroupNameWiseGroupValue
		 * 	Author	=	Vitthal	
		 * 	Date	=	03/02/2012
		 */
/**************************************************************************************************************************************/		
		public ArrayList<CommonForm> getStatus_GroupNameWiseGroupValue(String groupValue ,String groupName,String status)
/**************************************************************************************************************************************/		
		{
			String 		outputResult	=	"ERROR";
			Connection 	con				=	null;
			Statement	st				=	null;
			ResultSet	rs				=	null;
			String		query			=	"";
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			
			try
			{
			
			query	=	"	SELECT      CM.GROUPVALUEID, CM.GROUPVALUE_NAME " +
						"	FROM        EXP_COMMONMST CM " +
						"	INNER JOIN  EXP_GROUPMST GM     ON  GM.GROUPID          =       CM.GROUPID ";
			if(status != null && status.length()>0)
			{
				query	+=	"	AND         CM.STATUS       		=   	'"+status+"'	";
			}
			if(groupName != null && groupName.length()>0)
			{
				query	+=	"	AND 		GM.GROUP_NAME          	=       '"+groupName+"'	";
			}
			if(groupValue != null && groupValue.length()>0)
			{
				query	+=	"	AND        CM.GROUPVALUE_NAME     LIKE    '"+groupValue+"%'	";
			}
			query	+=	"	ORDER BY   CM.GROUPVALUE_NAME ";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//CM.GROUPVALUEID
				commonForm.setField2(rs.getString(2));//CM.GROUPVALUE_NAME
				dataList.add(commonForm);
			}
			
			}catch (Exception e) 
			{
				outputResult	=	"ERROR";
				System.out.println("Exception in CommonMethodBean.getStatus_GroupNameWiseGroupValue");
				e.printStackTrace();
				
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e) 
				{
					outputResult	=	"ERROR";
					System.out.println("Exception while Closing Connection in CommonMethodBean.getStatus_GroupNameWiseGroupValue");
					e.printStackTrace();
				}
			}
			
			return dataList;
		}
		public String	getDate()
		{
			
			Date date 					= Calendar.getInstance().getTime(); 
			DateFormat formatter 		= new SimpleDateFormat("dd/MM/yyyy");
			//DateFormat formatter 		= new SimpleDateFormat("yyyy-MM-dd");
			String today 				= formatter.format(date);
			
			//Date dateBefore 			=	 new Date(date.getTime() - 1 * 24 * 3600 * 1000 );
			//String	prevDate			=	formatter.format(dateBefore);
			return today;
		}
		/*-------------------------------------------------------------------------------
		 * Method to apply number formatter, e.g. 10.00 
		 * applyFormater()
		 * Return Type String
		--------------------------------------------------------------------------------*/
		public String applyFormater(String value)
		{
				NumberFormat formatter 	= 	new DecimalFormat("#0.00");
				if (value !=null && value.length()>0 )
				{
					try
					{
						 double temp  		= 	Double.valueOf(value.trim()).doubleValue();					
						 value				=	formatter.format(temp); 
					}catch(Exception e)
					{
						value				=	value;
					}
				}else
				{
					value	=	"-";
				}
				
				return value;
		}
		
		/**
		 * Method Name  : getStatusWiseSRNameList
		 * Purpose		: To Get Status Wise SR Name List
		 * @author		: Ashay
		 * Date			: 04/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseSRNameList(String srName, String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      SRID, SR_NAME" +
								"	FROM        EXP_SRMST " +
								"	WHERE       0=0";
				if(status != null && !status.equals("ALL"))
				{
					query	+=	"	AND         STATUS  	=   	'"+status+"'";
				}
				if(srName != null && srName.length() > 0)
				{
					query	+=	"	AND         SR_NAME 	LIKE   	'"+srName+"%'";
				}
				
				query		+=	"	ORDER BY    SR_NAME";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//SRID
					cForm.setField2(rs.getString(2));//SR_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseSRNameList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseSRNameList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getStatusWiseCustomerNameList
		 * Purpose		: To Get Status Wise Customer Name List
		 * @author		: Vitthal
		 * Date			: 06/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseCustomerNameList(String customerName, String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      CUSTOMERID,CUSTOMER_NAME " +
								"	FROM        EXP_CUSTOMERMST " +
								"	WHERE       0=0 ";
				if(status != null && status.length()>0 && !status.equals("ALL"))
				{
					query	+=	"	AND         STATUS  	=   	'"+status+"'";
				}
				if(customerName != null && customerName.length() > 0)
				{
					query	+=	"	AND         CUSTOMER_NAME 	LIKE   	'"+customerName+"%'";
				}
				
				query		+=	"	ORDER BY    CUSTOMER_NAME ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//CUSTOMERID
					cForm.setField2(rs.getString(2));//CUSTOMER_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseCustomerNameList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseCustomerNameList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getCustomerWiseCollectionList
		 * Purpose		: To getCustomerWiseCollectionList
		 * @author		: Vitthal
		 * Date			: 06/02/2012
		 */
		public ArrayList<CommonForm> getCustomerWiseCollectionList(String customerId) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      CCM.COLLECTIONID,MT.MISCTRANS_VALUE COLLECTION " +
								"	FROM        EXP_CUST_COLL_MAPPING   CCM " +
								"	INNER JOIN  MISC_TRANS              MT  ON  MT.MISCTRANSID      =   CCM.COLLECTIONID " +
								"	                                        AND CCM.CUSTOMERID      =   '"+customerId+"' " +
								"	ORDER BY    COLLECTION  ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//CUSTOMERID
					cForm.setField2(rs.getString(2));//CUSTOMER_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getCustomerWiseCollectionList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getCustomerWiseCollectionList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getStatusWiseCollectionList
		 * Purpose		: To Get Status Wise Collection Name List
		 * @author		: Vitthal
		 * Date			: 06/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseCollectionList(String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      MT.MISCTRANSID, MT.MISCTRANS_VALUE " +
								"	FROM        MISC_TRANS MT " +
								"	INNER JOIN  MISC_MASTER MM  ON  MM.MISCMSTID        =   MT.MISCMSTID " +
								"	                            AND MM.MISCMST_NAME     =   'COLLECTION' ";
				if(status != null && status.length()>0 && !status.equals("ALL"))
				{
					query	+=	"	AND MT.MISCTRANS_STATUS  	=   	'"+status+"'";
				}	
				query		+=	"	ORDER BY    MT.MISCTRANS_VALUE ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//MT.MISCTRANSID
					cForm.setField2(rs.getString(2));//MT.MISCTRANS_VALUE
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseCollectionList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseCollectionList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getStatusWiseQualityList
		 * Purpose		: To Get Status Wise Quality Name List
		 * @author		: Vitthal
		 * Date			: 07/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseQualityList(String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      QUALITYID,QUALITY_NAME " +
								"	FROM        QUALITYMST " +
								"	WHERE		0 = 0 ";
				if(status != null && status.length()>0 && !status.equals("ALL"))
				{
					query	+=	"	AND 	QUALITY_STATUS  	=   	'"+status+"'";
				}	
				query		+=	"	ORDER BY    QUALITY_NAME ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//QUALITYID
					cForm.setField2(rs.getString(2));//QUALITY_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseQualityList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseQualityList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getStatus_QualityWiseDesignList
		 * Purpose		: To getStatus_QualityWiseDesignList
		 * @author		: Vitthal
		 * Date			: 07/02/2012
		 */
		public ArrayList<CommonForm> getStatus_QualityWiseDesignList(String qualityId,String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      DM.DESIGNID,DM.DESIGN_NAME " +
								"	FROM        DESIGNMST DM " +
								"	INNER JOIN  PRODUCTMST  PM  ON  PM.DESIGNID     =   DM.DESIGNID  "; 
				if(qualityId != null && qualityId.length()>0)
				{
					query	+=	"	AND PM.QUALITYID    =   '"+qualityId+"'	";
				}
				if(status != null && status.length()>0 && !status.equalsIgnoreCase("ALL"))
				{
					query	+=	"	AND DM.DESIGN_STATUS = '"+status+"'	";
				}
				query	+=	"	GROUP BY    DM.DESIGNID,DM.DESIGN_NAME " +
							"	ORDER BY    DM.DESIGN_NAME ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//DM.DESIGNID
					cForm.setField2(rs.getString(2));//DM.DESIGN_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatus_QualityWiseDesignList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatus_QualityWiseDesignList : "+e2);
				}
			}
			return dataList;

		}
		/**
		 * Method Name  : getStatus_Quality_DesignWiseShadeList
		 * Purpose		: To getStatus_Quality_DesignWiseShadeList
		 * @author		: Vitthal
		 * Date			: 07/02/2012
		 */
		public ArrayList<CommonForm> getStatus_Quality_DesignWiseShadeList(String qualityId,String designId,String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			
			try
			{
				query		=	"	SELECT      PM.PRODUCTID,SM.SHADE_NAME " +
								"	FROM        SHADEMST SM " +
								"	INNER JOIN  PRODUCTMST  PM  ON  PM.SHADEID     =   SM.SHADEID  "; 
				if(qualityId != null && qualityId.length()>0)
				{
					query	+=	"	AND PM.QUALITYID    =   '"+qualityId+"'	";
				}
				if(designId != null && designId.length()>0)
				{
					query	+=	"	AND PM.DESIGNID    	=   '"+designId+"'	";
				}
				if(status != null && status.length()>0 && !status.equalsIgnoreCase("ALL"))
				{
					query	+=	"	AND SHADE_STATUS 	= '"+status+"'	";
				}
				query	+=	"	GROUP BY    PM.PRODUCTID,SM.SHADE_NAME " +
							"	ORDER BY    SM.SHADE_NAME ";
				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//PM.PRODUCTID
					cForm.setField2(rs.getString(2));//SM.SHADE_NAME
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatus_Quality_DesignWiseShadeList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatus_Quality_DesignWiseShadeList : "+e2);
				}
			}
			return dataList;

		}
		
		
		/**
		 * Method Name  : getStatusWiseCustomerCodeList
		 * Purpose		: To Get Status Wise Customer Code TTS List
		 * @author		: Ashay
		 * Date			: 09/02/2012
		 */
		public ArrayList<CommonForm> getStatusWiseCustomerCodeList(String customerCode, String status) 
		{
			ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
			Connection 				con			=	null;
			Statement 				st			=	null;
			ResultSet 				rs			=	null;
			String 					query		=	"";
			try
			{
				
				query		=	"	SELECT      CUSTOMERID,CUSTOMER_CODE" +
								"	FROM        EXP_CUSTOMERMST " +
								"	WHERE       0=0";
				if(status != null && !status.equals("ALL"))
				{
					query	+=	"	AND         STATUS          =	'"+status+"'";

				}	
				if(customerCode != null && customerCode.length() > 0)
				{
					query	+=	"	AND         CUSTOMER_CODE   LIKE	'"+customerCode+"%'";
				}
				query		+=	"	ORDER BY    CUSTOMER_CODE";

				
				con	=	dbConn.connect();
				st	=	con.createStatement();
				rs	=	st.executeQuery(query);		
				while(rs.next())
				{					
					CommonForm cForm	=	new CommonForm();				
					cForm.setField1(rs.getString(1));//CUSTOMERID
					cForm.setField2(rs.getString(2));//CUSTOMER_CODE
					
					dataList.add(cForm);
				}
				
			}catch (Exception e) {
				System.out.println("Error in CommonMethodBean.getStatusWiseCustomerCodeList : "+e);
			}finally
			{
				try {
					if(con!=null)
					{
						con.close();
						con=null;
					}
				} catch (Exception e2) {
					System.out.println("Error in CommonMethodBean.getStatusWiseCustomerCodeList : "+e2);
				}
			}
			return dataList;

		}
		
		
		
/**
 * Method Name  : getStatusWiseCourierNameList
 * Purpose		: To Get Status Wise Courier Name List
 * @author		: Saurabh
 * Date			: 10/02/2012
 */
public ArrayList<CommonForm> getStatusWiseCourierNameList(String courierName, String status) 
{
	ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
	Connection 				con			=	null;
	Statement 				st			=	null;
	ResultSet 				rs			=	null;
	String 					query		=	"";
	
	try
	{
		query		=	"	SELECT      COURIERID,COURIER_NAME" +
						"	FROM        EXP_COURIERMST" +
						"	WHERE       0=0";
		if(status != null && !status.equals("ALL"))
		{
			query	+=	"	AND         STATUS  	=   	'"+status+"'";
		}
		if(courierName != null && courierName.length() > 0)
		{
			query	+=	"	AND         COURIER_NAME 	LIKE   	'"+courierName+"%'";
		}
		
		query		+=	"	ORDER BY    COURIER_NAME";
		
		con	=	dbConn.connect();
		st	=	con.createStatement();
		rs	=	st.executeQuery(query);		
		while(rs.next())
		{
			CommonForm cForm	=	new CommonForm();				
			cForm.setField1(rs.getString(1));//COURIER_ID
			cForm.setField2(rs.getString(2));//COURIER_NAME
			
			dataList.add(cForm);
		}
		
	}catch (Exception e) {
		System.out.println("Error in CommonMethodBean.getStatusWiseCourierNameList : "+e);
	}finally
	{
		try {
			if(con!=null)
			{
				con.close();
				con=null;
			}
		} catch (Exception e2) {
			System.out.println("Error in CommonMethodBean.getStatusWiseCourierNameList : "+e2);
		}
	}
	return dataList;

}
		
/**
 * Method Name 	: getCellValue
 * Purpose		: To read Cell Value Of Excel File
 * @author		: Ashay
 * @param cell		: HSSFCell cell
 * @param format 	: expected format values are : string , date , long , double (these are expected format if we want to read string value from cell then pass string to this function)
 * Note : this function will return value in desired format but if format mismatched then it will return original value witch had read. 
 */

public String getExcelCellValue(HSSFCell cell, String format)
{
	String 				value				=	"";
	int 				type				=	0;
	CommonMethodBean	commonMethodBean 	= 	new CommonMethodBean();
	
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
				value = commonMethodBean.applyFormater(""+val);
				
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
		
		// if we need date format value
		if(format != null && format.equalsIgnoreCase("date"))			
		{
			String oldVal = value;
			try {
				SimpleDateFormat sdf			=	new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf1			=	new SimpleDateFormat("MMM/dd/yyyy");
				
				
				if(type == 0)
				{
					value	=	commonMethodBean.getProperDateFormat(value);
					Date dt	=	new Date(value);
					value	=	sdf.format(dt);
				}else
				{
					if(value.length()==34 || value.length()==28)
					{
						value	=	commonMethodBean.getProperDateFormat(value);
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
				value	=	applyFormater(value);
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
				value = commonMethodBean.applyFormater(""+val);
				
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
 * Date          : 20/02/2012
 * Note : this function is useful to compare format of given value if it is in desired format then it will return true otherwise false 
 * expected format values are ( string , date , long , double )
 */

public boolean	isProperFormat(String value,String format)
{
	boolean				result				=	false;
	String 				pointValue			=	null;
	
	if(format != null && format.equalsIgnoreCase("whole"))
	{
		try
		{
			double doubleVal = Double.parseDouble(value);
			value = ""+doubleVal;		
			value	=	applyFormater(value);
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
 * Method Name  : getQualityNameList
 * Purpose		: To Get Quality Name List
 * @author		: Prashant
 * Date			: 22/02/2012
 */
public ArrayList<CommonForm> getQualityNameList(String qualityName) 
{
	ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
	Connection	con	=	null;
	Statement	st	=	null;
	ResultSet	rs	=	null;
	String		query	=	"";
	
	try
	{
			query	+=	"	SELECT      QUALITYID,QUALITY_NAME" +
						"	FROM        QUALITYMST " +
						"	WHERE       0=0";
		if(qualityName != null && qualityName.length() > 0)
		{	
			query	+=	"	AND         QUALITY_NAME LIKE '"+qualityName+"%'";
		}	
			query	+=	"	ORDER BY    QUALITY_NAME";
			
		con	=	dbConn.connect();
		st	=	con.createStatement();
		rs	=	st.executeQuery(query);
		while(rs.next())
		{
			CommonForm	commonForm	=	new CommonForm();
			commonForm.setField1(rs.getString(1)); //QUALITYID
			commonForm.setField2(rs.getString(2)); //QUALITY_NAME
			
			dataList.add(commonForm);
		}
	}
	catch(Exception e)
	{
		System.out.println("Error in CommonMethodBean.getQualityNameList : "+e);
	}
	finally
	{
		try {
			if(con!=null)
			{
				con.close();
				con=null;
			}
		} 
		catch (Exception e) {
			System.out.println("Error in CommonMethodBean.getQualityNameList : "+e);
		}
	}
	return dataList;
}

public ArrayList<CommonForm> getDesignNameList(String designName) 
{
	ArrayList<CommonForm> dataList	=	new ArrayList<CommonForm>();
	Connection	con		=	null;
	Statement	st		=	null;
	ResultSet	rs		=	null;
	String		query	=	"";
	
	try
	{
			query	+=	"	SELECT      DESIGNID, DESIGN_NAME" +
						"	FROM        DESIGNMST D" +
						"	WHERE       0=0";
		if(designName!= null && designName.length() > 0)
		{
			query	+=	"	AND         D.DESIGN_NAME   LIKE '"+designName+"%'";
		}				
			query	+=	"	ORDER BY    D.DESIGN_NAME";
			
			con	=	dbConn.connect();
			st	=	con.createStatement();
			rs	=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//DESIGNID
				cForm.setField2(rs.getString(2));//DESIGN_NAME
				
				dataList.add(cForm);
			}
	}
	catch(Exception e)
	{
		System.out.println("Error in CommonMethodBean.getDesignNameList : "+e);
	}
	finally
	{
		try
		{
			if(con!= null)
			{
				con.close();
				con	=	null;
			}	
		}
		catch (Exception e) 
		{
			System.out.println("Error While Closing Connection in CommonMethodBean.getDesignNameList : "+e);
		}
	}
	return dataList;
}

public ArrayList<CommonForm> getShadeNameList(String shadeName) 
{
	ArrayList<CommonForm> dataList	=	new ArrayList<CommonForm>();
	Connection	con		=	null;
	Statement	st		=	null;
	ResultSet	rs		=	null;
	String		query	=	"";
	
	try
	{
			query	+=	"	SELECT      S.SHADEID, S.SHADE_NAME" +
						"	FROM        SHADEMST S" +
						"	WHERE       0=0 ";
		if(shadeName != null && shadeName.length() > 0)
		{
			query	+=	"	AND         S.SHADE_NAME        LIKE '"+shadeName+"%'";
		}
			query	+=	"	ORDER BY    S.SHADE_NAME";
			
		con		=	dbConn.connect();
		st		=	con.createStatement();
		rs		=	st.executeQuery(query);
		while (rs.next()) 
		{
			CommonForm	commonForm	=	new CommonForm();
			commonForm.setField1(rs.getString(1));//SHADEID
			commonForm.setField2(rs.getString(2));//SHADE_NAME
			
			dataList.add(commonForm);	
		}
	}
	catch(Exception e)
	{
		System.out.println("Error in CommonMethodBean.getDesignNameList : "+e);
	}
	finally
	{
		try
		{
			if(con!= null)
			{
				con.close();
				con	=	null;
			}	
		}
		catch (Exception e) 
		{
			System.out.println("Error While Closing Connection in CommonMethodBean.getDesignNameList : "+e);
		}
	}
	return dataList;	
}

	/**
	 * Method Name  : getRoleNameList
	 * Purpose		: To Get Role Name List
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 * @param status 
	 */
	public ArrayList<CommonForm> getRoleNameList(String roleName, String status) 
	{
		ArrayList<CommonForm> dataList	=	new ArrayList<CommonForm>();
		Connection	con		=	null;
		Statement	st		=	null;
		ResultSet	rs		=	null;
		String		query	=	"";
		
		try
		{
				query	+=	"	select 		roleid,role_name " + 
							"	from 		rolemst "+
							"	where 		0=0 ";
			if(roleName != null && roleName.length() > 0)
			{	
				query	+=	"	and		role_name	like	'"+roleName+"%'";
			}
			if(status!= null && status.length() > 0)
			{	
				query	+=	"	and		status	=	'"+status+"'";
			}	
				query	+=	"	order by 	role_name ";
				
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while (rs.next()) 
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//roleid
				commonForm.setField2(rs.getString(2));//role_name
				
				dataList.add(commonForm);	
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in CommonMethodBean.getRoleNameList : "+e);
		}
		finally
		{
			try
			{
				if(con!= null)
				{
					con.close();
					con	=	null;
				}	
			}
			catch (Exception e) 
			{
				System.out.println("Error While Closing Connection in CommonMethodBean.getRoleNameList : "+e);
			}
		}
		return dataList;
		
	}
	
	/**
	 * Method Name  : checkDuplicate
	 * Purpose		: To checkDuplicate
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 * @param status 
	 */
	public  boolean checkDuplicate(String tablename, String fieldname, String fieldvalue)
	{
		Connection con = dbConn.connect();
		try	
		{
			String query="select " + fieldname + " from " + tablename + " where " + fieldname + "='"+ fieldvalue + "'";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			while(result.next()) 
			{
				if((result.getString(fieldname)).equals(fieldvalue))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkDuplicate():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkDuplicate():- "+s);
			}
		}		
		return true;
	}
	
	/**
	 * Method Name  : checkDuplicateForModification
	 * Purpose		: To checkDuplicateForModification
	 * @author		: Prashant
	 * Date			: 06/03/2012
	 * @param status 
	 */
	public  boolean checkDuplicateForModification(String tablename, String field1name, String field1value, String field2name, String field2value)
	{
		Connection con = dbConn.connect();;
		try	
		{
			String query=	"	select	" + field1name + " " +
							"	from	" + tablename + " " +
							"	where	" + field1name + "	=	'"+ field1value + "'" +
							"	and		" + field2name + "	<>	'"+ field2value + "'";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			
			while(result.next()) 
			{
				if((result.getString(field1name)).equals(field1value))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkDuplicateForModification():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkDuplicateForModification():- "+s);
			}
		}		
		return true;
	}
	
	/**
	 * Method Name  : getCountryNameList
	 * Purpose		: To Get Country Name List
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * @param status 
	 */
	public ArrayList<CommonForm> getCountryNameList(String countryName, String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query		+=	"	select 	countryid,country_name " +
							"	from    country_master" +
							"	where	0=0";
			if(countryName != null && countryName.length() > 0)
			{	
			query		+=	"			and country_name	like	'"+countryName+"%'";
			}
			if(status != null && status.length() > 0)
			{	
			query		+=	"			and status		= 			'"+status+"'";
			}
			query		+=	"	order by  country_name ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//countryid
				commonForm.setField2(rs.getString(2));//country_name
				
				dataList.add(commonForm);
			}
			
			
		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getCountryNameList:- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getCountryNameList:- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getDepartmentNameList
	 * Purpose		: To Get Department Name List
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 * @param status 
	 */
	public ArrayList<CommonForm> getDepartmentNameList(String departmentName,String status)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select 	departmentid, department_name" +
						"	from	department_master" +
						"	where   0=0";
			if(departmentName != null && departmentName.length() > 0)
			{
			query	+=	"			and	department_name	 like	'"+departmentName+"%'";
			}
			if(status!= null && status.length() > 0)
			{	
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"	order by department_name";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//departmentid
				commonForm.setField2(rs.getString(2));//department_name
				
				dataList.add(commonForm);
			}
			
			
		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getDepartmentNameList:- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getDepartmentNameList :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : getCityNameList
	 * Purpose		: To Get City Name List
	 * @author		: Prashant
	 * Date			: 09/03/2012
	 * @param status 
	 * @param stateId 
	 * @param countryId 
	 */
	public ArrayList<CommonForm> getCityNameList(String cityName, String status, String countryId, String stateId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select 	cityid,city_name " +
						"	from    city_master " +
						"	where	0=0 ";
			if(countryId!= null && countryId.length() > 0)
			{
			query	+=	"			and	countryid	=	'"+countryId+"'";	
			}
			if(stateId!= null && stateId.length() > 0)
			{
			query	+=	"			and	stateid		=	'"+stateId+"'";	
			}
			if(cityName!= null && cityName.length() > 0)
			{	
			query	+=	"			and 	city_name	like	'"+cityName+"%'";
			}
			if(status!= null && status.length() > 0)
			{	
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order by	city_name";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//cityid
				commonForm.setField2(rs.getString(2));//city_name
				
				dataList.add(commonForm);
			}
			
			
		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getCityNameList:- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getCityNameList :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : checkCountryWiseCity
	 * Purpose		: To checkCountryWiseCity
	 * @author		: Prashant
	 * Date			: 12/03/2012
	 * @param stateId 
	 * @param status 
	 */
	public boolean checkCountryWiseCity(String countryId, String stateId,String cityName) 
	{
		Connection 	con = dbConn.connect();
		int 		cnt	=	0;
		try	
		{
			String query	=	"	select 		count(city_name)" +
								"	from 		city_master " +
								"	where		countryid		= 	'"+countryId+"'" +
								"				and	stateid		=	'"+stateId+"'" +
								"				and	city_name	=	'"+cityName+"'";
			
			Statement st		=	con.createStatement();
			ResultSet result	=	st.executeQuery(query);
			while(result.next()) 
			{
				cnt	=	result.getInt(1);
				if(cnt > 0)
				{					
					return false;
				}
					
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkCountryWiseCity():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkCountryWiseCity():- "+s);
			}
		}		
		return true;
	}
	/**
	 * Method Name  : checkCountryWiseCityForModification
	 * Purpose		: To checkCountryWiseCityForModification
	 * @author		: Prashant
	 * Date			: 12/03/2012
	 * @param cityId2 
	 * @param status 
	 */
	public int checkCountryWiseCityForModification(String cityName,String countryId, String stateId, String cityId)
	{
		Connection con 	= dbConn.connect();
		int count		=	0;
		try	
		{
			String query	=	"	select 	count(city_name)	" +
								"	from 	city_master 	" +
								"	where	city_name			=	'"+cityName+"'" +
								"			and countryid		=	'"+countryId+"' " +
								"			and stateid			=	'"+stateId+"'" +
								"			and cityid			<>	'"+cityId+"'";
			
			Statement 	st	=	con.createStatement();
			ResultSet	rs	=	st.executeQuery(query);
			if(rs.next())
			{
				count	=	rs.getInt(1);
			}
			
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkCountryWiseCityForModification():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkCountryWiseCityForModification():- "+s);
			}
		}		
		return count;
	}
	
	/**
	 * Method Name  : getLeaveTypeList
	 * Purpose		: To getLeaveTypeList
	 * @author		: Prashant
	 * Date			: 12/03/2012
	 * @param status 
	 */
	public ArrayList<CommonForm> getLeaveTypeList(String leaveType,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select 	leavemasterid, leavetype " +
						"	from	leavetype_master " +
						"	where	0=0 ";
			if(leaveType!= null && leaveType.length() > 0)
			{	
			query	+=	"			and	leavetype	like	'"+leaveType+"%'";
			}
			if(status!= null && status.length() > 0)
			{	
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"	order by leavetype ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//leavemasterid
				commonForm.setField2(rs.getString(2));//leavetype
				
				dataList.add(commonForm);
			}
			
			
		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getLeaveTypeList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getLeaveTypeList :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getGroupNameList
	 * Purpose		: To getGroupNameList
	 * @author		: Prashant
	 * Date			: 13/03/2012
	 * @param status 
	 */
	public ArrayList<CommonForm> getGroupNameList(String groupName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	groupid, group_name" +
						"	from	groupmst" +
						"	where	0=0";
			if(groupName!= null && groupName.length() >0)
			{	
			query	+=	"			and	group_name	like	'"+groupName+"%'";
			}
			if(status!= null && status.length() >0)
			{
			query	+=	"			and	status 		=	'"+status+"'";
			}
			query	+=	"	order by group_name";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//groupid
				commonForm.setField2(rs.getString(2));//group_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getGroupNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getGroupNameList :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : getGroupValueNameList
	 * Purpose		: To getGroupValueNameList
	 * @author		: Prashant
	 * Date			: 14/03/2012
	 * @param status 
	 * @param groupId 
	 */
	public ArrayList<CommonForm> getGroupValueNameList(String groupValueName, String groupId,String status)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	groupvalueid,groupvalue_name" +
						"	from	groupvalue" +
						"	where	0=0";
			if(groupValueName!= null && groupValueName.length() > 0)
			{	
			query	+=	"			and	groupvalue_name		like	'"+groupValueName+"%'";
			}
			if(groupId!= null && groupId.length() > 0)
			{
			query	+=	"			and	groupid			=	'"+groupId+"'";
			}
			if(status!=null && status.length()>0)
			{
			query	+=	"			and	status			=	'"+status+"'";
			}
			query	+=	"	order by	groupvalue_name";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//groupvalueid
				commonForm.setField2(rs.getString(2));//groupvalue_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getGroupValueNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getGroupValueNameList :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : checkForIsProperDelete
	 * Purpose		: To checkForIsProperDelete
	 * @author		: Prashant
	 * Date			: 14/03/2012
	 * @param status 
	 * @param groupId 
	 */
	public int checkForIsProperDelete(String tablename,String field1name,String field2name,String field1value)
	{
		Connection con 	= dbConn.connect();
		int count		=	0;
		try	
		{
			String query	=	"	select count("+field1name+")"+
								"	from	"+tablename+ " " +
								"	where	"+field2name+ " = '"+field1value+"'";
			Statement 	st	=	con.createStatement();
			ResultSet	rs	=	st.executeQuery(query);
			if(rs.next())
			{
				count	=	rs.getInt(1);
			}
			
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkForIsProperDelete():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkForIsProperDelete():- "+s);
			}
		}		
		return count;
	}
	
	/**
	 * Method Name  : checkForIsProperDelete
	 * Purpose		: To checkForIsProperDelete
	 * @author		: Prashant
	 * Date			: 29/03/2012
	 * @param  
	 * @param  
	 */
	public int checkForActive(String tablename,String field1name,String field2name,String field1value)
	{
		Connection con 	= dbConn.connect();
		int count		=	0;
		try	
		{
			String query	=	"	select count("+field1name+")"+
								"	from	"+tablename+ " " +
								"	where	"+field2name+ " = '"+field1value+"'";
			Statement 	st	=	con.createStatement();
			ResultSet	rs	=	st.executeQuery(query);
			if(rs.next())
			{
				count	=	rs.getInt(1);
			}
			
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkForIsProperDelete():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkForIsProperDelete():- "+s);
			}
		}		
		return count;
	}
	
	/**
	 * Method Name  : getActiveGroupValue
	 * Purpose		: To getActiveGroupValue
	 * @author		: Prashant
	 * Date			: 17/03/2012
	 * @param 
	 * @param	groupName 
	 */
	public ArrayList<CommonForm> getActiveGroupValue(String groupName) 
	{
		groupName		=	groupName.toUpperCase();
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	=	"	select	gv.groupvalueid, gv.groupvalue_name " +
						"	from	groupvalue gv " +
						"	inner join groupmst gm 	on gv.groupid		=	gm.groupid " +
						"							and	gm.group_name	=	'"+groupName+"'" +
						"							and	gv.status		=	'ACTIVE' " +
						"	order by gv.groupvalue_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//groupvalueid
				cForm.setField2(rs.getString(2));//groupvalue_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getActiveGroupValue :"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getActiveGroupValue :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getEmployeeNameList
	 * Purpose		: To getEmployeeNameList
	 * @author		: Prashant
	 * Date			: 19/03/2012
	 * @param 
	 * @param		empName,status 
	 */
	public ArrayList<CommonForm> getEmployeeNameList(String empName,String status)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		
		try
		{
			query	+=	"	select	employeeid,employee_name" +
						"	from	employee_master " +
						"	where	0=0 ";
			if(empName!= null && empName.length() > 0)
			{
			query	+=	"			and	employee_name	like	'"+empName+"%'";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"	order by	employee_name	";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//employeeid
				commonForm.setField2(rs.getString(2));//employee_name
			
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getEmployeeNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getEmployeeNameList :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getStatusWiseStateName
	 * Purpose		: To getStatusWiseStateName
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 * @param countryId 
	 * @param 
	 * @param		 
	 */
	public ArrayList<CommonForm> getStatusWiseStateName(String stateNames,String status, String countryId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	stateid,state_name " +
						"	from	state_master " +
						"	where	0=0 ";
			if(countryId!= null && countryId.length() > 0)
			{
			query	+=	"			and	countryid	=	'"+countryId+"'";	
			}
			if(stateNames!= null && stateNames.length() > 0)
			{
			query	+=	"			and	state_name	like	'"+stateNames+"%'";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"			order by state_name";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//stateid
				commonForm.setField2(rs.getString(2));//state_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getEmployeeNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getEmployeeNameList :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getStatusWiseStateName
	 * Purpose		: To getStatusWiseStateName
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 * @param 
	 * @param		 
	 */
	public ArrayList<CommonForm> getCFormArrayList(String query) 
	{
		ArrayList<CommonForm> 	dataList	=	new ArrayList<CommonForm>();
		Connection				con			=	null;
		Statement				st			=	null;
		ResultSet				rs			=	null;
		ResultSetMetaData 		rsmd		=	null;
		int						colCnt		=	0;
		int						rowCnt		=	0;
		String					rowRec		=	"";	
				
		try
		{
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			rsmd	=	rs.getMetaData();
			colCnt	=	rsmd.getColumnCount();
			while (rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				rowCnt++;
				for (int i = 1;i<=colCnt;i++)
				{	
					commonForm.setField1(""+rowCnt);
					rowRec	=	rs.getString(i)!=null && rs.getString(i).length() > 0 ? rs.getString(i) : "" ;
					switch(i)
					{							
						case 1	:	commonForm.setField2(rowRec);
									break;
						case 2	:	commonForm.setField3(rowRec);
									break;
						case 3	:	commonForm.setField4(rowRec);
									break;
						case 4	:	commonForm.setField5(rowRec);
									break;
						case 5	:	commonForm.setField6(rowRec);
									break;
						case 6	:	commonForm.setField7(rowRec);
									break;
						case 7	:	commonForm.setField8(rowRec);
									break;
						case 8	:	commonForm.setField9(rowRec);
									break;
						case 9	:	commonForm.setField10(rowRec);
									break;
						case 10	:	commonForm.setField11(rowRec);
									break;
	
						case 11	:	commonForm.setField12(rowRec);
									break;
						case 12	:	commonForm.setField13(rowRec);
									break;
						case 13	:	commonForm.setField14(rowRec);
									break;
						case 14	:	commonForm.setField15(rowRec);
									break;
						case 15	:	commonForm.setField16(rowRec);
									break;
						case 16	:	commonForm.setField17(rowRec);
									break;
						case 17	:	commonForm.setField18(rowRec);
									break;
						case 18	:	commonForm.setField19(rowRec);
									break;
						case 19	:	commonForm.setField20(rowRec);
									break;
						case 20	:	commonForm.setField21(rowRec);
									break;
				
						case 21	:	commonForm.setField22(rowRec);
									break;
						case 22	:	commonForm.setField23(rowRec);
									break;
						case 23	:	commonForm.setField24(rowRec);
									break;
						case 24	:	commonForm.setField25(rowRec);
									break;
						case 25	:	commonForm.setField26(rowRec);
									break;
						case 26	:	commonForm.setField27(rowRec);
									break;
						case 27	:	commonForm.setField28(rowRec);
									break;
						case 28	:	commonForm.setField29(rowRec);
									break;
						case 29	:	commonForm.setField30(rowRec);
									break;
						case 30	:	commonForm.setField31(rowRec);
									break;
	
						case 31	:	commonForm.setField32(rowRec);
									break;
						case 32	:	commonForm.setField33(rowRec);
									break;
						case 33	:	commonForm.setField34(rowRec);
									break;
						case 34	:	commonForm.setField35(rowRec);
									break;
						case 35	:	commonForm.setField36(rowRec);
									break;
						case 36	:	commonForm.setField37(rowRec);
									break;
						case 37	:	commonForm.setField38(rowRec);
									break;
						case 38	:	commonForm.setField39(rowRec);
									break;
						case 39	:	commonForm.setField40(rowRec);
									break;
						case 40	:	commonForm.setField41(rowRec);
									break;
	
						case 41	:	commonForm.setField42(rowRec);
									break;
						case 42	:	commonForm.setField43(rowRec);
									break;
						case 43	:	commonForm.setField44(rowRec);
									break;
						case 44	:	commonForm.setField45(rowRec);
									break;
						case 45	:	commonForm.setField46(rowRec);
									break;
						case 46	:	commonForm.setField47(rowRec);
									break;
						case 47	:	commonForm.setField48(rowRec);
									break;
						case 48	:	commonForm.setField49(rowRec);
									break;
						case 49	:	commonForm.setField50(rowRec);
									break;
					}
				}
				dataList.add(commonForm);
			}
		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getCFormArrayList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getCFormArrayList :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : checkDuplicationAgainstGroup
	 * Purpose		: To checkDuplicationAgainstGroup
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 * @param 		fieldXname	 from	database
	 * @param		fieldXvalue  from	textbox   
	 * X repersent values
	 * 
	 */
	public boolean checkDuplicationAgainstGroup(String field1name, String tablename,String field2name, String field1value, String field2vaule) 
	{
		Connection 	con 	= 	dbConn.connect();
		int 		cnt		=	0;
		try	
		{
			String query		=	"	select	count("+field1name+")" +
									"	from 	"+tablename+" "+
									"	where	"+field2name+"	=	'"+field1value+"'" +
									"	and		"+field1name+"	=	'"+field2vaule+"'";
			
			Statement st		=	con.createStatement();
			ResultSet result	=	st.executeQuery(query);
			while(result.next()) 
			{
				cnt	=	result.getInt(1);
				if(cnt > 0)
				{					
					return false;
				}
					
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkDuplicationAgainstGroup :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkDuplicationAgainstGroup :- "+s);
			}
		}		
		return true;
	}
	/**
	 * Method Name  : checkDuplicationAgainstGroupForModification
	 * Purpose		: To checkDuplicationAgainstGroupForModification
	 * @author		: Prashant
	 * Date			: 20/03/2012
	 * @param 		fieldXname	 from	database
	 * @param		fieldXvalue  from	textbox   
	 * X repersent values	
	 */
	public int checkDuplicationAgainstGroupForModification(String field1name,String tablename, String field2name, String field3name, String field1value,String field2value,String field3value) 
	{
		Connection 	con 	= 	dbConn.connect();
		Statement 	st		=	null;
		ResultSet	rs		=	null;
		int count			=	0;
		String	query		=	"";
		try	
		{
			query	+=	"	select	count("+field1name+")" +
						"	from	"+tablename+""+
						"	where	"+field1name+"		=	'"+field1value+"'" +
						"			and	"+field2name+"	=	'"+field2value+"'" +
						"			and	"+field3name+"	<>	'"+field3value+"'";
			
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			if(rs.next())
			{
				count	=	rs.getInt(1);
			}
			
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.checkDuplicationAgainstGroupForModification :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.checkDuplicationAgainstGroupForModification :- "+s);
			}
		}		
		return count;
	}
	/**
	 * Method Name  : getStatusWiseBankName
	 * Purpose		: To getStatusWiseBankName
	 * @author		: Prashant
	 * Date			: 23/03/2012
	 * @param 
	 * @param		 
	 */
	public ArrayList<CommonForm> getStatusWiseBankName(String bankName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	bankid,bank_name " +
						"	from	bank_master " +
						"	where	0=0	";
			if(bankName!= null && bankName.length() > 0)
			{
			query	+=	"			and	bank_name	like	'"+bankName+"%'";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"	order by bank_name ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//bankid
				commonForm.setField2(rs.getString(2));//bank_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseBankName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseBankName :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : getStatusWiseBranchName
	 * Purpose		: To getStatusWiseBranchName
	 * @author		: Prashant
	 * Date			: 23/03/2012
	 * @param 
	 * @param		 
	 */
	public ArrayList<CommonForm> getStatusWiseBranchName(String branchName,String status, String bankId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	branchid,branch_name " +
						"	from	bank_branch_master " +
						"	where	0=0 ";
			if(bankId != null && bankId.length() > 0)
			{
			query	+=	"			and	bankid		=	'"+bankId+"'";
			}
			if(branchName!= null && branchName.length() > 0)
			{
			query	+=	"			and	branch_name	like	'"+branchName+"%'" ;
			}
			query	+=	"	order by	branch_name ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//branchid
				commonForm.setField2(rs.getString(2));//branch_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseBranchName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseBranchName :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getBankName
	 * Purpose		: To getBankName
	 * @author		: Prashant
	 * Date			: 26/03/2012
	 * @param 
	 * @param	 
	 */
	public ArrayList<CommonForm> getBankName() 
	{
		
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	=	"	select	bankid,bank_name " +
						"	from	bank_master " +
						"	where	0=0 " +
						"			and	status	=	'ACTIVE' " +
						"	order by bank_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//bankid
				cForm.setField2(rs.getString(2));//bank_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getBankName :"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getBankName :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getBankIdWiseBranchName
	 * Purpose		: To getBankIdWiseBranchName
	 * @author		: Prashant
	 * Date			: 26/03/2012
	 * @param 
	 * @param	 
	 */
	public ArrayList<CommonForm> getBankIdWiseBranchName(String bankId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	=	"	select	branchid,branch_name " +
						"	from	bank_branch_master " +
						"	where	0=0 " ;
			if(bankId!= null && bankId.length() > 0)
			{
			query	+=	"			and	bankid	=	'"+bankId+"'";
			}
			query	+=	"	order by branch_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//branchid
				cForm.setField2(rs.getString(2));//branch_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getBankIdWiseBranchName :"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getBankIdWiseBranchName :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getHeadNameList
	 * Purpose		: To getHeadNameList
	 * @author		: Prashant
	 * Date			: 29/03/2012
	 
	 */
	public ArrayList<CommonForm> getHeadNameList(String headName) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	+=	"	select	salaryheadid, salaryheadname " +
						"	from	salary_head_master " +
						"	where	0=0	";
			if(headName!= null && headName.length() > 0)
			{
			query	+=	"			and	salaryheadname	like	'"+headName+"%'";
			}
			query	+=	"	order by  salaryheadname ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//salaryheadid
				cForm.setField2(rs.getString(2));//salaryheadname
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getHeadNameList : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getHeadNameList :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getDataOnOneCondition
	 * Purpose		: To getDataOnOneCondition
	 * @author		: Prashant
	 * Date			: 05/04/2012
	 */
	public String getDataOnOneCondition(String tableName, String returnValue,String condition, String field1value) 
	{
		String			outputString		=	"";
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	=	"	select "+returnValue+" from "+tableName+" where "+condition+" = '"+field1value+"'" ;
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				outputString	=	rs.getString(1);//returnValue
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getDataOnOneCondition : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getDataOnOneCondition :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : getStructureNameList
	 * Purpose		: To getStructureNameList
	 * @author		: Prashant
	 * Date			: 05/04/2012
	 */
	public ArrayList<CommonForm> getStructureNameList(String structName) 
	{
		ArrayList<CommonForm>	dataList			=	new ArrayList<CommonForm>();
		Connection				con					=	null;
		Statement				st					=	null;
		ResultSet				rs					=	null;
		String					query				=	"";
		try
		{
			query	+=	"	select 	structureid, structure_name" +
						"	from	salary_structure_header" +
						"	where	0=0	";
			if(structName!= null && structName.length() > 0)
			{
			query	+=	"			and	structure_name	like	'"+structName+"%'";
			}
			query	+=	"	order by structure_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//structureid
				cForm.setField2(rs.getString(2));//structure_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getStructureNameList : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getStructureNameList :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getMonthAndYearWiseAttendanceId
	 * Purpose		: To getMonthAndYearWiseAttendanceId
	 * @author		: Prashant
	 * Date			: 13/04/2012
	 */
	public String getMonthAndYearWiseAttendanceId(String tableName,String returnValue, String field1Name, String field2Name, String field1Value,String field2Value) 
	{
		
		String			outputString		=	"";
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	=	"	select "+returnValue+" from "+tableName+" where "+field1Name+" = '"+field1Value+"' and "+field2Name+" = '"+field2Value+"'" ;
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				outputString	=	rs.getString(1);//returnValue
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getMonthAndYearWiseAttendanceId : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getMonthAndYearWiseAttendanceId :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : getStatusWiseCompanyName
	 * Purpose		: To getStatusWiseCompanyName
	 * @author		: Prashant
	 * Date			: 20/04/2012
	 */
	public ArrayList<CommonForm> getStatusWiseCompanyName(String companyName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select 	companyid, companyname" +
						"	from	company_master" +
						"	where	0 = 0 " ;
			if(companyName!=null && companyName.length() > 0)
			{
			query	+=	"			and	companyname	like	'"+companyName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'";
			}
			query	+=	"	order by companyname ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//companyid
				commonForm.setField2(rs.getString(2));//companyname
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseCompanyName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseCompanyName :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getStatusWiseLoginName
	 * Purpose		: To getStatusWiseLoginName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	public ArrayList<CommonForm> getStatusWiseLoginName(String loginName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	userid,user_name " +
						"	from	usermst " +
						"	where	0 = 0 " ;
			if(loginName!=null && loginName.length() > 0)
			{
			query	+=	"			and	user_name	like	'"+loginName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order	by	user_name ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//user_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseLoginName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseLoginName :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : createFolderStructureFirstTime
	 * Purpose		: To createFolderStructureFirstTime
	 * @author		: Prashant
	 * Date			: 02/05/2012
	 */
	public boolean	createFolderStructureFirstTime(String mainFolder,String empCode)
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
			String	folderPath				=	mainFolder+"/"+empCode;
			File	projfolder		= 	new File(folderPath)	;
			if (!projfolder.exists())
			{
				projfolder.mkdir();			
			}
							
		} 
		catch (Exception e) 
		{ 
			result	=	false;
			System.out.println("Exception in CommonMethodBean.createFolderStructureFirstTime() :- "+e);

		}			
		return result;
	}
	
	/**
	 * Method Name  : uploadFile
	 * Purpose		: To uploadFile
	 * @author		: Prashant
	 * Date			: 02/05/2012
	 */
	public boolean uploadFile(FormFile uploadedFile, String destPath)
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
					System.out.println("Error in closing connection in CommonMethodBean.uploadFile():- "+e);
				}
		}
	    return result;
	 }
	/**
	 * Method Name  : getStatusWiseDocumentName
	 * Purpose		: To getStatusWiseDocumentName
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
	public ArrayList<CommonForm> getStatusWiseDocumentName(String docName,String status, String empId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	documentid,document_title " +
						"	from	employee_document " +
						"	where	0 = 0 " ;
			if(docName!=null && docName.length() > 0)
			{
			query	+=	"			and	document_title	like	'"+docName+"%'" ;
			}
			if(empId!=null && empId.length() > 0)
			{
			query	+=	"			and	employeeid		=	'"+empId+"'" ;
			}
			query	+=	"	order by document_title ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//documentid
				commonForm.setField2(rs.getString(2));//document_title
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseDocumentName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseDocumentName :- "+s);
			}
		}		
		return	dataList;
	}
	/**
	 * Method Name  : copyfile
	 * Purpose		: To copyfile
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
	public boolean copyfile(String srFile, String dtFile)
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
	 * Method Name  : copyFileOnSameFolder
	 * Purpose		: To copyFileOnSameFolder
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
	public boolean copyFileOnSameFolder(File src, File dst)
	 {
		boolean	result	=	false;
		
		try
		{	
			InputStream in = new FileInputStream(src);
		    OutputStream out = new FileOutputStream(dst);

		    // Transfer bytes from in to out
		    byte[] buf = new byte[50000];
		    int len;
		    while ((len = in.read(buf)) > 0) {
		        out.write(buf, 0, len);
		    }
		    in.close();
		    out.close();
	      	
	      	result	=	true;
		}
		catch (Exception e) {					
			result	=	false;
			System.out.println("Error in closing connection in CommonMethodBean.uploadFile():- "+e);
		}
		
	    return result;
	 }
	
	/**
	 * Method Name  : getModeWiseBankIdWiseBranchName
	 * Purpose		: To getModeWiseBankIdWiseBranchName
	 * @author		: Prashant
	 * Date			: 03/05/2012
	 */
	public ArrayList<CommonForm> getModeWiseBankIdWiseBranchName(String bankId,String mode) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		try
		{
			query	=	"	select	branchid,branch_name " +
						"	from	bank_branch_master " +
						"	where	0=0 " ;
			if(mode!=null && mode.length() >0 && mode.equalsIgnoreCase("MASTER"))
			{	
				//if(bankId!= null && bankId.length() > 0)
				//{
					query	+=	"			and	bankid	=	'"+bankId+"'";
				//}
			}
			if(mode!=null && mode.length() >0 && mode.equalsIgnoreCase("LIST"))
			{	
				if(bankId!= null && bankId.length() > 0)
				{
					query	+=	"			and	bankid	=	'"+bankId+"'";
				}
			}
			query	+=	"	order by branch_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//branchid
				cForm.setField2(rs.getString(2));//branch_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getBankIdWiseBranchName :"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getBankIdWiseBranchName :"+e);
			}
		}
		return dataList;
	}
	/**
	 * Method Name  : getSalaryStructure
	 * Purpose		: To getSalaryStructure
	 * @author		: Prashant
	 * Date			: 15/05/2012
	 */
	public ArrayList<CommonForm> getSalaryStructure() 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	=	"	select	structureid,structure_name" +
						"	from	salary_structure_header" +
						"	where	0=0	" +
						"	order by	structure_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//structureid
				cForm.setField2(rs.getString(2));//structure_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getSalaryStructure :"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getSalaryStructure :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getStatusWiseProjectName
	 * Purpose		: To getStatusWiseProjectName
	 * @author		: Prashant
	 * Date			: 10/09/2012
	 */
	public ArrayList<CommonForm> getStatusWiseProjectName(String projectName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	=	"	select	projectid,project_name " +
						"	from	projectmst " +
						"	where	0 = 0 " ;
			if(projectName!=null && projectName.length() > 0)
			{
			query	+=	"			and	project_name	like	'"+projectName+"%'";
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status			=		'"+status+"'";
			}
			query	+=	"	order by project_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//projectid
				cForm.setField2(rs.getString(2));//project_name
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getStatusWiseProjectName : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getStatusWiseProjectName : "+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getProjrctWiseClientList
	 * Purpose		: To getProjrctWiseClientList
	 * @author		: Gaurav
	 * Date			: 17/09/2012
	 */
	public ArrayList<CommonForm> getProjrctWiseClientList(String projectId,String status) 
	{
		ArrayList<CommonForm>   dataList		=	new  ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	=	"	select um.userid,um.user_name " +
						"	from   usermst um" +
						"	inner join project_users pu on     pu.userid    =  um.userid";
			if(projectId != null && projectId.length() > 0)
			{
            query   +=  "  						        and    pu.projectid =   '"+projectId+"'";
			}
            if(status != null && status.length() > 0)
            {
            query	+=  "								and    um.status    =   '"+status+"'"; 	
           	}
            query	+=	"	order by um.user_name ";
            
            con		=	dbConn.connect();
            st		=	con.createStatement();
            rs		=	st.executeQuery(query);
            while(rs.next())
            {
            	CommonForm		cForm	=	new  CommonForm();
            	cForm.setField1(rs.getString(1));
            	cForm.setField2(rs.getString(2));
            	dataList.add(cForm);
            }
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMethodBean.getProjrctWiseClientList : "+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in CommonMethodBean.getProjrctWiseClientList : "+e);
			}
		}
		return dataList;
	}
	
	
	
	/**
	 * Method Name  : getStatusWiseBankName
	 * Purpose		: To getStatusWiseClientName
	 * @author		: Prashant
	 * Date			: 27/09/2012
	 * @param 
	 * @param		 
	 */
	public ArrayList<CommonForm> getStatusWiseClientName(String clientName) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	clientid,clientname " +
						"	from	client_mst " +
						"	where	0=0	";
			if(clientName!= null && clientName.length() > 0)
			{
			query	+=	"			and	clientname	like	'"+clientName+"%'";
			}
			
			
			
			query	+=	"	order by clientname ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//clientid
				commonForm.setField2(rs.getString(2));//clientname
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseClientName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseClientName :- "+s);
			}
		}		
		return	dataList;
	}
	
	
	/**
	 * Method Name  : getPersonNameList
	 * Purpose		: To getPersonNameList
	 * @author		: Prashant
	 * Date			: 29/09/2012
	 * @param 	 
	 */
	public ArrayList<CommonForm> getPersonNameList(String clientId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select client_dtls_id,personname " +
						"	from client_contact_dtls " +
						"	where	0=0	";
			if(clientId!= null && clientId.length() > 0)
			{
			query	+=	"			and	clientid	=	'"+clientId+"'	";
			}	
			query	+=	"	order by personname ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//client_dtls_id
				commonForm.setField2(rs.getString(2));//personname
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getPersonNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getPersonNameList :- "+s);
			}
		}		
		return	dataList;
	}
	
	
	/**
	 * Method Name  : getValidOrNot
	 * Purpose		: To getValidOrNot
	 * @author		: Prashant
	 * Date			: 15/10/2012
	 * @param 	 
	 */
	public  String getValidOrNot(String userProcess,String rarId) 
	{
		String	outputString	=	"NOTVALID";
		if(rarId!=null && rarId.length() > 0 && userProcess!=null && userProcess.length() > 0)
		{
			if(userProcess.indexOf(rarId)>=0)// && process_Status!=null && process_Status.equals("ACTIVE"))
			{
				outputString	=	"VALID";
			}	
		}
		
		return  outputString;
	}
	
	/**
	 * Action Name 	: getIpAddress
	 * Purpose		: To getIpAddress 
	 * @author		: Prashant
	 * Date			: 23/10/2012
	 */
	
	public static String getIpAddress()
	{
		String ipAddress	=	"";
		try
		{
			InetAddress thisIp =InetAddress.getLocalHost();
			ipAddress	=	thisIp.getHostAddress();
			
		}
		catch(Exception e) 
		{
		  System.out.println("Error In CommonMethodBean.getIpAddress : "+e);
		}
		return ipAddress;
	}
	
	/**
	 * Method Name  : getEmployeeNameList
	 * Purpose		: To getEmployeeNameList
	 * @author		: Prashant
	 * Date			: 19/03/2012
	 * @param 
	 * @param		empName,status 
	 */
	public ArrayList<CommonForm> getEmployeeNameListForLoginDetails(String empName,String status)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		
		try
		{
			query	+=	"	select	um.userid,em.employee_name" +
						"	from	employee_master em" +
						"	inner join usermst um on um.employeeid = em.employeeid" +
						"	where	0=0";
			if(empName!= null && empName.length() > 0)
			{
			query	+=	"			 and     	em.employee_name	like	'"+empName+"%' ";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"			 and		em.status		=	'"+status+"'";
			}
			query	+=	"	order by	em.employee_name	";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//user_name
			
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getEmployeeNameList :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getEmployeeNameList :- "+s);
			}
		}		
		return	dataList;
	}

	/**
	 * Method Name  : checkDuplicate
	 * Purpose		: To checkDuplicate
	 * @author		: PRASHANT
	 * Date			: 06/03/2012
	 * @param status 
	 */
	public  int getMaxPrimaryId(String tablename, String fieldname)
	{
		Connection con 	= 	dbConn.connect();
		int		maxID	=	0;	
		try	
		{
			String query="select max("+fieldname+") from "+tablename+" ";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			while(result.next()) 
			{
				maxID = result.getInt(1);
					
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getMaxPrimaryId():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getMaxPrimaryId():- "+s);
			}
		}		
		return maxID;
	}

	/**
	 * Method Name  : getStatusWiseSpeakerName
	 * Purpose		: To getStatusWiseSpeakerName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	public ArrayList<CommonForm> getStatusWiseSpeakerName(String speakerName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	speaker_id,speaker_name " +
						"	from	speakermst " +
						"	where	0 = 0 " ;
			if(speakerName!=null && speakerName.length() > 0)
			{
			query	+=	"			and	speaker_name	like	'"+speakerName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order	by	speaker_name ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//user_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseLoginName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseLoginName :- "+s);
			}
		}		
		return	dataList;
	}
	
	/**
	 * Method Name  : getStatusWiseIndustryName
	 * Purpose		: To getStatusWiseIndustryName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	public ArrayList<CommonForm> getStatusWiseDeptName(String deptName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	deptid,deptname " +
						"	from	deptmaster " +
						"	where	0 = 0 " ;
			if(deptName!=null && deptName.length() > 0)
			{
			query	+=	"			and	deptname	like	'"+deptName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order	by	deptname ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//user_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseLoginName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseLoginName :- "+s);
			}
		}		
		return	dataList;
	}
	
	public ArrayList<CommonForm> getStatusWiseArticlerName(String articleName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	articleid,articlename " +
						"	from	articlemaster " +
						"	where	0 = 0 " ;
			if(articleName!=null && articleName.length() > 0)
			{
			query	+=	"			and	articlename	like	'"+articleName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order	by	articlename ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//user_name
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseArticlerName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseArticlerName :- "+s);
			}
		}		
		return	dataList;
	}
	
	
	/**
	 * Method Name  : getStatusWiseWebinarName
	 * Purpose		: To getStatusWiseWebinarName
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 */
	public ArrayList<CommonForm> getStatusWiseWebinarName(String webinarName,String status) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con					=	null;
		Statement		st					=	null;
		ResultSet		rs					=	null;
		String			query				=	"";
		
		try
		{
			query	+=	"	select	webinarid,webinarName " +
						"	from	webinarmaster " +
						"	where	0 = 0 " ;
			if(webinarName!=null && webinarName.length() > 0)
			{
			query	+=	"			and	webinarName	like	'"+webinarName+"%'" ;
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order	by	webinarName ";
			
			con		=    dbConn.connect();
			st		=	 con.createStatement();
			rs		=	 st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//webinarid
				commonForm.setField2(rs.getString(2));//webinarName
				
				dataList.add(commonForm);
			}

		}
		catch(Exception e)	
		{
			System.out.println("Error in CommonMethodBean.getStatusWiseLoginName :- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMethodBean.getStatusWiseLoginName :- "+s);
			}
		}		
		return	dataList;
	}
	
	
	
	public ArrayList<CommonForm> getGroupValueFromId(String groupId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=	"	select 	g.groupvalueid , g.groupvalue_name" +
						"	from 	groupvalue g" +
						"	where 	g.groupid = '"+groupId+"'	" +
						"	and 	g.status  = 'ACTIVE'" +
						"	order by g.groupvalue_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//groupvalueid
				commonForm.setField2(rs.getString(2));//groupvalue_name
				dataList.add(commonForm);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR In UserBean.getRoleList:"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in UserBean.getRoleList:"+e);
			}
		}
		return dataList;
	}
	
	
	public ArrayList<CommonForm> getUserWithDoctor() 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=	"	select 		u.userid , u.display_name" +
						"	from 		usermst u" +
						"	inner join 	groupvalue gv 	on 	gv.groupvalueid 	= 	u.staff_id" +
						"								and	gv.groupvalue_name	=	'DOCTOR'" +
						"	order by 	u.display_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//userid
				commonForm.setField2(rs.getString(2));//display_name
				dataList.add(commonForm);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR In UserBean.getRoleList:"+e);
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
					con	=	null;
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception While Closing The Connection in UserBean.getRoleList:"+e);
			}
		}
		return dataList;
	}
	
}



