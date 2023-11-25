import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pendrives {
    private final int QUANTIDADE_PENDRIVE_POR_AMBIENTE = 3;
    private ArrayList<TrioPendrives> trioPendrives;
    private Random random;

    public Pendrives(List<Disciplina> disciplinas, int quantidadeAmbientes) {
        trioPendrives = new ArrayList<>();
        quantidadeAmbientes--;

        ArrayList<Pendrive> pendrives = new ArrayList<>();
        random = new Random();

        // Cria 2 pendrives para cada disciplina
        for (Disciplina disciplina : disciplinas) {
            pendrives.add(new PendriveDisciplina(disciplina, false));
            pendrives.add(new PendriveDisciplina(disciplina, false));
        }

        // Calcula a quantidade de pendrives de tempo necessários e remove os pendrives para o ambiente de fora.
        int quantidadePendrivesTempo = (quantidadeAmbientes * QUANTIDADE_PENDRIVE_POR_AMBIENTE - pendrives.size());

        // Cria os pendrives de tempo
        for (int i = 0; i < quantidadePendrivesTempo / 2; i++) {
            pendrives.add(new PendriveTempo(false, -5));
            pendrives.add(new PendriveTempo(false, 10));
        }

        // Se a quantidade de pendrives de tempo for impar, adiciona um pendrive de tempo extra
        if (quantidadePendrivesTempo % 2 != 0) {
            pendrives.add(new PendriveTempo(false, -5));
        }

        Collections.shuffle(pendrives, random);

        for (int i = 0; i < pendrives.size(); i += 3) {
            Pendrive p1 = pendrives.get(i);
            Pendrive p2 = pendrives.get(i + 1);
            Pendrive p3 = pendrives.get(i + 2);
            trioPendrives.add(new TrioPendrives(p1, p2, p3));
        }
    }

    public List<TrioPendrives> getTrioPendrives() {
        return Collections.unmodifiableList(trioPendrives);
    }
}
