package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.Brand;
import entity.PageResult;

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
 * @Date: 2018/8/20 16:52
 * @Description:
 */
public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 增加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 删除品牌
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据主键查询品牌
     * @param id
     * @return
     */
    Brand findOne(Long id);

    /**
     * 分页查询商品
     * @param page 当前页
     * @param pageSize 每页查询条数
     * @return
     */
    PageResult fingPage(int page,int pageSize);

    /**
     * 条件分页查询
     * @param page 当前页
     * @param pageSize 每页查询条数
     * @param brand 查询条件
     * @return
     */
    PageResult findPage(int page,int pageSize,Brand brand);

    /**
     * 获取品牌下拉列表
     * @return
     */
    List<Map> getBrandList();
}
