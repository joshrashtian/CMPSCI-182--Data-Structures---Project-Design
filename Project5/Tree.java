package Project5;

import java.util.Arrays;

public class Tree {
    private Node root;
    Tree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public int getMax() {
        return root.getMax(0, root);
    }

    public int getHeight() {
        return root.getHeight(0, root);
    }

    public int getSum() {
        return root.getSum(root, 0);

    }

    public int getNodeCount() {
        return root.getNodeCount(root, 0);
    }

    Tree(int[] values) {
        root = new Node(values[0]);

        for (int i = 1; i < values.length; i++) {
            root.insert(values[i]);
        }
    }
}
