import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private ArrayList<String> reviews;

    // Constructor
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = 0.0;
        this.reviews = new ArrayList<>();
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<String> getReviews() {
        return new ArrayList<>(reviews);
    }

    // Rating and review methods
    public void setRating(double rating) {
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
            System.out.println("Rating set to " + rating + " for \"" + title + "\"");
        } else {
            System.out.println("Please provide a rating between 0.0 and 5.0");
        }
    }

    public void addReview(String review) {
        if (review != null && !review.trim().isEmpty()) {
            reviews.add(review);
            System.out.println("Review added for \"" + title + "\"");
        } else {
            System.out.println("Cannot add empty review");
        }
    }
}
