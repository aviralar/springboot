<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
		
	<head>
		  <title>Pravah</title>
	      <meta charset="utf-8">
	      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	      <script  type="text/javascript">var _contextPath = "${pageContext.request.contextPath}";</script>
	      <c:url value="/css/bootstrap.min.css" var="bootstrapCss" />
	      <c:url value="/js/jquery.min.js" var="jquery" />
	      <c:url value="/js/bootstrap.min.js" var="bootstrapJs" />
	      <c:url value="/js/angular.min.js" var="angularJs" />
	      <c:url value="/js/zingchart.min.js" var="zingchartJs" />
	      <c:url value="/css/main.css" var="main"/>
	      <c:url value="/images" var="images" scope="application" />
	      <link rel="stylesheet" href="${bootstrapCss}">
	      <script src="${jquery}"></script>
	      <script src="${bootstrapJs}"></script>
	      <script src="${angularJs}"></script>
	      
	      <!-- Main CSS -->
	      <link rel="stylesheet" href="${main}">
	</head>
</html>