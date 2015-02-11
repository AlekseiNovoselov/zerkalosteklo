package main;

import accountService.AccountServiceImpl;
import base.AccountService;
import base.SearchService;
import editor.EditorService;
import editor.EditorServiceImpl;
import frontEnd.EditorServlet;
import frontEnd.SearchServlet;
import messageSystem.MessageSystem;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resourse.ResourceFactory;
import resourse.StartPort;
import search.SearchServiceImpl;

import javax.servlet.http.HttpServlet;

public class Main {
    public static void main(String[] args) throws Exception {

        StartPort startPort = (StartPort) ResourceFactory.instance().get("./data/startPort.xml");
        if (startPort == null) {
            System.out.append("Read xml Error");
        }

        Integer port = startPort.getPort();
        System.out.append("Starting at port: ").append(port.toString()).append('\n');

        Server server = new Server(port);

        final MessageSystem messageSystem = new MessageSystem();

        AccountService service = new AccountServiceImpl(messageSystem);
        SearchService search = new SearchServiceImpl(messageSystem);

        EditorService editorService = new EditorServiceImpl();
        HttpServlet editorServlet = new EditorServlet(editorService);
        HttpServlet searchServlet = new frontEnd.SearchServlet(service, search);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(editorServlet), "/cropImage");
        context.addServlet(new ServletHolder(searchServlet), "/search");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        server.setHandler(handlers);
        server.start();
        server.join();
    }
}