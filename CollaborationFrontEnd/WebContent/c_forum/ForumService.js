'user strict'

app.factory('ForumService',['$http','$q','$rootScope',function($http,$q,$rootScope)
{
	var baseurl='http://localhost:8081/CollaborationBackEnd/'

	return {
		fetchAllForums: function()
		{
			return $http.get(baseurl + "/alluserforum").then(function (response) 
			{	
				return response.data;
			},
			function(errResponse)
			{
				console.error('Error while fetching Forums' + errResponse);
				deferred.reject(errResponse);
			}
		 );
	 },

	 fetchAllForumsCategory: function()
	 {
		return $http.get(baseurl + "/alluserforumcats").then(function (response) 
		{	
			return response.data;
		},
		function(errResponse)
		{
			console.error('Error while fetching Forums' + errResponse);
			deferred.reject(errResponse);
		});
	 },
	 
	 deleteforum: function(forumid)
	 {
		 return $http.post(baseurl+'/getdeleteforum/' + forumid)
		 	.then(function (response)
		 	{
		 		return response.data;
		    },
		    function(errResponse){
		    	console.error('Error while fetching Forum');
		        return $q.reject(errResponse);
		    }
		  );
	 },
	 
	 approvedforum: function(forumid)
	 {
		 return $http.post(baseurl+'/getapproveforum/' + forumid)
	            .then(
	            	function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching forums');
	                return $q.reject(errResponse);
	            }
	        );
	 },

	 rejectedforum: function(forumid)
	 {
		 return $http.post(baseurl+'/rejectedforum/' + forumid)
	            .then(
	            	function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching forums');
	                return $q.reject(errResponse);
	            }
	        );
	 },

     likeupdate: function(forumid)
	 {
	       return $http.post(baseurl+'/getupdateforumlike/' + forumid).then(
	    		function (response) {
		             return response.data;
		        },
		        function(errResponse){
		                console.error('Error while updating forums like');
		                return $q.reject(errResponse);
		            }
		        );
		},

	  readSelectedForum : function(forumid)
	  {
		  return $http.get(baseurl+'/getforumbyid/' + forumid).then(
		  function (response) {
			 return response.data;
		  },
		  function(errResponse){
			  console.error('Error while reading forum');
			  return $q.reject(errResponse);
		  }
		 );
	    },

		readSelectedForumComments : function(forumid)
		{
			return $http.get(baseurl+'/alluserforumcmts/' + forumid).then(
				function (response)
				{
					return response.data;
				},
				function(errResponse){
					console.error('Error while reading forum');
					return $q.reject(errResponse);
				});
		},

	 createNewForum : function (forum)
  	 {
  		  return $http.post(baseurl + '/adduserforum/', forum).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating blog');
  	           return $q.reject(errResponse);
  	      });
  	  },
  	  
 	 createNewForumComment : function (forumcmt)
  	 {
  		  return $http.post(baseurl + '/adduserforumcmt/', forumcmt).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating blog');
  	           return $q.reject(errResponse);
  	      });
  	  }  	  

	}
  }
]);