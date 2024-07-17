<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Usuario Cliente</title>
    <style>
        body {
            font-family: cursive sans-serif;
            background-color:#555555;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #3a87ad;
            padding: 50px;
            border-radius: 40px;
            box-shadow: 0 0 300px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        h1 {
            text-align: center;
            color: #000\9;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 15px;
            color: #f8f7f6;
        }
        input[type="text"], input[type="password"], input[type="submit"] {
            padding: 20px;
            margin-bottom: 30px;
            border: 3px solid #000\9;
            border-radius: 25px;
            font-size: 17px;
        }
        input[type="checkbox"] {
            margin-bottom: 30px;
        }
        input[type="submit"] {
            background-color: #53d9f0;
            color: #ffffff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #099;
        }
        .form-group {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registrar Cliente</h1>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="nombreUsuario">Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="form-group">
                <label for="clave">Contraseña:</label>
                <input type="password" id="clave" name="clave" required>
            </div>
            <div class="form-group">
                <label for="estado">Estado:</label>
                <input type="checkbox" id="estado" name="estado">
            </div>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
