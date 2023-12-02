import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa eh a classe principal do jogo Desafio Fragmentos Academicos.
 *
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o personagem, cria o tempo, cria o analisador
 * e comeca o jogo.
 * Ela tambeme avalia e executa os comandos que o analisador retorna.
 *
 * @author Caio Souza e Júlia Teixeira
 * @version 2023.11.23
 */

public class Jogo {
    private Analisador analisador;
    private Ambientes ambientes;
    private Jogador jogador;
    private DesempenhoJogoGUI interfaceGUI;
    private Tempo tempo;
    private boolean isJogando;
    private final int TEMPO_MAXIMO = 20; // Em segundos
    private List<Comando> comandos = new ArrayList<>();

    /**
     * Instancia o analisador de comandos do jogo.
     * Instancia o jogador.
     * Instancia o tempo.
     * Instancia os ambientes e cria os pendrives.
     * Define o ambiente inicial do jogador. No caso, o ambiente "fora".
     */
    public Jogo() {
        analisador = new Analisador();

        jogador = new Jogador();

        tempo = new Tempo(TEMPO_MAXIMO);

        ambientes = new Ambientes(jogador.getDisciplinas(), tempo);

        Ambiente ambienteInicial = ambientes.getAmbiente("meuQuarto");
        jogador.setAmbienteAtual(ambienteInicial);

        // Controla a interface grafica apos os ambientes, jogador e itens serem criados
        interfaceGUI = new DesempenhoJogoGUI(this);
        interfaceGUI.setVisible(true);
    }

    public Tempo getTempo() {
        return tempo;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public boolean getIsJogando() {
        return isJogando;
    }

    public void setJogando(boolean jogando) {
        isJogando = jogando;
    }

    public void getSalvaArquivo() {
        this.salvaArquivo(comandos);
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        isJogando = true;
        imprimirBoasVindas();
    }

    /**
     * Imprime a mensagem de fim de jogo, em caso de vitoria.
     */

    private void imprimirFimDeJogo() {
        interfaceGUI.atualizarInfoTextArea("Parabens! Voce conseguiu entregar todos os trabalhos a tempo!");
        interfaceGUI.atualizarInfoTextArea("Agora Zephyrion pode descansar e aproveitar as ferias.");
    }

    /**
     * Imprime informacoes sobre o ambiente atual.
     */
    private void imprimirInformacoesAmbiente() {
        interfaceGUI.atualizarInfoTextArea(jogador.getAmbienteAtual().getDescricaoCompleta());
        interfaceGUI.atualizarInfoTextArea(jogador.getAmbienteAtual().showPendrives());
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        interfaceGUI.atualizarInfoTextArea("Bem-vindo ao Desafio dos Fragmentos Academico!");
        interfaceGUI.atualizarInfoTextArea(
                "O Desafio dos Fragmentos Acadêmicos se passa nos minutos finais do segundo semestre de 2023 na Universidade Federal de Lavras.");
        interfaceGUI.atualizarInfoTextArea(
                "Zephyrion, estudante de Sistemas de Informação, está sobrecarregado com disciplinas e precisa entregar quatro trabalhos a tempo.");
        interfaceGUI.atualizarInfoTextArea(
                "Voce comecara a patir do quarto de Zephyrion. Ajude ele a encontrar os fragmentos dos trabalhos e entregar a tempo!");
        interfaceGUI.atualizarInfoTextArea("Se estiver perdido, clique no botão AJUDA ao lado.");
        imprimirInformacoesAmbiente();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o) caso seja um comando valido
     * 
     * @param comandoDigitado O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    public boolean analisarComando(String comandoDigitado) {
        Comando comando = analisador.pegarComando(comandoDigitado);
        if (comando != null) {
            processarComando(comando);
            return true;
        }

        return false;
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private void processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            interfaceGUI.atualizarInfoTextArea("Eu nao entendi o que voce disse...");
        } else {
            PalavraComando palavraDeComando = comando.getPalavraDeComando();
            if (palavraDeComando == PalavraComando.IR) {
                irParaAmbiente(comando);
            } else if (palavraDeComando == PalavraComando.ABRIR) {
                abrirPendrive(comando);
            }

        }

    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de
     * palavras de comando
     */
    public void imprimirAjuda() {
        interfaceGUI
                .atualizarInfoTextArea("Voce esta perdido. Voce esta sozinho. Voce caminha pela casa de Zephyrion.");
        interfaceGUI.atualizarInfoTextArea("Suas palavras de comando sao:");
        interfaceGUI.atualizarInfoTextArea(analisador.mostrarComandos());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            interfaceGUI.atualizarInfoTextArea("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = null;

        if (direcao.equals("norte") || direcao.equals("leste") || direcao.equals("sul") || direcao.equals("oeste")) {
            comandos.add(comando);
        }

        try {
            // Tenta obter o próximo ambiente com base na direção fornecida
            switch (direcao) {
                case "norte":
                    proximoAmbiente = jogador.getAmbienteAtual().getSaida("norte");
                    break;
                case "leste":
                    proximoAmbiente = jogador.getAmbienteAtual().getSaida("leste");
                    break;
                case "sul":
                    proximoAmbiente = jogador.getAmbienteAtual().getSaida("sul");
                    break;
                case "oeste":
                    proximoAmbiente = jogador.getAmbienteAtual().getSaida("oeste");
                    break;
                default:
                    throw new IllegalArgumentException("Desculpe, não reconheço a direção: " + direcao);
            }

            if (proximoAmbiente == null) {
                interfaceGUI.atualizarInfoTextArea("Desculpe, não há passagem para o \" + direcao + \"!\"");
            } else {
                jogador.setAmbienteAtual(proximoAmbiente);
                imprimirInformacoesAmbiente();
            }
        } catch (IllegalArgumentException e) {
            interfaceGUI.atualizarInfoTextArea(e.getMessage());
        }

    }

    /**
     * Imprime o status do inventario de disciplinas do jogador.
     */
    public void statusInventarioDisciplinas() {
        interfaceGUI.atualizarInfoTextArea(jogador.statusDisciplinas());
    }

    /**
     * Verifica se o jogador completou todas as disciplinas.
     */
    private void disciplinasCompleta() {
        if (jogador.disciplinasCompleta()) {
            imprimirFimDeJogo();
            setJogando(false);
        }
    }

    /**
     * Tenta abrir um pendrive. Se o pendrive nao existe, imprime mensagem de erro.
     * Se o pendrive ja foi aberto, imprime mensagem de erro.
     * Caso contrario, abre o pendrive.
     * 
     * @param comando O comando a ser processado.
     */
    private void abrirPendrive(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            interfaceGUI.atualizarInfoTextArea("Qual pendrive?");
            return;
        }

        String numeroPendrive = comando.getSegundaPalavra(); // index do pendrive a ser aberto

        try {
            int index = Integer.parseInt(numeroPendrive);

            if (jogador.getAmbienteAtual().temPendrive(index)) {
                comandos.add(comando);
                Pendrive pendrive = jogador.getAmbienteAtual().getPendrive(index);

                if (pendrive.estaAberto()) {
                    interfaceGUI.atualizarInfoTextArea("O pendrive já foi aberto.");
                    return;
                } else
                    pendrive.abrir(); // Polimorfismo é chamado baseado no tipo do pendrive

                // Verifica se o pendrive é um pendrive de disciplina
                if (pendrive instanceof PendriveDisciplina) {
                    teleportarMeuQuarto();
                    interfaceGUI.atualizarLabelTrabalhos(jogador.statusDisciplinas());
                    disciplinasCompleta();
                }
                if (pendrive instanceof PendriveTempo) {
                    int tempo = ((PendriveTempo) pendrive).getTempoPendrive();

                    if (tempo > 0)
                        interfaceGUI.atualizarInfoTextArea("Voce ganhou " + tempo + " segundos!");
                    else
                        interfaceGUI.atualizarInfoTextArea("Voce perdeu " + tempo + " segundos!");
                }
            } else {
                interfaceGUI.atualizarInfoTextArea("Nao ha um pendrive com esse numero!");
            }
        } catch (IllegalArgumentException e) {
            interfaceGUI.atualizarInfoTextArea(e.getMessage());
        }

    }

    /**
     * Teleporta o jogador para o ambiente "meuQuarto" apos ele abrir um pendrive
     * contendo um fragmento.
     */
    private void teleportarMeuQuarto() {
        interfaceGUI.atualizarInfoTextArea("ZWOOSH!");
        interfaceGUI
                .atualizarInfoTextArea("Voce encontrou um fragmento e foi teleportado para o seu quarto. Que loucura!");
        interfaceGUI.atualizarInfoTextArea("Continue procurando os outros fragmentos.");

        jogador.setAmbienteAtual(ambientes.getAmbiente("meuQuarto"));
        imprimirInformacoesAmbiente();
    }

    /**
     * Salva a lista de comandos em um arquivo de texto chamado "comandos.txt" e
     * registra o tempo de jogo.
     *
     * @param lista Lista de comandos a serem salvos no arquivo.
     */
    public void salvaArquivo(List<Comando> lista) {
        try (FileWriter fileWriter = new FileWriter("comandos.txt", true)) {
            // Escreve cada comando da lista no arquivo, seguido por uma quebra de linha
            for (Comando item : lista) {
                fileWriter.write(item.toString() + "\n");
            }

            // Registra o tempo total de jogo no arquivo
            fileWriter
                    .write("Você finalizou o jogo com  " + tempo.exibirTempoMinSeg() + " restantes \n");

            fileWriter.write("======================================================================\n");

        } catch (IOException e) {
            // Imprime o rastreamento da exceção em caso de erro durante a escrita no
            // arquivo
            e.printStackTrace();
        }
    }
}