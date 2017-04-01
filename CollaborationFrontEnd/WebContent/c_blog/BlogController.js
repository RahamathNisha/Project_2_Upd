'use strict';

angular.module('myApp').controller(
		'BlogController',
		[
				'$scope',
				'BlogService',
				'$location',
				function($scope, BlogService, $location) {
					console.log("BlogController.js....")

					var self = this;
					self.user = {
						id : '',
						title : '',
						description : ''
					};
					self.blogs = [];

					fetchAllBlogs()

					function fetchAllBlogs() {
						BlogService.fetchAllBlogs().then(function(data) {
							self.blogs = data;
						}, function(errResponse) {
							console.error('Error while fetching the Blogs');
						})
					}

					function createBlog(user) {
						alert("inside createBlog of BlogController")
						BlogService.createBlog(user).then(

						self.fetchAllBlogs,

						function(errResponse) {
							console.error('Error while creating Blog');
						})
					}

					function deleteBlog2(user) {
						console.log("inside DeleteBlog2 with blogid= ", user)
						BlogService.DeleteBlog(user).then(

						self.fetchAllBlogs,

						function(errResponse) {
							console.error('Error while deleting Blog');
						})
					}

					self.deleteBlog = function(user) {
						alert("Inside Blog Delete Controller");

						console.log('Deleting blog with blogid= ', user);
						deleteBlog2(user);

					};

					function updateBlog(identity) {

						BlogService.updateBlog(user, id).then(
								self.fetchAllUsers, function(errResponse) {
									console.error('Error while updating User');
								})
					}

					self.submit = function() {
						alert("Inside Blog Controller");
						if (self.user.id == null) {
							console.log('Saving New Blog', self.user);
							createBlog(self.user);
						} else {
							console.log('Saving new blog 2', self.user);
							createBlog(self.user);
							$location.path('/test');

						}
						self.reset();
					};

					self.reset = function() {
						self.user = {
							id : '',
							title : '',
							description : ''
						};
						$scope.myForm.$setPristine();
					}

				} 
		]
);