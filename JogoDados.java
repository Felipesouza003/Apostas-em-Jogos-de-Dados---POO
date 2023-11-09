import java.io.Serializable;

public abstract class JogoDados implements Serializable, Estatistica{
    private int nDados;
    private String nomeJogo;
    private Dado[] Dados = new Dado[nDados];
    private float saldo;

    public JogoDados(int nDados, String nome, Dado[] dados){
        this.nDados = nDados;
        this.nomeJogo = nome;
        this.Dados = dados;
        // Dados do jogador da rodada
        for (int i = 0; i < nDados; i++)
            Dados[i] = new Dado();
    }
    public void RolarDados() {
        System.out.println("\nFaces dos dados rolados:");
        for (int i = 0; i < nDados; i++) {
            Dados[i].roll();
            System.out.printf(Dados[i]+"   ");
        }
        System.out.println();
    }
    public String getNomeJogo(){
        return this.nomeJogo;
    }
    public float getSaldo(){
        return this.saldo;
    }
    public Dado[] getDados(){
        return this.Dados;
    }
    public int getNDados(){
        return this.nDados;
    }
}
