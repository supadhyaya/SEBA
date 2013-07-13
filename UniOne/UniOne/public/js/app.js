/**
 * Created with JetBrains WebStorm.
 * User: Irman
 * Date: 13.6.13
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */

var unione;

unione = angular.module("unione", []);

/*
 Shared Service
 */
unione.service( 'sharedService', function() {
    return {
        serviceURL: "http://www.serviceurl.com/rpc"
    };
});

/*
 *
 * Factory for communication with Local Storage
 */
unione.factory('storage', function() {

    function read(key) {
        try {
            var value = $.jStorage.get(key);
            return value;
        } catch (e) {
            //@TODO: Read exception! do something
            return false;
        }
    }

    function write(key, value, ttl) {
        try {
            $.jStorage.set(key, value, ttl)
            return true;
        } catch (e) {
            //@TODO: Write exception! do something
            return false;
        }
    }

    function deleteKey(key) {
        try {
            $.jStorage.deleteKey(key);
            return true;
        } catch (e) {
            //@TODO: Delete exception! do something
            return false;
        }
    }

    function deleteAll() {
        try {
            $.jStorage.flush();
            return true;
        } catch (e) {
            //@TODO: Delete exception! do something
            return false;
        }
    }

    return {
        read: 			read,
        write: 			write,
        delkey: 		deleteKey,
        delall: 		deleteAll
    }

});

unione.controller('CtrlLogin', [
    '$scope',
    function($scope) {

        $scope.data = {
            username: null,
            password: null
        };

        $scope.login = function() {
            // TODO: login
            alert("send ajax call to login");
        }
    }
]);

unione.controller('CtrlRegister', [
    '$scope', '$http', 'sharedService',
    function($scope, $http, sharedService) {

        $scope.uniDomain = null;
        $scope.registrationEmailSent = false;

        $scope.data = {
            firstName:      null,
            lastName:       null,
            universityId:   null,
            email:          null,
            password:       null,
            repeatPassword: null
        }

        $scope.universities = dummy.universities;

        $scope.updateUniDomain = function() {
            $.each($scope.universities, function(key, value){
                if (value.value == $scope.data.universityId) {
                    $scope.uniDomain = "@" + value.domain;
                    return false;
                }
            });
        }

        $scope.testAjax = function() {


            console.log("log message");

            $.get("http://localhost:9000/application/test", {variable: "test var"})
                .done(function(data) {
                    alert("Data Loaded: " + data);
                });

        }

        $scope.register = function() {

            // TODO: create user
            $http({ data: $scope.data, method: 'POST', url: sharedService.serviceURL + '/register'}).
                success(function(data, status, headers, config) {
                    $scope.registrationEmailSent = true;
                }).
                error(function(data, status, headers, config) {
                    alert('Please try again. Something went wrong.');
                    $scope.registrationEmailSent = true;
                });

        }

    }
]);


unione.controller('CtrlPartners', [
    '$scope',
    function($scope) {

    }
]);



/*  BROWSE COURSES PAGE  */


unione.controller('CtrlBrowse', [
    '$scope', 'storage', 'sharedService', '$window',
    function($scope, storage, sharedService, $window) {

        $scope.data = {
            universityId: null,
            query: null
        };

        $scope.visitedCourses = storage.read('visitedCourses');

        if ($scope.visitedCourses == null)
            $scope.visitedCourses = [];

        $scope.searchResults = [];
        $scope.searchTime = 0;

        $scope.universities = dummy.universities;



        $scope.search = function() {

            // TODO: Search Call
            $scope.searchResults = dummy.serachresults;
            $scope.searchTime = new Date().getTime();
        }

        $scope.viewCourse = function() {
            var $this = this.course;
            var holdKey = null;


            $.each($scope.visitedCourses, function(key, value) {

                if (value.cid == $this.cid && value.uid == $this.uid) {

                    holdKey = key;
                    return false;
                }
            });

            if (holdKey!=null) {

                $scope.visitedCourses[holdKey].clickCounter++;
            } else {

                $scope.visitedCourses.splice(0, 0, $this);
                $scope.visitedCourses[0].clickCounter = 1;
            }

            storage.write( 'visitedCourses', $scope.visitedCourses, {TTL: 0} );

            console.log($this);

            // goto sharedService.serviceURL
            $window.location='rate.html';

        }

        $scope.updateSearchMessage = function() {
            var result = null;
            /*
            if ($scope.searchResults.length>0) {

                $scope.searchTime = Math.round(((new Date().getTime()) - $scope.searchTime)*1000)/1000;
                result = "Found " + $scope.searchResults.length + " results in " + $scope.searchTime + " ms.";
            } else {

                if ($scope.searchTime == 0)
                    result = "<h3>Enter Course name and start Searching...</h3>"
                else
                    result = "<h3>No search results.</h3>"
            }
*/
            return result;
        }
    }
]);




unione.controller('CtrlRate', [
    '$scope', 'storage',
    function($scope, storage) {

        $scope.course = dummy.course;

        $scope.visitedCourses = storage.read('visitedCourses');

        if ($scope.visitedCourses == null)
            $scope.visitedCourses = [];


    }
]);


unione.controller('CtrlQuestions', [
   '$scope', 'storage',
   function($scope, storage) {

	   $scope.init = function() {
		   
		   
	   }
	   
	   


   }
]);





/*  DASHBOARD PAGE  */


unione.controller('CtrlMenu', [
    '$scope',
    function($scope) {

    }
]);


