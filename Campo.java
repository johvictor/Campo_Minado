package br.com.code.cm.modelo;


import br.com.code.cm.modelo.excecao.ExplosaoException;
import java.util.ArrayList;
import java.util.List;

public class Campo {
    
    private final int linha;
    private final int coluna;
    
    
    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;
    
    private List<Campo> vizinhos= new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    
    
    
    boolean AdicionarVizinho (Campo vizinho){
            
            boolean linhaDiferente = linha != vizinho.linha;
            boolean colunaDiferente = coluna != vizinho.coluna;
            
            boolean diagonal = linhaDiferente && colunaDiferente;
            
            int deltaLinha = Math.abs(linha - vizinho.linha);
            int deltaColuna= Math.abs(coluna - vizinho.coluna);
            int deltaGeral = deltaLinha + deltaColuna;
            
            if(deltaGeral  ==1 && !diagonal){
                vizinhos.add(vizinho);
                return true;
            } else if( deltaGeral == 2 && diagonal){
                vizinhos.add(vizinho);
                return true;
            }else{
              return false; 
            }
            
        }
 
        
    void AlternarMarcacao(){
            if(!aberto){
                this.marcado = !marcado;
            }
        }
        
        
    boolean abrir(){
            if(!aberto && !marcado){ //se o campo não tiver minado e não marcado 
               aberto=true;
                if(minado){
                    throw new ExplosaoException();
                }
                if(VizinhacaSegura()){
                    vizinhos.forEach(v -> v.abrir());
                }
                return true;
            } else{
                return false;
            }
        }

    
    public int getLinha() {
        return linha;
    }

    
    public int getColuna() {
        return coluna;
    }
        
        
    void minar(){
                if(!minado){
                    minado= true;
                }
        }
        
        
    boolean VizinhacaSegura(){
            return vizinhos.stream().noneMatch(v ->v.minado);
        }
        
    
    public boolean isMinado(){
    return minado;
}    
    
    
    public boolean isMarcado(){
            return marcado;
        }
      
    
    void setAberto(boolean aberto){
            this.aberto= aberto;
                    }
    
    public boolean isAberto(){
        return aberto;
    }
    
    
    boolean objetivoAlcancado (){
            boolean desvendado = !minado && aberto;
            boolean protegido = minado && marcado;
            return desvendado || protegido;
        }
       
    
    long minasNaVizinhanca(){
            return vizinhos.stream().filter(v -> v.minado).count(); 
        }
        
    
    void Reiniciar(){
            aberto = false;
            minado = false;
            marcado = false;
        }
        
    
    
    
    public String toString(){
            if(marcado){
                return "X";
           }else if (aberto && minado){
             return "*";
            }else if(aberto && minasNaVizinhanca() >0){
                return Long.toString(minasNaVizinhanca());
          }else if(aberto){
               return " ";
            }else{
               return "?";
           }
        }
}
