import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a interface gráfica do desempenho do jogo com um visual de game adventure em texto.
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

        // Cria um JScrollPane e define a altura mínima preferida maior
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));

         // Cria um JLabel para o minimapa
        JLabel minimapaLabel = new JLabel();
         minimapaLabel.setIcon(new ImageIcon("mapa.png")); // Substitua pelo caminho da sua imagem

         // Defina o JLabel para ser opaco para permitir o background da imagem
        minimapaLabel.setOpaque(true);
        minimapaLabel.setBackground(null); // Define o fundo como nulo para que a imagem seja o background

         // Adiciona rótulos de tempo e trabalhos ao painel norte
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

        container.add(painelNorte, BorderLayout.NORTH);

        // Substitui o JPanel do mapa pelo JLabel do minimapa
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, minimapaLabel);
        splitPane.setResizeWeight(1);  // Define a proporção da divisão

        // Adiciona o JSplitPane ao container usando BorderLayout
        container.add(splitPane, BorderLayout.CENTER);

        // Adiciona campo de texto e botão "Enviar" no painel sul
        JPanel painelSul = new JPanel(new BorderLayout());
        comandoTextField = new JTextField(15);

        // Adiciona campo de texto ao centro do painelSul
        painelSul.add(comandoTextField, BorderLayout.CENTER);

        // Adiciona botão "Ajuda" estilizado ao painelSul
        JButton botaoAjuda = new JButton("Ajuda");
        botaoAjuda.setBackground(Color.BLACK);
        botaoAjuda.setForeground(Color.WHITE);
        painelSul.add(botaoAjuda, BorderLayout.WEST);

        // Adiciona botão "Enviar" estilizado ao painelSul
        JButton botaoEnviar = new JButton("Enviar");
        botaoEnviar.setBackground(Color.BLACK);
        botaoEnviar.setForeground(Color.WHITE);
        painelSul.add(botaoEnviar, BorderLayout.EAST);

        // Estilo do botão Ajuda
        botaoEnviar.setBorderPainted(false);
        botaoEnviar.setFocusPainted(false);

        // Estilo do botão Ajuda
        botaoAjuda.setBorderPainted(false);
        botaoAjuda.setFocusPainted(false);

        // Organiza os componentes da interface gráfica
        container.add(painelSul, BorderLayout.SOUTH);

        // Adiciona ação para o botão "Enviar"
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarComando(comandoTextField.getText());
            }
        });

        // Configura ações dos botões
        botaoAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogo.imprimirAjuda();
            }
        });

        // Adiciona ação para o campo de texto (pressionar Enter)
        comandoTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarComando(comandoTextField.getText());
            }
        });

        // Timer para controlar o tempo do jogo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTempo();
                atualizarLabelTempo();
            }
        });
        timer.start();

        setSize(1700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Captura o comando digitado pelo jogador e o envia para o jogo.
     * @param comando Comando digitado pelo jogador.
     */
    public void capturarComando(String comando) {
        if(!jogo.analisarComando(comando)) infoTextArea.append("Comando inválido!\n");
        comandoTextField.setText("");
    }

    /**
     * Atualiza as informacoes que estao sendo exibidas no terminal de comandos
     * @param texto Texto a ser adicionado ao terminal de comandos
     */
    public void atualizarInfoTextArea(String texto) {
        infoTextArea.append(texto + "\n");
    }

    /**
     * Atualiza o rótulo de trabalhos na interface gráfica.
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
            timer.stop();
            jogo.setJogando(false);
            tempoLabel.setText("Tempo esgotado!");
            comandoTextField.setEnabled(true);
            atualizarInfoTextArea("Tempo esgotado! Fim do jogo.");
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
}
