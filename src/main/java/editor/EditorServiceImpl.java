package editor;

import base.EditorServiceError;
import base.EditorServiceResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditorServiceImpl implements EditorService {

    private String getUploadPath (String str, Image image) {
        System.out.println(str);
        String resultString = "";
        String[] strings = str.split("/");
        strings[1] = "public_html/uploads";
        String[] name = strings[2].split("\\.");
        System.out.println(name[0]);
        name[0] = name[0].concat(String.valueOf((image.hashCode())));
        System.out.println(name[0]);
        for (int i = 0; i < 2; i++ ) {
            resultString = resultString.concat(strings[i]);
            resultString = resultString.concat("/");
        }
        resultString = resultString.concat(name[0]);
        resultString  = resultString.concat(".png");
        System.out.println(resultString);
        return resultString;
    }

    public EditorServiceResponse cropImage(Image image) throws IOException {

        try {
            System.out.println(image.getOriginPath());
            long startTime = System.nanoTime();
            String path = image.getOriginPath();
            BufferedImage orig = ImageIO.read(new File(path));

            int resultX = image.getResultX();
            int resultY = image.getResultY();
            System.out.println(resultX);
            System.out.println(resultY);
            int w = image.getWidth();
            int h = image.getHeight();
            int x = image.getX();
            int y = image.getY();

            BufferedImage bi = new BufferedImage(resultX, resultY, BufferedImage.TYPE_INT_ARGB);
            bi.getGraphics().drawImage(orig, 0, 0, resultX, resultY, x, y, x+w, y+h, null);

            // надо подумать насчет формата сохраняемого изображения.
            ImageIO.write(bi, "png", new File(getUploadPath(path, image)));
            long estimatedTime = (System.nanoTime() - startTime);

            // запись в базу данных о создании нового изображения.

            System.out.println(estimatedTime);
            return new EditorServiceResponse<>(true, true);
        } catch (Exception e) {
            System.out.println("Exception in EditorService.cropImage: " + e.getMessage());
            return new EditorServiceResponse<>(false, EditorServiceError.ServerError);
        }
    }
}
