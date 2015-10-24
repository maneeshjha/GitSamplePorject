package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CountryForm;
import com.psa.mj.DBConnection.DBConnection;

public class CountryBean 
{
	DBConnection	dbConn	=	new DBConnection();
	
	/**
	 * Method Name  : saveCountryMaster
	 * Purpose		: To saveCountryMaster
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * 
	 */

	public String saveCountryMaster(CountryForm countryForm, String userName) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString			=	"";
		Connection		con						=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		String				countryCode			=	countryForm.getCountryCode().trim().toUpperCase();
		String				countryName			=	countryForm.getCountryName().trim().toUpperCase();
		String				status				=	countryForm.getStatus();
		try
		{
			
			if(commonMethodBean.checkDuplicate("country_master","country_name",countryName) == false)
			{
				outputString	=	"FAIL.COUNTRY NAME : "+countryName+" IS ALREADY PRESENT.";
			}
			else
			{	
				con		=	dbConn.connect();
				query	=	"	insert into country_master " +
							"	(country_code, country_name, status, createdby, createdon, updatedby, updatedon )" +
							"	values	(?,?,?,?,sysdate(),?,sysdate()) ";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1,countryCode);
				pst.setString(2,countryName);
				pst.setString(3,status);
				pst.setString(4,userName);
				pst.setString(5,userName);		
			
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.COUNTRY NAME : "+countryName+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CountryBean.saveCountryMaster:"+e);
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
				System.out.println("Exception While Closing The Connection in CountryBean.saveCountryMaster:"+e);
			}
		}
		return outputString;
	}

	/**
	 * Method Name  : getSearchList
	 * Purpose		: To getSearchList
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * 
	 */
	public ArrayList<CommonForm> getSearchCountryList(CountryForm countryForm) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			countryId	=	countryForm.getCountryId();
		String			status		=	countryForm.getStatus();
		String			countryCode	=	countryForm.getCountryCode()!= null && countryForm.getCountryCode().length() > 0 ? countryForm.getCountryCode().trim().toUpperCase(): "";
		try
		{
			query	+=	"	select 	countryid, country_code, country_name, status" +
						"	from 	country_master" +
						"	where 	0=0";
			if(countryId!= null && countryId.length() > 0)
			{	
			query	+=	"				and countryid		=	'"+countryId+"'";
			}
			if(countryCode!= null && countryCode.length() > 0)
			{	
			query	+=	"				and country_code	=	'"+countryCode+"'";
			}
			if(status!= null && status.length() > 0)	
			{
			query	+=	"				and status			=	'"+status+"'";
			}
			query	+=	"	order by	country_name ";	
				
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//countryid
				cForm.setField3(rs.getString(2));//country_code
				cForm.setField4(rs.getString(3));//country_name
				cForm.setField5(rs.getString(4));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CountryBean.getSearchCountryList:"+e);
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
				System.out.println("Exception While Closing The Connection in CountryBean.getSearchCountryList:"+e);
			}
		}
		return dataList;
	}

	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * 
	 */
	public ArrayList<CommonForm> getDataForModify(String countryId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			query	+=		"	select 	countryid, country_code, country_name, status" +
							"	from 	country_master " +
							"	where 	countryid	=	'"+countryId+"'";
			
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				CommonForm	cForm	=	new CommonForm();
				
				cForm.setField1(rs.getString(1));//countryid
				cForm.setField2(rs.getString(2));//country_code
				cForm.setField3(rs.getString(3));//country_name
				cForm.setField4(rs.getString(4));//status
				
				dataList.add(cForm);
			}	
			
		}
		catch (Exception e) 
		{
			System.out.println("ERROR In CountryBean.getDataForModify:"+e);
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
				System.out.println("Exception While Closing The Connection in CountryBean.getDataForModify :"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getModifyCountryMaster
	 * Purpose		: To getModifyCountryMaster
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * 
	 */
	public String getModifyCountryMaster(CountryForm countryForm, String userName) 
	{
		
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		String				countryId			=	countryForm.getCountryId();
		String				countryCode			=	countryForm.getCountryCode().trim().toUpperCase();
		String				countryName			=	countryForm.getCountryName().trim().toUpperCase();
		String				status				=	countryForm.getStatus();
		boolean				flag				=	true;	
		int 				count				=	0;
		int					totalCount			=	0;
		try
		{
			if(commonMethodBean.checkDuplicateForModification("country_master", "country_name", countryName, "countryid", countryId) == false)
			{
				flag			=	false;
				outputString	=	"FAIL.COUNTRY NAME : "+countryName+" IS ALREADY PRESENT.";
			}	
			
			count	=	commonMethodBean.checkForActive("state_master","countryid", "countryid", countryId);
			if(count > 0)
			{
				if(status.equalsIgnoreCase("INACTIVE"))
				{	
					flag			=	false;
					totalCount		=	1;
					outputString	=	"FAIL.CANNOT MODIFY  : STATES ARE PRESENT AGAINST COUNTRY NAME : "+countryName;
				}	
			}	
			if(count == 0)
			{	
				count	=	commonMethodBean.checkForActive("city_master","countryid", "countryid", countryId); 
				if(count > 0)
				{
					if(status.equalsIgnoreCase("INACTIVE"))
					{	
						flag			=	false;
						totalCount		=	1;
						outputString	=	"FAIL.CANNOT MODIFY  : CITIES ARE PRESENT AGAINST COUNTRY NAME : "+countryName;	
					}
				}
			}
			
			if(totalCount == 0)
			{	
				count			=	commonMethodBean.checkForIsProperDelete("state_master", "stateid", "countryid",countryId);
				if(count > 0)
				{
					flag			=	false;
					totalCount		=	1;
					outputString	=	"FAIL.CANNOT MODIFY : STATES ARE PRESENT AGAINST COUNTRY NAME : "+countryName;
				}
			}
			if(totalCount == 0)
			{	
				count			=	commonMethodBean.checkForIsProperDelete("city_master", "cityid", "countryid",countryId);
				if(count > 0)
				{
					flag			=	false;
					outputString	=	"FAIL.CANNOT MODIFY : CITIES ARE PRESENT AGAINST COUNTRY NAME : "+countryName;
				}
			}
			
			if(flag == true)
			{	
					query	+=	"	update country_master " +
								"	set    country_code	=	?," +
								"          country_name	=	?," +
								"          status		=	?," +
								"          updatedby	=	?," +
								"    	   updatedon	=	sysdate()" +
								"	where  countryid	=	? ";
					
					con		=	dbConn.connect();
					pst		=	con.prepareStatement(query);
					pst.setString(1,countryCode);
					pst.setString(2,countryName);
					pst.setString(3,status);
					pst.setString(4,userName);
					pst.setString(5,countryId);
					
					int	cnt		=	pst.executeUpdate();
					if(cnt > 0)
					{
						outputString	=	"PASS.COUNTRY NAME : "+countryName+" MODIFIED SUCCESSFULLY.";
					}
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CountryBean.getModifyRoleMaster:"+e);
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
				System.out.println("Exception While Closing The Connection in CountryBean.getModifyRoleMaster:"+e);
			}
		}
		return outputString;
	}

	/**
	 * Method Name  : deleteCountry
	 * Purpose		: To deleteCountry
	 * @author		: Prashant
	 * Date			: 07/03/2012
	 * 
	 */
	public String deleteCountry(String countryId, String countryName) 
	{	
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		Statement			st					=	null;
		String				query				=	"";
		int 				count				=	0;
		int					forDeleteCount		=	0;
		boolean				flag				=	true;
		try
		{
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("state_master", "stateid", "countryid",countryId);
			if(forDeleteCount > 0)
			{
				flag			=	false;
				outputString	=	"FAIL.CANNOT DELETE : STATES ARE PRESENT AGAINST COUNTRY : "+countryName;
			}
			
			forDeleteCount	=	0;
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("city_master", "cityid", "countryid",countryId);
			if(forDeleteCount > 0)
			{
				flag			=	false;
				outputString	=	"FAIL.CANNOT DELETE : CITIES ARE PRESENT AGAINST COUNTRY : "+countryName;
			}
			if(flag == true)
			{	
				query			+=		"	delete	" +
										"	from 	country_master";
				if(countryId!= null && countryId.length() > 0)
				{	
					query		+=		"	where	countryid	=	'"+countryId+"'";
				}	
				con				=	dbConn.connect();
				st				=	con.createStatement();
				
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.COUNTRY NAME : "+countryName+" DELETED SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.COUNTRY NAME : "+countryName+" NOT DELETE.";
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
