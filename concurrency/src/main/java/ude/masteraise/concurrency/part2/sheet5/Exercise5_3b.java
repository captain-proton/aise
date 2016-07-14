package ude.masteraise.concurrency.part2.sheet5;

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
    private static final Bank bank = new Bank();
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
            //            synchronized (Account.class)
            //            {
            //                src.transfer(dest, TRANSFER_AMOUNT);
            //            }
            synchronized (bank)
            {
                while (!bank.isFree(src, dest))
                {
                    ThreadUtils.log(this, "notFree", "waiting");
                    ThreadUtils.waitSilent(bank);
                }
                Transfer transfer = bank.startTransfer(src, dest);
                src.transfer(dest, TRANSFER_AMOUNT);
                bank.finishTransfer(transfer);
            }
        }
    }

    static class Bank
    {
        private final List<Transfer> activeTransfers = Collections.synchronizedList(new ArrayList<>());

        private synchronized boolean isFree(Account a1, Account a2)
        {
            return !activeTransfers.stream()
                    .filter(t ->
                            t.src.equals(a1)
                                    || t.dest.equals(a1)
                                    || t.src.equals(a2)
                                    || t.dest.equals(a2))
                    .findAny()
                    .isPresent();
        }

        public synchronized Transfer startTransfer(Account src, Account dest)
        {
            Transfer transfer = new Transfer(src, dest);
            activeTransfers.add(transfer);
            return transfer;
        }

        public synchronized void finishTransfer(Transfer transfer)
        {
            activeTransfers.remove(transfer);
            notify();
        }
    }

    static class Transfer
    {
        Account src;
        Account dest;

        public Transfer(Account src, Account dest)
        {
            this.src = src;
            this.dest = dest;
        }
    }
}
