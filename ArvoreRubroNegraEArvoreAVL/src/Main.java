import java.util.Random;

public class Main {
    private static final int[] VALORES = { 100000 };
    private static final String TEMPO = "Tempo";

    public static void main(String[] args) {
        Random random = new Random();

        // Construção da árvore AVL
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        BaseDeDados pegaArquivoAVL = new BaseDeDados();

        for (int valor : VALORES) {
            int[] numerosAVL = pegaArquivoAVL.choose(valor);
            long inicioConstrucaoAVL = System.nanoTime();
            inserirNaArvore(arvoreAVL, numerosAVL);
            long fimConstrucaoAVL = System.nanoTime();
            long duracaoConstrucaoAVL = fimConstrucaoAVL - inicioConstrucaoAVL;
            System.out.println("Tempo de Construção AVL: " + duracaoConstrucaoAVL + " ns");
        }

        // Construção da árvore Rubro-Negra
        redBlackTree<Integer> arvoreRubroNegra = new redBlackTree<>();
        BaseDeDados pegaArquivoRubroNegra = new BaseDeDados();

        for (int valor : VALORES) {
            int[] numerosRubroNegra = pegaArquivoRubroNegra.choose(valor);
            long inicioConstrucaoRubroNegra = System.nanoTime();
            inserirNaArvoreRubroNegra(arvoreRubroNegra, numerosRubroNegra);
            long fimConstrucaoRubroNegra = System.nanoTime();
            long duracaoConstrucaoRubroNegra = fimConstrucaoRubroNegra - inicioConstrucaoRubroNegra;
            System.out.println("Tempo de Construção Rubro-Negra: " + duracaoConstrucaoRubroNegra + " ns");
        }

        // Inserção de 50.000 números na árvore AVL
        long inicioInsercoesAVL = System.nanoTime();
        for (int i = 0; i < 50000; i++) {
            int numeroAleatorio = random.nextInt(19999) - 9999;
            arvoreAVL.inserir(numeroAleatorio);
        }
        long fimInsercoesAVL = System.nanoTime();
        long duracaoTotalInsercoesAVL = fimInsercoesAVL - inicioInsercoesAVL;
        System.out.println("Tempo Total de Inserções AVL: " + duracaoTotalInsercoesAVL + " ns");

        // Inserção de 50.000 números na árvore Rubro-Negra
        long inicioInsercoesRubroNegra = System.nanoTime();
        for (int i = 0; i < 50000; i++) {
            int numeroAleatorio = random.nextInt(19999) - 9999;
            arvoreRubroNegra.insert(numeroAleatorio);
        }
        long fimInsercoesRubroNegra = System.nanoTime();
        long duracaoTotalInsercoesRubroNegra = fimInsercoesRubroNegra - inicioInsercoesRubroNegra;
        System.out.println("Tempo Total de Inserções Rubro-Negra: " + duracaoTotalInsercoesRubroNegra + " ns");
    }

    private static void inserirNaArvore(ArvoreAVL arvore, int[] numeros) {
        for (int n : numeros) {
            arvore.inserir(n);
        }
    }

    private static void inserirNaArvoreRubroNegra(redBlackTree<Integer> arvore, int[] numeros) {
        for (int n : numeros) {
            arvore.insert(n);
        }
    }
}

