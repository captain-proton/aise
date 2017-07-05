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

import jcifs.smb.*;

public class SmbConnection extends NtlmAuthenticator  implements BasicConnection
{
    private String path = "";
    private String pwd = "smb://";
    private Vector listeners = new Vector();
    private  String files[];
    private String size[] = new String[0];
    private int perms[] = null;;
    private String user;
    private String pass;
    private String domain;

   public static int smbBuffer = 64000;
   public static int smbMessageAfterBytes = 1000000;

    public SmbConnection() {}

    public SmbConnection(String url, String user, String pass, ConnectionListener l)
    {
    	listeners.add(l);
	this.user = user;
	this.pass = pass;
	this.domain = domain;
	if(user != null) NtlmAuthenticator.setDefault(this);

	if(url == null) chdir(getPWD());
	else chdir(url);
    }
	protected NtlmPasswordAuthentication getNtlmPasswordAuthentication() {
		try {
			int i;

			if(( i = user.indexOf( '\\' )) != -1 ) {
				domain = user.substring( 0, i );
				user = user.substring( i + 1 );
			}
			return new NtlmPasswordAuthentication(domain, user, pass);
		} catch( Exception e ) {
		}
		return null;
	}


    public void removeFileOrDir(String file)
    {
    	file = toSMB(file);
	//System.out.println(file);

	try
	{
		SmbFile f = new SmbFile(file);
		if(f.exists() && f.isDirectory())
		{
			cleanSmbDir(file);
		}

		f.delete();
	}
	catch(Exception ex)
	{
		Log.debug("Removal failed ("+ex+").");
	}
    }

    private void cleanSmbDir(String dir) throws Exception
    {
	dir = toSMB(dir);

        SmbFile f2 = new SmbFile(dir);
        String tmp[] = f2.list();
	if(tmp == null) return;

        for(int i=0; i<tmp.length; i++)
        {
            SmbFile f3 = new SmbFile(dir+tmp[i]);

            if(f3.isDirectory())
            {
                //System.out.println(dir);
                cleanSmbDir(dir+tmp[i]);
                f3.delete();
            }
            else
            {
                //System.out.println(dir+tmp[i]);
                f3.delete();
            }
        }
    }

    public void sendRawCommand(String cmd)
    {
    }

    public void disconnect()
    {
    }

    public boolean isConnected()
    {
    	return true;
    }

    public String getPWD()
    {
        if(pwd.indexOf("smb://") >= 0) return pwd.substring(pwd.lastIndexOf("smb://"));
	else return pwd;
    }

    public boolean cdup()
    {
        String tmp = pwd;
	if(pwd.endsWith("/") && !pwd.equals("smb://")) tmp = pwd.substring(0,pwd.lastIndexOf("/"));
    	return chdir(tmp.substring(0,tmp.lastIndexOf("/")+1));
    }

    public boolean mkdir(String dirName)
    {
     try
     {
        if(!dirName.endsWith("/")) dirName = dirName + "/";
    	dirName = toSMB(dirName);

	SmbFile f = new SmbFile(dirName);
	f.mkdir();

	fireDirectoryUpdate();
	return true;
     }
     catch(Exception ex)
     {
      Log.debug("Failed to create directory ("+ex+").");
      return false;
     }
    }

    public void list(String outfile) throws IOException
    {
    }

    public boolean chdir(String p)
    {
     try
     {
        if(p.endsWith("..")) return cdup();

    	String tmp = toSMB(p);
	if(!tmp.endsWith("/")) tmp = tmp + "/";
	SmbFile f = new SmbFile(tmp);

	pwd = tmp;
	//System.out.println("chdir: " + getPWD());
	fireDirectoryUpdate();
	//System.out.println("chdir2: " + getPWD());

	return true;
    }
    catch(Exception ex)
    {
    	Log.debug("Can not change pwd ("+ex+").");
	return false;
    }
    }

    public boolean chdirNoRefresh(String p)
    {
	pwd = toSMB(p);
	return true;
    }

    public String getLocalPath()
    {
    	return path;
    }

    public boolean setLocalPath(String p)
    {
        if(StringUtils.isRelative(p)) p = path + p;
	p = p.replace('\\','/');
        //System.out.println(", local 2:" + p);

        File f = new File(p);

        if(f.exists())
        {
            try
            {
                path = f.getCanonicalPath();
                path = path.replace('\\','/');
                if(!path.endsWith("/")) path = path + "/";

		//System.out.println("localPath: "+path);
            }
            catch(IOException ex)
            {
                Log.debug("Error: can not get pathname (local)!");
		return false;
            }
        }
        else
        {
            Log.debug("(local) No such path: \"" + p + "\"");
	    return false;
        }

	return true;
    }

    public String[] sortLs(String file)
    {
     try
     {
     		chdirNoRefresh(getPWD());
		SmbFile fx = new SmbFile(pwd);
		//System.out.println(pwd);
		//if(fx == null) System.out.println("Smb: fx null");

		SmbFile[] f = fx.listFiles();
		//if(f == null) System.out.println("Smb: f null");
		files = new String[f.length];
		size = new String[f.length];
		perms = new int[f.length];
		int i;

		for(i=0; i<f.length; i++)
        	{
			files[i] = f[i].toURL().toString();
			size[i] = ""+ f[i].length();
			if(f[i].canRead()) perms[i] = FtpConnection.R;
			else if(f[i].canWrite()) perms[i] = FtpConnection.R;
			else  perms[i] = FtpConnection.DENIED;
		}

		return files;
      }
      catch(Exception ex)
      {
        if(ex instanceof SmbAuthException) Log.debug("Access denied.");
      	else Log.debug("Error: " + ex);
	return new String[0];
      }

    }

    public String[] sortSize(String file)
    {
    	return size;
    }

    public int[] getPermissions(String file)
    {
	return perms;
    }

    public void handleUpload(String f)
    {
	upload(f);
    }

    public void handleDownload(String f)
    {
	download(f);
    }


    public void upload(String f)
    {
        String file = toSMB(f);

    	if(file.endsWith("/"))
	{
	    	String out = StringUtils.getDir(file);
		uploadDir(file, getLocalPath() + out);
	}
	else
	{
		String outfile = StringUtils.getFile(file);
     		//System.out.println("transfer: " + file + ", " + getLocalPath() + outfile);
		work(getLocalPath()+outfile, file);
	}
    }

    public void download(String f)
    {
        String file = toSMB(f);

    	if(file.endsWith("/"))
	{
	    	String out = StringUtils.getDir(file);
		downloadDir(file, getLocalPath() + out);
	}
	else
	{
		String outfile = StringUtils.getFile(file);
     		//System.out.println("transfer: " + file + ", " + getLocalPath() + outfile);
		work(file, getLocalPath()+outfile);
	}
    }

    private void downloadDir(String dir, String out)
    {
     try
     {
     	//System.out.println("downloadDir: " + dir + "," + out);

        SmbFile f2 = new SmbFile(dir);
        String tmp[] = f2.list();
	if(tmp == null) return;

	File fx = new File(out);
	fx.mkdir();

        for(int i=0; i<tmp.length; i++)
        {
	    tmp[i] = tmp[i].replace('\\','/');
	    //System.out.println("1: " + dir+tmp[i] + ", " + out +tmp[i]);
            SmbFile f3 = new SmbFile(dir+tmp[i]);

            if(f3.isDirectory())
            {
	    	if(!tmp[i].endsWith("/")) tmp[i] = tmp[i] + "/";
                downloadDir(dir+tmp[i], out + tmp[i]);
            }
            else
            {
                work(dir +tmp[i], out + tmp[i]);
            }
        }
     }
     catch(Exception ex)
     {
        ex.printStackTrace();
	System.out.println(dir + ", " + out);
     	Log.debug("Transfer error: " + ex);
     }
    }

    private void uploadDir(String dir, String out)
    {
     try
     {
     	//System.out.println("uploadDir: " + dir + "," + out);

        File f2 = new File(out);
        String tmp[] = f2.list();
	if(tmp == null) return;

	SmbFile fx = new SmbFile(dir);
	fx.mkdir();

        for(int i=0; i<tmp.length; i++)
        {
	    tmp[i] = tmp[i].replace('\\','/');
	   //System.out.println("1: " + dir+tmp[i] + ", " + out +tmp[i]);
            File f3 = new File(out+tmp[i]);

            if(f3.isDirectory())
            {
	    	if(!tmp[i].endsWith("/")) tmp[i] = tmp[i] + "/";
                uploadDir(dir+tmp[i], out + tmp[i]);
            }
            else
            {
                work(out +tmp[i], dir + tmp[i]);
            }
        }
     }
     catch(Exception ex)
     {
        ex.printStackTrace();
	System.out.println(dir + ", " + out);
     	Log.debug("Transfer error: " + ex);
     }
    }

    private String toSMB(String f)
    {
    	String file;

        if(f.startsWith("smb://")) file = f;
	else file = getPWD()+f;

	file = file.replace('\\','/');
	return file;
    }

   private void work(String file, String outfile)
   {

     try
     {
        Log.debugRaw(file + ": ");
 	BufferedOutputStream out = null;

	if(outfile.startsWith("smb://"))  out =  new BufferedOutputStream(new SmbFileOutputStream(new SmbFile(outfile)));
	else out =  new BufferedOutputStream(new FileOutputStream(outfile));

	//System.out.println("out: " + outfile + ", in: " + file);
	BufferedInputStream in = null;

	if(file.startsWith("smb://")) in =  new BufferedInputStream(new SmbFile(file).getInputStream());
	else in = new BufferedInputStream(new FileInputStream(file));

	byte buf[] = new byte[smbBuffer];
	int len = 0;
	int x = 0;
	//System.out.println(file+":"+getLocalPath()+outfile);

	while(true)
	{
		len = in.read(buf);
		//System.out.print(".");
		if(len == StreamTokenizer.TT_EOF) break;
		out.write(buf,0,len);

		if(x >smbMessageAfterBytes)
		{
			x = 0;
			Log.debugRaw("x");
		}
		else x+=len;
	}

	out.flush();
	out.close();
	in.close();
	Log.debugRaw(" done.\n");
     }
     catch(IOException ex)
     {
     	Log.debug("Error with file IO ("+ex+")!");
     }
    }

    private void update(String file, String type, int bytes)
    {
     	if(listeners == null) return;
	else
	{
	 for(int i=0; i<listeners.size(); i++)
	 {
	   ConnectionListener listener = (ConnectionListener) listeners.elementAt(i);
	    listener.updateProgress(file, type, bytes);
	 }
	}
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
    public void fireDirectoryUpdate()
    {
    	if(listeners == null) return;
	else
	{
		for(int i=0; i<listeners.size(); i++)
		{
			((ConnectionListener)listeners.elementAt(i)).updateRemoteDirectory(this);
		}
	}
    }

    public boolean login(String user, String pass)
    {
    	return true;
    }

}
