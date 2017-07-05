package net.sf.jftp.net;

import net.sf.jftp.util.*;
import net.sf.jftp.config.*;

import java.io.*;
import java.util.*;

public class Transfer implements Runnable
{      	

 private String host;
 private int port;
 private String localPath;
 private String remotePath;
 private String file;
 private String user;
 private String pass;
 private FtpConnection con = null;
 public boolean work = true;
 public boolean pause = false;
 private String type;

 public Thread runner;

 private int stat = 0;
 private boolean started = false;

 public static final String PAUSED = "STOPPED";
 public static final String QUEUED = "QUEUED";
 public static final String REMOVED = "REMOVED";

 public static final String UPLOAD = "UPLOAD";
 public static final String DOWNLOAD = "DOWNLOAD";

 private ConnectionHandler handler;
 private Vector listeners;


 public Transfer(String host, int port, String localPath, String remotePath, String file, String user, String pass, String type, ConnectionHandler handler, Vector listeners)
 {
 	this.host = host;
	this.port = port;
	this.localPath = localPath;
	this.remotePath = remotePath;
	this.file = file;
	this.user = user;
	this.pass = pass;
	this.type = type;
	this.handler = handler;
	this.listeners = listeners;
	if(handler == null)
	{
		handler = new ConnectionHandler();
	}

	prepare();
 }

 public void prepare()
 {
	runner = new Thread(this);
	runner.setPriority(Thread.MIN_PRIORITY);
	runner.start();
 }

 public void run()
 {
 	//System.out.println(file);
 	if(handler.getConnections().get(file) == null)
	{
		handler.addConnection(file, this);
	}
	else if(!pause) {
		Log.debug("Transfer already in progress: " + file);
		work = false;
		stat = 2;
		return;
	}

	boolean hasPaused = false;

	while (pause) {
		try {
			runner.sleep(100);

			if(listeners != null)
				 for(int i=0; i<listeners.capacity(); i++) ((ConnectionListener)listeners.elementAt(i)).updateProgress(file, PAUSED, -1);

			if(!work)
			{
			  if(listeners != null)
				 for(int i=0; i<listeners.capacity(); i++) ((ConnectionListener)listeners.elementAt(i)).updateProgress(file, REMOVED, -1);
			}
		}
		catch(Exception ex) {}

		hasPaused = true;
	}

 	while(handler.getConnectionSize() >= Settings.getMaxConnections() && handler.getConnectionSize() > 0 && work)
	{
		try
		{
			stat = 4;
			runner.sleep(400);
  			//if(!pause) con.fireProgressUpdate(null, file, QUEUED, -1);
			//else con.fireProgressUpdate(null, file, PAUSED, -1);

			if(!hasPaused && listeners != null)
				 for(int i=0; i<listeners.capacity(); i++) ((ConnectionListener)listeners.elementAt(i)).updateProgress(file, QUEUED, -1);
			else break;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	if(!work)
	{
		if(listeners != null)
			  for(int i=0; i<listeners.capacity(); i++) ((ConnectionListener)listeners.elementAt(i)).updateProgress(file, REMOVED, -1);

		handler.removeConnection(file);
		stat = 3;
		return;
	}

	started = true;

 	//Log.debug("Download started: " + file);
	//if(Settings.safeMode)
	//{
	try {runner.sleep(2000);}
	catch(Exception ex) {}
	//}

	con = new FtpConnection(host, port, remotePath);

	con.setConnectionHandler(handler);
	con.setConnectionListeners(listeners);

	String status = con.login(user, pass);

	if(status.equals(FtpConnection.LOGIN_OK))
	{
		//stat = 1;
		File f = new File(localPath);
        	con.setLocalPath(f.getAbsolutePath());
		//System.out.println(con.getLocalPath());

		if(type.equals(UPLOAD)) con.upload(file);
		else	con.download(file);
		//Log.debug("Download finished: " + file);
	}
	//else
	//{
		//stat = -1;
		//Log.debug("Download failed: " + file);
	//}

	if(!pause) handler.removeConnection(file);
 }
 
 public int getStatus()
 {
 	return stat;
 }
 
 public boolean hasStarted()
 {
 	return started;
 }

 public FtpConnection getFtpConnection()
 {
 	return con;
 }

 public DataConnection getDataConnection()
 {
 	return con.getDataConnection();
 }

}
