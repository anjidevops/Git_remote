'use strict';

var controllers = angular.module('ReportCtrls',[]);

//MedicineListController is used to  Get the List of Medicine
controllers.controller('ReportController', ['$scope','$http','$location','WebServiceServerUrl',
     function($scope,$http,$location,WebServiceServerUrl) {
	
      var url = WebServiceServerUrl+"doctorReport/getNumberAptsPerDoctor";
      $scope.list = [];
      var result = $http.post(url);
    
		   result.success(function(data, status, headers, config) {
				
				if(data.userCountList != null && data.userCountList.length>0){
					$scope.reportList = data.userCountList;
				}
			});
		   result.error(function(data,status,headers,config){
				console.log("Failed called");	
			});
    }]);

	

	
