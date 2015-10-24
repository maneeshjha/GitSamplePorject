package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.Master.NotificationForm;
import com.psa.mj.DBConnection.DBConnection;

public class NotificationBean {
	DBConnection	dbConn	=	new DBConnection();
	public String saveNotification(NotificationForm notificationForm) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		boolean				flag				=	true;
		String				emailid			=	notificationForm.getEmail_id();
		String				notification		=	notificationForm.getNotification();	
		try
		{
			
				con		=	dbConn.connect();
				query	+=	"	insert into 	notificationmst" +
							"					(	" +
							"					emailiid,notification " +
							"					)" +
							"					values" +
							"					(" +
							"					?, ? " +
							"					)";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1, emailid);
				pst.setString(2,notification );
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.NOTIFICATION FOR  : "+emailid.trim().toUpperCase()+" SAVED SUCCESSFULLY.";
				}
				pst.close();
			
		}
		catch(Exception e)
		{
			outputString = "ERROR";
			System.out.println("ERROR In ArticleBean.saveArticleMaster :"+e);
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
				System.out.println("Exception While Closing The Connection in NotificationBean.saveArticleMaster :"+e);
			}
		}
		return outputString ;
	}


}
