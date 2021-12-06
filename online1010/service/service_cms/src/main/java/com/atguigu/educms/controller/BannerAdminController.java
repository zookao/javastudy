package com.atguigu.educms.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-13
 */

@RestController
@RequestMapping("/educms/bannerAdmin") //管理员后台
public class BannerAdminController {
    @Autowired
    CrmBannerService crmBannerService;
    // 1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }
    //2 添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }
    //3 修改banner
    @PutMapping("updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }
    //4 删除banner
    @DeleteMapping("deleteBanner/{id}")
    public R delereBanner(@PathVariable String id){
        crmBannerService.removeById(id);
        return R.ok();
    }
    //5 根据id查询
    @GetMapping("geiBanner/{id}")
    public R get(@PathVariable String id){
        CrmBanner byId = crmBannerService.getById(id);
        return R.ok().data("item",byId);
    }
}

