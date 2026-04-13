package AtividadeRPG;

public abstract class Personagem {
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;


    private int nivel;
    private int xp;
    private int xpParaProximoNivel;

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;


        this.nivel = 1;
        this.xp = 0;
        this.xpParaProximoNivel = 100;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }


    public void receberDano(int dano) {
        int danoFinal = dano - defesa;

        if (danoFinal < 1) {
            danoFinal = 1;
        }

        vida -= danoFinal;

        if (vida < 0) {
            vida = 0;
        }

        System.out.println(nome + " recebeu " + danoFinal + " de dano.");
    }

    public boolean estaVivo() {
        return vida > 0;
    }



    public void ganharXp(int quantidade) {
        xp += quantidade;
        System.out.println(nome + " ganhou " + quantidade + " XP!");

        while (xp >= xpParaProximoNivel) {
            subirNivel();
        }
    }

    private void subirNivel() {
        xp -= xpParaProximoNivel;
        nivel++;


        xpParaProximoNivel = (int)(xpParaProximoNivel * 1.5);


        vida += 10;
        ataque += 2;
        defesa += 2;

        System.out.println("🔥 " + nome + " SUBIU DE NÍVEL!");
        System.out.println("Agora está no nível " + nivel);
    }


    public int getNivel() {
        return nivel;
    }

    public int getXp() {
        return xp;
    }

    public int getXpParaProximoNivel() {
        return xpParaProximoNivel;
    }
}