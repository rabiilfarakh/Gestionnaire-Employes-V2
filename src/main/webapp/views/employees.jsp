<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="employes.Employee" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Employés</title>
    <link rel="stylesheet" type="text/css" href="../css/employes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</head>
<body>

<h1>Liste des Employés</h1>

<div class="controls">
    <form action="employees" method="get" style="display:inline;">
        <input type="hidden" name="action" value="search">
        <input type="text" name="searchInput" placeholder="Rechercher un employé...">
        <button type="submit">Rechercher</button>
    </form>

    <form action="employees" method="get" style="display:inline;">
        <input type="hidden" name="action" value="filter">
        <select name="departmentFilter" id="departmentFilter" onchange="this.form.submit();">
            <option value="">Sélectionner un département</option>
            <%
                List<String> distinctDepartments = (List<String>) request.getAttribute("distinctDepartments");
                if (distinctDepartments != null) {
                    for (String department : distinctDepartments) {
            %>
                <option value="<%= department %>"><%= department %></option>
            <%
                    }
                }
            %>
        </select>
    </form>
</div>

<table border="1">
    <thead>
        <tr>
            <th>Compte</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Date de Naissance</th>
            <th>CIN</th>
            <th>Département</th>
            <th>Poste</th>
            <th>Salaire</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employees");
            if (employees == null || employees.isEmpty()) {
        %>
            <tr>
                <td colspan="7">Aucun employé trouvé.</td>
            </tr>
        <%
            } else {
                int count = 1; // Initialisation du compteur
                for (Employee employee : employees) {
        %>
            <tr>
                <td><%= count++ %></td>
                <td><%= employee.getName() %></td>
                <td><%= employee.getEmail() %></td>
                <td><%= employee.getPhone() %></td>
                <td><%= employee.getBirthdate() != null ? employee.getBirthdate() : "Non spécifiée" %></td>
                <td><%= employee.getCin() != null ? employee.getCin() : "Non spécifié" %></td>
                <td><%= employee.getDepartment() != null ? employee.getDepartment() : "Non spécifié" %></td>
                <td><%= employee.getPosition() %></td>
                <td><%= employee.getSalary() != null ? employee.getSalary() : "Non spécifié" %></td>

                <td>
                    <form action="employees" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="<%= employee.getId() %>">
                        <button type="submit">Mettre à Jour</button>
                    </form>
                    <form action="employees" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= employee.getId() %>">
                        <button type="submit" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet employé ?');">Supprimer</button>
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </tbody>
</table>

<br>

<form action="addEmployee.jsp">
    <button type="submit">Ajouter un Employé</button>
</form>

</body>
</html>
