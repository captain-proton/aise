package de.hindenbug.dox.gen.TrainApp;

/**
* TrainApp/IllegalSpeedExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Montag, 24. Juli 2017 16:03 Uhr MESZ
*/

public final class IllegalSpeedExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public IllegalSpeedException value = null;

  public IllegalSpeedExceptionHolder ()
  {
  }

  public IllegalSpeedExceptionHolder (IllegalSpeedException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = IllegalSpeedExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    IllegalSpeedExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return IllegalSpeedExceptionHelper.type ();
  }

}
