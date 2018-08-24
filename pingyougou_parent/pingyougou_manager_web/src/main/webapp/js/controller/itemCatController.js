 //控制层 
app.controller('itemCatController' ,function($scope,$controller ,typeTemplateService  ,itemCatService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	$scope.findAllType=function(){
		typeTemplateService.findAll().success(function (response) {
			$scope.type_template={data:response};
        })
	}
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reLoad();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.delIds ).success(
			function(response){
				if(response.success){
					$scope.reLoad();//刷新列表
					$scope.delIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
    //根据parentId查询列表
	$scope.findByParentId=function (parentId){
		itemCatService.findByParentId(parentId).success(function (response) {
			$scope.list=response;
		})
    }
	//父id
	$scope.parentId=0;
    //面包屑导航
	$scope.level=0;
	$scope.setLevel=function(level){
		$scope.level=level;
	}
	$scope.setEntityLevel=function (p_entity) {
		$scope.parentId = p_entity.id;
		if($scope.level == 0){
			$scope.entity_1=null;
			$scope.entity_2=null;
		}else if($scope.level == 1){
			$scope.entity_1=p_entity;
			$scope.entity_2=null;
		}else {
			$scope.entity_2=p_entity;
		}
		$scope.findByParentId(p_entity.id);
    }
});	
