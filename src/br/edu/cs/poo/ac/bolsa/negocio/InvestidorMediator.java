package br.edu.cs.poo.ac.bolsa.negocio;

import java.math.BigDecimal;
import br.edu.cs.poo.ac.bolsa.dao.DAOInvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.dao.DAOInvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.entidade.Investidor;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.entidade.Endereco;
import br.edu.cs.poo.ac.bolsa.entidade.Contatos;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;
import br.edu.cs.poo.ac.bolsa.util.ValidadorCpfCnpj;

public class InvestidorMediator {

    private DAOInvestidorPessoa daoPessoa = new DAOInvestidorPessoa();
    private DAOInvestidorEmpresa daoEmpresa = new DAOInvestidorEmpresa();

    public InvestidorMediator() {}

    public MensagensValidacao incluirInvestidorPessoa(InvestidorPessoa ip) {
        MensagensValidacao msgs = validarInvestidorPessoa(ip);
        if (msgs.estaVazio()) {
            if (!daoPessoa.incluir(ip)) msgs.adicionar("Investidor Pessoa já existente.");
        }
        return msgs;
    }

    public MensagensValidacao alterarInvestidorPessoa(InvestidorPessoa ip) {
        MensagensValidacao msgs = validarInvestidorPessoa(ip);
        if (msgs.estaVazio()) {
            if (!daoPessoa.alterar(ip)) msgs.adicionar("Investidor Pessoa não existente.");
        }
        return msgs;
    }

    public MensagensValidacao excluirInvestidorPessoa(String cpf) {
        MensagensValidacao msgs = new MensagensValidacao();
        if (vazio(cpf)) {
            msgs.adicionar("CPF é obrigatório.");
        } else if (ValidadorCpfCnpj.validarCpf(cpf) != null) {
            msgs.adicionar("CPF inválido.");
        }
        if (msgs.estaVazio()) {
            if (!daoPessoa.excluir(cpf.trim())) msgs.adicionar("Investidor Pessoa não existente.");
        }
        return msgs;
    }

    public InvestidorPessoa buscarInvestidorPessoa(String cpf) {
        if (vazio(cpf) || ValidadorCpfCnpj.validarCpf(cpf) != null) return null;
        return daoPessoa.buscar(cpf.trim());
    }

    public MensagensValidacao incluirInvestidorEmpresa(InvestidorEmpresa ie) {
        MensagensValidacao msgs = validarInvestidorEmpresa(ie);
        if (msgs.estaVazio()) {
            if (!daoEmpresa.incluir(ie)) msgs.adicionar("Investidor Empresa já existente.");
        }
        return msgs;
    }

    public MensagensValidacao alterarInvestidorEmpresa(InvestidorEmpresa ie) {
        MensagensValidacao msgs = validarInvestidorEmpresa(ie);
        if (msgs.estaVazio()) {
            if (!daoEmpresa.alterar(ie)) msgs.adicionar("Investidor Empresa não existente.");
        }
        return msgs;
    }

    public MensagensValidacao excluirInvestidorEmpresa(String cnpj) {
        MensagensValidacao msgs = new MensagensValidacao();
        if (vazio(cnpj)) {
            msgs.adicionar("CNPJ é obrigatório.");
        } else if (ValidadorCpfCnpj.validarCnpj(cnpj) != null) {
            msgs.adicionar("CNPJ inválido.");
        }
        if (msgs.estaVazio()) {
            if (!daoEmpresa.excluir(cnpj.trim())) msgs.adicionar("Investidor Empresa não existente.");
        }
        return msgs;
    }

    public InvestidorEmpresa buscarInvestidorEmpresa(String cnpj) {
        if (vazio(cnpj) || ValidadorCpfCnpj.validarCnpj(cnpj) != null) return null;
        return daoEmpresa.buscar(cnpj.trim());
    }

    private MensagensValidacao validarInvestidorPessoa(InvestidorPessoa ip) {
        MensagensValidacao msgs = new MensagensValidacao();
        if (ip == null) {
            msgs.adicionar("Investidor não informado.");
            return msgs;
        }
        validarDadosBasicos(ip, msgs);
        validarCpf(ip, msgs);
        if (ip.getRenda() <= 0) msgs.adicionar("Renda deve ser maior que zero.");
        if (ip.getDataNascimento() == null) msgs.adicionar("Data de nascimento é obrigatória.");
        return msgs;
    }

    private MensagensValidacao validarInvestidorEmpresa(InvestidorEmpresa ie) {
        MensagensValidacao msgs = new MensagensValidacao();
        if (ie == null) {
            msgs.adicionar("Investidor não informado.");
            return msgs;
        }
        validarDadosBasicos(ie, msgs);
        validarCnpj(ie, msgs);
        if (ie.getFaturamento() < 0) msgs.adicionar("Faturamento deve ser maior ou igual a zero.");
        if (ie.getDataAbertura() == null) msgs.adicionar("Data de abertura é obrigatória.");
        return msgs;
    }

    private void validarDadosBasicos(Investidor inv, MensagensValidacao msgs) {
        if (vazio(inv.getNome())) msgs.adicionar("Nome é obrigatório.");
        
        Endereco end = inv.getEndereco();
        if (end == null) {
            msgs.adicionar("Endereço é obrigatório.");
        } else {
            if (vazio(end.getLogradouro())) msgs.adicionar("Logradouro é obrigatório.");
            if (vazio(end.getNumero())) msgs.adicionar("Número é obrigatório.");
            if (vazio(end.getCidade())) msgs.adicionar("Cidade é obrigatório."); // O teste espera 'obrigatório'
            if (vazio(end.getEstado())) msgs.adicionar("Estado é obrigatório.");
            if (vazio(end.getPais())) msgs.adicionar("País é obrigatório.");
        }

        Contatos cont = inv.getContatos();
        if (cont == null) {
            msgs.adicionar("Contato é obrigatório.");
        } else {
            if (vazio(cont.getEmail())) {
                msgs.adicionar("Email é obrigatório.");
            } else if (!cont.getEmail().contains("@") || !cont.getEmail().contains(".")) {
                msgs.adicionar("E-mail inválido."); // O teste espera 'E-mail' com hífen
            }
            
            if (inv instanceof InvestidorEmpresa && vazio(cont.getNomeParaContato())) {
                msgs.adicionar("Nome para contato é obrigatório para pessoa jurídica.");
            }

            String cel = cont.getTelefoneCelular();
            String fixo = cont.getTelefoneFixo();
            String zap = cont.getNumeroWhatsApp();
            
            // Teste contatosSemTelefone
            if (vazio(cel) && vazio(fixo) && vazio(zap)) {
                msgs.adicionar("Pelo menos um telefone deve ser informado.");
            }
            // Teste contatosTelefoneComLetras
            if (!vazio(cel) && !cel.matches("[0-9]+")) {
                msgs.adicionar("Telefone celular deve conter apenas números.");
            }
        }

        if (inv.getBonus() == null || inv.getBonus().compareTo(BigDecimal.ZERO) < 0) {
            msgs.adicionar("Bônus deve ser maior ou igual a zero.");
        }
    }

    private boolean vazio(String s) {
        return s == null || s.trim().isEmpty();
    }

    private void validarCpf(InvestidorPessoa ip, MensagensValidacao msgs) {
        if (vazio(ip.getCpf())) {
            msgs.adicionar("CPF é obrigatório.");
        } else if (ValidadorCpfCnpj.validarCpf(ip.getCpf()) != null) {
            msgs.adicionar("CPF inválido.");
        }
    }

    private void validarCnpj(InvestidorEmpresa ie, MensagensValidacao msgs) {
        if (vazio(ie.getCnpj())) {
            msgs.adicionar("CNPJ é obrigatório.");
        } else if (ValidadorCpfCnpj.validarCnpj(ie.getCnpj()) != null) {
            msgs.adicionar("CNPJ inválido.");
        }
    }
}