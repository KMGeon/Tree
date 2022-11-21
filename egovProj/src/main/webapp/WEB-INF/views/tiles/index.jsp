<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>  
<!DOCTYPE html>
<html class>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/resources/adminlte/dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/summernote/summernote-bs4.min.css">
  <!-- datatables -->
  <link rel="stylesheet" href="/resources/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="/resources/adminlte/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="/resources/adminlte/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<!-- <body class="hold-transition sidebar-mini layout-fixed"> -->
<body class="sidebar-mini sidebar-closed sidebar-collapse">
<div class="wrapper">

  <!-- Preloader -->
  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="/resources/adminlte/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
  </div>

  <!-- =========header 시작 Navbar ================== -->
  <tiles:insertAttribute name="header" />
  <!-- =========header 끝 /.navbar ================== -->

  <!-- =========== aside 시작 Main Sidebar Container ================ -->
  <tiles:insertAttribute name="aside" />
  <!-- =========== aside 끝 Content Wrapper. Contains page content  ================ -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${bodyTitle}</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- ======== body 시작 Main content =========== -->
    <tiles:insertAttribute name="body" />
    <!-- ======== body 끝 /.content =========== -->
  </div>
  <!-- ========== footer 시작 /.content-wrapper =============== -->
  <tiles:insertAttribute name="footer" />
  <!-- ========== footer 끝 Control Sidebar ================ -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="/resources/adminlte/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/resources/adminlte/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="/resources/adminlte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="/resources/adminlte/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="/resources/adminlte/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="/resources/adminlte/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="/resources/adminlte/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="/resources/adminlte/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="/resources/adminlte/plugins/moment/moment.min.js"></script>
<script src="/resources/adminlte/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="/resources/adminlte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="/resources/adminlte/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="/resources/adminlte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/adminlte/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/resources/adminlte/dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="/resources/adminlte/dist/js/pages/dashboard.js"></script>
<!-- datatables -->
<script src="/resources/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="/resources/adminlte/plugins/jszip/jszip.min.js"></script>
<script src="/resources/adminlte/plugins/pdfmake/pdfmake.min.js"></script>
<script src="/resources/adminlte/plugins/pdfmake/vfs_fonts.js"></script>
<script src="/resources/adminlte/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="/resources/adminlte/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
    });
  });
</script>
</body>
</html>
