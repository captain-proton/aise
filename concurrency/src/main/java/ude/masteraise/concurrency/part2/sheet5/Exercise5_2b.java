package ude.masteraise.concurrency.part2.sheet5;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_2b extends Exercise5_2a
{

    public static void main(String[] args)
    {
        Exercise5_2b.Library library = new Exercise5_2b.Library();

        List<Book> books = IntStream.range(0, 5)
                .mapToObj(i -> new Exercise5_2b.Book(i))
                .collect(Collectors.toList());
        books.forEach(b -> library.allBooks.add(b));

        List<Exercise5_2b.User> users = IntStream.range(0, 4)
                .mapToObj(i -> new Exercise5_2b.User(i, library))
                .collect(Collectors.toList());

        users.forEach(Thread::start);
    }

    static class User extends Exercise5_2a.User
    {
        private final Library library;

        public User(int id, Library library)
        {
            super(id, library);
            this.library = library;
        }

        @Override
        protected void runLoan(int iteration)
        {
            Book book = library.getRandomBook();
            ThreadUtils.log(this, "wants", "book", book.id);

            /*
            synchronize access to the entire library. this is pretty bad because the user has to wait for the library
            to notify. it would be better to synchronize the access to the book itself
             */
            synchronized (library)
            {
                while (!library.loan(book)) {
                    ThreadUtils.waitSilent(library);
                }
            }
            ThreadUtils.log(this, "loaned", "book", book.id);

            int readingTime = RandomUtils.nextInt(1500, 2000);
            ThreadUtils.log(this, "reading", "for", readingTime);
            ThreadUtils.sleepSilent(readingTime);

            ThreadUtils.log(this, "returning", "book", book.id);
            library.returnBook(book);
        }
    }

    static class Library extends Exercise5_2a.Library
    {
        private Set<Book> loanedBooks;
        private List<Book> allBooks;

        public Library()
        {
            loanedBooks = new HashSet<>();
            allBooks = new ArrayList<>();
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
            notify();
        }
    }
}
