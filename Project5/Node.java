package Project5;

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
        if(newNode > this.data) {
            if(this.left == null) {
                this.left = new Node(newNode);
            } else this.left.insert( newNode );
        }
        else if (this.right == null) this.right = new Node(newNode);
        else this.right.insert( newNode );
    }
}
