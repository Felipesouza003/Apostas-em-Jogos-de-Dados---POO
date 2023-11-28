/*public class JogoAzar extends JogoDados{
    // Atributos
    private float valorAposta;
    private int jogBus;
    private boolean vitoria;

    // Metodo construtor
    public JogoAzar(float valorAposta) {
        super(2, "JogoAzar");
        this.jogBus = -1;
        this.vitoria = false;
        this.valorAposta = valorAposta;
    }

    @Override
    public void somaFaceSort(int n) {
        faceSort[n -1]++;      
    }

    @Override
    public int[] getFacesSorteadasVet() {
        return faceSort;
    }

    @Override
    public void printFacesSorteadas() {
        System.out.println("Quantidade que cada face foi sorteada: ");
        for(int i = 0; i < numfaces; i++){
            System.out.println("Face " + (i+1) + ": " + faceSort[i] + " vezes.");
        }
    }

    public boolean jogadorVenceu() {
        return vitoria;
    }

    public void operaAposta(){
        float nSaldo;
        if(jogadorVenceu()){
            nSaldo = getSaldo() + (2*valorAposta);
        }
        else{
            nSaldo = getSaldo() - valorAposta;
        }
        setSaldo(nSaldo);
    }

    // Metodo principal para funcionamento do JogoAzar
    public void execJog() {
       
        int somaDados;
        int i = 1;
        int a, b;

        do{
            //Dois dados sao rolados em um loop, que tambem possui uma varivavel para contar em qual iteracao do lancamento esta
            getDados()[0].roll();
            getDados()[1].roll();
            System.out.println(i + " lancamento:");
            //Caso sejam atingidas as condicoes iniciais para vitoria ou derrota, o loop se encerra no primeiro lancamento de dados, registrando tambem
            //se o jogador venceu ou perdeu em uma variavel boolean
            //Caso contrario, entra no loop de buscar a soma desejada ou ate encontrar a condicao de derrota
            //Ao mesmo tempo, sao contadas quantas vezes cada face eh sorteada
            a = getDados()[0].getSideUp();
            b = getDados()[1].getSideUp();
            somaFaceSort(a);
            somaFaceSort(b);
            somaDados = getDados()[0].getSideUp() + getDados()[1].getSideUp();
            System.out.println(getDados()[0].getSideUp() + " e " + getDados()[1].getSideUp() + " = " + somaDados);

            if  (jogBus == -1) {
                if (somaDados == 7 || somaDados == 11) {
                    vitoria = true;
                    System.out.println("Jogador venceu!");
                    break;
                } else if (somaDados == 2 || somaDados == 3 || somaDados == 12) {
                    vitoria = false;
                    System.out.println("Jogador perdeu!");
                    break;
                } else {
                 jogBus = somaDados;
                 System.out.println("Numero a ser buscado: " + jogBus);
                }
            }
            else {
                    if (somaDados == jogBus) {
                    vitoria = true;
                        System.out.println("Jogador venceu!");
                        break;
                    }
                    else if(somaDados == 2 || somaDados == 3 || somaDados == 12){
                        System.out.println("Jogador perdeu!");
                        break;
                    }
            }
            i++;

        }while(jogBus != 0);

        operaAposta();
    }
}*/
