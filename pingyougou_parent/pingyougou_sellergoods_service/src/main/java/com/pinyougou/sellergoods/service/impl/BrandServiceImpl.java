package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.pojo.BrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/*
 *-----------------神兽保佑 -----------------
 *           ┌─┐        ┌─┐
 *       ┌─┘  ┴────┘  ┴─┐
 *       │                        │
 *       │        ───          │
 *       │  ─┬┘       └┬─   │
 *       │                        │
 *       │       ─┴─           │
 *       │                        │
 *       └───┐          ┌──┘
 *               │          │
 *               │          │
 *               │          │
 *               │          └────────────┐
 *               │                                    │
 *               │                                    ├─┐
 *               │                                    ┌─┘
 *               │                                    │
 *               └─┐  ┐  ┌─────┬─┐  ┌──┘
 *                   │  ┤  ┤          │  ┤  ┤
 *                   └─┴─┘          └─┴─┘
 *
 * ------------------代码无BUG!--------------
 * @Author: Seesky
 * @Date: 2018/8/20 16:54
 * @Description:
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> findAll() {
       return brandMapper.selectByExample(null);

    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public Brand findOne(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult fingPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Brand> brands = brandMapper.selectByExample(null);
        PageInfo<Brand> info = new PageInfo<>(brands);
        PageResult pageResult = new PageResult(info.getTotal(),info.getList());
        return pageResult;
    }

    @Override
    public PageResult findPage(int page, int pageSize, Brand brand) {
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if(brand.getName()!=null&&!"".equals(brand.getName())){
            criteria.andNameLike("%"+brand.getName()+"%");
        }
        if(brand.getFirstChar()!=null&&!"".equals(brand.getFirstChar())){
            criteria.andFirstCharEqualTo(brand.getFirstChar());
        }
        PageHelper.startPage(page, pageSize);
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> info = new PageInfo<>(brands);
        PageResult pageResult = new PageResult(info.getTotal(),info.getList());
        return pageResult;
    }

    @Override
    public List<Map> getBrandList() {
        return brandMapper.getBrandList();
    }
}
