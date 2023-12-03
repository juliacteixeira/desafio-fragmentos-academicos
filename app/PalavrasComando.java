import java.util.HashMap;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 * 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class PalavrasComando
{
    // um vetor constante que guarda todas as palavras de comandos validas
    private HashMap<String, PalavraComando> comandosValidos;

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public PalavrasComando()
    {
       comandosValidos = new HashMap<>();
       for(PalavraComando comando : PalavraComando.values()) {
           if(comando != PalavraComando.DESCONHECIDO) {
               comandosValidos.put(comando.toString(), comando);
           }
       }
    }

    /*
        * Encontra o PalavraComando correspondente a uma palavra.
        * @param palavra String a ser procurada.
        * @return PalavraComando correspondente a palavra, ou PalavraComando.DESCONHECIDO se a palavra nao eh conhecida.
     */
    public PalavraComando getComando(String palavra) {
        PalavraComando comando = comandosValidos.get(palavra);
        if (comando != null) {
            return comando;
        }
        return PalavraComando.DESCONHECIDO;
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehComando(String umaString)
    {
        return comandosValidos.containsKey(umaString);
    }

    public String imprimirComandos() {
        String comandos = "";

        for (String comando : comandosValidos.keySet()) {
            comandos += comando + " | ";
        }

        return comandos;
    }
}
