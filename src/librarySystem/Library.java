package librarySystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Library {

    // ==================== Fields ====================

    // Stores all library items using the item ID as the key
    private Map<String, LibraryItem> catalog;

    // Stores all members using the member ID as the key
    private Map<String, Member> members;

    // Stores the IDs of currently borrowed items without duplicates
    private Set<String> borrowedIds;


    // ==================== Constructor ====================

    public Library() {

        // Creates an empty catalog for library items
        this.catalog = new HashMap<>();

        // Creates an empty collection for registered members
        this.members = new HashMap<>();

        // Creates an empty set for currently borrowed item IDs
        this.borrowedIds = new HashSet<>();
    }


    // ==================== Add Methods ====================

    public void addItem(LibraryItem item) {

        // Stores the item using its automatically generated ID
        catalog.put(item.getId(), item);
    }

    public void addMember(Member member) {

        // Stores the member using the member ID
        members.put(member.getMemberId(), member);
    }


    // ==================== Borrow and Return Methods ====================

    public void borrowItem(String memberId, String itemId)
            throws LibraryException {

        // Searches for the member and item using their IDs
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        // Stops the operation if the member does not exist
        if (member == null) {
            throw new LibraryException(
                    "Member " + memberId + " was not found."
            );
        }

        // Stops the operation if the item does not exist
        if (item == null) {
            throw new LibraryException(
                    "Item " + itemId + " was not found."
            );
        }

        // Stops the operation if the item is already borrowed
        if (item.isBorrowed()) {
            throw new LibraryException(
                    "Item " + itemId + " is already out."
            );
        }

        // Stops the operation if the member reached the borrowing limit
        if (!member.canBorrowMore()) {
            throw new LibraryException(
                    "Member " + member.getName()
                            + " has reached the borrowing limit of "
                            + member.getMaxAllowed() + " items."
            );
        }

        // Updates the item, member, and borrowed IDs after validation
        item.markBorrowed();
        member.addBorrowedItem(item);
        borrowedIds.add(itemId);

        System.out.println(
                "Borrowed " + itemId + " to " + memberId + "."
        );
    }

    public void returnItem(String memberId, String itemId)
            throws LibraryException {

        // Searches for the member and item using their IDs
        Member member = members.get(memberId);
        LibraryItem item = catalog.get(itemId);

        // Stops the operation if the member does not exist
        if (member == null) {
            throw new LibraryException(
                    "Member " + memberId + " was not found."
            );
        }

        // Stops the operation if the item does not exist
        if (item == null) {
            throw new LibraryException(
                    "Item " + itemId + " was not found."
            );
        }

        // Ensures that this specific member has borrowed this item
        if (!member.hasBorrowedItem(item)) {
            throw new LibraryException(
                    "Member " + memberId
                            + " does not hold item " + itemId + "."
            );
        }

        // Updates the member, item status, and borrowed IDs
        member.removeBorrowedItem(item);
        item.markReturned();
        borrowedIds.remove(itemId);

        System.out.println(
                "Returned " + itemId + " from " + memberId + "."
        );
    }


    // ==================== Display Methods ====================

    public void listCatalog() {

        // Calls displayInfo() for every library item polymorphically
        for (LibraryItem item : catalog.values()) {
            item.displayInfo();
        }
    }

    public void printReport() {

        // Displays the current library statistics
        System.out.println("---------- REPORT ----------");
        System.out.println("Total items     : " + catalog.size());
        System.out.println("Currently out   : " + borrowedIds.size());
        System.out.println("Borrowed ids    : " + borrowedIds);

        // TODO: Add the count of items by type later

        System.out.println(
                "Total created   : "
                        + LibraryItem.getTotalItemsCreated()
        );
        System.out.println("----------------------------");
    }

}