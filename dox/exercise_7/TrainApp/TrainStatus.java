package TrainApp;


/**
* TrainApp/TrainStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./exercise_7.2.2.idl
* Dienstag, 27. Juni 2017 12:04 Uhr MESZ
*/

public class TrainStatus implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static TrainApp.TrainStatus[] __array = new TrainApp.TrainStatus [__size];

  public static final int __new = 0;
  public static final TrainApp.TrainStatus _new = new TrainApp.TrainStatus(__new);
  public static final int _good = 1;
  public static final TrainApp.TrainStatus good = new TrainApp.TrainStatus(_good);
  public static final int _okay = 2;
  public static final TrainApp.TrainStatus okay = new TrainApp.TrainStatus(_okay);
  public static final int _bad = 3;
  public static final TrainApp.TrainStatus bad = new TrainApp.TrainStatus(_bad);

  public int value ()
  {
    return __value;
  }

  public static TrainApp.TrainStatus from_int (int value)
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
