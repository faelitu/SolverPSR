/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

/**
 *
 * @author rmachado
 */
public class Tupla {
    private No no1;
    private No no2; /* para o caso de uma restricao binaria */
    private Integer valor; /* para o caso de uma restricao unaria */
    private int ordem; /* 1 para unaria, 2 para binaria */
    private boolean igual;

    // Construtor de restricao binaria
    public Tupla(No no1, No no2, boolean igual) {
        this.no1 = no1;
        this.no2 = no2;
        this.valor = null;
        this.ordem = 2;
        this.igual = igual;
    }
    
    // Construtor de restricao unaria
    public Tupla(No no, Integer valor, boolean igual) {
        this.no1 = no;
        this.valor = valor;
        this.no2 = null;
        this.ordem = 1;
        this.igual = igual;
    }

    public boolean isIgual() {
        return igual;
    }

    public No getNo1() {
        return no1;
    }

    public No getNo2() {
        return no2;
    }

    public Integer getValor() {
        return valor;
    }

    public int getOrdem() {
        return ordem;
    }
    
    
}
