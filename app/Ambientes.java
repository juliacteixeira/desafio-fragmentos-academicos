/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ambientes {
    private HashMap<String, Ambiente> ambientes;
    private Pendrives pendrives;

    public Ambientes(List<Disciplina> disciplinas) {
        ambientes = new HashMap<>();
        criarAmbientes();

        pendrives = new Pendrives(disciplinas, ambientes.size());
        espalharPendrives();
    }

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

    private void espalharPendrives() {
        List<TrioPendrives> trioPendrives = pendrives.getTrioPendrives();
        List<Ambiente> listaAmbientes = new ArrayList<>(ambientes.values());

        for (int i = 0; i < listaAmbientes.size(); i++) {
            Ambiente ambiente = listaAmbientes.get(i);
            TrioPendrives trio = trioPendrives.get(i);
            ambiente.adicionarPendrives(trio);
        }

    }
}*/
