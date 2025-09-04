package library.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;
    private List<Book> reservedBooks;

    // Constructor
    public LibraryMember(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public List<Book> getReservedBooks() {
        return new ArrayList<>(reservedBooks);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    // Manage borrowed books
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public boolean removeBorrowedBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public int getBorrowedBooksCount() {
        return borrowedBooks.size();
    }

    // Manage reserved books
    public void addReservedBook(Book book) {
        if (!reservedBooks.contains(book)) {
            reservedBooks.add(book);
        }
    }

    public boolean removeReservedBook(Book book) {
        return reservedBooks.remove(book);
    }

    public boolean hasReservedBook(Book book) {
        return reservedBooks.contains(book);
    }

    public int getReservedBooksCount() {
        return reservedBooks.size();
    }

    // Display member's activity
    public void displayMemberActivity() {
        System.out.println("\nActivity for " + name + " (ID: " + memberId + ")");

        System.out.println("Borrowed Books (" + borrowedBooks.size() + "):");
        if (borrowedBooks.isEmpty()) {
            System.out.println("  No books currently borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println("  - " + book.getTitle() + " by " + book.getAuthor());
            }
        }

        System.out.println("Reserved Books (" + reservedBooks.size() + "):");
        if (reservedBooks.isEmpty()) {
            System.out.println("  No books currently reserved.");
        } else {
            for (Book book : reservedBooks) {
                System.out.println("  - " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public String toString() {
        return "LibraryMember{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                ", borrowedBooks=" + borrowedBooks.size() +
                ", reservedBooks=" + reservedBooks.size() +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryMember member = (LibraryMember) obj;
        return memberId == member.memberId;
    }
}
