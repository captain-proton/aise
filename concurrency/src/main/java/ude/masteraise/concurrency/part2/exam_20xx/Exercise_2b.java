package ude.masteraise.concurrency.part2.exam_20xx;

import java.util.concurrent.Semaphore;

/**
 * Created by nils on 08.08.16.
 */
public class Exercise_2b
{
    // (1)
    static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] unbenutzt)
    {
        ProzessABC P1 = new ProzessABC("P1", 2, 2);
        ProzessABC P2 = new ProzessABC("P2", 3);
        ProzessABC P3 = new ProzessABC("P3", 2, 1);

        P1.start();
        P2.start();
        P3.start();
    }
}

class ProzessABC extends Thread
{
    int Res1, Res2;
    boolean EineAktion = false;
    boolean ZweiAktionen = false;

    ProzessABC(String name, int Res1, int Res2)
    {

        this.setName(name);
        this.Res1 = Res1; //Ressource für Aktion 1
        this.Res2 = Res2; //Ressource für Aktion 2

        ZweiAktionen = true;
    }

    public ProzessABC(String name, int Res1)
    {

        this.setName(name);
        this.Res1 = Res1; //Ressource für Aktion 1
        EineAktion = true;

    }

    public void rund()
    {

        // (2)
        try
        {
            if (EineAktion)
            {

                // (3)
                Exercise_2b.semaphore.acquire(1);

                Aktion();

                // (4)
                Exercise_2b.semaphore.release(1);
            } else if (ZweiAktionen)
            {
                // (5)
                Exercise_2b.semaphore.acquire(2);

                Aktion();
                Aktion();

                // (6)
                Exercise_2b.semaphore.release(2);
            }
             // (7)
        } catch (InterruptedException e)
        {
        }

    }

    void Aktion()
    {
        System.out.println(this.getName() + " Aktion");
    } // Aktion, hier nicht zu programmieren.
}