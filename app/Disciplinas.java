import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Disciplinas {
    private HashMap<DisciplinaNome, Disciplina> disciplinas;

    public Disciplinas() {
        disciplinas = new HashMap<>();

        for (DisciplinaNome disciplina : DisciplinaNome.values()) {
            String disciplinaString = disciplina.toString();
            disciplinas.put(disciplina, new Disciplina(disciplinaString));
        }
    }

    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }
}
