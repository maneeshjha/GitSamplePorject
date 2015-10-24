package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.CityForm;
import com.psa.mj.DBConnection.DBConnection;

public class CityBean 
{
	DBConnection	dbConn	=	new DBConnection();

	/**
	 * Method Name  : saveCityMaster
	 * Purpose		: To saveCityMaster
	 * @author		: Prashant
	 * Date			: 09/03/2012 
	 */
	public String saveCityMaster(CityForm cityForm, String userName) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString			=	"";
		Connection		con						=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		String				countryId			=	cityForm.getCountryId();
		String				stateId				=	cityForm.getStateId();
		//String				countryName			=	cityForm.getCountryName();
		String				cityName			=	cityForm.getCityName().trim().toUpperCase();
		String				cityDesc			=	cityForm.getCityDesc()!=null && cityForm.getCityDesc().length() > 0 ? cityForm.getCityDesc().trim().toUpperCase() : "";
		String				status				=	cityForm.getStatus();
		boolean				flag				=	true;
		try
		{
			
			if(commonMethodBean.checkCountryWiseCity(countryId,stateId,cityName)	==	false)
			{
				flag	=	false;
				outputString	=	"FAIL.CITY NAME : "+cityName+" IS ALREADY PRESENT.";
			}
			if(flag	==	true)
			{	
				con		=	dbConn.connect();
				query	+=	"	insert into city_master (countryid,stateid,city_name, city_desc, status, createdby, createdon, updatedby, updatedon )" +
							"	values (?,?,?, ?, ?, ?, sysdate(), ?, sysdate()	)";
				pst		=	con.prepareStatement(query);
				pst.setString(1,countryId);
				pst.setString(2,stateId);
				pst.setString(3,cityName);
				pst.setString(4,cityDesc);
				pst.setString(5,status);
				pst.setString(6,userName);
				pst.setString(7,userName);
				
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.CITY NAME : "+cityName+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CityBean.saveDepartmentMaster:"+e);
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
				System.out.println("Exception While Closing The Connection in CityBean.saveDepartmentMaster:"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : getSearchCityList
	 * Purpose		: To getSearchCityList
	 * @author		: Prashant
	 * Date			: 09/03/2012 
	 */
	public ArrayList<CommonForm> getSearchCityList(CityForm cityForm) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			countryId	=	cityForm.getCountryId();
		String			stateId		=	cityForm.getStateId();
		String			cityId		=	cityForm.getCityId();
		String			status		=	cityForm.getStatus();
		try
		{
			
			query	+=	"	select 		cm.cityid,cm.countryid,cmst.country_name,cm.stateid,smst.state_name,cm.city_name,cm.city_desc,cm.status " +
						"	from 		city_master cm " +
						"	inner join 	country_master cmst 	on	cmst.countryid		=	cm.countryid " +
						"	inner join	state_master   smst		on	smst.stateid		=	cm.stateid ";
						
			if(countryId!= null && countryId.length() > 0)
			{
			query	+=	"			and 	cm.countryid		=	'"+countryId+"'" ;
			}
			if(stateId!= null && stateId.length() > 0)
			{
			query	+=	"			and	cm.stateid				=	'"+stateId+"'" ;
			}
			if(cityId!= null && cityId.length() > 0)
			{			
			query	+=	"			and 	cm.cityid			=	'"+cityId+"'";
			}
			if(status != null && status.length() > 0)
			{		
			query	+=	"			and 	cm.status			=	'"+status+"'";
			}
			query	+=	"	order by	cmst.country_name,smst.state_name,cm.city_name ";
			
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				//cm.cityid,cm.countryid,cmst.country_name,cm.stateid,smst.state_name,cm.city_name,cm.city_desc,cm.status
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//cityid
				cForm.setField3(rs.getString(2));//countryid
				cForm.setField4(rs.getString(3));//country_name
				cForm.setField5(rs.getString(4));//stateid
				cForm.setField6(rs.getString(5));//state_name
				cForm.setField7(rs.getString(6));//city_name
				cForm.setField8(rs.getString(7)!=null && rs.getString(7).length() > 0 ? rs.getString(7) : "-");//city_desc
				cForm.setField9(rs.getString(8));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CityBean.getSearchCityList:"+e);
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
				System.out.println("Exception While Closing The Connection in CityBean.getSearchCityList:"+e);
			}
		}
		return dataList;
	}
	
	/**
	 * Method Name  : getDataForModify
	 * Purpose		: To getDataForModify
	 * @author		: Prashant
	 * Date			: 09/03/2012 
	 */
	public ArrayList<CommonForm> getDataForModify(String cityId)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			
			
			query	+=	"	select	cm.cityid,cm.countryid,cnmst.country_name,cm.stateid,sm.state_name,cm.city_name,cm.city_desc,cm.status "+
						"	from	city_master cm " +
						"	inner join country_master cnmst on	cm.countryid	=	cnmst.countryid " +
						"	inner join state_master   sm	on	cm.stateid		=	sm.stateid " +
						"									and	cm.cityid		=	'"+cityId+"'";
		
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				//cm.cityid,cm.countryid,cnmst.country_name,cm.stateid,sm.state_name,cm.city_name,cm.city_desc,cm.status
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//cityid
				cForm.setField2(rs.getString(2));//countryid
				cForm.setField3(rs.getString(3));//country_name
				cForm.setField4(rs.getString(4));//stateid
				cForm.setField5(rs.getString(5));//state_name
				cForm.setField6(rs.getString(6));//city_name
				cForm.setField7(rs.getString(7));//city_desc
				cForm.setField8(rs.getString(8));//status
				
				dataList.add(cForm);
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR In CityBean.getDataForModify:"+e);
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
				System.out.println("Exception While Closing The Connection in CityBean.getDataForModify:"+e);
			}
		}
		return dataList;
	}
	/**
	 * Method Name  : getModifyCityMaster
	 * Purpose		: To getModifyCityMaster
	 * @author		: Prashant
	 * Date			: 09/03/2012 
	 */
	
	public String getModifyCityMaster(CityForm cityForm, String userName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		
		String				cityId				=	cityForm.getCityId();
		String				stateId				=	cityForm.getStateId();
		String				countryId			=	cityForm.getCountryId();
		String				cityName			=	cityForm.getCityName().trim().toUpperCase();
		String				cityDesc			=	cityForm.getCityDesc()!=null && cityForm.getCityDesc().length() > 0 ? cityForm.getCityDesc().trim().toUpperCase() : "" ;
		String				status				=	cityForm.getStatus();
		boolean				flag				=	true;
		int					count				=	0;
		int					totalCount			=	0;
		try
		{
			count	=	commonMethodBean.checkCountryWiseCityForModification(cityName,countryId,stateId,cityId);	
			if(count > 0)
			{
				flag			=	false;
				totalCount		=	1;
				outputString	=	"FAIL.CITY NAME : "+cityName+" IS ALREADY PRESENT.";
			}
			
			if(status.equalsIgnoreCase("INACTIVE"))
			{	
				if(count == 0)
				{	
					count	=	commonMethodBean.checkForActive("bank_branch_master","cityid", "cityid", cityId); 
					if(count > 0)
					{
						if(status.equalsIgnoreCase("INACTIVE"))
						{
							flag			=	false;
							totalCount		=	1;
							outputString	=	"FAIL.CANNOT MODIFY  : BRANCHES ARE PRESENT AGAINST CITY NAME : "+cityName;
						}
					}
				}
				if(totalCount == 0)
				{	
					count	=	commonMethodBean.checkForActive("employee_master","local_city", "local_city", cityId); 
					if(count > 0)
					{
						if(status.equalsIgnoreCase("INACTIVE"))
						{
							flag			=	false;
							totalCount		=	1;
							outputString	=	"FAIL.CANNOT MODIFY  : CITIES ARE PRESENT AGAINST CITY NAME : "+cityName;
						}
					}
				}	
		
				if(totalCount == 0)
				{	
					count	=	commonMethodBean.checkForActive("employee_master","permanant_city", "permanant_city", cityId); 
					if(count > 0)
					{
						if(status.equalsIgnoreCase("INACTIVE"))
						{
							flag			=	false;
							totalCount		=	1;
							outputString	=	"FAIL.CANNOT MODIFY  : CITIES ARE PRESENT AGAINST CITY NAME : "+cityName;
						}
					}
				}
				
			}
			if(totalCount == 0)
			{
				count	=	commonMethodBean.checkForIsProperDelete("employee_master", "employeeid", "local_city",cityId);
				if(count > 0)
				{
					flag			=	false;
					totalCount		=	1;
					outputString	=	"FAIL.CANNOT MODIFY : EMPLOYEES ARE PRESENT AGAINST CITY NAME : "+cityName;
				}
			}
			if(totalCount == 0)
			{	
				count	=	commonMethodBean.checkForIsProperDelete("employee_master", "employeeid", "permanant_city",cityId);
				if(count > 0)
				{
					flag			=	false;
					totalCount		=	1;
					outputString	=	"FAIL.CANNOT MODIFY : EMPLOYEES ARE PRESENT AGAINST CITY NAME : "+cityName;
				}
			}
			if(totalCount == 0)
			{	
				count	=	commonMethodBean.checkForIsProperDelete("bank_branch_master", "branchid", "cityid",cityId);
				if(count > 0)
				{
					flag			=	false;
					outputString	=	"FAIL.CANNOT MODIFY : BRANCH NAME ARE PRESENT AGAINST CITY NAME : "+cityName;
				}
			}	
			
			if(flag	==	true)
			{	
					query		+=	"	update city_master" +
									"	set    " +
									"			countryid	=	?," +
									"			stateid		=	?," +
									"			city_name	=	?," +
									"			city_desc	=	?," +
									"			status		=	?," +
									"			updatedby	=	?," +
									"			updatedon	=	sysdate() " +
									"	where 	cityid		=	? ";
					
					
					con			=	dbConn.connect();
					pst			=	con.prepareStatement(query);
					pst.setString(1,countryId);
					pst.setString(2,stateId);
					pst.setString(3,cityName);
					pst.setString(4,cityDesc);
					pst.setString(5,status);
					pst.setString(6,userName);
					pst.setString(7,cityId);
									
					int	cnt		=	pst.executeUpdate();
					if(cnt > 0)
					{
						outputString	=	"PASS.CITY NAME : "+cityName+" MODIFIED SUCCESSFULLY.";
					}
			}
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CityBean.getModifyCountryMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in CityBean.getModifyCountryMaster :"+e);
			}
		}
		return outputString;
	}
	
	/**
	 * Method Name  : deleteCity
	 * Purpose		: To deleteCity
	 * @author		: Prashant
	 * Date			: 09/03/2012 
	 */
	
	public String deleteCity(String cityId, String cityName)
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String			outputString			=	"";
		Connection		con						=	null;
		Statement		st						=	null;
		String			query					=	"";
		boolean			flag					=	true;		
		int 			count					=	0;
		int				forDeleteCount			=	0;
		try
		{
			//NOTE: CHECK FOR LOCAL CITY ARE PRESENT AGAINST EMPLOYEE
			
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("employee_master", "employeeid", "local_city",cityId);
			if(forDeleteCount > 0)
			{
				flag			=	false;
				forDeleteCount	=	0;
				outputString	=	"FAIL.CANNOT DELETE : EMPLOYEES ARE PRESENT AGAINST CITY : "+cityName;
			}
			
			//NOTE: CHECK FOR PERMENANT CITY ARE PRESENT AGAINST EMPLOYEE
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("employee_master", "employeeid", "permanant_city",cityId);
			if(forDeleteCount > 0)
			{
				flag			=	false;
				outputString	=	"FAIL.CANNOT DELETE : EMPLOYEES ARE PRESENT AGAINST CITY : "+cityName;
			}
			
			//NOTE : CHECK FOR CITY ARE PRESENT AGAINST BRANCH NAME
			forDeleteCount	=	commonMethodBean.checkForIsProperDelete("bank_branch_master", "branchid", "cityid",cityId);
			if(forDeleteCount > 0 && flag == true)
			{
				flag	=	false;
				outputString	=	"FAIL.CANNOT DELETE : BRANCH NAME ARE PRESENT AGAINST CITY : "+cityName;
			}
			
			if(flag == true)
			{	
				query	+=	"	delete " +
							"	from 	city_master";
				if(cityId!= null && cityId.length() > 0)
				{
					query	+=	"			where   cityid	=	'"+cityId+"'";
				}	
					con				=	dbConn.connect();
					st				=	con.createStatement();
					count			=	st.executeUpdate(query);
					if( count > 0)
					{
						outputString	=	"PASS.CITY NAME : "+cityName+" DELETED SUCCESSFULLY.";
					}
					else
					{
						outputString	=	"FAIL.CITY NAME : "+cityName+" NOT DELETE.";
					}
			}		
				
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In CityBean.deleteCity :"+e);
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
				System.out.println("Exception While Closing The Connection in CityBean.deleteCity :"+e);
			}
		}
		return outputString;
	}
}
