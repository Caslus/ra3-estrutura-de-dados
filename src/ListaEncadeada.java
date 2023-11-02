public class ListaEncadeada {
    private Registro inicio;

    public ListaEncadeada(Registro registro) {
        this.inicio = registro;
    }

    public void inserir(int chave) {
        if (this.inicio == null) {
            this.inicio = new Registro(chave);
            return;
        }
        Registro temp = this.inicio;
        while (temp.getProximo() != null) {
            temp = temp.getProximo();
        }
        temp.setProximo(new Registro(chave));
    }

    public int buscar(int chave) {
        if (this.inicio == null) {
            return -1;
        }
        Registro temp = this.inicio;
        while (temp.getCodigo() != chave) {
            if (temp.getProximo() == null) {
                return -1;
            }
            temp = temp.getProximo();
        }
        return temp.getCodigo();
    }
}
