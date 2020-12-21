package com.ricbap.salvavidas.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ricbap.salvavidas.api.model.TipoLancamento;

public class LancamentoMensal {
	
	private TipoLancamento tipo;
	private String pessoa;
	private String descricao;	
	private LocalDate dataVencimento;	
	private BigDecimal valor;
	
	public LancamentoMensal(TipoLancamento tipo, String pessoa, String descricao,
			LocalDate dataVencimento, BigDecimal valor) {
		super();
		this.tipo = tipo;
		this.pessoa = pessoa;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
	}
	
	
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	public String getPessoa() {
		return pessoa;
	}
	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

}
