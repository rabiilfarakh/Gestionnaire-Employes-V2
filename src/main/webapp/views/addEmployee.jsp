<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/addEmployee.css">
    <script src="../js/validation.js"></script>

    <title>Ajouter un Employé</title>

</head>
<body>
    <h2>Ajouter un Employé</h2>
    <form action="employees" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="add">

        <!-- Nom -->
        Nom: <input type="text" id="name" name="name" required><br>

        <!-- E-mail -->
        E-mail: <input type="email" id="email" name="email" required><br>

        <!-- Mot de passe -->
        Mot de passe: <input type="password" id="password" name="password" required><br>

        <!-- Téléphone -->
        Téléphone: <input type="text" id="phone" name="phone" required><br>

        <!-- Date de naissance -->
        Date de naissance: <input type="date" id="birthdate" name="birthdate" required><br>

        <!-- CIN -->
        CIN: <input type="text" id="cin" name="cin" required><br>

        <!-- Poste -->
        Poste: <input type="text" id="position" name="position" required><br>

        <!-- Département -->
        Département: <input type="text" id="department" name="department" required><br>

        <!-- Salaire -->
        Salaire: <input type="number" step="0.01" id="salary" name="salary" required><br>

        <!-- Bouton soumettre -->
        <input type="submit" value="Ajouter">
    </form>

    <!-- Lien de retour -->
    <a href="employees">Retour à la liste</a>
</body>
</html>
