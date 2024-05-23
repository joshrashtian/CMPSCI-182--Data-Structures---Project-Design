package Project5;

import Universal.Log;

public class Project5 {
    public static void main(String[] args) {
        int data[] = { 50, 30, 60, 10, 80, 55, 40 };

        Tree NewTree = new Tree(data);
        Log.log("Inorder: ");
        Node.inorder(NewTree.getRoot());
        Log.log("Postorder: ");

        Log.log("Max: " + NewTree.getMax());
        Log.log("Sum: " + NewTree.getSum());
        Log.log("Num of Nodes: " + NewTree.getNodeCount());
        Log.log("Exists 40: " + NewTree.getNode(40));
        Log.log("Average: " + NewTree.getSum()/NewTree.getNodeCount());
        Log.log("Height: " + NewTree.getHeight());
    }
}
