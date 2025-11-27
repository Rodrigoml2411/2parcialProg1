
public class LinkedList<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Agrega un elemento al final
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
        } else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    // Obtiene por índice 
    public T get(int index) {
        checkIndex(index);
        Node<T> cur = head;
        int i = 0;
        while (i < index) {
            cur = cur.next;
            i++;
        }
        return cur.data;
    }

    // Elimina por valor. Retorna true si se eliminó.
    public boolean remove(T value) {
        if (head == null) return false;
        if (equalsValue(head.data, value)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> prev = head;
        Node<T> cur = head.next;
        while (cur != null) {
            if (equalsValue(cur.data, value)) {
                prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    // Vacia la lista
    public void clear() {
        head = null;
        size = 0;
    }

    // Verifica la existencia
    public boolean contains(T value) {
        Node<T> cur = head;
        while (cur != null) {
            if (equalsValue(cur.data, value)) return true;
            cur = cur.next;
        }
        return false;
    }

    
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    // ejecuta una acción sobre cada elemento (callback)
    public void forEach(Consumer<T> consumer) {
        Node<T> cur = head;
        while (cur != null) {
            consumer.accept(cur.data);
            cur = cur.next;
        }
    }

    private boolean equalsValue(T a, T b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Interfaz para el forEach
    public interface Consumer<E> {
        void accept(E e);
    }
}
