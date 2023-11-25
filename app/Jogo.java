import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambientes ambientes;
    private Jogador jogador;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        analisador = new Analisador();

        jogador = new Jogador();

        ambientes = new Ambientes(jogador.getDisciplinas());

        Ambiente ambienteInicial = ambientes.getAmbiente("fora");
        jogador.setAmbienteAtual(ambienteInicial);
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {

        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
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
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
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
        else if(palavraDeComando == PalavraComando.OLHAR) {
            olhar();
        }
        else if (palavraDeComando == PalavraComando.SAIR) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Remover esse metodo na nossa propria implementacao do jogo.
     */
    private void olhar() {
        imprimirInformacoesAmbiente();
    }

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

        // Tenta sair do ambiente atual
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
            return true;  // sinaliza que nos queremos sair
        }
    }
}
