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
        Ambiente dispensa, quintal, salaDeJantar, cozinha, meuQuarto,
                salaDeTv, deposito, garagem, corredor, quartoDosPais,
                banheiroDosPais, banheiroPrincipal, oficina;

        // cria os ambientes
        dispensa = new Ambiente("na dispensa, um labirinto de latas de feijão e pacotes de macarrão");
        quintal = new Ambiente("no quintal, onde os gnomos de jardim ganham vida à noite");
        salaDeJantar = new Ambiente("na sala de jantar, onde os talheres dançam no ritmo do relógio cuco");
        cozinha = new Ambiente("na cozinha, onde as panelas sussurram segredos culinários");
        meuQuarto = new Ambiente("no quarto de Zephyrion, um santuário de sonhos e meias perdidas");
        salaDeTv = new Ambiente("na sala de TV, onde os personagens das séries saem da tela nas madrugadas");
        deposito = new Ambiente("no deposito, onde tudo pode ter");
        garagem = new Ambiente("na garagem, lar do carro que sonha em ser uma nave espacial");
        corredor = new Ambiente("no corredor, uma pista de corrida para os fantasmas da casa");
        quartoDosPais = new Ambiente("no quarto dos pais, um oásis de tranquilidade e sabedoria");
        banheiroDosPais = new Ambiente("no banheiro dos pais, onde o sabonete conta histórias de espuma");
        banheiroPrincipal = new Ambiente("no banheiro principal, um palco para concertos de chuveiro");
        oficina = new Ambiente("na oficina, onde ferramentas dançam ao som de marteladas");

        // ajusta as saidas dos ambientes
        dispensa.ajustarSaida("leste", cozinha);
        quintal.ajustarSaida("norte", salaDeJantar);
        quintal.ajustarSaida("leste", deposito);
        deposito.ajustarSaida("oeste", quintal);
        salaDeJantar.ajustarSaida("norte", cozinha);
        salaDeJantar.ajustarSaida("leste", salaDeTv);
        salaDeJantar.ajustarSaida("sul", quintal);
        cozinha.ajustarSaida("oeste", dispensa);
        cozinha.ajustarSaida("sul", salaDeJantar);
        salaDeTv.ajustarSaida("oeste", salaDeJantar);
        salaDeTv.ajustarSaida("norte", meuQuarto);
        salaDeTv.ajustarSaida("leste", corredor);
        meuQuarto.ajustarSaida("sul", salaDeTv);
        corredor.ajustarSaida("oeste", salaDeTv);
        corredor.ajustarSaida("norte", quartoDosPais);
        corredor.ajustarSaida("leste", banheiroPrincipal);
        corredor.ajustarSaida("sul", garagem);
        garagem.ajustarSaida("norte", corredor);
        garagem.ajustarSaida("leste", oficina);
        oficina.ajustarSaida("oeste", garagem);
        banheiroPrincipal.ajustarSaida("oeste", corredor);
        quartoDosPais.ajustarSaida("sul", corredor);
        quartoDosPais.ajustarSaida("leste", banheiroDosPais);
        banheiroDosPais.ajustarSaida("oeste", quartoDosPais);

        // adiciona os ambientes no hashmap
        ambientes.put("dispensa", dispensa);
        ambientes.put("quintal", quintal);
        ambientes.put("salaDeJantar", salaDeJantar);
        ambientes.put("cozinha", cozinha);
        ambientes.put("meuQuarto", meuQuarto);
        ambientes.put("salaDeTv", salaDeTv);
        ambientes.put("deposito", deposito);
        ambientes.put("garagem", garagem);
        ambientes.put("corredor", corredor);
        ambientes.put("quartoDosPais", quartoDosPais);
        ambientes.put("banheiroDosPais", banheiroDosPais);
        ambientes.put("banheiroPrincipal", banheiroPrincipal);
        ambientes.put("oficina", oficina);
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