package TrainApp;

/**
* TrainApp/TrainHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./exercise_7.2.2.idl
* Dienstag, 27. Juni 2017 12:04 Uhr MESZ
*/

public final class TrainHolder implements org.omg.CORBA.portable.Streamable
{
  public TrainApp.Train value = null;

  public TrainHolder ()
  {
  }

  public TrainHolder (TrainApp.Train initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TrainApp.TrainHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TrainApp.TrainHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TrainApp.TrainHelper.type ();
  }

}