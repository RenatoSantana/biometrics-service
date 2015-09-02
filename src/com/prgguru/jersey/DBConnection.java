package com.prgguru.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class DBConnection {
	/**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}
	
	

	public static Funcionario getFuncionarioByMatricula(String matricula) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs;
		StringBuilder sb = new StringBuilder();
		Funcionario funcionario = new Funcionario();
		
		sb.append("SELECT fun.via as via,fun.matricula as matricula,concat(fun.nome,' ',fun.sobrenome) as nome,sp.descricao as situacaoPolicial, pg.descricao as postoGraduacao ,u.descricao as unidade FROM funcionario as fun")
		.append(" inner join situacaopolicial sp ")
		.append("   on fun.idSituacaoPolicial = sp.idSituacaoPolicial ")
		.append(" inner join postograduacao pg ")
		.append("   on pg.idPostoGraduacao = fun.idPostoGraduacao ")
		.append(" inner join unidade u ")
		.append(" on  u.idUnidade = fun.idUnidade")
		.append(" where fun.matricula = ? ");
		

		try {
			try {
				conn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
            pstm = conn.prepareStatement(sb.toString());
			pstm.setString(1, matricula);
			
			rs = pstm.executeQuery();

			if (rs.next()) {
	
				Integer via = rs.getInt(1);
				funcionario.setVia(via.toString());
				funcionario.setMatricula(rs.getString(2));
				funcionario.setNome(rs.getString(3));
				funcionario.setSituacao(rs.getString(4));
				funcionario.setPostoGraduacao(rs.getString(5));
				funcionario.setUnidade(rs.getString(6));
				
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (conn != null) {
				conn.close();
			}
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return funcionario;
	}
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
	public static boolean checkLogin(String uname, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM user_account WHERE userName = '" + uname
					+ "' AND password="+"'"+ pwd +"'";
			//System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				//System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}
	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
					+ uname + "','" + pwd + "')";
			//System.out.println(query);
			int records = stmt.executeUpdate(query);
			//System.out.println(records);
			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	
	
	public static boolean insertFile(String matricula, byte[] photo, byte[] assinatura,byte[] digital) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		StringBuilder query = new StringBuilder();
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
		       query.append( "INSERT into dadobiometrico(foto, digital, assinatura,matricula) values('"+photo+ "',"+"'"
					+ digital + "','" + assinatura + "','" + matricula + "')");
			//System.out.println(query);
			int records = stmt.executeUpdate(query.toString());
			//System.out.println(records);
			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	
	
	public static boolean updateFile(String matricula, byte[] photo, byte[] assinatura,byte[] digital) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		StringBuilder query = new StringBuilder();
		PreparedStatement preparedStatement = null;
		
		 query .append( "update  dadobiometrico set "
					+ " foto = ? , "
					+ " digital = ? ,"
					+ " assinatura = ?  WHERE matricula = ? ");
		try {
			try {
				dbConn = DBConnection.createConnection();
				preparedStatement = dbConn.prepareStatement(query.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			preparedStatement.setBytes(1, photo);
			preparedStatement.setBytes(2, digital);
			preparedStatement.setBytes(3, assinatura);
			preparedStatement.setString(4,matricula);
 
			// execute update SQL stetement
			int records= preparedStatement.executeUpdate();
		
		
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
}
