package br.edu.cs.poo.ac.bolsa.negocio;

import java.math.BigDecimal;

import br.edu.cs.poo.ac.bolsa.dao.InvestidorEmpresaDAO;
import br.edu.cs.poo.ac.bolsa.dao.InvestidorPessoaDAO;
import br.edu.cs.poo.ac.bolsa.entidade.Investidor;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;
import br.edu.cs.poo.ac.bolsa.util.ResultadoValidacao;
import br.edu.cs.poo.ac.bolsa.util.ValidadorCpfCnpj;

public class InvestidorMediator {

    private InvestidorPessoaDAO daoPessoa = new InvestidorPessoaDAO();
    private InvestidorEmpresaDAO daoEmpresa = new InvestidorEmpresaDAO();

    public InvestidorMediator() {
    }

    public MensagensValidacao incluirPessoa(InvestidorPessoa investidorPessoa) {
        MensagensValidacao msgs = validarInvestidorPessoa(investidorPessoa);

        if (msgs.estaVazio()) {
            if (!daoPessoa.incluir(investidorPessoa)) {
                msgs.adicionar("Investidor Pessoa já cadastrado com este CPF");
            }
        }

        return msgs;
    }

    public MensagensValidacao alterarPessoa(InvestidorPessoa investidorPessoa) {
        MensagensValidacao msgs = validarInvestidorPessoa(investidorPessoa);

        if (msgs.estaVazio()) {
            if (!daoPessoa.alterar(investidorPessoa)) {
                msgs.adicionar("Investidor Pessoa não encontrado com este CPF");
            }
        }

        return msgs;
    }

    public MensagensValidacao excluirPessoa(String cpf) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (cpf == null || cpf.trim().isEmpty()) {
            msgs.adicionar("CPF do investidor Pessoa é obrigatório");
            return msgs;
        }

        if (!daoPessoa.excluir(cpf.trim())) {
            msgs.adicionar("Investidor Pessoa não encontrado com este CPF");
        }

        return msgs;
    }

    public InvestidorPessoa buscarPessoa(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }

        return daoPessoa.buscar(cpf.trim());
    }

    public MensagensValidacao incluirEmpresa(InvestidorEmpresa investidorEmpresa) {
        MensagensValidacao msgs = validarInvestidorEmpresa(investidorEmpresa);

        if (msgs.estaVazio()) {
            if (!daoEmpresa.incluir(investidorEmpresa)) {
                msgs.adicionar("Investidor Empresa já cadastrado com este CNPJ");
            }
        }

        return msgs;
    }

    public MensagensValidacao alterarEmpresa(InvestidorEmpresa investidorEmpresa) {
        MensagensValidacao msgs = validarInvestidorEmpresa(investidorEmpresa);

        if (msgs.estaVazio()) {
            if (!daoEmpresa.alterar(investidorEmpresa)) {
                msgs.adicionar("Investidor Empresa não encontrado com este CNPJ");
            }
        }

        return msgs;
    }

    public MensagensValidacao excluirEmpresa(String cnpj) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (cnpj == null || cnpj.trim().isEmpty()) {
            msgs.adicionar("CNPJ do investidor Empresa é obrigatório");
            return msgs;
        }

        if (!daoEmpresa.excluir(cnpj.trim())) {
            msgs.adicionar("Investidor Empresa não encontrado com este CNPJ");
        }

        return msgs;
    }

    public InvestidorEmpresa buscarEmpresa(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return null;
        }

        return daoEmpresa.buscar(cnpj.trim());
    }

    private MensagensValidacao validarInvestidorPessoa(InvestidorPessoa investidorPessoa) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (investidorPessoa == null) {
            msgs.adicionar("Investidor Pessoa não pode ser nulo");
            return msgs;
        }

        validarDadosBasicos(investidorPessoa, msgs, "Pessoa");
        validarCpf(investidorPessoa, msgs);

        if (investidorPessoa.getFaixaRenda() == null) {
            msgs.adicionar("Faixa de renda do investidor Pessoa é obrigatória");
        }

        if (investidorPessoa.getRenda() <= 0) {
            msgs.adicionar("Renda do investidor Pessoa deve ser maior que zero");
        }

        if (investidorPessoa.getDataNascimento() == null) {
            msgs.adicionar("Data de nascimento do investidor Pessoa é obrigatória");
        }

        return msgs;
    }

    private MensagensValidacao validarInvestidorEmpresa(InvestidorEmpresa investidorEmpresa) {
        MensagensValidacao msgs = new MensagensValidacao();

        if (investidorEmpresa == null) {
            msgs.adicionar("Investidor Empresa não pode ser nulo");
            return msgs;
        }

        validarDadosBasicos(investidorEmpresa, msgs, "Empresa");
        validarCnpj(investidorEmpresa, msgs);

        if (investidorEmpresa.getFaturamento() < 0) {
            msgs.adicionar("Faturamento do investidor Empresa não pode ser negativo");
        }

        if (investidorEmpresa.getDataAbertura() == null) {
            msgs.adicionar("Data de abertura do investidor Empresa é obrigatória");
        }

        return msgs;
    }

    private void validarDadosBasicos(Investidor investidor, MensagensValidacao msgs, String tipo) {
        if (investidor == null) {
            return;
        }

        if (investidor.getNome() == null || investidor.getNome().trim().isEmpty()) {
            msgs.adicionar("Nome do investidor " + tipo + " é obrigatório");
        }

        if (investidor.getEndereco() == null) {
            msgs.adicionar("Endereço do investidor " + tipo + " é obrigatório");
        }

        if (investidor.getContatos() == null) {
            msgs.adicionar("Contato do investidor " + tipo + " é obrigatório");
        }

        if (investidor.getBonus() == null) {
            msgs.adicionar("Bônus do investidor " + tipo + " é obrigatório");
        } else if (investidor.getBonus().compareTo(BigDecimal.ZERO) < 0) {
            msgs.adicionar("Bônus do investidor " + tipo + " deve ser maior ou igual a zero");
        }
    }

    private void validarCpf(InvestidorPessoa investidorPessoa, MensagensValidacao msgs) {
        if (investidorPessoa.getCpf() == null || investidorPessoa.getCpf().trim().isEmpty()) {
            msgs.adicionar("CPF do investidor Pessoa é obrigatório");
            return;
        }

        ResultadoValidacao resultado = ValidadorCpfCnpj.validarCpf(investidorPessoa.getCpf());

        if (resultado != null) {
            msgs.adicionar("CPF do investidor Pessoa inválido: " + resultado.getMensagem());
        }
    }

    private void validarCnpj(InvestidorEmpresa investidorEmpresa, MensagensValidacao msgs) {
        if (investidorEmpresa.getCnpj() == null || investidorEmpresa.getCnpj().trim().isEmpty()) {
            msgs.adicionar("CNPJ do investidor Empresa é obrigatório");
            return;
        }

        ResultadoValidacao resultado = ValidadorCpfCnpj.validarCnpj(investidorEmpresa.getCnpj());

        if (resultado != null) {
            msgs.adicionar("CNPJ do investidor Empresa inválido: " + resultado.getMensagem());
        }
    }
}
