import java.util.Collections;
import java.util.List;

/**
 * Essa classe representa um jogador.
 * Ela eh composta por um ambiente atual e por uma lista de disciplinas.
 * Essa lista de disciplinas eh como o invetario do jogador.
 *
 * @author Caio Souza
 * @version 2023.11.23
 */

//Lembrar de colocar "Jogador implements RelatorioDesempenho"
public class Jogador {
//  private List<Pendrive> pendrivesColetados;
    private Ambiente ambienteAtual;
    private Disciplinas inventarioDisciplinas;

    /**
     * Instancia o invetario de disciplinas do jogador.
     */
    public Jogador() {
        inventarioDisciplinas = new Disciplinas();
    }

    /**
     * Retorna as disciplinas que o jogador possui em seu inventario.
     * @return List<Disciplina>  Lista de disciplinas do inventario do jogador.
     */
    public List<Disciplina> getDisciplinas() {
        return inventarioDisciplinas.getDisciplinas();
    }

    /**
     * Retorna o ambiente atual do jogador.
     * @return Ambiente atual do jogador.
     */
    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    /**
     * Altera o ambiente atual do jogador.
     * @param ambienteAtual Novo ambiente atual do jogador.
     */
    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    /**
     * Exibe o inventario de Disciplinas do jogador. Com o nome da disciplina, a quantidade de fragmentos que ela possui.
     */
    public void statusDisciplinas() {
        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            String disciplinaNome = disciplina.getNome();

            Trabalho trabalhoDisciplina = disciplina.getTrabalho();
            int fragmentosMax = trabalhoDisciplina.getFragmentosMax();
            int framgentos = trabalhoDisciplina.getFragmentos();

            if(trabalhoDisciplina.estaCompleto()) {
                System.out.printf("%s : %d/%d - %s\n",
                        disciplinaNome, fragmentosMax, fragmentosMax, "Completo");
            } else {
                System.out.printf("%s : %d/%d - %s\n",
                        disciplinaNome, framgentos, fragmentosMax, "Incompleto");
            }
        }
    }

    public boolean disciplinasCompleta() {
        boolean status = true;
        for (Disciplina disciplina : inventarioDisciplinas.getDisciplinas()) {
            if(!disciplina.getTrabalho().estaCompleto()) {
                status = false;
            }
        }
        
        return status;
    }

// Futura implementação dos métodos de RelatorioDesempenho, para a interface gráfica 

//  @Override
//  public List<Pendrive> getPendrivesColetados() {
//    return Collections.unmodifiableList(pendrivesColetados);
//  }
    
//  @Override
//  public int getTempoGasto() {
//    int tempoAtual = tempoJogo.getTempoJogo();  // Supondo que você tenha uma instância de Tempo chamada tempoDoJogo
//    int tempoInicial = tempoInicialDoJogo;  // Substitua isso com o tempo inicial do jogo (em minutos)

//    return tempoAtual - tempoInicial;
//  }
    }
            

