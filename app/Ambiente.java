import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Essa classe representa um abiente do jogo.
 * Ela eh composta por uma descricao e por saidas para outros ambientes.
 * Ela tambem eh composta por uma lista de pendrives, pois os pendrives existem somente dentro de um ambiente.
 *
 * @author  Caio Souza 
 * @version 2023.11.23
 */

public class Ambiente {
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private ArrayList<Pendrive> pendrives;

    /**
     * Cria um ambiente com a descricao passada por parametro.
     * Inicializa a lista de saidas e a lista de pendrives.
     * @param descricao A descricao do ambiente.
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        this.descricao = descricao;
        saidas = new HashMap<>();
        pendrives = new ArrayList<>();
    }

    /**
     * Ajusta uma saida para o ambiente.
     * @param direcao A direcao da saida.
     * @param ambiente O ambiente para onde a saida leva.
     */
    public void ajustarSaida(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }

    /**
     * Retorna o ambiente para onde a saida leva.
     * @param direcao A direcao da saida.
     * @return O ambiente para onde a saida leva.
     */
    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    /**
     * Retorna uma string com todas as saidas do ambiente.
     * @return Uma string com todas as saidas do ambiente.
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
     * Retorna a descricao do ambiente.
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Retorna a descricao completa do ambiente. Sua Descricao com suas saidas.
     * @return A descricao completa do ambiente.
     */
    public String getDescricaoCompleta() {
        return "Voce esta " + getDescricao() + ".\n" + getSaidaString();
    }

    /**
     * Adiciona um pendrive a lista de pendrives do ambiente.
     * @param trio O objeto contendo o trio de pendrives a ser adicionado no ambiente.
     */
    public void adicionarPendrives(TrioPendrives trio) {
        pendrives.add(trio.getP1());
        pendrives.add(trio.getP2());
        pendrives.add(trio.getP3());
    }

    /**
     * Verifica se a posicao passada por parametro existe na lista de pendrives.
     * @param index A posicao do pendrive.
     * @return True caso haja algum pendrive na posicao passada por parametro.
     */
    public boolean temPendrive(int index) {
        return index - 1 < pendrives.size();
    }

    /**
     * Retorna o pendrive na posicao passada por parametro.
     * @param index A posicao do pendrive.
     * @return O pendrive na posicao passada por parametro.
     */
    public Pendrive getPendrive(int index) {
        return pendrives.get(index - 1);
    }

    /**
     * Exibe todos os pendrives disponiveis no ambiente.
     * Verifica se todos os pendrives ja foram abertos.
     */
    public void showPendrives() {
        int quantidadePendrives = pendrives.size();
        boolean todosAbertos = true;

        if(quantidadePendrives == 0) {
            System.out.println("Nao ha pendrives nesse ambiente!");
        } else {
            String pendrivesAbertos = "";

            for(int i = 0; i < quantidadePendrives; i++) {
                Pendrive pendrive = pendrives.get(i);
                if(!pendrive.estaAberto()) {
                    todosAbertos = false;
                    pendrivesAbertos += "Pendrive " + (i + 1) + "\n";
                }
            }

            System.out.printf("%s", todosAbertos ? "Todos os pendrives ja foram abertos!\n" : "Pendrives: \n" + pendrivesAbertos);
        }
    }
}
