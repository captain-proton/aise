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

import java.awt.*;
import javax.swing.*;

public class DirCanvas extends JPanel
{
    JLabel text = new JLabel(" ");

    public DirCanvas()
    {
	setLayout(new FlowLayout(FlowLayout.LEFT));
	add(text);
        setVisible(true);
    }

    public void setText(String msg)
    {
        text.setText(msg);
	validate();
    }
 
    public void paintComponent(Graphics g)
    {
    	g.setColor(GUIDefaults.back);
	g.fillRect(0,0,getSize().width,getSize().height);
	g.setColor(GUIDefaults.front);
	g.drawRect(0,0,getSize().width-1,getSize().height-1);
	g.drawRect(0,0,getSize().width-2,getSize().height-2);
    }

}
