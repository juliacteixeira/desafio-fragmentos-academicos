public enum DisciplinaNome {
    PPOO("ppoo"), LFA("lfa"), GRAFOS("grafos"), SGBD("sgbd");
    private String nomeDisciplina;

    DisciplinaNome(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String toString() {
        return nomeDisciplina;
    }
}
