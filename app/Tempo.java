public class Tempo { // Criar a gestao de tempo do jogo aqui.
    private int tempoJogo;

    public Tempo(int tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    public int getTempoJogo() {
        return tempoJogo;
    }

    public void setTempoJogo(int tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    public void exibirTempo() {
        System.out.println("Tempo de jogo: " + tempoJogo);
    }
}
