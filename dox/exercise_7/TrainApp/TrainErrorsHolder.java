package TrainApp;


/**
* TrainApp/TrainErrorsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./exercise_7.2.2.idl
* Dienstag, 27. Juni 2017 12:04 Uhr MESZ
*/

public final class TrainErrorsHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public TrainErrorsHolder ()
  {
  }

  public TrainErrorsHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TrainApp.TrainErrorsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TrainApp.TrainErrorsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TrainApp.TrainErrorsHelper.type ();
  }

}