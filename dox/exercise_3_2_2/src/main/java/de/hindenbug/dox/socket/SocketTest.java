package de.hindenbug.dox.socket;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by nils on 15.05.17.
 */
public class SocketTest
{
    private static final Logger LOG = LoggerFactory.getLogger(SocketTest.class);

    public static void main(String[] args)
    {
        String host = "example.org";
        int port = 80;

        try
        {
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            send("GET / HTTP/1.1", out);
            send("Host: " + host, out);
            send("", out);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            List<String> response = IOUtils.readLines(in);
            response.forEach(LOG::info);

            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e)
        {
            LOG.error("unknown host " + host, e);
        } catch (IOException e)
        {
            LOG.error("io error", e);
        }
    }

    private static void send(String msg, Writer out) throws IOException
    {
        out.write(String.format("%s\r\n", msg));
    }
}
