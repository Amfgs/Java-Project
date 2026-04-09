package br.edu.cs.poo.ac.bolsa.negocio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.bolsa.dao.TituloDAO;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.entidade.StatusTitulo;
import br.edu.cs.poo.ac.bolsa.entidade.Titulo;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class TituloMediator {

	private TituloDAO dao = new TituloDAO();

	public TituloMediator() {
	}

	private MensagensValidacao validar(Titulo titulo) {
		MensagensValidacao msgs = new MensagensValidacao();

		if (titulo == null) {
			msgs.adicionar("Título não pode ser nulo");
			return msgs;
		}

		if (titulo.getInvestidorPessoa() == null && titulo.getInvestidorEmpresa() == null) {
			msgs.adicionar("Título deve ter um investidor (pessoa ou empresa)");
		}

		if (titulo.getAtivo() == null) {
			msgs.adicionar("Ativo do título é obrigatório");
		}

		if (titulo.getValorInvestido() == null) {
			msgs.adicionar("Valor investido é obrigatório");
		} else if (titulo.getValorInvestido().compareTo(BigDecimal.ZERO) <= 0) {
			msgs.adicionar("Valor investido deve ser maior que zero");
		}

		if (titulo.getValorAtual() == null) {
			msgs.adicionar("Valor atual é obrigatório");
		} else if (titulo.getValorAtual().compareTo(BigDecimal.ZERO) <= 0) {
			msgs.adicionar("Valor atual deve ser maior que zero");
		}

		if (titulo.getTaxaDiaria() == null) {
			msgs.adicionar("Taxa diária é obrigatória");
		} else if (titulo.getTaxaDiaria().compareTo(BigDecimal.ZERO) < 0) {
			msgs.adicionar("Taxa diária não pode ser negativa");
		}

		if (titulo.getDataAplicacao() == null) {
			msgs.adicionar("Data de aplicação é obrigatória");
		} else if (titulo.getDataAplicacao().isAfter(LocalDate.now())) {
			msgs.adicionar("Data de aplicação não pode ser no futuro");
		}

		if (titulo.getDataVencimento() == null) {
			msgs.adicionar("Data de vencimento é obrigatória");
		} else if (titulo.getDataVencimento().isBefore(titulo.getDataAplicacao())) {
			msgs.adicionar("Data de vencimento deve ser após a data de aplicação");
		}

		if (titulo.getStatus() == null) {
			msgs.adicionar("Status do título é obrigatório");
		}

		return msgs;
	}

	public MensagensValidacao incluir(Titulo titulo) {
		MensagensValidacao msgs = validar(titulo);

		if (msgs.estaVazio()) {
			boolean resultado = dao.incluir(titulo);

			if (!resultado) {
				msgs.adicionar("Título já existente");
			}
		}

		return msgs;
	}

	public MensagensValidacao alterar(Titulo titulo) {
		MensagensValidacao msgs = validar(titulo);

		if (msgs.estaVazio()) {

			boolean resultado = dao.alterar(titulo);

			if (!resultado) {
				msgs.adicionar("Título não encontrado");
			}
		}

		return msgs;
	}

	public MensagensValidacao excluir(String numeroTitulo) {
		MensagensValidacao msgs = new MensagensValidacao();

		if (numeroTitulo == null || numeroTitulo.trim().isEmpty()) {
			msgs.adicionar("Número do título é obrigatório");
			return msgs;
		}

		boolean resultado = dao.excluir(numeroTitulo.trim());

		if (!resultado) {
			msgs.adicionar("Título não encontrado com este número");
		}

		return msgs;
	}

	public Titulo buscar(String numeroTitulo) {
		if (numeroTitulo == null || numeroTitulo.trim().isEmpty()) {
			return null;
		}

		return dao.buscar(numeroTitulo.trim());
	}
}