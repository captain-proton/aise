package de.hindenbug.dox.gen.TrainApp;


/**
* TrainApp/CheapTrainOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Montag, 24. Juli 2017 16:03 Uhr MESZ
*/

public interface CheapTrainOperations  extends TrainOperations
{
  String[] errors ();
  void errors (String[] newErrors);
  void addError (String error, org.omg.CORBA.ShortHolder errors) throws SevereMalfunctionException;
} // interface CheapTrainOperations