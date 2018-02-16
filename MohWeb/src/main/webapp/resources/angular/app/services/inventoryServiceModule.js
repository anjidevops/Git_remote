'use strict';

var inventoryServiceModule = angular.module('InventoryServiceModule',[]);

inventoryServiceModule.factory('SharedService',function($rootScope) {

	var sharedService = {};
	sharedService.transData = {};
	
	sharedService.prepForBroadcast = function(data){
		this.transData = data;
	};
	
	sharedService.broadcastItem = function() {
		$rootScope.$broadcast('handleBroadcast');
	}
	
	 return sharedService;
    /*$rootScope.$on('handleEmit', function(event, args) {
        $rootScope.$broadcast('handleBroadcast', args);
    });*/
});
