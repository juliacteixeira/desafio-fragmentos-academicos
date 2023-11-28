/**
 * Essa eh uma superclasse abstrata que representa um pendrive.
 * O metodo abrir eh abstrado, pois depende do tipo de pendrive. Isso permite que as subclasses
 * implementem o metodo de acordo com o tipo de pendrive.
 * @author  Caio Souza and João Dias
 * @version 2023.11.23
 */
/**
 * Classe abstrata que representa um pendrive.
 */
public abstract class Pendrive {
    private boolean aberto;
    

    /**
     * Cria e inicializa o pendrive como fechado.
     */
    public Pendrive() {
        this.aberto = false;
    }

    /**
     * Abre o pendrive de acordo com o tipo de pendrive.
     */
    public abstract void abrir();


    /**
     * Altera o estado do pendrive.
     *
     * @param aberto true se o pendrive estiver aberto, false caso contrário.
     */
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    /**
     * Verifica se o pendrive está aberto.
     *
     * @return true se o pendrive estiver aberto, false caso contrário.
     */
    public boolean estaAberto() {
        return aberto;
    }
}

