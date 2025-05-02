package org.example.library_manager;
import org.example.library_manager.models.Book;
import org.example.library_manager.models.User;
import org.example.library_manager.services.BookService;
import org.example.library_manager.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LibraryConsoleApp implements CommandLineRunner {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public LibraryConsoleApp(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        User current_user = null;

        while (current_user == null) {
            System.out.println("=== Welcome to Library App ===");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Username: ");
                    String new_username = scanner.nextLine();
                    System.out.print("Password: ");
                    String new_password = scanner.nextLine();

                    if (userService.usernameExists(new_username)) {
                        System.out.println("Username already exists.");
                    }
                    else {
                        userService.register(new_username, new_password);
                        System.out.println("User successfully registered.");
                    }
                    break;

                case "2":
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    Optional<User> user = userService.login(username, password);

                    if (user.isPresent()) {
                        current_user = user.get();
                        System.out.println("Login successful. Welcome " + current_user.getUsername() + "!");
                    }
                    else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case "3":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        while (true) {
            System.out.println("1 - Add a new book");
            System.out.println("2 - List all books");
            System.out.println("3 - Delete a book");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter year published:");
                    int year = Integer.parseInt(scanner.nextLine());

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setYearPublished(year);
                    bookService.addBook(book);
                    System.out.println("Book added!");
                    break;

                case 2:
                    List<Book> books = bookService.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    }
                    else {
                        for (Book b : books) {
                            System.out.println(b.getId() + ": " + b.getTitle() + " by " + b.getAuthor());
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter Book ID to delete:");
                    Long id = Long.parseLong(scanner.nextLine());
                    bookService.deleteBook(id);
                    System.out.println("Book deleted!");
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}