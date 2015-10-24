package com.psa.mj.Bean;


public class ApplicationSetting {
	
	public static String userName 	=  "prashant";
	public static String password 	=  "prashant";
	public static String urlString 	=  "jdbc:oracle:thin:@localhost:1521:xe";
	
	public ApplicationSetting(String userName,String password,String urlString) {
		
		ApplicationSetting.userName		=	userName;
		ApplicationSetting.password		=	password;
		ApplicationSetting.urlString	=	urlString;
	}
}
