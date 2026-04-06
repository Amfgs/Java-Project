package br.edu.cs.poo.ac.bolsa.dao;

import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;

public class InvestidorEmpresaDAO extends DAOGenerico {

    public InvestidorEmpresaDAO (){
        inicializarCadastro(InvestidorEmpresa.class);
    }

    public InvestidorEmpresa buscar(String cnpj){
        
    }

}
