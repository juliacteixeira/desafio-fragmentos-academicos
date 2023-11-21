public enum PalavraComando {
    IR("ir"), SAIR("sair"), AJUDA("ajuda"), DESCONHECIDO("?"), OLHAR("olhar");
    private String palavraComando;

    /**
     * Inicializa com a string de comando correspondente.
     * @param palavraComando A palavra de comando.
     */
    PalavraComando(String palavraComando) {
        this.palavraComando = palavraComando;
    }

    /**
     * @return A palavra de comando como uma string.
     */
    public String toString()
    {
        return palavraComando;
    }
}
