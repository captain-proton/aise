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

public class LoadSet
{
//    private BufferedInputStream in = null;
//    private String result[] = new String[6];

    public static String[] loadSet(String file)
    {
        try
        {
            BufferedReader breader = new BufferedReader(new FileReader(file));
            String[] result = new String[6];
            result[0] = breader.readLine();
            result[1] = breader.readLine();
            result[2] = breader.readLine();
            result[3] = breader.readLine();
            result[4] = breader.readLine();
            result[5] = breader.readLine();

            return result;
        }
        catch(Exception ex)
        {
            // don't need this, occurs if first run
            //Log.debug(ex.toString() + "@LoadSet::loadSet()");
        }

        return null;
    }
}
