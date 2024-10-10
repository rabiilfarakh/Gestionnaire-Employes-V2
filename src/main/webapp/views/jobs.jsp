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
                <input type="text" placeholder="Quel metier ?">
                <input type="text" placeholder="Ou ?">
                <button type="submit">Rechercher</button>
            </form>
        </div>
    </section>

    <section class="recommended-jobs">
        <div class="container">
            <h2>Offres d'emploi recommandees</h2>
            <div class="job-list">
                <!-- Les offres d'emploi iront ici -->
                <div class="job">
                    <h3>Developpeur Web</h3>
                    <p>Entreprise: Tech Corp</p>
                    <p>Localisation: Paris</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Chef de Projet IT</h3>
                    <p>Entreprise: Innovatech</p>
                    <p>Localisation: Lyon</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Designer UI/UX</h3>
                    <p>Entreprise: Creative Studios</p>
                    <p>Localisation: Marseille</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Developpeur Mobile</h3>
                    <p>Entreprise: AppWorks</p>
                    <p>Localisation: Nice</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Data Scientist</h3>
                    <p>Entreprise: DataLab</p>
                    <p>Localisation: Toulouse</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Ingenieur DevOps</h3>
                    <p>Entreprise: CloudOps</p>
                    <p>Localisation: Nantes</p>
                    <button>Postuler</button>
                </div>
                <div class="job">
                    <h3>Architecte Logiciel</h3>
                    <p>Entreprise: CodeMaster</p>
                    <p>Localisation: Bordeaux</p>
                    <button>Postuler</button>
                </div>
            </div>
            <!-- Pagination buttons -->
            <div class="pagination">
                <div id="page-numbers"></div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2024 Emplois.ma. Tous droits reserves.</p>
        </div>
    </footer>
</body>

</html>
