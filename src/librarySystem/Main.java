package librarySystem;

import java.util.Scanner;

public class Main {

    // ==================== Field ====================

    // Reads all input entered by the user
    private static final Scanner scanner = new Scanner(System.in);


    // ==================== Main Method ====================

    public static void main(String[] args) {

        // Creates one empty library for the entire program
        Library library = new Library();

        int choice;

        do {

            displayMenu();
            choice = readInteger("Enter choice: ");

            try {

                switch (choice) {

                    case 1:
                        addItem(library);
                        break;

                    case 2:
                        addMember(library);
                        break;

                    case 3:
                        borrowItem(library);
                        break;

                    case 4:
                        returnItem(library);
                        break;

                    case 5:
                        library.listCatalog();
                        break;

                    case 6:
                        library.printReport();
                        break;

                    case 7:
                        System.out.println(
                                "Thank you for using the Library Lending System."
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please choose from 1 to 7."
                        );
                }

            } catch (LibraryException exception) {

                // Displays library operation errors without crashing
                System.out.println(
                        "Library error: " + exception.getMessage()
                );

            } catch (IllegalArgumentException exception) {

                // Displays constructor and setter validation errors
                System.out.println(
                        "Invalid data: " + exception.getMessage()
                );
            }

        } while (choice != 7);

        scanner.close();
    }


    // ==================== Menu Method ====================

    public static void displayMenu() {

        System.out.println();
        System.out.println("===== Library Lending System =====");
        System.out.println("1. Add Item");
        System.out.println("2. Add Member");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return Item");
        System.out.println("5. List Catalog");
        System.out.println("6. Report");
        System.out.println("7. Exit");
    }


    // ==================== Add Item Method ====================

    public static void addItem(Library library) {

        System.out.println("Choose item type:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");

        int itemType = readInteger("Enter item type: ");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        LibraryItem item;

        switch (itemType) {

            case 1:
                System.out.print("Author: ");
                String author = scanner.nextLine();

                int pages = readInteger("Number of pages: ");

                item = new Book(title, author, pages);
                break;

            case 2:
                int issueNumber = readInteger("Issue number: ");

                item = new Magazine(title, issueNumber);
                break;

            case 3:
                int runtimeMinutes =
                        readInteger("Runtime in minutes: ");

                item = new DVD(title, runtimeMinutes);
                break;

            default:
                System.out.println("Invalid item type.");
                return;
        }

        library.addItem(item);

        System.out.println(
                "Item added successfully with ID: " + item.getId()
        );
    }


    // ==================== Add Member Method ====================

    public static void addMember(Library library) {

        System.out.print("Member id: ");
        String memberId = scanner.nextLine();

        System.out.print("Member name: ");
        String name = scanner.nextLine();

        int maxAllowed =
                readInteger("Maximum allowed items: ");

        Member member =
                new Member(memberId, name, maxAllowed);

        library.addMember(member);

        System.out.println(
                "Member " + memberId + " added successfully."
        );
    }


    // ==================== Borrow Method ====================

    public static void borrowItem(Library library)
            throws LibraryException {

        System.out.print("Member id: ");
        String memberId = scanner.nextLine();

        System.out.print("Item id: ");
        String itemId = scanner.nextLine();

        library.borrowItem(memberId, itemId);
    }


    // ==================== Return Method ====================

    public static void returnItem(Library library)
            throws LibraryException {

        System.out.print("Member id: ");
        String memberId = scanner.nextLine();

        System.out.print("Item id: ");
        String itemId = scanner.nextLine();

        library.returnItem(memberId, itemId);
    }


    // ==================== Input Validation Method ====================

    public static int readInteger(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {

                // Converts the entered text into an integer
                return Integer.parseInt(input);

            } catch (NumberFormatException exception) {

                // Keeps asking instead of allowing the program to crash
                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }
}