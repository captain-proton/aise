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

import java.io.*;

public class StringUtils
{

    public static String removeStart(String str, String what)
    {
        if(str.startsWith(what))
        {
            int x = what.length();
            return str.substring(x);
        }
        else
            return str;
    }

    public static String cutAfter(String str, char c)
    {
        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i) == c)
            {
                //   System.out.println(str.substring(i+1));
                return str.substring(i+1);
            }
        }
        return str;
    }

    public static String contains(String[] tmp, String[] str)
    {
        for(int i=0; i<tmp.length; i++)
        {
            for(int j=0; j<str.length; j++)
            {
                if(tmp[i].startsWith(str[j]))
                {
                    return tmp[i];
                }
            }
        }
        return "";
    }

    public static boolean strstr(String tmp, char str)
    {
        for(int i=0; i<tmp.length(); i++)
        {
            if(tmp.charAt(i) == str)
            {
                return true;
            }
        }
        return false;
    }

    public static String cut(String tmp, String where)
    {
        StringBuffer ret = new StringBuffer();
        for(int i=0; i<tmp.length(); i++)
        {
            if(!string(tmp.charAt(i)).equals(where))
            {
                ret.append(string(tmp.charAt(i)));
            }
            if(string(tmp.charAt(i)).equals(where))
            {
                return ret.toString();
            }
        }
        return ret.toString();
    }

    public static String string(char c)
    {
        char[] buf = new char[1];
        buf[0] = c;
        return new String(buf);
    }


 public static String getFile(String file)
    {
	int x  = file.lastIndexOf("/");

	// unix
	if(x >= 0) file = file.substring(x+1);

	// windows
	x = file.lastIndexOf("\\");
	if(x >= 0) file = file.substring(x+1);

	// may work, but can test the other method better
	//int x  = file.lastIndexOf(File.separatorChar);
	//if(x >= 0) file = file.substring(x+1);

	//System.out.println(file);
	return file;
    }

    public static String getDir(String tmp)
    {
    	int x;

	while(true)
	{
		x = tmp.indexOf("/");
		if(x == tmp.length()-1|| x < 0) break;
		else tmp = tmp.substring(x+1);
	}

	while(true)
	{
		x = tmp.indexOf("\\");
		if(x == tmp.length()-1|| x < 0) break;
		else tmp = tmp.substring(x+1);
	}

	return tmp;
   }

    public static boolean isRelative(String file)
    {
    	// unix
    	if(file.startsWith("/")) return false;
	// windows
	if ((file.length() > 2) && (file.charAt(1) == ':'))  return false;
	//System.out.println("true: " + file);

	// default
	return true;
    }

    public static void main(String argv[])
    {
    String a1 = "E:\\programme\\test.html";
    String a2 = "programme\\test.html";
    String a3 = "test.html";
    String a4 = "/programme/test.html";
    String a5 = "programme/test.html";

    	System.out.println("getfile: " + getFile(a1) +" - false, " + isRelative(a1));
	System.out.println("getfile: " + getFile(a2) +" - true, " + isRelative(a2));
    	System.out.println("getfile: " + getFile(a3) +" - true, " + isRelative(a3));
	System.out.println("getfile: " + getFile(a4) +" - false, " + isRelative(a4));
    	System.out.println("getfile: " + getFile(a5) +" - true, " + isRelative(a5));
    }


}
