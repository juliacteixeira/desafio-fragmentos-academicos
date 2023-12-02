import java.util.List;

/**
 * Essa classe representa um jogador.
 * Ela é composta por um ambiente atual e por uma lista de disciplinas.
 * Essa lista de disciplinas é como o inventário do jogador.
 *
 * @author Caio Souza and João Dias
 * @version 2023.11.23
 */
public class Jogador {
    private Ambiente ambienteAtual;
    private Disciplinas inventarioDisciplinas;
    private InventarioDisciplinasGUI observador;

    /**
     * Instancia o inventário de disciplinas do jogador.
     */
    public Jogador() {
        inventarioDisciplinas = new Disciplinas();
    }

    public void adicionarObservador(InventarioDisciplinasGUI observador) {
        this.observador = observador;
    }

    public void notificarObservadores() {
        observador.atualizarInventarioGUI();
    }

    public void mudouInventario() {
        notificarObservadores();
    }

    /**
     * Retorna as disciplinas que o jogador possui em seu inventário.
     *
     * @return List<Disciplina> Lista de disciplinas do inventário do jogador.
     */
    public List<Disciplina> getDisciplinas() {
        return inventarioDisciplinas.getDisciplinas();
    }

    /**
     * Retorna o ambiente atual do jogador.
     *
     * @return Ambiente atual do jogador.
     */
    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    /**
     * Altera o ambiente atual do jogador.
     *
     * @param ambienteAtual Novo ambiente atual do jogador.
     */
    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    /**
     * Exibe o inventário de Disciplinas do jogador. Com o nome da disciplina, a quantidade de fragmentos que ela possui.
     */
    public String statusDisciplinas() {
        StringBuilder status = new StringBuilder("<html>");

        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            String disciplinaNome = disciplina.getNome();

            Trabalho trabalhoDisciplina = disciplina.getTrabalho();

            // Verifica se o trabalho é nulo antes de acessar seus métodos
            if (trabalhoDisciplina != null) {
                int fragmentosMax = trabalhoDisciplina.getFragmentosMax();
                int fragmentos = trabalhoDisciplina.getFragmentos();

                if (trabalhoDisciplina.estaCompleto()) {
                    status.append(String.format("%s : %d/%d - %s<br>",
                            disciplinaNome, fragmentos, fragmentosMax, "Completo"));
                } else {
                    status.append(String.format("%s : %d/%d - %s<br>",
                            disciplinaNome, fragmentos, fragmentosMax, "Incompleto"));
                }
            }
        }

        status.append("<br> </html>");
        return status.toString();
    }
    
    public boolean disciplinasCompleta() {
        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            Trabalho trabalho = disciplina.getTrabalho();
    
            // Verifica se o trabalho é nulo ou não está completo
            if (trabalho == null || !trabalho.estaCompleto()) {
                return false;
            }
        }
        return true;
    }
}


            

