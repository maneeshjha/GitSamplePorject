package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.UserForm;
import com.psa.mj.DBConnection.DBConnection;

public class UserBean 
{
	DBConnection	dbConn	=	new DBConnection();
	
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	public ArrayList<CommonForm> getRoleList(String string) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=	"	select	roleid,role_name" +
						"	from	rolemst" +
						"	where	status	=	'ACTIVE'" +
						"	order by role_name ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//roleid
				commonForm.setField2(rs.getString(2));//role_name
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

	public String saveUserMaster(UserForm userForm, String userName) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		UserBean			userBean			=	new UserBean();
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		boolean				flag				=	true;
		int					employeeIdCount		=	0;
		String				empId				=	userForm.getEmpId();
		String				displayName			=	userForm.getEmpName();
		String				userType			=	"EMPLOYEE";
		String				loginName			=	userForm.getLoginName().trim();
		String				password			=	userForm.getPassword();
		String				status				=	userForm.getStatus();
		String				roleId				=	userForm.getRoleId();
		String				rarId				=	userBean.getRoleIdWiseResponsibiltyIds(roleId); 
		
		try
		{
			
			if(commonMethodBean.checkDuplicate("usermst","user_name",loginName) == false)
			{
				flag			=	false;
				outputString	=	"FAIL.USER NAME : "+loginName.trim().toUpperCase()+" IS ALREADY PRESENT.";
			}
			
			if(flag	==	true)
			{	
				con		=	dbConn.connect();
				query	+=	"	insert into usermst ( user_name, password, display_name, roleid, rarid, status, " +
							"						 createdby, createdon, updatedby, updatedon )" +
							"	values (?, ?, ?, ?, ?, ?, ?, sysdate(), ?, sysdate() )";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1, loginName);
				pst.setString(2, password);
				pst.setString(3, displayName);
				pst.setString(4, roleId);
				pst.setString(5, rarId);		
				pst.setString(6, status);
				pst.setString(7, userName);
				pst.setString(8,userName);
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.USER NAME : "+loginName.trim().toUpperCase()+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In UserBean.saveRoleMaster :"+e);
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
				outputString = "ERROR";
				System.out.println("Exception While Closing The Connection in UserBean.saveRoleMaster :"+e);
			}
		}
		return outputString ;
	}


	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	private String getRoleIdWiseResponsibiltyIds(String rId) 
	{
		String			outPutString	=	"";
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=	"	select	responsibility_ids" +
						"	from	rolemst" +
						"	where	roleid	=	'"+rId+"'";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				outPutString	=	rs.getString(1);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR In UserBean.getRoleIdWiseResponsibiltyIds:"+e);
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
				System.out.println("Exception While Closing The Connection in UserBean.getRoleIdWiseResponsibiltyIds :"+e);
			}
		}
		return outPutString;
	}
	
	/**
	 * Method Name  : getSearchUserList
	 * Purpose		: To getSearchUserList
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	public ArrayList<CommonForm> getSearchUserList(UserForm userForm)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			userId		=	userForm.getLoginId();
		String			status		=	userForm.getStatus();
		try
		{
			
			query	+=	"	select 	umst.userid, umst.user_name, umst.display_name, umst.roleid, rm.role_name," +
						"			umst.status " +
						"	from	usermst umst " +
						"	inner 		join rolemst 				rm	on	rm.roleid		=	umst.roleid " ;
			if(userId!=null && userId.length() > 0)
			{
			query	+=	"													and	umst.userid	=	'"+userId+"'";
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"													and	umst.status	=	'"+status+"'" ;
			}
			query	+=	"	order by umst.user_name	";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//userid
				cForm.setField3(rs.getString(2).toUpperCase());//user_name
				cForm.setField4(rs.getString(3));//display_name
				cForm.setField5(rs.getString(4));//roleid
				cForm.setField6(rs.getString(5));//role_name
				cForm.setField7(rs.getString(6));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In UserBean.getSearchUserList :"+e);
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
				System.out.println("Exception While Closing The Connection in UserBean.getSearchUserList :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : deleteUserMaster
	 * Purpose		: To deleteUserMaster
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	public String deleteUserMaster(String userId, String userName)
	{
		String				outputString		=	"";
		Connection			con					=	null;
		Statement			st					=	null;
		String				query				=	"";
		int 				count				=	0;
		
		try
		{
			
				query			+=	"	delete	" +
									"	from	usermst " +
									"	where	userid	=	'"+userId+"'";
				con				=	dbConn.connect();
				st				=	con.createStatement();
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.USER NAME : "+userName+" DELETED SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.UESR NAME : "+userName+" NOT DELETE.";
				}		
				
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In UserBean.deleteUserMaster:"+e);
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
				outputString = "ERROR";
				System.out.println("Exception While Closing The Connection in UserBean.deleteUserMaster :"+e);
			}
		}
		return outputString;
	}
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	public ArrayList<CommonForm> getDataForModify(String userId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			
			query	+=	"	select 	umst.userid, umst.user_name, umst.display_name,umst.roleid,umst.status,umst.password " +
						"	from	usermst umst" +
						"	inner 		join rolemst 		rm	on	rm.roleid	=	umst.roleid" +
						"										and	umst.userid	=	'"+userId+"'" ;						
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			if(rs.next())
			{
				
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//userid
				cForm.setField2(rs.getString(2));//user_name		
				cForm.setField3(rs.getString(3));//display_name
				cForm.setField4(rs.getString(4));//roleid
				cForm.setField5(rs.getString(5));//status
				cForm.setField6(rs.getString(6));//password
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In UserBean.getDataForModify :"+e);
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
				System.out.println("Exception While Closing The Connection in UserBean.getDataForModify :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : modifyUserMaster
	 * Purpose		: To modifyUserMaster
	 * @author		: Prashant
	 * Date			: 28/04/2012
	 * @param  
	 */
	public String modifyUserMaster(UserForm userForm, String userName) 
	{
		UserBean			userBean			=	new UserBean();
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		
		String				loginId				=	userForm.getLoginId();
		String				loginName			=	userForm.getLoginName();
		/*String				password			=	userForm.getPassword();
		String				employeeId			=	userForm.getEmpId();*/
		String				displayName			=	userForm.getEmpName();
		String				roleId				=	userForm.getRoleId();
		String				rarid				=	userBean.getRoleIdWiseResponsibiltyIds(roleId);
		String				status				=	userForm.getStatus();
		try
		{

				query		+=	"	update  usermst   " +
								"	set		roleid		=	?, " +
								"			rarid		=	?, " +
								"			status		=	?, " +
								"			updatedby	=	?, " +
								"			updatedon	=	sysdate()," +
								"			display_name=	?" +
								"	where	userid		=	?";
				
				con			=	dbConn.connect();
				pst			=	con.prepareStatement(query);
				
				pst.setString(1, roleId);
				pst.setString(2, rarid);
				pst.setString(3, status);
				pst.setString(4, userName);
				pst.setString(5, displayName);
				pst.setString(6, loginId);
				
				int	cnt		=	pst.executeUpdate();
				if(cnt > 0)
				{
					outputString	=	"PASS.USER NAME : "+loginName.toUpperCase()+" MODIFIED SUCCESSFULLY.";
				}
			
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In UserBean.modifyUserMaster:"+e);
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
				outputString = "ERROR";
				System.out.println("Exception While Closing The Connection in UserBean.modifyUserMaster:"+e);
			}
		}
		return outputString;
	}

}
