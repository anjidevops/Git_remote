app.service('PrivilegeService',function(utilService,$rootScope){
	
	$rootScope.isRegistrationAccess =  utilService.isElementPresent("user_registration",privldges2_) ;
	
});