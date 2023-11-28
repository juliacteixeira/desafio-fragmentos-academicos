/**
 * Essa classe faz a criacao do trabalho de cada Disciplina. Pois nesse contexto temos que cada Disciplina eh composta por um trabalho.
 * Definimos uma variavel FRAGMENTOS_MAX que eh a quantidade maxima de fragmentos que um trabalho pode ter, podendo ser facilmente alterada.
 * Pra setar uma quantidade de fragmentos, usamos o metodo setFragmentos, que verifica se o trabalho esta completo e se a quantidade de fragmentos
 * eh menor ou igual a FRAGMENTOS_MAX.
 *
 * @author  João Dias
 * @version 2023.11.23
 */
public class Trabalho {
    private int fragmentos;
    private Disciplina disciplina;
    private final int FRAGMENTOS_MAX = 2;

    /**
     * Crie e inicializa a quantidade de fragmentos com 0.
     */
    public Trabalho(Disciplina disciplina) {
        this.disciplina = disciplina;
        this.fragmentos = 0;
    }
    /**
     * @return disciplina.
     */
    public Disciplina getDisciplina(){
        return disciplina;
    }


    /**
     * @return fragmentos quantidade de fragmentos que o trabalho tem.
     */
    public int getFragmentos() {
        return fragmentos;
    }

    /**
     * @return FRAGMENTOS_MAX quantidade maxima de fragmentos que um trabalho pode ter.
     */
    public int getFragmentosMax() {
        return FRAGMENTOS_MAX;
    }

    /**
     * @return true se o trabalho estiver completo, false caso contrario.
     */
    public boolean estaCompleto() {
        return fragmentos == FRAGMENTOS_MAX;
    }

    /**
     * @param fragmentos quantidade de fragmentos que o trabalho tera.
     * Faz a verificacao se o trabalho esta completo e se a quantidade de fragmentos eh menor ou igual a FRAGMENTOS_MAX.
     */
    public void setFragmentos(int fragmentos) {
        if(!estaCompleto() && fragmentos <= FRAGMENTOS_MAX) {
            this.fragmentos = fragmentos;
        }
    }
}

