<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Warehouse</title>
</head>
<br> Get all warehouse
<form th:action="@{/api/warehouse/all}" method="get">
    <button type="submit">All warehouses</button>
</form>
<br> Get warehouse by id
<form method="get" th:action="@{/api/warehouse/id}">
    <label>
        <input placeholder="id" name="id">
    </label>
    <button type="submit">Get Warehouse by id</button>
</form>
<br> Delete Warehouse
<form th:action="@{/api/warehouse/delete}" method="delete">
    <input placeholder="id" name="id">
    <button type="submit">delete warehouse</button>
</form>
<br> Update amount
<form th:action="@{/api/warehouse/update/amount}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="amount" name="amount">
    <button type="submit">Update amount</button>
</form>
<br> Update quantity
<form th:action="@{/api/warehouse/update/quantity}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="quantity" name="quantity">
    <button type="submit">Update quantity</button>
</form>
<br> Update sales date
<form th:action="@{/api/warehouse/update/name}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="name" name="name">
    <button type="submit">Update name</button>
</form>
<div>
    <table border="1">
        <tr>
            <th>id</th>
            <th>amount</th>
            <th>quantity</th>
            <th>name</th>
        </tr>
        <tr BGCOLOR="#5f9ea0" th:each="Warehouse:${warehouse}">
            <td th:text="${Warehouse.getId}"></td>
            <td th:text="${Warehouse.getAmount}"></td>
            <td th:text="${Warehouse.getQuantity}"></td>
            <td th:text="${Warehouse.getName}"></td>
        </tr>
    </table>
</div>
</html>
