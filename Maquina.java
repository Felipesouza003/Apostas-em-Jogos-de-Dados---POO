public class Maquina extends Jogador{
    //Metodo em que o jogador escolhe a jogada.
    /*public void EscolherJogada(int jogada){
        this.jogoD[jogada].pontuarJogada(jogada);
    }*/
    //Estrategia de maquina que retorna um inteiro representando a jogada escolhida.
    public int EstrategiaMaq(){
        int escolha=-1;//Variavel auxiliar que guarda a posicao que representa a jogada possivel.
        //for que percorre o vetor de jogadas e escolhe a primeira jogada validada na ordem decrescente.
        //A variavel escolha tem a finalidade de parar o laco quando uma jogada e validada.
        for(int i = jogoD[i].Getjogadas().length; i > 0 && escolha == -1; i--){
            if(jogoD.Getjogadas()[i-1] == -1)
                if(jogoD.validaJogadas(i) == true)
                    escolha = i;
        }//Caso a jogada seja validada e a variavel jogada recebeu atribuicao retorna o valor.
        if(escolha != -1)
            return escolha;
        else{//Caso nao seja possivel validar a jogada a primeira jogada em ordem decrescente 
            //que ainda nao foi utilizada e escolhida para ser zerada.
            for(int i = jogoD.Getjogadas().length; i > 0 && escolha == -1; i--){
                if(jogoD.Getjogadas()[i-1] == -1){//Acessa as posicoes do vetor de jogadas.
                    escolha = i;
                }
            }
            return escolha;
        }
    }
}
