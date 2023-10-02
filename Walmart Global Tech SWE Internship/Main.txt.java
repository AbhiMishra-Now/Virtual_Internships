import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {
    private int x; // The exponent value for the number of children of each parent node
    private List<T> heap;

    public PowerOfTwoMaxHeap(int x) {
        this.x = x;
        this.heap = new ArrayList<>();
    }

    public void insert(T item) {
        heap.add(item);
        heapifyUp(heap.size() - 1);
    }

    public T popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        T max = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        heapifyDown(0);

        return max;
    }

    private void heapifyUp(int index) {
        if (index <= 0) {
            return;
        }

        int parentIndex = (index - 1) / x;
        if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int maxIndex = index;

        for (int i = 1; i <= x; i++) {
            int childIndex = x * index + i;
            if (childIndex < heap.size() && heap.get(childIndex).compareTo(heap.get(maxIndex)) > 0) {
                maxIndex = childIndex;
            }
        }

        if (maxIndex != index) {
            swap(index, maxIndex);
            heapifyDown(maxIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

//Now you can create an instance of the PowerOfTwoMaxHeap class and use it to insert elements and pop the maximum element from the heap, 
//Example:-

public class Main {
    public static void main(String[] args) {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2);
        heap.insert(10);
        heap.insert(5);
        heap.insert(7);
        heap.insert(12);
        
        System.out.println(heap.popMax());  // Output: 12
        System.out.println(heap.popMax());  // Output: 10
    }
}
