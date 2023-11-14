import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    NoArvore raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new NoArvore(valor);
        } else {
            inserirRecursivo(raiz, valor);
        }
    }

    private void inserirRecursivo(NoArvore no, int valor) {
        if (valor < no.valor) {
            if (no.esquerda == null) {
                no.esquerda = new NoArvore(valor);
            } else {
                inserirRecursivo(no.esquerda, valor);
            }
        } else {
            if (no.direita == null) {
                no.direita = new NoArvore(valor);
            } else {
                inserirRecursivo(no.direita, valor);
            }
        }
    }

    public void preordem(NoArvore no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preordem(no.esquerda);
            preordem(no.direita);
        }
    }

    public void emordem(NoArvore no) {
        if (no != null) {
            emordem(no.esquerda);
            System.out.print(no.valor + " ");
            emordem(no.direita);
        }
    }

    public void posordem(NoArvore no) {
        if (no != null) {
            posordem(no.esquerda);
            posordem(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    public void nivel() {
        if (raiz == null) {
            return;
        }

        Queue<NoArvore> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoArvore no = fila.poll();
            System.out.print(no.valor + " ");

            if (no.esquerda != null) {
                fila.add(no.esquerda);
            }

            if (no.direita != null) {
                fila.add(no.direita);
            }
        }
    }
}
