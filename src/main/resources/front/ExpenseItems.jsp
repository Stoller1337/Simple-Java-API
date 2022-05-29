<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Charges</title>
</head>
<br>Get all Expense Items
<form th:action="@{/api/expense_items/all}" method="get">
    <button type="submit">All charges</button>
</form>

<br>Get Expense Items by id
<form th:action="@{/api/expense_items/id}" method="get">
    <input placeholder="id" name="id">
    <button type="submit">Get Charges</button>
</form>

<br>Update name
<form th:action="@{/api/expense_items/update/name}" method="post">
    <input placeholder="id" name="id">
    <input placeholder="name" name="name">
    <button type="submit">Update name</button>
</form>

<br>Add Expense Items
<form th:action="@{/api/expense_items/add}" method="post">
    <input placeholder="name" name="name">
    <button type="submit">Update name</button>
</form>

<br>Delete charges
<form th:action="@{/api/expense_items/delete}" method="delete">
    <input placeholder="id" name="id">
    <button type="submit">Delete</button>
</form>

<div>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        <tr BGCOLOR="#5f9ea0" th:each="ExpenseItems:${expense_items}">
            <td th:text="${ExpenseItems.getId}"></td>
            <td th:text="${ExpenseItems.getName}"></td>
        </tr>
    </table>
</div>