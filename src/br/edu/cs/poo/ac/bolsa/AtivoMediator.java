package br.edu.cs.poo.ac.bolsa;

import br.edu.cs.poo.ac.bolsa.dao.AtivoDAO;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class AtivoMediator {
	
	private AtivoDAO dao = new AtivoDAO();
	
	public AtivoMediator() {
	}
	
	private MensagensValidacao validar(Ativo ativo) {
		MensagensValidacao msgs = new MensagensValidacao();

		if (ativo == null) {
			msgs.adicionar("Ativo não pode ser nulo");
			return msgs;
		}

		if (ativo.getCodigo() <= 0) {
			msgs.adicionar("Código do ativo deve ser maior que zero");
		}

		if (ativo.getDescricao() == null || ativo.getDescricao().trim().isEmpty()) {
			msgs.adicionar("Descrição do ativo é obrigatória");
		}

		if (ativo.getValorMinimoAplicacao() <= 0) {
			msgs.adicionar("Valor mínimo de aplicação deve ser maior que zero");
		}
		if (ativo.getValorMinimoAplicacao() > ativo.getValorMaximoAplicacao()) {
			msgs.adicionar("Valor mínimo não pode ser maior que valor máximo");
		}

		if (ativo.getValorMaximoAplicacao() <= 0) {
			msgs.adicionar("Valor máximo de aplicação deve ser maior que zero");
		}
		if (ativo.getValorMaximoAplicacao() < ativo.getValorMinimoAplicacao()) {
			msgs.adicionar("Valor máximo não pode ser menor que valor mínimo");
		}

		if (ativo.getTaxaMensalMinima() < 0) {
			msgs.adicionar("Taxa mensal mínima não pode ser negativa");
		}
		if (ativo.getTaxaMensalMinima() > ativo.getTaxaMensalMaxima()) {
			msgs.adicionar("Taxa mensal mínima não pode ser maior que taxa máxima");
		}

		if (ativo.getTaxaMensalMaxima() < 0) {
			msgs.adicionar("Taxa mensal máxima não pode ser negativa");
		}
		if (ativo.getTaxaMensalMaxima() < ativo.getTaxaMensalMinima()) {
			msgs.adicionar("Taxa mensal máxima não pode ser menor que taxa mínima");
		}

		if (ativo.getFaixaMinimaPermitida() == null) {
			msgs.adicionar("Faixa mínima permitida é obrigatória");
		}

		if (ativo.getPrazoEmMeses() <= 0) {
			msgs.adicionar("Prazo em meses deve ser maior que zero");
		}
		
		return msgs;
	}
	
	public MensagensValidacao incluir(Ativo ativo) {
		MensagensValidacao msgs = validar(ativo);
		
		if (msgs.estaVazio()) {
			boolean resultado = dao.incluir(ativo);
			
			if (!resultado) {
				msgs.adicionar("Ativo já existente com este código");
			}
		}
		
		return msgs;
	}
	
	public MensagensValidacao alterar(Ativo ativo) {

		MensagensValidacao msgs = validar(ativo);

		if (msgs.estaVazio()) {
			boolean resultado = dao.alterar(ativo);

			if (!resultado) {
				msgs.adicionar("Ativo não existente com este código");
			}
		}

		return msgs;
	}

	public MensagensValidacao excluir(long codigo) {
		MensagensValidacao msgs = new MensagensValidacao();

		if (codigo <= 0) {
			msgs.adicionar("Código do ativo deve ser maior que zero");
			return msgs;
		}
		
		boolean resultado = dao.excluir(codigo);
		
		if (!resultado) {
			msgs.adicionar("Ativo não existente com este código");
		}
		
		
		return msgs;
	}
	
	public Ativo buscar(long codigo) {
		
		if (codigo <= 0) {
			return null;  
		}
		
		return dao.buscar(codigo);
	}
}