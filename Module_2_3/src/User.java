import java.util.ArrayList;

public class User {
    private String name;
    private int age;
    private ArrayList<Book> borrowedBooks;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Add a borrowed book
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Return a borrowed book
    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }
}
