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
    //Opera as apostas e seta os saldos dos jogadores.
    public void operaAposta(float valorAposta, boolean vitoria, int i){
        if(vitoria){
            //seta o novo saldo do jogador.
            jogadores[i].SetSaldo(jogadores[i].GetSaldo() + valorAposta);
            System.out.println("\n\nVoce venceu e seu saldo agora eh de R$: "+jogadores[i].GetSaldo());
            jogadores[i].SetContJogos(jogadores[i].getContJogos()+1);
        }
        else{
            //seta o novo saldo do jogador.
            jogadores[i].SetSaldo(jogadores[i].GetSaldo() - valorAposta);
            System.out.println("\n\nVoce perdeu e seu saldo agora eh de R$: "+jogadores[i].GetSaldo());
            System.out.println("\nDesejo mais sorte na proxima vez!");
            jogadores[i].SetContJogos(jogadores[i].getContJogos()+1);


        }
    }
    //Metodo para incluir novos jogadores.
    public void incluirJogador(){
        Scanner teclado = new Scanner (System.in);
        char tipo=' ';//Variavel para ler o tipo do jogador.
        String Cpf;
        String conta;
        String numeroBanco;
        String nome;
        String agencia;

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
                System.out.printf("Informe o numero do banco do(a) jogador(a): ");
                numeroBanco = teclado.nextLine();
                System.out.printf("Informe a agencia do(a) jogador(a): ");
                agencia = teclado.nextLine();
                jogadores[numJog] = new Humano(nome, tipo, Cpf, conta, numeroBanco, agencia);//Atribui um novo jogador ao vetor de jogadores.
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
        if(numJog == 0)//Verifica se existe pelo menos um jogador antes de iniciar uma nova rodada.
            System.out.println("\nPor favor inclua jogadores(as) antes de começar o campeonato!");
        else{
                for(int j=0; j < numJog; j++){//for que controla os jogadores que escolhem a jogada.
                    System.out.println();
                    if(jogadores[j].getContJogos() < 10){
                        if(jogadores[j].GetSaldo() > 0){
                            //Uso de instanceof 
                            if(jogadores[j] instanceof Humano){ 
                                //Instancia do tipo humano.
                                Humano humano = (Humano)jogadores[j];
                                humano.CriarJogo(humano.escolherJogo(), jogadores[j].getContJogos());
                                
                                //Comparacao do tipo do jogo.
                                if(humano.getJogoDados()[jogadores[j].getContJogos()] instanceof JogoGeneral){
                                    //Instancia do tipo Jogo Geneal.
                                    JogoGeneral AuxJogo = (JogoGeneral)humano.getJogoDados()[jogadores[j].getContJogos()];
                                    
                                    //Modifica valor da aposta.
                                    AuxJogo.setAposta(humano.escolherValorAposta());
                                    //chama metodo escolher jogada.
                                    humano.EscolherJogada(AuxJogo);
    
                                    operaAposta(AuxJogo.getAposta(), AuxJogo.resultadoGeneral(), j);
    
                                }else{
                                    JogoAzar AuxJogo = (JogoAzar)humano.getJogoDados()[jogadores[j].getContJogos()];
                                    //Seta aposta do jogo atualmente.
                                    AuxJogo.setAposta(humano.escolherValorAposta());
                                    //executa o jogo azar.
                                    AuxJogo.execJog();
                                    operaAposta(AuxJogo.getAposta(), AuxJogo.resultadoAzar(), j);

    
                                }
                            }
                            else{
                                //logica para jogador maquina.
                                Maquina maquina = (Maquina)jogadores[j];
                                //Criacao de jogo para  maquina.
                                maquina.CriarJogo(maquina.escolherJogo(), jogadores[j].getContJogos());
                                //instanceof para o tipo de jogo.
                                if(maquina.getJogoDados()[maquina.getContJogos()] instanceof JogoGeneral){
                                    JogoGeneral AuxJogo = (JogoGeneral)maquina.getJogoDados()[maquina.getContJogos()];
                                     //seta a aposta atual do jogo.   
                                    AuxJogo.setAposta(maquina.ApostaMaquina());
                                    maquina.EstrategiaMaq(AuxJogo);
                                    
                                    operaAposta(AuxJogo.getAposta(), AuxJogo.resultadoGeneral(), j);
                                }
                                else{
                                    //logica da maquina para jogo Azzar.
                                    JogoAzar AuxJogo = (JogoAzar)maquina.getJogoDados()[maquina.getContJogos()];
                                    AuxJogo.setAposta(maquina.ApostaMaquina());
                                    AuxJogo.execJog();
                                    operaAposta(AuxJogo.getAposta(), AuxJogo.resultadoAzar(), j);
                                }
                                
                            }
                            
                        }
                        else
                            System.out.println("Jogador(a) "+jogadores[j].GetNome()+" sem saldo!");

                    }
                    else
                        System.out.println("Limite de Apostas do(a) jogador(a) "+jogadores[j].GetNome()+" atingido!");
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
    //Metodos para procurar instancias do tipo humano.
    public boolean ExisteHumano(){
        for(int i=0; i < numJog; i++){
            if(jogadores[i] instanceof Humano)
                return true;
        }
        return false;
    }
    //Metodos para procurar instancias do tipo maquina.
    public boolean ExisteMaquina(){
        for(int i=0; i < numJog; i++){
            if(jogadores[i] instanceof Maquina)
                return true;
        }
        return false;
    }
    //Metodos para procurar instancias do tipo General.
    public boolean ExisteGeneral(){
        for(int i=0; i < numJog; i++){
            for(int j=0; j < jogadores[i].getContJogos(); j++){
                if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral)
                    return true;
            }
        }
        return false;
    }
    //Metodos para procurar instancias do tipo Azar.
    public boolean ExisteAzar(){
        for(int i=0; i < numJog; i++){
            for(int j=0; j < jogadores[i].getContJogos(); j++){
                if(jogadores[i].getJogoDados()[j] instanceof JogoAzar)
                    return true;
            }
        }
        return false;
    }
    //Metodo que imprime saldo dos jogadores.
    public void imprimirSaldo(){
        Scanner teclado = new Scanner(System.in);
        int opcao;
        //Menu de opcoes para tipo de jogador.
        System.out.println("\n========== Por favor informe como deseja imprimir os saldos ==========");
        System.out.println("\n1 - Imprimir saldo somente de jogadores Humanos");
        System.out.println("2 - Imprimir saldo somente de jogadores Maquinas");
        System.out.println("3 - Imprimir o saldo de ambos (Humanos e maquinas)");
        do {
            System.out.printf("\nDigite uma opcao: ");
            opcao = teclado.nextInt();
            if(opcao != 1 && opcao != 2 && opcao != 3)
                System.out.println("Por favor, informe uma opcao valida (1,2,3)!");
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        //Validacoes para jogadores.
        if(opcao == 1){
            if(ExisteHumano() == false)
                System.out.println("\nNão Existem jogadores do tipo humano!");
            else{
                System.out.println("\n===== Nome e saldo de jogadores Humanos =====\n");
                for(int i=0; i < numJog; i++){
                    //instanceof para tipo de jogador.
                    if(jogadores[i] instanceof Humano){
                        System.out.println("Nome:"+jogadores[i].GetNome()+"(H) Saldo: R$ "+jogadores[i].GetSaldo());
                    }
                }
            }
        }
        //Validacoes para jogadores.
        else if(opcao == 2){
            if(ExisteMaquina() == false)
                System.out.println("\nNão Existem jogadores do tipo maquina!");
            else{
                System.out.println("\n===== Nome e saldo de jogadores Maquinas =====\n");
                for(int i=0; i < numJog; i++){
                    //instanceof para tipo de jogador.
                    if(jogadores[i] instanceof Maquina){
                        System.out.println("Nome:"+jogadores[i].GetNome()+"(M) Saldo: R$ "+jogadores[i].GetSaldo());
                    }
                } 
            }
        }
        //Validacoes para jogadores.
        else{
            if(ExisteHumano() == false && ExisteMaquina() == false)
                System.out.println("\nNão Existem jogadores no campeonato!");
            else{
                System.out.println("\n===== Nome e saldo de todos os jogadores =====\n");
                for(int i=0; i < numJog; i++){
                    //instanceof para tipo de jogador.
                    if(jogadores[i] instanceof Humano)
                        System.out.println("Nome:"+jogadores[i].GetNome()+"(H) Saldo: R$ "+jogadores[i].GetSaldo());
                    else
                        System.out.println("Nome:"+jogadores[i].GetNome()+"(M) Saldo: R$ "+jogadores[i].GetSaldo());

                }   
            }
        }
    }
    //Metodo que imprime extratos.
    public void ImprimirExtratos(){
        Scanner teclado = new Scanner(System.in);
        int opcao;
        int opcaoExt;
        //Menu de opcoes para tipo de jogadores.
        do {
            System.out.println("1 - JOGADORES HUMANOS");
            System.out.println("2 - JOGADORES MAQUINAS");
            System.out.println("3 - AMBOS (HUMANOS E MAQUINAS)");
            System.out.printf("Escolha para qual tipo de jogador deseja imprimir os extratos: ");
            opcao = teclado.nextInt();

            if(opcao != 1 && opcao != 2 && opcao != 3)
                System.out.println("Por favor informe uma opcao valida");
                
        } while (opcao != 1 && opcao != 2 && opcao != 3);

        do {
            teclado.nextLine();
            //Menu de opcoes para tipo de jogos.
            System.out.println("1 - JOGO GENERAL");
            System.out.println("2 - JOGO AZAR");
            System.out.println("3 - AMBOS (AZAR E GENERAL)");
            System.out.printf("Escolha para qual tipo de jogo deseja imprimir os extratos ");
            opcaoExt = teclado.nextInt();
            
            if(opcao != 1 && opcao != 2 && opcao != 3)
                System.out.println("Por favor informe uma opcao valida");
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        if(opcao == 1){
            if(ExisteHumano() == false)
                System.out.println("\nNão Existem jogadores do tipo humano!");
            else{
                
                if(opcaoExt == 1){
                    if(ExisteGeneral() == false)
                        System.out.println("\nNão Existem Apostas no Jogo General!");
                    else{
                        System.out.println("\n======== EXTRATO DOS JOGOS GENERAIS APOSTADOS PELOS HUMANOS ========");
                        for(int i=0; i < numJog; i++){
                            //Uso do instanceof para tipo de jogadores.
                            if(jogadores[i] instanceof Humano){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipo de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                        JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Tipo do jogo: General");
                                        System.out.println("Jogadas:");
                                        general.MostraJogadas();
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(general.resultadoGeneral() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }               
                    }
                }else if(opcaoExt == 2){
                    if(ExisteAzar() == false)
                        System.out.println("\nNão Existem Apostas no Jogo Azar!");
                    else{
                        System.out.println("\n======== EXTRATO DOS JOGOS DE AZAR APOSTADOS PELOS HUMANOS ========");
                        for(int i=0; i < numJog; i++){
                            //Instanceof para jogadores.
                            if(jogadores[i] instanceof Humano){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipos de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoAzar){
                                        JogoAzar azar = (JogoAzar)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Tipo do jogo: Azar");
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(azar.resultadoAzar() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }               
                    }
                }
                else{
                    if(ExisteGeneral() == false && ExisteAzar() == false)
                        System.out.println("\nNão Existem Apostas!");
                    else{
                        System.out.println("\n======== EXTRATO DE TODOS OS JOGOS APOSTADOS PELOS HUMANOS ========");
                        for(int i=0; i < numJog; i++){
                            //Instanceof para tipo de jogadores.
                            if(jogadores[i] instanceof Humano){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                //for que controla os jogos.
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipo de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                        JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Tipo do jogo: Azar");
                                        System.out.println("Jogadas:");
                                        general.MostraJogadas();
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(general.resultadoGeneral() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                    else{
                                        //Informacoes do jogo azar.
                                        JogoAzar azar = (JogoAzar)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Tipo do jogo: Azar");
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(azar.resultadoAzar() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }               
                    }
                }
            }
        }
        else if(opcao == 2){
            if(ExisteMaquina() == false)
                System.out.println("\nNao existem jogadores do tipo maquina!");
            else{
                if(opcaoExt == 1){
                    //comparacao com metodo que procura instancias de general.
                    if(ExisteGeneral() == false)
                        System.out.println("\nNao existem Apostas no jogo General!");
                    else{
                        System.out.println("\n======== EXTRATO DOS JOGOS GENERAIS APOSTADOS PELAS MAQUINAS ========");
                        for(int i=0; i < numJog; i++){
                            //Instanceof para tipo de jogadores.
                            if(jogadores[i] instanceof Maquina){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                //for que controla os jogos.
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipo de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                        JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Jogadas:");
                                        general.MostraJogadas();
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(general.resultadoGeneral() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }
                    }
                }else if(opcaoExt == 2){
                    if(ExisteAzar() == false)
                        System.out.println("\nNao existem apostas no jogo azar");
                    else{
                        System.out.println("\n======== EXTRATO DOS JOGOS DE AZAR APOSTADOS PELAS MAQUINAS ========");
                        for(int i=0; i < numJog; i++){
                            //Instanceof para tipo de jogadores.
                            if(jogadores[i] instanceof Maquina){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                //for que controla os jogos.
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipo de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoAzar){
                                        //Informacoes do jogo azar.
                                        JogoAzar azar = (JogoAzar)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println();
                                        if(azar.resultadoAzar() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }                       
                    }
                }
                else if(opcaoExt == 3){
                    if(ExisteAzar() == false && ExisteGeneral() == false)
                        System.out.println("\nNao existem apostas!");
                    else{
                        System.out.println("\n======== EXTRATO DE TODOS OS JOGOS APOSTADOS PELAS MAQUINAS ========");
                        for(int i=0; i < numJog; i++){
                            //Instanceof para tipo de jogadores.
                            if(jogadores[i] instanceof Maquina){
                                System.out.println("Jogador(a): "+jogadores[i].GetNome());
                                //for que controla os jogos.
                                for(int j=0; j < jogadores[i].getContJogos(); j++){
                                    //Instanceof para tipo de jogos.
                                    if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                        JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println("Jogadas:");
                                        general.MostraJogadas();
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(general.resultadoGeneral() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                    else{
                                        //Informacoes do jogo azar.
                                        JogoAzar azar = (JogoAzar)jogadores[i].getJogoDados()[j];
                                        System.out.printf(" "+(j+1)+"° Aposta");
                                        System.out.println();
                                        System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                        if(azar.resultadoAzar() == true)
                                            System.out.println("resultado: Ganhou!\n");
                                        else
                                            System.out.println("resultado: Perdeu!\n");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            if(ExisteHumano() == false && ExisteMaquina() == false)
                System.out.println("\nNao existem jogadores!");
            else{
                if(opcaoExt == 1){
                    if(ExisteGeneral() == false)
                        System.out.println("\nNao existem apostas no jogo ganeral!");
                    else{
                        System.out.println("\n======== EXTRATO DE TODOS OS JOGOS GENERAIS APOSTADOS POR (HUMANOS/MAQUINAS) ========");
                        for(int i=0; i < numJog; i++){
                            System.out.println("Jogador(a): "+jogadores[i].GetNome());
                            //for que controla os jogos.
                            for(int j=0; j < jogadores[i].getContJogos(); j++){
                                //Instanceof para tipo de jogos.
                                 if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                    JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                    System.out.printf(" "+(j+1)+"° Aposta");
                                    System.out.println("Jogadas:");
                                    general.MostraJogadas();
                                    System.out.println();
                                    System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                    if(general.resultadoGeneral() == true)
                                        System.out.println("resultado: Ganhou!\n");
                                    else
                                        System.out.println("resultado: Perdeu!\n");
                                }
                            }
                        }
                    }
                }else if(opcaoExt == 2){
                    if(ExisteAzar() == false)
                        System.out.println("\nNao existem apostas em jogo azar");
                    else{
                        System.out.println("\n======== EXTRATO DE TODOS OS JOGOS DE AZAR APOSTADOS POR (HUMANOS/MAQUINAS) ========");
                        for(int i=0; i < numJog; i++){
                            System.out.printf("Jogador(a): "+jogadores[i].GetNome());
                            //for que controla os jogos.
                            for(int j=0; j < jogadores[i].getContJogos(); j++){
                                //Instanceof para tipo de jogos.
                                 if(jogadores[i].getJogoDados()[j] instanceof JogoAzar){
                                    JogoGeneral azar = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                    System.out.printf(" "+(j+1)+"° Aposta");
                                    System.out.println();
                                    System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                    if(azar.resultadoGeneral() == true)
                                        System.out.println("resultado: Ganhou!\n");
                                    else
                                        System.out.println("resultado: Perdeu!\n");
                                }
                            }
                        }
                    }
                }
                else{
                    if(ExisteAzar() == false && ExisteGeneral() == false)
                        System.out.println("\nNao existem apostas em jogos de dados!");
                    else{
                        System.out.println("\n======== EXTRATO DE TODOS OS JOGOS (AZAR/GENERAL) APOSTADOS POR (HUMANOS/MAQUINAS) ========");
                        for(int i=0; i < numJog; i++){
                            System.out.println("Jogador(a): "+jogadores[i].GetNome());
                            //for que controla os jogos.
                            for(int j=0; j < jogadores[i].getContJogos(); j++){
                                //Instanceof para tipo de jogos.
                                if(jogadores[i].getJogoDados()[j] instanceof JogoGeneral){
                                    JogoGeneral general = (JogoGeneral)jogadores[i].getJogoDados()[j];
                                    System.out.printf(" "+(j+1)+"° Aposta ");
                                    System.out.println("Jogadas:");
                                    general.MostraJogadas();
                                    System.out.println();
                                    System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                    if(general.resultadoGeneral() == true)
                                        System.out.println("resultado: Ganhou!\n");
                                    else
                                        System.out.println("resultado: Perdeu!\n");
                                }
                                else{
                                    //Informacoes do jogo azar.
                                    JogoAzar azar = (JogoAzar)jogadores[i].getJogoDados()[j];
                                    System.out.printf(" "+(j+1)+"° Aposta ");
                                    System.out.println();
                                    System.out.println("Valor da aposta: "+jogadores[i].getJogoDados()[j].getAposta());
                                    if(azar.resultadoAzar() == true)
                                        System.out.println("resultado: Ganhou!\n\n");
                                    else
                                        System.out.println("resultado: Perdeu!\n\n");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void imprimirEstatisticas(){
        Scanner teclado = new Scanner(System.in);
        int opcao;
        int opcaoExt;
        //menu tipos de jogadores.
        do {
            System.out.println("1 - JOGADORES HUMANOS");
            System.out.println("2 - JOGADORES MAQUINAS");
            System.out.println("3 - AMBOS (HUMANOS E MAQUINAS)");
            System.out.printf("Escolha para qual tipo de jogador deseja imprimir as Estatisticas: ");
            opcao = teclado.nextInt();

            if(opcao != 1 && opcao != 2 && opcao != 3)
                System.out.println("Por favor informe uma opcao valida");
                
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        //menu tipos de jogos.
        do {
            teclado.nextLine();
            System.out.println("1 - JOGO GENERAL");
            System.out.println("2 - JOGO AZAR");
            System.out.println("3 - AMBOS (AZAR E GENERAL)");
            System.out.printf("Escolha para qual tipo de jogo deseja imprimir as estatisticas: ");
            opcaoExt = teclado.nextInt();
            
            if(opcao != 1 && opcao != 2 && opcao != 3)
                System.out.println("Por favor informe uma opcao valida");
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        //printa o vetor de estatisticas.
        for(int i=0; i < numJog; i++){
            for(int j=0; j < jogadores[i].getContJogos(); j++){
                jogadores[i].getJogoDados()[j].printFacesSorteadas();
            }
        }
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
