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
public class Restricao {
    public Tupla tupla;
    public TodosDiferentes diff;
    public String tipo;

    public Restricao(Tupla tupla) {
        this.tupla = tupla;
        this.diff = null;
        this.tipo = "TUPLA";
    }

    public Restricao(TodosDiferentes diff) {
        this.diff = diff;
        this.tupla = null;
        this.tipo = "ALLDIFF";
    }
    
    
}
