'use strict';

var loginModule= angular.module('LoginModule',[]);

loginModule.controller('LoginController', ['$scope','$http','$location','$rootScope','xhrService','$cookieStore',
 function($scope,$http,$location,$rootScope,xhrService,$cookieStore) {
   var loginUrl = "login";
   $scope.doLogin = function(User){
     if(User && User.loginId.length > 0 && User.password.length > 0){
      xhrService.post(loginUrl,User).then(function(response){
      if(response.loginSuccess == true){
        $rootScope.userId = response.userId;
        $cookieStore.put('user',response);
        
        if(response.userRole.toLowerCase() == "admin" || response.userRole.toLowerCase() == "doctor"){
          $location.path('/admin');
        }else{
          $location.path('/patient');
        }
      }else{
        $scope.loginMessage ="Invalid Credentials";
      }       
    },function(error){
      console.log('error');      
    });
    }	
 	};	
 }
]);
