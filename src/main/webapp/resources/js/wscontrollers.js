
survey.controller('WebsocketController', function($scope,adminService){

    $scope.init= function(){
       $scope.connect();
    };

    $scope.connect = function(){
        var ws = null;
        var url = 'ws://' + window.location.host + "/surveyWS";

       ws =   new WebSocket(url);

        ws.onopen = function () {
            log('Info: connection opened.');
            $scope.isConnected= true;
        };
        ws.onmessage = function (event) {
            adminService.QuizLoaded();
            log('Received: ' + event.data);
        };
        ws.onclose = function (event) {
            $scope.isConnected= false;;
            log('Info: connection closed.');
            log(event);
        };
    };

    $scope.disconnect = function() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        $scope.isConnected= false;
    };

    $scope.echo = function() {
        if (ws != null) {
            var message = document.getElementById('message').value;
            log('Sent: ' + message);
            ws.send(message);
        } else {
            alert('connection not established, please connect.');
        }
    };

    function log(message) {
        console.log(message);
    }

});

