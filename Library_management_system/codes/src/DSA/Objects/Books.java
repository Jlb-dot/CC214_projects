package DSA.Objects;

import DSA.Admin.MySQLbookDb;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Books {

    public static final String LIBRARY_FILE = "library_books.csv";
    public static final String BORROW_HISTORY_FILE = "borrow_history.csv";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int ISBN;
    private  String title;
    private  String author;
    private  String genre;
    private  LocalDateTime datePublished;
    private int availableCopy;
    private int totalCopy;
    private String currentBorrower;
    private LocalDateTime borrowDate;
    private boolean borrowed;
    private String borrower;
    private boolean isAvailable;

    public Books(int id, String title, String genre, String author, Date datepub, int copies, int totalCopies) {
        this.ISBN = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        // Convert java.sql.Date to LocalDateTime
        this.datePublished = datepub.toLocalDate().atStartOfDay();
        this.availableCopy = copies;
        this.totalCopy = totalCopies;
        this.currentBorrower = "";
        this.borrowDate = LocalDateTime.now();
        this.borrowed = false;
        this.borrower = "";
    }

    // Keep your existing LocalDateTime constructor
    public Books() {

    }



    public void setCurrentBorrower(String currentBorrower) {
        this.currentBorrower = currentBorrower;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public void setBorrowDate(LocalDateTime borrowDate) {this.borrowDate = borrowDate;}

    public  String getGenre() {return genre;}

    // Getters and setters
    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    //todo if the available is == 0 return false;
    public int getAvailableCopy() {
        return availableCopy;
    }

    public int getTotalCopy() {
        return totalCopy;
    }

    public int setAvailableCopy(int availableCopy) {this.availableCopy = availableCopy;
        return availableCopy;
    }


    public String getCurrentBorrower() {
        return currentBorrower;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = (borrower != null) ? borrower : "";
    }

    public boolean isAvailable() {
        if (availableCopy != 0) {
            return true; // Return true if copies are available
        }
        return false;     // Return false if no copies are available
    }



    public void addBooks() {  // Make this non-static
        String genre = "";  // You might want to add genre as a class property

        if (!MySQLbookDb.validateaddedBooks(this.ISBN, this.title)) {  // Check if book doesn't exist
            if (MySQLbookDb.AddBooks(this.ISBN, this.title, genre, this.author,
                    this.datePublished, this.availableCopy, this.totalCopy)) {
                System.out.println("Books added Successfully");
            }
        } else {
            System.out.println("Book already exists: add some copies");
        }
    }
}


