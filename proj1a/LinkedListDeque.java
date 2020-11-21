public class LinkedListDeque<BleepBlorp> {
    public class IntNode {
        //circular loop
        private IntNode prev;
        private BleepBlorp item;
        private IntNode next;

        public IntNode(BleepBlorp item) {
            prev = null;
            this.item = item;
            next = null;
        }

        public IntNode() {
            new IntNode(null);
        }

        public void setItem(BleepBlorp item) {
            this.item = item;
        }

        public void setNext(IntNode next) {
            this.next = next;
        }

        public void setPrev(IntNode prev) {
            this.prev = prev;
        }

        public void display() {
            System.out.print(item + " ");
        }
    }

    private int size;
    private IntNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = other.size;
        sentinel = new IntNode(null);
        sentinel.setPrev(sentinel);
        sentinel.setNext(sentinel);

        IntNode prevOfOther = other.sentinel;
        IntNode prevOfLLD = sentinel;

        for (int i = 0; i < size; i++) {
            IntNode currOfOther = prevOfOther.next;
            IntNode newIntNode = new IntNode(currOfOther.item);

            newIntNode.setNext(sentinel);
            newIntNode.setPrev(prevOfLLD);
            prevOfLLD.setNext(newIntNode);
            sentinel.setPrev(newIntNode);

            prevOfOther = currOfOther;
            prevOfLLD = newIntNode;
        }
    }

    public void addFirst(BleepBlorp item) {
        IntNode newIntNode = new IntNode(item);
        IntNode firstIntNode = sentinel.next;

        newIntNode.setNext(firstIntNode);
        newIntNode.setPrev(sentinel);
        sentinel.setNext(newIntNode);
        firstIntNode.setPrev(newIntNode);
        size += 1;
    }

    public void addLast(BleepBlorp item) {
        IntNode newIntNode = new IntNode(item);
        IntNode lastIntNode = sentinel.prev;

        newIntNode.setPrev(lastIntNode);
        newIntNode.setNext(sentinel);
        lastIntNode.setNext(newIntNode);
        sentinel.setPrev(newIntNode);
        size += 1;
    }

    public boolean isEmpty() {
//        if (sentinel == null || size == 0) {
//            return true;
//        } else {
//            return false;
//        }
        return (sentinel == null || size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode prev = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(prev.next.item + " ");
            prev = prev.next;
        }
        System.out.print("\n");
    }

    public BleepBlorp removeFirst() {
        IntNode firstIntNode = sentinel.next;
        IntNode secondIntNode = sentinel.next.next;

        sentinel.setNext(secondIntNode);
        secondIntNode.setPrev(sentinel);
        size -= 1;
        return firstIntNode.item;
    }

    public BleepBlorp removeLast() {
        IntNode lastIntNode = sentinel.prev;
        IntNode secondLastIntNode = sentinel.prev.prev;

        secondLastIntNode.setNext(sentinel);
        sentinel.setPrev(secondLastIntNode);
        size -= 1;
        return lastIntNode.item;
    }

    public BleepBlorp get(int index) {
        if (index > size) {
            System.out.println("Ops!!!  Index out of size volume");
            return null;
        } else  {
            IntNode prev = sentinel;
            for (int i = 0; i < index; i++) {
                System.out.print(prev.next.item + " ");
                prev = prev.next;
            }
            return prev.next.item;
        }
    }

    public BleepBlorp getRecursive(int index) {
        if (this.size() - 1 == index) {
            return this.get(index);
        } else {
            this.removeLast();
            return this.getRecursive(index);
        }
    }
}
