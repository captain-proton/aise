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
package net.sf.jftp.gui;

import net.sf.jftp.gui.framework.*;
import net.sf.jftp.net.*;
import net.sf.jftp.*;
import net.sf.jftp.config.Settings;
import net.sf.jftp.util.*;

import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class DirLister implements ActionListener
{
    private int length;
    private String[] files;
    private String[] sizes;
    private int[] perms;
    private boolean isDirectory = true;
    public boolean finished = false;
    private BasicConnection con;

    public DirLister(BasicConnection con) //String type)
    {
	this.con = con;
	init();
    }

    public void init()
    {
        try {
		String outfile = Settings.ls_out;
		//BasicConnection con = JFtp.getControlConnection();
		con.list(outfile);
        	files = con.sortLs(outfile);
        	sizes = con.sortSize(outfile);
        	length = files.length;
		perms = con.getPermissions(outfile);
		isDirectory = true;
        }
        catch(Exception ex)
        {
		ex.printStackTrace();
      		isDirectory = false;
        }

        finished = true;
    }

    public void actionPerformed(ActionEvent e)
    {
    }

    public boolean isOk()
    {
        return isDirectory;
    }

    public int getLength()
    {
        return length;
    }

    public String[] list()
    {
        return files;
    }

    public String[] sList()
    {
    	return sizes;
    }
    
    public int[] getPermissions()
    {
    	return perms;
    }
    
}
