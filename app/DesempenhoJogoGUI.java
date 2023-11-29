import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * Classe que representa a interface gráfica do desempenho do jogo.
 *
 * Esta classe estende JFrame e implementa a interface TempoObserver para observar o tempo de jogo.
 *
 * @author João Dias
 * @version 2023.11.28
 */
public class DesempenhoJogoGUI extends JFrame implements InventarioDisciplinasGUI{

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
     */
    public DesempenhoJogoGUI(Tempo tempoJogo, Jogador jogador) {
        this.tempoJogo = tempoJogo;
        this.jogador = jogador;
        this.jogador.adicionarObservador(this);

        setTitle("Zephyrion: O Desafio dos Fragmentos Acadêmicos");

        labelTrabalhos.setText(jogador.statusDisciplinas());

        tempoLabel = new JLabel();
        atualizarLabelTempo();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelTrabalhos);
        panel.add(tempoLabel);

        // Configuração de cores e fontes
        panel.setBackground(Color.WHITE); // Cor de fundo
        labelTrabalhos.setForeground(Color.BLACK); // Cor do texto
        tempoLabel.setForeground(Color.BLACK); // Cor do texto
        
        // Adiciona um container usando BorderLayout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        Font fonte = new Font("Arial", Font.BOLD, 14); // Fonte personalizada
        labelTrabalhos.setFont(fonte);
        tempoLabel.setFont(fonte);

        // Adiciona um JTextArea para imprimir informações à direita
        infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setBackground(Color.BLACK);
        infoTextArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Ajuste conforme necessário
        container.add(scrollPane, BorderLayout.EAST);

        ImageIcon icon = new ImageIcon("capa-zephyrion.png"); // Adiciona a capa do jogo, gerada por AI
        JLabel labelIcone = new JLabel(icon);
        panel.add(labelIcone);

        // Adiciona campo de texto e botão "Enviar" no painel sul
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Usando FlowLayout para centralizar os componentes
        comandoTextField = new JTextField(20);
        painelSul.add(comandoTextField);

        // Organiza os componentes da interface gráfica
        container.add(painelSul, BorderLayout.SOUTH);
        container.add(panel, BorderLayout.CENTER);

        // Adiciona botões
        JButton botaoEnviar = new JButton("Enviar");
        painelSul.add(botaoEnviar);
        JButton botaoAjuda = new JButton("Ajuda");
        painelSul.add(botaoAjuda);

        // Configura ações dos botões
        botaoAjuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.append("TESTE");
            }
        });

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
     * @param comando Comando utilizado pelo jogador no chat.
     */
    private void processarComando(String comando) {
        System.out.println("Comando inserido: " + comando);
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
     * 
     * Este método pode ser modificado para incluir a lógica dos trabalhos coletados em Swing.
     */
    private void atualizarLabelTrabalhos() {
        labelTrabalhos.setText(jogador.statusDisciplinas());
    }

    @Override
    public void atualizarInventarioGUI() {
        atualizarLabelTrabalhos();
    }
}


















