package librarySystem;

public class DVD extends LibraryItem {

    // ==================== Fields ====================

    // Stores the DVD runtime in minutes
    private int runtimeMinutes;


    // ==================== Constructor ====================

    public DVD(String title, int runtimeMinutes) {

        // Sends the title to the parent constructor for validation,
        // automatic ID generation, and initial availability status
        super(title);

        // Calls the setter to validate and store the runtime
        setRuntimeMinutes(runtimeMinutes);
    }


    // ==================== Getters and Setters ====================

    public int getRuntimeMinutes() {

        // Returns the DVD runtime in minutes
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {

        // Rejects zero and negative runtime values
        if (runtimeMinutes <= 0) {

            // Stops the operation because the runtime is invalid
            throw new IllegalArgumentException(
                    "Runtime minutes must be greater than zero."
            );
        }

        // Stores the runtime only after it passes validation
        this.runtimeMinutes = runtimeMinutes;
    }


    // ==================== Overridden Methods ====================

    @Override
    public int getLoanPeriodDays() {

        // A DVD can be borrowed for 3 days
        return 3;
    }

    @Override
    public String getType() {

        // Returns the library item type
        return "DVD";
    }
}