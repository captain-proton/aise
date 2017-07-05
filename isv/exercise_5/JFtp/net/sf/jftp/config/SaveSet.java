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
package net.sf.jftp.config;

import java.io.*;

public class SaveSet
{
    private PrintStream out = null;

    public SaveSet(String file, String host, String user, String pass, String name)
    {
        try
        {
        	FileOutputStream fos;
            out = new PrintStream((fos = new FileOutputStream(file)));
            out.println(host);
            out.println(user);

            if(Settings.cachePass)
                out.println(pass);
            else
                out.println("");
            out.println(name);
            fos.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public SaveSet(String file, String host, String user, String pass,
                   String port, String cwd, String lcwd)
    {
        try
        {
            out = new PrintStream(new FileOutputStream(file));
            out.println(host);
            out.println(user);

            if(Settings.cachePass)
                out.println(pass);
            else
                out.println("");

            out.println(port);
            out.println(cwd);
            out.println(lcwd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
