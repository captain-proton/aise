package ude.masteraise.concurrency.part2.sheet4;

import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.stream.Stream;

/**
 * Created by nils on 30.06.16.
 */
public class Exercise4_1
{

    public static void main(String[] args)
    {
        // shared resource between customers
        Account a = new Account(1000);

        Stream.of("1", "2")
                .map(number -> new Customer("Customer_" + number, a))
                .forEach(Thread::start);
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

            // 100  customer:  debit 50
            ThreadUtils.sleepSilent(100);
            account.debit(50);
            ThreadUtils.sout(this, "debit", "account", 50, getRunTime());

            // 200  customer:  amount 950
            ThreadUtils.sleepSilent(100);
            ThreadUtils.sout(this, "amount", "account", account.getCredit(), getRunTime());

            // 300  customer:  debit 100
            ThreadUtils.sleepSilent(100);
            account.debit(100);
            ThreadUtils.sout(this, "debit", "account", 100, getRunTime());

            // 350  customer:  amount 850
            ThreadUtils.sleepSilent(50);
            ThreadUtils.sout(this, "amount", "account", account.getCredit(), getRunTime());

            // 500  customer:  debit 150
            ThreadUtils.sleepSilent(150);
            account.debit(150);
            ThreadUtils.sout(this, "debit", "account", 150, getRunTime());

            // 600  customer:  amount 550
            ThreadUtils.sleepSilent(100);
            ThreadUtils.sout(this, "amount", "account", account.getCredit(), getRunTime());

            // 650  customer‐‐‐> amount 550
            ThreadUtils.sleepSilent(50);
            ThreadUtils.sout(this, "final", "account", account.getCredit(), getRunTime());
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
