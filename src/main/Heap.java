package main;

public class Heap {

    // initialize array
    private final Array arr = new Array();

    // getter  methods
    public Array getArr() {
        return this.arr;
    }

    // return the smallest key in heap
    public Node getMin() {
        return arr.getElement(0);
    }

    // inserts an element into heap
    public void insert(char data) {
        Node temp = arr.search(data);
        if(temp == null) {
            temp = new Node(data);
            arr.insertTail(temp);
        }
        temp.frequency++;
    }

    // inserts a node into heap and sorts it
    public void insertAndSort(Node data) {
        arr.insertTail(data);
        heapifyUp();
    }

    // returns and removes the smallest key in heap
    public Node deleteMin() {
        Node temp = arr.getElement(0);
        int lastElementPos = arr.getNumOfElements() - 1;
        arr.swap(0, lastElementPos);
        arr.delete(lastElementPos);
        heapifyDown();
        return temp;
    }

    // sorts the array with the smallest frequency at top
    public void buildHeap() {
        Node[] temp = arr.getArray();
        int numOfElements = arr.getNumOfElements();
        arr.clear();
        for(int i=0; i<numOfElements; i++) {
            arr.insertTail(temp[i]);
            heapifyUp();
        }
    }

    // sorts tree upwards
    private void heapifyUp() {
        int chPos = arr.getNumOfElements() - 1;
        int prntPos = (chPos - 1) / 2;
        while(arr.getElement(chPos) != null
           && arr.getElement(prntPos) != null
           && arr.getElement(chPos).frequency < arr.getElement(prntPos).frequency)
        {
            arr.swap(chPos, prntPos);
            chPos = prntPos;
            prntPos = (prntPos - 1) / 2;
        }
    }

    // sorts tree downwards
    private void heapifyDown() {
        int prntPos = 0,
             leftPos = 1,
             rightPos = 2;

        Node prnt = arr.getElement(0),
             left = arr.getElement(1),
             right = arr.getElement(2);

        while(prnt != null && left != null
                && prnt.frequency > left.frequency)
        {
            if(right == null || left.frequency <= right.frequency) {
                arr.swap(prntPos, leftPos);
                prntPos = leftPos;
            } else {
                arr.swap(prntPos, rightPos);
                prntPos = rightPos;
            }
            leftPos = 2 * prntPos + 1;
            left = arr.getElement(leftPos);
            rightPos = 2 * prntPos + 2;
            right = arr.getElement(rightPos);
        }
    }

    // print heap as a tree
    public void printHeap(int prnt, int indent) {
        int left = 2*prnt+1, right = 2*prnt+2;
        if(right < arr.getNumOfElements())
            printHeap(right, indent+1);
        for(int i=0; i<indent; i++)
            System.out.print("|\t\t\t");
        System.out.printf(
                "%s : %.5f\n",
                arr.getElement(prnt).letter,
                arr.getElement(prnt).frequency
        );
        if(left < arr.getNumOfElements())
            printHeap(left, indent+1);
    }

}
