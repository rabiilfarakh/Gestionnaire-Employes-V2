<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des Employés</title>
    <link rel="stylesheet" type="text/css" href="../css/employes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</head>
<body>

<h1>Liste des Employés</h1>

<div class="controls">
    <form action="employees" method="get">
        <input type="hidden" name="action" value="search">
        <input type="text" name="searchInput" placeholder="Rechercher un employé...">
    </form>

    <form action="employees" method="get">
        <input type="hidden" name="action" value="filter">
        <select name="departmentFilter" id="departmentFilter" onchange="this.form.submit();">
            <option value="">Sélectionner un département</option>
            <c:forEach var="department" items="${distinctDepartments}">
                <option value="${department}">${department}</option>
            </c:forEach>
        </select>
    </form>
</div>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Téléphone</th>
        <th>Département</th>
        <th>Poste</th>
        <th>Actions</th>
    </tr>

    <c:choose>
        <c:when test="${empty employees}">
            <tr>
                <td colspan="7">Aucun employé trouvé.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.idEmployee}</td>
                    <td>${employee.name}</td>
                    <td>${employee.email}</td>
                    <td>${employee.phone}</td>
                    <td>
                        <c:if test="${employee.departement != null}">
                            ${employee.departement}
                        </c:if>
                        <c:if test="${employee.departement == null}">
                            Non spécifié
                        </c:if>
                    </td>
                    <td>${employee.poste}</td>
                    <td>
                        <form action="employees" method="get" style="display:inline;">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="${employee.idEmployee}">
                            <button type="submit">Mettre à Jour</button>
                        </form>
                        <form action="employees" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${employee.idEmployee}">
                            <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet employé ?');">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table><br>

<form action="addEmployee.jsp">
    <button type="submit">Ajouter un Employé</button>
</form>

</body>
</html>
