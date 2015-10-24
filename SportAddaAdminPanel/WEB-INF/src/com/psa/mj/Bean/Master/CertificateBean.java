package com.psa.mj.Bean.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.psa.mj.Bean.CommonMethodBean;
import com.psa.mj.DAO.Master.CertificateForm;
import com.psa.mj.DBConnection.DBConnection;

public class CertificateBean {
	DBConnection	dbConn	=	new DBConnection();
	public String saveCertificateDetails(CertificateForm certForm) 
	{
		CommonMethodBean	commonMethodBean	=	new CommonMethodBean();
		String				outputString		=	"ERROR";
		Connection			con					=	null;
		PreparedStatement	pst					=	null;
		String				query				=	"";
		boolean				flag				=	true;
		String				cust_name			=	certForm.getCustomer_name();
		String				certId		=	certForm.getTestimonials();	
		try
		{
			
				con		=	dbConn.connect();
				query	+=	"	insert into 	certificatemst" +
							"					(	" +
							"					userName,certId " +
							"					)" +
							"					values" +
							"					(" +
							"					?, ? " +
							"					)";
				
				pst		=	con.prepareStatement(query);
				pst.setString(1, cust_name);
				pst.setString(2,certId );
				int	count	=	pst.executeUpdate();
				if(count > 0)
				{
					outputString	=	"PASS.CERTIFICATE OF  : "+cust_name.trim().toUpperCase()+" SAVED SUCCESSFULLY.";
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
				System.out.println("Exception While Closing The Connection in ArticleBean.saveArticleMaster :"+e);
			}
		}
		return outputString ;
	}

}
