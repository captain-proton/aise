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

public class HostChooser extends HFrame implements ActionListener, WindowListener
{
    public static HTextField host = new HTextField("Hostname:","localhost");
    public static HTextField user = new HTextField("Username:","anonymous");
    //public static HTextField pass = new HTextField("Password:","none@nowhere.no");
    public static HPasswordField pass = new HPasswordField("Password:","none@nowhere.no");
    public HTextField port = new HTextField("Port:    ","21");
    public HTextField cwd  = new HTextField("Remote:  ",Settings.defaultDir);
    public HTextField lcwd  = new HTextField("Local:   ",Settings.defaultWorkDir);
    public HTextField dl = new HTextField("Max. connections:    ","3");
    
    private JCheckBox dirBox = new JCheckBox("Use default directories", Settings.getUseDefaultDir());
    private JCheckBox modeBox = new JCheckBox("Use active Ftp (no need to)", false);
    private JCheckBox threadBox = new JCheckBox("Multiple connections", false);
    
    private HPanel okP = new HPanel();
    private HButton ok = new HButton("Connect");

    private HButton backMode = new HButton("Yes");
    private HButton frontMode = new HButton("No");
    private HFrame h = new HFrame();

    private HPanel listP = new HPanel();
    private HButton list = new HButton("Choose from or edit list...");

    private ComponentListener listener = null;
    private int mode = 0;


    public HostChooser(ComponentListener l)
    {
    	listener = l;
	init();
    }

    public HostChooser()
    {
    	init();
    }

    public void init()
    {
        setSize(600,260);
        setLocation(50,150);
        setTitle("Ftp Connection...");
        setBackground(okP.getBackground());
        getContentPane().setLayout(new GridLayout(6,2));

        getContentPane().add(host);
        getContentPane().add(port);
        getContentPane().add(user);
        getContentPane().add(pass);
	getContentPane().add(dirBox);
	getContentPane().add(modeBox);
        getContentPane().add(lcwd);
        getContentPane().add(cwd);
	getContentPane().add(threadBox);
        getContentPane().add(dl);
	
	modeBox.setSelected(!Settings.getFtpPasvMode());
	threadBox.setSelected(Settings.getEnableMultiThreading());
	dirBox.setSelected(Settings.getUseDefaultDir());

        try
        {
            File f = new File(Settings.appHomeDir);
            f.mkdir();
            File f1 = new File(Settings.login);
            f1.createNewFile();
            File f2 = new File(Settings.login_def);
            f2.createNewFile();
            File f3 = new File(Settings.ls_out);
            f3.createNewFile();
            File f4 = new File(Settings.sortls_out);
            f4.createNewFile();
            File f5 = new File(Settings.sortsize_out);
            f5.createNewFile();
	    File f6 = new File(Settings.permissions_out);
            f6.createNewFile();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        LoadSet l = new LoadSet();
        String login[] = l.loadSet(Settings.login_def);

        if(login != null)
        {
            host.setText(login[0]);
            user.setText(login[1]);
            if(login[3] != null)
                port.setText(login[3]);
            if(login[4] != null)
                cwd.setText(login[4]);
            if(login[5] != null)
                lcwd.setText(login[5]);
        }

        if(Settings.cachePass)
        {
            if(login != null)
            {
                pass.setText(login[2]);
            }
        }
        else
            pass.setText("");

        getContentPane().add(okP);
        okP.add(ok);
        ok.addActionListener(this);

        getContentPane().add(listP);
        listP.add(list);
        list.addActionListener(this);

	dirBox.addActionListener(this);
	setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

	lcwd.setEnabled(!dirBox.isSelected());
	cwd.setEnabled(!dirBox.isSelected());
	pass.text.addActionListener(this);

        setModal(false);
        setVisible(false);
	addWindowListener(this);
	prepareBackgroundMessage();
    }

    public void update() {
        setVisible(true);
        toFront();
	host.requestFocus();
    }

    public void update(String url) {
     try
     {
        FtpURLConnection uc = new FtpURLConnection(new java.net.URL(url));
	FtpConnection con = uc.getFtpConnection();
	con.addConnectionListener((ConnectionListener) JFtp.localDir);
	con.addConnectionListener((ConnectionListener) JFtp.remoteDir);
	JFtp.remoteDir.setCon(con);

	uc.connect();
 	String response = uc.getLoginResponse();

	if(!response.equals(FtpConnection.LOGIN_OK)) {
		host.setText(uc.getHost());
		port.setText(Integer.toString(uc.getPort()));
		user.setText(uc.getUser());
		pass.setText(uc.getPass());
        	setVisible(true);
        	toFront();
		host.requestFocus();
	 }
	 else
	 {
		this.dispose();
		if(listener != null) listener.componentResized(new ComponentEvent(this, 0));
		JFtp.mainFrame.setVisible(true);
            	JFtp.mainFrame.toFront();
	 }
     }
     //catch(MalformedURLException ex)
     //{
     //	Log.debug("Maformed URL!");
     //	ex.printStackTrace();
     //}
     catch(IOException ex)
     {
     	Log.debug("Error!");
     	ex.printStackTrace();
     }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == ok || e.getSource() == pass.text)
        {
	    // Switch windows
	    this.setVisible(false);
	    JFtp.mainFrame.setVisible(true);
            JFtp.mainFrame.toFront();

	    FtpConnection con = null;
            JFtp.setHost(host.getText());
            String htmp = StringUtils.cut(host.getText()," ");
            String utmp = StringUtils.cut(user.getText()," ");
            String ptmp = StringUtils.cut(pass.getText()," ");
            String potmp = StringUtils.cut(port.getText()," ");

	    Settings.setProperty("jftp.ftpPasvMode", !modeBox.isSelected());
	    Settings.setProperty("jftp.enableMultiThreading", threadBox.isSelected());
	    Settings.setProperty("jftp.useDefaultDir", dirBox.isSelected());

	    boolean pasv = Settings.getFtpPasvMode();
	    boolean threads = Settings.getEnableMultiThreading();
	    if(!pasv && threads) // only passive ftp threading works
	    {
	    	Settings.setProperty("jftp.enableMultiThreading", false);
		JDialog j = new JDialog();
		j.setTitle("Warning");
		j.setLocation(150,150);
		j.setSize(450,100);
		j.getContentPane().add(new JLabel(" Multithreading does not work with active ftp yet, disabled it..."));
		j.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		j.setModal(true);
		j.show();
	    }

	    int x = Integer.parseInt(dl.getText().trim());
	    Settings.maxConnections = x;

	    Settings.save();

	    if(dirBox.isSelected()) {
	     SaveSet s = new SaveSet(Settings.login_def,htmp,utmp,ptmp,potmp,Settings.defaultDir,Settings.defaultWorkDir);

             if(!JFtp.localDir.setPath(Settings.defaultWorkDir))
             {
                 if(!JFtp.localDir.setPath(System.getProperty("user.home")))
                 {
                     JFtp.localDir.setPath("/");
                 }
             }
	     con = new FtpConnection(htmp,Integer.parseInt(potmp),Settings.defaultDir);
	    }
	    else {
	     String dtmp = cwd.getText();
             String ltmp = lcwd.getText();
	     SaveSet s = new SaveSet(Settings.login_def,htmp,utmp,ptmp,potmp,dtmp,ltmp);

	     if(!JFtp.localDir.setPath(ltmp))
             {
                 if(!JFtp.localDir.setPath(System.getProperty("user.home")))
                 {
                     JFtp.localDir.setPath("/");
                 }
             }
	     con = new FtpConnection(htmp,Integer.parseInt(potmp),dtmp);
	    }

	    con.addConnectionListener((ConnectionListener) JFtp.localDir);
	    con.addConnectionListener((ConnectionListener) JFtp.remoteDir);
	    JFtp.remoteDir.setCon(con);

	    String response = con.login(utmp, ptmp);

	if(response.startsWith(FtpConnection.OFFLINE) && Settings.reconnect)
	{
	    	h.setVisible(true);
		while(mode == 0) pause(10);

		JFtp.mainFrame.setVisible(false);

		while(response.startsWith(FtpConnection.OFFLINE) && mode == 1)
		{
			   System.out.print("Server is full, next attempt in ");
			   int r = 5;

			   for(int i=0; i<r; r--)
			   {
			    System.out.print(""+r+"-");

			    try {Thread.sleep(1000);}
			    catch(Exception ex) {}
			   }
			   System.out.println("0...");
			    response = con.login(utmp, ptmp);

		}
		if(mode == 1)
		{
			JFtp.mainFrame.setVisible(true);
		}
		else
		{
			// Switch windows
			JFtp.mainFrame.setVisible(false);
			this.setVisible(true);
            		this.toFront();
			return;
		}
	    }
	    else if(!response.equals(FtpConnection.LOGIN_OK) || (response.startsWith(FtpConnection.OFFLINE) && (!Settings.reconnect))) {
			// Switch windows
			JFtp.mainFrame.setVisible(false);
			this.setVisible(true);
            		this.toFront();
			return;
	    }
	    //else
	    //{
            this.dispose();
	    if(listener != null) listener.componentResized(new ComponentEvent(this, 0));
	    //}
	}
	else if(e.getSource() == list)
	{
		HostList hl = new HostList(this);
		FtpHost selectedHost = hl.getFtpHost();
		if (selectedHost == null) return;
		host.setText(selectedHost.hostname);
		pass.setText(selectedHost.password);
		user.setText(selectedHost.username);

		Settings.setProperty("jftp.useDefaultDir", true);
		dirBox.setSelected(Settings.getUseDefaultDir());
		lcwd.setEnabled(!dirBox.isSelected());
		cwd.setEnabled(!dirBox.isSelected());
	}
	else if(e.getSource() == dirBox) {
		if(!dirBox.isSelected()) {
			lcwd.setEnabled(true);
			cwd.setEnabled(true);
		}
		else {
			lcwd.setEnabled(false);
			cwd.setEnabled(false);
		}
	}
	else if(e.getSource() == backMode) {
		mode = 1;
		h.setVisible(false);;
	}
	else if(e.getSource() == frontMode) {
		mode = 2;
		h.setVisible(false);
	}
    }

    private void prepareBackgroundMessage()
    {
	HPanel p = new HPanel();
	p.add(backMode);
	p.add(frontMode);
	p.setLayout(new FlowLayout(FlowLayout.CENTER));

	backMode.addActionListener(this);
	frontMode.addActionListener(this);

	h.getContentPane().setLayout(new BorderLayout(10,10));
	h.setTitle("Connection failed!");
	h.setLocation(150,200);
	JTextArea text = new JTextArea();
	h.getContentPane().add("Center",text);
	h.getContentPane().add("South",p);
	text.setText(" ---------------- Output -----------------\n\n"+
			   "The server is busy at the moment.\n\n"+
			   "Do you want JFtp to go to dissapear and try to login\n"+
			   "continuously?\n\n"+
			   "(It will show up again when it has initiated a connection)\n\n");
	JFtp.log.setText("");
	text.setEditable(false);
	h.pack();
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
