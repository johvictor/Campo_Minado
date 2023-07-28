
package br.com.code.cm.modelo;

import br.com.code.cm.modelo.visao.TabuleiroConsole;


public class Aplicacao {
    
    public static void main(String[] args) {
        Tabuleiro tabu = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabu);
    }          
}
