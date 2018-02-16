'use strict';

var adminModule= angular.module('AdminModule',[]);

adminModule.controller('AdminController', ['$scope','$http','$location','$rootScope', '$filter', '$cookieStore','xhrService','utilService','PrivilegeService',
  function($scope,$http,$location,$rootScope,$filter, $cookieStore,xhrService,utilService,PrivilegeService) {
     $scope.showSearchResult = false; 
     var searchUrl = "user/search"; 
     var searchedUser = {};
     $scope.showAppointmentColumns = false;
     $scope.user = $cookieStore.get('user');
     $scope.someThing = 'myPrecious';
     
     $scope.doSearch = function(searchText){
         var searchedUrl = searchUrl+"?searchText="+searchText;
         

         xhrService.get(searchedUrl).then(function(response){
             $scope.showSearchResult = true; 

             angular.forEach(response.result, function(value,key){
                if(value.admin !== true)
                    $scope.showAppointmentColumns = true;
            });
             if(response.result.length){
                $scope.isUser = true;
                $scope.searchedUserList = response.result;
            }else {
                $scope.isUser = false;
            } 
        },function(error){
         console.log("failed");
     });
     };
     $scope.status = "Not sent";
  $scope.sendMail = function(status) {
    console.log(status)
    $scope.status = "Sent";
  };

        }]);
