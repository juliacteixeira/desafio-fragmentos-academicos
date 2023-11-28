/**
 * Interface que define métodos para observadores do tempo do jogo.
 * @author João Dias
 * @version 2023.11.28
 */
public interface TempoObserver {
    /**
     * Método chamado quando o tempo do jogo é atualizado.
     * @param tempoJogo  O novo objeto Tempo com as informações atualizadas.
     */
    void atualizarTempo(Tempo tempoJogo);
}

