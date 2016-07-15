package ude.masteraise.concurrency.part2.sheet5;

import org.apache.commons.lang3.RandomUtils;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_3b extends Exercise5_3a
{
    private static final List<Account> ACTIVE_ACCOUNTS = Collections.synchronizedList(new ArrayList<>());
    private static final int TRANSFER_AMOUNT = 100;

    public static void main(String[] args)
    {
        int credit = 500;
        List<Account> accounts = IntStream.range(0, 3)
                .mapToObj(i -> new Account(i, credit + i * 200))
                .collect(Collectors.toList());

        IntStream.range(0, 10)
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
//            synchronized (Account.class)
//            {
//                src.transfer(dest, TRANSFER_AMOUNT);
//            }
            try
            {

                while (!impendingTransfer(src, dest))
                {
                    int sleepTime = RandomUtils.nextInt(0, 10);
                    ThreadUtils.log(this, "blocked", "sleeping", sleepTime);
                    ThreadUtils.sleepSilent(sleepTime);
                }
                src.transfer(dest, TRANSFER_AMOUNT);
            } finally
            {
                synchronized (ACTIVE_ACCOUNTS)
                {
                    ACTIVE_ACCOUNTS.remove(src);
                    ACTIVE_ACCOUNTS.remove(dest);
                    yield();
                }
            }

        }

        private boolean impendingTransfer(Account src, Account dest)
        {

            boolean srcBlocked = false;
            boolean destBlocked = false;

            synchronized (ACTIVE_ACCOUNTS)
            {
                if (!ACTIVE_ACCOUNTS.contains(src))
                    srcBlocked = ACTIVE_ACCOUNTS.add(src);
            }
            if (srcBlocked)
            {
                synchronized (ACTIVE_ACCOUNTS)
                {
                    if (!ACTIVE_ACCOUNTS.contains(dest))
                        destBlocked = ACTIVE_ACCOUNTS.add(dest);
                    else
                        ACTIVE_ACCOUNTS.remove(src);
                }
            }
            return srcBlocked && destBlocked;
        }
    }
}
