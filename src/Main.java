import java.util.Locale;
import java.util.Random;

public class Main {
    public enum Tipo {
        RESTODIVISAO, MULTIPLICACAO, DOBRAMENTO
    }

    static Random random = new Random(40028922);

    public static int[] preencherConjunto(int tamanho) {
        int[] conjunto = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            conjunto[i] = 100000000 + random.nextInt(900000000);
        }
        return conjunto;
    }

    public static int numeroBusca(int[] conjunto) {
        return conjunto[random.nextInt(conjunto.length)];
    }

    public static TabelaHash popularTabelaHash(Tipo tipo, int tamanhoVetor, int[] conjunto) {
        TabelaHash tabelaHash;
        switch (tipo) {
            case RESTODIVISAO -> tabelaHash = new TabelaHashRestoDivisao(tamanhoVetor);
            case MULTIPLICACAO -> tabelaHash = new TabelaHashMultiplicacao(tamanhoVetor);
            case DOBRAMENTO -> tabelaHash = new TabelaHashDobramento(tamanhoVetor);
            default -> {
                return null;
            }
        }
        long start = System.nanoTime();
        for (int chave : conjunto) {
            tabelaHash.inserir(chave);
        }
        long finish = System.nanoTime();
        float elapsed = (float) (finish - start) / 1000000000;
        System.out.printf(Locale.UK, "%s,%d,%d,%d,%.6f\n", tipo, tamanhoVetor, conjunto.length, tabelaHash.getColisoes(), elapsed);
        return tabelaHash;
    }

    public static void main(String[] args) {
        int[] tamanhosVetor = {500, 5000, 50000, 500000, 5000000};
        int[] k2 = preencherConjunto(20000);
        int[] k100 = preencherConjunto(100000);
        int[] k500 = preencherConjunto(500000);
        int[] m1 = preencherConjunto(1000000);
        int[] m5 = preencherConjunto(5000000);
        int[][] conjuntos = {k2, k100, k500, m1, m5};
        int numeroBusca2k = numeroBusca(k2);
        int numeroBusca100k = numeroBusca(k100);
        int numeroBusca500k = numeroBusca(k500);
        int numeroBusca1m = numeroBusca(m1);
        int numeroBusca5m = numeroBusca(m5);
        int[] numerosBuscaArray = {numeroBusca2k, numeroBusca100k, numeroBusca500k, numeroBusca1m, numeroBusca5m};
        String[] nomes = {"BUSCA 2K", "BUSCA 100K", "BUSCA 500K", "BUSCA 1M", "BUSCA 5M"};

//        ------------------------------------------

        TabelaHash[] restoDivisao = new TabelaHash[5];
        for (int i = 0; i < tamanhosVetor.length; i++) {
            for (int[] conjunto : conjuntos) {
                restoDivisao[i] = popularTabelaHash(Tipo.RESTODIVISAO, tamanhosVetor[i], conjunto);
            }
        }

        TabelaHash[] multiplicacao = new TabelaHash[5];
        for (int i = 0; i < tamanhosVetor.length; i++) {
            for (int[] conjunto : conjuntos) {
                multiplicacao[i] = popularTabelaHash(Tipo.MULTIPLICACAO, tamanhosVetor[i], conjunto);
            }
        }

        TabelaHash[] dobramento = new TabelaHash[5];
        for (int i = 0; i < tamanhosVetor.length; i++) {
            for (int[] conjunto : conjuntos) {
                dobramento[i] = popularTabelaHash(Tipo.DOBRAMENTO, tamanhosVetor[i], conjunto);
            }
        }

//        ------------------------------------------

        for (int i = 0; i < restoDivisao.length; i++) {
            long start = System.nanoTime();
            restoDivisao[i].buscar(numerosBuscaArray[i]);
            long finish = System.nanoTime();
            float elapsed = (float) (finish - start) / 1000000000;
            System.out.printf(Locale.UK, "RESTODIVISAO,%s,%.6f\n", nomes[i], elapsed);
        }

        for (int i = 0; i < multiplicacao.length; i++) {
            long start = System.nanoTime();
            multiplicacao[i].buscar(numerosBuscaArray[i]);
            long finish = System.nanoTime();
            float elapsed = (float) (finish - start) / 1000000000;
            System.out.printf(Locale.UK, "MULTIPLICACAO,%s,%.6f\n", nomes[i], elapsed);
        }

        for (int i = 0; i < dobramento.length; i++) {
            long start = System.nanoTime();
            dobramento[i].buscar(numerosBuscaArray[i]);
            long finish = System.nanoTime();
            float elapsed = (float) (finish - start) / 1000000000;
            System.out.printf(Locale.UK, "DOBRAMENTO,%s,%.6f\n", nomes[i], elapsed);
        }
    }
}