/**
 * Interface para ouvintes de eventos relacionados a pendrives.
 */
public interface PendriveListener {
    /**
     * Método chamado quando um pendrive é aberto.
     *
     * @param pendrive O pendrive que foi aberto.
     */
    void onPendriveAberto(Pendrive pendrive);
}
