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

public class StatusPanel extends HPanel implements ActionListener
{
    private HImageButton newcon = new HImageButton(Settings.hostImage,"newcon","New FTP Connection...",this);
    private HImageButton smbcon = new HImageButton(Settings.openImage,"smbcon","New SMB Connection...",this);

    public HImageButton close = new HImageButton(Settings.closeImage,"close","Close Connection and connect to Filesystem...",this);

    private StatusCanvas status = new StatusCanvas();
    private JFtp jftp;

    public StatusPanel(JFtp jftp)
    {
		this.jftp = jftp;
		setLayout(new FlowLayout(FlowLayout.LEFT));
		//setLayout(new GridLayout(1,5));

	//newcon.setBorder(true);
	//close.setBorder(true);
	//info.setBorder(true);

        add(newcon);
        newcon.setSize(24,24);

	add(smbcon);
        smbcon.setSize(24,24);

        add(close);
        close.setSize(24,24);


        add(status);

        validate();
        setFont(GUIDefaults.menuFont);
        setVisible(true);
    }

    public void status(String msg)
    {
        status.setText(msg);
    }

    public String getHost()
    {
        return status.getHost();
    }

    public void setHost(String host)
    {
        status.setHost(host);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("smbcon"))
        {
	    jftp.safeDisconnect();

	    SmbHostChooser hc = new SmbHostChooser();
            hc.toFront();
	    hc.setModal(true);
            hc.update();
        }
        else if(e.getActionCommand().equals("close"))
        {
		jftp.safeDisconnect();
		FilesystemConnection con = new FilesystemConnection();
		jftp.remoteDir.setCon(con);
		con.addConnectionListener((ConnectionListener)jftp.remoteDir);
		if(!con.chdir("/")) con.chdir("C:\\");
        }
        else if(e.getActionCommand().equals("newcon") && (!jftp.uiBlocked))
        {
	    jftp.safeDisconnect();

            // Switch windows
	   // jftp.mainFrame.setVisible(false);
            HostChooser hc = new HostChooser();
            hc.toFront();
	    hc.setModal(true);
            hc.update();
        }
    }


    // <newimg>public void paintComponent(Graphics g)
    // <newimg>{
    // <newimg>	int x = getSize().width;
    // <newimg>while(x > -100) {
    // <newimg>		g.drawImage(HImage.getImage(this, Settings.statusBackImage), x, 0, this);
    // <newimg>	x -= 100;
    // <newimg>}
    // <newimg>}


    public Insets getInsets()
    {
        Insets in = super.getInsets();
        return new Insets(in.top,in.left,in.bottom,in.right);
    }
}
