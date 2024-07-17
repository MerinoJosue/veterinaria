<%@page import="config.Fecha"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Happypet| Inicio</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>

        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">
                <a href="#" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>SH</b>PP</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Sistema </b>Veterinaria</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs">${vendedor.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>                    
                                            Bienvenido - ${vendedor.nombreUsuario}
                                            <small>Usted es Cliente </small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <a href="srvUsuario?accion=cerrar" class="btn btn-default btn-flat">Cerrar Sesion</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Bienvenido, ${vendedor.nombreUsuario}</p>
                            <!-- Status -->
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <!-- search form (Optional) -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->

                    <!-- Sidebar Menu -->
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
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Pagina Principal
                        <small>Veterinaria</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Panel Administrativo</li>
                    </ol>
                </section>
                <br>
                <div class="container-fluid mt-4">
                    <div class="row justify-content-center">
                        <c:forEach var="pr" items="${productos}">
                            <div class="col-lg-4 col-md-4 col-sm-6 mb-4">
                                <div class="card h-100 shadow-lg border-0">
                                    <div class="card-header text-white text-center py-3" style="background-color: #4caf50;">
                                        <h5 class="card-title mb-0 font-weight-bold">${pr.getNombres()}</h5>
                                    </div>
                                    <div class="card-body text-center">
                                        <div class="mb-3">
                                            <span class="h4 text-success font-weight-bold"><i class="fas fa-dollar-sign"></i>${pr.getPrecio()}</span>
                                        </div>
                                        <img src="ControladorImg?id=${pr.getId_Producto()}" class="img-fluid rounded mb-3"style="height: 170px; object-fit: cover;">
                                        <p class="text-muted">${pr.getDescripcion()}</p>
                                    </div>
                                    <div class="card-footer bg-light text-center">
                                        <a href="Controlador?accion=AgregarCarrito&id=${pr.getId_Producto()}" class="btn btn-outline-primary btn-sm mr-2">
                                            Agregar a Carrito <i class="fas fa-cart-plus"></i>
                                        </a>
                                        <a href="Controlador?accion=Comprar&id=${pr.getId_Producto()}" class="btn btn-danger btn-sm">
                                            Comprar
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <style>
                    .card {
                        transition: transform 0.2s ease-in-out;
                    }
                    .card:hover {
                        transform: scale(1.05);
                    }
                    .bg-gradient-primary {
                        background: linear-gradient(45deg, #007bff, #0056b3);
                    }
                </style>
                <!-- /.content-wrapper -->  
                <div class="control-sidebar-bg"></div>
            </div>
               <!-- Main Footer -->
                <footer class="main-footer">
                    <!-- To the right -->
                    <div class="pull-right hidden-xs">
                        PetFrendly
                    </div>
                    <!-- Default to the left -->
                    <strong>HappyPet<a href="#"></a>.</strong> Todos los derechos reservados.
                </footer>
            <!-- ./wrapper -->

            <!-- REQUIRED JS SCRIPTS -->

            <!-- jQuery 3 -->
            <script src="bower_components/jquery/dist/jquery.min.js"></script>
            <!-- Bootstrap 3.3.7 -->
            <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
            <!-- AdminLTE App -->
            <script src="dist/js/adminlte.min.js"></script>



            <!-- Optionally, you can add Slimscroll and FastClick plugins.
                 Both of these plugins are recommended to enhance the
                 user experience. -->
    </body>
</html>
