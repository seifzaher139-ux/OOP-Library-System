package librarySystem;

import java.util.ArrayList;
import java.util.List;

public class Member {

    // ==================== Fields ====================

    // Stores the unique ID of the member
    private String memberId;

    // Stores the member's name
    private String name;

    // Stores the maximum number of items the member can borrow
    private int maxAllowed;

    // Stores all library items currently borrowed by this member
    // The LibraryItem type allows the list to contain Books, Magazines, and DVDs
    private List<LibraryItem> borrowedItems;


    // ==================== Constructor ====================

    public Member(String memberId, String name, int maxAllowed) {

        // Uses the setters to validate and store the member's data
        setMemberId(memberId);
        setName(name);
        setMaxAllowed(maxAllowed);

        // Creates an empty list because a new member
        // has not borrowed any items yet
        this.borrowedItems = new ArrayList<>();
    }


    // ==================== Getters and Setters ====================

    public String getMemberId() {

        // Returns the member's unique ID
        return memberId;
    }

    public void setMemberId(String memberId) {

        // Stores the member ID
        this.memberId = memberId;
    }

    public String getName() {

        // Returns the member's name
        return name;
    }

    public void setName(String name) {

        // Rejects null, empty text, or text containing spaces only
        if (name == null || name.trim().isEmpty()) {

            // Stops the operation when the member name is invalid
            throw new IllegalArgumentException(
                    "Name cannot be null or empty."
            );
        }

        // Stores the name only after it passes validation
        this.name = name;
    }

    public int getMaxAllowed() {

        // Returns the maximum number of items
        // that the member is allowed to borrow
        return maxAllowed;
    }

    public void setMaxAllowed(int maxAllowed) {

        // Rejects zero and negative borrowing limits
        if (maxAllowed <= 0) {

            // Stops the operation when the borrowing limit is invalid
            throw new IllegalArgumentException(
                    "The maximum allowed items must be greater than zero."
            );
        }

        // Stores the borrowing limit only afterohner Blur rs only after it passes validation
        this.maxAllowed = maxAllowed;
    }



    // ==================== Member Methods ====================

    public int getBorrowedCount() {

        // Returns the number of items currently borrowed by the member
        return borrowedItems.size();
    }

    //    public boolean canBorrowMore(){
//        if (getBorrowedCount()<getMaxAllowed()){
//            return true;
//        }else return false;
//    }
    public boolean canBorrowMore() {

        // Returns true if the member has not reached the borrowing limit
        return getBorrowedCount() < getMaxAllowed();
    }


// ==================== Helper Methods ====================

    public void addBorrowedItem(LibraryItem item) {

        // Adds the borrowed item to the member's borrowed items list
        borrowedItems.add(item);
    }

    public boolean hasBorrowedItem(LibraryItem item) {

        // Returns true if the member has borrowed the specified item
        return borrowedItems.contains(item);
    }

    public void removeBorrowedItem(LibraryItem item) {

        // Removes the returned item from the member's borrowed items list
        borrowedItems.remove(item);
    }

}