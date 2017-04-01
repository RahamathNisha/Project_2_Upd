'use strict';

app
		.controller(
				'ForumController',
				[
						'$scope',
						'ForumService',
						'$location',
						'$rootScope',
						'$window',
						function($scope, ForumService, $location, $rootScope,
								$window) {

							console.log("user forum controller");

							var self = this;
							self.forum = {
								forumid : '',
								approve : '',
								forumcategory : '',
								createdate : '',
								description : '',
								modifiedat : '',
								useremail : '',
								title : '',
								likes : '',
								countcmts : ''
							};

							self.forumcmt = {
								comments : '',
								dateofcomments : '',
								forumid : '',
								id : '',
								useremail : ''
							}
							self.forums = [];
							self.forumscat = [];
							self.forumcmts = [];
							self.flag = false;

							self.fetchAllForums = function() {
								ForumService
										.fetchAllForums()
										.then(
												function(d) {
													self.forums = d;
													console
															.log("Fetch all user forums")
												},
												function(errResponse) {
													console
															.error('Error while fetching User Forums'
																	+ errResponse);
												});
							};

							self.fetchAllForums();

							self.fetchAllForumsCategory = function() {
								ForumService
										.fetchAllForumsCategory()
										.then(
												function(d) {
													self.forumscat = d;
													console
															.log("Fetch all user forums cataegory")
												},
												function(errResponse) {
													console
															.error('Error while fetching User Forums Category'
																	+ errResponse);
												});
							};

							self.readSelectedForum = function(forumid) {
								ForumService
										.readSelectedForum(forumid)
										.then(
												function(d) {
													ForumService
															.readSelectedForumComments(
																	forumid)
															.then(
																	function(
																			data) {
																		self.forumcmts = data;
																	});
													self.forums = d;
													self.flag = true;
													console
															.log("Fetch selected"
																	+ forumcmts.useremail)
												},
												function(errResponse) {
													console
															.error('Error while fetching User Forums Category'
																	+ errResponse);
												});
							};

							self.fetchAllForumsCategory();

							self.createNewForum = function(forum) {
								ForumService
										.createNewForum(forum)
										.then(
												self.reset,
												function(errResponse) {
													console
															.error('Error while creating record');
												});
							};

							self.createNewForumComment = function(forumcmt) {
								ForumService
										.createNewForumComment(forumcmt)
										.then(
												self.reset,
												self.flag = false,
												function(errResponse) {
													console
															.error('Error while creating record');
												});
							};

							self.likeupdate = function(forumid) {
								ForumService
										.likeupdate(forumid)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while updating likes');
												});
							};

							self.approvedforum = function(forumid) {
								ForumService
										.approvedforum(forumid)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while approving forum');
												});
							};

							self.rejectedforum = function(forumid) {
								ForumService
										.rejectedforum(forumid)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while approving forum');
												});
							};

							self.deleteforum = function(forumid) {
								ForumService
										.deleteforum(forumid)
										.then(
												self.fetchAllForums,
												function(errResponse) {
													console
															.error('Error while approving blog');
												});
							};

							self.likeclick = function(forumid) {
								self.likeupdate(forumid);
							};

							self.approveforum = function(forumid) {
								if (confirm('Are you sure you want to approve this forum?')) {
									self.approvedforum(forumid);
								}
							};

							self.rejectforum = function(forumid) {
								if (confirm('Are you sure you want to reject this forum?')) {
									self.rejectedforum(forumid);
								}
							};

							self.submit = function() {
								self.createNewForum(self.forum);
								console.log("Saving new forum", self.forum);
							};

							self.submitcomment = function(forumid) {
								self.forumcmt.forumid = forumid;
								self.forumcmt.useremail = $rootScope.currentUser.email;
								self.forumcmt.id = $rootScope.currentUser.id;

								self.createNewForumComment(self.forumcmt);
								console.log("Saving new forum comment",
										self.forumcmt);
							};

							self.reset = function() {
								self.forum = {
									forumid : '',
									approve : '',
									forumcategory : '',
									createdate : '',
									description : '',
									modifiedat : '',
									useremail : '',
									title : '',
									likes : '',
									countcmts : ''
								};
								self.forumcmt = {
									comments : '',
									dateofcomments : '',
									forumid : '',
									id : '',
									useremail : ''
								}
								$scope.myForm.$setPristine();
							};

							self.getselected = function(forumid) {
								self.readSelectedForum(forumid);
							};

							self.openChildWindow = function() {
								alert("hi");
								$window.open('c_forum/frmAddComment.html',
										'_self');
							};

							self.delforum = function(forumid) {
								if (confirm('Are you sure you want to disable selected Forum?')) {
									self.deleteforum(forumid);
								}
							};
						} 
			]
);