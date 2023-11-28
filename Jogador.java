import java.io.Serializable;

public abstract class Jogador implements Serializable{
    //Atributos do jogador.
    private String nome;
    private char Tipo_jog;
    private float saldo;
    private JogoDados[] jogoD;

    //metodo construtor com parametros.
    public Jogador(String nome, char Tipo){
        jogoD  = new JogoDados[10];
        this.nome = nome;
        Tipo_jog = Tipo;
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
    //Metodo acessador de jogo
    public JogoDados[] getJogoDados(){
        return this.jogoD;
    }
    //Metodo que chama o setJogadas.
    public void zeraPont(){
        for(int i=0; i < jogoD.length; i++){
            if(jogoD[i] instanceof JogoGeneral){
                JogoGeneral varAux = (JogoGeneral) jogoD[i];
                varAux.setJogadas();
            }
        }
    }
    //Metodo que invoca o metodo para rolar os dados.
    public void jogarDados(int i){
        this.jogoD[i].RolarDados();
    }
    public String toString(){
        return this.nome + this.Tipo_jog;
    }

}
