package ude.masteraise.concurrency.part2.sheet5;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import ude.masteraise.concurrency.part2.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nils on 11.07.16.
 */
public class Exercise5_3a
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

    static class User extends Thread
    {
        private final List<Account> accounts;

        public User(int id, List<Account> accounts)
        {
            this.accounts = accounts;
            setName("user " + id);
        }

        @Override
        public void run()
        {
            IntStream.range(0, Integer.MAX_VALUE).forEach(this::transfer);
        }

        private void transfer(int iteration)
        {
            Pair<Account, Account> accounts = getRandomAccounts();
            Account src = accounts.getLeft();
            Account dest = accounts.getRight();

            ThreadUtils.log(Thread.currentThread(),
                    String.format("start_%-10d", iteration),
                    String.format("%-2d -> %-2d; amount: %5d", src.id, dest.id, TRANSFER_AMOUNT));
            ThreadUtils.log(this,
                    String.format("%-16s", "accounts"),
                    StringUtils.join(this.accounts.stream().map(Account::toString).collect(Collectors.toList()), ", "));
            ThreadUtils.log(this,
                    String.format("%-16s", "transfer"),
                    String.format("src: %-5d -> dest: %-5d", src.id, dest.id));
            src.transfer(dest, TRANSFER_AMOUNT);
        }

        private Pair<Account, Account> getRandomAccounts()
        {
            Account from = accounts.get(RandomUtils.nextInt(0, accounts.size()));
            List<Account> others = accounts.stream()
                    .filter(a -> a != from)
                    .collect(Collectors.toList());
            Account to = others.get(RandomUtils.nextInt(0, others.size()));
            return Pair.of(from, to);
        }
    }

    static class Account
    {
        private int id;
        private int credit;

        public Account(int id, int credit)
        {
            this.id = id;
            this.credit = credit;
        }

        synchronized void transfer(Account account, int amount)
        {
            credit = account.credit(credit, amount);
        }

        synchronized int credit(int credit, int amount)
        {
            this.credit = this.credit + amount;
            return credit - amount;
        }

        @Override
        public String toString()
        {
            return String.format("account %1d = %5d", id, credit);
        }
    }
}
