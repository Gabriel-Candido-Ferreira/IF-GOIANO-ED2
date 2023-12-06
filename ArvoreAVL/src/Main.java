public class Main {
    private static final int[] VALORES = {100000};
    private static final String EM_ORDEM = "Em Ordem";
    private static final String TEMPO = "Tempo";

    public static void main(String[] args) {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        BaseDeDados pegaArquivo = new BaseDeDados();

        for (int valor : VALORES) {
            int[] numeros = pegaArquivo.choose(valor);

            inserirNaArvore(arvoreAVL, numeros);

            imprimirResultado(arvoreAVL, EM_ORDEM);
        }
    }

    private static void inserirNaArvore(ArvoreAVL arvore, int[] numeros) {
        for (int n : numeros) {
            arvore.inserir(n);
        }
    }

    private static void imprimirResultado(ArvoreAVL arvore, String ordem) {
        long comeco = System.currentTimeMillis();

        System.out.println("Árvore AVL");
        System.out.println(ordem + ":");

        arvore.passadaEmOrdem(arvore.raiz);

        long fim = System.currentTimeMillis();


        long duracao = fim - comeco;
        imprimirTempo(duracao);
    }

    private static void imprimirTempo(long duracao) {
        long milisegundos = (duracao % 1000) / 100;
        long segundos = (duracao / 1000) % 60;
        long minutos = (duracao / (1000 * 60)) % 60;
        long horas = (duracao / (1000 * 60 * 60)) % 24;
        String tempo = String.format("%02d:%02d:%02d:%02d", horas, minutos, segundos, milisegundos);
        System.out.println(TEMPO + ": " + tempo);
    }
}
