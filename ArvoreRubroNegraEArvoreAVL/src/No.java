// No.java
public class No<T extends Comparable<T>> {
    T data;
    No<T> left, right;
    boolean color;

    public No(T data, boolean color) {
        this.data = data;
        this.color = color;
        this.left = this.right = null;
    }
}
