package synthesizer;
import java.util.Iterator;


// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }
    public int capacity() {
        return this.capacity;
    }

    public int fillCount() {
        return this.fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public int updatelastfirst(int a){
        if(a == capacity-1){
            return 0;
        }
        return a + 1;
    }

    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull()){
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last = updatelastfirst(last);
        fillCount = fillCount + 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */



    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()){
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T returnItem = rb[first];
        rb[first] = null;
        first = updatelastfirst(first);
        fillCount = fillCount - 1;

        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if(isEmpty()){
            throw new RuntimeException("The queue is empty!");
        }

        T peekreturnItem = rb[first];
        return peekreturnItem;

    }

    private class QueueIterator implements Iterator<T> {

        int numRemain;
        int current;

        QueueIterator() {
            numRemain = fillCount();
            current = first;
        }

        @Override
        public boolean hasNext() {
            return numRemain > 0;
        }

        @Override
        public T next() {
            T item = rb[current];
            numRemain -= 1;
            current = updatelastfirst(current);
            return item;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }
}
