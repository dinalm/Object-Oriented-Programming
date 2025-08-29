public class LibraryMain {
    public static void main(String[] args) {
        // Create instances of Book
        Book book1 = new Book("Introduction to Java Programming", "John Smith", 2020);
        Book book2 = new Book("Data Structures and Algorithms", "Jane Doe", 2018);
        Book book3 = new Book("The Art of Fiction", "Alice Johnson", 2019);

        // Create instances of User
        User user1 = new User("Alice Brown", 25);
        User user2 = new User("Bob Wilson", 30);
        User user3 = new User("Carol Davis", 22);

        // Create an instance of Library
        Library library = new Library();

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Add users to the library
        library.addUser(user1);
        library.addUser(user2);
        library.addUser(user3);

        // Display all books and users in the library
        library.displayBooks();
        System.out.println();
        library.displayUsers();

        // Search for books by a specific author
        library.findBooksByAuthor("Jane Doe");

        // Borrowing system with users
        Book borrowedBook1 = library.borrowBook("Data Structures and Algorithms", user1);
        Book borrowedBook2 = library.borrowBook("Introduction to Java Programming", user2);

        // Check availability
        System.out.println("Is book available? " + library.isBookAvailable("Data Structures and Algorithms"));

        // Display users after borrowing
        System.out.println("\nUsers after borrowing:");
        library.displayUsers();

        // Return books
        library.returnBook(borrowedBook1, user1);
        library.returnBook(borrowedBook2, user2);

        // Display users after returning
        System.out.println("\nUsers after returning books:");
        library.displayUsers();

        // Rating and review system
        book1.setRating(4.5);
        book1.addReview("Great book for beginners!");

        book2.setRating(4.8);
        book2.addReview("Excellent coverage of algorithms.");
        book2.addReview("Very detailed and well-structured.");

        book3.setRating(3.2);
        book3.addReview("Beautiful writing style.");
        book3.addReview("Engaging plot.");
        book3.addReview("Highly recommended for fiction lovers.");

        // Statistics methods
        System.out.println("\nLibrary Statistics");
        double averageRating = library.getAverageBookRating();
        System.out.println("Average book rating: " + averageRating);

        Book mostReviewed = library.getMostReviewedBook();
        if (mostReviewed != null) {
            System.out.println("Most reviewed book: \"" + mostReviewed.getTitle() +
                    "\" with " + mostReviewed.getReviews().size() + " reviews");
        }
    }
}