<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Usuario Cliente</title>
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <style>
        .register-page {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #d2d6de;
        }
        .register-box {
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .register-box h1 {
            text-align: center;
            color: #333333;
        }
        .register-box form {
            display: flex;
            flex-direction: column;
        }
        .register-box .form-group {
            position: relative;
        }
        .register-box .form-group input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .register-box .form-group .form-control-feedback {
            position: absolute;
            right: 10px;
            top: 10px;
            color: #999;
        }
        .register-box input[type="submit"] {
            background-color: #3c8dbc;
            color: #ffffff;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .register-box input[type="submit"]:hover {
            background-color: #367fa9;
        }
    </style>
</head>
<body class="register-page">
    <div class="register-box">
        <h1>Registrar Cliente</h1>
        <form action="RegisterServlet" method="post">
            <div class="form-group has-feedback">
                <label for="nombreUsuario">Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" class="form-control" placeholder="Usuario" required>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="apellidouser">Apellido:</label>
                <input type="text" id="lastnameuser" name="lastnameuser" class="form-control" placeholder="Apellido" required>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="correouser">Correo:</label>
                <input type="text" id="correouser" name="correouser" class="form-control" placeholder="Correo" required>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="dniuser">Dni:</label>
                <input type="text" id="dniuser" name="dniuser" class="form-control" placeholder="Dni" required>
                <span class="glyphicon glyphicon-info-sign form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <label for="clave">Contraseña:</label>
                <input type="password" id="clave" name="clave" class="form-control" placeholder="Contraseña" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group">
                <label for="estado">Estado:</label>
                <input type="checkbox" id="estado" name="estado">
            </div>
            <input type="submit" value="Registrar" class="btn btn-primary btn-block">
        </form>
    </div>

    <!-- jQuery 3 -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' /* optional */
            });
        });
    </script>
</body>
</html>
