import java.util.Scanner;

public class Humano extends Jogador implements JogarComoHumano{
    private String Cpf;
    private String conta;
    private String numeroBanco;
    private String agencia;

    //Contrutor padrao.
    public Humano(String nome, char Tipo, String Cpf, String conta, String numeroBanco, String agencia){
        super(nome, Tipo);
        this.Cpf = Cpf;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
        this.agencia = agencia;
    }
    //Sobreescrita de metodos.
    @Override
    public int escolherJogo(){
        int jogoEscolhido;
        Scanner teclado = new Scanner (System.in);
        do{
            System.out.printf("\nInforme o tipo de jogo em que deseja apostar: (1-Jogo general ou 2-Jogo azar): ");
            jogoEscolhido = teclado.nextInt();

            if(jogoEscolhido != 1 && jogoEscolhido != 2)
                System.out.println("\nPor favor ecolha entre 1 e 2!");
        } while (jogoEscolhido != 1 && jogoEscolhido != 2);
        return jogoEscolhido;
    }
    //Metodo de escolher aposta.
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
    //Metodo de escolher jogada jogo General.
    @Override
    public void EscolherJogada(JogoGeneral jogoG){
        int jogada=0;
        Scanner teclado = new Scanner (System.in);

        for(int i=0; i < 13; i++){
            System.out.println("\n\nRolando dados para "+GetNome()+"("+GetTipo()+")...");
            jogoG.RolarDados();
            do {
                    System.out.println("\nOpcões de jogadas:");
                    System.out.println("1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)");
                    System.out.println("Para qual jogada vc quer marcar? "); 
                    jogada = teclado.nextInt();
                    if(jogada < 1 || jogada > 13){
                        System.out.println("Por favor informe um valor entre 1 e 13!");
                    }
                    if(jogoG.validaJogadas(jogada) == false && (jogoG.Getjogadas()[jogada-1] != -1))
                        System.out.println("\nEssa jogada ja foi utilizada informe outra!");
            } while (jogada < 1 || jogada > 13 || jogoG.validaJogadas(jogada) == false && (jogoG.Getjogadas()[jogada-1] != -1));
            
            System.out.println("\nJogada escolhida: "+jogada);
            jogoG.pontuarJogada(jogada);
            jogoG.MostraJogadas();
        }
    }
    //Geters
    public String getCpfHumano(){
        return this.Cpf;
    }
    public String getConta(){
        return this.conta;
    }
    public String getNumeroBanco(){
        return this.numeroBanco;
    }
    public String getAgencia(){
        return this.agencia;
    }
}
