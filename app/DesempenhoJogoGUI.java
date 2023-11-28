import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a interface gráfica do desempenho do jogo.
 *
 * Esta classe estende JFrame e implementa a interface TempoObserver para observar o tempo de jogo.
 *
 * @author João Dias
 * @version 2023.11.28
 */
public class DesempenhoJogoGUI extends JFrame implements TempoObserver {

    // Variáveis de instância
    private int tempoRestante = 30 * 60;
    private JLabel tempoLabel;
    private JLabel labelTrabalhos = new JLabel();
    private int minutosRestantes;

    /**
     * Construtor padrão da classe DesempenhoJogoGUI.
     * Inicializa os componentes da interface gráfica, define o tempo inicial e configura o timer.
     */
    public DesempenhoJogoGUI() {
        this.minutosRestantes = 30;

        setTitle("Zephyrion: O Desafio dos Fragmentos Acadêmicos");

        labelTrabalhos.setText("Trabalhos Coletados:\n");

        tempoLabel = new JLabel();
        atualizarLabelTempo();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelTrabalhos);
        panel.add(tempoLabel);

        add(panel);

        // Configuração de cores e fontes
        panel.setBackground(new Color(97, 77, 92)); // Cor de fundo
        labelTrabalhos.setForeground(Color.WHITE); // Cor do texto
        tempoLabel.setForeground(Color.WHITE); // Cor do texto

        Font fonte = new Font("Arial", Font.BOLD, 14); // Fonte personalizada
        labelTrabalhos.setFont(fonte);
        tempoLabel.setFont(fonte);

        ImageIcon icon = new ImageIcon("meuQuarto.png"); // Adiciona a capa do jogo, gerada por AI
        JLabel labelIcone = new JLabel(icon);

        panel.add(labelIcone);

        // Adiciona botões
        JButton botaoSair = new JButton("Sair");

        // Adiciona os botões a um painel
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoSair);

        // Adiciona o painel de botões à interface
        add(painelBotoes, BorderLayout.SOUTH);

        // Configura ações dos botões

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrementarTempo();
                atualizarLabelTempo();
            }
        });
        timer.start();

        setSize(577, 578);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Método de callback chamado quando o tempo do jogo é atualizado.
     *
     * @param novoTempo O novo tempo de jogo.
     */
    @Override
    public void atualizarTempo(Tempo novoTempo) {
        SwingUtilities.invokeLater(() -> {
            int minutos = novoTempo.getTempoJogo() / 60;
            int segundos = novoTempo.getTempoJogo() % 60;

            tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
        });
    }

    /**
     * Método privado para decrementar o tempo de jogo.
     * Atualiza também a interface gráfica.
     */
    private void decrementarTempo() {
        if (tempoRestante > 0) {
            tempoRestante--;
            atualizarLabelTempo();
        } else {
            if (minutosRestantes > 0) {
                minutosRestantes--;
                tempoRestante = 59;
                atualizarLabelTempo();
            } else {
                System.out.println("Tempo esgotado! Fim do jogo.");
            }
        }
    }

    /**
     * Método privado para atualizar o rótulo de tempo na interface gráfica.
     */
    private void atualizarLabelTempo() {
        int minutos = tempoRestante / 60;
        int segundos = tempoRestante % 60;

        tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
    }

    /**
     * Método privado para atualizar o rótulo de trabalhos na interface gráfica.
     * 
     * Este método pode ser modificado para incluir a lógica dos trabalhos coletados em Swing.
     */
    private void atualizarLabelTrabalhos() {
        // Se precisar implementar a lógica dos trabalhos coletados em Swing, faça aqui
    }

    /**
     * Método principal que inicia a aplicação Swing.
     * Cria uma instância da classe DesempenhoJogoGUI.
     *
     * @param args Argumentos da linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DesempenhoJogoGUI();
        });
    }
}





















