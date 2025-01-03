package util;

import javax.swing.ImageIcon;

public class ImageUtil {
    public static ImageIcon loadImageIcon(String imageName) {
        // Load the image as a resource
        java.net.URL imageUrl = ImageUtil.class.getResource("/Images/" + imageName);

        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            System.err.println("Image not found: " + imageName);
            return null;
        }
    }
}