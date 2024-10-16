<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jobOffers.JobOffer" %>

<%
    // Récupération de l'offre d'emploi envoyée par le contrôleur
    JobOffer jobOffer = (JobOffer) request.getAttribute("jobOffer");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de l'Offre - Emplois.ma</title>
    <link rel="stylesheet" href="../css/job.css">
</head>
<body>

<header>
    <div class="container">
        <h1>Emplois.ma</h1>
        <nav>
            <a href="jobOffers?action=list">Retour à la liste</a>
            <a href="#">Connexion</a>
        </nav>
    </div>
</header>

<section class="job-detail">
    <div class="container">
        <h2>Détails de l'offre d'emploi</h2>
        <div class="job-info">
            <h3><%= jobOffer.getTitle() %></h3>
            <p><strong>Entreprise:</strong> <span style="color:blue;">IBM</span></p>
            <p><strong>Localisation:</strong> <%= jobOffer.getLocation() %></p>
            <p><strong>Salaire:</strong> <%= jobOffer.getSalary() %> DH</p>
            <p><strong>Type de contrat:</strong> <%= jobOffer.getContrat() %></p>
            <p><strong>Description:</strong> <%= jobOffer.getDescription() %></p>
        </div><br><br>

        <!-- Formulaire de candidature -->
        <h2>Postuler à cette offre</h2>
        <form action="jobOffers" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="apply">
            <input type="hidden" name="id" value="<%= jobOffer.getId() %>">
            <label for="cv">Télécharger votre CV (PDF) :</label>
            <input type="file" id="cv" name="cv" accept=".pdf" required>
            <button type="submit">Postuler</button>
        </form>

    </div>
</section>

<footer>
    <div class="container">
        <p>&copy; 2024 Emplois.ma. Tous droits réservés.</p>
    </div>
</footer>

</body>
</html>
