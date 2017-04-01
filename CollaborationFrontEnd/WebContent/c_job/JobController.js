'use strict';

app.controller('JobController', ['$scope', 'JobService', '$location', '$rootScope',
                                function($scope, JobService, $location, $rootScope) {
	
	console.log("user job controller");
	
	var self = this;
    self.job={
    		id:'',postdate:'', title:'', description:'', qualification:'', status:'',
    		title: '',location: '', experience: '', companyname: ''};
    self.jobs=[];
    
    self.jobapplications=[];
    
    self.fetchAllJobs = function()
    {
        JobService.fetchAllJobs().then(function(d)
    	{
        	self.jobs = d;
            console.log("Fetch all jobs sucess")
        },
        function(errResponse)
        {
        	console.error('Error while fetching jobs'+ errResponse);
        }
      );
   };
    
   self.fetchAllJobs();
   
   self.fetchAllAppliedJobs = function()
   {
       JobService.fetchAllAppliedJobs().then(function(d)
       {
          	self.jobs = d;
          	console.log("Fetch all applied jobs")
       },
       function(errResponse)
       {
          	console.error('Error while fetching job application'+ errResponse);
       }
     );
   }
   
   self.fetchAllAppliedJobs();
   
   self.createNewJob = function(job){
   	JobService.createNewJob(job)
   		.then(
   				self.fetchAllJobs, self.reset,
   				function(errResponse)
   				{
   					console.error('Error while creating record');
   				}
   		);
   };
   
   self.applyByUser = function(jobid)
   {
	   console.log('inside jobcontroller applyByUser')
	   JobService.applyByUser(jobid).then(
  				self.fetchAllJobs,
   				function(errResponse)
   				{
   					console.error('Error while creating record');
   				}
   		);
   };
   
   self.clickOnApply = function(jobid)
   {
	   alert(jobid);
		if(confirm('Are you sure you want to apply this job?')) 
		{
			
			self.applyByUser(jobid);
		}
   }
   self.submit = function()
   {
   	self.createNewJob(self.job);
   	console.log("Saving new job", self.job);
   };

   self.reset = function()
   {
	    self.job={
	    		id:'',postdate:'', title:'', description:'', qualification:'', status:'',
	    		title: '',location: '', experience: '', companyname: ''};
     	$scope.myform.$setPristine();
   };
}]);
