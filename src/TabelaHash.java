public abstract class TabelaHash {
    protected ListaEncadeada[] tabela;
    private int colisoes;

    public TabelaHash(int tamanhoVetor) {
        this.tabela = new ListaEncadeada[tamanhoVetor];
    }

    abstract int h(int chave);

    public void inserir(int chave) {
        Registro registro = new Registro(chave);
        int i = h(registro.getCodigo());
        if (tabela[i] == null) {
            tabela[i] = new ListaEncadeada(registro);
            return;
        }
        colisoes++;
        tabela[i].inserir(registro.getCodigo());
    }

    public int buscar(int chave) {
        int i = h(chave);
        if (tabela[i] == null) {
            return -1;
        }
        if (tabela[i].buscar(chave) == chave) {
            return i;
        }
        return -1;
    }

    public int getColisoes() {
        return colisoes;
    }
}
