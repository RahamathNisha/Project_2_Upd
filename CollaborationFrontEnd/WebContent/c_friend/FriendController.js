'use strict';
 
app.controller('FriendController', ['UserService','$scope', 'FriendService','$location',
   '$rootScope',function(UserService,$scope, FriendService,$location,$routeParams,$rootScope) {
	console.log("FriendController...")
          var self = this;
          self.friend={id:'',userID:'',friendID:'',status:''};
          self.friends=[];
          
          self.user = {
  				id : '',
  				name : '',
  				password : '',
  				mobileNo : '',
  				address : '',
  				email : '',
  				isOnline:'',
  				role : '',
  				errorMessage : ''
  			};
  			self.users = [];
          
         self.sendFriendRequest=sendFriendRequest
         
         function sendFriendRequest(friendID)
         {

       	  console.log("->sendFriendRequest :"+friendID)
             FriendService.sendFriendRequest(friendID)
                 .then(
                              function(d) {
                                   self.friend = d;
                                  alert("Friend request sent")
                              },
                               function(errResponse){
                                   console.error('Error while sending friend request');
                               }
                      );
         
        	 
         }
          
             
          self.getMyFriends = function(){
        	  console.log("Getting my friends")
              FriendService.getMyFriends()
                  .then(
                               function(d) {
                                    self.friends = d;
                                    console.log("Got the friends list")
                                     	/* $location.path('/view_friend');*/
                               },
                                function(errResponse){
                                    console.error('Error while fetching Friends');
                                }
                       );
          };
          
            
       
         self.acceptFriendRequest = function(friendID)
         {
        	 console.log('inside FriendController acceptFriendRequest method')
              FriendService.acceptFriendRequest(friendID)
                      .then( 
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while updating Friend.');
                              } 
                  );
          };
 
          self.rejectFriendRequest = function(friendID)
          {
        	  console.log('inside FriendController rejectFriendRequest method')
              FriendService.rejectFriendRequest(friendID)
                      .then( 
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while updating Friend.');
                              } 
                  );
          };
          
         self.deleteFriend = function(id){
              FriendService.deleteFriend(id)
                      .then(
                              self.fetchAllFriends, 
                              function(errResponse){
                                   console.error('Error while deleting Friend.');
                              } 
                  );
          };
          
          self.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			};
            
 
          self.fetchAllUsers();
          self.getMyFriends();
          
 
     
 
      }]);