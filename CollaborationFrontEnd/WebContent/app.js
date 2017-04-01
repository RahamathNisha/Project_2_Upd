//var app = angular.module('myApp', [ 'ngRoute','ngCookies','ngFileUpload' ]);
var app = angular.module('myApp', [ 'ngRoute', 'ngCookies' ]);
app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'c_home/main.html'

	})

	.when('/manageUser', {
		templateUrl : 'c_admin/manage_users.html',
		controller : 'AdminController'
	})

	.when('/event', {
		templateUrl : 'c_upload/upload.html',
		controller : 'FileUploadController'
	})

	.when('/login', {
		templateUrl : 'c_user/login.html',
		controller : 'UserController'
	})

	.when('/logout', {
		templateUrl : 'c_user/login.html',
		controller : 'UserController'
	})

	.when('/reg', {
		templateUrl : 'c_user/register.html',
		controller : 'UserController'
	})

	.when('/myProfile', {
		templateUrl : 'c_user/my_profile.html',
		controller : 'UserController'
	})

	/**
	 * Blog related mapping
	 */

	.when('/Blog', {
		templateUrl : 'c_blog/BlogAdd.html',
		controller : 'BlogController'
	})

	.when('/BlogView', {
		templateUrl : 'c_blog/BlogView.html',
		controller : 'BlogController'
	})

	.when('/Job', {
		templateUrl : 'c_job/frmJob.html',
		controller : 'JobController'
	})

	.when('/JobList', {
		templateUrl : 'c_job/frmListJob.html',
		controller : 'JobController'
	})

	.when('/JobApply', {
		templateUrl : 'c_job/frmViewMyJobApply.html',
		controller : 'JobController'
	})

	/**
	 * Friend related mapping
	 */

	.when('/add_friend', {
		templateUrl : 'c_friend/add_friend.html',
		controller : 'FriendController'
	})

	.when('/search_friend', {
		templateUrl : 'c_friend/search_friend.html',
		controller : 'FriendController'
	})

	.when('/view_friend', {
		templateUrl : 'c_friend/view_friend.html',
		controller : 'FriendController'
	})

	.when('/chat', {
		templateUrl : 'c_chat/ChatView.html',
		controller : 'ChatController'
	})
	/* Forum without chat */
	.when('/Forum', {
		templateUrl : 'c_forum/frmForum.html',
		controller : 'ForumController'
	})

	.when('/ForumView', {
		templateUrl : 'c_forum/frmUserForumAdmin.html',
		controller : 'ForumController'
	})

	.when('/listForum', {
		templateUrl : 'c_forum/frmListForum.html',
		controller : 'ForumController'
	})

	.when('/EditForum/:id', {
		templateUrl : 'c_forum/ForumEdit.html',
		controller : 'ForumController'
	})

	/**
	 * Chat Forum related mapping
	 */

	.when('/create_chat_forum', {
		templateUrl : 'c_chat_forum/create_chat_forum.html',
		controller : 'ChatForumController'
	})

	.when('/list_chat_forum', {
		templateUrl : 'c_chat_forum/list_chat_forum.html',
		controller : 'ChatForumController'
	})

	.when('/view_chat_forum', {
		templateUrl : 'c_chat_forum/view_chat_forum.html',
		controller : 'ChatForumController'
	})

	.otherwise({
		redirectTo : '/'
	});
});

app.run(function($rootScope, $location, $cookieStore, $http) {

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		console.log("$locationChangeStart")
		var restrictedPage = $.inArray($location.path(), [ '//', '/home.html',
				'/', '/search_job', '/view_blog', '/login', '/reg',
				'/list_blog' ]) === -1;

		console.log("restrictedPage:" + restrictedPage)
		var loggedIn = $rootScope.currentUser.id;
		$rootScope.username = loggedIn;
		console.log("loggedIn:" + loggedIn + " " + $rootScope.username)

		if (!loggedIn) {

			if (restrictedPage) {
				console.log("Navigating to login page:")

				$location.path('/login');
			}
		}

		else {

			var role = $rootScope.currentUser.role;
			var userRestrictedPage = $.inArray($location.path(),
					[ "/post_job" ]) == 0;

			if (userRestrictedPage && role != 'admin') {

				alert("You can not do this operation as you are logged as : "
						+ role)

			}

		}

	});
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};

	if ($rootScope.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic '
				+ $rootScope.currentUser;
	}

});