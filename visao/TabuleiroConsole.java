
package br.com.code.cm.modelo.visao;

import br.com.code.cm.modelo.Tabuleiro;
import br.com.code.cm.modelo.excecao.ExplosaoException;
import br.com.code.cm.modelo.excecao.SairException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner (System.in);
    
    
    public TabuleiroConsole (Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        
        executarJogo();
    }

    
    
    private void executarJogo() {
        try{ 
            boolean continuar = true;
            
            while(continuar){
                cicloDoJogo();
                System.out.println("Outra partida ? (S/n) ");
                String resposta = entrada.nextLine();
                if("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else{
                    tabuleiro.Reiniciar();
                }
            }
        }catch(SairException e ){
            System.out.println("Tchau!!!!");
        }finally{
            entrada.close();
        }
    }

    private void cicloDoJogo() {
        try{
            while(!tabuleiro.objetivoAlcancado()){
                
                System.out.println(tabuleiro);
                String digitado = CapturarValorDigitado("Digite valor de linha e coluna (x,y): ");
                
                Iterator<Integer> xy =
                        Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();
                
                String digitados = CapturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar");
                
                if("1".equals(digitados)){
                    tabuleiro.abrir(xy.next(),xy.next());
                }
                else if("2".equals(digitados)){
                    tabuleiro.marcar(xy.next(),xy.next());
                }
            }      
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!");
        }catch(ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("Você Perdeu!!");
        }
    }
    
    private String CapturarValorDigitado(String texto){
        System.out.println(texto);
        String digitado = entrada.nextLine();
        
        if("sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
