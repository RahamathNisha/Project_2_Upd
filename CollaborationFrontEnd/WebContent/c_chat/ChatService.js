app.service('ChatService', function($q, $timeout) {
	console.log("Inside ChatService");

	var service = {}, listener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/CollaborationBackEnd/chat";
	service.CHAT_TOPIC = "/topic/message";
	service.CHAT_BROKER = "/app/chat";

	service.receive = function() {
		console.log("Recieve")
		return listener.promise;
	};

	service.send = function(message) {
		console.log('inside ChatService.send')
		console.log('Message ' + message)
		var id = Math.floor(Math.random() * 1000000);
		console.log('@@@' + id);
		socket.stomp.send(service.CHAT_BROKER, {
			priority : 9
		}, JSON.stringify({
			message : message,
			id : id
		}));
		messageIds.push(id);
		console.log('inside chat service ' + messageIds + " " + message)
	};

	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getMessage = function(data) {
		console.log("getmessage")
		var message = JSON.parse(data), out = {};
		out.message = message.message;
		out.time = new Date(message.time);
		return out;
	};

	var startListener = function() {
		console.log("recieve")
		socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
			listener.notify(getMessage(data.body));
		});
	};

	var initialize = function() {
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	initialize();
	return service;
});