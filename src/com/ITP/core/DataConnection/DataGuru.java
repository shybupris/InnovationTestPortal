
package com.ITP.core.DataConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.jdom.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * @author shybu.sudhamani
 *
 */
public class DataGuru{
	public static ServletContextEvent e = null;
	public static ServletContext sContext = null;
	public static Statement stm = null;
	public static Connection conn = null;
	public static String type_JSON = "JSON";
	public static File dataGuru; ;
	private  String server = "";
	private  String sourceDetail = "";
	private  String port = "";
	private  String DataBaseName = "";
	private  String username = "";
	private  String Password = "";
	private  String driver = "";
	//Common constructor
	public DataGuru(HttpServletRequest servletRequest,String type){
		  sContext = servletRequest.getSession().getServletContext()  ;
		  try {
			 
			String path = sContext.getRealPath(sContext.getInitParameter("DataGuru"));
			// pass the reference to the inputstream reader for further processing
			dataGuru = new File(path);
			getConnection(dataGuru,type);
		System.out.println(dataGuru.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // this.dataGuru  = new File(url);

	
	}
	
	public Object getConnection(File file , String type){
		if(type.equalsIgnoreCase(type_JSON)){
			getJsonData(file);
		}
		
		return null;
	}
	
	public Statement getJsonData(File file){
		JSONParser parser = new JSONParser();
		 Object obj = null;
		try {
			obj = parser.parse(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 JSONObject jsonObject = (JSONObject) obj;
		 HashMap <String,String> connectionDetails = new HashMap<String,String>();
		 connectionDetails = (HashMap<String,String>)jsonObject.get("DataGuru");
		 setConnectionParams(connectionDetails);
		 setConenction();
		 setConenctionStatement();	 
		return null;
	}
	public void setConenctionStatement(){
		try{
			
			if(conn != null){
				Statement s1 = conn.createStatement();
				stm = s1;
			}
		}catch(Exception e){
			
		}
	}
	public void setConenction(){
		try{
			Class.forName(this.driver);		
	        String url = getConnectionString();
	        Connection con = DriverManager.getConnection(url, this.username, this.Password);
	        	conn = con;
			}catch(Exception e){
				System.out.println("Exception");
			}
	}
	public Statement getConenctionStatement(){
		return stm;
	}
	public Connection getConenction(){
		return conn;
	}
	public void closeConenctionStatement(){
		try {
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setConnectionParams(HashMap<String,String> connectionDetails){
		try{
		this.server = connectionDetails.get("Server");
		this.sourceDetail = connectionDetails.get("sourceDetail");
		this.port = connectionDetails.get("port");
		this.DataBaseName = connectionDetails.get("DataBaseName");
		this.username = connectionDetails.get("username");
		this.Password = connectionDetails.get("Password");
		this.driver = connectionDetails.get("driver");
		}catch(Exception e){
			
		}
		
	}
	
	public String getConnectionString() throws ClassNotFoundException{
        String url = "jdbc:"+this.server+"://"+this.sourceDetail+":"+this.port+";DatabaseName="+this.DataBaseName+";";   	
		return url;
	}
}
