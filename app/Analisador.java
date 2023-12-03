import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 * 
 * Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    private Scanner entrada;         // origem da entrada de comandos

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    /**
     * @return Caso seja um comando valido, retorna um comando. Caso contrario, retorna null.
     */
    public Comando pegarComando(String comandoDigitado) {
        String[] palavras = comandoDigitado.split(" ");

        boolean comandoValido = palavras.length == 2;

        if(comandoValido) {
            String palavra1 = palavras[0];
            String palavra2 = palavras[1];

            return new Comando(palavrasDeComando.getComando(palavra1), palavra2);
        }

        return  null;
    }

    /**
     * Chama o metodo imprimirComandos da classe PalavrasComando, evitando o aclopamento implicito.
     */
    public String mostrarComandos() {
        return palavrasDeComando.imprimirComandos();
    }
}
