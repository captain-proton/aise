package de.hindenbug.dox.gen.TrainApp;

/**
* TrainApp/PremiumTrainHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Montag, 24. Juli 2017 16:03 Uhr MESZ
*/

public final class PremiumTrainHolder implements org.omg.CORBA.portable.Streamable
{
  public PremiumTrain value = null;

  public PremiumTrainHolder ()
  {
  }

  public PremiumTrainHolder (PremiumTrain initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = PremiumTrainHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    PremiumTrainHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return PremiumTrainHelper.type ();
  }

}