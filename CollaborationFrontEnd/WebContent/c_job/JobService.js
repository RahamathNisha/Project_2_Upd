'user strict'

app.factory('JobService',['$http','$q','$rootScope',function($http,$q,$rootScope)
{
	var baseurl='http://localhost:8081/CollaborationBackEnd/'

		return {
		fetchAllJobs: function()
		{
				return $http.get(baseurl + "/alljobs").then(function (response) 
				{	
					return response.data;
				},
				function(errResponse)
				{
					console.error('Error while fetching Jobs' + errResponse);
					//deferred.reject(errResponse);
				}
			  );
		},
	 
		fetchAllAppliedJobs: function()
		{
			console.log('before get')
			return $http.get(baseurl + "/allappliedjobs").then(function (response) 
		    {
				console.log()
				
				return response.data;
			},
			function(errResponse)
			{
				console.error('Error while fetching Jobs' + errResponse);
				//deferred.reject(errResponse);
			}
		  );			
		},
		
	 createNewJob : function (job)
  	 {
  		  return $http.post(baseurl + '/addjob/', job).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating User');
  	           return $q.reject(errResponse);
  	      });
  	  },
	 
	 applyByUser : function (jobid)
	 {
		 console.log('inside jobservice applyByUser')
 		  return $http.post(baseurl + '/applyforjob/' + jobid).then(function (response) 
 	      {
 			  return response.data;
 		  },
 		  function(errResponse)
 		  {
 			  console.error('Error while applying job');
 		  	  return $q.reject(errResponse);
 		  });
	 }
	}
  }
]);