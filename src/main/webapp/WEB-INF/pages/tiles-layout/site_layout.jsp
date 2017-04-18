<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- favicon  -->
<link rel="icon" type="image/png" sizes="32x32" href="static/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="static/images/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="static/images/favicon-16x16.png">
<link rel='shortcut icon' type='image/x-icon' href='static/images/favicon.ico'> <!-- IE -->
<link rel='apple-touch-icon' type='image/png' href='static/images/apple-icon-57x57.png'> <!-- iPhone -->
<link rel='apple-touch-icon' type='image/png' sizes='72x72' href='static/images/apple-icon-57x57.png'> <!-- iPad -->
<link rel='apple-touch-icon' type='image/png' sizes='114x114' href='static/images/apple-icon-114x114.png'> <!-- iPhone4 -->

<!-- Bootstrap  -->
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

<!-- css/stylesheet -->
<link rel="stylesheet" href="static/css/styles.css">

<!-- lumino stylesheet in csshome  -->
<link rel="stylesheet" href="static/csshome/bootstrap-table.css">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.css">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.css.map">
<link rel="stylesheet" href="static/csshome/bootstrap-theme.min.css">
<link rel="stylesheet" href="static/csshome/bootstrap.css">
<link rel="stylesheet" href="static/csshome/bootstrap.css.map">
<link rel="stylesheet" href="static/csshome/bootstrap.min.css">
<link rel="stylesheet" href="static/csshome/datepicker.css">
<link rel="stylesheet" href="static/csshome/datepicker3.css">
<link rel="stylesheet" href="static/csshome/styles.css">

<!-- jquery -->
<script src="webjars/jquery/2.2.3/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- load kalrav.js after jquery -->
<script type="text/javascript" src="static/js/kalrav.js"></script>

<!-- lumino javascript in js  -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="static/js/bootstrap.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/chart-data.js"></script>
<script type="text/javascript" src="static/js/chart-min.js"></script>
<script type="text/javascript" src="static/js/easypiechart-data.js"></script>
<script type="text/javascript" src="static/js/easypiechart.js"></script>
<script type="text/javascript" src="static/js/html5shiv.min.js"></script>
<script type="text/javascript" src="static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="static/js/kalrav.glyphs.js"></script>
<script type="text/javascript" src="static/js/lumino.glyphs.js"></script>
<script type="text/javascript" src="static/js/respond.js"></script>

<!-- glyph icons from https://glyphs.co/docs/getting-started/installation  -->
<script type="text/javascript" src="//kit.glyphs.co/4uq709.js"></script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>

<body>
	<!-- header -->
	<div id="site_header">
		<tiles:insertAttribute name="header" />
	</div>
	<!-- Menu Page -->
	<div id="site_menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<!-- body -->
	<div id="site_body">
		<tiles:insertAttribute name="body" />
	</div>
	<!-- footer -->
	<div id="site_footer">
		<tiles:insertAttribute name="footer" />
	</div>
	
</body>
</html>