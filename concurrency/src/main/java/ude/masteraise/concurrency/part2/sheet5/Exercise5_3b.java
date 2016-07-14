package ude.masteraise.concurrency.part2.sheet5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_3b extends Exercise5_3a
{

    private static final int TRANSFER_AMOUNT = 100;

    public static void main(String[] args)
    {
        int credit = 500;
        List<Account> accounts = IntStream.range(0, 3)
                .mapToObj(i -> new Account(i, credit + i * 200))
                .collect(Collectors.toList());

        IntStream.range(0, 3)
                .mapToObj(i -> new User(i, accounts))
                .forEach(Thread::start);
    }

    static class User extends Exercise5_3a.User
    {
        public User(int id, List<Account> accounts)
        {
            super(id, accounts);
            setName("user " + id);
        }

        protected void transfer(Account src, Account dest, int amount)
        {
            /*
            This is the simplest solution to the deadlock in Exercise 5.3.a but it is also the worst solution.
            It would be better to only block the two needed accounts for the transfer. Other users are not able
            to perform a transfer between accounts even if these accounts are not src and dest.
             */
            synchronized (Account.class)
            {
                src.transfer(dest, TRANSFER_AMOUNT);
            }
        }
    }
}
