public class Hash {
    public int restoDivisao(int chave, int m) {
        return chave % m;
    }

    public int multiplicacao(int chave, int m) {
        double A = 0.6180339887F;
        return (int) (m * ((A * chave) % 1));
    }

    public int dobramento(int chave, int m) {
        String[] numeros = ("" + chave).split("");
        int soma1 = Integer.parseInt(numeros[0] + numeros[1] + numeros[2]);
        int soma2 = Integer.parseInt(numeros[3] + numeros[4] + numeros[5]);
        int soma3 = Integer.parseInt(numeros[6] + numeros[7] + numeros[8]);
        return (soma1 + soma2 + soma3) % m;
    }
}
