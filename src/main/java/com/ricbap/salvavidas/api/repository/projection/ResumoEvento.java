package com.ricbap.salvavidas.api.repository.projection;

import java.time.LocalDateTime;

public class ResumoEvento {
	
	private Long codigo;
	private LocalDateTime dataHoraEvento;
	private String grupo;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	private String tipoEvento;
	private String tema;
	private String palestrante;
	private Boolean destaque;
	
	public ResumoEvento(Long codigo, LocalDateTime dataHoraEvento, String grupo, String logradouro, String numero,
			String complemento, String bairro, String cidade, String estado, String tipoEvento, String tema,
			String palestrante, Boolean destaque) {
		this.codigo = codigo;
		this.dataHoraEvento = dataHoraEvento;
		this.grupo = grupo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.tipoEvento = tipoEvento;
		this.tema = tema;
		this.palestrante = palestrante;
		this.destaque = destaque;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataHoraEvento() {
		return dataHoraEvento;
	}

	public void setDataHoraEvento(LocalDateTime dataHoraEvento) {
		this.dataHoraEvento = dataHoraEvento;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
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

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(String palestrante) {
		this.palestrante = palestrante;
	}

	public Boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}
	
	

}
