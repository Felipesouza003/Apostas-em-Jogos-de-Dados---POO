public class Humano extends Jogador{
    private String Cpf;
    private String conta;
    private int numeroBanco;

    public Humano(String nome, char Tipo, String Cpf, String conta, int numeroBanco){
        super(nome, Tipo);
        this.Cpf = Cpf;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
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
