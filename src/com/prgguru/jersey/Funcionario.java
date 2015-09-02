package com.prgguru.jersey;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Funcionario {

	private String via;
	
	private String nome;
	
	private String matricula;
	
	private String situacao;
	
	private String unidade;
	
	private String postoGraduacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}



	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(String postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}
	
	
}
