
public class Main {
    public static void main(String[] args) {
        // Crea la biblioteca
        Library lib = new Library("Biblioteca Central");

        
        Book b1 = new Book("Programación en Java", "978-1111", "Juan Pérez", 2018);
        Book b2 = new Book("Estructuras de Datos", "978-2222", "María López", 2015);
        Book b3 = new Book("Algoritmos y Programación", "978-3333", "Carlos Ruiz", 2020);

        // Agrega los libros
        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        // Muestra las estadísticas iniciales
        System.out.println(lib.getStatistics());
        System.out.println("Total libros: " + lib.totalBooks());

        // Préstamos normales
        System.out.println("\n--- Préstamos ---");
        boolean prestado = b1.lend("usuarioA");
        System.out.println("b1 prestado a usuarioA? " + prestado + " -> disponible? " + b1.isAvailable());
        prestado = b1.lend("usuarioB");
        System.out.println("b1 prestado a usuarioB? " + prestado + " -> disponible? " + b1.isAvailable());
        b1.addToWaitlist("usuarioB");
        b1.addToWaitlist("usuarioD");
        System.out.println("Primero en espera para b1: " + b1.nextWaitingUser());

        prestado = b2.lend("usuarioC");
        System.out.println("b2 prestado a usuarioC? " + prestado);

        // Mira el último préstamo de b1
        System.out.println("\nÚltimo préstamo de b1: " + b1.viewLastLoan());

        // Deshace el último préstamo de b2 
        System.out.println("\n--- Undo préstamo b2 ---");
        LoanRecord undone = b2.undoLastLoan();
        System.out.println("Deshecho: " + undone + " -> disponible? " + b2.isAvailable());

        // Devuelve el libro b1: se asigna al primer usuario de la lista de espera.
        System.out.println("\n--- Devolución b1 ---");
        String assigned = b1.returnBook();
        System.out.println("Al devolver b1 se asignó a: " + (assigned == null ? "nadie (queda disponible)" : assigned));
        System.out.println("b1 disponible? " + b1.isAvailable() + ", historial size: " + b1.loanHistorySize());
        System.out.println("Siguiente usuario en espera: " + b1.nextWaitingUser());

        // Busca por el título parcial
        System.out.println("\n---  Búsqueda por título 'Program' ---");
        LinkedList<Book> found = lib.findBooksByTitle("Program");
        for (int i = 0; i < found.size(); i++) {
            System.out.println("Encontrado: " + found.get(i));
        }

        // Muestra los disponibles y prestados
        System.out.println("\nDisponibles:");
        LinkedList<Book> avail = lib.getAvailableBooks();
        for (int i = 0; i < avail.size(); i++) System.out.println(" - " + avail.get(i));
        System.out.println("\nPrestados:");
        LinkedList<Book> loaned = lib.getLoanedBooks();
        for (int i = 0; i < loaned.size(); i++) System.out.println(" - " + loaned.get(i));

        // Prueba el removeBook
        System.out.println("\n--- Eliminación ---");
        boolean removed = lib.removeBook("978-3333");
        System.out.println("Se eliminó 978-3333? " + removed + " -> total ahora: " + lib.totalBooks());

        // Prueba LinkedList operaciones básicas (usando una lista de prueba)
        System.out.println("\n--- Test LinkedList genérica ---");
        LinkedList<String> lista = new LinkedList<>();
        lista.add("A");
        lista.add("B");
        lista.add("C");
        System.out.println("Lista size: " + lista.size() + ", contiene B? " + lista.contains("B"));
        System.out.println("Elemento 1: " + lista.get(1));
        boolean rem = lista.remove("B");
        System.out.println("Se eliminó B? " + rem + ", size ahora: " + lista.size());
        lista.clear();
        System.out.println("Después de clear, isEmpty? " + lista.isEmpty());

        // Muestra las estadísticas finales
        System.out.println("\n Estadísticas finales: " + lib.getStatistics());

         System.out.println("\n Demostración completada.");
    }
}