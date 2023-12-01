import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano{
    private String Cpf;
    private String conta;
    private String numeroBanco;


    public Humano(String nome, char Tipo, String Cpf, String conta, String numeroBanco){
        super(nome, Tipo);
        this.Cpf = Cpf;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
    }
    @Override
    public int escolherJogo(){
        int jogoEscolhido;
        Scanner teclado = new Scanner (System.in);
        do{
            System.out.printf("\nInforme o tipo de jogo em que deseja apostar: (1-Jogo general ou 2-Jogo azar: ");
            jogoEscolhido = teclado.nextInt();

            if(jogoEscolhido != 1 && jogoEscolhido != 2)
                System.out.println("Por favor ecolha entre 1 e 2!");
        } while (jogoEscolhido != 1 && jogoEscolhido != 2);
        return jogoEscolhido;
    }
    public float escolherValorAposta(){
        float valorAposta;
        Scanner teclado = new Scanner (System.in);
        do {
            System.out.printf("\nPor favor informe o valor que deseja apostar $: ");
            valorAposta = teclado.nextFloat();

            if(valorAposta > super.GetSaldo() || valorAposta < 0){
                System.out.println("\nPor favor informe um valor valido!");
            }
        } while (valorAposta > GetSaldo() || valorAposta < 0);
        return valorAposta;
    }
    @Override
    public int EscolherJogada(){
        int jogada=0;
        Scanner teclado = new Scanner (System.in);
        do {
                System.out.println("\nOpcões de jogadas:");
                System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                System.out.println("Para qual jogada vc quer marcar? "); 
                jogada = teclado.nextInt();
                if(jogada < 1 || jogada > 13){
                    System.out.println("Por favor informe um valor entre 1 e 13!");
                }
        } while (jogada < 1 || jogada > 13);
        System.out.println("\nJogada escolhida: "+jogada);
        return jogada;
    }
    public String getCpfHumano(){
        return this.Cpf;
    }
    public String getConta(){
        return this.conta;
    }
    public String getNumeroBanco(){
        return this.numeroBanco;
    }
}
