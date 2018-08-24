app.controller("indexController",function ($http,$scope,loginService) {
    $scope.showloginName=function () {
        loginService.loginName().success(function (responce) {
            $scope.loginName=responce.loginName;
        })
    }
})