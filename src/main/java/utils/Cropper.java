package utils;

/*
    Класс нарезания изображений различного размера для плагина justifyGallery
    http://miromannino.github.io/Justified-Gallery/
    http://ondras.zarovi.cz/sql/demo/  Load-> kubanzsk
*/

import base.DBService;
import base.UserProfile;
import database.DBServiceImpl;
import resourse.Admin;
import resourse.DataBase;
import resourse.ResourceFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

public class Cropper {
    public static void main(String[] args) throws Exception {

        DBService dbService;

        String album = "zivotnie";
        String baseFolder = "public_html/static/"+album;
        String endFolder = "public_html/upload/"+album;

        PrintWriter writer = new PrintWriter("public_html/upload/"+album+".txt", "UTF-8");

        DataBase dataBase = (DataBase) ResourceFactory.instance().get("./data/dataBase.xml");
        dbService = new DBServiceImpl(dataBase.getHost(), dataBase.getPort(), dataBase.getUser(), dataBase.getName(), dataBase.getPassword());
        Admin admin = (Admin)ResourceFactory.instance().get("./data/admin.xml");
        UserProfile user = new UserProfile(Long.parseLong(admin.getId()), admin.getName(), admin.getEmail(), admin.getPassword(), Long.parseLong(admin.getScore()));
        if (!dbService.isUserExistsByEmail(admin.getEmail())) {
            dbService.addUser(user);
        }
        if (!dbService.isAlbumExistsByName(album)) {
            dbService.addAlbum(album, 0);
        }
        else System.out.println("This album already exists");

        // следует вынести в ресурсы
        int[] size = new int[6];
        size[0] = 100;
        size[1] = 240;
        size[2] = 320;
        size[3] = 500;
        size[4] = 640;
        size[5] = 1024;

        // также следует вынести в ресурсы
        String[] suffix = new String[6];
        suffix[0] = "_t";
        suffix[1] = "_m";
        suffix[2] = "_n";
        suffix[3] = "";
        suffix[4] = "_z";
        suffix[5] = "_b";

        File dir = new File(endFolder);
        dir.mkdir();

        File directory = new File(baseFolder);
        File[] fList = directory.listFiles();
        /*for (int i = 0 ; i < 6; i++ ) {
            for (File file : fList) {
                System.out.println(file.getName());
                Image orig = ImageIO.read(new File(file.getPath()));
                System.out.println("height " + orig.getHeight(null) + "   width " + orig.getWidth(null));
                Integer orig_height = orig.getHeight(null);
                Integer orig_width = orig.getWidth(null);
                Integer result_width = size[i];
                Integer result_height = result_width * orig_height / orig_width;
                System.out.println(result_height + " " + result_width);
                BufferedImage bi = new BufferedImage(result_width, result_height, BufferedImage.TYPE_INT_RGB);
                bi.getGraphics().drawImage(orig, 0, 0, result_width, result_height, null);
                //BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                //bi.getGraphics().drawImage(orig, 0, 0, w, h, x, y, x + w, y + h, null);
                String[] arr = file.getName().split(".j");
                ImageIO.write(bi, "jpeg", new File(endFolder + "/" + album + "_" + arr[0] + suffix[i] + ".jpg"));
            }
        }*/
        String distination = "../upload/" + album;
        for (File file : fList) {
            String[] arr = file.getName().split(".j");
            String abs_path_b = distination + "/" + album + "_" + arr[0] + suffix[5] + ".jpg";
            String abs_path_m = distination + "/" + album + "_" + arr[0] + suffix[1] + ".jpg";
            //writer.println("<a href=\"" + abs_path_b + "\" title=\"\"> ");
            //writer.println("    <img alt=\"\" src=\"" + abs_path_m + "\" />");
            //writer.println("</a>");
            String imageName = album + "_" + arr[0] + ".jpg";
            dbService.addImage(imageName, album);
        }
        writer.close();
        System.out.println("success");
    }
}