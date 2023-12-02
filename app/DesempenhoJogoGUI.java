import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Classe que representa a interface gráfica do desempenho do jogo com um visual
 * de game adventure em texto.
 *
 * Esta classe estende JFrame e implementa a interface InventarioDisciplinasGUI.
 *
 * @author João Dias
 * @version 2023.12.01
 */
public class DesempenhoJogoGUI extends JFrame {

    // Variáveis de instância
    private JLabel tempoLabel;
    private JLabel labelTrabalhos;
    private JTextField comandoTextField;
    private JTextArea infoTextArea;
    private Timer timer;
    private Jogo jogo;
    private Tempo tempoJogo;

    public DesempenhoJogoGUI(Jogo jogo) {
        // Para chamar os metodos de jogo, temos que ter uma referencia a ele
        this.jogo = jogo;
        tempoJogo = jogo.getTempo();

        setTitle("Zephyrion: O Desafio dos Fragmentos Acadêmicos");

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        criarLabels();

        JButton botaoAjuda = new JButton("Ajuda");
        JButton botaoEnviar = new JButton("Enviar");
        comandoTextField = new JTextField(20);

        JScrollPane scrollPane = criarScrollPane(infoTextArea);
        JLabel minimapaLabel = criarMinimapaLabel();
        JPanel painelNorte = criarPainelNorte(tempoLabel, labelTrabalhos);
        JSplitPane splitPane = criarSplitPane(scrollPane, minimapaLabel);
        JPanel painelSul = criarPainelSul(comandoTextField, botaoAjuda, botaoEnviar);

        estilizarBotoes(botaoAjuda, botaoEnviar);

        adicionarAcoesBotoes(botaoAjuda, botaoEnviar, comandoTextField);

        container.add(painelNorte, BorderLayout.NORTH);
        container.add(splitPane, BorderLayout.CENTER);
        container.add(painelSul, BorderLayout.SOUTH);

        adicionarEventoComandoTextField();

        configTimer();

        setSize(1700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void criarLabels() {
        Font fonte = new Font("Courier New", Font.PLAIN, 14);
        labelTrabalhos = new JLabel();
        labelTrabalhos.setFont(fonte);
        labelTrabalhos.setText(jogo.getJogador().statusDisciplinas());

        tempoLabel = new JLabel();
        tempoLabel.setFont(fonte.deriveFont(Font.BOLD, 18)); // Estilo negrito e tamanho maior
        tempoLabel.setText("Iniciando o jogo...");

        // Adiciona um JTextArea estilizado para imprimir informações à direita
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(Color.BLACK);
        infoTextArea.setForeground(Color.WHITE);

        // Define a política de quebra de palavras para ajustar à largura
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        // Ajusta a margem do JTextArea
        infoTextArea.setMargin(new Insets(10, 10, 10, 10));

        // Define uma fonte e tamanho maior para um estilo mais proeminente
        Font textoFonte = new Font("Courier New", Font.PLAIN, 16);
        infoTextArea.setFont(textoFonte);

        // Define o alinhamento do texto para justificado
        infoTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    // Método para criar JScrollPane
    private JScrollPane criarScrollPane(JTextArea infoTextArea) {
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        return scrollPane;
    }

    // Método para criar JLabel do minimapa
    private JLabel criarMinimapaLabel() {
        JLabel minimapaLabel = new JLabel();
        minimapaLabel.setIcon(new ImageIcon("mapa.png")); // Substitua pelo caminho da sua imagem
        minimapaLabel.setOpaque(true);
        minimapaLabel.setBackground(null); // Define o fundo como nulo para que a imagem seja o background
        return minimapaLabel;
    }

    // Método para criar painel norte
    private JPanel criarPainelNorte(JLabel tempoLabel, JLabel labelTrabalhos) {
        JPanel painelNorte = new JPanel(new GridLayout(2, 1)); // GridLayout para organizar em duas linhas
        painelNorte.setBackground(Color.WHITE);

        // Subpainel para o rótulo de tempo
        JPanel subPainelTempo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPainelTempo.setBackground(Color.WHITE);
        subPainelTempo.add(tempoLabel);
        painelNorte.add(subPainelTempo);

        // Subpainel para os rótulos de trabalhos
        JPanel subPainelTrabalhos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPainelTrabalhos.setBackground(Color.WHITE);
        subPainelTrabalhos.add(labelTrabalhos);
        painelNorte.add(subPainelTrabalhos);

        return painelNorte;
    }

    // Método para criar JSplitPane
    private JSplitPane criarSplitPane(JScrollPane scrollPane, JLabel minimapaLabel) {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, minimapaLabel);
        splitPane.setResizeWeight(1); // Define a proporção da divisão
        return splitPane;
    }

    // Método para criar painel sul
    private JPanel criarPainelSul(JTextField comandoTextField, JButton botaoAjuda, JButton botaoEnviar) {
        JPanel painelSul = new JPanel(new BorderLayout());
        painelSul.add(comandoTextField, BorderLayout.CENTER);
        painelSul.add(botaoAjuda, BorderLayout.WEST);
        painelSul.add(botaoEnviar, BorderLayout.EAST);
        return painelSul;
    }

    // Método para estilizar botões
    private void estilizarBotoes(JButton botaoAjuda, JButton botaoEnviar) {
        botaoEnviar.setBorderPainted(false);
        botaoEnviar.setFocusPainted(false);
        botaoAjuda.setBorderPainted(false);
        botaoAjuda.setFocusPainted(false);
    }

    // Método para adicionar ações aos botões
    private void adicionarAcoesBotoes(JButton botaoAjuda, JButton botaoEnviar, JTextField comandoTextField) {
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarComando(comandoTextField.getText());
            }
        });

        botaoAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogo.imprimirAjuda();
            }
        });
    }

    private void adicionarEventoComandoTextField() {
        comandoTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarComando(comandoTextField.getText());
            }
        });
    }

    private void configTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTempo();
                atualizarLabelTempo();
            }
        });
        timer.start();
    }

    /**
     * Captura o comando digitado pelo jogador e o envia para o jogo.
     * 
     * @param comando Comando digitado pelo jogador.
     */
    public void capturarComando(String comando) {
        if (!jogo.analisarComando(comando))
            infoTextArea.append("Comando inválido!\n");
        comandoTextField.setText("");
    }

    /**
     * Atualiza as informacoes que estao sendo exibidas no terminal de comandos
     * 
     * @param texto Texto a ser adicionado ao terminal de comandos
     */
    public void atualizarInfoTextArea(String texto) {
        infoTextArea.append(texto + "\n");
    }

    /**
     * Atualiza o rótulo de trabalhos na interface gráfica.
     * 
     * @param inventario Texto a ser exibido no rótulo de trabalhos.
     */
    public void atualizarLabelTrabalhos(String inventario) {
        labelTrabalhos.setText(inventario);
    }

    /**
     * Método privado para decrementar o tempo de jogo.
     * Atualiza também a interface gráfica.
     */
    private void alterarTempo() {
        int tempoRestante = tempoJogo.getTempoJogo();

        if (tempoRestante > 0 && jogo.getJogando()) {
            tempoJogo.setTempoJogo(tempoRestante - 1);
            atualizarLabelTempo();
        } else { // Aqui, o jogo termina.
            terminarJogo();
        }
    }

    /**
     * Método privado para atualizar o rótulo de tempo na interface gráfica.
     */
    private void atualizarLabelTempo() {
        int minutos = tempoJogo.getTempoJogo() / 60;
        int segundos = tempoJogo.getTempoJogo() % 60;

        tempoLabel.setText(String.format("Tempo Restante: %02d minutos e %02d segundos", minutos, segundos));
    }

    /**
     * Método privado para terminar o jogo.
     */
    private void terminarJogo() {
        timer.stop();
        jogo.setJogando(false);
        tempoLabel.setText("Tempo esgotado!");
        comandoTextField.setEnabled(true);
        atualizarInfoTextArea("Tempo esgotado! Fim do jogo.");
    }
}