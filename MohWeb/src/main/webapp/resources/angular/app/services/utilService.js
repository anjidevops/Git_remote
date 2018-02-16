app.factory('utilService', function() {
    var util = {
        isElementPresent: function(element,arr) {
            var isElementPresent = false;
            if(arr.indexOf(element) !== -1)
            	isElementPresent = true;
            return isElementPresent;
        }
    };
    return util;
});