public class mainApp {
    public static void main(String[] args) {
        StringDoubleEndedQueueImpl<String> q = new StringDoubleEndedQueueImpl<String>();

        q.addFirst("A");
        q.addLast("B");
        q.addLast("C");
        q.addFirst("D");
        q.printQueue(System.out);
        System.out.println();
        q.removeFirst();
        q.removeLast();
        q.printQueue(System.out);
    }
}
