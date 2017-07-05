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

public class FilesystemConnection implements BasicConnection
{
    private String path = "";
    private String pwd = "";
    private Vector listeners = new Vector();
    private  String files[];
    private String size[] = new String[0];
    private int perms[] = null;

    public static int localMessageAfterBytes = 1000000;
    public static int filesystemBuffer = 128000;

    public FilesystemConnection() {}

    public FilesystemConnection(String path, ConnectionListener l)
    {
    	listeners.add(l);
	chdir(path);
    }

    public void removeFileOrDir(String file)
    {
    	String tmp = file;
        if(StringUtils.isRelative(file)) tmp = getPWD() + file;

	File f = new File(tmp);
	if(f.exists() && f.isDirectory())
	{
		cleanLocalDir(tmp);
	}

	//System.out.println(tmp);
	if(!f.delete()) Log.debug("Removal failed.");
    }

    private void cleanLocalDir(String dir)
    {
	dir = dir.replace('\\','/');

        if(!dir.endsWith("/")) dir = dir + "/";

        //String remoteDir = StringUtils.removeStart(dir,path);
	//System.out.println(">>> " + dir);

        File f2 = new File(dir);
        String tmp[] = f2.list();
	if(tmp == null) return;

        for(int i=0; i<tmp.length; i++)
        {
            File f3 = new File(dir+tmp[i]);

            if(f3.isDirectory())
            {
                //System.out.println(dir);
                cleanLocalDir(dir+tmp[i]);
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
    	return pwd;
    }

    public boolean cdup()
    {
    	return chdir(pwd.substring(0,pwd.lastIndexOf("/")));
    }

    public boolean mkdir(String dirName) {
    	if(StringUtils.isRelative(dirName)) dirName = getPWD() + dirName;
	File f = new File(dirName);

	boolean x = f.mkdir();
	fireDirectoryUpdate();

	return x;
    }

    public void list(String outfile) throws IOException
    {
    }

    public boolean chdir(String p)
    {
    	String p2 = processPath(p);
	if(p2 == null) return false;
	pwd = p2;

	fireDirectoryUpdate();

	return true;
    }

    public boolean chdirNoRefresh(String p)
    {
    	String p2 = processPath(p);
	if(p2 == null) return false;

	//System.out.println(p2);
	pwd = p2;

	return true;
    }

    public String getLocalPath()
    {
    	//System.out.println("local: " + path);
    	return path;
    }

    public String processPath(String p)
    {
        p = p.replace('\\','/');
    	//System.out.print("processPath 1: "+p);
        if(StringUtils.isRelative(p)) p = pwd + p;
	p = p.replace('\\','/');

	//System.out.println(", processPath 2: "+p);
        File f = new File(p);
	String p2;

        if(f.exists())
        {
            try
            {
                p2 = f.getCanonicalPath();
                p2 = p2.replace('\\','/');

                if(!p2.endsWith("/"))
                {
                    p2 = p2 + "/";
                }


		return p2;
            }
            catch(IOException ex)
            {
                Log.debug("Error: can not get pathname (processPath)!");
		return null;
            }
	         }
        else
        {
            Log.debug("(processpPath) No such path: \"" + p + "\"");
	    return null;
        }
    }

    public boolean setLocalPath(String p)
    {
        p = p.replace('\\','/');
        //System.out.print("local 1:" + p);
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
    	File f = new File(pwd);
	files = f.list();
	if(files == null) return new String[0];
	size = new String[files.length];
	perms = new int[files.length];
	int accessible = 0;

       for(int i=0; i<files.length; i++)
       {
       File f2= new File(pwd+files[i]);

       	if(f2.isDirectory() && !files[i].endsWith("/")) files[i] = files[i] + "/";

	size[i] = "" + new File(pwd+files[i]).length();

	if(f2.canWrite()) accessible = FtpConnection.W;
	else if(f2.canRead()) accessible = FtpConnection.R;
	else accessible = FtpConnection.DENIED;
	perms[i] = accessible;
	//System.out.println(pwd+files[i] +" : " +accessible + " : " + size[i]);
       }

       return files;
    }

    public String[] sortSize(String file)
    {
    	return size;
    }

    public int[] getPermissions(String file)
    {
	return perms;
    }

    public void handleDownload(String file)
    {
	transfer(file);
    }

    public void handleUpload(String file)
    {
	transfer(file);
    }

    public void download(String file)
    {
	transfer(file);
    }

    public void upload(String file)
    {
	transfer(file);
    }

    private void transferDir(String dir, String out)
    {
        File f2 = new File(dir);
        String tmp[] = f2.list();
	if(tmp == null) return;

	File fx = new File(out);
	if(!fx.mkdir()) Log.debug("Can not create directory: " + out + " - already exist or permission denied?");

        for(int i=0; i<tmp.length; i++)
        {
	    tmp[i] = tmp[i].replace('\\','/');
	    //System.out.println("1: " + dir+tmp[i] + ", " + out +tmp[i]);
            File f3 = new File(dir+tmp[i]);

            if(f3.isDirectory())
            {
	    	if(!tmp[i].endsWith("/")) tmp[i] = tmp[i] + "/";
                transferDir(dir+tmp[i], out + tmp[i]);
            }
            else
            {
                work(dir +tmp[i], out + tmp[i]);
            }
        }

    }

    private void transfer(String file)
    {
    	String out = StringUtils.getDir(file);

	if(StringUtils.isRelative(file)) file = getPWD() + file;

	file = file.replace('\\','/');
	out = out.replace('\\','/');

	String outfile = StringUtils.getFile(file);

	if(file.endsWith("/"))
	{
		transferDir(file, getLocalPath() + out);
		return;
	}
	else work(file, getLocalPath()+outfile);
    }

   private void work(String file, String outfile)
   {
     try
     {
        Log.debugRaw(file+": ");
     	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outfile));
	BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
	byte buf[] = new byte[filesystemBuffer];
	int len = 0;
	int x = 0;
	//System.out.println(file+":"+getLocalPath()+outfile);

	while(true)
	{
		len = in.read(buf);
		//System.out.print(".");
		if(len == StreamTokenizer.TT_EOF) break;
		out.write(buf,0,len);

		if(x >localMessageAfterBytes)
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
