package librarySystem;

public class Magazine extends LibraryItem {

    // ==================== Fields ====================

    // Stores the issue number of the magazine
    private int issueNumber;


    // ==================== Constructor ====================

    public Magazine(String title, int issueNumber) {

        // Sends the title to the LibraryItem constructor
        // to validate it, generate the ID, and set borrowed to false
        super(title);

        // Stores the magazine issue number
        this.issueNumber = issueNumber;
    }


    // ==================== Getters and Setters ====================

    public int getIssueNumber() {

        // Returns the magazine issue number
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {

        // Updates the magazine issue number
        this.issueNumber = issueNumber;
    }


    // ==================== Overridden Methods ====================

    @Override
    public int getLoanPeriodDays() {

        // A magazine can be borrowed for 7 days
        return 7;
    }

    @Override
    public String getType() {

        // Returns the library item type
        return "Magazine";
    }
}