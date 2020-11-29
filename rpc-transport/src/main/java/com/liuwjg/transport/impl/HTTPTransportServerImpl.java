package com.liuwjg.transport.impl;

import com.liuwjg.transport.RequestHandler;
import com.liuwjg.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class HTTPTransportServerImpl implements TransportServer {
    private RequestHandler handler;

    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        //创建servletContext
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);

        //创建servletHolder
        ServletHolder holder = new ServletHolder(new RequestServlet());
        contextHandler.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            log.info("client connected....");
            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();
            if (handler != null) {
                handler.onRequest(inputStream, outputStream);
            }
            outputStream.flush();
        }
    }
}
