
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa o tempo do jogo.
 * @author Caio Souza and João Dias
 * @version 2023.11.28
 */

public class Tempo {
    private int tempoJogo;

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

    public void setTempoJogo(int tempoJogo) {
        this.tempoJogo = tempoJogo;
    }

    public void alterarTempo(int tempo) {
        tempoJogo += tempo;
    }

    /**
     * Exibe o tempo atual do jogo no console.
     */
    public void exibirTempo() {
        System.out.println("Tempo de jogo: " + tempoJogo);
    }
}


