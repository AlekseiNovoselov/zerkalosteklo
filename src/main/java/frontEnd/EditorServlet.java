package frontEnd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import base.EditorServiceResponse;
import editor.Image;
import editor.EditorService;
import org.json.JSONObject;

public class EditorServlet extends HttpServlet {

    private EditorService editorService;
    public EditorServlet (EditorService editor) {
        this.editorService = editor;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/#");
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        InputStream tmp = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(tmp));
        String jsonStr = br.readLine();
        System.out.println(jsonStr);
        try {
            JSONObject jsonObj= new JSONObject(jsonStr);
            Image image = new Image(jsonObj);
            EditorServiceResponse resp = editorService.cropImage(image);
            if (resp.getStatus()) {
                success(response);
            }
            else {
                error(response);
            }
        }
        catch (Exception e) {
            System.out.println("Exception in LoginServlet.doPost: " + e.getMessage());
            error(response);
        }
    }

    private void error(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    private void success(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
