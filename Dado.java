import java.io.Serializable;
import java.util.Random;
public class Dado implements Serializable{
    private int SideUp;
    //Metodo construtor 
    public Dado(){
        roll();
    }
    //Metodo get que retorna a face superior do dado.
    public int getSideUp(){
        return this.SideUp;
    }//Metodo toString que retorna a face superior do dado como uma String.
    public String toString(){
        return "" +SideUp;
    }//Metodo que rola os dados.
    public void roll(){
        Random x = new Random();
        SideUp = x.nextInt(6) + 1;
    }
}
