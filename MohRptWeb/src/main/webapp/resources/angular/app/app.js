'use strict';

/* App Module */
var app = angular.module('DoctorAppointment', [
		'ngRoute','ReportCtrls'
	   ]); 

app.config([ '$routeProvider', 
		function ($routeProvider) {
			console.log("route Info:"+$routeProvider);
			  
			$routeProvider.
			when('/openDoctorAppointments', {
			  templateUrl: 'doctorReportList.html',	
			  controller: 'ReportController'
			});
			
		} 
	]);

app.constant({ 
	"WebServiceServerUrl": "http://localhost:8080/MohRptWeb/"
	});

