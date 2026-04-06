package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {

    public static ResultadoValidacao validarCpf(String cpf) {
        //Verifica se não informado
        if (cpf == null || cpf.isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        //Remove máscara se houver (pontos e traço)
        cpf = cpf.replaceAll("[.\\-]", "");

        //Verifica tamanho e se todos os dígitos são iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        //Valida primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);

        if (primeiroDigito >= 10){
            primeiroDigito = 0;
        }

        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        // Valida segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);

        if (segundoDigito >= 10){
            segundoDigito = 0;
        }

        if (segundoDigito != Character.getNumericValue(cpf.charAt(10))) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }

    public static ResultadoValidacao validarCnpj(String cnpj) {
        // erifica se não informado
        if (cnpj == null || cnpj.isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }

        //Remove máscara se houver (pontos, barra e traço)
        cnpj = cnpj.replaceAll("[.\\-/]", "");

        // erifica tamanho e se todos os dígitos são iguais
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }

        //Valida primeiro dígito verificador
        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
        }
        int primeiroDigito = 11 - (soma % 11);

        if (primeiroDigito >= 10){
            primeiroDigito = 0;
        }

        if (primeiroDigito != Character.getNumericValue(cnpj.charAt(12))) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        //Valida segundo dígito verificador
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
        }
        int segundoDigito = 11 - (soma % 11);
        
        if (segundoDigito >= 10){
            segundoDigito = 0;
        }

        if (segundoDigito != Character.getNumericValue(cnpj.charAt(13))) {
            return ResultadoValidacao.DV_INVALIDO;
        }

        return null;
    }
}