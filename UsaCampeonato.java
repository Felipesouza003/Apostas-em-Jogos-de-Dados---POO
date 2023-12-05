import java.util.Scanner;
public class UsaCampeonato{
    public static void main(String[] args){
        Campeonato campion = new Campeonato();//Instancia da Classe Campeonato.
        Scanner teclado = new Scanner (System.in);
        char opcao;//Variavel para leitura da opcao do menu.
        do{
            System.out.println();
            System.out.println("\n--------- CAMPEONATO DE APOSTAS EM JOGOS DE DADOS ---------");
            System.out.println("a)Incluir um novo jogador.");
            System.out.println("b)Remover um jogador.");
            System.out.println("c)Executar rodada.");
            System.out.println("d)Imprimir saldo dos jogadores.");
            System.out.println("e)Imprimir extrato dos jogadores.");
            System.out.println("f)Imprimir estatisticas do campeonato.");
            System.out.println("g)Gravar os dados do campeonato em arquivo.");
            System.out.println("h)Ler os dados do campeonato em arquivo.");
            System.out.println("s)Sair da aplicação.");
            System.out.printf("\nEntre com a opção do menu: ");
            opcao = teclado.nextLine().charAt(0);

            switch(opcao){
                case 'a'://Incluir o jogador caso a opcao seja a.
                    campion.incluirJogador();
                    break;
                case 'A'://Incluir o jogador caso a opcao seja A.
                    campion.incluirJogador();
                    break;
                case 'b'://Exclui o jogador caso a opcao seja b.
                    campion.exluirJogador();
                    break;
                case 'B'://Exclui o jogador caso a opcao seja B.
                    campion.exluirJogador();
                    break;
                case 'c'://Inicia o campeontado caso a opcao seja c.
                    campion.iniciaCampeonato();
                    break;
                case 'C'://Inicia o campeontado caso a opcao seja C.
                    campion.iniciaCampeonato();
                    break;
                case 'd'://Mostra a tabela da ultima rodada caso a opcao seja d.
                    campion.imprimirSaldo();;
                    break;
                case 'D'://Mostra a tabela da ultima rodada caso a opcao seja D.
                    campion.imprimirSaldo();;
                    break;
                case 'e'://Imprime extratos do campeonato.
                    campion.ImprimirExtratos();
                    break;
                case 'E'://Imprime extratos do campeonato.
                    campion.ImprimirExtratos();
                    break;
                case 'f'://Imprime as estatisticas
                    campion.imprimirEstatisticas();
                    break;
                case 'F'://Imprime as estatisticas
                    campion.imprimirEstatisticas();
                    break;
                case 'g'://Grava os dados da ultima rodada em arquivo caso a opcao seja g.
                    campion.gravarArq();
                    break;
                case 'G'://Grava os dados da ultima rodada em arquivo caso a opcao seja G.
                    campion.gravarArq();
                    break;
                case 'h'://Le os dados gravados em arquivo caso a opcao seja h.
                    campion.LerArq();
                    break;
                case 'H'://Le os dados gravados em arquivo caso a opcao seja H.
                    campion.LerArq();
                    break;
                case 's'://Encerra o programa caso a opcao seja s.
                    System.out.println ("Saindo");
                    break;
                case 'S'://Encerra o programa caso a opcao seja S.
                    System.out.println ("Saindo");
                    break;
                default:
                    break;
            }

        }while(opcao != 's' && opcao != 'S');//Repete ate a opcao ser G ou g.
        teclado.close();
    }
}
