/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.jftp.net;

import net.sf.jftp.config.Settings;
import net.sf.jftp.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;

public class DataConnection implements Runnable
{
    private BufferedInputStream in;
    private BufferedOutputStream out;
    private Thread reciever;
    private int port = 7000;
    public Socket sock = null;
    private ServerSocket ssock = null;
    private String type;
    private String file;
    private String host;
    private boolean resume = false;
    public boolean finished = false;
    private boolean isThere = false;
    private long start;
    private FtpConnection con;

    public final static String GET = "GET";
    public final static String PUT = "PUT";
    public final static String FAILED = "FAILED";
    public final static String FINISHED = "FINISHED";
    public final static String DFINISHED = "DFINISHED";
    public final static String GETDIR = "DGET";
    public final static String PUTDIR = "DPUT";

    public DataConnection(FtpConnection con, int port, String host, String file, String type)
    {
	this.con = con;
        this.file = file;
        this.host = host;
        this.port = port;
        this.type = type;
        reciever = new Thread(this);
        reciever.start();
    }

    public DataConnection(FtpConnection con, int port, String host, String file, String type, boolean resume)
    {
	this.con = con;
        this.file = file;
        this.host = host;
        this.port = port;
        this.type = type;
        this.resume = resume;
        resume = false;
        reciever = new Thread(this);
        reciever.start();
    }

    public void run()
    {
        try
        {
            boolean ok = true;
            if (Settings.getFtpPasvMode())
            {
                try
                {
                    sock = new Socket(host,port);
                }
                catch(Exception ex)
                {
                    ok = false;
                    debug("Can't open Socket on port "+port);
                }
            }
            else
            {
                //Log.debug("trying new server socket: "+port);
                try
                {
                    ssock = new ServerSocket(port);
                }
                catch(Exception ex)
                {
                    ok = false;
                    Log.debug("Can't open ServerSocket on port "+port);
                }
            }

        }
        catch(Exception ex)
        {
            debug(ex.toString());
        }

        isThere = true;
	boolean ok = true;
	
        try
        {

            if (!Settings.getFtpPasvMode())
            {
                int retry = 0;
                while ((retry++ < 5) && (sock == null)) {
                    try {
                        ssock.setSoTimeout(Settings.getSocketTimeout());
                        sock = ssock.accept();
                    } catch (IOException e) {
                        sock = null;
			debug("Got IOException while trying to open a socket!");
			
                        if (retry == 5)
			    debug("Connection failed, tried 5 times - maybe try a higher timeout in Settings.java...");
                            throw e;
                    } finally {
                        ssock.close();
                    }
		    debug("Attempt timed out, retrying...");
                }
            }


            if(ok)
            {
                isThere = true;
		byte[] buf = new byte[Settings.bufferSize];
		start = System.currentTimeMillis();
		int buflen = 0;

                if(type.equals(GET) || type.equals(GETDIR))
                {
                    RandomAccessFile fOut = null;
                    BufferedOutputStream bOut = null;

                    try
                    {
                        if(resume)
                        {
                            File f = new File(file);
                            fOut = new RandomAccessFile(file,"rw");
                            fOut.skipBytes((int)f.length());
			    buflen = (int) f.length();
                        }
                        else
                        {
                            File f = new File(file);
                            if(f.exists())
                            {
                                f.delete();
                            }
                            bOut = new BufferedOutputStream(new FileOutputStream(file), Settings.bufferSize);
                        }
                    }
                    catch(Exception ex)
                    {
                        debug("Can't create outputfile: " + file);
			ok = false;
                        ex.printStackTrace();
                    }

                    if(ok)
                    {
                        try
                        {
                            in = new BufferedInputStream(sock.getInputStream(), Settings.bufferSize);
                        }
                        catch(Exception ex)
                        {
                            ok = false;
                            debug("Can't get InputStream");
                        }

                        if(ok)
                        {
                            try
                            {
                                int len = buflen;
                                if(fOut != null)
                                {

                                    while(true)
                                    {
                                        // resuming
                                        int read = in.read(buf);
                                        len += read;
                                        if(read == -1)
                                            break;

                                        fOut.write(buf,0,read);
					con.fireProgressUpdate(file, type, len);


                                        if(time())
                                        {
                                           // Log.debugSize(len, true, false, file);
                                        }
                                        if(read == StreamTokenizer.TT_EOF)
                                        {
                                            break;
                                        }
                                    }
                                    fOut.close();
				    //Log.debugSize(len, true, true, file);
                                }
                                else
                                {
                                    // no resuming
                                    while(true)
                                    {

                                        //System.out.println(".");
                                        int read = in.read(buf);
                                        len += read;
                                        if(read == -1)
                                            break;

                                        bOut.write(buf,0,read);
					con.fireProgressUpdate(file, type, len);

                                        if(time())
                                        {
					    //Log.debugSize(len, true, false, file);
                                        }
                                        if(read == StreamTokenizer.TT_EOF)
                                        {
                                            break;
                                        }
                                    }
                                    bOut.flush();
                                    bOut.close();
				    //Log.debugSize(len, true, true, file);
                                }
                            }
                            catch(IOException ex)
                            {
                                ok = false;
				debug("Old connection removed");
				con.fireProgressUpdate(file, FAILED, -1);
                                //debug(ex + ": " + ex.getMessage());
                                //ex.printStackTrace();
                            }
                        }
                    }
                }
                if(type.equals(PUT) || type.equals(PUTDIR))
                {
                    BufferedInputStream fIn = null;
                    try
                    {
                        fIn = new BufferedInputStream(new FileInputStream(file));
                    }
                    catch(Exception ex)
                    {
                        debug("Can't open inputfile: "+" ("+ex+")");
			ok = false;
                    }

                    if(ok)
                    {
                        try
                        {
                            out = new BufferedOutputStream(sock.getOutputStream());
                        }
                        catch(Exception ex)
                        {
                            ok = false;
                            debug("Can't get OutputStream");
                        }

                        if(ok)
                        {

                            try
                            {
                                int len = 0;
                                char b;

                                while(true)
                                {
                                      int read = fIn.read(buf);
                                      len += read;
                                      if(read == -1) break;
				      
                                      out.write(buf,0,read);
				      con.fireProgressUpdate(file, type, len);

                                      if(time())
                                      {
					 //   Log.debugSize(len, false, false, file);
                                      }
                                      if(read == StreamTokenizer.TT_EOF)
                                      {
                                            break;
                                      }
                                }
                                out.flush();
                                fIn.close();
				//Log.debugSize(len, false, true, file);
                            }
                            catch(IOException ex)
                            {
                                ok = false;
                                debug("Old connection removed");
				con.fireProgressUpdate(file, FAILED, -1);
                                //ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        catch(IOException ex)
        {
            Log.debug("Can't connect socket to ServerSocket");
            ex.printStackTrace();
        }

        try
        {
            sock.close();
        }
        catch(Exception ex)
        {
            debug(ex.toString());
        }

        if (!Settings.getFtpPasvMode())
        {
            try
            {
                ssock.close();
            }
            catch(Exception ex)
            {
                debug(ex.toString());
            }
        }

        finished = true;
	if(ok) con.fireProgressUpdate(file, FINISHED, -1);
	else con.fireProgressUpdate(file, FAILED, -1);
    }
    
    public FtpConnection getCon()
    {
    	return con;
    }

    private void debug(String msg)
    {
        Log.debug(msg);
    }

    public void reset()
    {
        reciever.destroy();
        reciever = new Thread(this);
        reciever.start();
    }

    private void pause(int time)
    {
        try
        {
            reciever.sleep(time);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    private boolean time()
    {
    	long now = System.currentTimeMillis();
	long offset = now-start;

	if(offset > Settings.statusMessageAfterMillis)
	{
		start = now;
		return true;
	}

	return false;
    }

    public boolean isThere()
    {
    	if(finished) return true;

	return isThere;
    }

    public void setType(String tmp)
    {
    	type = tmp;
    }

}
