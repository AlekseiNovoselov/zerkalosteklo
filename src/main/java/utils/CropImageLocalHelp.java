package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by aleksei on 02.02.15.
 */
public class CropImageLocalHelp {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Lexa");
        String path = "/duke.jpg";
        BufferedImage orig = ImageIO.read(new File("duke.jpg"));
        //System.out.println(orig.getHeight());
    }
}
