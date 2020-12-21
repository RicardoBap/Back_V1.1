package com.ricbap.salvavidas.api.dto;

public class AnexoDTO {
	
	private String nome;
	private String url;
	
	
	public AnexoDTO(String nome, String url) {		
		this.nome = nome;
		this.url = url;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	

}
