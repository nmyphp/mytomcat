package com.github.nmyphp;

import com.github.nmyphp.mytomcat.Dispatcher;
import com.github.nmyphp.mytomcat.HttpRequest;
import com.github.nmyphp.mytomcat.HttpResponse;
import com.github.nmyphp.mytomcat.StatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * Tomcat入口.
 *
 * @author nmyphp
 */
@Slf4j
public class Tomcat {

    private Integer port = 8080;

    public static void main(String[] args) throws IOException {
        new Tomcat().start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        Dispatcher dispatcher = new Dispatcher();
        System.out.println("Tomcat is started! [" + port + "]");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    HttpRequest req = new HttpRequest(inputStream);
                    HttpResponse res = new HttpResponse(outputStream, req.getContentType(), req.getCharsetName());
                    res.setStatusCode(StatusCode.OK);
                    dispatcher.dispatch(req, res);
                } catch (Exception ex) {
                    log.error("", ex);
                } finally {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
