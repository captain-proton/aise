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
import net.sf.jftp.config.*;
import net.sf.jftp.util.*;
import net.sf.jftp.net.*;
import net.sf.jftp.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class DownloadList extends HPanel implements ActionListener
{
 
//private JTextArea text = new JTextArea();
private JList text = new JList();
private Hashtable downloads = new Hashtable();
private boolean working = false;
private long oldtime = 0;

private HImageButton resume = new HImageButton(Settings.resumeImage,"resume","Resume selected transfer...",this);
private HImageButton pause = new HImageButton(Settings.pauseImage,"pause","Pause selected ransfer...",this);
private HImageButton cancel = new HImageButton(Settings.deleteImage,"delete","Cancel selected transfer...",this);
private HImageButton clear = new HImageButton(Settings.clearImage,"clear","Remove old/stalled items from output...",this);
//private HImageButton rotate = new HImageButton(Settings.cmdImage,"rotate","Toggle selected transfer...",this);

//private int rotator = 0;

private static final String SEP = "--> ";

public DownloadList()
{
	setLayout(new BorderLayout());

	text.setCellRenderer(new DirCellRenderer());

	HPanel cmdP = new HPanel();

//	cmdP.add(rotate);
	cmdP.add(clear);

	cmdP.add(new JLabel("   "));

	cmdP.add(resume);
	cmdP.add(pause);

	cmdP.add(new JLabel("   "));

	cmdP.add(cancel);

        clear.setSize(24,24);
	clear.setBorder(true);
        cancel.setSize(24,24);
	cancel.setBorder(true);
//        rotate.setSize(24,24);
//	rotate.setBorder(true);
        resume.setSize(24,24);
	resume.setBorder(true);
        pause.setSize(24,24);
	pause.setBorder(true);

	JScrollPane dP = new JScrollPane(text);
	add("South",cmdP);
	add("Center",dP);
}

public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("delete"))
	{
		deleteCon();
	}
	else if(e.getActionCommand().equals("clear"))
	{
		//FtpConnection con = JFtp.getControlConnection();
		//JFtp.getConnectionHandler().fireProgressClear(con);
		downloads = new Hashtable();
		updateArea();
	}
//	else if(e.getActionCommand().equals("rotate"))
//	{
//		rotator++;
//		if(rotator >= downloads.size()) rotator = 0;
//	}
	else if(e.getActionCommand().equals("pause"))
	{
		pauseCon();
	}
		else if(e.getActionCommand().equals("resume"))
	{
		resumeCon();
	}
}

private void deleteCon()
{
	String cmd = getActiveItem();
	if(cmd == null) return;

	if(cmd.indexOf(Transfer.QUEUED) >= 0 || cmd.indexOf(Transfer.PAUSED) >= 0)
	{
		 cmd = getFile(cmd);
		 try
		 {
    			Transfer d = (Transfer) JFtp.getConnectionHandler().getConnections().get(cmd);
			if(d == null) {return;}
			d.work = false;
			d.pause = false;
		 }
		 catch(Exception ex)
		 {
			ex.printStackTrace();
	 	}
	}
	else
	{
		cmd = getFile(cmd);
		Transfer d = (Transfer) JFtp.getConnectionHandler().getConnections().get(cmd);
		if(d == null) return;

		DataConnection con = d.getDataConnection();
		con.getCon().work = false;

		try
		{
			con.sock.close();
			//con.getCon().abort();
			//if(Settings.getEnableMultiThreading()) con.getCon().disconnect();
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			//System.out.println("no socket close");
		}

		LocalIO.pause(1000);
		updateList(getRawFile(getActiveItem()),DataConnection.FAILED,-1,-1);
	 }
}

// fake pause, it disconnects instead
private void pauseCon()
{
	String cmd = getActiveItem();
	if(cmd == null) return;

	if(cmd.indexOf(DataConnection.GET) >= 0 || cmd.indexOf(DataConnection.PUT) >= 0)
	{
		cmd = getFile(cmd);
		Transfer d = (Transfer) JFtp.getConnectionHandler().getConnections().get(cmd);
		if(d == null) return;
		d.pause = true;

		DataConnection con = d.getDataConnection();
		try
		{
			con.sock.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		d.prepare();
	 }
}


private void resumeCon()
{
	String cmd = getActiveItem();
	if(cmd == null) return;

	if(cmd.indexOf(Transfer.PAUSED) >= 0 || cmd.indexOf(Transfer.QUEUED) >= 0)
	{
		 cmd = getFile(cmd);
		 try
		 {
    			Transfer d = (Transfer) JFtp.getConnectionHandler().getConnections().get(cmd);
			if(d == null) return;
			d.work = true;
			d.pause = false;
		 }
		 catch(Exception ex)
		 {
			ex.printStackTrace();
	 	}

	}
}

private String getActiveItem()
{
	String tmp = (String) text.getSelectedValue().toString();
	if(tmp == null) return "";
	else return tmp;
}

public synchronized void updateList(String file, String type, long bytes, long size)
{
	String message =  type + ": <" + file +"> ";

	if(!safeUpdate())
	{
		if(!type.startsWith(DataConnection.DFINISHED) && ! type.startsWith(DataConnection.FINISHED) && !type.startsWith(DataConnection.FAILED)
		    && !type.startsWith(Transfer.PAUSED) && !type.startsWith(Transfer.REMOVED))
		{
			return;
		}
	}

	// directory
	int count = 0;

	if(type.startsWith(DataConnection.GETDIR) || type.startsWith(DataConnection.PUTDIR) || type.startsWith(DataConnection.PUTDIR) || type.startsWith(DataConnection.DFINISHED))
	{
		//System.out.println(type);
		String tmp = type.substring(type.indexOf(":")+1);
		type = type.substring(0, type.indexOf(":"));
		count = Integer.parseInt(tmp);
		message = type + ": <" + file + "> ";
	}
	// ---------------

	//System.out.print(size+":");
	String tmp;
	long s = (long)(size/1024);
	if(s > 0) tmp = Long.toString(s);
	else tmp = "?";

	//System.out.println(message);

	if(type.equals(DataConnection.GET) || type.equals(DataConnection.PUT)) message = message + (int)(bytes/1024) + " / " + tmp + " kb";
	else if(type.equals(DataConnection.GETDIR) || type.equals(DataConnection.PUTDIR)) message = message + (int)(bytes/1024) + " kb of file #" + count;
	else if(type.startsWith(DataConnection.DFINISHED))
	{
	 	message = message + " " + count + " files.";
	}

	if(type.equals(DataConnection.FINISHED) || type.startsWith(DataConnection.DFINISHED))
	{
		JFtp.getConnectionHandler().removeConnection(file);
	}
	else if(type.equals(DataConnection.FAILED))
	{
	}

	if(!(file.indexOf(Settings.ls_out_name) >= 0))
	{
		downloads.put(file, message);
	}

	updateArea();
}

private synchronized DirEntry[] toArray()
{

	DirEntry[] f = new DirEntry[downloads.size()];
	int i = 0;

	Enumeration k = downloads.elements();

	while(k.hasMoreElements())
	{
		String tmp = (String) k.nextElement();
		DirEntry d = new DirEntry(tmp, null);
		if(getFile(tmp).endsWith("/")) d.setDirectory();
		d.setNoRender();
		f[i] = d;
		i++;
	}

	return f;
}

private synchronized void updateArea()
{
	 int idx = text.getSelectedIndex();

	 DirEntry[] f = toArray();

	 text.setListData(f);

	  if(f.length == 1 && idx < 0) text.setSelectedIndex(0);
	  else  text.setSelectedIndex(idx);
}


private String getFile(String msg)
{
	String f = msg.substring(msg.indexOf("<")+1);
	f = f.substring(0,f.lastIndexOf(">"));
	//System.out.println(f);
	return getRealName(f);
}

private String getRealName(String file)
{
	//System.out.println(">>>"+file);
	Enumeration e = JFtp.getConnectionHandler().getConnections().keys();
	while(e.hasMoreElements())
	{
		String tmp = (String) e.nextElement();
		//System.out.println(tmp);
		if(tmp.endsWith(file)) return tmp;
	}

	return file;
}

private String getRawFile(String msg)
{
	String f = msg.substring(msg.indexOf("<")+1);
	f = f.substring(0,f.lastIndexOf(">"));
	//System.out.println(f);
	return f;
}

private boolean safeUpdate()
{
        long time = System.currentTimeMillis();

	if((time-oldtime) < Settings.refreshDelay)
	{
		return false;
	}

	oldtime = time;
	return true;
}

}
