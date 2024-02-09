import java.util.Random;

public class Maquina extends Jogador{
    //Metodo construtor padrao.
    public Maquina(String nome, char tipo){
        super(nome, tipo);
    }
    //escolhe o tipo de jogo aleatoriamente.
    public int escolherJogo(){

        System.out.printf("\nInforme o tipo de jogo em que deseja apostar: (1-Jogo general ou 2-Jogo azar): ");
        Random x = new Random();
        int escolha = x.nextInt(2) + 1;
        System.out.println("Escolha: "+escolha);
        return escolha;
    }
    //Retorna o valor a ser apostado. 
    public float ApostaMaquina(){
        if(GetSaldo() > 40)
            return (float)40;
        else if(GetSaldo() > 10)
            return (float)10;
        else
            return (float)5;
    }

    //Estrategia de maquina que retorna um inteiro representando a jogada escolhida.
    public void EstrategiaMaq(JogoGeneral jogoG){
        
        
        for(int r=0; r < 13; r++){
            //Variavel auxiliar que guarda a posicao que representa a jogada possivel.
            //A variavel escolha tem a finalidade de parar o laco quando uma jogada e validada.
            int escolha=-1;
            System.out.println("\n\nRolando dados para "+GetNome()+"("+GetTipo()+")...");
            jogoG.RolarDados();
            //for que percorre o vetor de jogadas e escolhe a primeira jogada validada na ordem decrescente.
            for(int j = jogoG.Getjogadas().length; j > 0 && escolha == -1; j--){
                if(jogoG.Getjogadas()[j-1] == -1)
                    if(jogoG.validaJogadas(j) == true)
                        escolha = j;
            }//Caso a jogada seja validada e a variavel jogada recebeu atribuicao retorna o valor.
            if(escolha != -1){
                System.out.println("\nJogada escolhida: "+escolha);
                jogoG.pontuarJogada(escolha);
                jogoG.MostraJogadas();
            }
            else{//Caso nao seja possivel validar a jogada a primeira jogada em ordem decrescente 
                //que ainda nao foi utilizada e escolhida para ser zerada.
                for(int j = jogoG.Getjogadas().length; j > 0 && escolha == -1; j--){
                    if(jogoG.Getjogadas()[j-1] == -1){//Acessa as posicoes do vetor de jogadas.
                        escolha = j;
                    }
                }
                System.out.println("\nJogada escolhida: "+escolha);
                //pontua a jogada escolhida.
                jogoG.pontuarJogada(escolha);
                jogoG.MostraJogadas();
            }
            
        }
    }
}
