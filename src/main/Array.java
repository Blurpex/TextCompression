package main;

public class Array {

    private int size;
    private int numOfElements;
    private Node[] arr;

    // initialize array with size 1
    Array() {
        this.size = 1;
        this.numOfElements = 0;
        this.arr = new Node[this.size];
    }

    Array(Array arr) {
        this.size = arr.size;
        this.numOfElements = arr.numOfElements;
        this.arr = arr.arr;
    }

    // getter methods
    public int getSize() {
        return this.size;
    }

    public int getNumOfElements() {
        return this.numOfElements;
    }

    public Node[] getArray() {
        return this.arr;
    }

    // returns the element at a given position
    public Node getElement(int pos) {
        return pos>=numOfElements || pos<0 ? null : arr[pos];
    }

    // inserts an element at a given position
    public void insert(Node data, int pos) {
        if(numOfElements == size)
            growSize();
        arr[pos] = data;
        ++numOfElements;
    }

    // inserts an element at the last position
    public void insertTail(Node data) {
        if(numOfElements == size)
            growSize();
        arr[numOfElements] = data;
        ++numOfElements;
    }

    // returns the element if it is in the array
    public Node search(char data) {
        for(int i=0; i<numOfElements; i++) {
            if(arr[i].letter == data)
                return arr[i];
        }
        return null;
    }

    // deletes an element at a given position
    public void delete(int pos) {
        arr[pos] = null;
        --numOfElements;
        if((double) size/numOfElements > 2)
            shrinkSize();
    }

    // clears the array
    public void clear() {
        size = 1;
        numOfElements = 0;
        arr = new Node[size];
    }

    // swaps two elements
    public void swap(int pos1, int pos2) {
        Node temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    // dynamically grows the size of the array
    private void growSize() {
        Node[] temp = size==0 ? new Node[size+1] : new Node[size*2];
        System.arraycopy(arr, 0, temp, 0, size);
        arr = temp;
        size*=2;
    }

    // dynamically shrinks the size of the array
    private void shrinkSize() {
        Node[] temp = new Node[size/2];
        System.arraycopy(arr, 0, temp, 0, size/2);
        arr = temp;
        size/=2;
    }

    // divides frequency by 100
    public void convertFrequency(int length) {
        for(int i=0; i<numOfElements; i++) {
            arr[i].frequency /= length;
        }
    }

    // prints the array
    public void printArray() {
        System.out.println("{ ");
        for(int i = 0; i < numOfElements; i++)
            System.out.println("\t" + arr[i].letter + " : "
                                + arr[i].path + ", ");
        System.out.println("}");
    }

}
