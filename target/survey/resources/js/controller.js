


survey.controller("AdminController", function ($scope, wsFactory, adminService) {

    init();

    function init() {
        wsFactory.connect();
        $scope.electBtnDisabled = true;
        $scope.QuizBoxChecked = true;
        $scope.max = 0;
        $scope.notElectedPeerList = {userName: "No One"};

    }
    $scope.quizList = function () {
        console.log({
            type: "quizList"
        });

        adminService.quizList()
            .then(
            function (payload) {
                console.log(payload);
                $scope.quizList=payload.quizList;
            },
            function (errorPayload) {
                console.log(errorPayload);
            });
    };

    $scope.publishQuiz = function () {
//set user info
        if ($scope.quizSelected == null) {
            alert("퀴즈를 선택해 주세요ㅣ");
            return;
        }
        console.log({
            quizNumber: $scope.quizSelected.number
        });

        adminService.publishQuiz({
            "number": $scope.quizSelected.number
        }).then(
            function (payload) {
                console.log(payload);

            },
            function (errorPayload) {
                console.log(errorPayload);
            });
        $scope.ResultBoxChecked = false;  // 결과 박스 닫기
        $scope.QuizBoxChecked = false;

    };


    $scope.peerList = function () {
//set user info
        console.log({
            msg: "call peerList()"
        });

        adminService.peerList()
            .then(
            function (payload) {
                console.log(payload);
//                console.log(peerList.peer);
                $scope.peerList = payload.peerList;
//                $scope.peerList =JSON.parse(peerList);
//                $scope.peerList = payload.peerList;
            },
            function (errorPayload) {
                console.log(errorPayload);
            });
        //visible input box
//        $scope.chatInputChecked = true;
    };

    $scope.electQuiz = function () {
        if ($scope.SelectedPerson == null) {
            alert("사람을 선택해 주세요ㅣ");
            return;
        }
        console.log({
            userName: $scope.userName,
            electedPerson: $scope.SelectedPerson.id,
            quizNumber: $scope.quizNumber,
        });

        adminService.electQuiz({
            userName: $scope.userName,
            electedPerson: $scope.SelectedPerson.id,
            quizNumber: $scope.quizNumber

        }).then(
            function (payload) {
                console.log(payload);
                $scope.electBtnDisabled = true;
                $scope.QuizBoxChecked = true;  //퀴즈 박스 닫기

            },
            function (errorPayload) {
                console.log(errorPayload);
            });
        //visible input box

    };

    $scope.showResult = function () {
//set user info
        console.log({
            quizNumber: $scope.quizSelected.number
        });

        adminService.showResult({
            "number": $scope.quizSelected.number
        }).then(
            function (payload) {
                console.log(payload);
                $scope.ResultBoxChecked = true;  // 결과 박스 열기
                $scope.quizDescription = payload.quizDescription;
                $scope.resultList = payload.resultList;
            },
            function (errorPayload) {
                console.log("ERROR");
                console.log(errorPayload);
            });
        //visible input box
//        $scope.chatInputChecked = true;
    };

    $scope.disposeNotElectedPeerList = function () {
//set user info
        console.log({
            type: "disposeNotElectedPeerList"
        });

        adminService.disposeNotElectedPeerList()
            .then(
            function (payload) {
                console.log(payload);
                console.log(payload.notElectedPeerList);
                $scope.notElectedPeerList = payload.notElectedPeerList;  // 결과 박스 열기
            },
            function (errorPayload) {
                console.log("ERROR");
                console.log(errorPayload);
            });

    };


    $scope.reset = function(){
        $scope.QuizBoxChecked = true;
        $scope.max = 0;
        $scope.notElectedPeerList = {userName: "No One"};
        $scope.electedPeopleCount = 0;
        $scope.ResultBoxChecked = false;
    }




    wsFactory.subscribe(function (message) {
        if (message.type == "publishQuiz") {
            $scope.QuizBoxChecked = false;
            $scope.electBtnDisabled = false;
            $scope.quizDescription = message.quizDescription;
            $scope.quizNumber = message.quizNumber;
            $scope.userName = message.userName;

        } else if (message.type == "connectedPeopleCount") {
            $scope.connectedPeopleCount = message.connectedPeopleCount;
        } else if (message.type == "electedPeopleCount") {
            $scope.electedPeopleCount = message.electedPeopleCount;
        }

        $scope.$apply();

    });




})



survey.directive("rank", function () {
    return {
        restrict: 'E',
        scope: {
            person: '=candidate'
        },
        transclude: false,

        link: function (scope, attrs) {
            scope.engName = scope.person.userVo.engName;
            scope.korName = scope.person.userVo.korName;

            if (scope.person.rank == 'FIRST_CLASS') {
                scope.medal = "gold";
            } else if (scope.person.rank == 'SECOND_CLASS') {
                scope.medal = "silver";
            } else {
                scope.medal = "bronze";
            }
            scope.rateOpen = function () {
                scope.dynamic = scope.person.rate;
            }

        },

        template: '<div ng-init="faceClose=true">' +
            '<div class="col-md-2">' +
            '<img ng-src="/resources/images/{{medal}}.jpg" class="btn" ng-click="rateOpen()" />' +
            '</div>' +

            '<div class="col-md-8 " ng-init="dynamic=0;max=100" style="margin-top:50px">' +
            '<progressbar max="max" value="dynamic" type="info">' +
            '<span style="color:black; white-space:nowrap;">{{dynamic}} %</span>' +
            '</progressbar>' +
            '</div>' +

            '<div class="col-md-2" style="margin-top:15px">' +
            '<img src="resources/images/redhat_door.jpg" ng-click="faceClose=!faceClose" ng-show="faceClose"/>' +
            '<img ng-src="/resources/images/{{engName}}.jpg" ng-show="!faceClose" />' +
            '<p ng-show="!faceClose" style="text-align: center"> {{ korName }} </p></div>' +
            '</div>'

    };
});









