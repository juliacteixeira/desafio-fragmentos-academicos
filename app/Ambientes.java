import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Essa classe representa os ambientes do jogo.
 * Primeiro, criamos os ambientes com suas respectivas saidas e sua descricao.
 * A quantidade de Pendrives eh calculada de acordo com a quantidade de ambientes.
 * Apos a criacao dos Pendrives, eles sao espalhados pelos ambientes.
 * Como o ambiente "fora" nao possui Pendrives, ele eh removido da lista de ambientes.
 *
 * @author Caio Souza
 * @version 2023.11.23
 */

public class Ambientes {
    private HashMap<String, Ambiente> ambientes;
    private Pendrives pendrives;

    /**
     * Cria os ambientes.
     * Instancia os Pendrives passando as disciplinas, a quantidade de ambientes e o tempo.
     * @param disciplinas
     * @param tempo
     */
    public Ambientes(List<Disciplina> disciplinas, Tempo tempo) {
        ambientes = new HashMap<>();
        criarAmbientes();

        pendrives = new Pendrives(disciplinas, ambientes.size(), tempo);
        espalharPendrives();
    }

    /**
     * Cria os ambientes e ajusta as saidas.
     */
    private void criarAmbientes() {
        Ambiente fora, anfiteatro, cantina, laboratorio, escritorio;

        // cria os ambientes
        fora = new Ambiente("do lado de fora da entrada principal de uma universidade");
        anfiteatro = new Ambiente("no anfiteatro");
        cantina = new Ambiente("na cantina do campus");
        laboratorio = new Ambiente("no laboratorio de computacao");
        escritorio = new Ambiente("na sala de administracao dos computadores");

        // inicializa as saidas dos ambientes
        fora.ajustarSaida("leste", anfiteatro);
        fora.ajustarSaida("sul", laboratorio);
        fora.ajustarSaida("oeste", cantina);
        anfiteatro.ajustarSaida("oeste", fora);
        cantina.ajustarSaida("leste", fora);
        laboratorio.ajustarSaida("norte", fora);
        laboratorio.ajustarSaida("leste", escritorio);
        escritorio.ajustarSaida("oeste", laboratorio);

        // adiciona os ambientes ao mapa
        ambientes.put("fora", fora);
        ambientes.put("anfiteatro", anfiteatro);
        ambientes.put("cantina", cantina);
        ambientes.put("laboratorio", laboratorio);
        ambientes.put("escritorio", escritorio);
    }

    /**
     * Apos a criação dos Pendrives, eles sao espalhados pelos ambientes.
     * Como o ambiente "fora" nao possui Pendrives, ele eh removido da lista de ambientes.
     */
    private void espalharPendrives() {
        List<TrioPendrives> trioPendrives = pendrives.getTrioPendrives();
        List<Ambiente> listaAmbientes = new ArrayList<>(ambientes.values());

        listaAmbientes.remove(ambientes.get("fora"));

        for (int i = 0; i < listaAmbientes.size(); i++) {
            Ambiente ambiente = listaAmbientes.get(i);
            TrioPendrives trio = trioPendrives.get(i);
            ambiente.adicionarPendrives(trio);
        }
    }

    /**
     * Retorna o ambiente com o nome passado por parametro.
     * @param nomeAmbiente O nome do ambiente.
     * @return O ambiente com o nome passado por parametro.
     */
    public Ambiente getAmbiente(String nomeAmbiente) {
        return ambientes.get(nomeAmbiente);
    }
}
