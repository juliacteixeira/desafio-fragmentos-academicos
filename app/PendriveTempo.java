/**
 * Essa classe representa um pendrive de tempo.
 * Ela guarda um objeto Tempo, que eh passado desde a classe Jogo, Ambientes e Pendrives.
 * Quando o pendrive eh aberto, o tempo do jogo eh alterado de acordo com o tempo do pendrive.
 *
 * @author  Caio Souza and João Dias
 * @version 2023.11.23
 */
/**
 * Classe que representa um pendrive de tempo.
 */
public class PendriveTempo extends Pendrive {
    private int tempo;
    private Tempo tempoJogo;

    /**
     * Construtor que inicializa o PendriveTempo.
     *
     * @param tempo     O tempo que o pendrive irá adicionar ou remover do tempo do jogo.
     * @param tempoJogo O objeto Tempo que é usado no Jogo.
     */
    public PendriveTempo(int tempo, Tempo tempoJogo) {
        this.tempo = tempo;
        this.tempoJogo = tempoJogo;
    }

    /**
     * Abre o Pendrive de Tempo.
     * Muda o atributo aberto para true e adiciona ou remove o tempo do jogo.
     */
    public void abrir() {
        super.setAberto(true);
        tempoJogo.alterarTempo(tempo);
    }

    public int getTempoPendrive() {
        return tempo;
    }
}

