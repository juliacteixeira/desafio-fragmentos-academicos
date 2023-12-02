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
public class DesempenhoJogoGUI extends JFrame implements InventarioDisciplinasGUI {

    // Variáveis de instância
    private Tempo tempoJogo;
    private JLabel tempoLabel;
    private JLabel labelTrabalhos = new JLabel();
    private Timer timer;
    private Jogador jogador;
    private JTextField comandoTextField;
    private JTextArea infoTextArea;

    /**
     * Construtor padrão da classe DesempenhoJogoGUI.
     * Inicializa os componentes da interface gráfica, define o tempo inicial e configura o timer.
     *
     * @param tempoJogo Objeto Tempo representando o tempo do jogo.
     * @param jogador   Objeto Jogador representando o jogador.
     */
    public DesempenhoJogoGUI(Tempo tempoJogo, Jogador jogador) {
        this.tempoJogo = tempoJogo;
        this.jogador = jogador;
        this.jogador.adicionarObservador(this);

        setTitle("Zephyrion: O Desafio dos Fragmentos Acadêmicos");

        labelTrabalhos.setText(jogador.statusDisciplinas());

        tempoLabel = new JLabel();
        atualizarLabelTempo();

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        Font fonte = new Font("Courier New", Font.PLAIN, 14);
        labelTrabalhos.setFont(fonte);
        tempoLabel.setFont(fonte.deriveFont(Font.BOLD, 18)); // Estilo negrito e tamanho maior

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

        // Subpainel para os rótulos de trabalhos
        JPanel subPainelTrabalhos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPainelTrabalhos.setBackground(Color.WHITE);
        subPainelTrabalhos.add(labelTrabalhos);
        painelNorte.add(subPainelTrabalhos);

        // Subpainel para o rótulo de tempo
        JPanel subPainelTempo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPainelTempo.setBackground(Color.WHITE);
        subPainelTempo.add(tempoLabel);
        painelNorte.add(subPainelTempo);

        container.add(painelNorte, BorderLayout.NORTH);

        // Substitui o JPanel do mapa pelo JLabel do minimapa
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, minimapaLabel);
        splitPane.setResizeWeight(1);  // Define a proporção da divisão

        // Adiciona o JSplitPane ao container usando BorderLayout
        container.add(splitPane, BorderLayout.CENTER);

        // Adiciona campo de texto e botão "Enviar" no painel sul
        JPanel painelSul = new JPanel(new BorderLayout());
        comandoTextField = new JTextField(20);

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
        botaoAjuda.setBorderPainted(false);
        botaoAjuda.setFocusPainted(false);

        // Estilo do botão Enviar
        botaoEnviar.setBorderPainted(false);
        botaoEnviar.setFocusPainted(false);

        // Organiza os componentes da interface gráfica
        container.add(painelSul, BorderLayout.SOUTH);

        // Configura ações dos botões
        botaoAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Adiciona ação para o botão "Enviar"
        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarComando(comandoTextField.getText());
                comandoTextField.setText("");
            }
        });

        // Adiciona ação para o campo de texto (pressionar Enter)
        comandoTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarComando(comandoTextField.getText());
                comandoTextField.setText("");
            }
        });

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
     * Método privado para decrementar o tempo de jogo.
     * Atualiza também a interface gráfica.
     */
    private void alterarTempo() {
        int tempoRestante = tempoJogo.getTempoJogo();

        if (tempoRestante > 0) {
            tempoJogo.setTempoJogo(tempoRestante - 1);
            atualizarLabelTempo();
        } else { // Aqui, o jogo termina.
            timer.stop();
            System.out.println("Tempo esgotado! Fim do jogo.");
        }
    }

    /**
     * Método privado para processar os comandos do jogo.
     *
     * @param comando Comando utilizado pelo jogador no chat.
     */
    private void processarComando(String comando) {
        // Adiciona o comando ao JTextArea
        infoTextArea.append(comando + "\n");

        // Se desejar rolar automaticamente para o final do JTextArea:
        infoTextArea.setCaretPosition(infoTextArea.getDocument().getLength());

        // Limpa o JTextField
        comandoTextField.setText("");
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
     * Método privado para atualizar o rótulo de trabalhos na interface gráfica.
     */
    private void atualizarLabelTrabalhos() {
        labelTrabalhos.setText(jogador.statusDisciplinas());
    }

    @Override
    public void atualizarInventarioGUI() {
        atualizarLabelTrabalhos();
    }
}
