/**
 * Essa classe contem as informacoes de uma disciplina.
 * Cada disciplina possui um nome e eh composta por um trabalho.
 * 
 * @author  João Dias
 * @version 2023.11.23
 */
public class Disciplina {
    private String nome;
    private Trabalho trabalho;

    /**
     * Crie e inicializa uma disciplina com o nome passado por parametro.
     * @param nome nome da disciplina.
     */
    public Disciplina(String nome) {
        this.nome = nome;
    }

    /**
     * @return nome
     * Retorna o nome da disciplina.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return Trabalho
     * Retorna o trabalho da disciplina.
     */
    public Trabalho getTrabalho() {
        return trabalho;
    }
}
