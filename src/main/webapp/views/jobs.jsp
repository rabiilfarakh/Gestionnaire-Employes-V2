<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="jobOffers.JobOffer" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Site d'emplois</title>
    <link rel="stylesheet" href="../css/jobs.css">
    <script src="../js/pagination.js" defer></script>
</head>

<body>
    <header>
        <div class="container">
            <h1>Emplois.ma</h1>
            <nav>
                <a href="#">#----------#</a>
                <a href="#">Connexion</a>
            </nav>
        </div>
    </header>

    <section class="search-section">
        <div class="container">
            <h2>Trouvez l'emploi qui vous correspond</h2>
            <form>
                <input type="text" placeholder="Quel métier ?">
                <input type="text" placeholder="Où ?">
                <button type="submit">Rechercher</button>
            </form>
        </div>
    </section>

    <section class="recommended-jobs">
        <div class="container">
            <h2>Offres d'emploi recommandées</h2>
            <div class="job-list">
                <!-- Boucle pour afficher dynamiquement les offres d'emploi -->
                <%
                    List<JobOffer> jobOffers = (List<JobOffer>) request.getAttribute("jobOffers");
                    if (jobOffers != null) {
                        for (JobOffer jobOffer : jobOffers) {
                %>
                    <div class="job">
                        <h3><%= jobOffer.getTitle() %></h3>
                        <p>Entreprise: </p>
                        <p>Localisation: <%= jobOffer.getLocation() %></p>
                        <p>Salaire: <%= jobOffer.getSalary() %> €</p>
                        <p>Type de contrat: <%= jobOffer.getContrat() %></p>
                        <button>Postuler</button>
                    </div>
                <%
                        }
                    } else {
                %>
                    <p>Aucune offre d'emploi disponible.</p>
                <%
                    }
                %>
            </div>
            <!-- Pagination buttons -->
            <div class="pagination">
                <div id="page-numbers"></div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2024 Emplois.ma. Tous droits réservés.</p>
        </div>
    </footer>
</body>

</html>
