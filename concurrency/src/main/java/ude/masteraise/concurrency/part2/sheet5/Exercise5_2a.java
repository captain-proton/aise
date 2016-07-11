package ude.masteraise.concurrency.part2.sheet5;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_2a
{

    public static void main(String[] args)
    {
        Library library = new Library();

        List<Book> books = IntStream.range(0, 5)
                .mapToObj(i -> new Book(i))
                .collect(Collectors.toList());
        books.forEach(b -> library.allBooks.add(b));

        List<User> users = IntStream.range(0, 4)
                .mapToObj(i -> new User(i, library))
                .collect(Collectors.toList());

        users.forEach(Thread::start);
    }

    static class User extends Thread
    {
        private final Library library;

        public User(int id, Library library)
        {
            this.library = library;
            setName("user " + id);
        }

        @Override
        public void run()
        {
            IntStream.range(0, 10).forEach(this::runLoan);
        }

        protected void runLoan(int iteration)
        {
            Book book = library.getRandomBook();
            ThreadUtils.log(this, "wants", "book", book.id);

            // active wait that book is loaned
            while (!library.loan(book)) { }
            ThreadUtils.log(this, "loaned", "book", book.id);

            int readingTime = RandomUtils.nextInt(1500, 2000);
            ThreadUtils.log(this, "reading", "for", readingTime);
            ThreadUtils.sleepSilent(readingTime);

            ThreadUtils.log(this, "returning", "book", book.id);
            library.returnBook(book);
        }
    }
    
    static class Book
    {
        int id;

        public Book(int id)
        {
            this.id = id;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Book{");
            sb.append("id=").append(id);
            sb.append('}');
            return sb.toString();
        }
    }

    static class Library
    {
        private List<Book> allBooks;
        private Set<Book> loanedBooks;

        public Library()
        {
            allBooks = new ArrayList<>();
            loanedBooks = new HashSet<>();
        }

        Book getRandomBook()
        {
            return allBooks.get(RandomUtils.nextInt(0, allBooks.size()));
        }

        synchronized boolean loan(Book book)
        {
            if (loanedBooks.contains(book))
                return false;

            loanedBooks.add(book);
            return true;
        }

        synchronized void returnBook(Book book)
        {
            loanedBooks.remove(book);
        }
    }
}
