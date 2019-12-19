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
public class TodosDiferentes {
    private LinkedList<No> diferentes;

    public TodosDiferentes(LinkedList<No> diferentes) {
        this.diferentes = diferentes;
    }

    public LinkedList<No> getDiferentes() {
        return diferentes;
    }

    public void setDiferentes(LinkedList<No> diferentes) {
        this.diferentes = diferentes;
    }
    
    public boolean temNo(No no) {
        return this.diferentes.contains(no);
    }
    
}
