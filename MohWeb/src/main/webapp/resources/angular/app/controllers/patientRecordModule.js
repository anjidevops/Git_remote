'use strict';

var attachmentModule = angular.module('PatientRecordModule',[]);

appointmentModule.controller('CreatePatientRecordController',['$scope','$http','$routeParams','$rootScope',
        function($scope,$http,$routeParams,$rootScope){
			var fileUploadUrl = "patientrecord/createRecord";
			var viewPatientRecordUrl = "patientrecord/viewRecord";
			var appointmentId = $rootScope.appointmentId;
			
			var prEditMode = false;
			//Admin can edit/create patient record
			var viewpatientRecordResult = $http.get(viewPatientRecordUrl, 
					 { 
				 	   params: {
						   appointmentId : appointmentId
					   }
					 } 
			 );
			viewpatientRecordResult.success(function(data, status, headers, config){
				console.log(JSON.stringify(data));
				if(data.patientRecordDto != null){
					$scope.PatientRecord = data.patientRecordDto;
					console.log("PR Found");
					prEditMode = true;
					
					$scope.updatePatientRecord = prEditMode;
					if(data.patientRecordDto.attach1Path != null && data.patientRecordDto.attach2Path != null){
				 		$scope.bothAttachmentDownloadLink = true;
				 	}else if(data.patientRecordDto.attach1Path != null){
						$scope.firstAttachmentDownloadLink = true;
				 	}else if(data.patientRecordDto.attach2Path != null){
				 		$scope.secondAttachmentDownloadLink = true;	
				 	}else {
				 		$scope.noneAttachmentLinks = true;
				 	}
					
				}else{
					console.log("PR NOT Found.");
				}
			});
			viewpatientRecordResult.error(function(data, status, headers, config){
				console.log("view PR failed");
			});

			$scope.filesChanged = function(elm){
				$scope.files = elm.files;
				$scope.$apply();
			};

			$scope.createPatientRecord = function(PatientRecord){
				PatientRecord.appointmentId = appointmentId;
				
				console.log('uploading file');
				console.log($scope.files);

				var fd = new FormData();
				angular.forEach($scope.files,function(file){
					fd.append('ufile',file);
				});

				//append each model attribute
				if(prEditMode ==true){
					fd.append('patientRecordId',PatientRecord.patientRecordId);
				}
				fd.append('patientId',PatientRecord.patientId);
				fd.append('appointmentId',PatientRecord.appointmentId);
				fd.append('summary',PatientRecord.summary);
				fd.append('prescription',PatientRecord.prescription);
				
				var result = $http.post(fileUploadUrl,fd,{
					transformRequest: angular.identity,
            		headers: {'Content-Type': undefined } , 
            		enctype: 'multipart/form-data'
				});
				result.success(function(data, status, headers, config){
					console.log(JSON.stringify(data));
					if(data.errorNo == 0){
						$scope.createPatientRecordResult = data.successMsg;
						//clear form data after successful PR creation
						$scope.PatientRecord = {};
  						$scope.patientRecordForm.$setPristine();
  						$scope.files = {};
					}else{
						$scope.createPatientRecordResult = data.errorMsg;
					}
				});
				result.error(function(data, status, headers, config){
						console.log("error");
				});

			};
		
        }
 ]);

appointmentModule.controller('ViewPatientRecordController',['$scope','$http','$routeParams',
  function($scope,$http,$routeParams){
  	 var viewPatientRecordUrl = "patientrecord/viewRecord";

	 var prResult = $http.get(viewPatientRecordUrl, 
			 { 
		 	   params: {
				   appointmentId : $routeParams.appointmentId
			   }
			 } 
	 );
	 prResult.success(function(data, status, headers, config){
	 	console.log(JSON.stringify(data));
	 	if(data.patientRecordDto != null){
	 		$scope.showRecord = true;
		 	$scope.patientRecord = data.patientRecordDto;
		 	if(data.patientRecordDto.attach1Path != null && data.patientRecordDto.attach2Path != null){
		 		$scope.bothAttachmentDownloadLink = true;
		 	}else if(data.patientRecordDto.attach1Path != null){
				$scope.firstAttachmentDownloadLink = true;
		 	}else if(data.patientRecordDto.attach2Path != null){
		 		$scope.secondAttachmentDownloadLink = true;	
		 	}else {
		 		$scope.nondeAttachmentLinks = true;
		 	}
	 	}else{
	 		$scope.showRecord = false;
	 	}

	 });
	 prResult.error(function(data, status, headers, config){
	 	console.log("error");
	 });

  }
]);                                                            