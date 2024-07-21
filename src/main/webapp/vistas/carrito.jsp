<%@page import="config.Fecha"%>
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
        <title>Sistema Happypet | Inicio</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

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
                                    <span class="hidden-xs">Invitado</span>
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





                <section>
                    <div class="container mt-4">
                        <div class="d-flex">
                            <h2>Carrito</h2>     
                            <%
                                Fecha fechaSistema = new Fecha();
                            %>                     
                            <p class="ml-auto"><%= fechaSistema.Fecha()%></p>                               
                        </div>                    
                        <div class="row">             
                            <div class="col-lg-9">                 
                                <table class="table">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                            <th>Item</th>                               
                                            <th>Articulo</th>
                                            <th>Descripcion</th>
                                            <th>Precio</th>
                                            <th>Cantidad</th>                       
                                            <th>Total</th>                       
                                            <th>Acciones</th>                       
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="c" items="${Carrito}"> 
                                            <tr class="text-center tr">
                                                <td>${c.getItem()}</td>                                   
                                                <td>${c.getNombres()}
                                                    <img src="ControladorImg?id=${c.getId_Producto()}" width="130" height="110">
                                                </td>
                                                <td>${c.getDescripcion()}</td>
                                                <td>${c.getPrecioCompra()}</td>
                                                <td>        
                                                    <input type="hidden" id="item1" value="${c.getId_Producto()}">
                                                    <input type="number" min="1" max="10"  id="Cant" class=" form-control text-center" value="${c.getCantidad()}">
                                                </td>   
                                                <td>${c.getSubTotal()}</td>                           
                                                <td class="text-center">                                         
                                                    <input type="hidden" id="item2" value="${c.getId_Producto()}">
                                                    <a id="deleteItem" href="#" class="btn"><i class="fas fa-trash-alt"></i></a>                                            
                                                </td>                           
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>                    
                            </div>
                            <div class="col-lg-3">                  
                                <div class="card">
                                    <div class="card-header">
                                        Generar Compra
                                    </div>
                                    <div class="card-body">
                                        <label>Subtotal:</label>
                                        <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> ${totalPagar}0</i></a>
                                        <label>Precio Envio:</label>
                                        <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> 0</i></a>
                                        <label>IGV:</label>
                                        <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> 18</i></a>
                                        <label>Total a Pagar:</label>
                                        <a class="form-control text-center"><i class="fas fa-dollar-sign h4 primary"> ${totalPagar}</i></a>
                                    </div>
                                    <div class="card-footer">
                                        <br>
                                        <a class="dropdown-item" href="Controlador?accion=RealizarPago">Realizar Pago</a>
                                        <!-- Mostrar mensaje si está presente -->
                                        <c:if test="${not empty mensaje}">
                                            <div class="alert alert-info">${mensaje}</div>
                                        </c:if>
                                    </div>
                                    <div class="card-footer">
                                        <a class="dropdown-item" href="Controlador?accion=GenerarCompra">Generar Compra</a>
                                    </div>
                                </div>
                            </div>
                        </div>          
                    </div>
                </section>
 <script>
                    window.addEventListener('mouseover', initLandbot, {once: true});
                    window.addEventListener('touchstart', initLandbot, {once: true});
                    var myLandbot;
                    function initLandbot() {
                        if (!myLandbot) {
                            var s = document.createElement('script');
                            s.type = 'text/javascript';
                            s.async = true;
                            s.addEventListener('load', function () {
                                var myLandbot = new Landbot.Livechat({
                                    configUrl: 'https://storage.googleapis.com/landbot.online/v3/H-2560099-7S2PZU0T80D5OGBE/index.json',
                                });
                            });
                            s.src = 'https://cdn.landbot.io/landbot-3/landbot-3.0.0.js';
                            var x = document.getElementsByTagName('script')[0];
                            x.parentNode.insertBefore(s, x);
                          }
                    }
                </script>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
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

        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->

        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>

        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>

    </body>


</html>
