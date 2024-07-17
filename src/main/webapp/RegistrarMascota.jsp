<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("vendedor") != null) {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Bodega | Registrar Mascota</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <header class="main-header">
                <a href="#" class="logo">
                    <span class="logo-mini"><b>S</b>BL</span>
                    <span class="logo-lg"><b>Sistema </b>Bodega</span>
                </a>
                <nav class="navbar navbar-static-top" role="navigation">
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">${vendedor.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>Bienvenido - ${vendedor.nombreUsuario}<br>
                                            <small>Usted es, ${vendedor.cargo.nombreCargo}</small>
                                        </p>
                                    </li>
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a href="srvUsuario?accion=cerrar" class="btn btn-default btn-flat">Cerrar Sesión</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <aside class="main-sidebar">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Bienvenido, ${vendedor.nombreUsuario}</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                     <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">INICIO</li>
                        <!-- Optionally, you can add icons to the links -->
                        <li class="active"><a href="Controlador?accion=home"><i class="fa fa-cart-arrow-down"></i> <span>Catalogo</span></a></li>
                        <li class="active"><a href="Controlador?accion=MisCompras"><i class="fa fa-cart-arrow-down"></i> <span>Mis compras</span></a></li>
                        <li class="active"><a href="srvMascota?menu=RegistrarMascota&accion=Listar"><i class="fa fa-tags"></i> <span> Mis Mascotas</span></a></li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=carrito"><i class="fas fa-cart-plus">(<label style="color: darkorange">${cont}</label>)</i> Carrito</a>
                        </li> 
                    </ul>
                </section>
            </aside>

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Registrar Mascota</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Registrar Mascota</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-paw"></i> Registrar Mascota</h4>
                                </div>
                                <div class="card-body">
                                    <form action="srvMascota" method="POST">
                                        <input type="hidden" name="accion" value="agregar" />
                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input type="text" name="nombre" class="form-control" required>
                                        </div> 
                                        <div class="form-group">
                                            <label>Raza</label>
                                            <input type="text" name="raza" class="form-control" required>
                                        </div> 
                                        <div class="form-group">
                                            <label>Sexo</label>
                                            <input type="text" name="sexo" class="form-control" required>
                                        </div> 
                                        <div class="form-group">
                                            <label>Especie</label>
                                            <input type="text" name="especie" class="form-control" required>
                                        </div>
                                        <div class="text-center">
                                            <button type="submit" class="btn btn-primary">Registrar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-8 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-list"></i> Lista de Mascotas</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-container">
                                        <div class="mb-3">
                                            <input type="text" id="searchInput" class="form-control" placeholder="Buscar mascota...">
                                        </div>
                                        <table id="tablaMascotas" class="table">
                                            <thead>
                                                <tr>
                                                    <th>Nombre</th>
                                                    <th>Raza</th>
                                                    <th>Sexo</th>
                                                    <th>Especie</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="mascota" items="${mascotas}">
                                                    <tr>
                                                        <td>${mascota.nombre}</td>
                                                        <td>${mascota.raza}</td>
                                                        <td>${mascota.sexo}</td>
                                                        <td>${mascota.especie}</td>
                                                        <td>
                                                        <a class="btn btn-warning" href="srvMascota?accion=editarMascota&id=${mascota.getIDUSUARIO()}"><i class="fa fa-pencil"></i></a>
                                                        <a class="btn btn-danger" href="srvMascota?accion=deleteMascota&id=${mascota.getIDUSUARIO()}"><i class="fa fa-trash"></i></a>
                                                    </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <a class="btn btn-info" data-toggle="modal" data-target="#addMascotaModal"><i class="fa fa-paw"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

             <footer class="main-footer">
                <!-- To the right -->
                <div class="pull-right hidden-xs">
                    PetFrendly
                </div>
                <!-- Default to the left -->
                <strong>HappyPet<a href="#"></a>.</strong> Todos los derechos reservados.
            </footer>
            <div class="control-sidebar-bg"></div>
        </div>

        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="dist/js/adminlte.min.js"></script>

        <script>
            $(document).ready(function() {
                $('#tablaMascotas').DataTable();
                $('#searchInput').on('keyup', function() {
                    $('#tablaMascotas').DataTable().search($(this).val()).draw();
                });
            });
        </script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("identificar.jsp");
    }
%>
