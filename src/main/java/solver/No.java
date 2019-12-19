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
public class No {
    private int id;
    private String nome;
    private LinkedList<Integer> dominio;
    private Integer atribuicao = null;

    public No(int id, String nome, LinkedList<Integer> dominio, Integer atribuicao) {
        this.id = id;
        this.nome = nome;
        this.dominio = dominio;
        this.atribuicao = atribuicao;
    }
    
    public No(int id, String nome, LinkedList<Integer> dominio) {
        this.id = id;
        this.nome = nome;
        this.dominio = dominio;
    }

    public No(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.dominio = new LinkedList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Integer> getDominio() {
        return dominio;
    }

    public void setDominio(LinkedList<Integer> dominio) {
        this.dominio = dominio;
    }

    public Integer getAtribuicao() {
        return atribuicao;
    }

    public void setAtribuicao(Integer atribuicao) {
        this.atribuicao = atribuicao;
    }
    
    public int getDominioSize() {
        return this.dominio.size();
    }
    
    public void insereNoDominio(Integer valor) {
        int i;
        this.dominio.add(valor);
    }
    
    public void removeDoDominio(Integer valor) {
        if (this.dominio.contains(valor)) {
            this.dominio.remove(valor);
        }
    }
}
