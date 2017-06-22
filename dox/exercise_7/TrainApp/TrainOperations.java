package TrainApp;


/**
* TrainApp/TrainOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from exercise_7.2.2.idl
* Dienstag, 20. Juni 2017 15:22 Uhr MESZ
*/

public interface TrainOperations 
{
  int seats ();
  float maxSpeed ();
  TrainApp.TrainStatus status ();
  void status (TrainApp.TrainStatus newStatus);
  void increaseSpeed (int increment) throws TrainApp.IllegalSpeedException;
  void decreaseSpeed (int decrement) throws TrainApp.IllegalSpeedException;
  int getSpeed ();
} // interface TrainOperations
