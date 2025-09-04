package library;

import library.model.Book;
import library.model.LibraryMember;
import library.system.Library;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        // Create books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4");
        Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8");

        // Add books to the library
        System.out.println("Adding Books");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        // Create some library members
        LibraryMember member1 = new LibraryMember("Alice Johnson", 1001);
        LibraryMember member2 = new LibraryMember("Bob Smith", 1002);
        LibraryMember member3 = new LibraryMember("Charlie Brown", 1003);

        // Add members to the library
        System.out.println("\nAdding Members");
        library.addMember(member1);
        library.addMember(member2);
        library.addMember(member3);

        // Display initial state
        library.displayAllBooks();
        library.displayAllMembers();
        library.displayLibraryStats();

        // Perform borrowing operations
        System.out.println("\nBorrowing Operations");
        library.borrowBook(member1, book1);
        library.borrowBook(member1, book2);
        library.borrowBook(member2, book3);
        library.borrowBook(member2, book1);

        // Display current state
        library.displayAvailableBooks();
        library.displayLibraryStats();

        // Returning operations
        System.out.println("\nReturning Operations");
        library.returnBook(member1, book1);
        library.returnBook(member2, book4);

        // Display final state
        library.displayAvailableBooks();
        library.displayLibraryStats();

        System.out.println("\nReservations");
        library.reserveBook(member1, book4);
        library.reserveBook(member2, book2);
        library.reserveBook(member3, book4);

        // Display reserved books
        library.displayReservedBooks(member1);
        library.displayReservedBooks(member2);
        library.displayAllReservedBooks();

        // Reserving a borrowed book
        System.out.println("\nReserving Borrowed Books");
        library.reserveBook(member1, book3);

        // Reservation limits
        System.out.println("\nReservation Limits");
        Book book6 = new Book("Brave New World", "Aldous Huxley", "978-0-06-085052-4");
        Book book7 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0-544-00341-5");
        library.addBook(book6);
        library.addBook(book7);

        library.reserveBook(member1, book6);
        library.reserveBook(member1, book7);

        // Canceling reservations
        System.out.println("\nReservation Cancellation");
        library.cancelReservation(member1, book4);
        library.cancelReservation(member2, book4);
        library.cancelReservation(member3, book6);

        // Borrowing reserved book
        System.out.println("\nBorrowing Reserved Books");
        library.borrowBook(member2, book2);

        // Display member activities
        System.out.println("\nMember Activities");
        member1.displayMemberActivity();
        member2.displayMemberActivity();
        member3.displayMemberActivity();

        // Statistics with reservations
        library.displayLibraryStats();
        library.displayAllReservedBooks();
    }
}
