/**
 * Essa eh uma superclasse abstrata que representa um pendrive.
 * O metodo abrir eh abstrado, pois depende do tipo de pendrive. Isso permite que as subclasses
 * implementem o metodo de acordo com o tipo de pendrive.
 * @author  João Dias
 * @version 2023.11.23
 */
import java.util.ArrayList;
import java.util.List;
/**
 * Classe abstrata que representa um pendrive.
 */
public abstract class Pendrive {
    private boolean aberto;
    private List<PendriveListener> listeners = new ArrayList<>();

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
     * Adiciona um ouvinte para eventos de pendrive.
     *
     * @param listener O ouvinte a ser adicionado.
     */
    public void addPendriveListener(PendriveListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifica os ouvintes quando um pendrive é aberto.
     */
    protected void notifyPendriveAberto() {
        for (PendriveListener listener : listeners) {
            listener.onPendriveAberto(this);
        }
    }

    /**
     * Altera o estado do pendrive.
     *
     * @param aberto true se o pendrive estiver aberto, false caso contrário.
     */
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
        if (aberto) {
            notifyPendriveAberto();
        }
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

