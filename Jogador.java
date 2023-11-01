import java.io.Serializable;

public class Jogador implements Serializable{
    //Atributos do jogador.
    private String nome;
    private char Tipo_jog;
    private JogoGeneral jogoG;

    //metodo construtor com parametros.
    public Jogador(String nome, char Tipo){
        this.nome = nome;
        Tipo_jog = Tipo;
        jogoG = new JogoGeneral();
    }
    //Metodo acessador do nome do jogador.
    public String GetNome(){
        return this.nome;
    }
    //Metodo acessador de tipo do jogador.
    public char GetTipo(){
        return Tipo_jog;
    }
    //Metodo acessador de jogo
    public JogoGeneral getJogo(){
        return this.jogoG;
    }
    //Metodo que chama o setJogadas.
    public void zeraPont(){
        jogoG.setJogadas();
    }
    //Metodo que invoca o metodo para rolar os dados.
    public void jogarDados(){
        this.jogoG.RolarDados();
    }
    //Metodo em que o jogador escolhe a jogada.
    public void EscolherJogada(int jogada){
        this.jogoG.pontuarJogada(jogada);
    }
    //Estrategia de maquina que retorna um inteiro representando a jogada escolhida.
    public int EstrategiaMaq(){
        int escolha=-1;//Variavel auxiliar que guarda a posicao que representa a jogada possivel.
        //for que percorre o vetor de jogadas e escolhe a primeira jogada validada na ordem decrescente.
        //A variavel escolha tem a finalidade de parar o laco quando uma jogada e validada.
        for(int i = jogoG.Getjogadas().length; i > 0 && escolha == -1; i--){
            if(jogoG.Getjogadas()[i-1] == -1)
                if(jogoG.validaJogadas(i) == true)
                    escolha = i;
        }//Caso a jogada seja validada e a variavel jogada recebeu atribuicao retorna o valor.
        if(escolha != -1)
            return escolha;
        else{//Caso nao seja possivel validar a jogada a primeira jogada em ordem decrescente 
            //que ainda nao foi utilizada e escolhida para ser zerada.
            for(int i = jogoG.Getjogadas().length; i > 0 && escolha == -1; i--){
                if(jogoG.Getjogadas()[i-1] == -1){//Acessa as posicoes do vetor de jogadas.
                    escolha = i;
                }
            }
            return escolha;
        }
    }
    //Metodo que mostra o vetor de jogadas.
    public void MostraJogadas(){
        int[] vet = this.jogoG.Getjogadas();
        for(int i=0; i < vet.length; i++){
            if(vet[i] == -1)
                System.out.printf("-\t");
            else
                System.out.printf(vet[i]+"\t");
        }
    }
    //Metodo que soma o valor de todas as posicoes do vetor jogadas de cada jogador.
    public int SomaTot(){
        int[] vetJogadas = jogoG.Getjogadas();//Invocacao do metodo acessador de jogadas.
        int soma=0;
        for(int i=0; i < vetJogadas.length; i++){
            if(vetJogadas[i] != -1)
                soma += vetJogadas[i];//soma os valores das posicoes do vetor de jogadas.
        }
        return soma;
    }

}
