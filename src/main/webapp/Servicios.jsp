<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("usuario") != null) {
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Bodega | Inicio</title>
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
            <!-- Main Header -->
            <header class="main-header">
                <a href="#" class="logo">
                    <span class="logo-mini"><b>S</b>BL</span>
                    <span class="logo-lg"><b>Sistema </b>HappyPet</span>
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
                                    <span class="hidden-xs"> ${usuario.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>                    
                                            Bienvenido - ${usuario.nombreUsuario}
                                            <small>Usted es, ${usuario.cargo.nombreCargo} </small>
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
                            <p>Bienvenido, ${usuario.nombreUsuario} </p>
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
                        <li class="active"><a href="ControladorVeterinario?menu=Veterinario&accion=Listar"><i class="fa fa-link"></i> <span>Veterinarios</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-archive"></i>Clientes</a></li>
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
                </section>
            </aside>

            <div class="content-wrapper">
                <div class="row">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card">
                            <div class="card-header text-center">
                                <h4><i class="fa fa-user-plus"></i> Registrar Servicio</h4>
                            </div>
                            <div class="card-body">
                                <form action="ControladorServicios?menu=Servicios" method="POST">
                                    <input type="hidden" name="menu" value="Servicios" />
                                    <input type="hidden" name="id" value="${servicio.id_Servicio}" />
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input type="text" name="txtNombre" value="${servicio.nombre}" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>Precio</label>
                                        <input type="text" name="txtPrecio" value="${servicio.precio}" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label>Descripcion</label>
                                        <input type="text" name="txtDescripcion" value="${servicio.descripcion}" class="form-control">
                                    </div>
                                    <div class="text-center">
                                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-6 mb-4">
                        <div class="card">
                            <div class="card-header text-center">
                                <h4><i class="fa fa-list"></i> Lista de Servicios</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-container">
                                    <div class="mb-3">
                                        <input type="text" id="searchInput" class="form-control" placeholder="Buscar servicio...">
                                    </div>
                                    <table id="tablaServicios" class="table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Nombre</th>
                                                <th>Precio</th>
                                                <th>Descripcion</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="sv" items="${servicios}">
                                                <tr>
                                                    <td>${sv.id_Servicio}</td>
                                                    <td>${sv.nombre}</td>
                                                    <td>${sv.precio}</td>
                                                    <td>${sv.descripcion}</td>
                                                    <td>
                                                        <a class="btn btn-warning" href="ControladorServicios?menu=Servicios&accion=Editar&id=${sv.id_Servicio}"><i class="fa fa-pencil"></i></a>
                                                        <a class="btn btn-danger" href="ControladorServicios?menu=Servicios&accion=Delete&id=${sv.id_Servicio}"><i class="fa fa-trash"></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <a class="btn btn-info" data-toggle="modal" data-target="#addServiceModal"><i class="fa fa-user-plus"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <!-- Modal Agregar Servicio -->
            <div class="modal fade" id="addServiceModal" tabindex="-1" aria-labelledby="addServiceModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addServiceModalLabel">Agregar Servicio</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="ControladorServicios?menu=Servicios" method="POST">
                                <input type="hidden" name="menu" value="Servicios" />
                                <div class="form-group">
                                    <label>Nombre</label>
                                    <input type="text" name="txtNombre" value="${servicio.getNombre()}" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Precio</label>
                                    <input type="text" name="txtPrecio" value="${servicio.getPrecio()}" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Descripcion</label>
                                    <input type="text" name="txtDescripcion" value="${servicio.getDescripcion()}" class="form-control">
                                </div>
                                <div class="text-center">
                                    <input type="submit" name="accion" value="Agregar" class="btn btn-info">
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

        <!-- jQuery 3 -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- DataTables -->
        <script src="https://cdn.datatables.net/1.11.6/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            $(document).ready(function () {
                // Inicializar la tabla de servicios
                $('#tablaServicios').DataTable();

                // Agregar funcionalidad de búsqueda
                $('#searchInput').on('keyup', function () {
                    $('#tablaServicios').DataTable().search($(this).val()).draw();
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
