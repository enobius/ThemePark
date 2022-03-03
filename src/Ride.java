public class Ride<E> {

    public Queue<String> potRiders = new Queue();
    public Queue<String> fastRiders = new Queue();
    public int numOfRiders;
    public int timeofRide = 5;
    public int size = 6;
    public int durationOfService;
    public String rideName;
    public Ride(String rideName) {
        this.rideName = rideName;
    }


    public int getSize() {
        return size;
    }

    public int getTimeOfRide() {
        return timeofRide;
    }

    public String getRideName() {
        return rideName;
    }

    public void addSlowWait(String rider) {
        potRiders.enqueue(rider);
    }

    public void addFastWait(String rider) {
        fastRiders.enqueue(rider);
    }

    public String sendSwiftly() {
        return fastRiders.dequeue();
    }

    public void sendSlow() {
        potRiders.dequeue();
    }
}
