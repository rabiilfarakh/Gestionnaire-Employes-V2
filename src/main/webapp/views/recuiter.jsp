<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer une offre d'emploi - Recruteur</title>
    <link rel="stylesheet" href="../css/recuiter.css">
</head>

<body>
    <header>
        <div class="container">
            <h1>Emplois.ma - Espace Recruteur</h1><br>
            <h2 id="nameR">Bonjour M. Rabii</h2>
        </div>
    </header>

    <section class="create-job-section">
        <div class="container">
            <h2>Créer une nouvelle offre d'emploi</h2>
            <!-- Formulaire pour la création d'une offre -->
            <form action="CreateJobServlet" method="POST">
                <div class="form-group">
                    <label for="jobTitle">Titre du poste :</label>
                    <input type="text" id="jobTitle" name="jobTitle" placeholder="Titre du poste" required>
                </div>
                <div class="form-group">
                    <label for="companyName">Nom de l'entreprise :</label>
                    <input type="text" id="companyName" name="companyName" placeholder="Nom de l'entreprise" required>
                </div>
                <div class="form-group">
                    <label for="location">Localisation :</label>
                    <input type="text" id="location" name="location" placeholder="Ville" required>
                </div>
                <div class="form-group">
                    <label for="description">Description de l'offre :</label>
                    <textarea id="description" name="description" rows="5" placeholder="Description du poste" required></textarea>
                </div>
                <div class="form-group">
                    <label for="salary">Salaire :</label>
                    <input type="number" id="salary" name="salary" placeholder="Salaire (optionnel)">
                </div>
                <div class="form-group">
                    <label for="jobType">Type de contrat :</label>
                    <select id="jobType" name="jobType" required>
                        <option value="CDI">CDI</option>
                        <option value="CDD">CDD</option>
                        <option value="Stage">Stage</option>
                        <option value="Freelance">Freelance</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit">Créer l'offre</button>
                </div>
            </form>
        </div>
    </
