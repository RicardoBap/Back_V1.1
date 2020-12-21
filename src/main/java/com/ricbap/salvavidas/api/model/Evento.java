package com.ricbap.salvavidas.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;	
	
	@Column(name = "data_hora_evento")
	private LocalDateTime dataHoraEvento;
	
	@Column(name = "data_referencia")
	private LocalDate dataReferencia;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_grupo")
	private Grupo grupo;	

	@NotNull
	@Size(min = 5, max = 30)
	@Column(name = "tipo_evento")
	private String tipoEvento;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String tema;
	
	@NotNull
	@Size(min = 5, max = 20)
	private String palestrante;
	
	private Boolean destaque = false;
	
	@Transient
	private LocalDate dataEvento;
	
	@Transient
	private LocalTime horaEvento;
	

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
	public LocalDate getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(LocalDate dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	public LocalTime getHoraEvento() {
		return horaEvento;
	}
	public void setHoraEvento(LocalTime horaEvento) {
		this.horaEvento = horaEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	

}
