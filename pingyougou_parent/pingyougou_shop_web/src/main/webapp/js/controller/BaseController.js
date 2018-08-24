app.controller('baseController',function ($scope) {

    $scope.paginationConf={
        currentPage : 1,
        totalItems : 10,
        itemsPerPage : 10,
        perPageOptions : [10 ,20 ,30 ,40 ,50],
        onChange : function () {
            $scope.reLoad();
        }
    }

    $scope.reLoad = function () {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }

    //删除数据的 ids
    $scope.delIds=[];
    //往数组中添加或删除id
    $scope.setIds=function ($event,id) {
        if($event.target.checked){
            //$event.target : 获取当前对象
            $scope.delIds.push(id); //添加
        }else{
            var index = $scope.delIds.indexOf(id); //获取索引
            $scope.delIds.splice(index,1); //删除
        }
    }

    $scope.jsonToString=function (jsonString,key) {
        var array = new Array();
        var json = JSON.parse(jsonString);
        for (var i = 0 ;i<json.length;i++) {
            array.push(json[i][key])
        }
        return array.join(",");
        
    }

})