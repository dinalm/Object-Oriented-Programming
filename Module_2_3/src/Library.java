import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a user to the library
    public void addUser(User user) {
        users.add(user);
    }

    // Display all books in the library
    public void displayBooks() {
        System.out.println("Library Catalog:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". Title: \"" + book.getTitle() + "\", Author: \"" + book.getAuthor() + "\", Year: " + book.getPublicationYear());
        }
    }

    // Display all users in the library
    public void displayUsers() {
        System.out.println("Library Users:");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println((i + 1) + ". Name: \"" + user.getName() + "\", Age: " + user.getAge() + ", Borrowed Books: " + user.getBorrowedBooks().size());
        }
    }

    // Find books by a specific author
    public void findBooksByAuthor(String author) {
        System.out.println("\nBooks by Author \"" + author + "\":");
        boolean found = false;

        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                System.out.println("Title: \"" + book.getTitle() +
                        "\", Year: " + book.getPublicationYear());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by author \"" + author + "\"");
        }
    }

    // Borrow a book from the library by a user
    public Book borrowBook(String title, User user) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equals(title)) {
                books.remove(i);
                user.borrowBook(book);
                System.out.println("Book borrowed: \"" + title + "\" by " + user.getName());
                return book;
            }
        }
        System.out.println("Book \"" + title + "\" is not available in the library.");
        return null;
    }

    // Return a book to the library by a user
    public void returnBook(Book book, User user) {
        if (book != null && user.returnBook(book)) {
            books.add(book);
            System.out.println("Book returned: \"" + book.getTitle() + "\" by " + user.getName());
        } else {
            System.out.println("Cannot return book or user doesn't have this book.");
        }
    }

    // Check if a book is available by title
    public boolean isBookAvailable(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    // Calculate and return the average rating of all books
    public double getAverageBookRating() {
        if (books.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Book book : books) {
            totalRating += book.getRating();
        }

        return totalRating / books.size();
    }

    // Return the book with the highest number of reviews
    public Book getMostReviewedBook() {
        if (books.isEmpty()) {
            return null;
        }

        Book mostReviewed = books.get(0);
        for (Book book : books) {
            if (book.getReviews().size() > mostReviewed.getReviews().size()) {
                mostReviewed = book;
            }
        }

        return mostReviewed;
    }
}
