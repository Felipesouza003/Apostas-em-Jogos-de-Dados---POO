import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Campeonato implements Serializable{
    private Jogador[] jogadores;//vetor de instancias da classe Jogador.
    private int numJog;//Variavel para controlar o numero de jogadores.
    
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
        Scanner teclado = new Scanner (System.in);
        char tipo=' ';//Variavel para ler o tipo do jogador.
        String Cpf;
        String conta;
        String numeroBanco;
        String nome;

        if(numJog < 10){
            do{
                System.out.printf("Informe o nome do jogador(a): ");
                nome = teclado.nextLine();
                System.out.printf("Agora informe o tipo do jogador(a) [H - humano ou M - máquina]");
                tipo = teclado.next().charAt(0);
                //verificacao  do tipo de jogador informado.
                if(tipo != 'm' && tipo != 'M' && tipo != 'h' && tipo != 'H')
                    System.out.println("Tipo de jogador informado é invalido por favor informe novamente!");
            }while(tipo != 'm' && tipo != 'M' && tipo != 'h' && tipo != 'H');

            if(tipo == 'h' || tipo == 'H'){
                System.out.printf("Informe o CPF do(a) jogador(a): ");
                Cpf = teclado.nextLine();
                teclado.nextLine();
                System.out.printf("Informe a conta do(a) jogador(a): ");
                conta = teclado.nextLine();
                //teclado.nextLine();
                System.out.printf("Informe o numero do banco do(a) jogador(a): ");
                numeroBanco = teclado.nextLine();
                //teclado.nextLine();
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

        if(numJog == 0)//Verifica se existe pelo menos um jogador antes de iniciar uma nova rodada.
            System.out.println("\nPor favor inclua jogadores(as) antes de começar o campeonato!");
        else{
            for(int i=0; i < 10; i++){//for que controla os jogadores.
                for(int j=0; j < numJog; j++){//for que controla os jogadores que escolhem a jogada.
                    System.out.println();
                    if(jogadores[j].GetSaldo() > 0){
                        if(jogadores[j] instanceof Humano){
                            Humano humano = (Humano)jogadores[j];
                            humano.CriarJogo(humano.escolherJogo(), i);
                            if(humano.getJogoDados()[i] instanceof JogoGeneral){
                                JogoGeneral AuxJogo = (JogoGeneral)humano.getJogoDados()[i];
                                AuxJogo.setAposta(humano.escolherValorAposta());
                                for(int r=0; r < 13; r++){
                                    System.out.println("\n\nRolando dados para "+humano.GetNome()+"("+humano.GetTipo()+")...");
                                    AuxJogo.RolarDados();
                                    AuxJogo.pontuarJogada(humano.EscolherJogada());
                                    AuxJogo.MostraJogadas();
                                }
                                if(AuxJogo.SomaTotAte12() > 2*AuxJogo.Getjogadas()[12]){
                                    humano.SetSaldo(humano.GetSaldo()- AuxJogo.getAposta());
                                    System.out.println("\n\nVoce venceu e seu saldo agora eh de R$: "+humano.GetSaldo());
                                }
                                else
                                    System.out.println("Voce perdeu desejo mais sorte na proxima vez!");

                            }
                        }
                        else{
                            Maquina humano = (Maquina)jogadores[j];
                            humano.escolherJogo();
                        }
                        
                    }
                }
            }
        }
    }

    //Metodo que exclui o jogador pelo nome.
    public void exluirJogador(){
        Scanner teclado = new Scanner (System.in);
        String nome;
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
