import java.util.List;

public class Jogador {
    private Ambiente ambienteAtual;
    private Disciplinas disciplinas;

    public Jogador() {

        disciplinas = new Disciplinas();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas.getDisciplinas();
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }
}
