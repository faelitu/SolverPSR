/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.util.LinkedList;

/**
 *
 * @author rmachado
 */
public class Solver {
    private LinkedList<No> variaveis, vars; // vars -> variavel auxiliar
    private LinkedList<Restricao> restricoes;

    public Solver(LinkedList<No> variaveis, LinkedList<Restricao> restricoes) {
        this.variaveis = vars = variaveis;
        this.restricoes = restricoes;
        updateDominioR1();
    }

    public LinkedList<No> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(LinkedList<No> variaveis) {
        this.variaveis = variaveis;
    }

    public LinkedList<Restricao> getRestricoes() {
        return restricoes;
    }

    public void setRestricoes(LinkedList<Restricao> restricoes) {
        this.restricoes = restricoes;
        updateDominioR1();
    }
    
    public LinkedList<No> getVars() {
        return vars;
    }
    
    public void resetVars() {
        vars = variaveis;
    }
    
    private void updateDominioR1() { // atualiza o dominio das variaveis em relacao as restricoes unarias
        for (Restricao r : restricoes) {
            if (r.tipo.equals("TUPLA") && r.tupla.getOrdem() == 1) {
                No aux = r.tupla.getNo1();
                for (No no : variaveis) {
                    if (no.getId() == aux.getId()) {
                        no.removeDoDominio(r.tupla.getValor());
                    }
                }
            }
        }
    }
    
    private void updateDominioR2(No no) { // atualiza o dominio das variaveis em relacao as restricoes binarias e n-arias
        No no2;
        for (Restricao r : restricoes) {
            if (r.tipo.equals("TUPLA") && r.tupla.getOrdem() == 2 && 
                    (r.tupla.getNo1().getId() == no.getId() || r.tupla.getNo2().getId() == no.getId())) { // restricao binaria
                if (r.tupla.getNo1().getId() == no.getId()) {
                    no2 = r.tupla.getNo2();
                } else {
                    no2 = r.tupla.getNo1();
                }
                
                vars.get(no2.getId()).removeDoDominio(no.getAtribuicao());
                
            } else if (r.tipo.equals("ALLDIFF") && r.diff.temNo(no)) { // restricao n-aria TodosDiferentes
                for (int i = 0; i < r.diff.getDiferentes().size(); i++) {
                    vars.get(r.diff.getDiferentes().get(i).getId()).removeDoDominio(no.getAtribuicao());
                }
            }
        }
    }
    
    public LinkedList<No> solveMaisRestrita() {        
        No noRest = vars.getFirst();
        for (int i = 0; i < vars.size(); i++) {
            if (vars.get(i).getAtribuicao() == null) {
                noRest = vars.get(i);
                break;
            }
        }
        for (No no : vars) {
            if (no.getAtribuicao() == null && no.getDominioSize() < noRest.getDominioSize()) {
                noRest = no;
            }
        }
        
        noRest.setAtribuicao(noRest.getDominio().getFirst());
        vars.get(noRest.getId()).setAtribuicao(noRest.getAtribuicao());
        updateDominioR2(noRest);
        
        for (No no : vars) {
            if (no.getAtribuicao() == null && !no.getDominio().isEmpty()) { // quer dizer que ainda existem variaveis com  atribuicoes a serem feitas
                solveMaisRestrita();
            }
        }
        
        return vars;
    }
    
    public LinkedList<No> solveMenosRestrita() {        
        No noRest = vars.getFirst();
        for (int i = 0; i < vars.size(); i++) {
            if (vars.get(i).getAtribuicao() == null) {
                noRest = vars.get(i);
                break;
            }
        }
        for (No no : vars) {
            if (no.getAtribuicao() == null && no.getDominioSize() > noRest.getDominioSize()) {
                noRest = no;
            }
        }
        
        noRest.setAtribuicao(noRest.getDominio().getFirst());
        vars.get(noRest.getId()).setAtribuicao(noRest.getAtribuicao());
        updateDominioR2(noRest);
        
        for (No no : vars) {
            if (no.getAtribuicao() == null && !no.getDominio().isEmpty()) { // quer dizer que ainda existem variaveis com  atribuicoes a serem feitas
                solveMenosRestrita();
            }
        }
        
        return vars;
    }
}