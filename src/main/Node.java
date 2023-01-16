package main;

public class Node {

    public char letter;
    public double frequency;
    public String path;
    public Node left;
    public Node right;

    public Node(char letter) {
        this.letter = letter;
        this.frequency = 0;
        this.path = "";
    }

    public Node(double frequency, Node left, Node right) {
        this.letter = '*';
        this.frequency = frequency;
        this.path = "";
        this.left = left;
        this.right = right;
    }

    public Node(char letter, String path) {
        this.letter = letter;
        this.path = path;
    }
}
