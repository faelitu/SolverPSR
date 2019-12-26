/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instancias;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author rmachado
 */
public class Sudoku {

    public static void main(String[] args) throws Exception {
        // Initial matrix:
        // Set 0 for values to be set by the solver
        int[][] matrix = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                          {6, 0, 0, 1, 9, 5, 0, 0, 0},
                          {0, 9, 8, 0, 0, 0, 0, 6, 0},
                          {8, 0, 0, 0, 6, 0, 0, 0, 3},
                          {4, 0, 0, 8, 0, 3, 0, 0, 1},
                          {7, 0, 0, 0, 2, 0, 0, 0, 6},
                          {0, 6, 0, 0, 0, 0, 2, 8, 0},
                          {0, 0, 0, 4, 1, 9, 0, 0, 5},
                          {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        String name = "sudoku"; // Set this variable as the name you want for the file
        File file = new File(name + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        
        // Write type of instance
        bw.write("sudoku\n");
        
        // Write variables
        bw.append("X={A11,A12,A13,A14,A15,A16,A17,A18,A19,"
                  + "A21,A22,A23,A24,A25,A26,A27,A28,A29,"
                  + "A31,A32,A33,A34,A35,A36,A37,A38,A39,"
                  + "A41,A42,A43,A44,A45,A46,A47,A48,A49,"
                  + "A51,A52,A53,A54,A55,A56,A57,A58,A59,"
                  + "A61,A62,A63,A64,A65,A66,A67,A68,A69,"
                  + "A71,A72,A73,A74,A75,A76,A77,A78,A79,"
                  + "A81,A82,A83,A84,A85,A86,A87,A88,A89"
                  + "A91,A92,A93,A94,A95,A96,A97,A98,A99}\n");
        
        // Write domain
        bw.append("D={1,2,3,4,5,6,7,8,9}\n");
        
        // Write restrictions
        bw.append("C={");
            // First, set lines to be AllDifferent
        bw.append("(A11,A12,A13,A14,A15,A16,A17,A18,A19),"
                + "(A21,A22,A23,A24,A25,A26,A27,A28,A29),"
                + "(A31,A32,A33,A34,A35,A36,A37,A38,A39),"
                + "(A41,A42,A43,A44,A45,A46,A47,A48,A49),"
                + "(A51,A52,A53,A54,A55,A56,A57,A58,A59),"
                + "(A61,A62,A63,A64,A65,A66,A67,A68,A69),"
                + "(A71,A72,A73,A74,A75,A76,A77,A78,A79),"
                + "(A81,A82,A83,A84,A85,A86,A87,A88,A89),"
                + "(A91,A92,A93,A94,A95,A96,A97,A98,A99),");
            // Secondly, set columns to be AllDifferent
        bw.append("(A11,A21,A31,A41,A51,A61,A71,A81,A91),"
                + "(A12,A22,A32,A42,A52,A62,A72,A82,A92),"
                + "(A13,A23,A33,A43,A53,A63,A73,A83,A93),"
                + "(A14,A24,A34,A44,A54,A64,A74,A84,A94),"
                + "(A15,A25,A35,A45,A55,A65,A75,A85,A95),"
                + "(A16,A26,A36,A46,A56,A66,A76,A86,A96),"
                + "(A17,A27,A37,A47,A57,A67,A77,A87,A97),"
                + "(A18,A28,A38,A48,A58,A68,A78,A88,A98),"
                + "(A19,A29,A39,A49,A59,A69,A79,A89,A99),");
            // Then set squares to be AllDifferent
        bw.append("(A11,A12,A13,A21,A22,A23,A31,A32,A33),"
                + "(A14,A15,A16,A24,A25,A26,A34,A35,A36),"
                + "(A17,A18,A19,A27,A28,A29,A37,A38,A39),"
                + "(A41,A42,A43,A51,A52,A53,A61,A62,A63),"
                + "(A44,A45,A46,A54,A55,A56,A64,A65,A66),"
                + "(A47,A48,A49,A57,A58,A59,A67,A68,A69),"
                + "(A71,A72,A73,A81,A82,A83,A91,A92,A93),"
                + "(A74,A75,A76,A84,A85,A86,A94,A95,A96),"
                + "(A77,A78,A79,A87,A88,A89,A97,A98,A99)");
            // Finally, set the unary restrictions of equality
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] != 0) {
                    bw.append(",[A"+(i+1)+(j+1)+","+matrix[i][j]+"]");
                }
            }
        }
            // End of restrictions
        bw.append("}");
        
        bw.close();
    }
}
