package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.GroupForm;
import com.psa.mj.DBConnection.DBConnection;

public class GroupBean 
{
	DBConnection	dbConn	=	new DBConnection();

	/**
	 * Method Name  : saveGroupMaster
	 * Purpose		: To saveGroupMaster
	 * @author		: Mahesh
	 * Date			: 12/03/2012 
	 */
	public String saveGroupMaster(GroupForm groupForm, String userName) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		String				groupName			=	groupForm.getGroupName().trim().toUpperCase();
		String				dataType			=	groupForm.getDataType().trim().toUpperCase();
		String				allowMultiple		=	groupForm.getAllowMultiple();
		String				allowModify			=	groupForm.getAllowModify();
		String				status				=	groupForm.getStatus();
		try
		{
			
			if(commonMethodBean.checkDuplicate("groupmst","group_name",groupName) == false)
			{
				outputString	=	"FAIL.GROUP NAME : "+groupName+" IS ALREADY PRESENT.";
			}
			else
			{	
				con		=	dbConn.connect();
				query	+=	"	insert into groupmst (group_name, datatype, allow_multiple, allow_modify, status, createdby, createdon	)" +
							"	values (?, ?, ?, ?, ?, ?, sysdate() )";
				pst		=	con.prepareStatement(query);
				pst.setString(1,groupName);
				pst.setString(2,dataType);
				pst.setString(3,allowMultiple);
				pst.setString(4,allowModify);
				pst.setString(5,status);
				pst.setString(6,userName);
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.GROUP NAME : "+groupName+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In GroupBean.saveGroupMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in GroupBean.saveGroupMaster :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : getSearchGroupList
	 * Purpose		: To getSearchGroupList
	 * @author		: Mahesh
	 * Date			: 12/03/2012 
	 */

	public ArrayList<CommonForm> getSearchGroupList(GroupForm groupFrom) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			groupId	=	groupFrom.getGroupId();
		String			dataType	=	groupFrom.getDataType();
		String			allowMultiple	=	groupFrom.getAllowMultiple();
		String			allowModify		=	groupFrom.getAllowModify();
		String			status		=	groupFrom.getStatus();
		try
		{
			query	+=	"	select groupid, group_name, datatype, allow_multiple, allow_modify, status " +
						"	from   groupmst " +
						"	where  0=0";
			if(groupId!= null && groupId.length() > 0)
			{	
			query	+=	"       	and	groupid		=	'"+groupId+"'";
			}
			if(dataType!= null && dataType.length() > 0)
			{
			query	+=	"       	and	datatype	=	'"+dataType+"'";
			}
			if(allowMultiple!= null && allowMultiple.length() >0)
			{
			query	+=	"		    and	allow_multiple	=	'"+allowMultiple+"'";
			}
			if(allowModify!= null && allowModify.length() > 0)
			{
			query	+=	"       	and	allow_modify	=	'"+allowModify+"'";
			}
			if(status!= null && status.length() > 0)
			{	
			query	+=	"       	and	status 		=	'"+status+"'";
			}
			query	+=	"	order by	group_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//groupid
				cForm.setField3(rs.getString(2));//group_name
				cForm.setField4(rs.getString(3));//datatype
				cForm.setField5(rs.getString(4));//allow_multiple
				cForm.setField6(rs.getString(5));//allow_modify
				cForm.setField7(rs.getString(6));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In GroupBean.getSearchGroupList : "+e);
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
				System.out.println("Exception While Closing The Connection in getSearchGroupList.getSearchGroupList: "+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public ArrayList<CommonForm> getDataForModify(String groupId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=	"	select	groupid, group_name, datatype, allow_multiple, allow_modify, status" +
						"	from   	groupmst " +
						"	where	groupid	=	'"+groupId+"'";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//groupid
				commonForm.setField2(rs.getString(2));//group_name
				commonForm.setField3(rs.getString(3));//datatype
				commonForm.setField4(rs.getString(4));//allow_multiple
				commonForm.setField5(rs.getString(5));//allow_modify
				commonForm.setField6(rs.getString(6));//status
				
				dataList.add(commonForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In GroupBean.getDataForModify : "+e);
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
				System.out.println("Exception While Closing The Connection in GroupBean.getDataForModify : "+e);
			}
		}
		return	dataList;
	}
	
	/**
	 * Method Name  : getModifyGroupMaster
	 * Purpose		: To getModifyGroupMaster
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public String getModifyGroupMaster(GroupForm groupForm, String userName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString		=	"";
		Connection		con					=	null;
		PreparedStatement	pst				=	null;
		String			query				=	"";
		String			groupId				=	groupForm.getGroupId();
		String			groupName			=	groupForm.getGroupName().trim().toUpperCase();
		String			dataType			=	groupForm.getDataType().trim().toUpperCase();
		String			allowMultiple		=	groupForm.getAllowMultiple();
		String			allowModify			=	groupForm.getAllowModify();
		String			status				=	groupForm.getStatus();
		
		try
		{
			
			if(commonMethodBean.checkDuplicateForModification("groupmst", "group_name", groupName, "groupid", groupId) == false)
			{
				
				outputString	=	"FAIL.GROUP NAME : "+groupName+" IS ALREADY PRESENT.";
			}	
			else
			{	
					query		+=	"	update	groupmst " +
									"	set		group_name		=	?, " +
									"			datatype		=	?, " +
									"			allow_multiple	=	?, " +
									"			allow_modify	=	?, " +
									"			status			=	? " +
									"	where	groupid			=	?  ";
					
					con			=	dbConn.connect();
					pst			=	con.prepareStatement(query);
					pst.setString(1,groupName);
					pst.setString(2,dataType);
					pst.setString(3,allowMultiple);
					pst.setString(4,allowModify);
					pst.setString(5,status);
					pst.setString(6,groupId);
					
					int	cnt		=	pst.executeUpdate();
					if(cnt > 0)
					{
						outputString	=	"PASS.GROUP NAME : "+groupName+" MODIFY SUCCESSFULLY.";
					}
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In GroupBean.getModifyGroupMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in GroupBean.getModifyGroupMaster :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : deleteGroup
	 * Purpose		: To deleteGroup
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public String deleteGroup(String groupId, String groupName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString		=	"";
		Connection		con					=	null;
		Statement		st					=	null;
		String			query				=	"";
		int 			count				=	0;
		int				forDeleteCount		=	0;
		try
		{
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("groupvalue", "groupvalueid", "groupid" ,groupId);
			
			if(forDeleteCount > 0)
			{
				outputString	=	"FAIL.CANNOT DELETE : GROUP VALUES ARE PRESENT AGAINST GROUP : "+groupName;
			}
			
			else
			{	
				query			+=	"	delete " +
									"	from	groupmst " +
									"	where	groupid		=	'"+groupId+"'";
				
				con				=	dbConn.connect();
				st				=	con.createStatement();
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.GROUP NAME : "+groupName+" DELETE SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.GROUP NAME : "+groupName+" NOT DELETE.";
				}
			}	
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In GroupBean.deleteGroup :"+e);
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
				System.out.println("Exception While Closing The Connection in GroupBean.deleteGroup :"+e);
			}
		}
		return outputString;
	}
}
