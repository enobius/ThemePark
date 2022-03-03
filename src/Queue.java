public class Queue<E> {

    private int size;
    private int front = 0;
    private int rear = -1;
    private E[] data;
    private int count = 0;

    public Queue(){
        this.size = 100;
        this.data = (E[])(new Object[this.size]);
    }

    public Queue(int size){
        this.size = size;
        this.data = (E[])(new Object[this.size]);
    }

    public E examine(){
        if (!isEmpty()){
            return data[front];
        }
        return null;
    }

    public boolean isFull() {
        return count == size;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void enqueue(E element){
        if (!isFull()){
            rear = (rear+1)%size;
            data[rear] = element;
            count++;
        }
    }

    public E dequeue(){
        if (!isEmpty()){
            E val = data[front];
            front = (front + 1)%size;
            count--;
            return val;
        }
        return null;
    }

    public String toString(){
        StringBuilder string = new StringBuilder(" ");
        if (!isEmpty()){
            for (int i = front;i != rear; i = (i + 1)%size){
                string.append("|").append(data[i]).append("|");
            }
            string.append("|").append(data[rear]).append("|");
        }else{
            string.append("| |");
        }
        return string.toString();
    }
}
