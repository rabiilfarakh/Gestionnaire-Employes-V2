<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Employee" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/editEmployee.css">
    <title>Éditer un Employé</title>
</head>
<body>
    <h2>Éditer un Employé</h2>

        <form action="employees" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${employee.idEmployee}">

            Nom: <input type="text" name="name" value="${employee.name}" required><br>
            Email: <input type="email" name="email" value="${employee.email}" required><br>
            Téléphone: <input type="text" name="phone" value="${employee.phone}" required><br>
            Département: <input type="text" name="departement" value="${employee.departement}" required><br>
            Poste: <input type="text" name="poste" value="${employee.poste}" required><br>
            <input type="submit" value="Mettre à jour">
        </form>



    <a href="employees">Retour à la liste</a>
</body>
</html>