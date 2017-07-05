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
 * Interface for all connection types
 */
public interface BasicConnection
{
    public boolean hasUploaded = false;

    public void removeFileOrDir(String file);
    public void sendRawCommand(String cmd);

    //public boolean login(String user, String pass);
    public void disconnect();
    public boolean isConnected();
    public String getPWD();

    public boolean cdup();
    public boolean mkdir(String dirName);
    public void list(String outfile) throws IOException;
    public boolean chdir(String p);
    public boolean chdirNoRefresh(String p);
    public String getLocalPath();
    public boolean setLocalPath(String newPath);
    public String[] sortLs(String file);
    public String[] sortSize(String file);
    public int[] getPermissions(String file);
    public void handleDownload(String file);
    public void handleUpload(String file);
    public void download(String file);
    public void upload(String file);

    public void addConnectionListener(ConnectionListener listener);
    public void setConnectionListeners(Vector listeners);
}
