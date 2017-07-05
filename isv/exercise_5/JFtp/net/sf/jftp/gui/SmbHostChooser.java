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
import net.sf.jftp.config.*;
import net.sf.jftp.util.*;
import net.sf.jftp.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SmbHostChooser extends HFrame implements ActionListener, WindowListener
{
    public static HTextField host = new HTextField("URL:","smb://localhost/");
    JCheckBox lan = new JCheckBox("Browse LAN", true);
    public static HTextField user = new HTextField("Username:","guest");
    //public static HTextField pass = new HTextField("Password:","none@nowhere.no");
    public static HPasswordField pass = new HPasswordField("Password:","nopasswd");
    //public HTextField domain = new HTextField("Domain:    ","WORKGROUP");

    private HPanel okP = new HPanel();
    private HButton ok = new HButton("Connect");

    private ComponentListener listener = null;

    public SmbHostChooser(ComponentListener l)
    {
    	listener = l;
	init();
    }

    public SmbHostChooser()
    {
    	init();
    }

    public void init()
    {
        setSize(500,180);
        setLocation(100,150);
        setTitle("Smb Connection...");
        setBackground(okP.getBackground());
        getContentPane().setLayout(new GridLayout(3,2));

        getContentPane().add(host);
        getContentPane().add(lan);
        getContentPane().add(user);
        getContentPane().add(pass);

        getContentPane().add(okP);
	getContentPane().add(new JLabel(""));

        okP.add(ok);
        ok.addActionListener(this);

	host.setEnabled(!lan.isSelected());
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

	lan.addActionListener(this);
	pass.text.addActionListener(this);

        setModal(false);
        setVisible(false);
	addWindowListener(this);
    }

    public void update() {
        setVisible(true);
        toFront();
	host.requestFocus();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == lan)
        {
		host.setEnabled(!lan.isSelected());
        }
        else if(e.getSource() == ok || e.getSource() == pass.text)
        {
	    // Switch windows
	    this.setVisible(false);
	    JFtp.mainFrame.setVisible(true);
            JFtp.mainFrame.toFront();

	    SmbConnection con = null;
            //JFtp.setHost(host.getText());
            String htmp = StringUtils.cut(host.getText()," ");
            String utmp = StringUtils.cut(user.getText()," ");
            String ptmp = StringUtils.cut(pass.getText()," ");
            //String dtmp = StringUtils.cut(domain.getText()," ");
	    if(lan.isSelected()) htmp = null;

	    try
	    {
	     con = new SmbConnection(htmp,utmp,ptmp, ((ConnectionListener)JFtp.remoteDir));
	    JFtp.remoteDir.setCon(con);
	    con.setLocalPath(JFtp.localDir.getCon().getPWD());
	    con.addConnectionListener((ConnectionListener) JFtp.localDir);
	    con.addConnectionListener((ConnectionListener) JFtp.remoteDir);

	    if(htmp != null) JFtp.remoteDir.getCon().chdir(htmp);
	    else JFtp.remoteDir.getCon().chdir(JFtp.remoteDir.getCon().getPWD());
	    }
	    catch(Exception ex)
	    {
	    	Log.debug("Could not create SMBConnection, does this distribution come with jcifs?");
            }

            this.dispose();
	    if(listener != null) listener.componentResized(new ComponentEvent(this, 0));
	}
    }

    public void windowClosing(WindowEvent e)
    {
        //System.exit(0);
	this.dispose();
    }

    public void windowClosed(WindowEvent e)
    {}

    public void windowActivated(WindowEvent e)
    {}

    public void windowDeactivated(WindowEvent e)
    {}

    public void windowIconified(WindowEvent e)
    {}

    public void windowDeiconified(WindowEvent e)
    {}

    public void windowOpened(WindowEvent e)
    {}


    public Insets getInsets()
    {
        Insets std = super.getInsets();
        return new Insets(std.top + 15, std.left + 15, std.bottom + 15, std. right + 15);
    }

    public void pause(int time)
    {
    	try { Thread.sleep(time); }
	catch(Exception ex) {}
    }

}
