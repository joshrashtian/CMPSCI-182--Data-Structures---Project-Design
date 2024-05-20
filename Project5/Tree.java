package Project5;

import java.util.Arrays;

public class Tree {
    private Node root;
    Tree() {
        root = null;
    }


    Tree(int[] values) {
        root = new Node(values[0]);

        for (int i = 1; i < values.length; i++) {
            root.insert(values[i]);
        }
    }
}
