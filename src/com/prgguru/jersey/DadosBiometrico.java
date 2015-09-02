package com.prgguru.jersey;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DadosBiometrico {

	 private String matricula;
	 private String digital;
	 private String photo;
	 private String assinatura;
	 
	 public String getMatricula() {
		 return matricula;
	 }
	 public void setMatricula(String matricula) {
		 this.matricula = matricula;
	 }
	public String getDigital() {
		return digital;
	}
	public void setDigital(String digital) {
		this.digital = digital;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAssinatura() {
		return assinatura;
	}
	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}
	 
	 
	 
	 
}
