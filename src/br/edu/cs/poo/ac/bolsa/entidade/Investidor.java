package br.edu.cs.poo.ac.bolsa.entidade;
import java.io.Serializable;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.Period;

public class Investidor implements Serializable {
	private String nome;
	private Endereco endereco;
	private LocalDate dataCriacao;
	private BigDecimal bonus;
	private Contatos contatos;
	
	public Investidor() {
		this.nome = null;
		this.endereco = null;
		this.dataCriacao = null;
		this.bonus = null;
		this.contatos = null;
	}

	public Investidor(String nome, Endereco endereco, LocalDate dataCriacao, BigDecimal bonus, Contatos contatos) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataCriacao = dataCriacao;
		this.bonus = bonus;
		this.contatos = contatos;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Contatos getContatos() {
		return this.contatos;
	}
	
	public void setContatos(Contatos contatos) {
		this.contatos = contatos;
	}
	
	protected LocalDate getDataCriacao() {
		return this.dataCriacao;
	}
	
	protected void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public BigDecimal getBonus() {
		return this.bonus;
	}
	
	public int getIdade() {
		LocalDate atual = LocalDate.now();	
		Period periodo = Period.between(this.dataCriacao, atual);
		int idade = periodo.getYears();
		return idade;
	}
	
	public void creditarBonus(BigDecimal valor) {
		this.bonus = this.bonus.add(valor);
	}
	
	public void debitarBonus(BigDecimal valor) {
		this.bonus = this.bonus.subtract(valor);
	}
}
