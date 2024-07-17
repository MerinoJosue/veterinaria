<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    if (session.getAttribute("usuario") != null) {
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
                                    <span class="hidden-xs">${vendedor.nombreUsuario}</span>
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
                        <li class="active"><a href="ControladorVeterinario?menu=Veterinario&accion=Listar"><i class="fa fa-link"></i> <span>Veterinarios</span></a></li>
                        <li class="treeview">
                            <a href="#"><i class="glyphicon glyphicon-th-large"></i> <span>Registros</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#"><i class="fa fa-archive"></i>Clientes</a></li>
                                <li><a href="svrCitas?menu=RegistrarCitas&accion=Listar"><i class="fa fa-calendar-check-o"></i> <span>Citas</span></a></li>  
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
                    <h1>Dashboard</h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-user"></i> Cantidades de productos vendidas</h4>
                                </div>
                                <div class="card-body d-flex justify-content-center">
                                    <div id="chart1"></div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-6 mb-4">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h4><i class="fa fa-list"></i> Citas atendidas por veterinario</h4>
                                </div>
                                <div class="card-body d-flex justify-content-center">
                                    <div id="chart2"></div>
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
        response.sendRedirect("../identificar.jsp");
    }
%>
