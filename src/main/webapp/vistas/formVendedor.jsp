<%@page import="config.Fecha"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sistema Happypet | Inicio</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <!-- CSS dependencies -->
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <!-- Main Header -->
            <header class="main-header">
                <a href="#" class="logo">
                    <span class="logo-mini"><b>SH</b>PP</span>
                    <span class="logo-lg"><b>Sistema</b> Veterinaria</span>
                </a>

                <!-- Navbar -->
                <nav class="navbar navbar-static-top">
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
                                        <p>Bienvenido - ${vendedor.nombreUsuario}<small>Usted es Cliente</small></p>
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

            <!-- Sidebar -->
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
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>

                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">INICIO</li>
                        <li class="active"><a href="Controlador?accion=home"><i class="fa fa-cart-arrow-down"></i> <span>Catálogo</span></a></li>
                        <li class="active"><a href="Controlador?accion=MisCompras"><i class="fa fa-cart-arrow-down"></i> <span>Mis Compras</span></a></li>
                        <li class="active"><a href="srvMascota?menu=RegistrarMascota&accion=Listar"><i class="fa fa-tags"></i> <span>Mis Mascotas</span></a></li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controlador?accion=carrito"><i class="fas fa-cart-plus">(<label style="color: darkorange">${cont}</label>)</i> Carrito</a>
                        </li>
                    </ul>
                </section>
            </aside>

            <!-- Content Wrapper -->
            <div class="content-wrapper">
                <section class="content">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Bienvenidos a Nuestra Veterinaria</h3>
                        </div>
                        <div class="box-body">
                            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                </ol>
                                <!-- Slides -->
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img src="../reportes/pexels-helen1-16395147.jpg" alt="First slide">
                                    </div>
                                    <div class="item">
                                        <img src="../reportes/pexels-ingewallu-126407.jpg" alt="Second slide">
                                    </div>
                                    <div class="item">
                                        <img src="../reportes/pexels-pixabay-160846.jpg" alt="Third slide">
                                    </div>
                                </div>
                                <!-- Controls -->
                                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>

                    <!-- About Us Section -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Sobre Nosotros</h3>
                        </div>
                        <div class="box-body">
                            <p>HappyPet es una veterinaria dedicada al cuidado de tu mascota, ofreciendo diferentes tipos de servicios como: “Higiene, asesorías, ventas de productos y control de su mascota”. La veterinaria abrió sus puertas a inicios del 2024 hasta hoy, con el mejor equipo médico, y los mayores descuentos a sus clientes.</p>
                        </div>
                    </div>

                    <!-- Mission Section -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Misión</h3>
                        </div>
                        <div class="box-body">
                            <p>En veterinaria HappyPet se vela por el bienestar de sus mascotas, brindando un servicio con gran calidez humana y técnica a través de la prestación de servicios médicos veterinarios altamente especializados.</p>
                        </div>
                    </div>

                    <!-- Vision Section -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Visión</h3>
                        </div>
                        <div class="box-body">
                            <p>HappyPet busca ser una clínica veterinaria modelo, teniendo una capacitación constante a su personal, para brindar una atención enfocada en la satisfacción del cliente y el bienestar de su mascota, buscando ser líderes indiscutibles, en servicios médicos veterinarios, contando con instalaciones, equipos médicos altamente calificados.</p>
                        </div>
                    </div>

                    <!-- Product Section -->
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
                                            <img src="ControladorImg?id=${pr.getId_Producto()}" class="img-fluid rounded mb-3" style="height: 170px; object-fit: cover;">
                                            <p class="text-muted">${pr.getDescripcion()}</p>
                                        </div>
                                        <div class="card-footer bg-light text-center">
                                            <a href="Controlador?accion=AgregarCarrito&id=${pr.getId_Producto()}" class="btn btn-outline-primary btn-sm mr-2">
                                                Agregar a Carrito <i class="fas fa-cart-plus"></i>
                                            </a>
                                            <a href="Controlador?accion=Comprar&id=${pr.getId_Producto()}" class="btn btn-danger btn-sm">Comprar</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </section>
            </div>

            <!-- Footer -->
            <footer class="main-footer">
                <div class="pull-right hidden-xs">PetFriendly</div>
                <strong>HappyPet</strong>. Todos los derechos reservados.
            </footer>

            <!-- Background for control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>

        <!-- JavaScript dependencies -->
        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="dist/js/adminlte.min.js"></script>
    </body>
</html>
