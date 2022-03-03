public class Node<E> {
    private E data;
    private Node next;

    public Node(E data) {
        this.data = this.data;
        this.next = null;
    }

    public Node getNext(){
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setNode(Node next) {
        this.next = next;
    }

    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.data + "->" + this.next;
    }


    public int compareTo(Object o) {
        if(o instanceof Number) {
            Number in = (Number) o;
            Number myval = (Number) this.getData();
            return (in.intValue() == myval.intValue()) ? 0 : 1;
        }
        return 0;
    }
}