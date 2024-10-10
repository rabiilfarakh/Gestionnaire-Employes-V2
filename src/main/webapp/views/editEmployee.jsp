<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="employes.Employee" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/addEmployee.css">
    <title>Éditer un Employé</title>
</head>
<body>
    <h2>Éditer un Employé</h2>

    <%
        // Récupération de l'objet Employee depuis la requête
        Employee employee = (Employee) request.getAttribute("employee");
    %>

    <form action="employees" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= employee.getId() %>">

        Nom: <input type="text" name="name" value="<%= employee.getName() %>" required><br>
        Email: <input type="email" name="email" value="<%= employee.getEmail() %>" required><br>
        Password: <input type="password" name="password" value="<%= employee.getPassword() %>" required><br>
        Téléphone: <input type="text" name="phone" value="<%= employee.getPhone() %>" required><br>
        Date de naissance: <input type="date" name="birthdate" value="<%= employee.getBirthdate() %>" required><br>
        CIN: <input type="text" name="cin" value="<%= employee.getCin() %>" required><br>
        Poste: <input type="text" name="position" value="<%= employee.getPosition() %>" required><br>
        Département: <input type="text" name="department" value="<%= employee.getDepartment() != null ? employee.getDepartment() : "" %>" required><br>
        Salaire: <input type="number" name="salary" value="<%= employee.getSalary() %>" required><br>
        <input type="submit" value="Mettre à jour">
    </form>

    <a href="employees">Retour à la liste</a>
</body>
</html>
