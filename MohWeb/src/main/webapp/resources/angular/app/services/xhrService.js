app.factory('xhrService', function($q, $timeout, $http) {
    var xhr = {
        get: function(url) {
            var deferred = $q.defer();
            $timeout(function() {	
                $http.get(url).success(function(data) {
                    deferred.resolve(data);
                });
            }, 30);
            return deferred.promise;
        },
        post: function(url, data) {
            var deferred = $q.defer();
            $timeout(function() {	
                $http.post(url, data).success(function(data) {
                    deferred.resolve(data);
                });
            }, 30);
            return deferred.promise;
        }
    };
    return xhr;
});