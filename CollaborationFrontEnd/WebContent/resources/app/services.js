angular.module("chatApp.services").service("ChatService", function($q, $timeout)
//app.factory('ChatService', ['$http', '$q', '$location',function($http,$q,$location)
{
	console.log("Inside ChatService");
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/MyChatApp/";
    service.CHAT_TOPIC = "/topic/message";
    //service.CHAT_BROKER = "/app/chat";
    service.CHAT_BROKER = "/MyChatApp/chat";
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(message)
    //function send(message)
    {
    	
    	console.log('inside ChatService.send');
    	var id = Math.floor(Math.random() * 1000000);
    	console.log('@@@'+id);
    	socket.stomp.send(service.CHAT_BROKER, {priority: 9
        }, JSON.stringify({
          message: message,
          id: id
        }));
    	messageIds.push(id);
    };
    
    
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.message;
      out.time = new Date(message.time);
      if (_.contains(messageIds, message.id)) {
        out.self = true;
        messageIds = _.remove(messageIds, message.id);
      }
      return out;
    };
    
    var startListener = function() {
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