survey.service('adminService', function($http, $q) {
    this.quizList = function(){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/quizList.ajax',
            method: "POST",
            data: "",
            headers: {'Content-Type': 'application/json'}
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };
    this.publishQuiz = function(objPing){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/publishQuiz.ajax',
            method: "POST",
            data: objPing,
            headers: {'Content-Type': 'application/json'}
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };

    this.peerList = function(){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/peerList.ajax',
            method: "POST",
            data: "",
            contentType:"application/json; charset=EUC_KR"
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };
    this.electQuiz = function(objPing){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/electQuiz.ajax',
            method: "POST",
            data: objPing,
            headers: {'Content-Type': 'application/json'}
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };

    this.showResult =  function(objPing){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/showResult.ajax',
            method: "POST",
            data: objPing,
            headers: {'Content-Type': 'application/json'}
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };

    this.disposeNotElectedPeerList =  function(){
        var deferred = $q.defer();
        $http({
            url: urlBase+'admin/disposeNotElectedPeerList.ajax',
            method: "POST",
            data: "",
            headers: {'Content-Type': 'application/json'}
        }).success(function(result){
            deferred.resolve(result);
        }).error(function(msg, code){
            deferred.reject(msg);
        });
        return deferred.promise;
    };

});

survey.factory('wsFactory', ['$q', '$rootScope', function ($q, $rootScope) {
    // We return this object to anything injecting our service
    var Service = {};
    // Keep all pending requests here until they get responses
    var callbacks = {};
    // Create a unique callback ID to map requests to responses
    var currentCallbackId = 0;
    // Create our websocket object with the address to the websocket

    var data;

    var ws = null;

    Service.connect = function() {
        var url = 'ws://' + window.location.host + "/surveyWS";

        ws = new WebSocket(url);

        ws.onopen = function () {
            console.log('Info: connection opened.');
        };
        ws.onmessage = function (event) {
            listener(JSON.parse(event.data));
            console.log('Received: ' + event.data);
        };
        ws.onclose = function (event) {
            console.log('Info: connection closed.');
        };
    }



    function sendRequest(request) {
        var defer = $q.defer();
        var callbackId = getCallbackId();
        callbacks[callbackId] = {
            time: new Date(),
            cb: defer
        };
        request.callback_id = callbackId;
        console.log('Sending request', request);
        ws.send(JSON.stringify(request));
        return defer.promise;
    }

    function listener(data) {
        var messageObj = data;
        console.log("Received data from websocket: ", messageObj);
        Service.callback(messageObj);
        console.log("inside data:"+ data);

        // If an object exists with callback_id in our callbacks object, resolve it
//        if (callbacks.hasOwnProperty(messageObj.callback_id)) {
//            console.log(callbacks[messageObj.callback_id]);
//            $rootScope.$apply(callbacks[messageObj.callback_id].cb.resolve(messageObj.data));
//            delete callbacks[messageObj.callbackID];
//        }
    }

    // This creates a new callback ID for a request
    function getCallbackId() {
        currentCallbackId += 1;
        if (currentCallbackId > 10000) {
            currentCallbackId = 0;
        }
        return currentCallbackId;
    }

    function waitForSocketConnection(socket, callback) {
        setTimeout(
            function () {
                if (ws.readyState === 1) {
                    console.log("Connection is made")
                    if (callback != null) {
                        callback();
                    }
                    return;

                } else {
                    console.log("wait for connection...")
                    waitForSocketConnection(socket, callback);
                }

            }, 5); // wait 5 milisecond for the connection...
    }

    // Define a "getter" for getting customer data
    Service.getCustomers = function () {
        var request = {
            type: "get_customers"
        }
        var promise = null;
        // Storing in a variable for clarity on what sendRequest returns
        console.log("What?" + ws.readyState);

        waitForSocketConnection(ws, function () {
            promise = sendRequest(request);
        })
        console.log("promise :" + promise);
        return promise;
    }

    Service.subscribe = function(callback) {
        Service.callback = callback;
    }

    return Service;
}])