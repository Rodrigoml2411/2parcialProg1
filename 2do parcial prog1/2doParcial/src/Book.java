public class Book {

    private String title;
    private String isbn;
    private String author;
    private int publicationYear;
    private boolean available;
    private int timesLoaned;

    private Queue<String> waitlist;
    private Stack<LoanRecord> loanHistory;

    public Book(String title, String isbn, String author, int publicationYear) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true;
        this.timesLoaned = 0;
        this.waitlist = new Queue<>();
        this.loanHistory = new Stack<>();
    }

    // Intenta prestar a 'user'. Solo presta si está disponible.
    public boolean lend(String user) {
        if (user == null) return false;

        if (available) {
            if (!waitlist.isEmpty()) {
                if (!user.equals(waitlist.peek())) return false;
                waitlist.dequeue();
            }
            available = false;
            timesLoaned++;
            loanHistory.push(new LoanRecord(user, now()));
            return true;
        }

        return false;
    }

    // Agrega un usuario a la espera si el libro está prestado.
    public boolean addToWaitlist(String user) {
        if (user == null || available) return false;
        waitlist.enqueue(user);
        return true;
    }

    // Consulta el primer usuario en espera sin quitarlo de la cola.
    public String nextWaitingUser() {
        return waitlist.peek();
    }

    // Ve el último préstamo sin modificar
    public LoanRecord viewLastLoan() {
        return loanHistory.peek();
    }

    // Deshace el último préstamo
    public LoanRecord undoLastLoan() {
        LoanRecord last = loanHistory.pop();
        if (last != null) {
            available = true;
            if (timesLoaned > 0) timesLoaned--;
        }
        return last;
    }

    // Devuelve el libro y lo presta al primer usuario en espera, si existe.
    public String returnBook() {
        available = true;
        String nextUser = waitlist.peek();
        if (nextUser != null && lend(nextUser)) return nextUser;
        return null;
    }


    
    // Getters / setters
    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return available; }
    public int getTimesLoaned() { return timesLoaned; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(int year) { this.publicationYear = year; }

    // Solo historial
    public int loanHistorySize() {
        return loanHistory.size();
    }

    // Timestamp simple
    private String now() {
        long ms = System.currentTimeMillis();
        return Long.toString(ms);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', isbn='" + isbn + "', author='" + author +
                "', year=" + publicationYear + ", available=" + available +
                ", timesLoaned=" + timesLoaned + "}";
    }
}
