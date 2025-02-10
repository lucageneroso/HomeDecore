package Utils;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtil {

    public static byte[] readImageAsBytes(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Puoi gestire meglio l'errore
        }
    }

    public static byte[] readImageFromResources(String resourcePath) {
        try (InputStream is = ImageUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Immagine non trovata: " + resourcePath);
            }
            return is.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getImageIconFromBytes(byte[] imageData) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage bufferedImage = ImageIO.read(bis);
            return new ImageIcon(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
