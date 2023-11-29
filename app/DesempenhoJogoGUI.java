/**
 * Classe que representa a interface gráfica do desempenho do jogo.
 * Esta classe estende JFrame e implementa a interface TempoObserver para observar o tempo de jogo.
 *
 * @author João Dias
 * @version 2023.11.28
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class DesempenhoJogoGUI extends JFrame implements TempoObserver {

    private int tempoRestante = 30 * 60;
    private JLabel tempoLabel;
    private JLabel labelTrabalhos = new JLabel();
    private int minutosRestantes;
    private JTextField comandoTextField;
    private JTextArea infoTextArea;

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

        ImageIcon icon = new ImageIcon("capa-zephyrion.png");
        JLabel labelIcone = new JLabel(icon);
        panel.add(labelIcone);

        // Adiciona um container usando BorderLayout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Adiciona um JTextArea para imprimir informações à direita
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(Color.BLACK);
        infoTextArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Ajuste conforme necessário
        container.add(scrollPane, BorderLayout.EAST);

        // Adiciona campo de texto e botão "Enviar" no painel sul
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usando FlowLayout para centralizar os componentes
        comandoTextField = new JTextField(20);
        painelSul.add(comandoTextField);

        JButton botaoEnviar = new JButton("Enviar");
        painelSul.add(botaoEnviar);

        // Adiciona botões no painel sul
        JButton botaoSair = new JButton("Sair");
        painelSul.add(botaoSair);

        // Configura ações dos botões
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Organiza os componentes da interface gráfica
        container.add(painelSul, BorderLayout.SOUTH);
        container.add(panel, BorderLayout.CENTER);

        comandoTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String comando = comandoTextField.getText();
                    processarComando(comando);
                    comandoTextField.setText("");
                }
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

        setSize(1700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void processarComando(String comando) {
        System.out.println("Comando inserido: " + comando);
    }

    @Override
    public void atualizarTempo(Tempo novoTempo) {
        SwingUtilities.invokeLater(() -> {
            int minutos = novoTempo.getTempoJogo() / 60;
            int segundos = novoTempo.getTempoJogo() % 60;

            tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
        });
    }

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

    private void atualizarLabelTempo() {
        int minutos = tempoRestante / 60;
        int segundos = tempoRestante % 60;

        tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
    }

    private void atualizarLabelTrabalhos() {
        // Lógica dos trabalhos coletados
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DesempenhoJogoGUI();
        });
    }
}





















