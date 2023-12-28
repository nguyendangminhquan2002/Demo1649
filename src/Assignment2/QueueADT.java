package Assignment2;

public interface QueueADT<E> extends Iterable<E> {
    void enqueue(E element);

    E dequeue();

    E peek();

    int size();

    boolean isEmpty();
}