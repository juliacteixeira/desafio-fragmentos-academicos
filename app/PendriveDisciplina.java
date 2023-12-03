/**
 * Essa classe representa um pendrive de disciplina.
 * Ela recebe uma disciplina e, ao ser aberto, adiciona um fragmento de trabalho a disciplina.
 * Antes da adição do fragmento, é verificado se o trabalho já está completo. Caso esteja, o fragmento não é adicionado.
 *
 * @author  Caio Souza and João Dias
 * @version 2023.11.23
 */
public class PendriveDisciplina extends Pendrive {
    private Disciplina disciplina;

    /**
     * @param disciplina A disciplina que o pendrive pertence.
     */
    public PendriveDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Abre o Pendrndrive da Disciplina na qual ele pertence.
     * Muda o atributo aberto para true e adiciona um fragmento de trabalho a disciplina, caso o trabalho não esteja completo.
     */
    public void abrir() {
        super.setAberto(true);
        Trabalho disciplinaTrabalho = disciplina.getTrabalho();

        if(!disciplinaTrabalho.estaCompleto()) {
            disciplinaTrabalho.setFragmentos(disciplinaTrabalho.getFragmentos() + 1);
        }
    }
}

