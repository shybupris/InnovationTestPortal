/**
 * 
 */
package com.ITP.exam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * @author shybu.sudhamani
 *
 */
@Path("/user/ExamModules/{profLevel}")
public class ExamModules {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String createExamModule(@PathParam("profLevel") String profLevel){
		
		return "";
	}
}
