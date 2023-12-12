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
    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }
    Node remover(Node node, int valor) {
        if (node == null) {
            return null;
        }

        if (valor < node.valor) {
            node.esquerda = remover(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = remover(node.direita, valor);
        } else {
            // Nó a ser removido
            if (node.esquerda == null || node.direita == null) {
                Node temp = (node.esquerda != null) ? node.esquerda : node.direita;

                if (temp == null) {
                    // Nó sem filhos
                    temp = node;
                    node = null;
                } else {
                    // Nó com um filho
                    node = temp; // Copia o conteúdo do filho para o nó a ser removido
                }
            } else {
                // Nó com dois filhos, encontra o sucessor in-order (o menor valor na subárvore à direita)
                Node temp = encontrarMenor(node.direita);
                // Copia o valor do sucessor in-order para este nó
                node.valor = temp.valor;
                // Remove o sucessor in-order
                node.direita = remover(node.direita, temp.valor);
            }
        }

        // Se a árvore tinha apenas um nó
        if (node == null) {
            return null;
        }

        // Atualiza a altura do nó atual
        node.altura = maior(altura(node.esquerda), altura(node.direita)) + 1;

        // Rebalanceia o nó
        return rebalancear(node);
    }

    private Node encontrarMenor(Node node) {
        Node atual = node;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    int contarOcorrencias(int valor) {
        return contarOcorrencias(raiz, valor);
    }

    private int contarOcorrencias(Node node, int valor) {
        if (node == null) {
            return 0;
        }

        int cmp = Integer.compare(valor, node.valor);
        if (cmp < 0) {
            return contarOcorrencias(node.esquerda, valor);
        } else if (cmp > 0) {
            return contarOcorrencias(node.direita, valor);
        } else {
            return 1 + contarOcorrencias(node.esquerda, valor) + contarOcorrencias(node.direita, valor);
        }
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
    void passadaEmOrdem(Node node) {
        if (node != null) {
            passadaEmOrdem(node.esquerda);
            System.out.print(" " + node.valor);
            passadaEmOrdem(node.direita);
        }
    }
}