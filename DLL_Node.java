public class DLL_Node<T> {
    public T data;
    public DLL_Node<T> next;
    public DLL_Node<T> prev;

    DLL_Node(T item){
        data = item;
        next = prev = null;
    }
}