package Test;

public class test1 {
    class MyCircularQueue {

        private int first;
        private int last;
        private int size;
        private int[] data;
        private int count;

        // Help method

        public int update(int a){
            if(a == size - 1){
                return 0;
            }
            return a + 1;
        }

        public MyCircularQueue(int k) {
            this.data = new int[k];
            this.first = 0;
            this.last = 0;
            this.size = k;
            this.count = 0;
        }

        public boolean enQueue(int value) {
            if(isFull()){
                return false;
            }

            // first enqueue time:special case !
            if(isEmpty()){
                data[last] = value;
                count = count + 1;
                return true;
            }

            last = update(last);
            data[last] = value;
            count = count + 1;
            return true;
        }

        public boolean deQueue() {
            if(isEmpty()){
                return false;
            }
            if(first == last){
                data[first] = 0;
                first = 0;
                last = 0;
                return true;
            }
            data[first] = 0;
            first = update(first);
            count = count - 1;
            return true;
        }

        public int Front() {
            if(isEmpty()){
                return -1;
            }

            return data[first];
        }

        public int Rear() {
            if(isEmpty()){
                return -1;
            }

            return data[last];
        }

        public boolean isEmpty() {
            if(count == 0){
                return true;
            }
            return false;

        }

        public boolean isFull() {
            if(size == count){
                return true;
            }
            return false;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}
