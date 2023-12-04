import java.io.Serializable;

public abstract class JogoDados implements Serializable, Estatistica{
    private int nDados;
    private String nomeJogo;
    private Dado[] Dados;
    private float Aposta;
    private int[] faceSort;
    //Metodo construtor.
    public JogoDados(int nDados, String nome){
        this.nDados = nDados;
        this.nomeJogo = nome;
        this.Dados = new Dado[nDados];
        this.faceSort = new int[6];
        // Dados do jogador da rodada
        for (int i = 0; i < nDados; i++)
            Dados[i] = new Dado();
    }
    //Metodo que rola os dados.
    public void RolarDados() {
        System.out.println("\nFaces dos dados rolados:");
        for (int i = 0; i < nDados; i++) {
            Dados[i].roll();
            //atualiza o vetor de estatisticas.
            somaFaceSort(Dados[i].getSideUp());
            System.out.printf(Dados[i]+"   ");
        }
        System.out.println();
    }
    //incrementacao do vetor de faces.
    @Override
    public void somaFaceSort(int n) {
        this.faceSort[n - 1]++;      
    }
    //Retorna o vetor de faces sorteadas.
    @Override
    public int[] getFacesSorteadasVet() {
        return this.faceSort;
    }
    //Printa os o vetor de faces sorteadas.
    @Override
    public void printFacesSorteadas() {
        System.out.println("Quantidade que cada face foi sorteada: ");
        for(int i = 0; i < numfaces; i++){
            System.out.println("Face " + (i+1) + ": " + faceSort[i] + " vezes.");
        }
    }
    //Geters e seters
    public String getNomeJogo(){
        return this.nomeJogo;
    }
    public void setAposta(float Aposta){
        this.Aposta = Aposta;
    }
    public float getAposta(){
        return this.Aposta;
    }
    public Dado[] getDados(){
        return this.Dados;
    }
    public int getNDados(){
        return this.nDados;
    }
}
