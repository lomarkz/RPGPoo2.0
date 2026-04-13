package AtividadeRPG;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] mapa = {
                {0, 1, 0, 2},
                {3, 4, 1, 0},
                {0, 0, 3, 4},
                {2, 1, 0, 5}
        };

        PerguntaJava[] perguntas = new PerguntaJava[2];
        perguntas[0] = new PerguntaJava(
                "Qual palavra e usada para criar uma classe que nao pode ser instanciada?",
                new String[]{"interface", "abstract", "extends", "static"},
                2
        );
        perguntas[1] = new PerguntaJava(
                "Qual metodo e usado para mostrar texto no terminal em Java?",
                new String[]{"printLine()", "System.out.println()", "console.write()", "show()"},
                2
        );

        System.out.println("Bem-vindo ao Templo do Codigo.");
        System.out.print("Digite o nome do seu personagem: ");
        String nomeJogador = scanner.nextLine();

        Jogador jogador = new Jogador(nomeJogador, 100, 12, 4, 0, 1, 0, 0);

        System.out.println();
        System.out.println("Voce entrou em uma ruina antiga.");
        System.out.println("Para chegar ao tesouro final, sera preciso explorar salas e abrir portas magicas com conhecimentos de Java.");
        System.out.println();

        boolean jogoAtivo = true;
        int indicePergunta = 0;

        while (jogoAtivo && jogador.estaVivo()) {
            System.out.println("Escolha uma acao:");
            System.out.println("1 - Norte");
            System.out.println("2 - Sul");
            System.out.println("3 - Leste");
            System.out.println("4 - Oeste");
            System.out.println("5 - Ver status");
            System.out.print("Opcao: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("5")) {
                jogador.exibirStatus();
                continue;
            }

            int linhaAtual = jogador.getLinha();
            int colunaAtual = jogador.getColuna();
            int novaLinha = linhaAtual;
            int novaColuna = colunaAtual;

            if (opcao.equals("1")) {
                novaLinha--;
            } else if (opcao.equals("2")) {
                novaLinha++;
            } else if (opcao.equals("3")) {
                novaColuna++;
            } else if (opcao.equals("4")) {
                novaColuna--;
            } else {
                System.out.println("Opcao invalida. Digite um numero de 1 a 5.");
                System.out.println();
                continue;
            }

            if (novaLinha < 0 || novaLinha >= mapa.length || novaColuna < 0 || novaColuna >= mapa[0].length) {
                System.out.println("Voce nao pode sair do mapa.");
                System.out.println();
                continue;
            }

            int evento = mapa[novaLinha][novaColuna];

            jogador.setLinha(novaLinha);
            jogador.setColuna(novaColuna);

            if (evento == 0) {
                System.out.println("Sala vazia. Voce pode continuar explorando.");
                System.out.println();
            } else if (evento == 1) {
                Inimigo inimigo = criarInimigo(novaLinha, novaColuna);
                inimigo.exibirDesenho();
                System.out.println("Um inimigo apareceu: " + inimigo.getNome());
                System.out.println();

                while (inimigo.estaVivo() && jogador.estaVivo()) {
                    System.out.println("1 - Atacar");
                    System.out.println("2 - Usar pocao");
                    System.out.print("Escolha sua acao no combate: ");
                    String escolhaCombate = scanner.nextLine();

                    if (escolhaCombate.equals("1")) {
                        jogador.atacar(inimigo);
                    } else if (escolhaCombate.equals("2")) {
                        jogador.usarPocao();
                    } else {
                        System.out.println("Acao invalida.");
                        continue;
                    }

                    if (inimigo.estaVivo()) {
                        inimigo.atacar(jogador);
                    }

                    System.out.println("Vida do jogador: " + jogador.getVida());
                    System.out.println("Vida do inimigo: " + inimigo.getVida());
                    System.out.println();
                }

                if (jogador.estaVivo()) {
                    int xpGanho = 50;

                    if (inimigo instanceof EsqueletoGuerreiro) {
                        xpGanho = 70;
                    } else if (inimigo instanceof OrcBrutal) {
                        xpGanho = 100;
                    } else if (inimigo instanceof Goblin) {
                        xpGanho = 40;
                    }

                    jogador.ganharXp(xpGanho);
                    System.out.println("Voce ganhou " + xpGanho + " de XP!");

                    int ouroGanho = 15;

                    if (inimigo instanceof EsqueletoGuerreiro) {
                        ouroGanho = 20;
                    } else if (inimigo instanceof OrcBrutal) {
                        ouroGanho = 30;
                    }

                    jogador.setOuro(jogador.getOuro() + ouroGanho);
                    System.out.println("Voce venceu o inimigo e ganhou " + ouroGanho + " moedas de ouro.");

                    if (inimigo instanceof Goblin || inimigo instanceof EsqueletoGuerreiro) {
                        jogador.setPocoes(jogador.getPocoes() + 1);
                        System.out.println("Voce tambem encontrou uma pocao.");
                    }

                    mapa[novaLinha][novaColuna] = 0;
                    System.out.println();
                }
            } else if (evento == 2) {
                int recompensa = 10;
                jogador.setOuro(jogador.getOuro() + recompensa);
                System.out.println("Voce encontrou um tesouro com " + recompensa + " moedas de ouro.");

                if (jogador.getPocoes() < 3) {
                    jogador.setPocoes(jogador.getPocoes() + 1);
                    System.out.println("Dentro do bau havia uma pocao.");
                }

                mapa[novaLinha][novaColuna] = 0;
                System.out.println();
            } else if (evento == 3) {
                int danoArmadilha = 8;
                System.out.println("Voce caiu em uma armadilha!");
                jogador.receberDano(danoArmadilha);
                mapa[novaLinha][novaColuna] = 0;
                System.out.println();
            } else if (evento == 4) {
                PerguntaJava pergunta = perguntas[indicePergunta % perguntas.length];
                indicePergunta++;

                System.out.println("Voce encontrou uma porta magica.");
                exibirPergunta(pergunta);
                System.out.print("Digite a alternativa correta: ");

                int respostaJogador;
                try {
                    respostaJogador = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    respostaJogador = -1;
                }

                if (pergunta.verificarResposta(respostaJogador)) {
                    System.out.println("Resposta correta. A porta foi aberta.");
                    mapa[novaLinha][novaColuna] = 0;
                } else {
                    System.out.println("Resposta errada. A porta continua fechada.");
                    jogador.receberDano(6);
                    jogador.setLinha(linhaAtual);
                    jogador.setColuna(linhaAtual);
                }

                System.out.println();
            } else if (evento == 5) {
                System.out.println("Voce encontrou o tesouro final do templo!");
                System.out.println("Parabens, " + jogador.getNome() + "! Voce venceu a aventura.");
                jogoAtivo = false;
            }

            if (!jogador.estaVivo()) {
                System.out.println("Sua vida chegou a zero.");
                System.out.println("Fim de jogo.");
            }
        }

        scanner.close();
    }

    public static Inimigo criarInimigo(int linha, int coluna) {
        if (linha == 0 && coluna == 1) {
            return new Goblin();
        } else if (linha == 1 && coluna == 2) {
            return new EsqueletoGuerreiro();
        } else {
            return new OrcBrutal();
        }
    }

    public static void exibirPergunta(PerguntaJava pergunta) {
        System.out.println(pergunta.getEnunciado());

        for (int i = 0; i < pergunta.getAlternativas().length; i++) {
            System.out.println((i + 1) + " - " + pergunta.getAlternativas()[i]);
        }
    }
}