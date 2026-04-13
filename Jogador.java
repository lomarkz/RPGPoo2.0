package AtividadeRPG;

public class Jogador extends Personagem {
    private int ouro;
    private int pocoes;
    private int linha;
    private int coluna;

    public Jogador(String nome, int vida, int ataque, int defesa, int ouro, int pocoes, int linha, int coluna) {
        super(nome, vida, ataque, defesa);
        this.ouro = ouro;
        this.pocoes = pocoes;
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getPocoes() {
        return pocoes;
    }

    public void setPocoes(int pocoes) {
        this.pocoes = pocoes;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void atacar(Personagem alvo) {
        int dano = getAtaque();
        System.out.println(getNome() + " atacou " + alvo.getNome() + "!");
        alvo.receberDano(dano);
    }

    public void usarPocao() {
        if (pocoes > 0) {
            setVida(getVida() + 15);
            pocoes--;

            if (getVida() > 100) {
                setVida(100);
            }

            System.out.println(getNome() + " usou uma pocao e recuperou vida.");
        } else {
            System.out.println("Voce nao tem pocoes.");
        }
    }

    public void exibirStatus() {
        String textoPocoes;

        if (pocoes == 1) {
            textoPocoes = "1 pocao de cura";
        } else {
            textoPocoes = pocoes + " pocoes de cura";
        }

        System.out.println("Nome: " + getNome());
        System.out.println("Vida: " + getVida());
        System.out.println("Ataque: " + getAtaque());
        System.out.println("Defesa: " + getDefesa());
        System.out.println("Ouro: " + ouro);
        System.out.println("Inventario: " + textoPocoes);
        System.out.println("Posicao: (" + linha + ", " + coluna + ")");
        System.out.println();
    }
}
