<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("vendedor") != null)  {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Bodega | Registrar Cita</title>
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
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">INICIO</li>
                        <!-- Optionally, you can add icons to the links -->
                        <li><a href="#"><i class="fa fa-link"></i> <span>Productos</span></a></li>
                        <li class="active"><a href="svrCitas?menu=RegistrarCitas&accion=Listar"><i class="fa fa-calendar-check-o"></i> <span>Citas</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-cart-arrow-down"></i> <span>Ventas</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-cart-arrow-down"></i>Nueva Venta</a></li>
                                <li><a href="srvMascota?menu=RegistrarMascota&accion=Listar"><i class="fa fa-tags"></i>Mascotas</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
            </aside>

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Citas</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Registrar Cita</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-calendar-check-o"></i>Actualizar Cita</h4>
                                </div>
                                <div class="card-body">
                                    <form action="svrCitas" method="POST">
                                        <input type="hidden" name="accion" value="Actualizar" />
                                        <div class="form-group">
                                            <label for="mascota">Mascota</label>
                                            <select class="form-control" id="mascota" name="mascota" required>
                                            <c:forEach var="mascota" items="${mascotas}">
                                                <option value="${mascota.getIDUSUARIO()}"
                                                <c:if test="${mascota.getIDUSUARIO() == cita.getMascota()}">
                                                selected
                                                </c:if>>${mascota.nombre}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect1">Veterinario</label>
                                            <select class="form-control" name="veterinario" required>
                                            <c:forEach var="vet" items="${veterinarios}">
                                                <option value="${vet.getId_Veterinario()}"
                                                <c:if test="${vet.getId_Veterinario() == cita.getVeterinario()}">
                                                selected
                                                </c:if>>${vet.nom}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect1">Servicio</label>
                                            <select class="form-control" name="servicio" required>
                                            <c:forEach var="serv" items="${servicios}">
                                                <option value="${serv.getId_Servicio()}"
                                                <c:if test="${serv.getId_Servicio() == cita.getServicio()}">
                                                selected
                                                </c:if>>${serv.nombre}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div>                                                                                 
                                        <div class="form-group">
                                            <label for="pwd">Fecha</label>
                                            <input type="date" class="form-control" name="fecha" value="${cita.getFecha()}" required>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="pwd">Hora</label>
                                            <input type="time" class="form-control" name="hora" value="${cita.getHora()}" required>
                                        </div>
                                        
                                        
                                        
                                        <div class="text-center">
                                            <button class="btn btn-primary" data-toggle="modal" data-target="#updateCitaModal">Registrar</button>
                                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                        </div>
                                        
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-8 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-list"></i> Lista de Citas</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-container">
                                        <table id="tablaCitas" class="table">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Mascota</th>
                                                    <th>Veterinario</th>
                                                    <th>Servicio</th>
                                                    <th>Fecha</th>
                                                    <th>Hora</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="cita" items="${citas}">
                                                    <tr>
                                                        <td>${cita.id_cita}</td>
                                                        <td>${cita.mascota}</td>
                                                        <td>${cita.veterinario}</td>
                                                        <td>${cita.servicio}</td>
                                                        <td>${cita.fecha}</td>
                                                        <td>${cita.hora}</td>
                                                        <td>
                                                            <a class="btn btn-warning" href="svrCitas?accion=Editar&id=${cita.getId_cita()}"><i class="fa fa-pencil"></i></a>
                                                            <a class="btn btn-danger" href="svrCitas?accion=Delete&id=${cita.getId_cita()}"><i class="fa fa-trash"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
                            
            <!-- Modal Actualizar Cirta -->
            <div class="modal fade" id="updateCitaModal" tabindex="-1" aria-labelledby="updateCitaModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateCitaModalLabel">Agregar Cita</h5>
                        </div>
                        <div class="modal-body">
                            <form action="svrCitas" method="POST">
                                <input type="hidden" name="accion" value="agregar" />
                                <div class="form-group">
                                    <label for="mascota">Mascota</label>
                                    <select class="form-control" id="mascota" name="mascota" required>
                                    <c:forEach var="mascota" items="${mascotas}">
                                        <option value="${mascota.getIDUSUARIO()}">
                                        ${mascota.nombre}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div> 
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Veterinario</label>
                                    <select class="form-control" name="veterinario" required>
                                    <c:forEach var="vet" items="${veterinarios}">
                                        <option value="${vet.getId_Veterinario()}">
                                        ${vet.nom}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div> 
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Servicio</label>
                                    <select class="form-control" name="servicio" required>
                                    <c:forEach var="serv" items="${servicios}">
                                        <option value="${serv.getId_Servicio()}">
                                        ${serv.nombre}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div>                                                                                 
                                <div class="form-group">
                                    <label for="pwd">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" value="${cita.getFecha()}" required>
                                </div>

                                <div class="form-group">
                                    <label for="pwd">Hora</label>
                                    <input type="time" class="form-control" name="hora" value="${cita.getHora()}" required>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Agregar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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
                $('#tablaCitas').DataTable();
                $('#searchInput').on('keyup', function() {
                    $('#tablaCitas').DataTable().search($(this).val()).draw();
                });
            });
        </script>
    </body>
</html>
<%} else if( session.getAttribute("usuario") != null) { %>
<html> 
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Bodega | Registrar Cita</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
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
                                    <span class="hidden-xs">${usuario.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>Bienvenido - ${usuario.nombreUsuario}<br>
                                            <small>Usted es, ${usuario.cargo.nombreCargo}</small>
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
                            <p>Bienvenido, ${usuario.nombreUsuario}</p>
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
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">INICIO</li>
                        <li><a href="ControladorVeterinario?menu=Veterinario&accion=Listar"><i class="fa fa-link"></i> <span>Veterinarios</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-archive"></i>Clientes</a></li>
                                <li class="active"><a href="svrCitas?menu=RegistrarCitas&accion=Listar"><i class="fa fa-calendar-check-o"></i> <span>Citas</span></a></li>  
                                <li><a href="#"><i class="fa fa-tags"></i>Proveedores</a></li>
                                <li><a href="ControladorProductos?menu=Productos&accion=Listar"><i class="fa fa-cube"></i>Productos</a></li>
                                <li><a href="ControladorServicios?menu=Servicios&accion=Listar"><i class="fa fa-users"></i>Servicios</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-cart-arrow-down"></i> <span>Ventas</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-cart-arrow-down"></i>Nueva Venta</a></li>
                                <li><a href="#"><i class="fa fa-tags"></i>Administrar Ventas</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#"><i class="fa fa-area-chart"></i> <span>Reportes</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-bar-chart"></i>Reportes Ventas</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
            </aside>

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>Citas</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Registrar Cita</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="row">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-calendar-check-o"></i>Actualizar Cita</h4>
                                </div>
                                <div class="card-body">
                                    <form action="svrCitas" method="POST">
                                        <input type="hidden" name="accion" value="Actualizar" />
                                        <div class="form-group">
                                            <label for="mascota">Mascota</label>
                                            <select class="form-control" id="mascota" name="mascota" required>
                                            <c:forEach var="mascota" items="${mascotas}">
                                                <option value="${mascota.getIDUSUARIO()}"
                                                <c:if test="${mascota.getIDUSUARIO() == cita.getMascota()}">
                                                selected
                                                </c:if>>${mascota.nombre}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect1">Veterinario</label>
                                            <select class="form-control" name="veterinario" required>
                                            <c:forEach var="vet" items="${veterinarios}">
                                                <option value="${vet.getId_Veterinario()}"
                                                <c:if test="${vet.getId_Veterinario() == cita.getVeterinario()}">
                                                selected
                                                </c:if>>${vet.nom}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label for="exampleFormControlSelect1">Servicio</label>
                                            <select class="form-control" name="servicio" required>
                                            <c:forEach var="serv" items="${servicios}">
                                                <option value="${serv.getId_Servicio()}"
                                                <c:if test="${serv.getId_Servicio() == cita.getServicio()}">
                                                selected
                                                </c:if>>${serv.nombre}</option>
                                            </c:forEach>                                                                                     
                                            </select>
                                        </div>                                                                                 
                                        <div class="form-group">
                                            <label for="pwd">Fecha</label>
                                            <input type="date" class="form-control" name="fecha" value="${cita.getFecha()}" required>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="pwd">Hora</label>
                                            <input type="time" class="form-control" name="hora" value="${cita.getHora()}" required>
                                        </div>
                                        
                                        
                                        
                                        <div class="text-center">
                                            <button class="btn btn-primary" data-toggle="modal" data-target="#updateCitaModal">Registrar</button>
                                            <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                        </div>
                                        
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-8 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-list"></i> Lista de Citas</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-container">
                                        <table id="tablaCitas" class="table">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Mascota</th>
                                                    <th>Veterinario</th>
                                                    <th>Servicio</th>
                                                    <th>Fecha</th>
                                                    <th>Hora</th>
                                                    <th>Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="cita" items="${citas}">
                                                    <tr>
                                                        <td>${cita.id_cita}</td>
                                                        <td>${cita.mascota}</td>
                                                        <td>${cita.veterinario}</td>
                                                        <td>${cita.servicio}</td>
                                                        <td>${cita.fecha}</td>
                                                        <td>${cita.hora}</td>
                                                        <td>
                                                            <a class="btn btn-warning" href="svrCitas?accion=Editar&id=${cita.getId_cita()}"><i class="fa fa-pencil"></i></a>
                                                            <a class="btn btn-danger" href="svrCitas?accion=Delete&id=${cita.getId_cita()}"><i class="fa fa-trash"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
                            
            <!-- Modal Actualizar Cirta -->
            <div class="modal fade" id="updateCitaModal" tabindex="-1" aria-labelledby="updateCitaModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateCitaModalLabel">Agregar Cita</h5>
                        </div>
                        <div class="modal-body">
                            <form action="svrCitas" method="POST">
                                <input type="hidden" name="accion" value="agregar" />
                                <div class="form-group">
                                    <label for="mascota">Mascota</label>
                                    <select class="form-control" id="mascota" name="mascota" required>
                                    <c:forEach var="mascota" items="${mascotas}">
                                        <option value="${mascota.getIDUSUARIO()}">
                                        ${mascota.nombre}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div> 
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Veterinario</label>
                                    <select class="form-control" name="veterinario" required>
                                    <c:forEach var="vet" items="${veterinarios}">
                                        <option value="${vet.getId_Veterinario()}">
                                        ${vet.nom}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div> 
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Servicio</label>
                                    <select class="form-control" name="servicio" required>
                                    <c:forEach var="serv" items="${servicios}">
                                        <option value="${serv.getId_Servicio()}">
                                        ${serv.nombre}</option>
                                    </c:forEach>                                                                                     
                                    </select>
                                </div>                                                                                 
                                <div class="form-group">
                                    <label for="pwd">Fecha</label>
                                    <input type="date" class="form-control" name="fecha" value="${cita.getFecha()}" required>
                                </div>

                                <div class="form-group">
                                    <label for="pwd">Hora</label>
                                    <input type="time" class="form-control" name="hora" value="${cita.getHora()}" required>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary">Agregar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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
            
            document.addEventListener("DOMContentLoaded", function() {
                var prodCantidad = <%= Arrays.toString((int[]) request.getAttribute("productosCantidades")) %>;                
                var prodLabel = <%= Arrays.toString((String[]) request.getAttribute("productosLabel")) %>;
                
                var vetLabel = <%= Arrays.toString((String[]) request.getAttribute("vetLabel")) %>;                               
                var vetCantConsult = <%= Arrays.toString((int[]) request.getAttribute("vetCantConsult")) %>;

                var options1 = {
                    series: [{
                    data: prodCantidad
                  }],
                    chart: {
                    type: 'bar',
                    height: 350
                  },
                  plotOptions: {
                    bar: {
                      borderRadius: 4,
                      borderRadiusApplication: 'end',
                      horizontal: false
                    }
                  },
                  dataLabels: {
                    enabled: false
                  },
                  xaxis: {
                    categories: prodLabel
                  }
                  };


                var options2 = {
                    series: vetCantConsult,
                    chart: {
                    width: 380,
                    type: 'polarArea'
                  },
                  labels: vetLabel,
                  fill: {
                    opacity: 1
                  },
                  stroke: {
                    width: 1,
                    colors: undefined
                  },
                  yaxis: {
                    show: false
                  },
                  legend: {
                    position: 'bottom'
                  },
                  plotOptions: {
                    polarArea: {
                      rings: {
                        strokeWidth: 0
                      },
                      spokes: {
                        strokeWidth: 0
                      },
                    }
                  },
                  theme: {
                    monochrome: {
                      enabled: true,
                      shadeTo: 'light',
                      shadeIntensity: 0.6
                    }
                  }
                  };

                var chart = new ApexCharts(document.querySelector("#chart1"), options1);
                chart.render();

                var chart2 = new ApexCharts(document.querySelector("#chart2"), options2);
                chart2.render();
            });
            
        </script>
    </body>
</html>

<%
    } else {
        response.sendRedirect("identificar.jsp");
    }
%>
