package br.edu.cs.poo.ac.bolsa.util;

import java.util.ArrayList;
import java.util.List;

public class MensagensValidacao {
    
    private List<String> mensagens;
    
    public MensagensValidacao() {
        this.mensagens = new ArrayList<>();
    }
    
    public void adicionar(String mensagem) {
        if (mensagem != null && !mensagem.trim().isEmpty()) {
            this.mensagens.add(mensagem);
        }
    }
    
    public boolean estaVazio() {
        return this.mensagens.isEmpty();
    }
    
    public List<String> obterMensagens() {
        return new ArrayList<>(this.mensagens);
    }
    
    public String[] getMensagens() {
        return this.mensagens.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        if (this.estaVazio()) {
            return "Validação OK!";
        }
        
        StringBuilder texto = new StringBuilder();
        for (String msg : this.mensagens) {
            texto.append("X").append(msg).append("\n");
        }
        return texto.toString();
    }
}