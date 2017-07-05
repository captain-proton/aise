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

public class LocalDir extends DirPanel implements ListSelectionListener, ActionListener, ConnectionListener
{
    HImageButton deleteButton;
    HImageButton mkdirButton;
    HImageButton cmdButton;
    HImageButton refreshButton;
    HImageButton cdButton;
    HImageButton uploadButton;
    HImageButton zipButton;
    HImageButton cpButton;
    HImageButton rnButton;

    static final String deleteString = "rm";
    static final String mkdirString = "mkdir";
    static final String refreshString = "fresh";
    static final String cdString = "cd";
    static final String cmdString = "cmd";
    static final String downloadString = "<-";
    static final String uploadString = "->";
    static final String zipString = "zip";
    static final String cpString = "cp";
    static final String rnString = "rn";

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

public LocalDir()
{
	type = "local";
	con = new FilesystemConnection();
	con.addConnectionListener(this);
}

public LocalDir(String path)
{
	type = "local";
	path = path;
	con = new FilesystemConnection();
	con.addConnectionListener(this);
	con.chdir(path);
	//gui(false);
}

   public void gui_init()
    {
	setLayout(new BorderLayout());

	buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        deleteButton = new HImageButton(Settings.deleteImage,deleteString,"Delete selected",this);
        deleteButton.setToolTipText("Delete selected");

        mkdirButton = new HImageButton(Settings.mkdirImage,mkdirString,"Create a new directory",this);
        mkdirButton.setToolTipText("Create directory");

        refreshButton = new HImageButton(Settings.refreshImage,refreshString,"Refresh current directory",this);
        refreshButton.setToolTipText("Refresh directory");

        cdButton = new HImageButton(Settings.cdImage,cdString,"Change directory",this);
        cdButton.setToolTipText("Change directory");

        uploadButton = new HImageButton(Settings.uploadImage,uploadString,"Upload selected",this);
        uploadButton.setToolTipText("Upload selected");

        zipButton = new HImageButton(Settings.zipFileImage,zipString,"Add selected to new zip file",this);
        zipButton.setToolTipText("Create zip");

        cpButton = new HImageButton(Settings.copyImage,cpString,"Copy selected files to another local dir",this);
        cpButton.setToolTipText("Local copy selected");

        rnButton = new HImageButton(Settings.textFileImage,rnString,"Rename selected file or directory",this);
        rnButton.setToolTipText("Rename selected");

	label.setText("Filesystem: "+cutPath(path));
        label.setSize(getSize().width-10, 24);
        currDirPanel.add(label);
        currDirPanel.setSize(getSize().width-10, 32);
        label.setSize(getSize().width-20, 24);

	p.setLayout(new BorderLayout());
	p.add("North", currDirPanel);

	buttonPanel.add(new JLabel(" "));

        buttonPanel.add(refreshButton);

	buttonPanel.add(cpButton);
	buttonPanel.add(rnButton);
	buttonPanel.add(mkdirButton);

        buttonPanel.add(cdButton);
	buttonPanel.add(deleteButton);
	buttonPanel.add(new JLabel(" "));

	buttonPanel.add(zipButton);
	buttonPanel.add(new JLabel("                "));

        buttonPanel.add(uploadButton);

        buttonPanel.setVisible(true);

        buttonPanel.setSize(getSize().width-10, 32);
	p.add("East", buttonPanel);
	add("North", p);

        setDirList(true);
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

						      // mousewheel bugfix, ui refresh bugfix
						      if(index < -1) return;

                                                      String tgt=(String)jl.getSelectedValue().toString();
                                                      //System.out.println("List selection: "+index);
                                                      if (index < 0)
                                                      {
                                                          setPath(path+tgt);
                                                      }
						      else if(dirEntry == null || dirEntry.length < index || dirEntry[index] == null) return;
                                                      else if (dirEntry[index].isDirectory())
                                                      {
                                                          setPath(path+tgt);
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

    public void setViewPort() {}


    public void gui(boolean fakeInit)
    {
        if (firstGui)
        {
            gui_init();
            firstGui=false;
        }

	label.setText("Filesystem: "+cutPath(path));

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
			fSize[y] =  tmp.substring(0,tmp.lastIndexOf("@@@"));
			if(perms != null) perms[y] = Integer.parseInt(tmpx[y].substring(tmpx[y].lastIndexOf("@@@")+3));
		      }
		    }
		    // ----------- end sorting --------------------

                    for(int i=0; i<length; i++)
                    {
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
    }

    // 20011213:rb - Added logic for MSDOS style root dir
    public boolean setPath(String p)
    {
    		if(JFtp.remoteDir == null) return false;

	       BasicConnection c = JFtp.remoteDir.getCon();

		if(c != null && (c instanceof FtpConnection))
		{
		 FtpConnection con = (FtpConnection) c;
		 //con.setLocalPath(path);

                 SaveSet s = new SaveSet(Settings.login_def,
                                        con.getHost(),
                                        con.getUsername(),
                                        con.getPassword(),
                                        Integer.toString(con.getPort()),
                                        con.getCachedPWD(),
                                        con.getLocalPath()
                                       );
		}

	    if(con.chdirNoRefresh(p))
	    {
	      path = con.getPWD();
	      JFtp.remoteDir.getCon().setLocalPath(path);
              pathChanged = true;
              gui(false);
	      return true;
	    }

	//Log.debug("CWD (local) : " + p);
        return false;
    }

    private String getPathFromDialog()
    {
     		JFileChooser chooser = new JFileChooser(path);
		chooser.setDialogTitle("Choose directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    		int returnVal = chooser.showOpenDialog(this);
    		if(returnVal == JFileChooser.APPROVE_OPTION) {
		 File f = chooser.getSelectedFile();

		 return f.getPath();
		}

		return null;
    }

    public void actionPerformed(ActionEvent e)
    {
     if(JFtp.uiBlocked) return;

        if(e.getActionCommand().equals("rm"))
        {
            for(int i=0; i<length;i++)
            {
                if(dirEntry[i].selected)
                {
	    		con.removeFileOrDir(dirEntry[i].file);
		}
	    }
	    fresh();
        }
        else if(e.getActionCommand().equals("mkdir"))
        {
            Creator c = new Creator("Create:",con);
	    fresh();
        }
        else if(e.getActionCommand().equals("cmd"))
        {
            RemoteCommand rc = new RemoteCommand();
	    fresh();
        }
        else if(e.getActionCommand().equals("cd"))
        {
 		 String tmp = getPathFromDialog();
		 setPath(tmp);
        }
        else if(e.getActionCommand().equals("fresh"))
        {
            fresh();
        }
        else if(e.getActionCommand().equals("cp"))
        {
	    Object o[] = jl.getSelectedValues();
	    if(o == null) return;

	    String tmp = getPathFromDialog();
	    if(tmp == null) return;
	    if(!tmp.endsWith("/")) tmp = tmp + "/";

	    try
	    {
	    	copy(o, path, "", tmp);
		fresh();
		Log.debug("Copy finished...");
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
		Log.debug("Copy failed!");
	    }
        }
        else if(e.getActionCommand().equals("zip"))
        {
	 try
	 {
	    Object entry[] = jl.getSelectedValues();
	    if(entry == null) return;

	    String tmp[] = new String[entry.length];
	    for(int i=0; i<tmp.length; i++)
	    {
	    	tmp[i] = entry[i].toString();
	    }

	    NameChooser n = new NameChooser();
	    String name = n.text.getText();
            ZipFileCreator z = new ZipFileCreator(tmp, path, name);
	    fresh();
	 }
	 catch(Exception ex)
	 {
	 	ex.printStackTrace();
	 }
        }
        else if(e.getActionCommand().equals("->"))
        {
	 blockedTransfer(-2);
        }
        else if(e.getActionCommand().equals("<-"))
        {
	 blockedTransfer(-2);
        }
	else  if(e.getActionCommand().equals("rn"))
        {
		String target = jl.getSelectedValue().toString();
		if(target == null) return;

		Renamer r = new Renamer(target, path);
		fresh();
        }
    }

    private void copy(Object fRaw[], String path, String offset, String target) throws Exception
    {

	String files[] = new String[fRaw.length];

	for(int j=0; j<fRaw.length; j++)
	{
		files[j] = fRaw[j].toString();
	}

    	for(int i=0; i<files.length; i++)
	{
		File f = new File(path+offset +files[i]);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		byte buf[] = new byte[4096];

		if(f.exists() && !f.isDirectory())
		{
			 in = new BufferedInputStream(new FileInputStream(path+offset + files[i]));
			 out = new BufferedOutputStream(new FileOutputStream(target + offset + files[i]));
			int len = 0;

		 	while(in != null)
			 {
			 	len = in.read(buf);
				if(len== StreamTokenizer.TT_EOF) break;
				out.write(buf,0,len);
			 }

			out.flush();
		 	out.close();
		}
		else if(f.exists())
		{
			if(!files[i].endsWith("/")) files[i] = files[i] +"/";
			File f2 = new File(target + offset + files[i]);
			if(!f.exists()) f.mkdir();

			copy(f.list(), path, offset + files[i], target);
		}
	}
     }


    public synchronized void blockedTransfer(int index)
    {
	 tmpindex = index;

	 Runnable r = new Runnable()
	 {
	  public void run()
	  {     // --------------- local -------------------
	        boolean block = !Settings.getEnableMultiThreading();
		if(!(con instanceof FtpConnection)) block = true;

	    	if(block || Settings.getNoUploadMultiThreading()) lock(false);

            	transfer(tmpindex);

	    	if(block || Settings.getNoUploadMultiThreading()) unlock(false);
		//{
		 //JFtp.remoteDir.fresh();
		 //unlock(false);
		 //}
	  }
	 };

	 Thread t = new Thread(r);
	 t.start();
    }

    public void lock(boolean first)
    {
    	JFtp.uiBlocked = true;
	jl.setEnabled(false);
	if(!first) JFtp.remoteDir.lock(true);
    }

    public void unlock(boolean first)
    {
    	JFtp.uiBlocked = false;
	jl.setEnabled(true);
	if(!first) JFtp.remoteDir.unlock(true);
    }

    public void fresh()
    {
    	    //System.out.println(">>> fresh: "+path);
            setPath(path);
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
            {        // -------------------- local --------------------------
                    String tgt=(String)jl.getSelectedValue().toString();

                    for (int i=0; i<dirEntry.length; i++)
                    {
                        dirEntry[i].setSelected(jl.isSelectedIndex(i+1));
                    }
            }
        }
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
	if(entry.getRawSize() < Settings.smallSizeUp && !entry.isDirectory()) JFtp.remoteDir.getCon().upload(path+entry.file);
	else JFtp.remoteDir.getCon().handleUpload(path+entry.file);
  }
  else if(JFtp.remoteDir.getCon() instanceof FilesystemConnection)
  {
  	con.upload(path+entry.file);
	JFtp.remoteDir.actionPerformed(con, "FRESH");
  }
  else
  {
  	JFtp.remoteDir.getCon().upload(entry.file);
	JFtp.remoteDir.actionPerformed(con, "FRESH");
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


public void safeUpdate()
{
        long time = System.currentTimeMillis();

	if((time-oldtime) < 5000)
	{
		return;
	}

	oldtime = time;
	fresh();
}


public void actionPerformed(Object target, String msg)
{
	//System.out.print(msg);
	//fresh();
	safeUpdate();
}

public void updateProgress(String file, String type, long bytes) {}

public void connectionInitialized(BasicConnection con)
{
	//fresh();
}

public void actionFinished(BasicConnection con)
{
	fresh();
	//safeUpdate();
}

public void connectionFailed(BasicConnection con, String reason) {}

public void updateRemoteDirectory(BasicConnection con)
{
	fresh();
	//safeUpdate();
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
