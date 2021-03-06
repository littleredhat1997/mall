package com.example.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.PageResult;
import com.example.demo.dto.page.ProductPageRequest;
import com.example.demo.entity.Product;
import com.example.demo.facade.ProductFacade;
import com.example.demo.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "商品")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @ApiOperation("获取商品列表")
    @GetMapping("")
    public PageResult<List<ProductVo>> list(@Valid ProductPageRequest pageRequest) {
        Page<Product> page = productFacade.list(pageRequest);
        List<ProductVo> productVoList = Convert.convert(new TypeReference<List<ProductVo>>() {
        }, page.getRecords());
        return PageResult.success(productVoList, page.getTotal());
    }
}
