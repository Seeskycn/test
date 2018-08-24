app.service('brandService',function ($http) {
    this.findPage = function (page,pageSize) {
        return $http.get('../brand/findPage.do?page=' + page + '&pageSize=' + pageSize)
    }
    this.add=function (entity) {
        return $http.post("../brand/add.do", entity);
    }
    this.update=function (entity) {
        return $http.post("../brand/update.do", entity);
    }
    this.findOne= function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    }
    this.dele=function (ids) {
        return $http.get("../brand/delete.do?ids=" + ids);
    }
    this.search=function (page,pageSize,entity) {
        return $http.post("../brand/search.do?page=" + page + "&pageSize=" + pageSize, entity);
    }
    this.getBrandList=function () {
        return $http.post("../brand/getBrandList.do")

    }
});