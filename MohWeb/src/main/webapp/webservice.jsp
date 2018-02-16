<html>
<head>
<title>Webservice</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
		$(document).ready(function() {
			// Webservice 1
			$.ajax({
				type : "GET",
				url : "rest/getAllRoles",
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#getAllRoles").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#getAllRoles").html(JSON.stringify(data));
				}
			});
			// Webservice 2
			$.ajax({
				type : "GET",
				url : "rest/getAllPrivileges",
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#getAllPrivileges").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#getAllPrivileges").html(JSON.stringify(data));
				}
			});
			// Webservice 2-1
			$.ajax({
				type : "GET",
				url : "rest/getAllPrivileges/1",
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#getAllPrivileges1").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#getAllPrivileges1").html(JSON.stringify(data));
				}
			});
			// Webservice 2-2
			$.ajax({
				type : "GET",
				url : "rest/getAllPrivileges/2",
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#getAllPrivileges2").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#getAllPrivileges2").html(JSON.stringify(data));
				}
			});
			// Webservice 3
			$.ajax({
				type : "GET",
				url : "rest/hasPrivilege/department_all_doctor_by_department",
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#hasPrivilege").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#hasPrivilege").html(JSON.stringify(data));
				}
			});
			// Webservice 4
			var arr = ["doctor_appointment_details","appointment_params","department_all_doctor_by_department"];
			var input = JSON.stringify(arr);
			$.ajax({
				type : "POST",
				url : "rest/hasAllPrivileges",
				data : input,
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
				dataType : "json",
				cache : false,
				success : function(data) {
					$("#hasAllPrivileges").html(JSON.stringify(data));
				},
				error : function(data) {
					$("#hasAllPrivileges").html(JSON.stringify(data));
				}
			});
		});
	</script>
</head>
<body>
	<h2>Webservice call outputs:</h2>
	GET: http://localhost:8080${pageContext.servletContext.contextPath}/rest/getAllRoles
	<div id="getAllRoles" style="border: 1px solid #EEEEEE; padding: 10px; background: #AABBCC;"></div>
	GET: http://localhost:8080${pageContext.servletContext.contextPath}/rest/getAllPrivileges
	<div id="getAllPrivileges" style="border: 1px solid #EEEEEE; padding: 10px; overflow: auto; background: #AABBCC;"></div>
	GET: http://localhost:8080${pageContext.servletContext.contextPath}/rest/getAllPrivileges/1
	<div id="getAllPrivileges1" style="border: 1px solid #EEEEEE; padding: 10px; overflow: auto; background: #AABBCC;"></div>
	GET: http://localhost:8080${pageContext.servletContext.contextPath}/rest/getAllPrivileges/2
	<div id="getAllPrivileges2" style="border: 1px solid #EEEEEE; padding: 10px; overflow: auto; background: #AABBCC;"></div>
	GET: http://localhost:8080${pageContext.servletContext.contextPath}/rest/hasPrivilege/department_all_doctor_by_department
	<div id="hasPrivilege" style="border: 1px solid #EEEEEE; padding: 10px; background: #AABBCC;"></div>
	POST: http://localhost:8080${pageContext.servletContext.contextPath}/rest/hasAllPrivileges(["doctor_appointment_details","appointment_params","department_all_doctor_by_department"])
	<div id="hasAllPrivileges" style="border: 1px solid #EEEEEE; padding: 10px; background: #AABBCC;"></div>
</body>
</html>