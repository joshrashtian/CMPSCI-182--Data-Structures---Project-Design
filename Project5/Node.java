package Project5;

import Universal.Log;

public class Node {
    int data;
    Node left;
    Node right;

    Node (int nodeData) {
        this.data = nodeData;
        Node left = null;
        Node right = null;
    }

    int getData() {
        return data;
    }

    void insert( int newNode ) {
        if(newNode < this.data) {
            if(this.left == null) {
                this.left = new Node(newNode);
            } else this.left.insert( newNode );
        }
        else if (this.right == null) this.right = new Node(newNode);
        else this.right.insert( newNode );
    }

    int getHeight(int height, Node root) {
        if(root == null) return height;

        if (root.left == null && root.right != null) {
            return getHeight(height + 1, root.right);
        } else {
            return getHeight(height + 1, root.left);
        }

    }

    int getMax(int curr, Node root) {
        if(root == null) return curr;

        else return getMax(root.data, root.right);
    }

    int getSum(Node root, int val) {

        if(root == null) return val;
        int curr = root.data;

        curr += getSum(root.left, val);
        curr += getSum(root.right, val);

        return curr;
    }

    int getNodeCount(Node root, int val) {
        if(root == null) return val;
        int curr = 1;

        curr += getNodeCount(root.left, val);
        curr += getNodeCount(root.right, val);

        return curr;
    }

    public static void inorder(Node root){
        if(root==null) return;

        inorder(root.left);
        System.out.print(root.data + ",");
        inorder(root.right);
    }
}




