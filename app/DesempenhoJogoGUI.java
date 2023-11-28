import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a interface gráfica do desempenho do jogo.
 * Estende a classe Application do JavaFX.
 *
 * @author João Dias
 * @version 2023.11.28
 */
public class DesempenhoJogoGUI extends Application implements TempoObserver {

    private int tempoRestante = 30 * 60; // Tempo restante 
    private List<Trabalho> trabalhosColetados;
    private Label tempoLabel;
    private Label labelTrabalhos = new Label();
    private int minutosRestantes;
    private Tempo tempoJogo;

    /**
     * Construtor padrão da classe. Necessário para utilizar JavaFX.
     */
    public DesempenhoJogoGUI() {
        this.trabalhosColetados = new ArrayList<>();
        this.minutosRestantes = 30;
        this.tempoJogo = new Tempo(30 * 60);
    }

    /**
     * Método principal que inicia a interface gráfica.
     *
     * @param primaryStage O palco principal.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Desempenho");

        labelTrabalhos.setText("Trabalhos Coletados:\n");

        tempoLabel = new Label(); // Adicione esta linha para inicializar tempoLabel
        atualizarLabelTempo(); // Adicione esta linha para exibir o tempo inicial

        VBox vbox = new VBox(labelTrabalhos, tempoLabel);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        tempoJogo.adicionarObservador(this); // Registro da instância da interface gráfica como observador do tempo

        // Configurar o cronômetro para decrementar o tempo a cada segundo
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    decrementarTempo();
                    atualizarLabelTempo();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir indefinidamente
        timeline.play();
    }

    /**
     * Método da interface TempoObserver chamado quando o tempo do jogo é atualizado.
     * Atualiza dinamicamente o timer na interface gráfica com o novo tempo.
     *
     * @param novoTempo O novo tempo do jogo.
     */
    @Override
    public void atualizarTempo(Tempo novoTempo) {
    Platform.runLater(() -> {
        int minutos = novoTempo.getTempoJogo() / 60;
        int segundos = novoTempo.getTempoJogo() % 60;

        tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
    });
    }   


    /**
     * Método para decrementar o tempo.
     */
    private void decrementarTempo() {
        if (tempoRestante > 0) {
            tempoRestante--;
            atualizarLabelTempo();
        } else {
            // Tempo esgotou, verificar se há minutos para decrementar
            if (minutosRestantes > 0) {
                minutosRestantes--;
                tempoRestante = 59; // reinicia os segundos
                atualizarLabelTempo();
            } else {
                // Tempo chegou a zero, encerrar o jogo ou realizar outras ações
                System.out.println("Tempo esgotado! Fim do jogo.");
            }
        }
    }

    /**
     * Método para atualizar o texto da label de tempo.
     */
    private void atualizarLabelTempo() {
        int minutos = tempoRestante / 60;
        int segundos = tempoRestante % 60;

        tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
    }

    /**
     * Método para atualizar a label de trabalhos coletados.
     */
    private void atualizarLabelTrabalhos() {
        StringBuilder trabalhosText = new StringBuilder("Trabalhos Coletados:\n");

        // Verifica se trabalhosColetados não é nulo antes de iterar sobre ele
        if (trabalhosColetados != null) {
            for (Trabalho trabalho : trabalhosColetados) {
                // Verifica se a disciplina do trabalho não é nula antes de acessar o nome
                if (trabalho.getDisciplina() != null) {
                    trabalhosText.append(trabalho.getDisciplina().getNome()).append("\n");
                }
            }
        }

        labelTrabalhos.setText(trabalhosText.toString());
    }

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
}













