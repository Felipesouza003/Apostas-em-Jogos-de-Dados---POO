public class JogoGeneral extends JogoDados{
    private int[] jogadas = new int[13];
    
    // Construtor padrao
    public JogoGeneral(int nDados, String nome) {
        super(nDados, nome);
        // Inicializando jogadas
        for (int i = 0; i < 13; i++) {
            jogadas[i] = -1;
        }
    }
    //Metodo seter que inicializa o vetor de jogadas.
    public void setJogadas(){
        for (int i = 0; i < 13; i++) {
            jogadas[i] = -1;
        }
    }

    // Metodo acessador de jogadas]
    public int[] Getjogadas() {
        return this.jogadas;
    }
    //Metodo que faz a soma das faces dos dados rolados e retorna.
    public int SomaDados() {
        int soma = 0;
        for (int i = 0; i < 5; i++) {
            soma += getDados()[i].getSideUp();
        }
        return soma;
    }
    int pontuacao;//variavel auxiliar para guardar a pontuacao caso a jogada seja validada
    public boolean validaJogadas(int jogada){
        // vetor que conta as aparicoes das faces
		int[] vet_cont = {0,0,0,0,0,0}; 
		// vetor vet_con[] conta o numero de aparicoes das faces e coloca no indice respectivo
		for(int i = 0 ; i < 5 ; i++) { 
			vet_cont[(getDados()[i].getSideUp()) - 1] += 1;
		}
        //Verificacao se a jogada ja foi utilizada
        if(jogadas[jogada - 1] != -1){
            System.out.println("\nEssa jogada ja foi utilizada!");
            return false;
        }//Caso a jogada escolhida seja entre 6 e 1 faz a multiplicacao da jogada pelo numero de vezes que a face virou.
        else if(jogada >= 1 && jogada <= 6){ 
            if(vet_cont[jogada - 1] >=1){//Caso pelo menos uma aparicao aconteca atribui a pontuacao.
                pontuacao =  vet_cont[jogada-1] * jogada;
                return true;
            }
            else
                return false;
        }
        else if(jogada > 6)
        {
            if(jogada == 7){//Para a jogada de trinca a pontuacao reecebe a soma dos dados.
                int cont=0;//Contador que verifica se houve tres aparicoes da face.
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] == 3)
                        cont++;
                }//Caso a variavel nao seja incrementada nao houve tres aparicoes entao retorna false.
                if(cont == 0)
                    return false;
                else{//Caso haja tres repeticoes a pontuacao repete a soma de dados.
                    pontuacao = SomaDados();
                    return true;
                }
            }
            else if(jogada == 8){//Para a jogada de quadra a pontuacao reecebe a soma dos dados.
                int cont=0;
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] == 4)
                        cont++;
                }
                if(cont !=0){//Caso seja diferente de 0 houve 4 repeticoes entao a pontuacao recebe a soma dos dados.
                    pontuacao = SomaDados();
                    return true;
                }
                else
                    return false;
            }
            else if(jogada == 9){
                //Para ser uma jogada full-house o vetor de repeticoes deve conter duas posicoes
                //Uma contendo o numero de tres repeticoes e uma contendo duas repeticoes.
                int trinca=0, par=0;//variaveis contadoras para o par e a trinca.
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] == 3)
                        trinca++;
                    else if(vet_cont[i] == 2)
                        par++;
                }
                if(par != 0 && trinca != 0){//Caso sejam diferentes de 0 entao ouve um par e uma trinca.
                    pontuacao = 25;
                    return true;
                }
                else
                    return false;
            }
            else if(jogada == 10){//Se nao ha repeticoes no vetor e a face 1 nao virou temos uma sequencia alta.
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] > 1)//Verifica se nao ha repeticoes.
                        return false;
                }
                if(vet_cont[0] == 0){//Verifica a posicao em que sao contadas as aparicoes do 1.
                    pontuacao = 30;
                    return true;
                }
            }
            else if(jogada == 11){//Se nao ha repeticoes no vetor e a face 6 nao virou temos uma sequencia alta.
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] > 1)//Verifica se nao ha repeticoes.
                        return false;
                }
                if(vet_cont[5] == 0){//Verifica a posicao em que sao contadas as aparicoes do 6.
                    pontuacao = 40;
                    return true;
                }
            }
            //General
            else if(jogada == 12){
                for(int i=0; i < 6; i++){
                    if(vet_cont[i] == 5){//Verifica no vetor de aparicoes se a 5 repeticoes de um numero.
                        pontuacao = 50;
                        return true;
                    }
                }
                return false;
            }
            else if(jogada == 13){
                pontuacao = SomaDados();//Jogada aleatoria recebe a soma dos dados.
                return true;
            }
        }
        return false;
    }
    //Metodo que mostra o vetor de jogadas.(Mexer)
    /*public void MostraJogadas(){
        int[] vet = this.jogoD[i].Getjogadas();
        for(int i=0; i < vet.length; i++){
            if(vet[i] == -1)
                System.out.printf("-\t");
            else
                System.out.printf(vet[i]+"\t");
        }
    }*/
     //Metodo que soma o valor de todas as posicoes do vetor jogadas de cada jogador.(Mexer)
     /*public int SomaTot(){
        int[] vetJogadas = jogoD.Getjogadas();//Invocacao do metodo acessador de jogadas.
        int soma=0;
        for(int i=0; i < vetJogadas.length; i++){
            if(vetJogadas[i] != -1)
                soma += vetJogadas[i];//soma os valores das posicoes do vetor de jogadas.
        }
        return soma;
    }*/
    //metodo que pontua as jogadas separadamente.
    public void pontuarJogada(int jogada) {
        if(validaJogadas(jogada)==true)//Valida a jogada antes de pontuar.
            jogadas[jogada-1] = pontuacao;
        else{
            if(jogadas[jogada-1] == -1){//Caso a jogada nao cumpra os requisitos para a opcao a a pontuacao é zerada.
                System.out.println("Sua pontuação não cumpre os requisitos para essa jogada!");
                jogadas[jogada - 1] = 0;
            }   
        }
    }
}
