import java.util.Scanner;
public class UsaCampeonato{
    public static void main(String[] args){
        Campeonato campion = new Campeonato();//Instancia da Classe Campeonato.
        Scanner teclado = new Scanner (System.in);
        char opcao;//Variavel para leitura da opcao do menu.
        do{
            System.out.println();
            System.out.println("\n--------- JOGO GENERAL ---------");
            System.out.println("a)Incluir um novo jogador.");
            System.out.println("b)Remover um jogador.");
            System.out.println("c)Executar rodada.");
            System.out.println("d)Mostrar cartela de resultados da última rodada");
            System.out.println("e)Gravar os dados do campeonato em arquivo.");
            System.out.println("f)Ler os dados do campeonato em arquivo.");
            System.out.println("g)Sair da aplicação.");
            System.out.printf("\nEntre com a opção do menu: ");
            opcao=teclado.next().charAt(0);

            switch(opcao){
                case 'a'://Incluir o jogador caso a opcao seja a.
                    campion.incluirJogador();
                    break;
                case 'b'://Exclui o jogador caso a opcao seja b.
                    campion.exluirJogador();
                    break;
                case 'c'://Inicia o campeontado caso a opcao seja c.
                    campion.iniciaCampeonato();
                    break;
                case 'd'://Mostra a tabela da ultima rodada caso a opcao seja d.
                    campion.mostraTabelaRodada();
                    break;
                case 'e'://Grava os dados da ultima rodada em arquivo caso a opcao seja e.
                    campion.gravarArq();
                    break;
                case 'f'://Le os dados gravados em arquivo caso a opcao seja f.
                    campion.LerArq();
                    break;
                case 'g'://Encerra o programa caso a opcao seja g.
                    System.out.println ("Saindo");
                    break;
                default:
                    break;
            }

        }while(opcao != 'g' && opcao != 'G');//Repete ate a opcao ser G ou g.
        teclado.close();
    }
}
