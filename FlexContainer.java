public class FlexContainer<E> {
    private Object[] internalStore;
    private int occupancy;
    private static final int INITIAL_CAPACITY = 10;

    public FlexContainer() {
        internalStore = new Object[INITIAL_CAPACITY];
    }

    public void inject(E element) {
        ensureCapacity(occupancy + 1);
        internalStore[occupancy++] = element;
    }

    public E extract(int position) {
        if (position < 0 || position >= occupancy) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        @SuppressWarnings("unchecked")
        E item = (E) internalStore[position];
        return item;
    }

    public void insert(int position, E element) {
        if (position < 0 || position > occupancy) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        ensureCapacity(occupancy + 1);
        System.arraycopy(internalStore, position, internalStore, position + 1, occupancy - position);
        internalStore[position] = element;
        occupancy++;
    }

    public E remove(int position) {
        if (position < 0 || position >= occupancy) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        @SuppressWarnings("unchecked")
        E removedItem = (E) internalStore[position];
        int numMoved = occupancy - position - 1;
        if (numMoved > 0) {
            System.arraycopy(internalStore, position + 1, internalStore, position, numMoved);
        }
        internalStore[--occupancy] = null;
        return removedItem;
    }

    public int size() {
        return occupancy;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > internalStore.length) {
            int newCapacity = Math.max(internalStore.length * 2, minCapacity);
            Object[] newStore = new Object[newCapacity];
            System.arraycopy(internalStore, 0, newStore, 0, occupancy);
            internalStore = newStore;
        }
    }

    public static void main(String[] args) {
        FlexContainer<String> container = new FlexContainer<>();

        // Test 1: Inject elements
        container.inject("Alpha");
        container.inject("Beta");
        container.inject("Gamma");
        System.out.println("Test 1 - Size: " + container.size());

        // Test 2: Extract element
        System.out.println("Test 2 - Extracted: " + container.extract(1));

        // Test 3: Insert element
        container.insert(1, "Delta");
        System.out.println("Test 3 - Size after insert: " + container.size());

        // Test 4: Remove element
        String removed = container.remove(2);
        System.out.println("Test 4 - Removed: " + removed + ", New size: " + container.size());

        // Test 5: Capacity expansion
        for (int i = 0; i < 20; i++) {
            container.inject("Element " + i);
        }
        System.out.println("Test 5 - Size after expansion: " + container.size());
    }
}