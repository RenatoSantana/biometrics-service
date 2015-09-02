package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

@Path("dadosbiometricos")
public class JsonService {
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public DadosBiometrico getDadosInJSON() {
 
		DadosBiometrico dados = new DadosBiometrico();
		
        dados.setMatricula("123456");
		return dados;
 
	}
	
	
	
	
	@POST
	@Path("/salvarDadosBiometricos")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createDadosInJSON(DadosBiometrico dados) {
		String response = "";

		Integer retCode  = registerFile(dados.getMatricula(),
				Utitlity.converteStringToByte(dados.getPhoto()), Utitlity.converteStringToByte(dados.getAssinatura()),Utitlity.converteStringToByte(dados.getDigital()));
		if(retCode == 0){
			response = Utitlity.constructJSON("registro_dados",true);
		}else if(retCode == 1){
			response = Utitlity.constructJSON("registro_dados",false, "Este usuário já se encontrava registrado, os dados foram atualizados.");
		}else if(retCode == 2){
			response = Utitlity.constructJSON("registro_dados",false, "Ocorreu um erro");
		}else if(retCode == 3){
			response = Utitlity.constructJSON("registro_dados",false, "Ocorreu um erro");
		}
		return response;
	}
	
	
	
	private int registerFile(String matricula, byte[] photo, byte[] assinatura,byte[] digital){
		System.out.println("Inicio cadastro biometrico para matricula : "+ matricula );
		int result = 3;
		if(Utitlity.isNotNull(matricula)){
			try {
				if(DBConnection.insertFile(matricula, photo, assinatura, digital)){
					System.out.println("Registro Efetuado");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("RegisterFile catch sqle");
				//When Primary key violation occurs that means user is already registered
				if(sqle.getErrorCode() == 1062){
					result = 1;
					try {
						DBConnection.updateFile(matricula, photo, assinatura, digital);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result = 3;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result = 3;
					}
				} 
				//When special characters are used in name,username or password
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Um erro ocorreu ");
				result = 3;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
			
		return result;
	}
	
	



}
