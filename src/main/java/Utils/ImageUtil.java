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


    public static byte[] resizeImage(byte[] originalImage, int width, int height) throws IOException {
        // Converti l'array di byte in BufferedImage
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(originalImage);
        BufferedImage originalBufferedImage = ImageIO.read(byteArrayInputStream);

        // Ridimensiona l'immagine
        Image scaledImage = originalBufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedScaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);

        // Converte l'immagine ridimensionata in byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedScaledImage, "jpg", byteArrayOutputStream);  // Usa il formato che preferisci
        return byteArrayOutputStream.toByteArray();
    }


    public static byte[] readImageFromResources(String resourcePath) {
        int maxSize = 500;

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
            System.out.println("Dimensioni originali: " + originalWidth + "x" + originalHeight);

            // Se è già più piccola della dimensione massima, la restituiamo senza ridimensionarla
            if (originalWidth <= maxSize && originalHeight <= maxSize) {
                System.out.println("Immagine già piccola");
                return convertToByteArray(originalImage);
            }

            // Calcola la nuova dimensione mantenendo le proporzioni
            double scale = Math.min((double) maxSize / originalWidth, (double) maxSize / originalHeight);
            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            System.out.println("Dimensioni ridimensionate: " + newWidth + "x" + newHeight);

            // Ridimensiona l'immagine
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            //System.out.println("Qui");
            // Converti l'immagine ridimensionata in byte array
            return convertToByteArray(resizedImage);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] convertToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);  // Modifica il formato se necessario
        baos.flush();
        return baos.toByteArray();
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

    public static void processAllImagesInFolder(String folderPath, int maxSize) {
        // Ottieni la cartella dove cercare le immagini
        File folder = new File(folderPath);

        // Controlla se la cartella esiste
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("La cartella non esiste o non è una directory.");
            return;
        }

        // Ottieni tutti i file nella cartella
        File[] files = folder.listFiles();

        // Itera su tutti i file e processa solo le immagini
        for (File file : files) {
            if (file.isFile() && isImageFile(file)) {
                String relativePath = "images/" + file.getName(); // Relative path per la cartella src/main/resources/images
                System.out.println("Processando immagine: " + relativePath);
                saveResizedImage(relativePath, maxSize);
            }
        }
    }

    // Funzione per controllare se il file è un'immagine (basata sull'estensione)
    private static boolean isImageFile(File file) {
        String[] validExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        String fileName = file.getName().toLowerCase();

        for (String ext : validExtensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static void saveResizedImage(String resourcePath, int maxSize) {
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
            System.out.println("Dimensioni originali: " + originalWidth + "x" + originalHeight);

            // Se è già più piccola della dimensione massima, non la ridimensioniamo
            if (originalWidth <= maxSize && originalHeight <= maxSize) {
                System.out.println("Immagine già piccola");
                saveImageToFile(originalImage, resourcePath);
                return;
            }

            // Calcola la nuova dimensione mantenendo le proporzioni
            double scale = Math.min((double) maxSize / originalWidth, (double) maxSize / originalHeight);
            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            System.out.println("Dimensioni ridimensionate: " + newWidth + "x" + newHeight);

            // Ridimensiona l'immagine
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            // Salva l'immagine ridimensionata nel file system
            saveImageToFile(resizedImage, resourcePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToFile(BufferedImage image, String resourcePath) {
        // Ottieni il percorso della risorsa
        String pathToSave = "src/main/resources/" + resourcePath;
        File outputFile = new File(pathToSave);

        // Crea la cartella se non esiste
        outputFile.getParentFile().mkdirs();

        try {
            // Scrivi l'immagine nel file
            ImageIO.write(image, "jpg", outputFile);  // puoi cambiare il formato se necessario
            System.out.println("Immagine salvata in: " + pathToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // Per esempio, per la cartella 'cucina'
        String folderPath = "src/main/resources/images/giardino"; // Percorso della cartella
        int maxSize = 500; // Dimensione massima per il ridimensionamento
        ImageUtil.processAllImagesInFolder(folderPath, maxSize);
    }

}
