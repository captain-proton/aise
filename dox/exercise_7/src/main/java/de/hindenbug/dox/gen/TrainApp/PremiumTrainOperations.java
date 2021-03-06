package de.hindenbug.dox.gen.TrainApp;


/**
* TrainApp/PremiumTrainOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Montag, 24. Juli 2017 16:03 Uhr MESZ
*/

public interface PremiumTrainOperations  extends TrainOperations
{
  String name ();
  void setTemperature (org.omg.CORBA.FloatHolder temperature) throws AirconditionerException;
  void setDiffuseLightBrightness (org.omg.CORBA.FloatHolder brightness);
  void setChilloutMusic (float audioData);
} // interface PremiumTrainOperations
