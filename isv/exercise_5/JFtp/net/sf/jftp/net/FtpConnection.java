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
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.jftp.net;

import net.sf.jftp.config.Settings;
import net.sf.jftp.util.Log;
import net.sf.jftp.util.StringUtils;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Base class. All control transactions are made here.
 */
public class FtpConnection implements BasicConnection, FtpConstants
{
    // everything starting with this will be treated
    // as a negative response
    private final static String NEGATIVE = "5";
    private final static String NEGATIVE2 = "4";

    // everything starting with this will be treated
    // as a positive response
    private final static String POSITIVE= "2";

    // for resuming
    private final static String PROCEED = "3"; //"350";

    // if the 4th character is this, we do not break
    // but wait for more lines
    private final static char MORE_LINES_APPENDED = '-';

    // login failed
    public final static String WRONG_LOGIN_DATA = "Wrong login data!";

    // temporarily not available
    public final static String OFFLINE = "Offline";

    // connection failed
    public final static String GENERIC_FAILED = "Connection failed!";

    // login ok
    public final static String LOGIN_OK = "ok";

    public final static String DOWNLOAD  = "DOWNLOAD";

    public final static String UPLOAD  = "UPLOAD";

    public final static String ABOR  = "ABOR";

    // to check file permissions
    public static final int R = 23;
    public static final int W = 42;
    public static final int DENIED = 666;

    private boolean msg = true;
    private boolean ok = true;
    private String pwd = "";
    private String initCWD = Settings.defaultDir;
    private String[] loginAck = new String[] { FTP331_USER_OK_NEED_PASSWORD, FTP230_LOGGED_IN };
    private String osType;
    private String dataType;
    public boolean work = true;

    private DataConnection dcon = null;

    private Vector listeners = new Vector();
    private ConnectionHandler handler = new ConnectionHandler();

    // directory downloaded to
    private String localPath = null;

    // active ftp ports
    private int porta = 5; // 5 * 256 + 1 = 1281
    private int portb = 1;

    /** the host */
    private String host = "";

    /** the user */
    private String username;

    /** the password */
    private String password;

    /** the port */
    private int port = 21;

    /** the InputStream of the control connection */
    private BufferedReader in;

    /** the OutputStream of the control connection */
    private JConnection jcon;

    /** we are disconnected */
    private boolean connected = false;

    public boolean hasUploaded = false;

    private boolean shortProgress = false;

    private boolean isDirUpload = false;

    private String baseFile;

    private int fileCount;


    public FtpConnection(String host)
    {
        this.host = host;

        File f = new File(".");
        setLocalPath(f.getAbsolutePath());
    }

    public FtpConnection(String host, int port, String initCWD)
    {
        this.host = host;
        this.port = port;
        this.initCWD = initCWD;

        File f = new File(".");
        setLocalPath(f.getAbsolutePath());
    }

    /** connects to the server. */
    public String login(String username, String password)
    {
        this.username = username;
        this.password = password;

	String status = LOGIN_OK;

        if(msg)
        {
            Log.debug("Connecting to "+host);
        }
        jcon = new JConnection(host,port);

        if(jcon.isThere())
        {
            in = jcon.getReader();

            if(getLine(FTP220_SERVICE_READY) == null)
            {
                ok = false;
                Log.debug("Server closed Connection, maybe too many users...");
                status = OFFLINE;
            }
            if(msg)
            {
                Log.debug("Connection established...");
            }

            jcon.send(USER + " " + username);

            if(!getLine(loginAck).startsWith(FTP230_LOGGED_IN))
            {
                jcon.send(PASS + " " + password);

                if(success(FTP230_LOGGED_IN))
                {
                    if(msg)
                        Log.debug("Logged in...");
                }
                else
                {
                    Log.debug("Wrong password!");
                    ok = false;
		    status = WRONG_LOGIN_DATA;
                }
            }

            // end if(jcon)...
        }
        else
        {
            if(msg)
            {
                Log.debug("FTP not available!");
                ok = false;
		status = GENERIC_FAILED;
            }
        }

        if(ok)
        {
	    connected = true;
	    system();
	    binary();

	    if(initCWD.trim().equals(Settings.defaultDir) ||
               !chdirNoRefresh(initCWD))
	    {
	        //System.out.println("default dir...");
	    	//if(!chdirNoRefresh(getPWD()))
		//{
			//System.out.println("FtpConnection should not do this...");
                //	if(!chdirNoRefresh("~"))
                //	{
                  //  		chdirNoRefresh("/");
                //	}
		//}
		updatePWD();
	    }

	    fireDirectoryUpdate(this);
	    fireConnectionInitialized(this);
        }
	else
	{
	    fireConnectionFailed(this, status);
	}

	return status;
    }

    /** sorts the filesizes */
    public String[] sortSize(String file)
    {
        BufferedReader in = null;
        PrintStream out = null;
        int length = 0;
	
        try
        {
            in = new BufferedReader(new FileReader(file));
            out = new PrintStream(new FileOutputStream(Settings.sortsize_out));

            while(true)
            {
                String tmp = in.readLine();
                if(tmp == null)
                    break;
                length++;
		
		// ------------- VMS override -------------------
		if(getOsType().startsWith("VMS"))
		{
			if(tmp.indexOf(";")<0)
			{
			 length--;
			 continue;
			}
			
			out.println("-1");
			continue;
		}
		// ------------------------------------------------
                else if(osType.startsWith("WINDOW"))
                {
                    StringTokenizer to = new StringTokenizer(tmp, " ", false);
		    if (to.countTokens() > 5) {
			to.nextToken();
			to.nextToken();
			to.nextToken();
			to.nextToken();
			String size = to.nextToken();
			out.println(size);
                    } else if(to.countTokens() >= 3)
                    {
                        to.nextToken();
                        to.nextToken();
                        String size = to.nextToken();
                        if(size.equals("<DIR>"))
                        {
                            out.println("0");
                        }
                        else
                        {
                            out.println(size);
                        }
                    }
                } 
		else 
		{
                    StringTokenizer to = new StringTokenizer(tmp," ",true);
                    if(to.countTokens() > 8)
                    {
                        tmp = giveSize(to,4);

                        //    System.out.println(s);

                        out.println(tmp);
                    }
		    else
		    {
		    	length--;
		    }
                }
            }
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::sortSize#1");
        }

        try
        {
            in = new BufferedReader(new FileReader(Settings.sortsize_out));
            String[] files = new String[length];

            for(int i=0; i<length; i++)
            {
                files[i] = in.readLine();
                //System.out.println(files[i]);
		//Log.debug(files[i]);
            }
            return files;
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::sortSize#2");
        }

        return new String[0];
    }
    
    
    /** sorts the filesizes */
    public int[] getPermissions(String file)
    {
        BufferedReader in = null;
        PrintStream out = null;
        int length = 0;

        try
        {
            in = new BufferedReader(new FileReader(file));
            out = new PrintStream(new FileOutputStream(Settings.permissions_out));

            while(true)
            {
                String tmp = in.readLine();
                if(tmp == null) break;

		// ------------- VMS override -------------------
		if(getOsType().startsWith("VMS"))
		{
			if(tmp.indexOf(";")<0) continue;
			out.println("r");
			length++;
			continue;
		}
		// ------------------------------------------------
		
                length++;


                    StringTokenizer to = new StringTokenizer(tmp.trim()," ",false);
		    if(!(to.countTokens() > 3) || (tmp.startsWith("/") && tmp.indexOf("denied") > 0))
		    {
		     length--;	
		     continue;
		    }
		    
                    tmp = to.nextToken();
		    
		    if(tmp.length() != 10)
		    {
		    	//System.out.println(tmp + " - e");
		    	return null; // exotic bug, hardlinks are not found or something ("/bin/ls: dir: no such file or directy" in ls output) - we have no permissions then
		    }
		    
		    char ur = tmp.charAt(1);
		    char uw = tmp.charAt(2);
		    char ar = tmp.charAt(7);
		    char aw = tmp.charAt(8);

		    to.nextToken();
		    String user = to.nextToken();
		    //System.out.println(""+ur+":"+ar+":"+tmp);


		    if(aw == 'w')
		    {
		    	out.println("w");
		    }
		    else if(user.equals(username) && uw == 'w')
		    {
		    	out.println("w");
		    }
		    else if(ar == 'r')
		    {
		    	out.println("r");
		    }
		    else if(user.equals(username) && ur == 'r')
		    {
		    	out.println("r");
		    }
		    else
		    {
		    	//System.out.println(ur+":"+ar+":"+user+":"+username+":");
		    	out.println("n");
		    }

            }

	    out.flush();
	    out.close();
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::getPermissions#1");
        }

        try
        {
            in = new BufferedReader(new FileReader(Settings.permissions_out));
            //String[] files = new String[length];
	    int ret[] = new int[length];
	    String fx;

            for(int i=0; i<length; i++)
            {
                fx = in.readLine();

	    	if(fx.equals("w"))
		{
			ret[i] = W;
		}
	    	else if(fx.equals("n"))
		{
			ret[i] = DENIED;
		}
		else ret[i] = R;

                //System.out.println(ret[i]);
            }

	    return ret;
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::getPermissions#2");
        }

        return new int[0];
    }



    /** parses the ls-output */
    public String[] sortLs(String file)
    {

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            PrintStream out = new PrintStream(new FileOutputStream(Settings.sortls_out));
            int length = 0;

            while(true)
            {
                String tmp = in.readLine();
                if(tmp == null) break;

		// ------------------- VMS override --------------------
	    	if(getOsType().startsWith("VMS"))
		{
			int x = tmp.indexOf(";");
			if(x<0) continue;

			tmp = tmp.substring(0,x);
			;
			if(tmp.endsWith("DIR"))
			{
				out.println(tmp.substring(0,tmp.lastIndexOf("."))+"/");
			}
			else
			{
				out.println(tmp);
			}

			length++;
			continue;
		}
		// -------------------------------------------------------

		// ------------------- Unix, Windows and others -----
		boolean isDir = false;
		boolean isLink = false;

                if(tmp.startsWith("d") || tmp.indexOf("<DIR>") >= 0) isDir = true;
                if(tmp.startsWith("l")) isLink = true;
		if(tmp.startsWith("/") && tmp.indexOf("denied") > 0)	continue;

                StringTokenizer to = new StringTokenizer(tmp," ",true);
                int tokens = to.countTokens();

                if (tokens > 8) // unix
		{
                       tmp = giveFile(to,8);
                }
		else if (tokens > 3) // old windows
		{
                       tmp = giveFile(to,3);
                } else
		{
		       continue;
		}

                if(isDir)
                {
                            tmp = tmp + "/";
                } else if (isLink)
		{
		    	tmp = tmp + "@";
		}

		out.println(tmp);
                length++;
		// -------------------------------------------------------------
            }

            in = new BufferedReader(new FileReader(Settings.sortls_out));
            String[] files = new String[length];

            for(int i=0; i<length; i++)
            {
                files[i] = in.readLine();
            }
            return files;
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::sortLs");
        }

        return new String[0];
    }

    /** get a filename */
    private String giveFile(StringTokenizer to, int lev)
    {
        for(int i=0; i<lev; i++)
        {
            if(to.hasMoreTokens())
            {
                String t = to.nextToken();
                if(t.equals(" ") || t.equals("\t"))
                {
                    i--;
                }
            }
        }
        String tmp = "<error>";
        while(tmp.equals(" ") || tmp.equals("\t") || tmp.equals("<error>"))
        {
            if(to.hasMoreTokens())
            {
                tmp = to.nextToken();
            }
            else
                break;
        }

        while(to.hasMoreTokens())
        {
            tmp = tmp + to.nextToken();
        }
        return tmp;
    }

    /** get a filesize */
    private String giveSize(StringTokenizer to, int lev)
    {
        for(int i=0; i<lev; i++)
        {
            if(to.hasMoreTokens())
            {
                if(to.nextToken().equals(" "))
                {
                    i--;
                }
            }
        }
        while(to.hasMoreTokens())
        {
            String tmp = to.nextToken();
            if(!tmp.equals(" "))
            {
                try
                {
                    Integer.parseInt(tmp);
                }
                catch(NumberFormatException ex)
                {
                    return "-1";
                }
                return tmp;
            }
        }
        return "-2";
    }

    /** try to determine the os-type to properly parse the ls-output */
    public String getOsType()
    {
        return osType;
    }

    private void setOsType(String os)
    {
        osType = os.toUpperCase();
    }
    
    private int getPasvPort(String str)
    {
    	int start = str.lastIndexOf(",");
	String lo = "";
	start++;
	
	while(start < str.length())
	{
		char c = str.charAt(start);
		if(!Character.isDigit(c)) break;
		lo = lo + c;
		start++;
	}
	
	String hi = "";
	start = str.lastIndexOf(",");
	start --;
	
	while(true)
	{
		char c = str.charAt(start);
		if(!Character.isDigit(c)) break;
		hi = c + hi;
		start--;
	}
	
	//System.out.print(hi+":"+lo+" - ");
	
	return (Integer.parseInt(hi) * 256 + Integer.parseInt(lo));
    }


    /** returns the passive port out of the server response */
    /*
    private int getOldPasvPort(String str) 
    {
	
        int start = str.lastIndexOf(",");
        int end = str.lastIndexOf(")");
	
        String lo = null; // lo-ordered byte
	
        if (start > -1 && end > -1) lo = str.substring(start + 1, end);
	else if(end < 0)
	{
		System.out.println("INFO: please report this server to the author if you experience any problems");
	        System.out.println("INFO: running some untested fallback code...");
		lo = str.substring(start+1);
	}
	    
        end = start;
        start = str.lastIndexOf(",", start - 1);
	
        String hi = null; // hi-ordered byte
	
        if (start > -1 && end > -1) hi = str.substring(start + 1, end);
	else if(end < 0)
	{
		System.out.println("INFO: please report this server to the author if you experience any problems");
	        System.out.println("INFO: running some untested fallback code...");
		lo = str.substring(start+1);
	}
	
	//System.out.println(hi+":"+lo);
	            
        int ret = (Integer.parseInt(hi) * 256 + Integer.parseInt(lo));
	
        return ret;
    }
    */

    private int getActivePort()
    {
        return getPortA() * 256 + getPortB();
    }

    /** parse a symlink */
    private  String parseSymlink(String file)
    {
        if(file.indexOf("->") >= 0)
        {
            //System.out.print(file+" :-> symlink converted to:");
            file = file.substring(0, file.indexOf("->")).trim();
            //System.out.println(file);
        }
        return file;
    }
    
     /** parse a symlink and cut the back */
    private String parseSymlinkBack(String file)
    {
        if(file.indexOf("->") >= 0)
        {
            //System.out.print(file+" :-> symlink converted to:");
            file = file.substring(file.indexOf("->")+2).trim();
            //System.out.println(file);
        }
        return file;
    }
    
    /** test for symlink */
    private  boolean isSymlink(String file)
    {
        if(file.indexOf("->") >= 0)
        {
            return true;
        }
        return false;
    }

    public void handleDownload(String file)
    {
      if(Settings.getEnableMultiThreading())
      {
	        Transfer t = new Transfer(host, port, getLocalPath(), getCachedPWD(), file, username, password, Transfer.DOWNLOAD, handler, listeners);
      }
      else
      {
      	download(file);
      }
    }

    /** download a file */
    public void download(String file)
    {
      if(file.endsWith("/"))
      {
        shortProgress = true;
	fileCount = 0;
	baseFile = file;
	dataType = DataConnection.GETDIR;

      	downloadDir(file);

	pause(500);
	fireActionFinished(this);
	fireProgressUpdate(baseFile,DataConnection.DFINISHED + ":" + fileCount, -1);
	shortProgress = false;
      }
     else
     {
     	dataType = DataConnection.GET;
      	rawDownload(file);
	fireActionFinished(this);
     }

     	try{Thread.sleep(400);}
	catch(Exception ex) {}
    }

    private void rawDownload(String file)
    {
        file = parse(file);
        //String path = file;

        try
        {
            int p =0;
             //if(isRelative(file)) path = getLocalPath() + file;
	     file = StringUtils.getFile(file);

	     String path = getLocalPath() + file;

	     //System.out.println(file + " : " + path);

            BufferedReader in = jcon.getReader();

            modeStream();
            //binary();
            p = negotiatePort();

            File f = new File(path);
	    //System.out.println("path: "+path);
            boolean resume = false;

            if(f.exists() && Settings.enableResuming)
            {
                jcon.send(REST + " " + f.length());
                if(getLine(PROCEED) != null)
                {
                    resume = true;
                }
            }

            dcon = new DataConnection(this,p,host,path,dataType, resume); //, new Updater());

            while(!dcon.isThere()) pause(100);

            jcon.send(RETR + " " + file);
	    String line = in.readLine();
            Log.debug(line);
	    //System.out.println(line);

	    // file has been created even if not downloaded, delete it
	    if(line.startsWith(NEGATIVE))
	    {
	    	File f2 = new File(path);
	        if(f2.exists() && f2.length() == 0) f2.delete();
		return;
	    }

	    // we need to block since some ftp-servers do not want the
	    // refreh command that dirpanel sends otherwise
	    while(!dcon.finished) pause(100);
        }
        catch(Exception ex)
        {
	    ex.printStackTrace();
            Log.debug(ex.toString()+" @FtpConnection::download");
        }
    }


    /** recursive download of a directory */
        public void downloadDir(String dir)
    {
        if(!dir.endsWith("/"))
        {
            dir = dir + "/";
        }

	if(StringUtils.isRelative(dir)) dir = getCachedPWD() + dir;

	String path = getLocalPath() + StringUtils.getDir(dir);
	//System.out.println("path: "+path);

	String pwd = getCachedPWD()+StringUtils.getDir(dir);

	String oldDir = getLocalPath();
        String oldPwd = getCachedPWD();

        File f = new File(path);

        if(!f.exists())
        {
            if(!f.mkdir())
            {
                Log.debug("Can't create directory: "+dir);
            }
            else
                Log.debug("Created directory...");
        }

	setLocalPath(path);

        if(!chdirNoRefresh(pwd)) return;

	try
	{
		list(Settings.ls_out);
	}
	catch(IOException ex)
	 {
		// probably we don't have permission to ls here
		return;
	}
        String tmp[] = sortLs(Settings.ls_out);

	setLocalPath(path);

        for(int i=0; i<tmp.length; i++)
        {
            if(tmp[i].endsWith("/"))
            {
                if(tmp[i].trim().equals("../") || tmp[i].trim().equals("./"))
                {
                    Log.debug("Skipping " + tmp[i].trim());
                }
                else
                {
		    if(!work)
		    {
			return;
		    }
		    downloadDir(tmp[i]);
                }
            }
            else
            {
                //System.out.println( "file: " + getLocalPath() + tmp[i] + "\n\n");
		if(!work)
		{
			return;
		}
		fileCount++;
                rawDownload(getLocalPath() + tmp[i]);
            }
        }

	chdirNoRefresh(oldPwd);
	setLocalPath(oldDir);
    }

    public void handleUpload(String file)
    {
      if(Settings.getEnableMultiThreading() && (!Settings.getNoUploadMultiThreading()))
      {
        	Transfer t = new Transfer(host, port, getLocalPath(), getCachedPWD(), file, username, password, Transfer.UPLOAD, handler, listeners);
      }
      else
      {
      	upload(file);
      }
    }

    public void upload(String file)
    {
    	hasUploaded = true;

     if(new File(file).isDirectory())
     {
        shortProgress = true;
	fileCount = 0;
	baseFile = file;
	dataType = DataConnection.PUTDIR;
	isDirUpload = true;

     	uploadDir(file);

	shortProgress = false;
	//System.out.println(fileCount + ":" + baseFile);
	fireProgressUpdate(baseFile,DataConnection.DFINISHED + ":" + fileCount, -1);
	fireActionFinished(this);
	fireDirectoryUpdate(this);
     }
     else
     {
     	dataType = DataConnection.PUT;
	rawUpload(file);
	fireActionFinished(this);
	fireDirectoryUpdate(this);
     }

     	try{Thread.sleep(500);}
	catch(Exception ex) {}
    }

    /** uploads a file */
    private void rawUpload(String file)
    {
    	file = parse(file);
        String path =file;

        try
        {
            int p=0;
            if(StringUtils.isRelative(file)) path = getLocalPath() + file;
	    file = StringUtils.getFile(file);

            BufferedReader in = jcon.getReader();
            modeStream();
            //binary();
	    p = negotiatePort();
	    //File f = new File(file);

            dcon = new DataConnection(this,p,host,path,dataType); //, new Updater());
            while(!dcon.isThere())
                pause(100);

	    //System.out.println(path + " : " + file);
            Log.debug("File: "+file);

            jcon.send(STOR + " " + file);
            Log.debug(in.readLine());

	    // we need to block since some ftp-servers do not want the
	    // refresh command that dirpanel sends otherwise
	    while(!dcon.finished) pause(100);
        }
        catch(Exception ex)
        {
	    ex.printStackTrace();
            Log.debug(ex.toString()+" @FtpConnection::upload");
        }
    }

    /** uploads a directory recursively */
    public void uploadDir(String dir)
    {
        //System.out.println("up");
        if(dir.endsWith("\\"))
        {
            System.out.println("Something's wrong with the selected directory - please report this bug!");
        }

        if(!dir.endsWith("/"))
        {
            dir = dir + "/";
        }

	if(StringUtils.isRelative(dir)) dir = getLocalPath() + dir;

	String single = StringUtils.getDir(dir);
	String path = dir.substring(0, dir.indexOf(single));

        String remoteDir = getCachedPWD() + single;	//StringUtils.removeStart(dir,path);
	String oldDir = getCachedPWD();

        if(Settings.safeMode) noop();

        boolean successful = mkdir(remoteDir);
        if (!successful) return;

        if(Settings.safeMode) noop();

	chdirNoRefresh(remoteDir);

        File f2 = new File(dir);
        String tmp[] = f2.list();

        for(int i=0; i<tmp.length; i++)
        {
	    String res = dir+tmp[i];
            File f3 = new File(res);

            if(f3.isDirectory())
            {
	    	if(!work)
		{
			return;
		}
                uploadDir(res);
            }
            else
            {
		//System.out.println(">>>\nlp: "+path+single + "\nrp: ");
		//System.out.println(remoteDir +"\nres: "+res);
		if(!work)
		{
			return;
		}
		fileCount++;
                rawUpload(res);
            }
        }

	chdirNoRefresh(oldDir);
    }

    private String parse(String file)
    {
	file = parseSymlink(file);

	return file;
    }

    /** recursive delete remote directory */
    private void cleanDir(String dir, String path)
    {
        if(!dir.endsWith("/"))
        {
            dir = dir + "/";
        }
        String remoteDir = StringUtils.removeStart(dir,path);

        String oldDir = pwd;
        chdirNoRefresh(pwd+remoteDir);

	try {
		list(Settings.ls_out);
	} catch(IOException ex) {
		// probably we don't have permission to ls here
		return;
	}
	String tmp[] = sortLs(Settings.ls_out);
        chdirNoRefresh(oldDir);

        if(tmp == null)
            return;

        for(int i=0; i<tmp.length; i++)
        {
	 tmp[i] = parseSymlink(tmp[i]);
	 if(tmp[i].equals("./") || tmp[i].equals("../"))
	 {
	 	//System.out.println(tmp[i]+"\n\n\n");
	 	continue;
	 }
	 //System.out.println(tmp[i]);
	 //pause(500);

            if(tmp[i].endsWith("/"))
            {
                //   System.out.println(dir);
                cleanDir(dir+tmp[i],path);
                removeFileOrDir(dir+tmp[i]);
            }
            else
            {
                //   System.out.println(dir+tmp[i]);
                removeFileOrDir(dir+tmp[i]);
            }
        }
    }

    /** remove a remote file or directory */
    public void removeFileOrDir(String file) {
    	file = parseSymlink(file);

	if(file.endsWith("/"))
	{
		cleanDir(file, getCachedPWD());
		jcon.send(RMD + " " + file);
	}
	else	jcon.send(DELE + " " + file);

        getLine(FTP250_COMPLETED);
    }

    /** disconnect */
    public void disconnect() {
 	jcon.send(QUIT);
	getLine(FTP221_SERVICE_CLOSING);
	connected = false;
    }

    /** execute noop -> command -> noop */
    public void sendRawCommand(String cmd)
    {
        noop();
	Log.clearCache();
        jcon.send(cmd);
        noop();
    }

    /** reads until line found or error */
    public String getLine(String until)
    {
        return getLine(new String[] { until });
    }

    /** reads until line found or error */
    public String getLine(String until[])
    {
        BufferedReader in = null;

        try
        {
            in = jcon.getReader();
        }
        catch(Exception ex)
        {
            Log.debug(ex.toString()+" @FtpConnection::getLine");
        }

        String firstMatch = null;
        String tmp;

        while(true)
        {
	    try
            {
                    tmp = in.readLine();
                    if(tmp == null)
                    {
                        break;
                    }

                    Log.debug(tmp);

                    if((tmp.startsWith(NEGATIVE) || (tmp.startsWith(NEGATIVE2))) &&
		       (tmp.charAt(3) != MORE_LINES_APPENDED))
                    {
                        return tmp;
                    }
                    else
		    {
                        for(int i=0; i<until.length; i++)
                        {
                            if (tmp.startsWith(until[i]))
                            {
                                if (firstMatch == null)
                                    firstMatch = tmp;
                                if (tmp.charAt(3) != MORE_LINES_APPENDED)
                                    return firstMatch;
                            }
                        }
                    }
            }
            catch(Exception ex)
            {
                Log.debug(ex.toString() + " @FtpConnection::getLine");
                break;
            }

        }
	
	return null;
    }

    public boolean isConnected()
    {
    	return connected;
    }

    /** gets current remote directory */
    public String getPWD()
    {
        if(connected) updatePWD();
    	return pwd;
    }

    /** gets current remote directory (cached) */
    public String getCachedPWD()
    {
    	return pwd;
    }

    /** updates PWD */
    private void updatePWD()
    {
    	jcon.send(PWD);
        String tmp = getLine(FTP257_PATH_CREATED);
        String x1 = tmp.substring(tmp.indexOf("\"")+1);
        x1 = x1.substring(0,x1.indexOf("\""));
        if(!x1.endsWith("/"))
        {
        	x1 = x1 + "/";
        }
	pwd = x1;
    }

    /** change directory, use chdir instead
    	if you do not parse the dir correctly...
    */
    public boolean chdirRaw(String dirName) {
        jcon.send(CWD + " " + dirName);
	return success(FTP250_COMPLETED);
    }

    private boolean success(String op)
    {
    	if(getLine(op).startsWith(op)) return true;
	else return false;
    }

    /**
     * Change to the parent of the current working directory.
     * The CDUP command is a special case of CWD, and is included
     * to simplify the implementation of programs for transferring
     * directory trees between operating systems having different
     * syntaxes for naming the parent directory.
     */
    public boolean cdup() {
        jcon.send(CDUP);
        return success(FTP200_OK);
    }

    /** create a directory */
    public boolean mkdir(String dirName) {
        jcon.send(MKD + " " + dirName);
	return success(FTP257_PATH_CREATED);
    }

    private int negotiatePort() throws IOException
    {
    	String tmp = "";

        if (Settings.getFtpPasvMode())
        {
            jcon.send(PASV);
            tmp = getLine(FTP227_ENTERING_PASSIVE_MODE);
	    if(!tmp.startsWith(NEGATIVE)) return getPasvPort(tmp);
        }

        tmp = getActivePortCmd();
        jcon.send(tmp);
        getLine(FTP200_OK);
        return getActivePort();
    }

    /** lists remote directory */
    public void list(String outfile) throws IOException
    {
    	String oldType = "";

        try
        {
            BufferedReader in = jcon.getReader();

            int p = 0;

            modeStream();

            oldType = getTypeNow();
	    ascii();

            p = negotiatePort();
            dcon = new DataConnection(this,p,host,outfile,DataConnection.GET); //,null);
//System.out.println("2...");
	    while(!dcon.isThere()) pause(10);
            jcon.send(LIST);
//System.out.println("3...");
            while(!dcon.finished) pause(10);
            getLine(FTP226_CLOSING_DATA_REQUEST_SUCCESSFUL);
//System.out.println("4...");
	    if(!oldType.equals(ASCII)) type(oldType);
     }
     catch(Exception ex)
     {
        Log.debug("Cannot list remote directory!");
	if(!oldType.equals(ASCII)) type(oldType);
     	ex.printStackTrace();
	throw new IOException(ex.getMessage());
     }

    }

    /** parses directory and does a chdir() */
    public boolean chdir(String p)
    {
     boolean tmp = chdirWork(p);
     if(!tmp) return false;
     else
     {
     	fireDirectoryUpdate(this);
	return true;
     }
    }

    /** parses directory and does a chdir(), but does not send an update signal */
    public boolean chdirNoRefresh(String p)
    {
    	return chdirWork(p);
    }

    private boolean chdirWork(String p)
    {
        if(Settings.safeMode)
        {
	    noop();
        }

	p = parseSymlinkBack(p);

        try
        {
	    // check if we don't have a relative path
            if(!p.startsWith("/") && !p.startsWith("~"))
            {
                p = pwd + p;
            }

	    // argh, we should document that!
            if (p.endsWith(".."))
            {
                boolean home = p.startsWith("~");
                StringTokenizer stok = new StringTokenizer(p,"/");

                //System.out.println("path p :"+p +" Tokens: "+stok.countTokens());

                if (stok.countTokens()>2)
                {
                    String pold1="";
                    String pold2="";
                    String pnew="";
                    while (stok.hasMoreTokens())
                    {
                        pold1=pold2;
                        pold2=pnew;
                        pnew=pnew+"/"+stok.nextToken();
                    }
                    p=pold1;
                }
                else
                {
                    p="/";
                }
                if(home)
                {
                    p = p.substring(1);
                }
            }

            //System.out.println("path: "+p);

            if(!chdirRaw(p))
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            Log.debug("(remote) Can not get pathname! "+pwd+":"+p);
            ex.printStackTrace();
            return false;
        }

	//fireDirectoryUpdate(this);
	updatePWD();
	return true;
    }


    /** waits a specified amount of time (in milliseconds) */
    public void pause(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /** get the local cwd */
    public String getLocalPath()
    {
    	return localPath;
    }

    /** set the path downloaded to */
    public boolean setLocalPath(String newPath)
    {
    	localPath = newPath;
        if(!localPath.endsWith("/"))
        {
            localPath = localPath + "/";
        }

	return true;
    }

    public int getPort()
    {
        return port;
    }

    private int getPortA()
    {
        return porta;
    }

    private int getPortB()
    {
        return portb;
    }

    private void incrementPort()
    {
        portb++;
    }

    private String getActivePortCmd() throws UnknownHostException, IOException
    {
        InetAddress ipaddr = jcon.getLocalAddress();
        String ip = ipaddr.getHostAddress().replace('.', ',');

        incrementPort();
        int a = getPortA();
        int b = getPortB();
        String ret = "PORT " + ip + "," + a + "," + b;
	//System.out.println(ret);

	return ret;
    }

    /**
     * Tell server we want binary data connections.
     */
    public void binary()
    {   // possible responses 200, 500, 501, 504, 421 and 530
        type(BINARY);
    }

    /**
     * Tell server we want ascii data connections.
     */
    public void ascii()
    {
        type(ASCII);
    }

    public final static String ASCII = "A";
    public final static String BINARY = "I";
    public final static String EBCDIC = "E";
    public final static String L8 = "L 8";
    private String typeNow = "";

    public boolean type(String code)
    {
        jcon.send(TYPE + " " + code);
        if(getLine(FTP200_OK).startsWith(FTP200_OK))
	{
	 typeNow = code;
	 return true;
	 }

	 return false;
    }
    
    public String getTypeNow()
    {
    	return typeNow;
    }

    /**
     * do nothing, but flush buffers
     */
    public void noop()
    {
        jcon.send(NOOP);
        getLine(FTP200_OK);
    } 
    
    public void abort()
    {
        jcon.send(ABOR);
        getLine(POSITIVE); // 226
    } 

    /**
     * This command is used to find out the type of operating
     * system at the server. The reply shall have as its first
     * word one of the system names listed in the current version
     * of the Assigned Numbers document (RFC 943).
     */
    public String system()
    {   // possible responses 215, 500, 501, 502, and 421
        jcon.send(SYST);
        String response = getLine(FTP215_SYSTEM_TYPE);
        if (response != null)
        {
            StringTokenizer st = new StringTokenizer(response);
            if (st.countTokens() >= 2)
            {
                 st.nextToken();
                 String os = st.nextToken();
                 setOsType(os);
            }
        }
        else
        {
            setOsType("UNIX");
        }
        return response;
    }

    public final static String STREAM = "S";
    public final static String BLOCKED = "B";
    public final static String COMPRESSED = "C";
    private static boolean useStream = true;
    private static boolean useBlocked = true;
    private static boolean useCompressed = true;

    public void modeStream()
    {
        if (useStream) {
	    String ret = mode(STREAM);
            if (ret != null && ret.startsWith(NEGATIVE))
                useStream = false;
        }
    }

    /**
     * unsupported at this time
     */
    public void modeBlocked()
    {
        if (useBlocked) {
            if (mode(BLOCKED).startsWith(NEGATIVE))
                useBlocked = false;
        }
    }

    /*
     * unsupported at this time
     */
    public void modeCompressed()
    {
        if (useCompressed) {
            if (mode(COMPRESSED).startsWith(NEGATIVE))
                useCompressed = false;
        }
    }

    public String mode(String code)
    {
        jcon.send(MODE + " " + code);
	String ret = "";
	
	try 
	{ 
		ret = getLine(FTP200_OK); 
	}
	catch(Exception ex) 
	{
		ex.printStackTrace();
	}
	
	return ret;
    }

    public String getHost()
    {
        return host;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
    
    public DataConnection getDataConnection()
    {
    	return dcon;
    }

    public void addConnectionListener(ConnectionListener l)
    {
    	listeners.add(l);
    }

    public void setConnectionListeners(Vector l)
    {
    	listeners = l;
    }

  /** remote directory has changed */
    public void fireDirectoryUpdate(FtpConnection con)
    {
    	if(listeners == null) return;
	else
	{
		for(int i=0; i<listeners.size(); i++)
		{
			((ConnectionListener)listeners.elementAt(i)).updateRemoteDirectory(con);
		}
	}
    }

    /** progress update */
    public void fireProgressUpdate(String file, String type, int bytes)
    {
    	//System.out.println(listener);
    	if(listeners == null) return;
	else
	{
	 for(int i=0; i<listeners.size(); i++)
	 {
	   ConnectionListener listener = (ConnectionListener) listeners.elementAt(i);

	   if(shortProgress && Settings.shortProgress)
	  {
		if(type.startsWith(DataConnection.DFINISHED))
		{
			 listener.updateProgress( baseFile, DataConnection.DFINISHED+":"+fileCount, bytes);
		}
		else if(isDirUpload) listener.updateProgress( baseFile, DataConnection.PUTDIR+":"+fileCount, bytes);
		else listener.updateProgress( baseFile, DataConnection.GETDIR+":"+fileCount, bytes);
	   }
	   else listener.updateProgress(file, type, bytes);
	  }
	}

    }

    /** connection is there and user logged in */
    public void fireConnectionInitialized(FtpConnection con)
    {
    	if(listeners == null) return;
	else
	{
		for(int i=0; i<listeners.size(); i++)
		{
			((ConnectionListener)listeners.elementAt(i)).connectionInitialized(con);
		}
	}
    }

    /** we are not logged in for some reason */
    public void fireConnectionFailed(FtpConnection con, String why)
    {
    	if(listeners == null) return;
	else
	{
		for(int i=0; i<listeners.size(); i++)
		{
			((ConnectionListener)listeners.elementAt(i)).connectionFailed(con, why);
		}
	}
    }

    /** transfer is done */
    public void fireActionFinished(FtpConnection con)
    {
    	if(listeners == null) return;
	else
	{
		for(int i=0; i<listeners.size(); i++)
		{
			((ConnectionListener)listeners.elementAt(i)).actionFinished(con);
		}
	}
    }

    public ConnectionHandler getConnectionHandler()
    {
    	return handler;
    }

    public void setConnectionHandler(ConnectionHandler h)
    {
    	handler = h;
    }

}
