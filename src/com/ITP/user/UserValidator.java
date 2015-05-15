/**
 * 
 */
package com.ITP.user;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ITP.core.DataConnection.DataGuru;

/**
 * @author shybu.sudhamani
 *                                                            
 */
@Path("user/{userName}")
public class UserValidator extends HttpServlet implements
		ServletContextListener {
	public static HashSet<String> userDetails = new HashSet<String>();
	public static ServletContextEvent e = null;
	public static ServletContext sContext = null;
	public static File dataGuru;
	public String default_Type = "JSON";
	public static Statement stm = null;
	public static Connection conn = null;
	public DataGuru dG = null;

	public UserValidator(@Context HttpServletRequest servletRequest) {
		dG = null;
		dG = new DataGuru(servletRequest, default_Type);
		// dG.closeConenctionStatement();
		// dG.closeConnection();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response validateUser(@Context HttpServletRequest servletRequest,
			@PathParam("userName") String username) {
		String status = "";
		String userValidateQuery = "select userName from userData where userName ='"
				+ username + "' and Status ='Active'";
		try {
			ResultSet userDetailSet = dG.getConenctionStatement().executeQuery(
					userValidateQuery);
			if (userDetailSet != null) {
				while (userDetailSet.next()) {
					status = "success , user " + username + " exist in system";
					return Response.status(200).entity(status).build();
				}
				if (status.equals("")) {
					status = "No Such user found in System";
					return Response.status(200).entity(status).build();
				}
			} else {
				status = "No Such user found in System";
				return Response.status(200).entity(status).build();
			}
		} catch (Exception e) {
			status = "Connection exception";
			return Response.status(200).entity(status).build();
		}
		status = "No Response";
		return Response.status(200).entity(status).build();
	}

	@Path("/add/{addUserName}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response authorizedAddUder(
			@Context HttpServletRequest servletRequest,
			@PathParam("userName") String username,
			@PathParam("addUserName") String addUserName) {
		String status = "";
		if (userDetails.contains(username)) {
			status = "success , user " + username + " exist in system";
			return Response.status(200).entity(status).build();
		} else {
			status = "No Such user found in System";
			return Response.status(200).entity(status).build();
		}
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUser(@PathParam("userName") String username) {
		String status = "";

		if (!userDetails.contains(username)) {
			status = "Added user " + username + " to the system.";
			userDetails.add(username);
			return Response.status(200).entity(status).build();
		} else {
			status = "User "
					+ username
					+ " already exist in system , Please try different user name.";
			return Response.status(200).entity(status).build();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		File datGuru = new File(sContext.getInitParameter("DataGuru"));
		File customerDataFile = new File(datGuru, "jdbc.txt");

	}

	public boolean isValidUser(String userName) {
		String query = "select id from userData where userName ='" + userName
				+ "'";

		return false;
	}
}
