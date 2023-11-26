/**
 * Essa classe representa um trio de Pendrives.
 * Poderia ser feito usando Enum?
 *
 * @author Caio Souza
 * @version 2023.11.23
 */
public class TrioPendrives {
    private Pendrive p1;
    private Pendrive p2;
    private Pendrive p3;

    public TrioPendrives(Pendrive p1, Pendrive p2, Pendrive p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Pendrive getP1() {
        return p1;
    }

    public Pendrive getP2() {
        return p2;
    }

    public Pendrive getP3() {
        return p3;
    }
}
