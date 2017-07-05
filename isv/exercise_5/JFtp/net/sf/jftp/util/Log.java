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
package net.sf.jftp.util;

public class Log
{
    private static Logger logger = new SystemLogger();
    private static Log log = new Log();
    private static StringBuffer cache = new StringBuffer();

    private Log ()
    {
    }

    public static void setLogger(Logger logger)
    {
        Log.logger = logger;
    }

    public static void debug(String msg)
    {
    	//System.out.println(msg);
        logger.debug(msg);
	cache.append(msg+"\n");
    }

    public static void debugRaw(String msg)
    {
        logger.debugRaw(msg);
	cache.append(msg);
    }


    public static String getCache()
    {
    	return cache.toString();
    }
    
    public static void clearCache()
    {
    	cache = new StringBuffer();
    }
}
