
public class Library {
    private String name;
    private LinkedList<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new LinkedList<>();
    }

    // Agrega el libro
    public void addBook(Book b) {
        if (b == null) return;
        books.add(b);
    }



    // Elimina un libro por ISBN. Retorna true si se eliminó.
    public boolean removeBook(String isbn) {
        // buscar el libro
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && equalsIgnoreNull(b.getIsbn(), isbn)) {
                return books.remove(b); // usa remove por valor
            }
        }
        return false;
    }

    // Busca por ISBN (coincidencia exacta)
    public Book findBookByIsbn(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && equalsIgnoreNull(b.getIsbn(), isbn)) return b;
        }
        return null;
    }

    // Busca por el título (coincidencia parcial)
    public LinkedList<Book> findBooksByTitle(String partial) {
        LinkedList<Book> result = new LinkedList<>();
        if (partial == null) return result;
        String q = partial.toLowerCase();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && b.getTitle() != null && b.getTitle().toLowerCase().contains(q)) {
                result.add(b);
            }
        }
        return result;
    }

    // Busca por autor (coincidencia parcial)
    public LinkedList<Book> findBooksByAuthor(String partial) {
        LinkedList<Book> result = new LinkedList<>();
        if (partial == null) return result;
        String q = partial.toLowerCase();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && b.getAuthor() != null && b.getAuthor().toLowerCase().contains(q)) {
                result.add(b);
            }
        }
        return result;
    }

    // Obtiene los libros disponibles
    public LinkedList<Book> getAvailableBooks() {
        LinkedList<Book> result = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && b.isAvailable()) result.add(b);
        }

        
        return result;
    }

    // Obtiene los libros prestados
    public LinkedList<Book> getLoanedBooks() {
        LinkedList<Book> result = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null && !b.isAvailable()) result.add(b);
        }
        return result;
    }

    // Estadísticas simples: total libros, disponibles, prestados, total préstamos acumulados
    public String getStatistics() {
        int total = books.size();
        int avail = 0;
        int loaned = 0;
        int totalTimesLoaned = 0;
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b != null) {
                if (b.isAvailable()) avail++;
                else loaned++;
                totalTimesLoaned += b.getTimesLoaned();
            }
        }
        return "Library '" + name + "' stats -> total: " + total + ", available: " + avail + ", loaned: " + loaned + ", totalTimesLoaned: " + totalTimesLoaned;
    }

    
    private boolean equalsIgnoreNull(String a, String b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    // Métodos de ayuda para pruebas: iterar libros y ejecutar acción
    public void forEachBook(LinkedList.Consumer<Book> consumer) {
        books.forEach(consumer);
    }

    public int totalBooks() {
        return books.size();
    }
}
