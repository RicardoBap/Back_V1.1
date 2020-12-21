package com.ricbap.salvavidas.api.repository.projection;

public class ResumoCidade {
	
	private Long codigo;
	
	private String cidade;
	
	private String estado;
	

	public ResumoCidade(Long codigo, String cidade, String estado) {
		this.codigo = codigo;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

}
