<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Example - example-example89-production</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/foundation.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
    <style>
        .sample-show-hide {
            padding: 10px;
            border: 1px solid black;
            background: white;
        }

        .sample-show-hide {
            -webkit-transition: all linear 0.5s;
            transition: all linear 0.5s;
        }

        .sample-show-hide.ng-hide {
            opacity: 0;
        }

        .css-class-add, .css-class-remove {
            -webkit-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            -moz-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            -o-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
        }

        .css-class,
        .css-class-add.css-class-add-active {
            color: red;
            font-size: 3em;
        }

        .css-class-remove.css-class-remove-active {
            font-size: 1.0em;
            color: black;
        }

        .toggle {
            -webkit-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            -moz-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            -o-transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
            transition: all cubic-bezier(0.250, 0.460, 0.450, 0.940) 0.5s;
        }

        .toggle.ng-enter {
            opacity: 0;
        }

        .toggle.ng-enter-active {
            opacity: 1;
        }

        .toggle.ng-leave {
            -webkit-transition: all linear 0.5s;
            transition: all linear 0.5s;
        }

        .toggle.ng-leave-active {
            opacity: 0;
        }
    </style>

</head>
<body ng-app="survey">

<div ng-controller="CarouselDemoCtrl as app">
    <div style="height: 305px">
        <carousel interval="myInterval">
            <slide ng-repeat="slide in slides" active="slide.active">
                <img ng-src="{{slide.image}}" style="height:100px;width:100px;margin:auto;"/>

                <div class="carousel-caption">
                    <h4>Slide {{$index}}</h4>

                    <p>{{slide.text}}</p>
                </div>
            </slide>
        </carousel>
    </div>
    <div class="row">
        <div class="col-md-6">
            <button type="button" class="btn btn-info" ng-click="addSlide()">Add Slide</button>
        </div>
        <div class="col-md-6">
            Interval, in milliseconds: <input type="number" class="form-control" ng-model="myInterval">
            <br/>Enter a negative number or 0 to stop the interval.
        </div>


        <div class="row" ng-init="testCheck=true">
            <div class="col-md-2"><img ng-src="/resources/images/gold.jpg" class="btn"
                                       ng-click="random()"/></div>

            <div class="col-md-8" ng-init="dynamic=0">
                <progressbar max="max" value="dynamic"><span style="color:black; white-space:nowrap;">{{dynamic}} / {{max}}</span>
                </progressbar>
            </div>

            <div class="col-md-2"><img src="/resources/images/redhat_door.jpg" ng-click="testCheck=!testCheck"
                                       ng-show="testCheck"/><img ng-src="/resources/images/jlee.jpg" ng-show="!testCheck" ng-click="enlarge()"/></div>
        </div>


    </div>
    <button ng-click="app.toggle=!app.toggle">Toggle Animation</button>

    <div ng-if="app.toggle">out side of content
        <div class="toggle" ng-if="!app.toggle">Some content here</div>
    </div>
    <div class="alertbox toggle" ng-if="!app.toggle">test</div>


</div>


</body>
<script src="<c:url value="/resources/js/angular.js"/>"></script>
<script src="<c:url value="/resources/js/angular-animation.js"/>"></script>
<%--<script src="<c:url value="/resources/js/test.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/service.js"/>"></script>--%>
<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.12.0.js"/>"></script>
<script src="<c:url value="/resources/js/angular-wurfl-images-tailor.js"/>"></script>


<%--<script src="../../resources/js/angular.js"/>--%>
<%--<script src="../../resources/js/angular-animation.js"/>--%>
<%--<script src="../../resources/js/test.js"/>--%>
<%--<script src="../../resources/js/service.js"/>--%>
<%--<script src="../../resources/js/ui-bootstrap-tpls-0.12.0.js"/>--%>


<script>
    var survey = angular.module('survey', ['ui.bootstrap', 'angular-wurfl-image-tailor', 'ngAnimate']);

    survey.controller('CarouselDemoCtrl', function ($scope) {
        $scope.myInterval = 5000;
        $scope.max = 200;
        var slides = $scope.slides = [];
        $scope.addSlide = function (name) {
            slides.push({
                image: '/resources/images/' + name + '.jpg',
                text: ['clee', 'dban', 'echo', 'eyi'][slides.length % 4] + ' ' +
                        ['1', '2', '3', '4'][slides.length % 4]
            });
        };
        for (var i = 0; i < 4; i++) {
            $scope.addSlide(imageName[i].name);
        }
        this.toggle = true;

        $scope.random = function () {
            var value = Math.floor((Math.random() * 100) + 1);
            var type;

            if (value < 25) {
                type = 'success';
            } else if (value < 50) {
                type = 'info';
            } else if (value < 75) {
                type = 'warning';
            } else {
                type = 'danger';
            }

            $scope.showWarning = (type === 'danger' || type === 'warning');

            $scope.dynamic = value;
            $scope.type = type;
        };
//        $scope.random();


    });

    var imageName = [
        {name: 'clee'},
        {name: 'dban'},
        {name: 'echo'},
        {name: 'eyi' }
    ];


</script>
</html>