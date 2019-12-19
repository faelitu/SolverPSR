/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author rmachado
 */
public class Main {
    
    // funcao auxiliar para escrever os resultados na tela
    public void escreve(LinkedList<No> result, String[] D) {
        for (int i = 0; i < result.size(); i++) {
            String atributo = null;
            for (int j = 0; j < D.length; j++) {
                if (result.get(i).getAtribuicao() == j) {
                    atributo = D[j];
                }
            }
            System.out.println(result.get(i).getNome() + " - " + atributo);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        LinkedList<No> nos = new LinkedList();
        LinkedList<Restricao> restricoes = new LinkedList();
        String[] X, D = null, C; /* X = variaveis
                                    D = dominio
                                    C = condicoes */
        No noAux = null, noAux2 = null;
        
        File file = new File("australia.txt"); // instancia de entrada
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (count == 0) { // VARIAVEIS
                X = line.split(",");
                X[0] = X[0].substring(3);
                X[X.length-1] = X[X.length-1].substring(0, X[X.length-1].length()-1);
                for (int i = 0; i < X.length; i++) {
                    noAux = new No(i, X[i]);
                    nos.add(noAux);
                }
            } else if (count == 1) { // DOMINIO
                D = line.split(",");
                D[0] = D[0].substring(3);
                D[D.length-1] = D[D.length-1].substring(0, D[D.length-1].length()-1);
                for (No no : nos) {
                    for (int i = 0; i < D.length; i++)
                        no.insereNoDominio(i);
                }
            } else if (count == 2) { // RESTRICOES
                C = line.split(",(?![^\\(\\[]*[\\]\\)])");
                C[0] = C[0].substring(3);
                C[C.length-1] = C[C.length-1].substring(0, C[C.length-1].length()-1);
                for (int i = 0; i < C.length; i++) {
                    //System.out.println(C[i]);
                    String[] rests = C[i].split(",");
                    rests[0] = rests[0].substring(1);
                    rests[rests.length-1] = rests[rests.length-1].substring(0, rests[rests.length-1].length()-1);
                    for (int j = 0; j < rests.length; j++) {
                        if (rests.length == 2) { // TUPLA
                            int flag = 0;
                            Integer valor = 0;
                            for (int k = 0; k < D.length; k++) {
                                if (rests[1].equals(D[k])) { // Tupla unaria
                                    flag = 1;
                                    valor = k;
                                    break;
                                } else { // Tupla binaria
                                    flag = 2;
                                }
                            }
                            
                            for (No no : nos) {
                                if (no.getNome().equals(rests[0])) {
                                    noAux = no;
                                } else if (flag == 2 && no.getNome().equals(rests[1])) {
                                    noAux2 = no;
                                }
                            }
                            
                            if (flag == 1) { // Tupla unaria
                                Tupla unaria = new Tupla(noAux, valor);
                                Restricao r = new Restricao(unaria);
                                restricoes.add(r);
                            } else if (flag == 2) { // Tupla binaria
                                Tupla binaria = new Tupla(noAux, noAux2);
                                Restricao r = new Restricao(binaria);
                                restricoes.add(r);
                            }
                        }
                        
                        if (rests.length > 2) { // TODOS DIFERENTES
                            
                        }
                    }
                }
            }
            
            count++;
        } /* FIM Leitura de Intancia */
        
        //INICIO SOLVER
        Main m = new Main();
        Solver solver = new Solver(nos, restricoes);
        LinkedList<No> result;
        
        System.out.println("\n############# NÓ MAIS RESTRITO #############\n");
        result = solver.solveMaisRestrita();
        solver.resetVars();
        m.escreve(result, D);
        
        System.out.println("\n############# NÓ MENOS RESTRITO #############\n");
        result = solver.solveMenosRestrita();
        solver.resetVars();
        m.escreve(result, D);
    }
}
