package ude.masteraise.concurrency.part2.sheet4;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.Arrays;

/**
 * Created by nils on 30.06.16.
 */
public class Exercise4_1
{

    public static void main(String[] args)
    {
        // shared resource between customers
        Account a = new Account(1000);

        // how much each customer debit of given account
        int[] amounts = {50, 100, 150};

        Customer[] customers = {
                new Customer("Customer1", a, amounts),
                new Customer("Customer2", a, amounts)
        };

        // start each customer
        Arrays.stream(customers).forEach(Thread::start);
    }

    /**
     * A <code>Customer</code> is a thread that debits amounts of an {@linkplain Account}.
     */
    static class Customer extends Thread
    {
        private final Account account;
        private long startTime;
        private int[] amounts;

        public Customer(String name, Account account, int... amounts)
        {
            super(name);
            this.account = account;
            this.amounts = amounts;
        }

        @Override
        public void run()
        {
            startTime = System.currentTimeMillis();

            // debit each amount
            Arrays.stream(amounts).forEach(this::debit);

            // print out final credit after each debit has finished on this customer
            ThreadUtils.sout(this, "done", "credit", account.getCredit(), getRunTime());
        }

        public void debit(int amount)
        {
            // debit (synchronized)
            account.debit(amount);
            ThreadUtils.sleepSilent(100);
            ThreadUtils.sout(this, "debit", "amount", amount, getRunTime());

            // print current credit
            int credit = account.getCredit();
            ThreadUtils.sleepSilent(50);
            ThreadUtils.sout(this, "debit", "credit", credit, getRunTime());
        }

        private long getRunTime() {
            return System.currentTimeMillis() - startTime;
        }
    }

    static class Account extends Thread
    {
        private int credit;

        public Account(int credit)
        {
            this.credit = credit;
        }

        /**
         * Tries to debit target amount on this {@linkplain #credit}. The credit can only be decreased if given amount
         * is less or equal than the credit. The access to the credit is synchronized.
         */
        public synchronized void debit(int amount)
        {
            // canDebit must be synchronized with the decrease, otherwise it may lead to a wrong credit
            if (canDebit(amount))
                credit -= amount;
        }

        private boolean canDebit(int amount)
        {
            return credit >= amount;
        }

        public int getCredit()
        {
            return credit;
        }
    }
}
