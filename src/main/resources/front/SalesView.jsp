<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Sales</title>
</head>
<br> Get all sales
<form th:action="@{/api/sales/all}" method="get">
    <button type="submit">All sales</button>
</form>

<br> Get sales by id
<form method="get" th:action="@{/api/sales/id}">
    <label>
        <input placeholder="id" name="id">
    </label>
    <button type="submit">Get by id</button>
</form>
<br> Update amount
<form th:action="@{/api/sales/update/amount}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="amount" name="amount">
    <button type="submit">Update amount</button>
</form>
<br> Update quantity
<form th:action="@{/api/sales/update/quantity}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="quantity" name="quantity">
    <button type="submit">Update quantity</button>
</form>
<br> Update sales date
<form th:action="@{/api/sales/update/date}" th:method="post">
    <input placeholder="id" name="id">
    <input placeholder="salesDate" name="sales_date">
    <button type="submit">Update sales date</button>
</form>
<br> Delete sales
<form th:action="@{/api/sales/delete/}" th:method="delete">
    <input placeholder="id" name="id">
    <button type="submit">Delete sales</button>
</form>

<br>
<div>
    <table border="1">
        <tr>
            <th>id</th>
            <th>amount</th>
            <th>quantity</th>
            <th>sales date</th>
            <th>warehouse id</th>
        </tr>
        <tr bgcolor="#5f9ea0" th:each = "Sales:${sales}">
            <td th:text="${Sales.getId}"></td>
            <td th:text="${Sales.getAmount}"></td>
            <td th:text="${Sales.getQuantity}"></td>
            <td th:text="${Sales.getSalesDate}"></td>
            <td th:text="${Sales.getWarehouseId_id}"></td>
        </tr>
    </table>
</div>

</html>
