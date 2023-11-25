import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Essa classe tem o objetivo de criar todos os pendrives do jogo.
 * Ela recebe uma lista de disciplinas, a quantidade de ambientes e o tempo do jogo.
 * Em seu constrtutor ela desconsidera o ambiente fora, cria os pendrives de cada disciplina e os pendrives de tempo. Por fim, ela
 * embaralha os pendrives e os agrupa em trios.
 *
 @author  Caio Souza
  * @version 2023.11.23
 */


public class Pendrives {
    private final int QUANTIDADE_PENDRIVE_POR_AMBIENTE = 3;
    private ArrayList<TrioPendrives> trioPendrives;
    private Random random;

    /**
     * Descondira o ambiente fora, cria os pendrives de cada disciplina e os pendrives de tempo. Por fim,
     * ela embaralha os pendrives e os agrupa em trios.
     *
     * @param disciplinas A lista de disciplinas do jogo.
     * @param quantidadeAmbientes A quantidade de ambientes do jogo.
     * @param tempo O objeto Tempo que eh usado no Jogo.
     */
    public Pendrives(List<Disciplina> disciplinas, int quantidadeAmbientes, Tempo tempo) {
        quantidadeAmbientes--; // Descondira o ambiente fora

        trioPendrives = new ArrayList<>();
        ArrayList<Pendrive> pendrives = new ArrayList<>();
        random = new Random();

        // Cria 2 pendrives para cada disciplina
        for (Disciplina disciplina : disciplinas) {
            pendrives.add(new PendriveDisciplina(disciplina));
            pendrives.add(new PendriveDisciplina(disciplina));
        }

        // Calcula a quantidade de pendrives de tempo necessários
        int quantidadePendrivesTempo = (quantidadeAmbientes * QUANTIDADE_PENDRIVE_POR_AMBIENTE - pendrives.size());

        // Cria os pendrives de tempo
        for (int i = 0; i < quantidadePendrivesTempo / 2; i++) {
            pendrives.add(new PendriveTempo(-5, tempo));
            pendrives.add(new PendriveTempo(10, tempo));
        }

        // Se a quantidade de pendrives de tempo for impar, adiciona um pendrive de tempo extra
        if (quantidadePendrivesTempo % 2 != 0) {
            pendrives.add(new PendriveTempo(-5, tempo));
        }

        // Embaralha os pendrives
        Collections.shuffle(pendrives, random);

        // Cria um trio de pendrives para cada ambiente
        for (int i = 0; i < pendrives.size(); i += 3) {
            Pendrive p1 = pendrives.get(i);
            Pendrive p2 = pendrives.get(i + 1);
            Pendrive p3 = pendrives.get(i + 2);
            trioPendrives.add(new TrioPendrives(p1, p2, p3));
        }
    }

    /**
     * Retorna a lista de trios de pendrives. Eh retornado uma lista imutavel.
     *
     * @return A lista de trios de pendrives.
     */
    public List<TrioPendrives> getTrioPendrives() {
        return Collections.unmodifiableList(trioPendrives);
    }
}
