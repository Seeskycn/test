app.controller("brandController",function ($scope,$http,$controller,brandService) {
    $controller('baseController',{$scope:$scope}) //继承
    $scope.findAll=function () {
        $http.get('../brand/findAll.do').success(function (response) {
            $scope.list=response;
        })
    }

    /*无条件分页查询*/
    $scope.findPage = function (page,pageSize) {
        brandService.findPage(page,pageSize).success(function (response) {
            $scope.list=response.rows;
            $scope.paginationConf.totalItems=response.total;
        })
    }
    //增加
    $scope.save=function () {
        var Object=brandService.add($scope.entity);
        if($scope.entity.id != null){
            Object = brandService.update($scope.entity)
        }
        Object.success(function (response) {
            if(response.success){
                $scope.reLoad();
            }else{
                alert(response.message);
            }
        })
    }

    //按id查询
    $scope.findOne= function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity=response;
        })
    }



    //删除方法
    $scope.dele=function () {
        brandService.dele($scope.delIds).success(function (response) {
            if(response.success){
                $scope.reLoad();
            }else{
                alert(response.message);
            }
        })
    }
    //自定义查询对象
    $scope.searchEntity={};
    //查询
    $scope.search=function (page,pageSize) {
        brandService.search(page,pageSize,$scope.searchEntity).success(function (response) {
            $scope.paginationConf.totalItems=response.total;
            $scope.list=response.rows;
        })
    }
})