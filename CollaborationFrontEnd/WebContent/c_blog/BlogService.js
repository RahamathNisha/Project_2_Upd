'use strict';

app.factory('BlogService', [
		'$http',
		'$q',
		'$location',
		function($http, $q, $location) {
			console.log("BlogService..")

			var BASE_URL = 'http://localhost:8081/CollaborationBackEnd/';

			var factory = {
				fetchAllBlogs : fetchAllBlogs,
				createBlog : createBlog,
				DeleteBlog : DeleteBlog
			};

			return factory;

			function fetchAllBlogs() {
				var deferred = $q.defer();

				$http.get(BASE_URL + '/BlogDetails/').then(function(response) {
					deferred.resolve(response.data);
				}, function(errResponse) {
					console.error('Error while fetching Forums!');
					deferred.reject(errResponse);
				});

				return deferred.promise;

			}

			function createBlog(user) {
				alert('inside Blog Service.jsp');
				console.log('inside blog service with ' + user)
				return $http.post(BASE_URL + '/BlogSave/', user)

				.then(function(response) {
					return response.data;

				}, function(errResponse) {
					console.error('Error while creating user');
					return $q.reject(errResponse);
				});
			}

			function DeleteBlog(user) {
				alert('inside Blog Service.DeleteBlog');
				console.log('inside blog service with ' + user)
				return $http.post(BASE_URL + 'admin/delete/' + user).then(
						function(response) {
							return response.data;

						}, function(errResponse) {
							console.error('Error while creating user');
							return $q.reject(errResponse);
						});
			}

			function updateBlog(user) {
				alert("inside Blog Service.jsp");
				console.log('inside blog service with ' + user)
				return $http.post(BASE_URL + '/update{username}/', user).then(
						function(response) {
							return response.data;

						}, function(errResponse) {
							console.error('Error while creating user');
							return $q.reject(errResponse);
						});
			}

		} 
		]
);