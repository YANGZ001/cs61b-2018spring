public class ArrayDeque<BleepBlorp, remove> {
    private int size = 0;
    private int nextFirst = 3;
    private int nextLast = 4;
    private BleepBlorp[] sentinel;

    public ArrayDeque(BleepBlorp item){
        sentinel = (BleepBlorp[]) new Object[8];
        sentinel[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public ArrayDeque(){
        sentinel = (BleepBlorp[]) new Object[8];
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }

    public void addFirst(BleepBlorp item){
//        if ( !isEmpty() && ( Math.abs(nextFirst - nextLast) == 1 || Math.abs(nextFirst - nextLast) == sentinel.length - 1)){
//            resize(2);
//        }
        sentinel[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        resize();
    }

    public BleepBlorp removeFirst(){
        nextFirst += 1;
        BleepBlorp bb = sentinel[nextFirst];
        size -= 1;
        resize();
        return bb;
    }

    public void addLast(BleepBlorp item){
        sentinel[nextLast] = item;
        size += 1;
        nextLast += 1;
        resize();
    }

    public BleepBlorp removeLast(){
        nextLast -= 1;
        BleepBlorp bb = sentinel[nextLast];
        size -= 1;
        resize();
        return bb;
    }

    public BleepBlorp get(int x){
        return sentinel[x];
    }

    public void printDeque(){
        for (int i = nextFirst + 1; i < size; i++){
            System.out.print(sentinel[i] + " ");
        }
    }

    public int size(){
        return size;
    }

    public void resize() {
        if (nextFirst == -1 || nextLast == sentinel.length) {
            resize(2);
        }else if (!isEmpty() && (nextLast < sentinel.length / 2) || (nextFirst > sentinel.length - 1)){
            resize(0.5);
        }
        if (size >= 16) {
            if (size > 0.5 * sentinel.length) {
                resize(2.0);
            } else if (size <= 0.25 * sentinel.length) {
                resize(0.5);
            }
        }
    }

    public void resize(double factor){
        BleepBlorp[] newArray = (BleepBlorp[]) new Object[(int) (sentinel.length * factor)];
        int newNextFirst = newArray.length / 2 - size / 2 - 1;
        int newNextLast = newArray.length / 2 + size / 2;
        System.arraycopy(sentinel, nextFirst + 1, newArray, newNextFirst + 1, size);
        nextFirst = newNextFirst;
        nextLast = newNextLast;
        sentinel = newArray;
    }

    public static void main(String[] args) {
        System.out.println(2/8);
    }
}