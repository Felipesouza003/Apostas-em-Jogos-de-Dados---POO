import java.io.Serializable;

public abstract class Jogador implements Serializable{
    //Atributos do jogador.
    private String nome;
    private char Tipo_jog;
    private float saldo;
    private JogoDados[] jogoD;
    private int contJogos;

    //metodo construtor com parametros.
    public Jogador(String nome, char Tipo){
        jogoD  = new JogoDados[10];
        this.nome = nome;
        Tipo_jog = Tipo;
        contJogos = 0;
        this.saldo = (float)100;
    }
    //Metodo acessador do nome do jogador.
    public String GetNome(){
        return this.nome;
    }
    //Metodo acessador de tipo do jogador.
    public char GetTipo(){
        return Tipo_jog;
    }
    public float GetSaldo(){
        return this.saldo;
    }
    public void SetSaldo(float saldo){
        this.saldo = saldo;
    }
    public int getContJogos(){
        return this.contJogos;
    }
    public void SetContJogos(int cont){
        this.contJogos = cont;
    }
    //Metodo acessador de jogo
    public JogoDados[] getJogoDados(){
        return this.jogoD;
    }
    
    //Metodo que invoca o metodo para rolar os dados.
    public void jogarDados(int i){
        this.jogoD[i].RolarDados();
    }
    public String toString(){
        return this.nome + this.Tipo_jog;
    }
    //criar metodo incluir jogo.
    public void CriarJogo(int escolha, int i){
        if(escolha == 1){
            this.jogoD[i] = new JogoGeneral(5, "Jogo General");
        }
        else if(escolha == 2)
            this.jogoD[i] = new JogoAzar();

    }
}
