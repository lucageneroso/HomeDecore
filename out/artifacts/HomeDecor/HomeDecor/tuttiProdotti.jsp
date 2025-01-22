<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ page import="service.Catalogo" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.UserManagement.Fornitore" %>


<%@ page import="java.util.List" %>
<%@ page import="javax.swing.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="jakarta.inject.Inject" %>

<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
    if (prodotti == null) {
        System.out.println("Prodotti is null in JSP");
    } else {
        System.out.println("Number of products in JSP: " + prodotti.size());
    }
%>

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


    <div class="content">
        <h1>Prodotti</h1>
        <ul id="product-list">
            <c:forEach items="${prodotti}" var="prodotto">
                <li>
                    <a href="ProductDetails.jsp?id=${prodotto.id}">
                            ${prodotto.nome}<br>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <!--<ul id="product-list">

            <%/*
                if (prodotti != null && !prodotti.isEmpty()) {
                    for (Prodotto product : prodotti) {
                    	
                    	//byte[] imageBytes = product.getImage();
                        //String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                        //ImageIcon imageIcon = product.getImage(); // Ottieni l'ImageIcon
                        //String base64Image = convertImageIconToBase64(imageIcon);
                        
                        out.println("<li><a href='ProductDetails.jsp?id=" + product.getId() + "'>" +
                                product.getNome() + "<br>" +
                                //"<img src='data:image/jpg;base64," + base64Image + "' alt='Immagine Prodotto' style='max-width:100px;'>"+
                                "</a></li>");
                    }
                } else {
                    out.println("<li>Nessun prodotto disponibile</li>");
                }*/
            %>
        </ul>-->
        </div>

</body>
