<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ page import="service.Catalogo" %>
<%@ page import="model.Prodotto" %>

<%@ page import="java.util.List" %>
<%@ page import="javax.swing.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>

<%!
    public static String convertImageIconToBase64(ImageIcon imageIcon) {
        try {
            // Ottieni l'immagine da ImageIcon
            BufferedImage bufferedImage = new BufferedImage(
                    imageIcon.getIconWidth(),
                    imageIcon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            imageIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

            // Converti BufferedImage in array di byte
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Codifica in Base64
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
%>

<%
    List<Prodotto> prodotti = catalogo.getProducts();

    // Verifica se la lista prodotti è vuota o nulla
    if (prodotti.isEmpty()) {
        out.println("N");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <title>Tutti i Prodotti</title>
    <!--
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/Product.css">
    -->
</head>
<body>
<jsp:include page="header.jsp" />

<div class="content">
    <h1>Prodotti</h1>
    <ul id="product-list">
            if (prodotti != null && !prodotti.isEmpty()) {
                for (Prodotto product : prodotti) {

                    //byte[] imageBytes = product.getImage();
                    //String base64Image = Base64.getEncoder().encodeToString(imageBytes);


                    out.println("<li><a href='ProductDetails.jsp?id=" + product.getId() + "'>" +
                            product.getNome() + "<br>" +
                            "</a></li>");
                }
            } else {
                out.println("<li>Nessun prodotto disponibile</li>");
        %>
</div>

</body>