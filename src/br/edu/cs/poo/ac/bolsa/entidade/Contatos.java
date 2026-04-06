package br.edu.cs.poo.ac.bolsa.entidade;

public class Contatos {
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	private String numeroWhatszap;
	private String nomeParaContato;
	
	public Contatos(String email, String telefoneFixo, String telefoneCelular, String numeroWhatszap, String nomeParaContato) {
		this.email = email;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.numeroWhatszap = numeroWhatszap;
		this.nomeParaContato = nomeParaContato;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getNumeroWhatszap() {
		return numeroWhatszap;
	}

	public void setNumeroWhatszap(String numeroWhatszap) {
		this.numeroWhatszap = numeroWhatszap;
	}

	public String getNomeParaContato() {
		return nomeParaContato;
	}

	public void setNomeParaContato(String nomeParaContato) {
		this.nomeParaContato = nomeParaContato;
	}
}
