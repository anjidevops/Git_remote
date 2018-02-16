<!DOCTYPE html>
 
<html lang="en" ng-app="DoctorAppointment">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Starter Health Care</title>
    <meta name="description" content="Online resource tailored to the needs of  healthcare professionals that provides access to resources and educational programs, along with guidelines for AstraZeneca medicines.">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/app.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/animations.css">
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css"> 
  
  <!-- CDN library dependencies -->	 
 <!--  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-route.min.js"></script> -->
  
  <!-- local dependencies for working in intranet -->
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/jquery-1.10.2.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/angular.min-1.3.5.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/staticContents/js/angular-route.min-1.3.5.js"></script>
   <script src="${pageContext.servletContext.contextPath}/js/app/app.js"></script>
  <script src="${pageContext.servletContext.contextPath}/js/app/controllers/doctorReportcontroller.js"></script>
  

</head>
<body>
	
    <header class="header">
        
    </header>

    <main class="main landing-page" role="main">
        

            <div class="row latest-updates">
                
					<a href="#/openDoctorAppointments">Open Doctor Appointments</a>
					<div class="view-container">
						<div ng-view class="view-frame"></div>
				    </div>
                
            </div>
        
    </main>
    
    <footer id="footer">
       
    </footer>   
</body>
</html>
