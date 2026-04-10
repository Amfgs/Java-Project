package br.edu.cs.poo.ac.bolsa.negocio;

import br.edu.cs.poo.ac.bolsa.dao.AtivoDAO;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class AtivoMediator {

    private static AtivoMediator instancia;
    private AtivoDAO dao = new AtivoDAO();

    private AtivoMediator() { }

    public static AtivoMediator getInstancia() {
        if (instancia == null) {
            instancia = new AtivoMediator();
        }
        return instancia;
    }

    private MensagensValidacao validar(Ativo ativo) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (ativo == null) {
            msgs.adicionar("Ativo não informado.");
            return msgs;
        }

        if (ativo.getCodigo() <= 0) {
            msgs.adicionar("Código deve ser maior que zero.");
        }

        if (ativo.getDescricao() == null || ativo.getDescricao().trim().isEmpty()) {
            msgs.adicionar("Descrição é obrigatória.");
        }

        if (ativo.getValorMinimoAplicacao() <= 0) {
            msgs.adicionar("Valor mínimo de aplicação deve ser maior que zero.");
        }

        if (ativo.getValorMaximoAplicacao() < ativo.getValorMinimoAplicacao()) {
            msgs.adicionar("Valor máximo de aplicação deve ser maior ou igual ao valor mínimo.");
        }

        if (ativo.getTaxaMensalMinima() < 0) {
            msgs.adicionar("Taxa mensal mínima deve ser maior ou igual a zero.");
        }

        if (ativo.getTaxaMensalMaxima() < ativo.getTaxaMensalMinima()) {
            msgs.adicionar("Taxa mensal máxima deve ser maior ou igual à taxa mensal mínima.");
        }

        if (ativo.getFaixaMinimaPermitida() == null) {
            msgs.adicionar("Faixa de renda mínima permitida é obrigatória.");
        }

        if (ativo.getPrazoEmMeses() < 1) {
            msgs.adicionar("Prazo em meses deve ser maior ou igual a um.");
        }

        return msgs;
    }

    public MensagensValidacao incluir(Ativo ativo) {
        MensagensValidacao msgs = validar(ativo);

        if (msgs.estaVazio()) {
            boolean resultado = dao.incluir(ativo);
            if (!resultado) {
                msgs.adicionar("Ativo já existente.");
            }
        }
        return msgs;
    }

    public MensagensValidacao alterar(Ativo ativo) {
        MensagensValidacao msgs = validar(ativo);

        if (msgs.estaVazio()) {
            boolean resultado = dao.alterar(ativo);
            if (!resultado) {
                msgs.adicionar("Ativo não existente.");
            }
        }
        return msgs;
    }

    public MensagensValidacao excluir(long codigo) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (codigo <= 0) {
            msgs.adicionar("Código deve ser maior que zero.");
            return msgs;
        }

        boolean resultado = dao.excluir(codigo);
        if (!resultado) {
            msgs.adicionar("Ativo não existente.");
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