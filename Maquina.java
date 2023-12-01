import java.util.Random;

public class Maquina extends Jogador{
    public Maquina(String nome, char tipo){
        super(nome, tipo);
    }
    public int escolherJogo(){
        Random x = new Random();
        return x.nextInt(2) + 1;
    }
    
    /*public boolean ehJogoGeneral(){
        for(int i=0; i < getJogoDados().length; i++){
            if(getJogoDados()[i] instanceof JogoGeneral){
                return true;
            }
            else
                return false;
        }
    }*/
    //Metodo em que o jogador escolhe a jogada.
    public void EscolherJogada(int i){
        if(getJogoDados()[i] instanceof JogoGeneral){
            JogoGeneral varAux = (JogoGeneral)getJogoDados()[i];
            varAux.pontuarJogada(i);
        }
            
    }
    //Estrategia de maquina que retorna um inteiro representando a jogada escolhida.
    public int EstrategiaMaq(int i){
        int escolha=-1;//Variavel auxiliar que guarda a posicao que representa a jogada possivel.
        //for que percorre o vetor de jogadas e escolhe a primeira jogada validada na ordem decrescente.
        //A variavel escolha tem a finalidade de parar o laco quando uma jogada e validada.
        if(getJogoDados()[i] instanceof JogoGeneral){
            JogoGeneral varAux = (JogoGeneral)getJogoDados()[i];
            for(int j = varAux.Getjogadas().length; j > 0 && escolha == -1; j--){
                if(varAux.Getjogadas()[i-1] == -1)
                    if(varAux.validaJogadas(j) == true)
                        escolha = j;
            }//Caso a jogada seja validada e a variavel jogada recebeu atribuicao retorna o valor.
            if(escolha != -1){
                System.out.println("\nJogada escolhida: "+escolha);
                return escolha;
            }
            else{//Caso nao seja possivel validar a jogada a primeira jogada em ordem decrescente 
                //que ainda nao foi utilizada e escolhida para ser zerada.
                for(int j = varAux.Getjogadas().length; j > 0 && escolha == -1; j--){
                    if(varAux.Getjogadas()[j-1] == -1){//Acessa as posicoes do vetor de jogadas.
                        escolha = j;
                    }
                }
                System.out.println("\nJogada escolhida: "+escolha);
                return escolha;
            }
        }
        System.out.println("\nJogada escolhida: "+escolha);
        return escolha;
    }
}
