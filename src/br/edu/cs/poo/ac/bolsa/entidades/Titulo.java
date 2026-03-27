package br.edu.cs.poo.ac.bolsa.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Titulo {
	private InvestidorPessoa investidorPessoa;
	private InvestidorEmpresa investidorEmpresa;
	private Ativo ativo;
	private BigDecimal valorInvestido;
	private BigDecimal valorAtual;
	private BigDecimal taxaDiaria;
	private LocalDate dataAplicacao;
	private LocalDate dataVencimento;
	private LocalDate dataUltimoRendimento;
	private StatusTitulo status;
	
	public Titulo(InvestidorPessoa investidorPessoa, InvestidorEmpresa investidorEmpresa, Ativo ativo,
			BigDecimal valorInvestido, BigDecimal valorAtual, BigDecimal taxaDiaria, LocalDate dataAplicacao,
			LocalDate dataVencimento, LocalDate dataUltimoRendimento, StatusTitulo status) {
		this.investidorPessoa = investidorPessoa;
		this.investidorEmpresa = investidorEmpresa;
		this.ativo = ativo;
		this.valorInvestido = valorInvestido;
		this.valorAtual = valorAtual;
		this.taxaDiaria = taxaDiaria;
		this.dataAplicacao = dataAplicacao;
		this.dataVencimento = dataVencimento;
		this.dataUltimoRendimento = dataUltimoRendimento;
		this.status = status;
	}

	public InvestidorPessoa getInvestidorPessoa() {
		return investidorPessoa;
	}

	public InvestidorEmpresa getInvestidorEmpresa() {
		return investidorEmpresa;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public BigDecimal getValorInvestido() {
		return valorInvestido;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public BigDecimal getTaxaDiaria() {
		return taxaDiaria;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public LocalDate getDataUltimoRendimento() {
		return dataUltimoRendimento;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setInvestidorPessoa(InvestidorPessoa investidorPessoa) {
		this.investidorPessoa = investidorPessoa;
	}

	public void setInvestidorEmpresa(InvestidorEmpresa investidorEmpresa) {
		this.investidorEmpresa = investidorEmpresa;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public void setValorInvestido(BigDecimal valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public void setTaxaDiaria(BigDecimal taxaDiaria) {
		this.taxaDiaria = taxaDiaria;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setDataUltimoRendimento(LocalDate dataUltimoRendimento) {
		this.dataUltimoRendimento = dataUltimoRendimento;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	
	public boolean render() {
		
		if (this.status != StatusTitulo.ATIVO) return false;
		
		LocalDate atual = LocalDate.now();
		if (atual.isAfter(dataVencimento)) return false;
		
		if (atual.isBefore(dataAplicacao)) return false;
		
		if (atual.isBefore(dataUltimoRendimento)) return false;
		
		if ()
		
		
		
		
	}
	
	
}
