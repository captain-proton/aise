package TrainApp;


/**
* TrainApp/CheapTrainOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Dienstag, 20. Juni 2017 15:22 Uhr MESZ
*/

public interface CheapTrainOperations  extends TrainApp.TrainOperations
{
  String[] errors ();
  void errors (String[] newErrors);
  void addError (String error, org.omg.CORBA.ShortHolder errors) throws TrainApp.SevereMalfunctionException;
} // interface CheapTrainOperations
