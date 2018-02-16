'use strict';

var patientModule = angular.module('PatientModule',[]);

patientModule.controller('PatientController', ['$scope','$http','$rootScope','xhrService','utilService','PrivilegeService',
	function($scope,$http,$rootScope,xhrService,utilService,PrivilegeService) {
	   var appointmentHistoryUrl = "user/visits";
	   var appointmentHistoryGetUrl = appointmentHistoryUrl+"?patientId="+$rootScope.loggedUser.userId;
	   
	   xhrService.get(appointmentHistoryGetUrl).then(function(response){
		   $scope.appointmentHistory = response.appointments;
	   },function(error){
	   	   console.log("get ApponintmentHistory failed");
	   });
   }
]);