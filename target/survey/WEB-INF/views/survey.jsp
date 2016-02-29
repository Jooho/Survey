<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<html>
<head>
    <title>AngularJS Tutorials</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/foundation.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/survey.css"/>">

</head>

<body>
<div class="surveyContainer">
    <div ng-app="survey">
        <input type="hidden" value="{{quizNumber}}"/>
        <input type="hidden" value="{{userName}}"/>

        <div class="jumbotron text-center">
            <h1>Welcome to <span style="color: red">Survey</span></h1>

            <p>Please wait for downloading question.</p>

            <p><a href="#" class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
        </div>


        <div ng-controller="AdminController" ng-init="init()">
            <button class="hidden" ng-click="QuizBoxChecked">Toggle collapse</button>
            <%--<div class="check-element sample-show-hide" ng-show="QuizBoxChecked" style="clear:both;">--%>
            <h3><span class="label label-default">Q.</span></h3>

            <div collapse="QuizBoxChecked">
                <div class="well well-lg">{{quizDescription}}
                </div>
                <h3>
                    <small for="personSelected">누가 제일 이미지가 비슷한가요? 선택해주세요:</small>
                </h3>
            </div>

            <%--</div>--%>


            <div class="input-group">

                <select class="form-control" id="personSelected" ng-model="SelectedPerson"
                        ng-disabled="electBtnDisabled"
                        ng-options="peer.korName for peer in peerList" ng-init="peerList()">
                    <option value="">선택하세요</option>
                </select>
			<span class="input-group-btn">
				<button class="btn btn-danger" type="button" ng-disabled="electBtnDisabled" data-ng-click="electQuiz()">
                    선택
                </button>
            </span>
            </div>
            <br>

            <div ng-if="SelectedPerson != null">
                <div ng-show="electBtnDisabled">
                    <div ng-show="QuizBoxChecked">
                        <div style=" text-align: center;">
                            <h3>감사합니다.</h3> <br/>
                            <h4>
                    <span style="color: blue">
                        <strong>{{SelectedPerson.korName}}</strong>
                    </span>님을
                            </h4> 선택하셨습니다.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mastfoot">
        <div class="inner">
            <p>Survey game for <a href="http://redhat.com">Red Hat</a>, by <a href="http://github.com/jooho">Jooho</a>.
            </p>
            <img src="/resources/images/logo-red-hat-black.png"/>
        </div>
    </div>
</div>
</body>
<script src="<c:url value="/resources/js/angular.js"/>"></script>
<script src="<c:url value="/resources/js/angular-animation.js"/>"></script>
<script src="<c:url value="/resources/js/angular-route.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/ui-bootstrap-tpls-0.12.0.js"/>"></script>
<script language="javascript" src="<c:url value="/resources/js/app.js"/>" charset="UTF-8"></script>
<script language="javascript" src="<c:url value="/resources/js/service.js"/>" charset="UTF-8"></script>
<script language="javascript" src="<c:url value="/resources/js/controller.js"/>" charset="UTF-8"></script>


</html>

