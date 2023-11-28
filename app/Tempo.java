
import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa o tempo do jogo.
 * @author Caio Souza and João Dias
 * @version 2023.11.28
 */

public class Tempo {
    private int tempoJogo;
    private List<TempoObserver> observadores = new ArrayList<>();

    /**
     * Construtor para inicializar o tempo do jogo.
     * @param tempoJogo O tempo inicial do jogo.
     */
    public Tempo(int tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    /**
     * Obtém o tempo atual do jogo.
     * @return O tempo atual do jogo.
     */
    public int getTempoJogo() {
        return tempoJogo;
    }

    /**
     * Define o tempo do jogo e notifica os observadores sobre a mudança.
     * @param tempoJogo O novo tempo do jogo.
     */
    public void setTempoJogo(int tempoJogo) {
        this.tempoJogo = tempoJogo;
        notificarObservadores();
    }

    /**
     * Adiciona um observador para receber notificações sobre mudanças no tempo.
     * @param observador O observador a ser adicionado.
     */
    public void adicionarObservador(TempoObserver observador) {
        observadores.add(observador);
    }

    /**
     * Notifica todos os observadores sobre mudanças no tempo.
     */
    void notificarObservadores() {
        for (TempoObserver observador : observadores) {
            observador.atualizarTempo(this); // Alterado para passar o objeto Tempo
        }
    }

    /**
     * Aumenta o tempo, conforme o pendrive de tempo aberto.
     * @param pendrive O pendrive de tempo.
     */
    public void aumentarTempo(PendriveTempo pendrive) {
        setTempoJogo(getTempoJogo() + pendrive.getTempo());
    }
    /**
     * Diminui o tempo, conforme o pendrive de tempo aberto.
     * @param pendrive O pendrive de tempo.
     */
    public void decrementarTempo(PendriveTempo pendrive) {
        setTempoJogo(getTempoJogo() - pendrive.getTempo());
    }
    /**
     * Exibe o tempo atual do jogo no console.
     */
    public void exibirTempo() {
        System.out.println("Tempo de jogo: " + tempoJogo);
    }
}


