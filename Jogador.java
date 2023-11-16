import java.io.Serializable;

public abstract class Jogador implements Serializable{
    //Atributos do jogador.
    private String nome;
    private char Tipo_jog;
    private JogoDados[] jogoD  = new JogoDados[10];

    //metodo construtor com parametros.
    public Jogador(String nome, char Tipo){
        this.nome = nome;
        Tipo_jog = Tipo;
    }
    //Metodo acessador do nome do jogador.
    public String GetNome(){
        return this.nome;
    }
    //Metodo acessador de tipo do jogador.
    public char GetTipo(){
        return Tipo_jog;
    }
    //Metodo acessador de jogo
    public JogoDados[] getJogo(){
        return this.jogoD;
    }

}
