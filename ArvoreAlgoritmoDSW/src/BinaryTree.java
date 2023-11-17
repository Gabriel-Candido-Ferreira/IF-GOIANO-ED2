import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree {
    public static void main(String[] args) {
        TreeNode initialTree = createRandomTree(100);

        System.out.println("Árvore inicial: " + printTree(initialTree) + "\n");

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int additionalNumber = random.nextInt(101);
            initialTree = insert(initialTree, additionalNumber);
        }

        initialTree = balanceTree(initialTree);

        System.out.println("Árvore balanceada após adicionar 20 números e aplicar o Algoritmo DSW: " + printTree(initialTree));
    }

    private static TreeNode createRandomTree(int size) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(101));
        }

        TreeNode root = null;
        for (int num : numbers) {
            root = insert(root, num);
        }
        return root;
    }

    private static String printTree(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root != null) {
            printTreeAux(root, values);
        }
        return values.toString();
    }

    private static void printTreeAux(TreeNode root, List<Integer> values) {
        if (root != null) {
            printTreeAux(root.left, values);
            values.add(root.value);
            printTreeAux(root.right, values);
        }
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private static TreeNode balanceTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        traverseInOrder(root, nodes);
        return performRotations(root, nodes.size()).root;
    }

    private static RotationResult performRotations(TreeNode root, int size) {
        if (size < 2) {
            return new RotationResult(root, null);
        }

        TreeNode p = root;
        TreeNode q = p.left;
        for (int i = 0; i < size; i++) {
            if (q != null) {
                root = q;
                p.left = q.right;
                q.right = p;
                p = root;
                q = p.left;
            }
        }
        return new RotationResult(root, q);
    }

    private static void traverseInOrder(TreeNode root, List<TreeNode> nodes) {
        if (root != null) {
            traverseInOrder(root.left, nodes);
            nodes.add(root);
            traverseInOrder(root.right, nodes);
        }
    }

    private static class RotationResult {
        TreeNode root;
        TreeNode q;

        RotationResult(TreeNode root, TreeNode q) {
            this.root = root;
            this.q = q;
        }
    }
}
