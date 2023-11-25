import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue<T>{
    private DLL_Node<T> m_head, m_tail;
    private int m_size;

    StringDoubleEndedQueueImpl(){
        m_head = m_tail = null;
        m_size = 0;
    }

    public boolean isEmpty(){
        return (m_size == 0);
    }

    public T getFirst() throws NoSuchElementException { 
        if(isEmpty())
            throw new NoSuchElementException();
        return m_head.data;
    }

    public T getLast() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        return m_tail.data;
    }

    public void addFirst(T item){
        if(isEmpty()){
            m_head = m_tail = new DLL_Node<T>(item);
            ++m_size;
            return;
        }
        DLL_Node<T> newFirstNode = new DLL_Node<T>(item);
        m_head.prev = newFirstNode;
        newFirstNode.next = m_head;
        m_head = newFirstNode;
        ++m_size;
    }

    public T removeFirst() throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException();
        T tempRet = m_head.data;

        if(m_head.next == null){
            m_head = m_tail = null;
        }
        else{
            m_head = m_head.next;
            m_head.prev = null;
        }
        
        --m_size;

        return tempRet;
    }

    public void addLast(T item){
        if(isEmpty()){
            m_head = m_tail = new DLL_Node<T>(item);
            ++m_size;
            return;
        }

        DLL_Node<T> newLastNode = new DLL_Node<T>(item);
        m_tail.next = newLastNode;
        newLastNode.prev = m_tail;
        m_tail = newLastNode;
        ++m_size;
    }

    public T removeLast() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();
        T tempRet = m_tail.data;

        if(m_tail.prev == null)
            m_head = m_tail = null;
        else{
            m_tail = m_tail.prev;
            m_tail.next = null;
        }
        --m_size;
        return tempRet;
    }

    public void printQueue(PrintStream stream){
        DLL_Node<T> temp = m_head;
        while(temp != null){
            stream.append(temp.data.toString());
            temp = temp.next;
        }
        stream.append('\n');
    }

    public int size() { return m_size; }

}