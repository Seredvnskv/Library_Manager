package org.example.library_manager;
import org.example.library_manager.models.Book;
import org.example.library_manager.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class LibraryConsoleApp implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public LibraryConsoleApp(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book\n2. List Books\n3. Delete Book\n4. Exit");
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
                    for (Book b : books) {
                        System.out.println(b.getId() + ": " + b.getTitle() + " by " + b.getAuthor());
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