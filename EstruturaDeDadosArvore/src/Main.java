import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(42);
        int[] numeros = random.ints(20, 0, 101).distinct().toArray();

        ArvoreBinaria arvore = new ArvoreBinaria();
        for (int numero : numeros) {
            arvore.inserir(numero);
        }

        System.out.println("Árvore original:");
        System.out.print("Pré-ordem: ");
        arvore.preordem(arvore.raiz);
        System.out.print("\nEm ordem: ");
        arvore.emordem(arvore.raiz);
        System.out.print("\nPós-ordem: ");
        arvore.posordem(arvore.raiz);
        System.out.print("\nEm nível: ");
        arvore.nivel();

        System.out.println("\n\nRemovendo 5 elementos aleatórios da árvore:");

        StringBuilder elementosRemovidos = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(numeros.length);
            int noARemover = numeros[indice];
            elementosRemovidos.append(noARemover).append(" ");
            numeros = removeElemento(numeros, indice);
        }
        System.out.println("Elementos removidos: " + elementosRemovidos.toString());

        arvore = new ArvoreBinaria();
        for (int numero : numeros) {
            arvore.inserir(numero);
        }

        System.out.println("Árvore após a remoção dos elementos:");
        System.out.print("Pré-ordem: ");
        arvore.preordem(arvore.raiz);
        System.out.print("\nEm ordem: ");
        arvore.emordem(arvore.raiz);
        System.out.print("\nPós-ordem: ");
        arvore.posordem(arvore.raiz);
        System.out.print("\nEm nível: ");
        arvore.nivel();
    }

    private static int[] removeElemento(int[] array, int indice) {
        int[] newArray = new int[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i != indice) {
                newArray[k++] = array[i];
            }
        }
        return newArray;
    }
}
