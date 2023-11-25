import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Ambiente { // Falta implementar Cloneable
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private ArrayList<Pendrive> pendrives;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        this.descricao = descricao;
        saidas = new HashMap<>();
        pendrives = new ArrayList<>();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param direcao A direcao da saida.
     * @param ambiente O ambiente de destino.
     */
    public void ajustarSaida(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }

    /**
     * Dada uma direcao, retorna o ambiente em tal direcao.
     * @param direcao A direcao da saida.
     * @return O ambiente na direcao especificada.
     */
    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    /**
     * @return A descricao das saidas do ambiente.
     */
    public String getSaidaString() {
        String saidaString = "Saidas: ";
        Set<String> chaves = saidas.keySet();
        for (String saida : chaves) {
            saidaString += saida + " ";
        }

        return saidaString;
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * @return A descricao completa do ambiente, incluindo as saidas.
     */
    public String getDescricaoCompleta() {
        return "Voce esta " + getDescricao() + ".\n" + getSaidaString();
    }

    public void adicionarPendrives(TrioPendrives trio) {
        pendrives.add(trio.getP1());
        pendrives.add(trio.getP2());
        pendrives.add(trio.getP3());
    }

    public void showPendrives() {
        System.out.println("Pendrives: ");
        for(int i = 0; i < pendrives.size(); i++) {
            System.out.println("Pendrive " + (i + 1));
        }
    }
}
