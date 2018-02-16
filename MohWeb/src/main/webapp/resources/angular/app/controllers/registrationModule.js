'use strict';

var registrationModule= angular.module('RegistrationModule',[]);

registrationModule.controller('RegistrationController', ['$scope','$http','$location','$rootScope','utilService','PrivilegeService',
    function($scope,$http,$location,$rootScope,utilService,PrivilegeService) {
  		var registrationUrl = "user/registration";
  		
  		if(!$rootScope.isRegistrationAccess ){
  			window.location = "unauthorized.jsp";
  		}
  		
  		$scope.doRegistration = function(User){
  			register(User,false);
  		};
  		$scope.doRegistrationAndAppointment = function(User){
  			register(User,true);
  		};
  		
  		function register(User,appointment){
  			
  			//post ajax data
  			var result = $http.post(registrationUrl, User);	
  			result.success(function(data, status, headers, config){
  				console.log("Registration Success:"+JSON.stringify(data));
  				if(data.errorNo == 0){
  					$scope.registrationResultMessage = data.successMsg;
  					$scope.User = {};
  					$scope.registrationForm.$setPristine();
  					
  					if(appointment){
  						console.log("After Registration , Redirecting to Booking Appointment");
  						$rootScope.patientId = data.userId;
  						$location.path('/bookAppointment/'+data.userId);
  					}
  				}else{
  					$scope.registrationResultMessage = data.errorMsg;
  				}
  			});
  			result.error(function(data, status, headers, config){
  				console.log("Failed:"+data);
  			});
  		}
  		
    }
]);
  