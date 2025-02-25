<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
</head>
<body>
    <h1>User Management</h1>

    <form action="user/add" method="post">
        <h2>Add User</h2>
		Id: <input type="number" name="user_id" required><br>
        Name: <input type="text" name="user_name" required><br>
        Email: <input type="email" name="user_email" required><br>
        <button type="submit">Add User</button>
    </form>

    <form action="user/fetch" method="get">
        <h2>Fetch User</h2>
        ID: <input type="number" name="user_id"><br>
        <button type="submit">Fetch User</button>
    </form>

    <div>
        <h2>Result:</h2>
        <ul>
            <c:forEach var="user" items="${users}">
                <li>${user.user_id} - ${user.user_name} - ${user.user_email}</li>
            </c:forEach>
        </ul>
    </div>

    <div>${message}</div>
</body>
</html>
