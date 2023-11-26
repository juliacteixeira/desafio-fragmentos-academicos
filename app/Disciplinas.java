import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Essa classe eh responsavel por criar as disciplinas.
 * Ela eh como o inventario de disciplinas do jogador, pois armazena as disciplinas e controla a quantidade de fragmentos
 * de cada uma, dentro do HashMap disciplinas.
 * Assim como em comandos, percorremos um Enum de DisciplinaNome para criar as mesmas, isso facilita a criacao de novas disciplinas.
 *
 * @author Caio Souza
 * @version 2023.11.23
 */

public class Disciplinas {
    private HashMap<DisciplinaNome, Disciplina> disciplinas;

    /**
     * Cria e inicializa as disciplinas baseado no Enum de DisciplinaNome.
     */
    public Disciplinas() {
        disciplinas = new HashMap<>();

        for (DisciplinaNome disciplina : DisciplinaNome.values()) {
            String disciplinaString = disciplina.toString();
            disciplinas.put(disciplina, new Disciplina(disciplinaString));
        }
    }

    /**
     * Retorna somente as disciplinas criadas.
     * @return List<Disciplina> retorna uma lista de disciplinas.
     */
    public List<Disciplina> getDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }
}
