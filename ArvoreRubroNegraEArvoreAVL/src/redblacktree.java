class redBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    No<T> root;


    static class No<T extends Comparable<T>> {
        T data;
        No<T> left, right;
        boolean color;

        public No(T data, boolean color) {
            this.data = data;
            this.color = color;
            this.left = this.right = null;
        }
    }

    // Construtor
    public redBlackTree() {
        this.root = null;
    }


    private boolean isRed(No<T> no) {
        if (no == null) return false;
        return no.color == RED;
    }

    private No<T> rotateLeft(No<T> h) {
        No<T> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private No<T> rotateRight(No<T> h) {
        No<T> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(No<T> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void insert(T data) {
        root = insert(root, data);
        root.color = BLACK;
    }

    private No<T> insert(No<T> h, T data) {
        if (h == null) return new No<>(data, RED);

        int cmp = data.compareTo(h.data);
        if (cmp < 0) h.left = insert(h.left, data);
        else if (cmp > 0) h.right = insert(h.right, data);
        else h.data = data;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(No<T> no) {
        if (no != null) {
            inOrderTraversal(no.left);
            System.out.print(no.data + " ");
            inOrderTraversal(no.right);
        }
    }
}
