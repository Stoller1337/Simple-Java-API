<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1  th:text="${message}"></h1>
<h3><a href="http://localhost:8080/api/sales/all"> Sales </a></h3>
<h3><a href="http://localhost:8080/api/warehouse/all"> Warehouse </a></h3>
<h3><a href="http://localhost:8080/api/charges/all"> Charges </a></h3>
<h3><a href="http://localhost:8080/api/expense_items/all"> Expense Items </a></h3>
<h3><a href="http://localhost:8080/api/auth/all" methods="post"> SignIn </a></h3>
</body>
</html>