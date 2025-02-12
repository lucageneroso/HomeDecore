package Utils;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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

        int maxSize=300;

        try (InputStream is = ImageUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Immagine non trovata: " + resourcePath);
            }

            // Leggi l'immagine originale
            BufferedImage originalImage = ImageIO.read(is);
            if (originalImage == null) {
                throw new IOException("Errore nella lettura dell'immagine: " + resourcePath);
            }

            // Ottieni dimensioni originali
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            // Se è già più piccola della dimensione massima, la restituiamo senza ridimensionarla
            if (originalWidth <= maxSize && originalHeight <= maxSize) {
                return convertToByteArray(originalImage);
            }

            // Calcola la nuova dimensione mantenendo le proporzioni
            double scale = Math.min((double) maxSize / originalWidth, (double) maxSize / originalHeight);
            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            // Ridimensiona l'immagine
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // Converti l'immagine ridimensionata in byte array
            return convertToByteArray(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] convertToByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos); // Puoi cambiare "png" con "jpg" o altro
            return baos.toByteArray();
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
