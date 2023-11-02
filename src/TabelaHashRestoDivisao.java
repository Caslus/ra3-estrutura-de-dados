public class TabelaHashRestoDivisao extends TabelaHash {
    public TabelaHashRestoDivisao(int tamanhoVetor) {
        super(tamanhoVetor);
    }

    @Override
    int h(int chave) {
        Hash hash = new Hash();
        return hash.restoDivisao(chave, tabela.length);
    }
}