package com.ricbap.salvavidas.api.repository.projection;

public class ResumoGrupo {
	
	private Long codigo;
	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String diaReuniao;
	private String cidade;
	private String estado;
	private String anexoUrl;
	
	
	public ResumoGrupo(Long codigo, String nome, String logradouro, String numero, String complemento, String bairro,
			String cep, String diaReuniao, String cidade, String estado, String anexoUrl) {
		this.codigo = codigo;
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.diaReuniao = diaReuniao;
		this.cidade = cidade;
		this.estado = estado;
		this.anexoUrl = anexoUrl;
	}
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDiaReuniao() {
		return diaReuniao;
	}
	public void setDiaReuniao(String diaReuniao) {
		this.diaReuniao = diaReuniao;
	}
	public String getAnexoUrl() {
		return anexoUrl;
	}
	public void setAnexoUrl(String anexoUrl) {
		this.anexoUrl = anexoUrl;
	}	

}
