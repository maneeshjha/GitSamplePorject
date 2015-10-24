package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.StateForm;
import com.psa.mj.DBConnection.DBConnection;

public class StateBean 
{
	DBConnection	dbConn	=	new DBConnection();

	/**
	 * Method Name  : getSearchStateList
	 * Purpose		: To getSearchStateList
	 * @author		: Prashant
	 * Date			: 20/03/2012 
	 */
	public ArrayList<CommonForm> getSearchStateList(StateForm stateForm) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			countryId	=	stateForm.getCountryId();
		String			stateId		=	stateForm.getStateId();
		String			status		=	stateForm.getStatus();
		try
		{
			
			query	+=	"	select 	st.stateid, st.countryid,cm.country_name, st.state_code, st.state_name, st.status " +
						"	from	state_master st " +
						"	inner join  country_master cm	on	st.countryid	=	cm.countryid ";
			if(countryId!= null && countryId.length() > 0)
			{
			query	+=	"									and	st.countryid	=	'"+countryId+"'";
			}
			if(stateId!= null && stateId.length() > 0)
			{
			query	+=	"									and	st.stateid		=	'"+stateId+"'";
			}
			if(status!= null && status.length() > 0)
			{
			query	+=	"									and	st.status		=	'"+status+"'";
			}
			query	+=	"	order by cm.country_name,st.state_name ";
		
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//stateid
				cForm.setField3(rs.getString(2));//countryid
				cForm.setField4(rs.getString(3));//country_name
				cForm.setField5(rs.getString(4));//state_code
				cForm.setField6(rs.getString(5));//state_name
				cForm.setField7(rs.getString(6));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In StateBean.getSearchStateList :"+e);
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
				System.out.println("Exception While Closing The Connection in StateBean.getSearchStateList :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : saveStateMaster
	 * Purpose		: To saveStateMaster
	 * @author		: Prashant
	 * Date			: 20/03/2012 
	 */
	public String saveStateMaster(StateForm stateForm, String userName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				countryId			=	stateForm.getCountryId();
		String				stateCode			=	stateForm.getStateCode().trim().toUpperCase();
		String				stateName			=	stateForm.getStateName().trim().toUpperCase();
		String				status				=	stateForm.getStatus();
		String				query				=	"";
		boolean				flag				=	true;
		try
		{
			
			if(commonMethodBean.checkDuplicationAgainstGroup("state_name","state_master","countryid",countryId,stateName)	==	false)
			{
				flag	=	false;
				outputString	=	"FAIL.STATE NAME : "+stateName+" IS ALREADY PRESENT.";
				//outputString	=	"FAIL.STATE NAME : "+stateName+" IS ALREADY PRESENT AGAINST COUNTRY : "+countryName+".";
			}
			if(flag	==	true)
			{	
				con		=	dbConn.connect();
				query	+=	"	insert into state_master (countryid, state_code, state_name, status, createdby, createdon, updatedby, updatedon )" +
							"	values (?, ?, ?, ?, ?, sysdate(), ?, sysdate() )";
				pst		=	con.prepareStatement(query);
				
				pst.setString(1,countryId);
				pst.setString(2,stateCode);
				pst.setString(3,stateName);
				pst.setString(4,status);
				pst.setString(5,userName);
				pst.setString(6,userName);
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.STATE NAME : "+stateName+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In StateBean.saveDepartmentMaster:"+e);
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
				System.out.println("Exception While Closing The Connection in StateBean.saveDepartmentMaster:"+e);
			}
		}
		return outputString;
	}
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 20/03/2012 
	 */
	public ArrayList<CommonForm> getDataForModify(String stateId) 
	{
		ArrayList<CommonForm>dataList	=	new ArrayList<CommonForm>();
		Connection			con		=	null;
		Statement			st		=	null;
		ResultSet			rs		=	null;
		String				query	=	"";
		try
		{
				query	+=	"	select 	st.stateid, st.countryid,cm.country_name, st.state_code, st.state_name, st.status " +
							"	from	state_master st " +
							"	inner join  country_master cm	on	st.countryid	=	cm.countryid " +
							"									and	st.stateid		=	'"+stateId+"'";
				con		=	dbConn.connect();
				st		=	con.createStatement();
				rs		=	st.executeQuery(query);
				while(rs.next())
				{
					CommonForm	cForm	=	new CommonForm();
					cForm.setField1(rs.getString(1));//stateid
					cForm.setField2(rs.getString(2));//countryid
					cForm.setField3(rs.getString(3));//country_name
					cForm.setField4(rs.getString(4));//state_code
					cForm.setField5(rs.getString(5));//state_name
					cForm.setField6(rs.getString(6));//status
					
					dataList.add(cForm);
				}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In StateBean.getDataForModify :"+e);
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
				System.out.println("Exception While Closing The Connection in StateBean.getDataForModify :"+e);
			}
		}
		return	dataList;
	}
	
	/**
	 * Method Name  : getModifyStateMaster
	 * Purpose		: To getModifyStateMaster
	 * @author		: Prashant
	 * Date			: 20/03/2012 
	 */
	public String getModifyStateMaster(StateForm stateFrom, String userName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		
		String				stateId				=	stateFrom.getStateId();
		String				countryId			=	stateFrom.getCountryId();
		String				stateCode			=	stateFrom.getStateCode().trim().toUpperCase();
		String				stateName			=	stateFrom.getStateName().trim().toUpperCase();
		String				status				=	stateFrom.getStatus();
		int					count				=	0;
		boolean				flag				=	true;
		int					totalCount			=	0;
		try
		{
			
			count	=	commonMethodBean.checkDuplicationAgainstGroupForModification("state_name","state_master","countryid","stateid",stateName,countryId,stateId);	
			if(count > 0)
			{
				flag			=	false;
				totalCount		=	1;
				outputString	=	"FAIL.STATE NAME : "+stateName+" IS ALREADY PRESENT.";
			}
			
			if(count == 0)
			{	
				count	=	commonMethodBean.checkForActive("city_master","stateid", "stateid", stateId); 
				if(count > 0)
				{
					if(status.equalsIgnoreCase("INACTIVE"))
					{
						flag			=	false;
						totalCount		=	1;
						outputString	=	"FAIL.CANNOT MODIFY : CITIES ARE PRESENT AGAINST STATE NAME : "+stateName;
					}
				}	
			}
			
			if(totalCount == 0)
			{
				count	=	commonMethodBean.checkForIsProperDelete("city_master", "cityid", "stateid",stateId);
				if(count > 0)
				{
					flag			=	false;
					outputString	=	"FAIL.CANNOT MODIFY : CITIES ARE PRESENT AGAINST STATE NAME : "+stateName;
				}
			}
			if(flag	==	true)
			{	
					query		+=	"	update	state_master" +
									"	set 	countryid	=	?, " +
									"			state_code	=	?, " +
									"			state_name	=	?, " +
									"			status		=	?, " +
									"			updatedby	=	?, " +
									"			updatedon	=	sysdate()" +
									"	where	stateid		=	? ";	
					
					con			=	dbConn.connect();
					pst			=	con.prepareStatement(query);
					pst.setString(1,countryId);
					pst.setString(2,stateCode);
					pst.setString(3,stateName);
					pst.setString(4,status);
					pst.setString(5,userName);
					pst.setString(6,stateId);
					
					int	cnt		=	pst.executeUpdate();
					if(cnt > 0)
					{
						outputString	=	"PASS.STATE NAME : "+stateName+" MODIFIED SUCCESSFULLY.";
					}
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In StateBean.getModifyStateMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in StateBean.getModifyStateMaster :"+e);
			}
		}
		return outputString;
	}

	/**
	 * Method Name  : deleteCity
	 * Purpose		: To deleteCity
	 * @author		: Prashant
	 * Date			: 20/03/2012 
	 */
	public String deleteCity(String stateId, String stateName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString			=	"";
		Connection		con						=	null;
		Statement		st						=	null;
		String			query					=	"";
		int 			count					=	0;
		int				forDeleteCount			=	0;
		try
		{
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("city_master", "cityid", "stateid",stateId);
			if(forDeleteCount > 0)
			{
				outputString	=	"FAIL.CANNOT DELETE : CITIES ARE PRESENT AGAINST STATE : "+stateName;
			}	
			else
			{	
				query			+=		"	delete	" +
										"	from	state_master" +
										"	where	stateid		=	'"+stateId+"'";	
				con				=	dbConn.connect();
				st				=	con.createStatement();
				
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.STATE NAME : "+stateName+" DELETED SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.STATE NAME : "+stateName+" NOT DELETE.";
				}
			}	
				
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CountryBean.deleteCountry:"+e);
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
				System.out.println("Exception While Closing The Connection in CountryBean.deleteCountry:"+e);
			}
		}
		return outputString;
	}
}
