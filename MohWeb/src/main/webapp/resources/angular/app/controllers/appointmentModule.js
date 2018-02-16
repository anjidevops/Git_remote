'use strict';

var appointmentModule = angular.module('AppointmentModule',[]);

appointmentModule.controller('BookAppointmentController',['$scope','$http','$routeParams','$rootScope','xhrService', function($scope,$http,$routeParams,$rootScope,xhrService){
		var allDepartmentUrl = "department/allDepartment";
		var doctorListByDepartmentUrl = "department/allDoctorByDepartment";
		var doctorAppointmentTimeUrl = "appointment/dayApts";
		var bookAppointmentUrl = "appointment/bookAppointment";
    $scope.allStatus = [{
      name: 'NA',
      class:'unavailable'
    },{
      name: 'Free',
      class:'free'
    },{
      name: 'OP',
      class:'opd'
    },{
      name: 'Busy',
      class:'busy'
    },{
      name: 'Surgery',
      class:'surgery'
    },{
      name: 'IP',
      class:'ipd'
    }];
    $scope.showBookingWindow = false;
    $scope.selectedSlots = [];
    $scope.showStatusSelect = false;
		  $scope.Appointment = {};
      $scope.showWeeks = false;
  		var allDepartmentResult = $http.get(allDepartmentUrl);
  		allDepartmentResult.success(function(data,status){
  			console.log("getting all Departments SUCCESS:"+JSON.stringify(data.departments));
  			$scope.allDepartments = data.departments;
  		});
  		allDepartmentResult.error(function(data,staus){
  			console.log("getting all department failed");
  		});
  		
      $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
      };
      $scope.format = 'yyyy-MM-dd';
      $scope.disabled = function(date, mode) {
        return ( mode === 'day' && ( date.getDay() === 5 || date.getDay() === 6 ) );
      };
      $scope.minDate = new Date();
      $scope.open = function($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
      };
  		$scope.getDoctors = function(Appointment){
            $scope.showBookingWindow = false;
  	        console.log("selected departmentId:"+Appointment.departmentId);

  	        var doctorListResult = $http.get(doctorListByDepartmentUrl, {
  	            params: {
  	               departmentId : Appointment.departmentId   
  	              }
  	        });
  	        doctorListResult.success(function(data,status){
  	          $scope.allDoctors = data.doctors;
  	        });
  	        doctorListResult.error(function(data,staus){
  	          console.log("getting all DoctorsByDepartment failed");
  	        });
  	    };
  	    
  	  $scope.getDoctorTime = function(Appointment){
          $scope.bookingWindow = [];

          if($rootScope.loggedUser.userRole == 'Doctor') {
            Appointment.departmentId = 2;
            Appointment.doctorId = $rootScope.loggedUser.userId;
          }
          if(Appointment.departmentId && Appointment.doctorId && Appointment.appointmentDay && Appointment.lane) {
            xhrService.post(doctorAppointmentTimeUrl,Appointment).then(function(response){
              var numberOfHours = response.hours.length;
              var numberOfIterations = 1;
              $scope.showBookingWindow = true;
              angular.forEach(response.hours, function(value,key){
                var currentHour = value;
                var currentHour1 = value;
                if(currentHour <= 11) {
                  currentHour = currentHour+" am";
                }else {
                  if (currentHour > 12)
                    currentHour = currentHour - 12;
                  currentHour = currentHour+" pm";
                }
                angular.forEach(response.slots, function(value,key){
                  if(value[0][0].hourStart === currentHour1 && numberOfIterations <= numberOfHours){
                      $scope.bookingWindow.push({
                        'hour':currentHour,
                        'lane1':value[0],
                        'lane2':value[1],
                      });
                  }
                });
              });
              $scope.appointmentResultMessage = "";
            },function(error){
              console.log("failed");
            });
          }else {
            if($scope.showBookingWindow){
              $scope.valid = false;
              $scope.appointmentResultMessage = "Fill all the above fields to book a slot.";
            }
          }
      };
      $scope.changeStatus = function(status) {
        console.log(status)
        if($scope.Appointment.lane == 0){
          $scope.selectedSlot = $scope.bookingWindow[$scope.parentIndexSelected].lane1[$scope.indexSelected];
        }
        else if($scope.Appointment.lane == 1) {
          $scope.selectedSlot = $scope.bookingWindow[$scope.parentIndexSelected].lane2[$scope.indexSelected];
        }
        $scope.selectedSlot.status = status;
      };
      $scope.selectStatus = function(slot, index, parentIndex){
        if($scope.loggedUser.userRole == 'Doctor'){
          $scope.showStatusSelect = true;
          $scope.parentIndexSelected = parentIndex;
          $scope.indexSelected = index;
        }else {
          if(slot.status.toLowerCase() == 'free'){
            slot.index = index;
            slot.parentIndex = parentIndex;
            $scope.selectedSlots.push(slot);
            slot.status = 'selected';
          }
          else if(slot.status.toLowerCase() == 'selected'){
            angular.forEach($scope.selectedSlots, function(value, key){
              if(slot.parentIndex === value.parentIndex && slot.index === value.index){
                value.index = null;
                value.parentIndex = null;
                slot.status = 'Free';
                $scope.selectedSlots.splice(key, 1);
              }
            });
          }
        }
      };
      $scope.bookAppointment = function(){
        var bookAppointment = 'appointment/bookAppointment';
        var bookingParams;
        if($scope.selectedSlots.length) {
          $scope.hourStart = $scope.selectedSlots[0].hourStart;
          $scope.hourEnd = $scope.selectedSlots[0].hourEnd;
          $scope.minuteStart = $scope.selectedSlots[0].minuteStart;
          $scope.minuteEnd = $scope.selectedSlots[0].minuteEnd;
          angular.forEach($scope.selectedSlots, function(value, key){
            if(key && key < $scope.selectedSlots.length){
              if(value.hourStart < $scope.hourStart){
                $scope.hourStart = value.hourStart;
                $scope.minuteStart = value.minuteStart;
              }else if(value.hourStart == $scope.hourStart && value.minuteStart < $scope.minuteStart){
                $scope.minuteStart = value.minuteStart;
              }
              if(value.hourEnd > $scope.hourEnd){
                $scope.hourEnd = value.hourEnd;
                $scope.minuteEnd = value.minuteEnd;
              }else if(value.hourEnd == $scope.hourEnd && value.minuteEnd > $scope.minuteEnd){
                $scope.minuteEnd = value.minuteEnd;
              }
            }
          });
          bookingParams = {
             'departmentId': Number($scope.Appointment.departmentId),
             'doctorId': Number($scope.Appointment.doctorId),
             'patientId' : Number($routeParams.userId),
             'appointmentDay': $scope.Appointment.appointmentDay,
             'fromHour' : $scope.hourStart,
             'fromMin' : $scope.minuteStart,
             'toHour' : $scope.hourEnd,
             'toMin' : $scope.minuteEnd,
             'lane' : $scope.Appointment.lane
          }
        }else {
          bookingParams = {
             'departmentId': Number($scope.Appointment.departmentId),
             'doctorId': Number($scope.Appointment.doctorId),
             'patientId' : Number($routeParams.userId),
             'appointmentDay': $scope.Appointment.appointmentDay,
             'fromHour' : $scope.selectedSlot.hourStart,
             'fromMin' : $scope.selectedSlot.minuteStart,
             'toHour' : $scope.selectedSlot.hourEnd,
             'toMin' : $scope.selectedSlot.minuteEnd,
             'lane' : $scope.Appointment.lane,
             'slotStatus' :$scope.selectedSlot.status
          }
        }

        xhrService.post(bookAppointment, bookingParams).then(function(response){
          if(response.errorNo === 0){
           $scope.valid = true;
           $scope.appointmentResultMessage = response.successMsg;
           $scope.getDoctorTime($scope.Appointment);
          }else {
            $scope.valid = false;
            $scope.appointmentResultMessage = response.errorMsg;
          }
          angular.forEach($scope.selectedSlots, function(value, key){
            value.status = 'Free'
          });
          $scope.selectedSlots= [];
        });
      };

      $scope.bookTime = function(hour,min,Appointment){
          console.log("bookTime called");
          console.log(hour+":"+min);

          if(Appointment.fromHour == null && Appointment.fromMin ==null ){
            console.log("setting from Hour,min");
            if(checkAppointmentTimeRange(Appointment,hour,min,false) ){
                Appointment.fromHour = hour;
                Appointment.fromMin = min;
            }  
          } else if(Appointment.fromHour == hour && Appointment.fromMin == min){
              //deselect from Hour,Min
              console.log("clearing from Hour,min");
              Appointment.fromHour = null;
              Appointment.fromMin = null;
          } else if( Appointment.toHour == null && Appointment.toMin == null ){
             console.log("setting toHour,min");
             if(checkAppointmentTimeRange(Appointment,hour,min,true) ){
                 Appointment.toHour = hour;
                 Appointment.toMin = min;
             }
          } else if(Appointment.toHour == hour && Appointment.toMin == min){
             console.log("clearing toHour,min");
             Appointment.toHour = null;
             Appointment.toMin = null;
          }

          console.log("after clicking appointment =");
          console.log(JSON.stringify(Appointment));
      };

      function checkAppointmentTimeRange(Appointment,hour,min,toValidationType){
         console.log("checking validation totype:"+ toValidationType);
         // end time should be after start booking time.
         // if time range is valid , then return true otherwise false
         var alertMessage = "Please select valid time range. End time will be after start appoitnment time";
         if(Appointment.fromHour == null && Appointment.toHour == null){
            //no validation required. FROM && TO hour,min  ANY value is not SET.
            return true;
         }

         if(toValidationType){
              if(Appointment.fromHour > hour){
                  alert(alertMessage);
                  return false;
              }else if(Appointment.fromHour == hour && Appointment.fromMin > min) {
                 alert(alertMessage);
                  return false;
              }
         }else {
              if(hour > Appointment.toHour){
                  alert(alertMessage);
                  return false;
              }else if(hour == Appointment.toHour && min > Appointment.toHour){
                  alert(alertMessage);
                  return false;
              }
         }
         return true;
      }

  		// $scope.bookAppointment = function(Appointment){
    //     if(Appointment.fromHour == null || Appointment.toHour == null){
    //        $scope.appointmentResultMessage = "Appointment Time Range is INVALID";
    //        return false;
    //     }

  		// 	Appointment.patientId=$rootScope.patientId;
  		// 	//Appointment.fromMin = Number(Appointment.fromMin) + 1;
  		// 	console.log("Appointment:"+JSON.stringify(Appointment));
  		// 	Appointment.toMin = Number(Appointment.toMin) -1 ;
         
  		// 	var result = $http.post(bookAppointmentUrl, Appointment);
  		// 	result.success(function(data, status, headers, config){
  		// 		console.log("Booking Appointment Status:"+JSON.stringify(data));
  		// 		if(data.errorNo == 0){
  		// 			$scope.appointmentResultMessage = data.successMsg;
  					
  		// 			$("#doctorAppointmentTimeResult").text('');
  		// 			$scope.Appointment = {};
  		// 			$scope.appointmentForm.$setPristine();
  		// 		}else{
  		// 			if(Appointment.fromMin){
  		// 				Appointment.fromMin = Number(Appointment.fromMin) - 1;
  		// 			}
  		// 			$scope.appointmentResultMessage = data.errorMsg;
  		// 		}
  		// 	});
  		// 	result.error(function(data, status, headers, config){
  		// 		console.log("failed");
  		// 	});
  			
  		// };
  	}]);
appointmentModule.controller('NotificationToPatientController', ['$scope','$http','$location','SharedService',
       function($scope, $http, $location,SharedService) {
	        var appointmentNotification = "appointment/notificationToPatients";
	        var result = $http.get(appointmentNotification);
	        result.success(function(data, status, headers, config) {
  	        if (data.errorNo == 0) {
    	        console.log("success...");
    	        $scope.appointmentResultMessage = data.successMsg;
    	        $scope.appointmentErrorMessage = "";
  	        } else {
    	        $scope.appointmentResultMessage = data.errorMsg;
    	        $scope.appointmentSuccessMessage = "";
  	        }
	       });
	       result.error(function(data, status, headers, config) {
	         console.log("Failed called");
	       });
	}
	]);


appointmentModule.controller('AppSettingsByIdController', ['$scope','$http','$location','SharedService',
      function($scope, $http, $location, SharedService) {
			var appointmentNotification = "appointment/params";
			var id=4;
			var result = $http.get(appointmentNotification);
			result.success(function(data, status, headers, config) {
				console.log("Data Received from User:");
				console.log(JSON.stringify(data));
				if (data.errorNo == 0) {
					console.log("success...");
					$scope.appointmentResultMessage = data.successMsg;
					$scope.appointmentErrorMessage = "";
				} else {
					$scope.appointmentResultMessage = data.errorMsg;
					$scope.appointmentSuccessMessage = "";
				}
			});
			result.error(function(data, status, headers, config) {
				console.log("Failed called");
			});
	}
]);

appointmentModule.controller('AppointmentSearchResultController', ['$scope','$http','$location','$rootScope',
    function($scope,$http,$location,$rootScope){
       var searchAppointmentUrl = "appointment/appointmentSearch";

       $scope.searchAppointment = function(userText){
          console.log(userText);

          var searchAppointmentResult = $http.get(searchAppointmentUrl , {
             params: {
                userId: userText
             }
          });
          searchAppointmentResult.success(function(data, status, headers, config) {
            console.log(JSON.stringify(data));
            $scope.showResult = true;
            if(data.appointmentResults != null && data.appointmentResults.length > 1){
               $scope.showAppointmentResult=true;
               $scope.appointments = data.appointmentResults;
            }else {
               $scope.showAppointmentResult=false;
               $scope.appointments = {};
            }
          });
          searchAppointmentResult.error(function(data, status, headers, config) {
              console.log("Failed fetching appointment list");
          });      

       };

       $scope.makeAppointment = function(appointment){
          $rootScope.appointmentId = appointment.appointmentId;
          $location.path('/createPatientRecord');
       };

    }
 ]);
