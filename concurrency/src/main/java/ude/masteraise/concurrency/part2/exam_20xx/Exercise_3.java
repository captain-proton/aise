package ude.masteraise.concurrency.part2.exam_20xx;

/**
 * Created by nils on 08.08.16.
 */
public class Exercise_3
{
    static long Startzeit = System.nanoTime();

    public static void main(String[] unbenutzt)
    {

        Monitor m1 = new Monitor("M1");
        Monitor m2 = new Monitor("M2");

        new Prozess(1, m1, m2).start();
        new Prozess(3, m1, m2).start();
        new Prozess(5, m1, m2).start();

    }
}

class Monitor
{

    String m;
    int n = 0;

    Monitor(String m)
    {
        this.m = m;
    }

    synchronized void Eintritt(int index)
    {

        Ausgabe("P" + index + " IN " + m + ", n = " + n);
        n += 2;
        while (n < 3)
            try
            {
                Ausgabe("P" + index + " Wartet " + m + ", n = " + n);
                wait();
            } catch (InterruptedException e)
            {
            }

        if (n >= 4 && n < 10) n += 2;
        else n -= 3;

        Ausgabe("P" + index + " OUT " + m + ", n = " + n);
        notifyAll();

    }

    void Ausgabe(String Meldung) // Zeit in Millisekunden.

    {
        long Zeit = (System.nanoTime() - Exercise_3.Startzeit) / 1000000L;
        System.out.println(Zeit + " " + Meldung);

    }

}

class Prozess extends Thread
{
    int index;
    Monitor m1, m2;

    Prozess(int index, Monitor m1, Monitor m2)
    {
        this.index = index;

        this.m1 = m1;
        this.m2 = m2;

    }

    public void run()
    {
        try
        {
            Thread.sleep(100 * index);
        } catch (InterruptedException e)
        {
        }

        m1.Eintritt(index);

        try
        {
            Thread.sleep(100 * index);
        } catch (InterruptedException e)
        {
        }

        m2.Eintritt(index);

    }

}