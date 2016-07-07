package ude.masteraise.concurrency.part2.sheet4;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The solution to exercise 4.1.a contains a naive and a wrong approach to debit one account with two customers.
 * The shared resource is not synchronized therefore a write conflict may occur.
 */
public class Exercise4_1a
{

    public static void main(String[] args)
    {
        ThreadUtils.sout(Thread.currentThread(), "start");
        boolean conflictOccurred = false;

        /*
        run until a write conflict occurred on the account. this may run for an infinite amount of time. Add an
        additional check for example of a maximum number of runs.
         */
        while (!conflictOccurred)
        {
            // shared resource between customers
            Account a = new Account(1000);

            // start customers
            Stream.of("1", "2")
                    .map(number -> new Customer("Customer " + number, a))
                    .forEach(Exercise4_1a::runCustomer);
            conflictOccurred = a.credit != 0;
        }
    }

    /**
     * Start given customer and wait for it to finish.
     */
    static boolean runCustomer(Customer c)
    {
        c.start();
        ThreadUtils.joinSilent(c);
        if (c.account.credit != 0)
        {
            ThreadUtils.sout(c, "conflict");
            ThreadUtils.sout(c, "final", "account", c.account.credit);
        }
        return c.account.credit != 0;
    }

    /**
     * A <code>Customer</code> is a thread that debits amounts of an {@linkplain Account}.
     */
    static class Customer extends Thread
    {
        private final Account account;
        private long startTime;

        public Customer(String name, Account account)
        {
            super(name);
            this.account = account;
        }

        @Override
        public void run()
        {
            startTime = System.currentTimeMillis();

            /*
            debit amounts from 1 to 50. If the final account credit is not 0 then write errors occured.
             */
            IntStream.range(1, 1010).forEach(i -> account.debit(1));

            ThreadUtils.sleepSilent(50);
        }

        private long getRunTime() {
            return System.currentTimeMillis() - startTime;
        }
    }

    /**
     * An <code>Account</code> is a simple container that contains an amount of credit customers may debit.
     */
    static class Account extends Thread
    {
        private int credit;

        public Account(int credit)
        {
            this.credit = credit;
        }

        /**
         * Tries to debit target amount on this {@linkplain #credit}. The credit can only be decreased if given amount
         * is less or equal than the credit.
         */
        public void debit(int amount)
        {
            if (canDebit(amount))
                credit  = credit - amount;
        }

        private boolean canDebit(int amount)
        {
            return credit >= amount;
        }
    }
}
