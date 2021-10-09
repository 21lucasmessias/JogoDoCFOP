package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    private static ImageUtils instanceFlag = null;

    private ImageUtils() {}

    public static ImageUtils getInstance() {
        if(instanceFlag == null) instanceFlag = new ImageUtils();
        return instanceFlag;
    }

    public static Image getImage(String fileName) {
        return getInstance()._getImage(fileName);
    }

    private Image _getImage(String filename) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
