<%@ taglib uri="/WEB-INF/tld/mohSecurity.tld" prefix="moh" %>
<!DOCTYPE html>
 
<html lang="en" ng-app="mohStarterApp">
<head>
	<script>
		var roles_ = <moh:roles />;
		
		var privldges1_ = <moh:privs group="1" />;
		var privldges2_ = <moh:privs group="2" />;
	</script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Starter Health Care</title>
    <meta name="description" content="Online resource tailored to the needs of  healthcare professionals that provides access to resources and educational programs, along with guidelines for AstraZeneca medicines.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/app.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-simplex.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootswatch.min.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/animations.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css"> 
  
  <!-- CDN library dependencies -->  
 <!--  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-route.min.js"></script> -->

    
</head>
<body>
  <div class="container">
    <div ng-include="'header.html'"></div>
    <div ng-if="loggedUser" ng-include="'welcome-strip.html'"></div>
    <div ng-if="loggedUser" class="row dashboard">
      <div class="col-md-3">
        <div ng-include="'left-section.html'"></div>
      </div>
      <div class="col-md-9 text-center">
          <div ng-view></div>
      </div>
    </div>
    <div ng-if="!loggedUser" ng-view></div>
    <div ng-if="loggedUser" ng-include="'footer.html'"></div>
  </div> 
   
  <!-- local dependencies for working in intranet -->
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/angular.min-1.3.5.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/angular-route.min-1.3.5.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/angular-cookies-1.3.5.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/ui-bootstrap-tpls-0.12.0.js"></script>

  <script src="${pageContext.servletContext.contextPath}/js/app/app.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/services/inventoryServiceModule.js"></script>
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/app/services/xhrService.js"></script>
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/app/services/utilService.js"></script>
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/app/directives/drag-me.js"></script>
  
  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/app/services/userPrivileges.js"></script>
  	   
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/loginModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/registrationModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/adminModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/patientModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/appointmentModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/inventoryModule.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/patientRecordModule.js"></script>
</body>
</html>









