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
    '$scope', '$http', '$window',
    function($scope, $http, $window) {

        $scope.data = {
            username: null,
            password: null
        };

        $scope.login = function() {
        	
        	var getData = {
                username: $scope.data.username,
                password: $scope.data.password
            }
        	
        	$.get("http://localhost:9000/usercontroller/loginuser", getData)
            .done(function(data) {
            	if (!data.error) {
            		$scope.$apply(function () {
            			$window.location='browse';
	                });
            	} else {
            		alert(data.message)
            	}
            		
            })
            .fail(function() { 
            	alert('Please try again. Something went wrong.');
            });
        	
        	
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
     // firstName, lastName, university, email, password, dOB

        $scope.universities = dummy.universities;
        
        $scope.getUniDomain = function() {        	
            $.each($scope.universities, function(key, value){
                if (value.value == $scope.data.universityId) {
                    $scope.uniName = value.name;
                    return false;
                }
            });
        }
        
        $scope.updateUniDomain = function() {
            $.each($scope.universities, function(key, value){
                if (value.value == $scope.data.universityId) {
                	if (value.domain==null) {
                		$scope.uniDomain = "";
                	} else {
                		$scope.uniDomain = "@" + value.domain;
                	}
                    return false;
                }
            });
        }

        $scope.testAjax = function() {


            console.log("log message");

            $.get("http://localhost:9000/usercontroller/testajax", {test: "Irman"})
                .done(function(data) {
                    alert("Data Loaded: " + data);
                });
            

        }

        $scope.register = function() {

        	// String firstName, String lastName, String university, String email, String password, String dOB

        	if ($scope.data.password == $scope.data.repeatPassword) {
        		
        		var getData = {
        			firstName:      $scope.data.firstName,
                    lastName:       $scope.data.lastName,
                    university:   	$scope.data.universityId,
                    email:          $scope.data.email+$scope.uniDomain,
                    password:       $scope.data.password
            	}
            	
            	$.get("http://localhost:9000/usercontroller/registerUser", getData)
    	            .done(function(data) {
    	            	
    	            	if (!data.error) {
    	            		$scope.$apply(function () {
        	            		$scope.registrationEmailSent = true;
        	                });
    	            	} else {
    	            		$scope.$apply(function () {
        	            		$scope.registrationEmailSent = false;
        	                });
    	            		alert(data.message)
    	            	}
    	            	
    	            })
    	            .fail(function() { 
    	            	$scope.$apply(function () {
    	            		alert('Please try again. Something went wrong.');
        	                $scope.registrationEmailSent = false;
    	                });
    	            	
    	            });
        	} else {
        		
        		alert("Passwords do not match!");
        	}
  
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
    '$scope', 'storage', 'sharedService', '$window', '$timeout',
    function($scope, storage, sharedService, $window, $timeout) {

        $scope.data = {
            universityId: 0,
            query: null
        };

        $scope.visitedCourses = storage.read('visitedCourses');

        if ($scope.visitedCourses == null)
            $scope.visitedCourses = [];

        $scope.searchResults = [];
        $scope.searchTime = 0;

        $scope.universities = dummy.universities;

        $scope.search = function() {
        	
        	$timeout.cancel($scope.tPromise);

        	if ($scope.data.query) {
        		
        		$scope.tPromise = $timeout(function(){
                	
                	$.get("http://localhost:9000/coursecontroller/getSearchedCourses", angular.copy($scope.data))
                    .done(function(data) {
                    	if (data.error === undefined) {
                    		$scope.$apply(function () {
                    			$scope.searchResults = data;
         	           		});
                    	} else {
                    		
                    		$scope.$apply(function () {
                    			$scope.searchResults = [];
         	           		});
                    	}
                    		
             	       })
             	       .fail(function() { 
             	       		alert('Please try again. Something went wrong.');
             	       });

                }, 500);
        	} else {
        		$scope.searchResults = [];
        	}
        	
            //$scope.searchResults = dummy.serachresults;
            $scope.searchTime = new Date().getTime();
        }

        $scope.viewCourse = function() {
        	
            var $this = this.course;
            var holdKey = null;


            $.each($scope.visitedCourses, function(key, value) {

                if (value.id == $this.id && value.universityid == $this.universityid) {

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

            storage.write( 'visitedCourses', angular.copy($scope.visitedCourses), {TTL: 0} );

            console.log($this);

            // goto sharedService.serviceURL
            $window.location='/questions/'+$this.id;

        }
        
        $scope.uniIdToName = function(id) {
        	console.log(this.course);
        	var result = null;
        	
        	$.each($scope.universities, function(key, value) {

                if (value.value == id) {

                    result = value.name;
                    return false;
                }
            });
        	
        	return result;
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
    '$scope', 'storage', '$window',
    function($scope, storage, $window) {

        $scope.course = dummy.course;

        var urlValues = document.URL.split("/");
 	   
 	   var cId = parseInt(urlValues[urlValues.length-1]);
        
        $scope.visitedCourses = storage.read('visitedCourses');

        if ($scope.visitedCourses == null)
            $scope.visitedCourses = [];
        
        $scope.courseRating = angular.copy(dummy.questions);

        $.each($scope.courseRating, function(key, value){
        	value.cid = cId;
        });
        
        
        
        $scope.getCourseNameById = function() {
 		   
 		   $.get("http://localhost:9000/coursecontroller/getCourseNameById", {id: cId})
 	       .done(function(data) {
 	    	   		
 	    	   if (data.error === undefined) {
 		       		$scope.$apply(function () {
 		       			$scope.courseNameTitle = data.courseName;
 		       		});
 		       	} 
 	    	   	
 		       })
 		       .fail(function() { 
 		       		alert('Please refresh the page. Something went wrong.');
 		       });
 	   	}
        $scope.getCourseNameById();
        
        $scope.save = function() {
        	
        	$.get("http://localhost:9000/courseratingcontroller/submitCouseRating", {
        		courseId: cId,
        		rating: JSON.stringify(angular.copy($scope.courseRating))
        		})
            .done(function(data) {
         	   		
         	   	
     	       })
     	       .fail(function() { 
     	       		alert('Please try again. Something went wrong.');
     	       });
     	    
        }
        
        $scope.rateCourse = function() {
  		   $window.location='rate/'+cId;
  	   }
        
        $scope.uniIdToName = function(id) {
           	
           	var result = null;
           	
           	$.each($scope.universities, function(key, value) {

                   if (value.value == id) {

                       result = value.name;
                       return false;
                   }
               });
           	
           	return result;
           }
        
        $scope.logout = function() {
        	
        	$.get("http://localhost:9000/usercontroller/logoutUser")
            .done(function(data) {
            	$window.location='/index';
            })
            .fail(function() { 
            	alert('Please try again. Something went wrong.');
            });
        }

    }
]);


unione.controller('CtrlQuestions', [
   '$scope', 'storage', '$window', '$location',
   function($scope, storage, $window, $location) {

	   $scope.visitedCourses = storage.read('visitedCourses');

       if ($scope.visitedCourses == null)
           $scope.visitedCourses = [];
       
       $scope.universities = dummy.universities;
	   
	   var urlValues = document.URL.split("/");
	   
	   var cId = parseInt(urlValues[urlValues.length-1]);
	   
	   $scope.data = {
			   questionText: null,
			   courseId: cId
	   }

	   $scope.refreshQuestions = function() {
		   
		   $.get("http://localhost:9000/coursecontroller/getAllQuestions", {id: cId})
	       .done(function(data) {
	    	   		
	    	   if (data.error === undefined) {
		       		$scope.$apply(function () {
		       			$scope.uQuestions = data;
		       		});
		       	} else {
		       		
		       		$scope.$apply(function () {
		       			$scope.uQuestions = [];
		           	});
		       	}
	    	   
	    	   	
		       })
		       .fail(function() { 
		       		alert('Please refresh the page. Something went wrong.');
		       });
	   }
	   
	   $scope.getCourseNameById = function() {
		   
		   $.get("http://localhost:9000/coursecontroller/getCourseNameById", {id: cId})
	       .done(function(data) {
	    	   		
	    	   if (data.error === undefined) {
		       		$scope.$apply(function () {
		       			$scope.courseNameTitle = data.courseName;
		       		});
		       	} 
	    	   	
		       })
		       .fail(function() { 
		       		alert('Please refresh the page. Something went wrong.');
		       });
	   }
	   
	   $scope.init = function() {
		   
		   $scope.refreshQuestions();
		   $scope.getCourseNameById();
	   }
	   
	   $scope.init(); // init (refresh)
	       
	   $scope.rateCourse = function() {
		   $window.location='rate/'+cId;
	   }
	   
	   $scope.viewCourse = function() {
       	
           var $this = this.course;
           var holdKey = null;


           $.each($scope.visitedCourses, function(key, value) {

               if (value.id == $this.id && value.universityid == $this.universityid) {

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

           storage.write( 'visitedCourses', angular.copy($scope.visitedCourses), {TTL: 0} );

           console.log($this);

           // goto sharedService.serviceURL
           $window.location='/questions/'+$this.id;

       }
	   
	   
	   
	   
	   $scope.addQuestion = function() {
		   
		   $.get("http://localhost:9000/coursecontroller/postQuestion", angular.copy($scope.data))
           .done(function(data) {
        	   	$scope.data.questionText = null;
        	   	$scope.refreshQuestions();
    	       })
    	       .fail(function() { 
    	       		alert('Please try again. Something went wrong.');
    	       });
	   }
       
       $scope.uniIdToName = function(id) {
       	
       	var result = null;
       	
       	$.each($scope.universities, function(key, value) {

               if (value.value == id) {

                   result = value.name;
                   return false;
               }
           });
       	
       	return result;
       }
	   
	   
	   $scope.logout = function() {
       	
       	$.get("http://localhost:9000/usercontroller/logoutUser")
           .done(function(data) {
           	$window.location='/index';
           })
           .fail(function() { 
           	alert('Please try again. Something went wrong.');
           });
       }
	   
   }
]);



// activating the user 
unione.controller('CtrlActivate', [
    '$scope', '$http', '$window',
    function($scope, $http, $window) {
    	
        $scope.data = {
            userName: null,
            activationKey: null
        };

        $scope.activate = function() {
        	alert("inside activate user.js");	
        	var getData = {
                userName: $scope.data.userName,
                activationKey: $scope.data.activationKey
            }
        	
        	$.get("http://localhost:9000/usercontroller/activateUser", getData)
            .done(function(data) {
            	if (!data.error) {
            		$scope.$apply(function () {
            			$window.location='browse';
	                });
            	} else {
            		alert(data.message)
            	}
            		
            })
            .fail(function() { 
            	alert('Please try again. Something went wrong.');
            });
        }
    }
]);


/*  DASHBOARD PAGE  */


unione.controller('CtrlMenu', [
    '$scope',
    function($scope) {

    }
]);


