package de.hindenbug.dox.gen.TrainApp;


/**
* TrainApp/TrainStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Montag, 24. Juli 2017 16:03 Uhr MESZ
*/

public class TrainStatus implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static TrainStatus[] __array = new TrainStatus[__size];

  public static final int __new = 0;
  public static final TrainStatus _new = new TrainStatus(__new);
  public static final int _good = 1;
  public static final TrainStatus good = new TrainStatus(_good);
  public static final int _okay = 2;
  public static final TrainStatus okay = new TrainStatus(_okay);
  public static final int _bad = 3;
  public static final TrainStatus bad = new TrainStatus(_bad);

  public int value ()
  {
    return __value;
  }

  public static TrainStatus from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected TrainStatus (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class TrainStatus
