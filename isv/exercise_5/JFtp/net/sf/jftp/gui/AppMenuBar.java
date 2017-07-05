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
import net.sf.jftp.config.Settings;
import net.sf.jftp.net.*;
import net.sf.jftp.util.*;
import net.sf.jftp.JFtp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenuBar extends JMenuBar implements ActionListener
{
 private JFtp jftp;
 JMenu file = new JMenu("File");
 JMenu opt = new JMenu("Options");
 JMenu view = new JMenu("View");
 JMenu info = new JMenu("Info");

 JMenuItem newCon = new JMenuItem("Connect to FTP server");
 JMenuItem smbCon = new JMenuItem("Browse LAN / connect to SMB server");
 JMenuItem close = new JMenuItem("Disconnect and connect to Filesystem");
 JMenuItem exit = new JMenuItem("Exit");

 JMenuItem readme = new JMenuItem("Show readme");
 JMenuItem changelog = new JMenuItem("View the changelog");
 JMenuItem todo = new JMenuItem("What's next?");

  JCheckBoxMenuItem resuming = new JCheckBoxMenuItem("Enable Resuming", Settings.enableResuming);
  JCheckBoxMenuItem ask = new JCheckBoxMenuItem("Always ask to resume", Settings.askToResume);

  public static JCheckBoxMenuItem fadeMenu = new JCheckBoxMenuItem("Enable status animation", true);
 JMenuItem clear = new JMenuItem("Clear log");


public AppMenuBar(JFtp jftp)
{
	this.jftp = jftp;

	newCon.addActionListener(this);
	close.addActionListener(this);
	exit.addActionListener(this);
	readme.addActionListener(this);
	changelog.addActionListener(this);
	todo.addActionListener(this);
	resuming.addActionListener(this);
	ask.addActionListener(this);
	smbCon.addActionListener(this);
	clear.addActionListener(this);

	file.add(newCon);
	file.add(smbCon);
	file.add(close);
	file.add(exit);
	file.insertSeparator(2);
	file.insertSeparator(4);

	opt.add(resuming);
	opt.add(ask);

	view.add(fadeMenu);
	view.add(clear);

	info.add(readme);
	info.add(changelog);
	info.add(todo);

	add(file);
	add(opt);
	add(view);
	add(info);
}

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == clear)
        {
		jftp.clearLog();
        }
        else if(e.getSource() == readme)
        {
		show(Settings.readme);
        }
	else if(e.getSource() == changelog)
        {
		show(Settings.changelog);
        }
        else if(e.getSource() == todo)
        {
		show(Settings.todo);
        }
        else if(e.getSource() == exit)
        {
		jftp.windowClosing(null); // handles everything
        }
        else if(e.getSource() == close)
        {
		jftp.safeDisconnect();
		FilesystemConnection con = new FilesystemConnection();
		jftp.remoteDir.setCon(con);
		con.addConnectionListener((ConnectionListener)jftp.remoteDir);
		if(!con.chdir("/")) con.chdir("C:\\");
	}
        else if(e.getSource() == newCon && (!jftp.uiBlocked))
        {
	    jftp.safeDisconnect();

            HostChooser hc = new HostChooser();
            hc.toFront();
	    hc.setModal(true);
            hc.update();
        }
        else if(e.getSource() == smbCon && (!jftp.uiBlocked))
        {
	    jftp.safeDisconnect();

	    SmbHostChooser hc = new SmbHostChooser();
            hc.toFront();
	    hc.setModal(true);
            hc.update();
        }
	else if(e.getSource() == resuming)
	{
		Settings.enableResuming = resuming.getState();
		ask.setEnabled(Settings.enableResuming);
	}
	else if(e.getSource() == ask)
	{
		Settings.askToResume = ask.getState();
	}
    }

    private void show(String file)
    {
            java.net.URL url = ClassLoader.getSystemResource(file);
            if(url == null)
            {
                url = HImage.class.getResource("/"+file);
            }
            Displayer d = new Displayer(url);
    }
}
