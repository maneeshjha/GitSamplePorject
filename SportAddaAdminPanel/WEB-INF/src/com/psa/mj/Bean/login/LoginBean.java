package com.psa.mj.Bean.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.login.LoginForm;
import com.psa.mj.DBConnection.DBConnection;




public class LoginBean
{
		public static String dbusername;//uname;
		public static String dbuserpass;//upass;
		public String username;//uname;
		public static String userpass;//upass;
		public static String usertype;//upass;
		public static String userid;//upass;
		

		public LoginBean()
		{
		}

		public LoginBean(String uname,String upass,String utype,String uid)
		{
			username=uname;
			usertype=utype;
			userid=uid;
		}

/* ----------------------------------------------------------------------------------- 
		Method		:- checkLogin
		Return Type :- String.
		Purpose		:- Check DataBase(userid,password) with USER (userid, password)
	----------------------------------------------------------------------------------- */
	public static ArrayList checkLogin(String username,String userpass)
	{
		ArrayList retList 	= 	new ArrayList();
		String retResult	=	"invalid";
		Connection con		=	null;
		PreparedStatement st		=	null;			
		ResultSet rs		=	null;

		DBConnection dbConn = new DBConnection();
		con=dbConn.connect();
		String query	=	"	select	user_name,display_name,password,rarid,status,userid,roleid" +
							"	from 	usermst" +
							"	where	user_name	=	?" +
							"	and	binary password	=	?";
		
		try{
			
			st	=	con.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, userpass);
			
			rs	=	st.executeQuery();
			if(rs.next())
			{
				String userName			=	rs.getString(1);	//	login name
				String dispName			=	rs.getString(2);	// 	display name
				String password			=	rs.getString(3);	//	password
				String rarIDs 			= 	rs.getString(4);	//	rarids
				String status 			= 	rs.getString(5);	//	status	
				String userID			=	rs.getString(6);	//	userid	
				String roleID			=	rs.getString(7);	//	roleid	
				retResult				=	"valid";
				retList.add(retResult);
				retList.add(username);
				retList.add(dispName);
				retList.add(status);
				retList.add(rarIDs);	
				retList.add(userID);
				retList.add(roleID);
			}
			st.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in LoginBean.checkLogin(): "+s);
			}
		}	
		return retList;
	}
	
	
	/**
	 * Method Name : getAllProcessList
	 * Created By : Saurabh
	 * Created On : 04/02/2012
	 */
	public static ArrayList<CommonForm> getAllProcessList(String status)
	{
		ArrayList<CommonForm> dataList 	= 	new ArrayList<CommonForm>();
		String retResult	=	"invalid";
		Connection con		=	null;
		Statement st		=	null;
		ResultSet rs		=	null;

		DBConnection dbConn = new DBConnection();
		con=dbConn.connect();
		String query	=	"	select responsibilityid,responsibility_name,status " +
							"	from responsibilitymst  " +
							"	where       0=0";
		if(status != null && !status.equals("ALL"))
		{
				query	+=	"	and         status  = '"+status+"'";
		}
				query	+=	"	order by groupid,responsibilityid";
		try{
			st=con.createStatement() ;
			rs=st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm = new CommonForm();
				commonForm.setField1(rs.getString(1));//RESPONSIBILITYID
				commonForm.setField2(rs.getString(2));//RESPONSIBILITY_NAME
				commonForm.setField3(rs.getString(3));//STATUS
				dataList.add(commonForm);
				
			}
		}catch(Exception e)
		{
			System.out.println("Error in closing connection in LoginBean.getAllProcessList(): "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in LoginBean.getAllProcessList(): "+s);
			}
		}	
		return dataList;
	}
	
	public int updateUserDetails(String userId,String ipAddress) 
	{
		
		DBConnection dbConn = new DBConnection();
		
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		int 				count				=	0;
		//String				ipAddress			=	CommonMethodBean.getIpAddress(); 
		
		
		try
		{
		
				con		=	dbConn.connect();
				query	+=	"	insert into user_login_dtls ( userid, login_date, ip_address, logged_in_date)" +
							"	values (? , CURDATE() , ? ,  sysdate())";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1, userId);
				pst.setString(2, ipAddress);
				
				count	=	pst.executeUpdate();
				pst.close();
			
		}
		catch(Exception e)
		{
			
			System.out.println("ERROR In LoginBean.updateUserDetails :"+e);
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
				
				System.out.println("Exception While Closing The Connection in LoginBean.updateUserDetails :"+e);
			}
		}
		return count ;
	}
	
	
	/**
	 * Method Name : checkForTodaysLogin
	 * Created By : Prashant
	 * Created On : 23/10/2012
	 */
	public int checkForTodaysLogin(String currentDate, String userId)
	{
		Connection con		=	null;
		Statement st		=	null;
		ResultSet rs		=	null;
		int count = -1;

		DBConnection dbConn = new DBConnection();
		con=dbConn.connect();
		String query	=	"	select count(userid) from user_login_dtls " +
							"	where 0	= 0	" ;
				if(currentDate!=null && currentDate.length()>0)
				{
				query   +=	"	   			and login_date = '"+currentDate+"'	" ;
				}
				if(userId != null && userId.length()>0)
				{
				query	+=	"				 and userid = "+userId+"	";
				}
				
		try{
			st=con.createStatement() ;
			rs=st.executeQuery(query);
			if(rs.next())
			{
				count 	=	rs.getInt(1);	
			}
		}catch(Exception e)
		{
			System.out.println("Error in closing connection in LoginBean.checkForTodaysLogin(): "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in LoginBean.checkForTodaysLogin(): "+s);
			}
		}	
		return count;
	}
	
	
	/**
	 * Method Name  : updateLogoutDetails
	 * Purpose		: To updateLogoutDetails
	 * @author		: Prashant
	 * Date			: 23/10/2012
	 * @param  
	 */
	public int updateLogoutDetails(String currentDate, String userId) 
	{
		DBConnection dbConn = new DBConnection();
	
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		int cnt	=	-1;
	
		try
		{

				query		+=	"	update user_login_dtls" +
								" 	set 			logged_out_date  =  sysdate()	" +
								"	where  			login_date	     = 	? " +
								"			and 	userid    		 =  ? " ;
				
				
				con			=	dbConn.connect();
				pst			=	con.prepareStatement(query);
				
				
				pst.setString(1, currentDate);
				pst.setString(2, userId);
				
				cnt		=	pst.executeUpdate();
				
			
		}
		catch(Exception e)
		{
			
			System.out.println("ERROR In LoginBean.updateLogoutDetails:"+e);
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
				
				System.out.println("Exception While Closing The Connection in LoginBean.updateLogoutDetails:"+e);
			}
		}
		return cnt;
	}
	
	
	/**
	 * Method Name  : getSearchLoginDetails
	 * Purpose		: To getSearchLoginDetails
	 * @author		: Prashant
	 * Date			: 23/10/2012
	 * @param  
	 */
	public ArrayList<CommonForm> getSearchLoginDetails(LoginForm loginform)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		DBConnection dbConn = new DBConnection();
		
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		
		String			userId		=	loginform.getUserID();
		String			fromDt		=	loginform.getFromDate();
		String			toDt		=	loginform.getToDate();	
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		try
		{
			
			query	+=	"	select um.userid,date_format(um.login_date, '%d/%m/%Y'),um.ip_address,date_format(um.logged_in_date, '%d/%m/%Y %h:%i %p'),date_format(um.logged_out_date, '%d/%m/%Y %h:%i %p'),em.employee_name " +
						"	from 		  user_login_dtls 	um " +
						"	inner join 	  usermst 			umst on umst.userid 	= 	um.userid" +
						"	inner join 	  employee_master 	em 	 on umst.employeeid = 	em. employeeid " +	
						"	where 0 = 0 " ;
					
			if(userId!=null && userId.length() > 0)
			{
			query	+=	"			and um.userid = '"+userId+"' 	" ;
			}
			if(fromDt!=null && fromDt.length() > 0)
			{
			query 	+=	"			and	um.login_date 	>=	str_to_date('"+fromDt+"','%d/%m/%Y')";
			}
			if(toDt!=null && toDt.length() > 0)
			{
			query 	+=	"			and	um.login_date 	<=	str_to_date('"+toDt+"','%d/%m/%Y')";
			}
			
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//userid
				cForm.setField3(rs.getString(2)==null ? "-" : rs.getString(2));//login_date
				cForm.setField4(rs.getString(3));//ip_address
				cForm.setField5(rs.getString(4)==null ? "-" : rs.getString(4));//logged_in_date
				cForm.setField6(rs.getString(5)==null ? "-" : rs.getString(5));//logged_out_date
				cForm.setField7(rs.getString(6)!=null && rs.getString(6).length()>0 ? rs.getString(6) : "-");//employee_name
		
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In LoginBean.getSearchLoginDetails :"+e);
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
				System.out.println("Exception While Closing The Connection in LoginBean.getSearchLoginDetails :"+e);
			}
		}
		return dataList;
	}
}

