app.directive('dragMe', function(){
	return {
		restrict: 'A',
		scope: {
			someThing1:'@someThing',
			onSend1: '=someThing',
			dataSource: '=',
			onSend: '&',
			action: '&'
		},
		link: function(scope, element, attrs){
			// element.draggable();
			// console.log(scope)
			// console.log(element)
			// console.log(attrs)
			// scope.callMe = function(){
		 //    	scope.customer.name = 'Ashton';
		 //    }
		 //    scope.customer = {
		 //        name: 'David',
		 //        street: '1234 Anywhere St.'
		 //    };
		 // //    console.log(scope.someThing1)
			// // console.log(scope.someThing)
			// // console.log(scope.onSend)
			// scope.onSend();
		},
		controller: function($scope, $element, $attrs){
			// console.log($scope)
			// console.log($element)
			// console.log($attrs)
			console.log($scope.someThing1)
			$scope.action();
			// console.log()
			// $scope.onSend($scope.someThing1);
		},
		template: '<ul><li ng-repeat="prop in dataSource">{{ prop }}</li></ul><br/><button ng-click="action()">Change Data</button>..Name: {{customer.name}}<br /> Street: {{customer.street}} <div ng-click="callMe()">callMe</div>'
	};
})