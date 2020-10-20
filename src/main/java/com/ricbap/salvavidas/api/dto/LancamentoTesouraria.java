package com.ricbap.salvavidas.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.ricbap.salvavidas.api.model.TipoLancamento;

public class LancamentoTesouraria {	
	
	private TipoLancamento tipo;
	private Date data_vencimento;
	private String descricao;	
	private BigDecimal valor;
	
	public LancamentoTesouraria() {}

	public LancamentoTesouraria(TipoLancamento tipo, LocalDate data_vencimento, String descricao, BigDecimal valor) {
		this.tipo = tipo;
		this.data_vencimento = convertToDateViaSqlDate(data_vencimento);		
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public Date convertToDateViaSqlDate(LocalDate data_vencimento) {
	    return java.sql.Date.valueOf(data_vencimento);
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
	
}

