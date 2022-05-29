<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Charges</title>
</head>
<br> Get all charges
<form th:action="@{/api/charges/all}" method="get">
    <button type="submit">All charges</button>
</form>
<br> Get charges by id
<form th:action="@{/api/charges/id}" method="get">
    <input placeholder="id" name="id">
    <button type="submit">Get charges</button>
</form>
<br> New charges
<form th:action="@{/api/charges/add}" method="post">
    <input placeholder="amount" name="amount">
    <input placeholder="name" name="name">
    <input placeholder="expense_items" name="expense_items">
    <button type="submit">Add charges</button>
</form>
<br> Delete Charges
<form th:action="@{/api/charges/delete}" method="post">
    <input placeholder="id" name="id">
    <input placeholder="name" name="name">
    <button type="submit">Delete</button>
</form>
<br> Update amount
<form th:action="@{/api/charges/update/amount}" method="post">
    <input placeholder="id" name="id">
    <input placeholder="amount" name="amount">
    <button type="submit">Uodate</button>
</form>
<br> Update name
<form th:action="@{/api/charges/update/name}" method="post">
    <input placeholder="id" name="id">
    <input placeholder="name" name="name">
    <button type="submit">Update</button>
</form>
<div>
    <table border="1">
        <tr>
            <th>id</th>
            <th>amount</th>
            <th>name</th>
            <th>expense_items</th>
        </tr>
        <tr BGCOLOR="#5f9ea0" th:each="Charges:${charges}">
            <td th:text="${Charges.getId}"></td>
            <td th:text="${Charges.getAmount}"></td>
            <td th:text="${Charges.getChargeDate}"></td>
            <td th:text="${Charges.getExpense_items}"></td>
        </tr>
    </table>
</div>