public class TabelaHashDobramento extends TabelaHash {
    public TabelaHashDobramento(int tamanhoVetor) {
        super(tamanhoVetor);
    }

    @Override
    int h(int chave) {
        Hash hash = new Hash();
        return hash.dobramento(chave, tabela.length);
    }
}