package library.system;

import library.model.Book;
import library.model.LibraryMember;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<LibraryMember> members;
    private static final int MAX_BOOKS_PER_MEMBER = 3;
    private static final int MAX_RESERVATIONS_PER_MEMBER = 2;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Add book to the library
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
            System.out.println("Book added successfully: " + book.getTitle());
        } else {
            System.out.println("Book already exists or is null.");
        }
    }

    // Add member to the library
    public void addMember(LibraryMember member) {
        if (member != null && !members.contains(member)) {
            members.add(member);
            System.out.println("Member added successfully: " + member.getName());
        } else {
            System.out.println("Member already exists or is null.");
        }
    }

    // Borrow book from the library
    public void borrowBook(LibraryMember member, Book book) {
        if (member == null || book == null) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (!members.contains(member)) {
            System.out.println("Member not found in library system.");
            return;
        }

        if (!books.contains(book)) {
            System.out.println("Book not found in library.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is not available for borrowing.");
            return;
        }

        if (member.getBorrowedBooksCount() >= MAX_BOOKS_PER_MEMBER) {
            System.out.println("Member has reached maximum borrowing limit (" + MAX_BOOKS_PER_MEMBER + " books).");
            return;
        }

        // Borrow the book
        if (member.hasReservedBook(book)) {
            member.removeReservedBook(book);
            book.setReserved(false);
            book.setReservedBy(null);
        }

        book.setAvailable(false);
        member.addBorrowedBook(book);
        System.out.println(member.getName() + " borrowed: " + book.getTitle());
    }

    // Return book to the library
    public void returnBook(LibraryMember member, Book book) {
        if (member == null || book == null) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (!members.contains(member)) {
            System.out.println("Member not found in library system.");
            return;
        }

        if (!member.getBorrowedBooks().contains(book)) {
            System.out.println("Member has not borrowed this book.");
            return;
        }

        // Return the book
        book.setAvailable(true);
        member.removeBorrowedBook(book);
        System.out.println(member.getName() + " returned: " + book.getTitle());
    }

    // Reserve a book
    public void reserveBook(LibraryMember member, Book book) {
        if (member == null || book == null) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (!members.contains(member)) {
            System.out.println("Member not found in library system.");
            return;
        }

        if (!books.contains(book)) {
            System.out.println("Book not found in library.");
            return;
        }

        if (book.isReserved()) {
            System.out.println("Book '" + book.getTitle() + "' is already reserved by " + book.getReservedBy());
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book '" + book.getTitle() + "' is currently borrowed. You can reserve it for when it's returned.");
        }

        if (member.hasReservedBook(book)) {
            System.out.println("Member " + member.getName() + " has already reserved this book.");
            return;
        }

        if (member.getReservedBooksCount() >= MAX_RESERVATIONS_PER_MEMBER) {
            System.out.println("Member has reached maximum reservation limit (" + MAX_RESERVATIONS_PER_MEMBER + " books).");
            return;
        }

        // Reserve the book
        book.setReserved(true);
        book.setReservedBy(member.getName());
        member.addReservedBook(book);
        System.out.println(member.getName() + " successfully reserved: " + book.getTitle());
    }

    // Cancel a book reservation
    public void cancelReservation(LibraryMember member, Book book) {
        if (member == null || book == null) {
            System.out.println("Invalid member or book.");
            return;
        }

        if (!members.contains(member)) {
            System.out.println("Member not found in library system.");
            return;
        }

        if (!book.isReserved()) {
            System.out.println("Book '" + book.getTitle() + "' is not currently reserved.");
            return;
        }

        if (!member.hasReservedBook(book)) {
            System.out.println("Book '" + book.getTitle() + "' was not reserved by " + member.getName());
            return;
        }

        // Cancel the reservation
        book.setReserved(false);
        book.setReservedBy(null);
        member.removeReservedBook(book);
        System.out.println(member.getName() + " canceled reservation for: " + book.getTitle());
    }

    // Display reserved books for a specific member
    public void displayReservedBooks(LibraryMember member) {
        if (member == null) {
            System.out.println("Invalid member.");
            return;
        }

        System.out.println("\nReserved Books for " + member.getName());
        List<Book> reservedBooks = member.getReservedBooks();

        if (reservedBooks.isEmpty()) {
            System.out.println("No books currently reserved.");
        } else {
            for (Book book : reservedBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (Status: " + book.getStatus() + ")");
            }
        }
    }

    // Display all reserved books in the library
    public void displayAllReservedBooks() {
        System.out.println("\nAll Reserved Books");
        boolean hasReserved = false;

        for (Book book : books) {
            if (book.isReserved()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (Reserved by: " + book.getReservedBy() + ")");
                hasReserved = true;
            }
        }

        if (!hasReserved) {
            System.out.println("No books are currently reserved.");
        }
    }

    // Display all books
    public void displayAllBooks() {
        System.out.println("\nLibrary Books");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Display all members
    public void displayAllMembers() {
        System.out.println("\nLibrary Members");
        if (members.isEmpty()) {
            System.out.println("No members in the library.");
        } else {
            for (LibraryMember member : members) {
                System.out.println(member);
            }
        }
    }

    // Display available books
    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books");
        boolean hasAvailable = false;
        for (Book book : books) {
            if (book.isAvailable() && !book.isReserved()) {
                System.out.println(book);
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("No books available.");
        }
    }

    // Get library statistics
    public void displayLibraryStats() {
        int availableBooks = 0;
        int borrowedBooks = 0;
        int reservedBooks = 0;

        for (Book book : books) {
            if (!book.isAvailable()) {
                borrowedBooks++;
            } else if (book.isReserved()) {
                reservedBooks++;
            } else {
                availableBooks++;
            }
        }

        System.out.println("\nLibrary Statistics");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Borrowed Books: " + borrowedBooks);
        System.out.println("Reserved Books: " + reservedBooks);
        System.out.println("Total Members: " + members.size());
    }
}

