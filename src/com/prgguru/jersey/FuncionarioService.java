package com.prgguru.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("funcionario")
public class FuncionarioService {
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Funcionario getDadosInJSON(@QueryParam("matricula")String matricula) {
 
	 try {
		return  DBConnection.getFuncionarioByMatricula(matricula);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return new Funcionario();
 
	}
	
	

}
