public class ArrayDeque<T> {
    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 1;
    private T[] sentinel;

    private void nextFirstMinusOne() {
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = sentinel.length - 1;
        }
    }

    private void nextLastMinusOne() {
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast = sentinel.length - 1;
        }
    }

    private void nextFirstPlusOne() {
        nextFirst += 1;
        if (nextFirst == sentinel.length) {
            nextFirst = 0;
        }
    }

    private void nextLastPlusOne() {
        nextLast += 1;
        if (nextLast == sentinel.length) {
            nextLast = 0;
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
        if (!isEmpty()) {
            nextFirstPlusOne();
            T bb = sentinel[nextFirst];
            sentinel[nextFirst] = null;
            size -= 1;
            resize();
            return bb;
        } else {
            return null;
        }
    }

    public void addLast(T item) {
        sentinel[nextLast] = item;
        size += 1;
        nextLastPlusOne();
        resize();
    }

    public T removeLast() {
        if (!isEmpty()) {
            nextLastMinusOne();
            T bb = sentinel[nextLast];
            sentinel[nextLast] = null;
            size -= 1;
            resize();
            return bb;
        } else {
            return null;
        }
    }

    public T get(int x) {
        int offset = x + nextFirst + 1;
        if (offset < sentinel.length) {
            return sentinel[offset];
        } else {
            return sentinel[offset - sentinel.length];
        }
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
        if ((Math.abs(nextFirst - nextLast) == 1) && size != 0) {
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

//    public static void main(String[] args) {
//        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
//        arrayDeque.addFirst("ab");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addFirst("cd");
//        arrayDeque.addLast("fd");
//        arrayDeque.addLast("fd0");
//        arrayDeque.removeFirst();
//        System.out.println(arrayDeque.get(1)); //2.0
//        arrayDeque.printDeque();
//        System.out.println(arrayDeque.size());
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        arrayDeque.removeFirst();
//        System.out.println("is empty?" + arrayDeque.isEmpty());
//        System.out.println("size?" + arrayDeque.size());
//    }
}
