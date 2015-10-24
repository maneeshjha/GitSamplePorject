package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CommonMasterForm;
import com.psa.mj.DBConnection.DBConnection;

public class CommonMasterBean 
{
	DBConnection	dbConn	=	new DBConnection();

	/**
	 * Method Name  : getGroupName
	 * Purpose		: To getGroupName
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public ArrayList<CommonForm> getGroupName() 
	{
		ArrayList<CommonForm>dataList	=	new ArrayList<CommonForm>();
		Connection	con		=	null;
		Statement	st		=	null;
		ResultSet	rs		=	null;
		String		query	=	"";
		try
		{
			query	+=	"	select	groupid, group_name" +
						"	from   	groupmst " +
						"	where		status	=	'ACTIVE'" +
						"	order by 	group_name";
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	commonForm	=	new CommonForm();
				commonForm.setField1(rs.getString(1));//groupid
				commonForm.setField2(rs.getString(2));//group_name
				
				dataList.add(commonForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMasterBean.getGroupName : "+e);
		}
		finally
		{
			try
			{
				if(con	!= null)
				{
					con.close();
					con	=	null;
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception While Closing The Connection In CommonMasterBean.getGroupName : "+e);
			}
		}
		return	dataList;
	}
	
	/**
	 * Method Name  : getGroupWiseGroupDataType
	 * Purpose		: To getGroupWiseGroupDataType
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public ArrayList<CommonForm> getGroupWiseGroupDataType(String groupId) 
	{
		ArrayList<CommonForm>dataList	=	new ArrayList<CommonForm>();
		Connection	con		=	null;
		Statement	st		=	null;
		ResultSet	rs		=	null;
		String		query	=	"";
		try
		{
			query		+=	"	select	group_name,datatype, allow_multiple, allow_modify" +
							"	from	groupmst" +
							"	where	groupid		=	'"+groupId+"'";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//group_name
				cForm.setField2(rs.getString(2));//datatype
				cForm.setField3(rs.getString(3));//allow_multiple
				cForm.setField4(rs.getString(4));//allow_modify
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMasterBean.getGroupName : "+e);
		}
		finally
		{
			try
			{
				if(con	!= null)
				{
					con.close();
					con	=	null;
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception While Closing The Connection In CommonMasterBean.getGroupName : "+e);
			}
		}
		return dataList;
	}

	/**
	 * Method Name  : saveGroupValueMaster
	 * Purpose		: To saveGroupValueMaster
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 */
	public String saveGroupValueMaster(CommonMasterForm commonMasterForm,String userName) 
	{
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		String				groupName			=	commonMasterForm.getGroupName();
		String				allowMultiple		=	commonMasterForm.getAllowMultiple();
		String				groupId				=	commonMasterForm.getGroupId();
		String				groupValue			=	commonMasterForm.getGroupValue().trim().toUpperCase();
		String				description			=	commonMasterForm.getDescription()!= null && commonMasterForm.getDescription().length() > 0 ? commonMasterForm.getDescription().trim().toUpperCase():"";
		String				status				=	commonMasterForm.getStatus();
		boolean				flag				=	true;	
		
		try
		{
			if(allowMultiple!= null && allowMultiple.length() > 0 && allowMultiple.equalsIgnoreCase("N"))
			{
				int count	=	commonMasterBean.AllowMultipleWiseGroupValue(groupId);
				if(count	> 0)
				{
					flag			=	false;
					outputString	=	"FAIL.YOU CANNOT ENTER MULTIPLE VALUE FOR : "+groupName ;
				}
			}
			
			if(allowMultiple!= null && allowMultiple.length() > 0 && allowMultiple.equalsIgnoreCase("Y"))
			{
				if(commonMasterBean.checkDuplicateAgaintsGroupId("groupvalue","groupvalue_name","groupid",groupValue,groupId) == false)
				{
					flag			=	false;
					outputString	=	"FAIL."+groupValue+" UNDER GROUP : "+groupName+" IS ALREADY PRESENT.";
				}
			}
			if(flag	==	true)
			{
				con		=	dbConn.connect();
				query	+=	"	insert into groupvalue	(groupid, groupvalue_name, description, status, createdby, createdon, updatedby, updatedon )" +
							"	values	(?, ?, ?, ?, ?, sysdate(), ?, sysdate()	)";
				pst		=	con.prepareStatement(query);
				
				pst.setString(1,groupId);
				pst.setString(2,groupValue);
				pst.setString(3,description);
				pst.setString(4,status);
				pst.setString(5,userName);
				pst.setString(6,userName);
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS."+groupValue+" UNDER GROUP : "+groupName+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}		
			
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CommonMasterBean.saveGroupValueMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in CommonMasterBean.saveGroupValueMaster :"+e);
			}
		}
		return outputString;
	}
	
	

	/**
	 * Method Name  : AllowMultipleWiseGroupValue
	 * Purpose		: To AllowMultipleWiseGroupValue
	 * @author		: Mahesh
	 * Date			: 13/03/2012 
	 * @param groupId 
	 */
	private int AllowMultipleWiseGroupValue(String groupId) 
	{
		Connection con 	= dbConn.connect();
		int count		=	0;
		try
		{
			String	query	=	"	select	count(groupvalue_name)" +
								"	from	groupvalue" +
								"	where	groupid		=	'"+groupId+"'";
			Statement	st	=	con.createStatement();
			ResultSet	rs	=	st.executeQuery(query);
			if(rs.next())
			{
				count	=	rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			
			System.out.println("ERROR In CommonMasterBean.AllowMultipleWiseGroupValue :"+e);
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
				
				System.out.println("Exception While Closing The Connection in CommonMasterBean.AllowMultipleWiseGroupValue :"+e);
			}
		}
		
		return	count;
	}
	
	/**
	 * Method Name  : getSearchGroupValueList
	 * Purpose		: To getSearchGroupValueList
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	public ArrayList<CommonForm> getSearchGroupValueList(CommonMasterForm commonMasterForm) 
	{
		ArrayList<CommonForm>dataList	=	new ArrayList<CommonForm>();
		Connection	con		=	null;
		Statement	st		=	null;
		ResultSet	rs		=	null;
		String		query	=	"";
		int			srNo	=	1;
		String		groupId			=	commonMasterForm.getGroupId();
		String		groupValueId	=	commonMasterForm.getGroupValueId();
		String		status			=	commonMasterForm.getStatus();
		try
		{
			query	+=	"	select 		gv.groupvalueid, gv.groupid, gv.groupvalue_name,gm.group_name,gv.description, gv.status,gm.allow_modify " +
						"	from		groupvalue gv " +
						"	inner join 	groupmst gm	on	gv.groupid		=	gm.groupid";
			if(groupId!= null && groupId.length() > 0)
			{
			query	+=	"							and	gv.groupid		=	'"+groupId+"'";
			}
			if(groupValueId!= null && groupValueId.length() > 0)
			{
			query	+=	"							and	gv.groupvalueid		=	'"+groupValueId+"'";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"							and	gv.status		=	'"+status+"'";
			}
			query	+=	"	order by	gm.group_name,gv.groupvalue_name";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//groupvalueid		
				cForm.setField3(rs.getString(2));//groupid
				cForm.setField4(rs.getString(3));//groupvalue_name
				cForm.setField5(rs.getString(4));//group_name
				cForm.setField6(rs.getString(5)!= null && rs.getString(5).length() > 0 ? rs.getString(5):"-");//description
				cForm.setField7(rs.getString(6));//status
				cForm.setField8(rs.getString(7));//allow_modify
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			
			System.out.println("ERROR In CommonMasterBean.getSearchGroupValueList :"+e);
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
				
				System.out.println("Exception While Closing The Connection in CommonMasterBean.getSearchGroupValueList :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	public ArrayList<CommonForm> getDataForModify(String groupValueId)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
		
			query	+=	"	select 		gv.groupvalueid, gv.groupid, gv.groupvalue_name, gv.description, gv.status, " +
						"				gm.datatype,gm.allow_multiple,gm.allow_modify	" +
						"	from		groupvalue gv " +
						"	inner join 	groupmst gm	on	gv.groupid		=	gm.groupid " +
						"				and	gv.groupvalueid		=	'"+groupValueId+"' ";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				
				cForm.setField1(rs.getString(1));//groupvalueid
				cForm.setField2(rs.getString(2));//groupid
				cForm.setField3(rs.getString(3));//groupvalue_name
				cForm.setField4(rs.getString(4));//description
				cForm.setField5(rs.getString(5));//status
				cForm.setField6(rs.getString(6));//datatype
				cForm.setField7(rs.getString(7));//allow_multiple
				cForm.setField8(rs.getString(8));//allow_modify

				
				dataList.add(cForm);
			}
		
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CommonMasterBean.getDataForModify :"+e);
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
				System.out.println("Exception While Closing The Connection in CommonMasterBean.getDataForModify:"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getModifyGroupValueMaster
	 * Purpose		: To getModifyGroupValueMaster
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	public String getModifyGroupValueMaster(CommonMasterForm commonMasterForm,String userName) 
	{
		
		CommonMasterBean	commonMasterBean	=	new CommonMasterBean();
		String			outputString		=	"";
		Connection		con					=	null;
		PreparedStatement	pst				=	null;
		String			query				=	"";
		String			groupName			=	commonMasterForm.getGroupName();
		String			groupValueId		=	commonMasterForm.getGroupValueId();
		String			groupValueName		=	commonMasterForm.getGroupValue().trim().toUpperCase();
		String			groupId				=	commonMasterForm.getGroupId();
		String			description			=	commonMasterForm.getDescription()!= null && commonMasterForm.getDescription().length() > 0 ? commonMasterForm.getDescription().trim().toUpperCase() : "";
		String			status				=	commonMasterForm.getStatus();
		try
		{
			if(commonMasterBean.checkDuplicateAgaintsIdWiseModification("groupvalue", "groupvalue_name","groupid","groupvalueid",groupValueName,groupId,groupValueId) == false)
			{
				outputString	=	"FAIL."+groupValueName+" UNDER GROUP : "+groupName+" IS ALREADY PRESENT.";
			}	
			else
			{	
					query		+=	"	update	groupvalue " +
									"	set 	groupid			=	?, " +
									"			groupvalue_name	=	?, " +
									"			description		=	?, " +
									"			status			=	?, " +
									"			updatedby		=	?, " +
									"			updatedon		=	sysdate() " +
									"	where	groupvalueid 	=	? ";
					con			=	dbConn.connect();
					pst			=	con.prepareStatement(query);
					pst.setString(1, groupId);
					pst.setString(2, groupValueName);
					pst.setString(3, description);
					pst.setString(4, status);
					pst.setString(5, userName);
					pst.setString(6, groupValueId);
					
					int	cnt		=	pst.executeUpdate();
					if(cnt > 0)
					{
						outputString	=	"PASS."+groupValueName+" UNDER GROUP : "+groupName+" MODIFIED SUCCESSFULLY.";
					}
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CommonMasterBean.getModifyGroupValueMaster:"+e);
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
				System.out.println("Exception While Closing The Connection in CommonMasterBean.getModifyGroupValueMaster:"+e);
			}
		}
		return outputString;
	}
	
	

	/**
	 * Method Name  : deleteGroupValue
	 * Purpose		: To deleteGroupValue
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	public String deleteGroupValue(String groupId, String groupName) 
	{
		String				outputString		=	"";
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		Connection			con					=	null;
		Statement			st					=	null;
		String				query				=	"";
		int 				count				=	0;
		int					forDeleteCount		=	0;
		boolean				flag				=	true;
		try
		{
				
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("employee_master", "qualification", "employeeid",groupId);
			if(forDeleteCount > 0)
			{
				flag			=	false;
				outputString	=	"FAIL.CANNOT DELETE : EMPLOYEES ARE PRESENT AGAINST GROUP NAME : "+groupName;
			}	
			if(flag == true)
			{
				query			+=	"	delete	" +
									"	from	groupvalue " ;
				if(groupId!= null && groupId.length() > 0)
				{
				query			+=	"	where	groupvalueid	=	'"+groupId+"'";
				}
				con				=	dbConn.connect();
				st				=	con.createStatement();
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.GROUP VALUE : "+groupName+" DELETED SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.GROUP VALUE : "+groupName+" NOT DELETE.";
				}
			}	
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CommonMasterBean.deleteGroupValue :"+e);
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
				System.out.println("Exception While Closing The Connection in CommonMasterBean.deleteGroupValue :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : checkDuplicateAgaintsGroupId
	 * Purpose		: To checkDuplicateAgaintsGroupId
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	private boolean checkDuplicateAgaintsGroupId(String tableName, String columName,String field,String groupValue, String groupId)
	{
		Connection con = dbConn.connect();
		try	
		{
			String query	=	"	select " + columName + " from " + tableName + 
								" 	where " + columName + "='"+ groupValue + "'" +
								"	and	"+ field + "='"+groupId+"'";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			while(result.next()) 
			{
				if((result.getString(columName)).equals(groupValue))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMasterBean.checkDuplicateAgaintsGroupId():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMasterBean.checkDuplicateAgaintsGroupId():- "+s);
			}
		}		
		return true;
	}
	/**
	 * Method Name  : checkDuplicateAgaintsIdWiseModification
	 * Purpose		: To checkDuplicateAgaintsIdWiseModification
	 * @author		: Mahesh
	 * Date			: 14/03/2012 
	 */
	private boolean checkDuplicateAgaintsIdWiseModification(String tablename,String field1name, String field2name, String field3name,String field1value, String field2value, String field3value) 
	{
		Connection con = dbConn.connect();;
		try	
		{
			String query=	"	select	" + field1name + " " +
							"	from	" + tablename + " " +
							"	where	" + field1name + "	=	'"+ field1value + "'" +
							"	and		" + field2name + "	=	'"+ field2value + "'" + 
							"	and		" + field3name + "	<>	'"+ field3value + "'";
			Statement st=con.createStatement();
			ResultSet result=st.executeQuery(query);
			
			while(result.next()) 
			{
				if((result.getString(field1name)).equals(field1value))
					return false;
			}
		}catch(Exception e)	
		{
			System.out.println("Error in CommonMasterBean.checkDuplicateAgaintsIdWiseModification():- "+e);
		}finally 
		{
			try
			{
				if(con != null)
					con.close();
			}catch(Exception s)
			{
				System.out.println("Error in closing connection in CommonMasterBean.checkDuplicateAgaintsIdWiseModification():- "+s);
			}
		}		
		return true;
	}
}
