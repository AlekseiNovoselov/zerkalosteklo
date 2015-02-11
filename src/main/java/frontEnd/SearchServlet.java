package frontEnd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import base.AccountService;
import base.SearchService;
import database.ImageDataSet;
import org.json.*;
import search.SearchServiceError;
import search.SearchServiceResponse;

public class SearchServlet extends HttpServlet {
    private AccountService service;
    private SearchService search;
    public SearchServlet(AccountService service, SearchService search) {
        this.service = service;
        this.search = search;
    }
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in search Servlet");
        try {
            SearchServiceResponse resp = search.findImage(request.getParameter("q"));
            if ( resp.getStatus() ) {
                System.out.println("Status true");
                success(response, (ArrayList<ImageDataSet>)resp.getResponse());
            }
            else {
                error(response, (SearchServiceError)resp.getResponse());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void success(HttpServletResponse response, ArrayList<ImageDataSet> collection) throws IOException {
        JSONArray jsnArray = new JSONArray();
        for (ImageDataSet image : collection) {
            jsnArray.put(new JSONObject().put("name", image.getName()).put("album", image.getAlbum()));
        }
        System.out.println(jsnArray.toString());
        response.getWriter().println(jsnArray.toString());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }
    public void error(HttpServletResponse response, SearchServiceError error) throws IOException {
        JSONObject jsnObj = new JSONObject().put("message", error.getMessage());
        response.getWriter().print(jsnObj.toString());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do Post");
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
