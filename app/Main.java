import javafx.application.Application;
/**
 * Classe principal que inicia o jogo e a interface gráfica.
 * Instancia o jogo, inicia a interface gráfica em uma thread separada e inicia o jogo.
 *
 * @author Caio Souza, João Dias and Julia Teixeira
 * @version 2023.11.27
 */
public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        /**  
        * Inicia a interface gráfica em uma thread separada
        */ 
        new Thread(() -> Application.launch(DesempenhoJogoGUI.class, args)).start();

        /**
        * Inicia o jogo
        */ 
        jogo.jogar();
    }
}


