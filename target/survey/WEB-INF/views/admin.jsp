<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/foundation.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/survey.css"/>">

</head>

<body>
<div ng-app="survey">
    <div class="text-center">
        <img src="/resources/images/R.jpeg" style="height:100px;width:100px;margin:auto;"/>
        <img src="/resources/images/E.jpeg" style="height:100px;width:100px;margin:auto;"/>
        <img src="/resources/images/D.jpeg" style="height:100px;width:100px;margin:auto;"/>
        <img src="/resources/images/H.jpeg" style="height:100px;width:100px;margin:auto;"/>
        <img src="/resources/images/A.jpeg" style="height:100px;width:100px;margin:auto;"/>
        <img src="/resources/images/T.jpeg" style="height:100px;width:100px;margin:auto;"/>
    </div>
    <br/>

    <h1 style="color:red">
        <div class="text-center">
            S U R V E Y - A D M I N
        </div>
    </h1>
    <br/>


    <div ng-controller="AdminController" ng-init="init();">

        <div class="text-center"><h4>현재 Survey에 접속한 사람 : {{connectedPeopleCount }} 명</h4></div>


        <h3>Quiz
            <small>(Selected :<strong>{{quizSelected.number}} </strong> )</small>
        </h3>
        <div class="input-group" ng-init="quizList()">
            <select class="form-control" data-ng-model="quizSelected"
                    ng-options="quiz.number for quiz in quizList">
                <option value="">선택하세요</option>
            </select>
                    <span class="input-group-btn">
                    <button class="btn btn-danger" type="button"
                            data-ng-click="publishQuiz()">Publish Quiz
                    </button>
                    </span>
        </div>
        <br/>

        <div style="color: #fff">{{quizSelected.quizDescription}}</div>

        <button class="hidden" ng-click="QuizBoxChecked">Toggle collapse</button>
        <%--<div class="check-element sample-show-hide" ng-show="QuizBoxChecked" style="clear:both;">--%>
        <h3><span class="label label-default">Q.</span></h3>


        <div collapse="QuizBoxChecked">
            <div class="well well-lg text-center" style="font-size: 40px">{{quizDescription}}
            </div>
        </div>
        <div ng-show="!QuizBoxChecked">
            <div class="col-md-9">
                <progressbar class="progress-striped active" value="(electedPeopleCount*100/ connectedPeopleCount)"
                             max="100" type="warning">
                    {{electedPeopleCount}}
                </progressbar>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <div class="btn-group" dropdown>
                        <button type="button" class="btn btn-danger" ng-click="disposeNotElectedPeerList()"
                                alt="선택하지 않은 사람찾기">dispose
                        </button>
                        <button type="button" class="btn btn-danger dropdown-toggle" dropdown-toggle>
                            <span class="caret"></span>
                            <span class="sr-only">Split button!</span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <div ng-repeat="notElectedPeer in notElectedPeerList">
                                <li>
                                    {{notElectedPeer}}
                                </li>
                            </div>
                        </ul>
                    </div>


                    <button class="btn btn-danger" type="button"
                            data-ng-click="showResult()">Show Result
                    </button>
                    <button class="btn btn-danger" type="button"
                            data-ng-click="reset()">Reset
                    </button>
                </div>
            </div>
        </div>


        <div ng-init="ResultBoxChecked=false">
            <div class="sample-show-hide align" ng-show="ResultBoxChecked" style="clear:both;">
                <div class="col-md-6 col-md-offset-3">
                    <table>
                        <tr ng-repeat="person in resultList">
                            <td>
                                <rank candidate="person"></rank>
                            </td>
                        </tr>
                    </table>
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
<script language="javascript" src="<c:url value="/resources/js/app.js"/>" charset="utf-8"></script>
<script language="javascript" src="<c:url value="/resources/js/controller.js"/>" charset="utf-8"></script>
<script language="javascript" src="<c:url value="/resources/js/service.js"/>" charset="utf-8"></script>

</html>