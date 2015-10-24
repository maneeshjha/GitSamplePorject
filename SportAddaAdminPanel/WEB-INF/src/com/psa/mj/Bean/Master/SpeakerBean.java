package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.CommonForm;
import com.psa.mj.DAO.Master.SpeakerForm;
import com.psa.mj.DBConnection.DBConnection;

public class SpeakerBean 
{
	DBConnection	dbConn	=	new DBConnection();
	

	public String saveSpeakerMaster(SpeakerForm speakerForm, String userName) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		boolean				flag				=	true;
		String				speakerName			=	speakerForm.getSpeakerName().trim();
		String				decription			=	speakerForm.getDescription(); 		
		String				status				=	speakerForm.getStatus();
		String				industryid[]		=	speakerForm.getIndustryid();
		String				photoName			=	speakerForm.getPhoto()!=null && speakerForm.getPhoto().getFileSize()>0 ? speakerForm.getPhoto().getFileName() : "" ;
		String              indId       		=  	"";
		if(industryid != null && industryid.length>0)
		{
	        for(int i=0;i<industryid.length;i++)
	        {
	            if(indId!=null && indId.length()==0)
	            { 	
	        	 indId +=industryid[i];
	            }
	            else
	            {
	              indId+=","+industryid[i];
	            }
	        }
		}
		try
		{
			
			if(commonMethodBean.checkDuplicate("speakermst","speaker_name",speakerName) == false)
			{
				flag			=	false;
				outputString	=	"FAIL.SPEAKER NAME : "+speakerName.trim().toUpperCase()+" IS ALREADY PRESENT.";
			}
			
			if(flag	==	true)
			{	
				con		=	dbConn.connect();
				query	+=	"	insert into 	speakermst" +
							"					(	" +
							"					speaker_name, description, photo_name, " +
							"					status, updatedby, updatedon, createdby, " +
							"					createdon,industryid" +
							"					)" +
							"					values" +
							"					(" +
							"					?, ?, ?, " +
							"					?, ?, sysdate(), ?, sysdate(),?" +
							"					)";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1, speakerName);
				pst.setString(2, decription);
				pst.setString(3, photoName);
				pst.setString(4, status);
				pst.setString(5, userName);
				pst.setString(6, userName);
				pst.setString(7, indId);
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.SPEAKER : "+speakerName.trim().toUpperCase()+" SAVED SUCCESSFULLY.";
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
	 * Method Name  : getSearchUserList
	 * Purpose		: To getSearchUserList
	 * @author		: Prashant
	 * Date			: 27/04/2012
	 * @param  
	 */
	public ArrayList<CommonForm> getSearchSpeakerList(SpeakerForm speakerForm)
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		int				srNo		=	1;
		String			userId		=	speakerForm.getSpeakerId();
		String			status		=	speakerForm.getStatus();
		try
		{
			
			query	+=	"	select 	speaker_id, speaker_name, description, photo_name, status " +
						"	from 	speakermst	" +
						"	where 	0=0" ;
			if(userId!=null && userId.length() > 0)
			{
			query	+=	"			and	speaker_id	=	'"+userId+"'";
			}
			if(status!=null && status.length() > 0)
			{
			query	+=	"			and	status		=	'"+status+"'" ;
			}
			query	+=	"	order by speaker_name	";
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			while(rs.next())
			{
				
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(""+(srNo++));//SRNO
				cForm.setField2(rs.getString(1));//speaker_id
				cForm.setField3(rs.getString(2).toUpperCase());//speaker_name
				cForm.setField4(rs.getString(3));//description
				cForm.setField5(rs.getString(4)!=null && rs.getString(4).length()>0 ? rs.getString(4) : "-");//photo_name
				cForm.setField6(rs.getString(5));//status
				
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
	public String deleteSpeakerMaster(String userId, String userName)
	{
		String				outputString		=	"";
		Connection			con					=	null;
		Statement			st					=	null;
		String				query				=	"";
		int 				count				=	0;
		
		try
		{
			
				query			+=	"	delete	" +
									"	from	speakermst " +
									"	where	speaker_id	=	'"+userId+"'";
				con				=	dbConn.connect();
				st				=	con.createStatement();
				count			=	st.executeUpdate(query);
				if( count > 0)
				{
					outputString	=	"PASS.SPEAKER : "+userName+" DELETED SUCCESSFULLY.";
				}
				else
				{
					outputString	=	"FAIL.SPEAKER : "+userName+" NOT DELETE.";
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
	public ArrayList<CommonForm> getDataForModify(String spkId) 
	{
		ArrayList<CommonForm> dataList		=	new ArrayList<CommonForm>();
		Connection		con			=	null;
		Statement		st			=	null;
		ResultSet		rs			=	null;
		String			query		=	"";
		try
		{
			
			query	+=	"	select 	speaker_id, speaker_name, description, photo_name, status,industryid" +
						"	from 	speakermst	" +
						"	where	speaker_id = '"+spkId+"'	" ;						
			
			con		=	dbConn.connect();
			st		=	con.createStatement();
			rs		=	st.executeQuery(query);
			if(rs.next())
			{
				
				CommonForm	cForm	=	new CommonForm();
				cForm.setField1(rs.getString(1));//speaker_id
				cForm.setField2(rs.getString(2));//speaker_name		
				cForm.setField3(rs.getString(3));//description
				cForm.setField4(rs.getString(4));//photo_name
				cForm.setField5(rs.getString(5));//status
				cForm.setField6(rs.getString(6));//industryid
				
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
	public String modifySpeakerMaster(SpeakerForm speakerForm, String userName,String oldFileName) 
	{
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		
		String				speakerId			=	speakerForm.getSpeakerId();
		String				speakerName			=	speakerForm.getSpeakerName().trim();
		String				decription			=	speakerForm.getDescription(); 		
		String				status				=	speakerForm.getStatus();
		String				photoName			=	speakerForm.getPhoto()!=null && speakerForm.getPhoto().getFileSize()>0 ? speakerForm.getPhoto().getFileName() : "" ;
		String 				industryid[]		=	speakerForm.getIndustryid();
		
		String              indId       		=  	"";
		if(industryid != null && industryid.length>0)
		{
	        for(int i=0;i<industryid.length;i++)
	        {
	            if(indId!=null && indId.length()==0)
	            { 	
	        	 indId +=industryid[i];
	            }
	            else
	            {
	              indId+=","+industryid[i];
	            }
	        }
		}
		
		try
		{

				query		+=	"	update  speakermst   " +
								"	set		speaker_name	=	?, " +
								"			description		=	?, " +
								"			photo_name		=	?, " +
								"			status			=	?, " +
								"			updatedby		=	?, " +
								"			updatedon		=	sysdate()," +
								"			industryid		=	?" +
								"	where	speaker_id		=	?";
				
				con			=	dbConn.connect();
				pst			=	con.prepareStatement(query);
				
				pst.setString(1, speakerName);
				pst.setString(2, decription);
				pst.setString(3, photoName!=null && photoName.length()>0 ? photoName : oldFileName);
				pst.setString(4, status);
				pst.setString(5, userName);
				pst.setString(6, indId);
				pst.setString(7, speakerId);
				
				int	cnt		=	pst.executeUpdate();
				if(cnt > 0)
				{
					outputString	=	"PASS.SPEAKER : "+speakerName.toUpperCase()+" MODIFIED SUCCESSFULLY.";
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
	public  ArrayList<CommonForm> getSpeakerList()
	{
		Connection			   con			=	null;
		ArrayList<CommonForm>  dataList		=	new    ArrayList<CommonForm>();
		String				   query		=	"";	
		Statement  				stmt = null		;
		ResultSet   			rs			= null;
		try
		{
			con		=	dbConn.connect();
			query="select speaker_id,speaker_name from speakermst";
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				CommonForm		cForm		=	new  CommonForm();
				cForm.setField1(rs.getString(1));//industriestid
				cForm.setField2(rs.getString(2));//industryname
				dataList.add(cForm);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error in SpeakerBean.getSpeakerList():"+e);
		}
		return dataList;
		
	}

}
