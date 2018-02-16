'use strict';

/* App Module */
var app = angular.module('mohStarterApp', [ 'ngRoute', 'ngCookies', 'LoginModule', 'RegistrationModule', 'AdminModule', 'PatientModule',
		'AppointmentModule', 'InventoryModule', 'PatientRecordModule', 'InventoryServiceModule', 'ui.bootstrap' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'login.html',
		controller : 'LoginController'
	}).when('/registration', {
		templateUrl : 'registration.html',
		controller : 'RegistrationController'
	}).when('/admin', {
		templateUrl : 'admin.html',
		controller : 'AdminController'
	}).when('/patient', {
		templateUrl : 'patient.html',
		controller : 'PatientController'
	}).when('/bookAppointment/:userId', {
		templateUrl : 'bookAppointment.html',
		controller : 'BookAppointmentController'
	}).when('/getMedicineList', {
		templateUrl : 'medicineList.html',
		controller : 'MedicineListController'
	}).when('/addEditMedicine', {
		templateUrl : 'addEditMedicine.html',
		controller : 'AddEditMedicineController'
	}).when('/prescriptionDetails/:appointmentId', {
		templateUrl : 'prescriptionDetails.html',
		controller : 'ViewPatientRecordController'
	}).when('/sendReport', {
		templateUrl : 'medicineList.html',
		controller : 'sendReportController'
	}).when('/notificationToPatient', {
		templateUrl : 'notificationToPatient.html',
		controller : 'NotificationToPatientController'
	}).when('/createPatientRecord', {
		templateUrl : 'createPatientRecord.html',
		controller : 'CreatePatientRecordController'
	}).when('/appSettingsById', {
		templateUrl : 'appSettingsById.html',
		controller : 'AppSettingsByIdController'
	}).when('/searchAppointment', {
		templateUrl : 'appointmentSearchResult.html',
		controller : 'AppointmentSearchResultController'
	}).otherwise({
		redirectTo : '/login'
	});

} ]).run(
		function($rootScope, $route, $http, $location, $cookieStore, $window, $timeout) {
			$rootScope.$on('$routeChangeSuccess', function() {
				if ($location.path() !== '/login') {
					if ($cookieStore.get('user')) {
						$rootScope.loggedUser = $cookieStore.get('user');

						if ($location.path() == '/admin' && $cookieStore.get('user').userRole.toLowerCase() != "admin") {
							// user loggedIn as patient AND hit http://localhost:8080/MohWeb/#/admin url ,
							// based on roletype , redirect to proper page.
							$location.path('/patient');
						} /*
							 * else if($location.path() == '/patient' && $cookieStore.get('user').userRole.toLowerCase() != "patient" ){
							 * //redirect to proper page. }
							 */
					} else {
						if ($rootScope.loggingOut == false) {
							logoutAndGotoLoginPage();
						}
					}
				} else if ($location.path() === '/login') {
					if ($cookieStore.get('user')) {
						if ($cookieStore.get('user').userRole.toLowerCase() == "admin"
								|| $cookieStore.get('user').userRole.toLowerCase() == "doctor")
							$location.path('/admin');
						else if ($cookieStore.get('user').userRole.toLowerCase() == "patient")
							$location.path('/patient');
						else
							$location.path('/patient');
					}
				}
			});

			$rootScope.items = [ 'Profile', 'Setting', 'FAQs' ];

			$rootScope.logout = function() {
				$rootScope.loggingOut = true;
				logoutAndGotoLoginPage();
			};

			// move this to utility file
			function jsdelete_cookie(name, path, domain) {
				if (get_cookie(name)) {
					document.cookie = name + "=" + ((path) ? ";path=" + path : "") + ((domain) ? ";domain=" + domain : "")
							+ ";expires=Thu, 01 Jan 1970 00:00:01 GMT";
				}
			}
			function logoutAndGotoLoginPage() {
				var result = $http.get("logout");
				result.success(function(data, status, headers, config) {
					$cookieStore.remove('user');
					$cookieStore.remove('JSESSIONID');
					// $location.path('/login');
					$timeout(function() {
						$rootScope.loggingOut = false;

					}, 50);
				});
				result.error(function(data, status, headers, config) {
					console.log("logout normal Failed");// call js function to remove cookie

				});
				try {
					// window.location.reload() ;//window.location= "";
					// better to keep all html in jsp and soft code this to page context path to root/ login/ homepage
					// remove from same path as set
					// $.removeCookie('nameofcookie', { path: '/' });
					jsdelete_cookie("user", "/");
					jsdelete_cookie("JSESSIONID", "/");
				} catch (e) {//can console log
				}
				window.location = "login.jsp";
			}

			$rootScope.status = {
				isopen : false
			};

			$rootScope.makeAppointment = function(id) {
				$location.path('/bookAppointment/' + id);
			};

			$rootScope.openAddEditMed = function() {
				$location.path('/getMedicineList');
			};
			$rootScope.notificationToPatient = function() {
				$location.path('/notificationToPatient');
			};
			$rootScope.appSettingById = function() {
				$location.path('/appSettingsById');
			};
			$rootScope.searchAppointment = function() {
				$location.path('/searchAppointment');
			};

		});
