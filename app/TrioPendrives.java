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
        return p1.clone();
    }

    public Pendrive getP2() {
        return p2.clone();
    }

    public Pendrive getP3() {
        return p3.clone();
    }
}
