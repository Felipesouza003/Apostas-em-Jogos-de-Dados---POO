import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano{
    private String Cpf;
    private String conta;
    private int numeroBanco;
    private Scanner teclado = new Scanner (System.in);//Variavel para escanear as escolhas dos jogadores.


    public Humano(String nome, char Tipo, String Cpf, String conta, int numeroBanco){
        super(nome, Tipo);
        this.Cpf = Cpf;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
    }
    @Override
    public int escolherJogo(){
        int jogoEscolhido;
        do {
            System.out.printf("Informe o tipo de jogo em que deseja apostar: (1-Jogo general ou 2-Jogo azar: ");
            jogoEscolhido = teclado.nextInt();
            if(jogoEscolhido != 1 && jogoEscolhido != 2)
                System.out.println("Por favor ecolha entre 1 e 2!");
        } while (jogoEscolhido != 1 && jogoEscolhido != 2);
        return jogoEscolhido;
    }
    @Override
    public int EscolherJogada(int i){
        int jogada=0;
        System.out.println("\nRolando dados para "+GetNome()+"("+GetTipo()+")...");
            getJogoDados()[i].RolarDados();//invocando o metodo de jogar dados.
        do {
            System.out.println("\nOpcões de jogadas:");
                System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                System.out.println("Para qual jogada vc quer marcar? "); 
                jogada = teclado.nextInt();
                if(jogada < 1 || jogada > 13)
                    System.out.println("Por favor informe um valor entre 1 e 13!");
                teclado.nextLine();
        } while (jogada < 1 && jogada > 13);
        System.out.println("\nJogada escolhida: "+jogada);
        return jogada;
    }
    public String getCpfHumano(){
        return this.Cpf;
    }
    public String getConta(){
        return this.conta;
    }
    public int getNumeroBanco(){
        return this.numeroBanco;
    }
}
