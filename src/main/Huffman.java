package main;

public class Huffman {

    // instances
    private Node root;
    private final Heap heap;
    public Array arr;

    // constructor
    public Huffman(Heap heap) {
        this.root = null;
        this.heap = heap;
    }

    // creates a huffman tree
    public void buildTree() {
        while(heap.getArr().getNumOfElements()!=1) {
            Node node1 = heap.deleteMin();
            Node node2 = heap.deleteMin();
            Node node = new Node(node1.frequency + node2.frequency, node1, node2);
            heap.insertAndSort(node);
        }
        root = heap.deleteMin();
    }

    // encodes a message to binary
    public void encodePath() {
        encodePath(root);
    }
    private void encodePath(Node node) {
        if(node.left!=null) {
            node.left.path = node.path + "0";
            encodePath(node.left);
        }
        if(node.right!=null) {
            node.right.path = node.path + "1";
            encodePath(node.right);
        }
    }

    // finds the code for given data
    public void encoder(String message) {
        for(int i=0; i<message.length(); i++)
            encoder(root, message.charAt(i));
    }
    private void encoder(Node node, char data) {
        if(node.letter == data) System.out.println(node.path);
        if(node.left!=null) encoder(node.left, data);
        if(node.right!=null) encoder(node.right, data);
    }

    // decodes a binary message to string
    public String decoder(String code) {
        StringBuilder decodedText = new StringBuilder();
        Node node = root;
        for(int i=0; i<code.length(); i++) {
            char data = code.charAt(i);
            if(data=='0' && node.left!=null)
                node = node.left;
            if(data=='1' && node.right!=null)
                node = node.right;
            if(node.letter!='*') {
                decodedText.append(node.letter);
                node = root;
            }
        }
        return decodedText.toString();
    }

    // stores the path in an array
    public void storePath() {
        arr = new Array();
        storePath(arr, root);
        arr.printArray();
    }
    private void storePath(Array arr, Node node) {
        if(node.letter != '*') arr.insertTail(new Node(node.letter, node.path));
        if(node.left!=null) storePath(arr, node.left);
        if(node.right!=null) storePath(arr, node.right);
    }

    // prints the huffman tree
    public void printTree() {
        printTree(root, 0);
    }
    private  void printTree(Node node, int indent) {
        for(int i=0; i<indent; i++)
            System.out.print("|\t");
        System.out.printf(
                "%s : %.2f : %s\n",
                node.letter,
                node.frequency,
                node.path
        );
        if(node.left != null)
            printTree(node.left, indent+1);
        if(node.right != null)
            printTree(node.right, indent+1);
    }

}
