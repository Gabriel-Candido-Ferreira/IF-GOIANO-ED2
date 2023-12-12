class ArvoreAVL {
    Node raiz;

    int altura(Node node) {
        return (node == null) ? 0 : node.altura;
    }

    int maior(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rotacaoDireita(Node node) {
        Node filhoEsquerdo = node.esquerda;
        node.esquerda = filhoEsquerdo.direita;
        filhoEsquerdo.direita = node;
        node.altura = maior(altura(node.esquerda), altura(node.direita)) + 1;
        filhoEsquerdo.altura = maior(altura(filhoEsquerdo.esquerda), altura(filhoEsquerdo.direita)) + 1;
        return filhoEsquerdo;
    }

    Node rotacaoEsquerda(Node node) {
        Node filhoDireita = node.direita;
        node.direita = filhoDireita.esquerda;
        filhoDireita.esquerda = node;
        node.altura = maior(altura(node.esquerda), altura(node.direita)) + 1;
        filhoDireita.altura = maior(altura(filhoDireita.esquerda), altura(filhoDireita.direita)) + 1;
        return filhoDireita;
    }

    int fatorBalanceamento(Node node) {
        return (node == null) ? 0 : altura(node.direita) - altura(node.esquerda);
    }

    void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    Node rebalancear(Node node) {
        int fatorBalanceamento = fatorBalanceamento(node);

        if (fatorBalanceamento < -1) {
            if (fatorBalanceamento(node.esquerda) <= 0) {
                node = rotacaoDireita(node);
            } else {
                node.esquerda = rotacaoEsquerda(node.esquerda);
                node = rotacaoDireita(node);
            }
        }

        if (fatorBalanceamento > 1) {
            if (fatorBalanceamento(node.direita) >= 0) {
                node = rotacaoEsquerda(node);
            } else {
                node.direita = rotacaoDireita(node.direita);
                node = rotacaoEsquerda(node);
            }
        }

        return node;
    }

    Node inserir(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }

        if (valor < node.valor) {
            node.esquerda = inserir(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = inserir(node.direita, valor);
        } else {
            return node;
        }

        node.altura = maior(altura(node.esquerda), altura(node.direita)) + 1;
        node = rebalancear(node);
        return node;
    }
    void emOrdem(Node node) {
        if (node != null) {
            emOrdem(node.esquerda);
            System.out.print(" " + node.valor);
            emOrdem(node.direita);
        }
    }
}
