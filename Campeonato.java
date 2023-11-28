import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Campeonato implements Serializable{
    private Jogador[] jogadores;//vetor de instancias da classe Jogador.
    private Scanner teclado = new Scanner (System.in);//Variavel para escanear as escolhas dos jogadores.
    private int numJog;//Variavel para controlar o numero de jogadores.
    private String nome;//Variavel para ler o nome dos jogadores.
    
    //Metodo construtor 
    public Campeonato(){
        this.jogadores = new Jogador[10];
        this.numJog=0;
    }
    //Metodo contrutor com dois parametros.
    public Campeonato(Jogador[] jogadores, int numeroJog){
        this.jogadores = jogadores;
        numJog = numeroJog;
    }
    //metodo acessador que retorna o vetor de instancias da classe Jogador.
    public Jogador[] getJogadores() {
        return this.jogadores;
    }
    //Metodo acessador que retorna o numero de jogadores.
    public int getNumJog(){
        return this.numJog;
    }
    //Metodo para incluir novos jogadores.
    public void incluirJogador(){
        char tipo=' ';//Variavel para ler o tipo do jogador.
        String Cpf;
        String conta;
        int numeroBanco;

        if(numJog < 10){
            do{
                System.out.printf("Informe o nome do jogador(a): ");
                nome = teclado.nextLine();
                System.out.printf("Agora informe o tipo do jogador(a) [H - humano ou M - máquina]");
                tipo = teclado.next().charAt(0);
                teclado.nextLine();
                //verificacao  do tipo de jogador informado.
                if(tipo != 'm' && tipo != 'M' && tipo != 'h' && tipo != 'H')
                    System.out.println("Tipo de jogador informado é invalido por favor informe novamente!");
            }while(tipo != 'm' && tipo != 'M' && tipo != 'h' && tipo != 'H');

            if(tipo == 'h' || tipo == 'H'){
                System.out.printf("Informe o CPF do(a) jogador(a): ");
                Cpf = teclado.nextLine();
                System.out.printf("Informe a conta do(a) jogador(a): ");
                conta = teclado.nextLine();
                System.out.printf("Informe o numero do banco do(a) jogador(a): ");
                numeroBanco = teclado.nextInt();

                teclado.nextLine();
                jogadores[numJog] = new Humano(nome, tipo, Cpf, conta, numeroBanco);//Atribui um novo jogador ao vetor de jogadores.
                numJog++;//Como um novo jogador foi incluido aumenta-se a variavel contadora de jogadores

            }
            else{
                jogadores[numJog] = new Maquina(nome, tipo);//Atribui um novo jogador ao vetor de jogadores.
                numJog++;//Como um novo jogador foi incluido aumenta-se a variavel contadora de jogadores  
            }
            System.out.println("\nJogador(a) "+nome+" incluido com sucesso!");
        }
        else//Caso o limite de jogadores seja atingido impede a inclusao de um novo jogador.
            System.out.println("\nNao e mais possivel incluir jogadores(as) inicie o compeonato!");
    }
    //Metodo que inicia um novo compeonato
    public void iniciaCampeonato(){
        int k=0;//Reinicializa o vetor de jogadores no caso de uma nova rodada.
        while(k < numJog){
            jogadores[k].zeraPont();
            k++;
        }
        int jogada=0;//reinicializa a a variavel jogada.
        if(numJog == 0)//Verifica se existe pelo menos um jogador antes de iniciar uma nova rodada.
            System.out.println("\nPor favor inclua jogadores(as) antes de começar o campeonato!");
        else{
            /*for(int i=0; i < 10; i++){//for que controla os jogadores.
                for(int j=0; j < numJog; j++){//for que controla os jogadores que escolhem a jogada.
                    System.out.println();
                    if(jogadores[i].GetSaldo() > 0){

                    }
                    do {
                        System.out.println("\nOpcões de jogadas:");
                        System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                        System.out.println("Para qual jogada vc quer marcar? ");
                        //Verificacao do tipo de jogador caso seja humano le a jogada
                        //caso seja maquina invoca o metodo de estrategia de maquina. 
                        if(jogadores[j].GetTipo() == 'h' || jogadores[j].GetTipo() == 'H'){
                            jogada = teclado.nextInt();
                            teclado.nextLine();
                        }
                        else//o metodo Estrategia de maquina retorna um valor que representa a jogada escolhida.
                            jogada = jogadores[j].EstrategiaMaq();
                        if(jogada < 1 || jogada >13)//Verifica se a jogada escolhida esta dentro dos limites possiveis.
                            System.out.println("Jogada Inválida por favor informe um número entre 1 e 13");
                    } while (jogada < 1 || jogada >13);
                    System.out.println("\nJogada escolhida: "+jogada);
                    //Invocacao do metodo que realmente ecolhe a jogada para ser pontuada.
                    jogadores[j].EscolherJogada(jogada);
                    //Metodo que mostra o vetor de jogadas atulaizado.
                    jogadores[j].MostraJogadas();
                }
            }*/
        }
    }

    //Metodo que exclui o jogador pelo nome.
    public void exluirJogador(){
        if(numJog > 0){//Verifica se existem jogadores antes da exclusao.
            //Imprime a lista de jogadores para escolha.
            System.out.println("\nJogadores Disponíveis:\n");
            for(int i=0; i < numJog; i++)
                System.out.println(jogadores[i].GetNome());
            System.out.printf("\nPor favor informe qual jogador(a) deseja exculir: ");
            nome = teclado.nextLine();//Leitura do jogador para exclusao.
            int pos=-1;
            for(int i=0; i < numJog; i++){//Procura a posicao do jogador escolhido.
                if(jogadores[i].GetNome().equals(nome)){
                    pos = i;
                }
            }//Se a posicao foi encontrada exclui o jogador.
            if(pos != -1){
                //Move os jogadores no vetor que estao a partir do jogador a ser excluido.
                for(int i=pos; i < numJog; i++){
                    jogadores[i] = jogadores[i+1];
                }
                numJog--;//Depois da exclusao o numero de jogadores e decrementado.
                System.out.println("\nJogador(a) "+nome+" Excluido com sucesso!");
            }
            else//Caso o jogador nao seja encontrado exibe a mensagem.
                System.out.println("\nJogador(a) não encontrado!!!");

        }else
            System.out.println("\nNao existem jogadores para exclusao!!!");
    }
    public void gravarArq(){
        File Dados_JogoG = new File("Jogo.dat");//arquivo para gravacao do dados do jogo 

         //Gravar em arquivo
        try {
            FileOutputStream fout = new FileOutputStream(Dados_JogoG);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(jogadores);//gravacao do vetor de instancias da classe Jogador.
            oos.writeObject(numJog);//Gravacao do numero de jogadores existentes.
            oos.flush();
            oos.close();
            fout.close();
            //Mensagem caso a gravacao seja bem sucedida.
            System.out.println("\nDados gravados em Arquivo com sucesso!");
        }
        catch (Exception ex) {
        System.err.println("erro: " + ex.toString());
        }
    }
    public void LerArq(){
        System.out.println();
        File Dados_JogoG = new File("Jogo.dat");//arquivo para gravacao do dados do jogo 
       try {
            FileInputStream fin = new FileInputStream(Dados_JogoG);
            ObjectInputStream oin = new ObjectInputStream(fin);
            /*Lendo os objetos de um arquivo e fazendo a
            coercao de tipos*/
            
            Jogador[] CampAnterior = (Jogador[]) oin.readObject();
            int numJogAnterior = (int) oin.readObject();
            oin.close();
            fin.close();
            this.jogadores = CampAnterior;//Leitura do numero de jogadores gravado no arquivo.
            this.numJog = numJogAnterior; 
            //Mensagem caso haja sucesso na leitura dos dados do arquivo.
            System.out.println("Dados lidos com sucesso!");
        }catch (Exception ex) {
                System.err.println("erro: " + ex.toString());
        }
    }
}
