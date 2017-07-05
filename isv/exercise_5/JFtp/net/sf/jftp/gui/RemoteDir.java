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
import net.sf.jftp.net.*;
import net.sf.jftp.util.*;
import net.sf.jftp.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class RemoteDir extends DirPanel implements ListSelectionListener, ActionListener, ConnectionListener
{
    HImageButton deleteButton;
    HImageButton mkdirButton;
    HImageButton cmdButton;
    HImageButton refreshButton;
    HImageButton cdButton;
    HImageButton uploadButton;
    HImageButton downloadButton;
    //HImageButton openButton;

    static final String deleteString = "rm";
    static final String mkdirString = "mkdir";
    static final String refreshString = "fresh";
    static final String cdString = "cd";
    static final String cmdString = "cmd";
    static final String downloadString = "<-";
    static final String uploadString = "->";
    //static final String openString = "open";

    private DirCanvas label = new DirCanvas();
    private boolean pathChanged = true;
    private boolean firstGui = true;
    private int pos=0;
    private JPanel p = new JPanel();
    private BorderPanel buttonPanel = new BorderPanel();
    private JPanel currDirPanel = new JPanel();
    public JList jl = new JList();
    private DefaultListModel jlm;
    private JScrollPane jsp = new JScrollPane(jl);
    private int tmpindex = -1;
    private Hashtable dummy = new Hashtable();

    private HImageButton list = new HImageButton(Settings.listImage,"list","Show remote listing...",this);
    private HImageButton transferType = new HImageButton(Settings.typeImage,"type","Toggle transfer type...",this);


public RemoteDir()
{
	type = "remote";
	con = new FilesystemConnection();
	con.addConnectionListener(this);
	if(!con.chdir("/")) con.chdir("C:\\");
	//con.chdir(Settings.appHomeDir);
}

public RemoteDir(String path)
{
	type = "remote";
	path = path;
	con = new FilesystemConnection();
	con.addConnectionListener(this);
	con.chdir(path);
}

    public void gui_init()
    {
	setLayout(new BorderLayout());

        currDirPanel = new JPanel();
	buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	buttonPanel.left = false;

        deleteButton = new HImageButton(Settings.deleteImage,deleteString,"Delete  selected",this);
        deleteButton.setToolTipText("Delete selected");

        mkdirButton = new HImageButton(Settings.mkdirImage,mkdirString,"Create a new directory",this);
        mkdirButton.setToolTipText("Create directory");

        refreshButton = new HImageButton(Settings.refreshImage,refreshString,"Refresh current directory",this);
        refreshButton.setToolTipText("Refresh directory");

        cdButton = new HImageButton(Settings.cdImage,cdString,"Change directory",this);
        cdButton.setToolTipText("Change directory");

        cmdButton = new HImageButton(Settings.cmdImage,cmdString,"Execute remote command",this);
	cmdButton.setToolTipText("Execute remote command");

        downloadButton = new HImageButton(Settings.downloadImage,downloadString,"Download selected",this);
        downloadButton.setToolTipText("Download selected");

        //openButton = new HImageButton(Settings.openImage,openString,"Connect to server",this);
        //openButton.setToolTipText("Connect");

	setLabel();
        label.setSize(getSize().width-10, 24);
        currDirPanel.add(label);
        currDirPanel.setSize(getSize().width-10, 32);
        label.setSize(getSize().width-20, 24);

	p.setLayout(new BorderLayout());
	p.add("North", currDirPanel);

        buttonPanel.add(downloadButton);

	buttonPanel.add(new JLabel("               "));

	//buttonPanel.add(openButton);
	//buttonPanel.add(new JLabel("   "));
	buttonPanel.add(refreshButton);
	buttonPanel.add(mkdirButton);
	buttonPanel.add(cdButton);
	buttonPanel.add(deleteButton);
	buttonPanel.add(new JLabel(" "));

        buttonPanel.add(cmdButton);
        buttonPanel.add(list);
        buttonPanel.add(transferType);
	buttonPanel.add(new JLabel(" "));

        buttonPanel.setVisible(true);

        buttonPanel.setSize(getSize().width-10, 32);
	p.add("West", buttonPanel);
	add("North", p);

        //setDirList(true);
        jlm=new DefaultListModel();
        jl=new JList(jlm);
        jl.setCellRenderer(new DirCellRenderer());
        jl.setVisibleRowCount(Settings.visibleFileRows);
        jl.addListSelectionListener(this);

        // add this becaus we need to fetch only doubleclicks
        MouseListener mouseListener = new MouseAdapter()
                                      {
                                          public void mouseClicked(MouseEvent e)
                                          {
					   if(JFtp.uiBlocked) return;

						//System.out.println("DirEntryListener::");
                                              if (e.getClickCount() == 2)
                                              {
                                                       //System.out.println("2xList selection: "+jl.getSelectedValue().toString());
                                                      int index = jl.getSelectedIndex()-1;

						      // mousewheel bugfix
						      if(index < -1) return;

						      String tgt=(String)jl.getSelectedValue().toString();

                                                      if (index < 0)
                                                      {
                                                          con.chdir(path+tgt);
                                                      }
						      else if(dirEntry == null || dirEntry.length < index || dirEntry[index] == null) return;
                                                      else if (dirEntry[index].isDirectory())
                                                      {
                                                          con.chdir(path+tgt);
                                                      } else if(dirEntry[index].isLink())
						      {
						      	  if(!con.chdir(path+tgt))
							  {
							  	blockedTransfer(index);
							  }
						      }
                                                      else
                                                      {
                                                          blockedTransfer(index);
                                                      }
                                                  }
                                          }
                                      };
        jl.addMouseListener(mouseListener);

        jsp = new JScrollPane(jl);
        jsp.setSize(getSize().width-20, getSize().height-72);
        add("Center", jsp);
        jsp.setVisible(true);
        setVisible(true);
    }

    public void setViewPort()
    {
    }

    private void setLabel()
    {
    	if(con instanceof FilesystemConnection) label.setText("Filesystem: "+cutPath(path));
	else if(con instanceof FtpConnection) label.setText("Ftp: "+cutPath(path));
	else label.setText(cutPath(path));
    }


    public void gui(boolean fakeInit)
    {
        if (firstGui)
        {
            gui_init();
            firstGui=false;
        }

	setLabel();

        if (!fakeInit)
        {
            setDirList(false);
        }

	invalidate();
	validate();
    }

    public void setDirList(boolean fakeInit)
    {
        jlm = new DefaultListModel();

        DirEntry dwn = new DirEntry("..",this);
        dwn.setDirectory();
        jlm.addElement(dwn);

        if(!fakeInit)
        {
            if(pathChanged)
            {
                pathChanged = false;
                DirLister dir = new DirLister(con);

                while(!dir.finished) LocalIO.pause(10);

                if(dir.isOk())
                {
                    length = dir.getLength();
                    dirEntry = new DirEntry[length];
                    files = dir.list();
                    String fSize[] = dir.sList();
		   int perms[] = dir.getPermissions();

		    // --------- sorting aphabetically ------------
		    if(Settings.sortDir)
	            {

		      String[] tmpx = new String[length];

		      //if(fSize != null) System.out.println(":"+length+":"+fSize.length+":"+files.length);

		      int pLength = length;
		      if(perms != null) pLength = perms.length;

		      //System.out.println(files.length + ":" + fSize.length+":"+ pLength + ":"+ length);

		      if(fSize.length != files.length || pLength != files.length || length != files.length) System.out.println("Sort mismatch - hopefully ignoring it...");

		      for(int x=0; x<length; x++)
                      {
			if(perms != null) tmpx[x] = files[x] + "@@@" + fSize[x] + "@@@" + perms[x];
			else  tmpx[x] = files[x] + "@@@" + fSize[x];
		      }
		      LocalIO.sortStrings(tmpx);

                      for(int y=0; y<length; y++)
                      {
			files[y] = tmpx[y].substring(0,tmpx[y].indexOf("@@@"));

			String tmp = tmpx[y].substring(tmpx[y].indexOf("@@@")+3);
			if(tmp.indexOf("@@@") > 0) fSize[y] =  tmp.substring(0,tmp.lastIndexOf("@@@"));
			else fSize[y] = tmp;

			if(perms != null) perms[y] = Integer.parseInt(tmpx[y].substring(tmpx[y].lastIndexOf("@@@")+3));
		      }
		    }
		    // ----------- end sorting --------------------

                    for(int i=0; i<length; i++)
                    {
                        //System.out.println(files[i]);
			if(files == null || files[i] == null)
			{
				//System.out.println("Critical error, files or files[i] is null!\nPlease report when and how this happened...");
				System.out.println("skipping setDirList, files or files[i] is null!");
				return;
				//System.exit(0);
			}

			dirEntry[i] = new DirEntry(files[i],this);
			if(dirEntry[i] == null) { System.out.println("\nskipping setDirList, dirEntry[i] is null!"); return; }
			if(dirEntry[i].file == null) { System.out.println("\nskipping setDirList, dirEntry[i].file is null!"); return; }

			if(perms != null) dirEntry[i].setPermission(perms[i]);

                            dirEntry[i].setFileSize(Long.parseLong(fSize[i]));

                            if(dirEntry[i].file.endsWith("/"))
                            {
                                dirEntry[i].setDirectory();
                            }
                            else
                            {
                                dirEntry[i].setFile();
                            }
                            if(dirEntry[i].file.endsWith("@"))
                            {
                                dirEntry[i].setLink();
                            }

			    jlm.addElement(dirEntry[i]);
                    }
                }
                else
                {
                    Log.debug("Not a directory: " + path);
                }
            }
            //System.out.println("length: "+dirEntry.length);
        }
        jl.setModel(jlm);

	//System.out.print("x");
    	    //new Exception().printStackTrace();
	    //System.out.println("\n\n\n");
    }

    public void actionPerformed(ActionEvent e)
    {
     if(JFtp.uiBlocked) return;

        if(e.getActionCommand().equals("rm"))
        {
	   lock(false);

            for(int i=0; i<length;i++)
            {
                if(dirEntry[i].selected)
                {
	    		con.removeFileOrDir(dirEntry[i].file);
                }
            }

	    unlock(false);
	    fresh();
        }
        else if(e.getActionCommand().equals("mkdir"))
        {
            Creator c = new Creator("Create:",con);
	    fresh();
        }
        else if(e.getActionCommand().equals("cmd"))
        {
	    if(!(con instanceof FtpConnection))
	    {
	    	Log.debug("This feature is for ftp only.");
		return;
	    }

            RemoteCommand rc = new RemoteCommand();
	    fresh();
        }
        else if(e.getActionCommand().equals("cd"))
        {
                PathChanger pthc = new PathChanger("remote");
		fresh();
        }
        else if(e.getActionCommand().equals("fresh"))
        {
            fresh();
        }
        else if(e.getActionCommand().equals("->"))
        {
	 blockedTransfer(-2);
        }
        else if(e.getActionCommand().equals("<-"))
        {
	 blockedTransfer(-2);
        }
       else if(e.getActionCommand().equals("list"))
        {
            try
            {
                java.net.URL url = new java.io.File(Settings.ls_out).toURL();
                Displayer d = new Displayer(url);
            }
            catch(java.net.MalformedURLException ex)
            {
                ex.printStackTrace();
            }
        }
	else if(e.getActionCommand().equals("type") && (!JFtp.uiBlocked))
	{
	 if(!(con instanceof FtpConnection))
	 {
	 	Log.debug("You can only set the transfer type for ftp connections.");
	 	return;
	}

	 FtpConnection c = (FtpConnection) con;
	 String t = c.getTypeNow();
	 boolean ret = false;

	 if(t.equals(FtpConnection.ASCII))
	 {
	 	ret = c.type(FtpConnection.BINARY);
	 }
	 else if(t.equals(FtpConnection.BINARY))
	 {
	 	ret = c.type(FtpConnection.EBCDIC);
	 }

	 if(t.equals(FtpConnection.EBCDIC) || (!ret && !t.equals(FtpConnection.L8)))
	 {
	 	ret = c.type(FtpConnection.L8);
	 }

	 if(!ret)
	 {
	 	c.type(FtpConnection.ASCII);
		Log.debug("Warning: type should be \"I\" if you want to transfer binary files!");
	 }

	 Log.debug("Type is now " + c.getTypeNow());
	}
/*
        else if(e.getActionCommand().equals("open"))
        {
	    JFtp.safeDisconnect();
	    HostChooser hc = new HostChooser();
            hc.toFront();
	    hc.setModal(true);
            hc.update();
        }
*/
    }


    public synchronized void blockedTransfer(int index)
    {
	 tmpindex = index;

	 Runnable r = new Runnable()
	 {
	  public void run()
	  {
	        boolean block = !Settings.getEnableMultiThreading();
		if(!(con instanceof FtpConnection)) block = true;

	    	if(block) lock(false);

		transfer(tmpindex);

            	if(block)
		{
		 JFtp.localDir.fresh();
		 unlock(false);
		}
	  }
	 };

	 Thread t = new Thread(r);
	 t.start();
    }

    public void lock(boolean first)
    {
    	JFtp.uiBlocked = true;
	jl.setEnabled(false);
	if(!first) JFtp.localDir.lock(true);
    }

    public void unlock(boolean first)
    {
    	JFtp.uiBlocked = false;
	jl.setEnabled(true);
	if(!first) JFtp.localDir.unlock(true);
    }

    public void fresh()
    {
            con.chdir(path);
    }

    /** this manages the selections */
    public void valueChanged(ListSelectionEvent e)
    {
        if (e.getValueIsAdjusting() == false)
        {
	    							  //  ui refresh bugfix
	    int index = jl.getSelectedIndex()-1;
            if (index < 0 || dirEntry == null || dirEntry.length < index || dirEntry[index] == null)
            {
	    	return;
            }
            else
            {
                    String tgt=(String)jl.getSelectedValue().toString();

		    //if (index < 0)
                    //{
                        //con.chdir(path+tgt);
                    //}
                    for (int i=0; i<dirEntry.length; i++)
                    {
                        dirEntry[i].setSelected(jl.isSelectedIndex(i+1));
                    }
            }
        }
    }

    public void updateProgress(String file, String type, long bytes)
    {
    	if(dList == null || dirEntry == null) return;

	boolean flag = false;

	if(file.endsWith("/") && file.length() > 1)
	{
		flag = true;
		file = file.substring(0,file.lastIndexOf("/"));
	}

    	file = file.substring(file.lastIndexOf("/")+1);
	if(flag) file = file + "/";
	long s = 0;

	if(dummy.containsKey(file))
	{
		s = ((Long) dummy.get(file)).longValue();
	}
	else
	{
	 for(int i=0; i<dirEntry.length; i++)
	 {
		if(dirEntry[i] == null) continue;
		if(dirEntry[i].toString().equals(file))
		{
		 s = dirEntry[i].getRawSize();
		 dummy.put(file, new Long(s));
		 break;
		}
	 }
	 
	 if(s == 0)
	 {
	 	File f = new File(JFtp.localDir.getPath()+file);
		if(f.exists()) s = f.length();
	 }
	}

    	dList.updateList(file,type,bytes,s);
    }

    public void connectionInitialized(BasicConnection con) {}

    public void connectionFailed(BasicConnection con, String reason)
    {
    	if(reason.equals(FtpConnection.OFFLINE) && Settings.reconnect) return;

    	//this.con = con;
	HFrame h = new HFrame();
	h.getContentPane().setLayout(new BorderLayout(10,10));
	h.setTitle("Connection failed!");
	h.setLocation(150,200);
	JTextArea text = new JTextArea();
	h.getContentPane().add("Center",text);
	text.setText(" ---------------- Output -----------------\n" + JFtp.log.getText());
	JFtp.log.setText("");
	text.setEditable(false);
	h.pack();
	h.show();
    }

    public void updateRemoteDirectory(BasicConnection c)
    {
    	if(con == null) return;

    	if(c != con && !c.hasUploaded && con instanceof FtpConnection)
	{
		//System.out.println("Skipping connection: " + con.getCachedPWD() + " : " + JFtp.remoteDir.path);
		return;
	}

	if(con instanceof FtpConnection) path = ((FtpConnection)con).getCachedPWD();
	else if(con instanceof SmbConnection && !path.startsWith("smb://")) path = c.getPWD();
	else path = con.getPWD();

	//System.out.println("path: "+path +":"+ con +":" +con.getPWD() +":"+c+":" +c.getPWD());
	if(c != null && (c instanceof FtpConnection))
	{
	 FtpConnection con = (FtpConnection) c;

	String tmp = con.getCachedPWD();
	SaveSet s = new SaveSet(Settings.login_def,
                                        con.getHost(),
                                        con.getUsername(),
                                        con.getPassword(),
                                        Integer.toString(con.getPort()),
                                        tmp,
                                        con.getLocalPath()
                                       );
	}
	else if(c != null && (c instanceof FilesystemConnection))
	{
		JFtp.localDir.getCon().setLocalPath(path);
	}

        pathChanged = true;
        gui(false);
    }

    /** Transfers all selected files */
    public synchronized void transfer()
    {
      boolean bFileSelected[] = new boolean[dirEntry.length + 1];
      DirEntry cacheEntry[] = new DirEntry[dirEntry.length];
      System.arraycopy(dirEntry,0,cacheEntry,0,cacheEntry.length);

      for (int i = 0; i < dirEntry.length; i++)
      {
        bFileSelected[i] = cacheEntry[i].selected;
	if(!cacheEntry[i].equals(dirEntry[i])) System.out.println("mismatch");
      }

      for (int i = 0; i < cacheEntry.length; i++)
      {

        if (bFileSelected[i])
	{
		startTransfer(cacheEntry[i]);
        }
      }

    }

 public void startTransfer(DirEntry entry)
 {
  if(JFtp.remoteDir.getCon() instanceof FtpConnection)
  {
	int status = checkForExistingFile(entry);

	if(status >= 0)
	{
			if(entry.getRawSize() < Settings.smallSize && !entry.isDirectory()) con.download(entry.file);
			else con.handleDownload(path+entry.file);
	}
 }
 else if(con instanceof FilesystemConnection)
  {
  	con.download(path+entry.file);
	JFtp.localDir.actionPerformed(con, "");
  }
  else
  {
  	con.download(entry.file);
	JFtp.localDir.actionPerformed(con, "");
  }
}

    /** Transfers single file, or all selected files if index is -1 */
    public void transfer(int i)
    {
    	if(i == -2)
	{
		transfer();
		return;
	}
	else if(dirEntry[i].selected)
        {
		startTransfer(dirEntry[i]);
        }
    }

private int checkForExistingFile(DirEntry dirEntry)
{
	File f = new File(JFtp.localDir.getPath() + dirEntry.file);

	if(f.exists() && Settings.enableResuming && Settings.askToResume) {
		ResumeDialog r = new ResumeDialog(dirEntry); // ResumeDialog handels the rest
		return -1;
	}

	return 1;
}

public void actionFinished(BasicConnection c) {
	JFtp.localDir.actionPerformed(c, "LOWFRESH");
        fresh();
}

public void actionPerformed(Object target, String msg)
{
	if(msg.equals(type))
	{
		gui(true);
		//updateRemoteDirectory(con);
	}
	else if(msg.equals("FRESH")) fresh();
}

   String cutPath(String s)
    {
        int maxlabel = 64;
        if(s.length() > maxlabel)
        {
            while(true)
            {
                s = StringUtils.cutAfter(s,'/');
                if(s.length() < 16)
                {
                    StringBuffer sb = new StringBuffer(s);
                    sb.insert(0,".../");
                    return sb.toString();
                }
            }
        }
        return s;
    }

}
