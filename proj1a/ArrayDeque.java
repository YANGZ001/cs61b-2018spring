public class ArrayDeque<T> {
    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 0;
    private T[] sentinel;

    public ArrayDeque(T item) {
        sentinel = (T[]) new Object[8];
        sentinel[nextFirst] = item;
        nextFirstMinusOne();
        size += 1;
    }

    private void nextFirstMinusOne() {
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = sentinel.length - 1;
        }
    }

    public ArrayDeque() {
        sentinel = (T[]) new Object[8];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        sentinel[nextFirst] = item;
        size += 1;
        nextFirstMinusOne();
        resize();
    }

    public T removeFirst() {
        nextFirst += 1;
        T bb = sentinel[nextFirst];
        size -= 1;
        resize();
        return bb;
    }

    public void addLast(T item) {
        sentinel[nextLast] = item;
        size += 1;
        nextLast += 1;
        resize();
    }

    public T removeLast() {
        nextLast -= 1;
        T bb = sentinel[nextLast];
        size -= 1;
        resize();
        return bb;
    }

    public T get(int x) {
        return sentinel[x];
    }

    public void printDeque() {
        int exit = 0;
        if (nextFirst < sentinel.length - 1) {
            for (int i = nextFirst + 1; i < sentinel.length; i++) {
                System.out.print(sentinel[i] + " ");
                exit += 1;
            }
        }
        for (int i = 0; i < size - exit; i++) {
            System.out.print(sentinel[i] + " ");
        }
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (Math.abs(nextFirst - nextLast) == 1) {
            resize(2);
        }
        if (size >= 16) {
            if (size > 0.5 * sentinel.length) {
                resize(2.0);
            } else if (size <= 0.25 * sentinel.length) {
                resize(0.5);
            }
        }
    }

    private void resize(double factor) {
//        T[] newArray = (T[]) new Object[(int) (sentinel.length * factor)];
//        int newNextFirst = newArray.length / 2 - size / 2 - 1;
//        int newNextLast = newArray.length / 2 + size / 2;
//        System.arraycopy(sentinel, nextFirst + 1, newArray, newNextFirst + 1, size);
//        nextFirst = newNextFirst;
//        nextLast = newNextLast;
//        sentinel = newArray;
        T[] newArray = (T[]) new Object[(int) (sentinel.length * factor)];
        int newNextFirst = nextFirst + newArray.length - sentinel.length; //addLast does not change
        System.arraycopy(sentinel, 0, newArray, 0, nextLast);
        System.arraycopy(sentinel, nextFirst + 1, newArray, newNextFirst + 1, size - nextLast);
        nextFirst = newNextFirst;
        sentinel = newArray;
    }
}
