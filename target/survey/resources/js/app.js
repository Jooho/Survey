//setInterval(function () {alert("Hello")}, 3000);

var urlBase = "/";

var survey = angular.module('survey', ['ui.bootstrap','ngAnimate','ngRoute']);

survey.config(function ($routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
//	$routeProvider
//		.when('/main',
//			{
//				controller: 'PingSearchController',
//				templateUrl: urlBase + 'ping-search'
//			})
//		.when('/write',
//			{
//				controller: 'PingQuestionController', 
//				templateUrl: urlBase + 'ping-write'
//			})
//		.otherwise({ redirectTo: '/main' });
});
