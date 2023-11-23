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
 * @author  João Dias
 * @version 2023.11.23
 */
public class PendriveTempo extends Pendrive {
    private int tempoAdicional;
    private int tempoReduzido;

    public PendriveTempo(int fragmentos, int tempoAdicional, int tempoReduzido) {
        super.setFragmentos(fragmentos);
        this.tempoAdicional = tempoAdicional;
        this.tempoReduzido = tempoReduzido;
    }

    public int getTempoAdicional() {
        return tempoAdicional;
    }

    public void setTempoAdicional(int tempoAdicional) {
        this.tempoAdicional = tempoAdicional;
    }

    public int getTempoReduzido() {
        return tempoReduzido;
    }

    public void setTempoReduzido(int tempoReduzido) {
        this.tempoReduzido = tempoReduzido;
    }
}
