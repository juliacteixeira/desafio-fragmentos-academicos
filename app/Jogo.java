import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Essa eh a classe principal do jogo Desafio Fragmentos Academicos.
 *
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o personagem, cria o tempo, cria o analisador
 * e comeca o jogo.
 * Ela tambeme avalia e executa os comandos que o analisador retorna.
 *
 * @author  Caio Souza
 * @version 2023.11.23
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambientes ambientes;
    private Jogador jogador;

    private Tempo tempo;
    private final int TEMPO_MAXIMO = 60; // Em segundos

    /**
     * Instancia o analisador de comandos do jogo.
     * Instancia o jogador.
     * Instancia o tempo.
     * Instancia os ambientes e cria os pendrives.
     * Define o ambiente inicial do jogador. No caso, o ambiente "fora".
     */
    public Jogo()
    {
        analisador = new Analisador();

        jogador = new Jogador();

        tempo = new Tempo(TEMPO_MAXIMO);

        ambientes = new Ambientes(jogador.getDisciplinas(), tempo);

        Ambiente ambienteInicial = ambientes.getAmbiente("meuQuarto");
        jogador.setAmbienteAtual(ambienteInicial);
    }

    public Tempo getTempo() {
        return tempo;
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar()
    {

        imprimirBoasVindas();

        boolean terminado = false;
        while (! terminado) {
            if(jogador.disciplinasCompleta()) { // Verifica se o jogador completou todas as disciplinas, caso sim, o jogo termina.
                imprimirFimDeJogo();
                terminado = true;
            } else {
                Comando comando = analisador.pegarComando();
                terminado = processarComando(comando);
            }
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    private void imprimirFimDeJogo() {
        System.out.println("Parabens! Voce conseguiu entregar todos os trabalhos a tempo!");
        System.out.println("Agora Zephyrion pode descansar e aproveitar as ferias.");
    }

    /**
     * Imprime informacoes sobre o ambiente atual.
     */
    private void imprimirInformacoesAmbiente() {
        System.out.println(jogador.getAmbienteAtual().getDescricaoCompleta());
        jogador.getAmbienteAtual().showPendrives();
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao Desafio dos Fragmentos Academico!");
        System.out.println("O Desafio dos Fragmentos Acadêmicos se passa nos minutos finais do segundo semestre de 2023 na Universidade Federal de Lavras.");
        System.out.println("Zephyrion, estudante de Sistemas de Informação, está sobrecarregado com disciplinas e precisa entregar quatro trabalhos a tempo.");
        System.out.println("Voce comecara a patir do quarto de Zephyrion. Ajude ele a encontrar os fragmentos dos trabalhos e entregar a tempo!");
        System.out.println("Digite '" + PalavraComando.AJUDA + "' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirInformacoesAmbiente();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando)
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        PalavraComando palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando == PalavraComando.AJUDA) {
            imprimirAjuda();
        }
        else if (palavraDeComando == PalavraComando.IR) {
            irParaAmbiente(comando);
        }
        else if(palavraDeComando == PalavraComando.ABRIR) {
            abrirPendrive(comando);
        }
        else if(palavraDeComando == PalavraComando.STATUS) {
            statusInventarioDisciplinas();
        }
        else if (palavraDeComando == PalavraComando.TEMPO) {
            tempo.exibirTempo();
        }
        else if (palavraDeComando == PalavraComando.SAIR) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de
     * palavras de comando
     */
    private void imprimirAjuda()
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        analisador.mostrarComandos();
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando)
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        Ambiente proximoAmbiente = null;
        if(direcao.equals("norte")) {
            proximoAmbiente = jogador.getAmbienteAtual().getSaida("norte");
        }
        if(direcao.equals("leste")) {
            proximoAmbiente = jogador.getAmbienteAtual().getSaida("leste");
        }
        if(direcao.equals("sul")) {
            proximoAmbiente = jogador.getAmbienteAtual().getSaida("sul");
        }
        if(direcao.equals("oeste")) {
            proximoAmbiente = jogador.getAmbienteAtual().getSaida("oeste");
        }

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            jogador.setAmbienteAtual(proximoAmbiente);

            imprimirInformacoesAmbiente();
        }
    }

    /**
     * Imprime o status do inventario de disciplinas do jogador.
     */
    private void statusInventarioDisciplinas() {
        jogador.statusDisciplinas();
    }

    /**
     * Tenta abrir um pendrive. Se o pendrive nao existe, imprime mensagem de erro.
     * Se o pendrive ja foi aberto, imprime mensagem de erro.
     * Caso contrario, abre o pendrive.
     * @param comando O comando a ser processado.
     */
    private void abrirPendrive(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            System.out.println("Qual pendrive?");
            return;
        }

        String numeroPendrive = comando.getSegundaPalavra(); // index do pendrive a ser aberto

        int index = Integer.parseInt(numeroPendrive);

        if(jogador.getAmbienteAtual().temPendrive(index)) {
            Pendrive pendrive = jogador.getAmbienteAtual().getPendrive(index);

            if(pendrive.estaAberto()) System.out.println("O pendrive já foi aberto.");
            else pendrive.abrir(); // Polimorfismo é chamado baseado no tipo do pendrive

            // Verifica se o pendrive é um pendrive de disciplina
            if(pendrive instanceof PendriveDisciplina) teleportarMeuQuarto();
        }
        else {
            System.out.println("Nao ha um pendrive com esse numero!");
        }
    }

    /**
     * Teleporta o jogador para o ambiente "meuQuarto" apos ele abrir um pendrive contendo um fragmento.
     */
    private void teleportarMeuQuarto() {
        System.out.println("ZWOOSH!");
        System.out.println("Voce encontrou um fragmento e foi teleportado para o seu quarto. Que loucura!");
        System.out.println("Continue procurando os outros fragmentos.");

        jogador.setAmbienteAtual(ambientes.getAmbiente("meuQuarto"));
        imprimirInformacoesAmbiente();
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */

    private boolean sair(Comando comando)
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;
        }
    }
}
