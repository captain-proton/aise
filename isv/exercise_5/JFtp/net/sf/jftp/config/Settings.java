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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Settings
{
    private static Properties p = new Properties();
    public static final String propertyFilename = ".jftp/jftp.properties".replace('/', File.separatorChar);
    static
    {
        try {
        	p.load(new FileInputStream(propertyFilename));
        } catch (Exception e) {
		System.out.println("no property file loaded, using defaults... ("+e+")");
	}
    }

    public static Object setProperty(String key, String value)
    {
        return p.setProperty(key, value);
    }

    public static Object setProperty(String key, int value)
    {
        return p.setProperty(key, Integer.toString(value));
    }

    public static Object setProperty(String key, boolean value)
    {
    	String val = "false";
	if(value) val = "true";
        return p.setProperty(key, val);
    }

    public static void save()
    {
        try {
		new File(".jftp").mkdir();
        	p.store(new FileOutputStream(propertyFilename), "jftp.properties");
        } catch (Exception e) {
		System.out.println("Cannot save properties...");
		//e.printStackTrace();
	}
    }

    public static final String defaultWidth = "780";
    public static final String defaultHeight = "540";
    public static final String defaultX = "20";
    public static final String defaultY = "20";
    
    public static int maxConnections = 3;

    public static boolean enableResuming = false; // overridden by JFtp
    public static boolean askToResume = true;

    public static boolean reconnect = true;

    public static int smallSize = 100000;
    public static int smallSizeUp = 50000;

    public static boolean autoUpdate =false;

    public static  boolean shortProgress = true;

    public static int getMaxConnections()
    {
    	return maxConnections;
    }

    private static String defaultFtpPasvMode = "true";
    
    private static String enableMultiThreading = "true";
    private static String noUploadMultiThreading = "false";

    public static boolean getEnableMultiThreading()
    {
     	String what = p.getProperty("jftp.enableMultiThreading", enableMultiThreading);
	if(what.trim().equals("false")) return false;
	else return true;
    }

    public static boolean getNoUploadMultiThreading()
    {
     	String what = p.getProperty("jftp.noUploadMultiThreading", noUploadMultiThreading);
	if(what.trim().equals("false")) return false;
	else return true;
    }

    public static boolean getFtpPasvMode()
    {
     	String what = p.getProperty("jftp.ftpPasvMode", defaultFtpPasvMode);
	if(what.trim().equals("false")) return false;
	else return true;
    }

    public static boolean getUseDefaultDir()
    {
     	String what = p.getProperty("jftp.useDefaultDir", "true");
	if(what.trim().equals("false")) return false;
	else return true;
    }


    public static java.awt.Dimension getWindowSize()
    {
        int width = Integer.parseInt(p.getProperty("jftp.window.width", defaultWidth));
        int height = Integer.parseInt(p.getProperty("jftp.window.height", defaultHeight));
        return new java.awt.Dimension(width, height);
    }

    public static java.awt.Point getWindowLocation()
    {
        int x = Integer.parseInt(p.getProperty("jftp.window.x", defaultX));
        int y = Integer.parseInt(p.getProperty("jftp.window.y", defaultY));
	return new java.awt.Point(x, y);
    }
    
    public static int getSocketTimeout()
    {
    	return connectionTimeout;
    }
    

    // caches the passes for connections
    public static boolean cachePass = true;;

    // hides some messages like MODE, Type etc.
    public static boolean hideStatus = false;
 
    // for DataConnection - lower means less buffer, more updates in the downloadmanager
    // i recommend to use values greater than 2048 bytes
    public static int bufferSize = 4096;

    // sends NOOPs to ensure that buffers are empty
    public static boolean safeMode = false;

    // title of the app
    public static final String title = "JFTP";

    public static int refreshDelay = 250;

    public static boolean useDefaultDir = true;

    // may the windows be resized?
    public static boolean resize = true;

    public static boolean showFileSize = true;

    public static boolean sortDir = true;

    public static final int visibleFileRows = 15;

    public static int scrollSpeed = 9;

    public static int numFiles = 9;

    public static final int connectionTimeout = 30000;

    public static int statusMessageAfterMillis = 1000;

    public static final String defaultDir = "<default>";

    public static final String defaultWorkDir = System.getProperty("user.home");
    public static final String userHomeDir = System.getProperty("user.home");
    public static final String appHomeDir = userHomeDir+"/.jftp/".replace('/', File.separatorChar);

    public static final String greeting = "Have a lot of fun...";

    public static final String ls_out_name = ".ls_out";
    public static final String ls_out = appHomeDir+ls_out_name.replace('/', File.separatorChar);
    public static final String sortls_out = appHomeDir+".sortls_out".replace('/', File.separatorChar);
    public static final String sortsize_out = appHomeDir+".sortsize_out".replace('/', File.separatorChar);
    //public static final String ls_out_remote = appHomeDir+".ls_out".replace('/', File.separatorChar);
    //public static final String sortls_out_remote = appHomeDir+".sortls_out".replace('/', File.separatorChar);
    //public static final String sortsize_out_remote = appHomeDir+".sortsize".replace('/', File.separatorChar);
    public static final String permissions_out = appHomeDir+".permissions_out".replace('/', File.separatorChar);

    public static final String login_def = appHomeDir+".login_default".replace('/', File.separatorChar);
    public static final String login = appHomeDir+".login".replace('/', File.separatorChar);

    public static final String readme = "docs/readme";
    public static final String changelog = "docs/CHANGELOG";
    public static final String todo = "docs/TODO";

    public static String iconImage = "images/org/javalobby/icons/20x20/ComputerIn.gif";
    public static String hostImage = "images/org/javalobby/icons/20x20/ComputerIn.gif";
    public static String closeImage = "images/org/javalobby/icons/20x20/Error.gif";
    public static String infoImage = "images/org/javalobby/icons/20x20/Inform.gif";
    public static String listImage = "images/org/javalobby/icons/20x20/List.gif";

    public static String deleteImage = "images/org/javalobby/icons/16x16/DeleteDocument.gif";
    public static String rmdirImage = "images/org/javalobby/icons/16x16/DeleteFolder.gif";
    public static String mkdirImage = "images/org/javalobby/icons/16x16/NewFolder.gif";
    public static String refreshImage = "images/org/javalobby/icons/16x16/Undo.gif";
    public static String cdImage = "images/org/javalobby/icons/16x16/Open.gif";
    public static String cmdImage = "images/org/javalobby/icons/16x16/ExecuteProject.gif";

    public static String downloadImage = "images/org/javalobby/icons/16x16/Left.gif";
    public static String uploadImage = "images/org/javalobby/icons/16x16/Right.gif";

    public static String fileImage = "images/org/javalobby/icons/16x16/Document.gif";
    public static String dirImage = "images/org/javalobby/icons/16x16/Folder.gif";
    public static String codeFileImage = "images/org/javalobby/icons/16x16/List.gif";
    public static String textFileImage = "images/org/javalobby/icons/16x16/DocumentDraw.gif";
    public static String execFileImage = "images/org/javalobby/icons/16x16/ExecuteProject.gif";
    public static String audioFileImage = "images/org/javalobby/icons/16x16/cd.gif";
    public static String videoFileImage = "images/org/javalobby/icons/16x16/CameraFlash.gif";
    public static String htmlFileImage = "images/org/javalobby/icons/16x16/World2.gif";
    public static String zipFileImage = "images/org/javalobby/icons/16x16/DataStore.gif";
    public static String imageFileImage = "images/org/javalobby/icons/16x16/Camera.gif";
    public static String presentationFileImage = "images/org/javalobby/icons/16x16/DocumentDiagram.gif";
    public static String spreadsheetFileImage = "images/org/javalobby/icons/16x16/DatePicker.gif";
    public static String bookFileImage = "images/org/javalobby/icons/16x16/Book.gif";

    public static String copyImage = "images/org/javalobby/icons/16x16/Copy.gif";
    public static String openImage = "images/org/javalobby/icons/16x16/World2.gif";

    public static String linkImage = "images/org/javalobby/icons/16x16/Right.gif";
    public static String typeImage = "images/org/javalobby/icons/20x20/Type.gif";
    public static String clearImage = "images/org/javalobby/icons/16x16/Undo.gif";
    public static String resumeImage = "images/org/javalobby/icons/16x16/GreenCircle.gif";
    public static String pauseImage = "images/org/javalobby/icons/16x16/RedCircle.gif";

}
