package librarySystem;

public abstract class LibraryItem {

    // ==================== Fields ====================

    private String id;
    private String title;
    private boolean borrowed;

    // Shared counter that stores the total number of created library items
    private static int totalItemsCreated;

    // Shared counter used to generate unique IDs such as ITEM-1 and ITEM-2
    private static int nextNumber = 1;


    // ==================== Constructor ====================

    public LibraryItem(String title) {

        // Calls setTitle() to validate and store the title
        setTitle(title);

        // Generates the item ID automatically using nextNumber
        this.id = "ITEM-" + nextNumber;

        // Prepares the next unique number for the next library item
        nextNumber++;

        // Increases the total number of created library items
        totalItemsCreated++;

        // Every new library item starts as available
        this.borrowed = false;
    }


    // ==================== Getters ====================

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public static int getTotalItemsCreated() {
        return totalItemsCreated;
    }


    // ==================== Setter and Validation ====================

    public void setTitle(String title) {

        // Rejects null, empty text, or text containing spaces only
        if (title == null || title.trim().isEmpty()) {

            // Stops the operation and explains why the title is invalid
            throw new IllegalArgumentException(
                    "Title cannot be null or empty."
            );
        }

        // Stores the title only after it passes validation
        this.title = title;
    }


    // ==================== Concrete Methods ====================

    public void markBorrowed() {

        // Changes the item status from available to borrowed
        this.borrowed = true;
    }

    public void markReturned() {

        // Changes the item status from borrowed to available
        this.borrowed = false;
    }

    public void displayInfo() {

        // Displays the common information for any type of library item
        System.out.println(
                getId() + " | " + getTitle()
                        + " | " + getType()
                        + " | loan: " + getLoanPeriodDays() + " days"
                        + " | " + (isBorrowed() ? "OUT" : "available")
        );
    }


    // ==================== Abstract Methods ====================

    // Every subclass must return its own borrowing period
    public abstract int getLoanPeriodDays();

    // Every subclass must return its own item type
    public abstract String getType();
}