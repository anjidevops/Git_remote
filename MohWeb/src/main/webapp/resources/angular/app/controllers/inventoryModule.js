'use strict';

var inventoryModule = angular.module('InventoryModule',[]);

//MedicineListController is used to  Get the List of Medicine
inventoryModule.controller('MedicineListController', ['$scope','$http','$location','SharedService','$cookieStore',
     function($scope,$http,$location,SharedService, $cookieStore) {
      var medListUrl = "inventory/getMedicineList";
      $scope.medList = [];
      var result = $http.get(medListUrl);
      result.success(function(data, status, headers, config) {
        console.log(data);
			if(data.list != null && data.list.length>0){
				$scope.medList = data.list;
			}
	  });
	  result.error(function(data,status,headers,config) {
		console.log("Failed called");	
	  });
	  
     $scope.doSearch = function(searchText){
     	 $scope.medList = [];
 	     var result = $http.get(medListUrl, 
				   { params: {
						  searchText : searchText   
					 }
				   });
 	  
	    result.success(function(data, status, headers, config) {
			if(data.list.length>0){
			   $scope.medList = data.list;
			}
		});
	    result.error(function(data,status,headers,config){
			console.log("Failed called");	
		});
    };   	

	$scope.addMedicineInventory = function(){
		var objData = {};
		SharedService.prepForBroadcast(objData);
		$location.path('/addEditMedicine');
	};	
	
	$scope.editMedicineInventory = function(objData){
	   	SharedService.prepForBroadcast(objData);
		$location.path('/addEditMedicine');
   	};
   	$scope.sendInventoryReport=function(){
		$location.path('/sendReport');
	};

 }]);

inventoryModule.controller('AddEditMedicineController', ['$scope','$http','$location','SharedService',
        function($scope,$http,$location,SharedService) {

       	var addEditMedUrl = "inventory/addEditMedicine";
       	$scope.medMode= "Add Medicine";
   		if(SharedService.transData && SharedService.transData.id>0){
   			$scope.Med = SharedService.transData;
   			$scope.medMode= "Edit Medicine";
   		}
       	
       	$scope.addEditMed = function(Med){
       		if($scope.medMode=='Add Medicine'){
       			Med.id=0;
       		}
       		
       		if(Med && Med.medicineName.length > 0){
       			
       			var result = $http.post(addEditMedUrl, Med);
       			result.success(function(data, status, headers, config) {
       				if(data.errorNo == 0){
       					if($scope.medMode == 'Add Medicine'){
       						$scope.medSuccessMessage = data.successMsg;
       		       		}else{
       		       		$scope.medSuccessMessage = 'Inventory Edited';
       		       		}
       					
       					if(data.id != null && data.id > 0){
       						Med.id = data.id;
       						$scope.medMode= "Edit Medicine"
       					}
       					$scope.medErrorMessage = "";
    				}else{
    					$scope.medErrorMessage = data.errorMsg;
    					$scope.medSuccessMessage = "";
    				}
       			});
       			result.error(function(data,status,headers,config){
       				console.log("Failed called");	
       			});
       		}else{
       			console.log("INVALID");
       		}	
       		
       	};	
       	
       	$scope.clearData = function(){
       		$scope.Med = {};
       		$scope.medMode = "Add Medicine";
       		$scope.medForm.$setPristine();
       	}
       	
       	$scope.cancelAddMed = function(){
       		 $location.path('/getMedicineList');
       	}
}]);
       	inventoryModule.controller('sendReportController', ['$scope','$http','$location','SharedService',
       	        function($scope, $http, $location, SharedService) {
       	        var addEditMedUrl = "inventory/sendReport";
       	        var result = $http.post(addEditMedUrl);
       	        result.success(function(data, status, headers, config) {
       	        if (data.errorNo == 0) {
       	        console.log("success...");
       	        $scope.medSuccessMessage = data.successMsg;
       	        $scope.medErrorMessage = "";
       	        } else {
       	        $scope.medErrorMessage = data.errorMsg;
       	        $scope.medSuccessMessage = "";
       	        }
       	       });
       	       result.error(function(data, status, headers, config) {
       	       console.log("Failed called");
       	       });
       	}
       	]);

	
