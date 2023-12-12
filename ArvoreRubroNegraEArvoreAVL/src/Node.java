public class Node {
    int valor;
    int altura;
    Node esquerda;
    Node direita;

    Node(int valor) {
        this.valor = valor;
        altura = 1;
    }
}