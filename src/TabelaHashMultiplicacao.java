public class TabelaHashMultiplicacao extends TabelaHash {
    public TabelaHashMultiplicacao(int tamanhoVetor) {
        super(tamanhoVetor);
    }

    @Override
    int h(int chave) {
        Hash hash = new Hash();
        return hash.multiplicacao(chave, tabela.length);
    }
}