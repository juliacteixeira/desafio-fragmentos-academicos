import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Essa classe representa um jogador.
 * Ela é composta por um ambiente atual e por uma lista de disciplinas.
 * Essa lista de disciplinas é como o inventário do jogador.
 *
 * @author Caio Souza and João Dias
 * @version 2023.11.23
 */
public class Jogador implements RelatorioDesempenho {
    private Ambiente ambienteAtual;
    private Disciplinas inventarioDisciplinas;
    private long tempoInicial;

    /**
     * Instancia o inventário de disciplinas do jogador.
     */
    public Jogador() {
        inventarioDisciplinas = new Disciplinas();
        tempoInicial = System.currentTimeMillis();
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
    public void statusDisciplinas() {
        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            String disciplinaNome = disciplina.getNome();
    
            Trabalho trabalhoDisciplina = disciplina.getTrabalho();
    
            // Verifica se o trabalho é nulo antes de acessar seus métodos
            if (trabalhoDisciplina != null) {
                int fragmentosMax = trabalhoDisciplina.getFragmentosMax();
                int fragmentos = trabalhoDisciplina.getFragmentos();
    
                if (trabalhoDisciplina.estaCompleto()) {
                    System.out.printf("%s : %d/%d - %s\n",
                            disciplinaNome, fragmentosMax, fragmentosMax, "Completo");
                } else {
                    System.out.printf("%s : %d/%d - %s\n",
                            disciplinaNome, fragmentos, fragmentosMax, "Incompleto");
                }
            }
        }
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
    

    /**
     * Cria uma lista com os trabalhos completos, a serem mostrados na interface gráfica.
     */
    @Override
    public List<Disciplina> getTrabalhosColetados() {
        List<Disciplina> trabalhosConcluídos = new ArrayList<>();

        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            Trabalho trabalhoDisciplina = disciplina.getTrabalho();

            if (trabalhoDisciplina.estaCompleto()) {
                trabalhosConcluídos.add(disciplina);
            }
        }

        return Collections.unmodifiableList(trabalhosConcluídos);
    }

    /**
     * Pega o tempo gasto para finalizar o jogo, a ser mostrado na interface gráfica.
     */
    @Override
    public int getTempoGasto() {
        long tempoAtual = System.currentTimeMillis();
        long tempoGastoMilissegundos = tempoAtual - tempoInicial;
        int tempoGastoMinutos = (int) (tempoGastoMilissegundos / 60000);  // Converter milissegundos para minutos
        return tempoGastoMinutos;
    }

}


            

