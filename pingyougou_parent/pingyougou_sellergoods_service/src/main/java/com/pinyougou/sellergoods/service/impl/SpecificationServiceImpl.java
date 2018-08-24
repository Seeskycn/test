package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.pojo.SpecificationOptionExample;
import com.pinyougou.pojogroup.SpecificationAndOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationExample;
import com.pinyougou.pojo.SpecificationExample.Criteria;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private SpecificationMapper specificationMapper;

	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Specification> page=   (Page<Specification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(SpecificationAndOption specificationAndOption) {
		Specification specification = specificationAndOption.getSpecification();
		specificationMapper.insert(specification);
		List<SpecificationOption> options = specificationAndOption.getSpecificationOptions();
		for (SpecificationOption option : options) {
			option.setSpecId(specification.getId());
			specificationOptionMapper.insert(option);
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(SpecificationAndOption specificationAndOption){
		Specification specification = specificationAndOption.getSpecification();
		specificationMapper.updateByPrimaryKey(specification);
		//删除原来表中的记录
		SpecificationOptionExample example = new SpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(specification.getId());
		specificationOptionMapper.deleteByExample(example);
		//更新规格表
		List<SpecificationOption> options = specificationAndOption.getSpecificationOptions();
		for (SpecificationOption option : options) {
			option.setSpecId(specification.getId());
			specificationOptionMapper.insert(option);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public SpecificationAndOption findOne(Long id){
		SpecificationAndOption specificationAndOption = new SpecificationAndOption();
		Specification specification = specificationMapper.selectByPrimaryKey(id);
		specificationAndOption.setSpecification(specification);
		SpecificationOptionExample example = new SpecificationOptionExample();
		example.createCriteria().andSpecIdEqualTo(id);
		List<SpecificationOption> options = specificationOptionMapper.selectByExample(example);
		specificationAndOption.setSpecificationOptions(options);
		return specificationAndOption;

	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		SpecificationOptionExample example = new SpecificationOptionExample();
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			example.createCriteria().andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(Specification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SpecificationExample example=new SpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<Specification> page= (Page<Specification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> getSpecList() {

		return specificationMapper.getSpecList();
	}

}
