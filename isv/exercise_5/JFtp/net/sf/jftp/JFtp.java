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
package net.sf.jftp;

import net.sf.jftp.gui.framework.*;
import net.sf.jftp.gui.*;
import net.sf.jftp.config.Settings;
import net.sf.jftp.util.*;
import net.sf.jftp.net.*;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JFtp extends JPanel implements WindowListener, ComponentListener, Logger
{
    public static boolean mainUsed = false;
    public static StatusPanel statusP;
    public static JLabel statusL = new JLabel("Welcome to JFtp...                                                            ");
    public static JFrame mainFrame;
    public static Dir localDir;
    public static Dir remoteDir;
    public static DownloadList dList = new DownloadList();
    public static boolean uiBlocked = false;
    //public static BasicConnection controlConnection = null;

    private boolean initSize = true;
    private String oldText = "";

    /** JSplitPane that holds the local and remote DirPanels */
    private JSplitPane dirP = null;

    /** JSplitPane that holds the directory panes and the log/dl JSplitPane */
    private JSplitPane workP = null;

    /** JSplitPane that holds the log download parts */
    private JSplitPane logP = null;

    private static JScrollPane logSp;
    public static JTextArea log;
    
    public HostChooser hc;

    public JFtp()
    {
        Log.setLogger(this);
        // we have jesktop-environment
        statusP.remove(statusP.close);
        init();
    }

    public JFtp(boolean mainUsed)
    {
        Log.setLogger(this);
        this.mainUsed = mainUsed;
        init();
    }

    public void init()
    {
        setLayout(new BorderLayout());

        setBackground(GUIDefaults.mainBack);
        setForeground(GUIDefaults.front);

        statusP = new StatusPanel(this);
        add("North",statusP);

        localDir = (Dir) new LocalDir(Settings.defaultWorkDir);

        remoteDir = (Dir) new RemoteDir();
	remoteDir.setDownloadList(dList);

	Dimension d = Settings.getWindowSize();
        setPreferredSize(d);
        setSize(d);
        int width = (int)d.getWidth();
        int height = (int)d.getHeight();
        dirP= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, (Component) localDir,(Component)  remoteDir);

	log = new JTextArea();
        log.setBackground(GUIDefaults.back);
        log.setForeground(GUIDefaults.front);
        log.setEditable(false);
        logSp = new JScrollPane(log);

        logP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, logSp, dList);

        workP = new JSplitPane(JSplitPane.VERTICAL_SPLIT, dirP, logP);
        workP.setSize(new Dimension((int)width, (int)height));
        add("Center", workP);

        dirP.setMinimumSize(new Dimension((int)width, (int)(height * 0.60)));
        dirP.setPreferredSize(new Dimension((int)width, (int)(height * 0.75)));
        dirP.setSize(new Dimension((int)width, (int)(height * 0.75)));
        dirP.setDividerLocation(0.5);
        logSp.setMinimumSize(new Dimension((int)(width/1.8000), (int)(height * 0.20)));
        logSp.setPreferredSize(new Dimension((int)(width/1.8000), (int)(height * 0.25)));
        logSp.setSize(new Dimension((int)(width/1.8000), (int)(height * 0.25)));
        dList.setMinimumSize(new Dimension((int)(width/2.5), (int)(height * 0.20)));
        dList.setPreferredSize(new Dimension((int)(width/2.5), (int)(height * 0.25)));
        dList.setSize(new Dimension((int)(width/2.5), (int)(height * 0.25)));
        workP.setDividerLocation(0.6);

        addComponentListener(this);
        componentResized(new ComponentEvent(dirP,0));
        componentResized(new ComponentEvent(logSp,0));
        componentResized(new ComponentEvent(workP,0));

        validate();
        setVisible(true);

        if(!mainUsed)
        {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    chooseHost();
                }
            });
        }
        else
        {
            chooseHost();
        }


    }

    protected void chooseHost() {
        hc = new HostChooser(this);
        if(!mainUsed)
        {
	    hc.update();
        }
    }

    public static String getHost()
    {
        return statusP.getHost();
    }

    public static void setHost(String which)
    {
        statusP.setHost(which);
    }

    public static void localUpdate()
    {
        localDir.fresh();
    }

    public static void remoteUpdate()
    {
    	remoteDir.fresh();
    }

    public void windowClosing(WindowEvent e)
    {
   	Settings.setProperty("jftp.window.width", this.getWidth());
        Settings.setProperty("jftp.window.height", this.getHeight());

	if(!mainUsed)
        {
        	Settings.setProperty("jftp.window.x", (int)this.getLocationOnScreen().getX());
        	Settings.setProperty("jftp.window.y", (int)this.getLocationOnScreen().getY());
	}
	else
        {
        	Settings.setProperty("jftp.window.x", (int)mainFrame.getLocationOnScreen().getX());
        	Settings.setProperty("jftp.window.y", (int)mainFrame.getLocationOnScreen().getY());
	}

        Settings.save();
	safeDisconnect();
        System.exit(0);
    }

    public static void safeDisconnect()
    {
    	BasicConnection con = remoteDir.getCon();

	if(con != null && con.isConnected())
	{
        	try {
            		con.disconnect();
        	} catch(Exception ex) {
        	}
	}
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

    public void componentHidden(ComponentEvent e)
    {}

    public void componentMoved(ComponentEvent e)
    { }

    public void componentShown(ComponentEvent e)
    {}

    public void componentResized(ComponentEvent e)
    {
        localDir.actionPerformed(this,"local");
        remoteDir.actionPerformed(this,"remote");
        validate();
    }

    private static void compile()
    {
    /*
     try
     {
    	Compiler.enable();
	System.out.println(Compiler.compileClass(Class.forName("java.util.Hashtable")));
	System.out.println(Compiler.compileClasses("net.sf.jftp"));
	System.out.println(Compiler.compileClasses("net.sf.jftp.JFtp"));
	System.out.println(Compiler.compileClasses("JFtp"));
      }
      catch(ClassNotFoundException ex)
      {
      	ex.printStackTrace();
      }
      */
    }

    public static void main(String argv[])
    {
        compile();
        if(Settings.autoUpdate) checkForUpgrade();

    	Settings.enableResuming = true;
        JFtp jftp = new JFtp(true);

        mainFrame = new JFrame();
		Point p = Settings.getWindowLocation();
        mainFrame.setLocation(p);
        mainFrame.setTitle(Settings.title + " - Version "+getVersion());
        mainFrame.setResizable(Settings.resize);
        mainFrame.addWindowListener(jftp);
        Image icon = HImage.getImage(jftp, Settings.iconImage);
        mainFrame.setIconImage(icon);
        mainFrame.setFont(GUIDefaults.font);
	mainFrame.setJMenuBar(new AppMenuBar(jftp));
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.getContentPane().add("Center", jftp);
        mainFrame.pack();
        mainFrame.validate();
	mainFrame.setVisible(true);

	if(argv.length > 0)
	{
		jftp.hc.update(argv[0]);
	}
	else
	{
		//jftp.hc.update();
	}
    }

    private void log(String msg)
    {
        if(msg.startsWith("200") || msg.startsWith("227"))
        {
            if(msg.indexOf("NOOP") > 0 || msg.indexOf("Type") > 0 ||
                    msg.indexOf("MODE") > 0 || msg.indexOf("Passive") > 0)
            {
                if(Settings.hideStatus)
                {
                    return;
                }
            }
        }

        log.append(" "+msg);

        JScrollBar bar = logSp.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
	logSp.paintImmediately(0,0,logSp.getSize().width,logSp.getSize().height);
    }

    private void logRaw(String msg)
    {
        log.append(msg);
	logSp.paintImmediately(0,0,logSp.getSize().width,logSp.getSize().height);
    }

    public void clearLog()
    {
        log.setText("");
	logSp.paintImmediately(0,0,logSp.getSize().width,logSp.getSize().height);
    }

    private void log(String msg, Throwable throwable)
    {
        PrintWriter p = new PrintWriter(new StringWriter());
        throwable.printStackTrace( p );
        log(msg);
        log(p.toString());
    }

    public void debug(String msg)
    {
       log(msg+"\n");
    }

    public void debugRaw(String msg)
    {
       logRaw(msg);
    }

    public void debug(String msg, Throwable throwable)
    {
        log(msg, throwable);
    }

    public void warn(String msg)
    {
        log(msg);
    }

    public void warn(String msg, Throwable throwable)
    {
        log(msg, throwable);
    }

    public void error(String msg)
    {
        log( msg );
    }

    public void error(String msg, Throwable throwable)
    {
        log( msg, throwable );
    }

    public void info(String msg)
    {
        log( msg );
    }

    public void info(String msg, Throwable throwable)
    {
        log( msg, throwable );
    }

    public void fatal(String msg)
    {
        log( msg );
    }

    public void fatal(String msg, Throwable throwable)
    {
        log( msg, throwable );
    }

    public void debugSize(int size, boolean recv, boolean last, String file)
    {

    }

    public static String getVersion()
    {
     try
     {
        URL u = ClassLoader.getSystemResource(Settings.readme);
       if(u == null) u = HImage.class.getResource("/"+Settings.readme);
       DataInputStream i = new DataInputStream(u.openStream());
       String tmp = i.readLine();
       tmp = tmp.substring(tmp.lastIndexOf(">")+1);
       tmp = tmp.substring(0,tmp.indexOf("<"));
       return tmp;
     }
     catch(Exception ex)  {}

     return "";
    }

    private static void checkForUpgrade()
    {
     try
     {
       System.out.println("Checking for updates...");
       URL u = ClassLoader.getSystemResource(Settings.readme);
       if(u == null) u = HImage.class.getResource("/"+Settings.readme);
       DataInputStream i = new DataInputStream(u.openStream());
       String tmp = i.readLine();
       tmp = tmp.substring(tmp.lastIndexOf(">"));
       tmp = tmp.substring(tmp.indexOf(".")+1);
       tmp = tmp.substring(0,tmp.indexOf("<"));

       int x =Integer.parseInt(tmp) + 1;

       String nextVersion = "jftp-1.";
       if(x < 10) nextVersion = nextVersion + "0";
       nextVersion = nextVersion +x+".tar.gz";

        //System.out.println(nextVersion);

	File dl = new File(nextVersion);
	if(!dl.exists() || dl.length() <= 0)
        {
     	URL url = new URL("http://osdn.dl.sourceforge.net/sourceforge/j-ftp/"+nextVersion);
        BufferedOutputStream f = new BufferedOutputStream(new FileOutputStream(dl));
	BufferedInputStream in = new BufferedInputStream(url.openStream());
	byte buf[] = new byte[4096];
	int stat = 1;

	System.out.println("\nDownloading update: "+dl.getAbsolutePath() + "\n\n");

	while(stat > 0)
	{
		//while(in.available() <= 0) LocalIO.pause(10);
		stat = in.read(buf);
		if(stat == -1) break;
		f.write(buf,0,stat);
		System.out.print(".");
	}

	f.flush();
	f.close();
	in.close();
        }

	System.out.println("\n\n\nA newer version was found!\nPlease install the File "+dl.getAbsolutePath()+
					" or even better visit the homepage to download the latest version...\n"+
					"you can turn this feature off if you don't like it (view readme for details)\n\nStarting anyway...\n\n");
	LocalIO.pause(5000);
	//System.exit(0);
   }
   catch(Exception ex)
   {
   	// FileNotFoundException, everything is ok
	//ex.printStackTrace();
   }

    System.out.println("Finished check...");
  }


  public static ConnectionHandler getConnectionHandler()
  {
   BasicConnection con = remoteDir.getCon();

   if(con != null && con instanceof FtpConnection)
  	return ((FtpConnection)con).getConnectionHandler();
   else return null;
  }

}
