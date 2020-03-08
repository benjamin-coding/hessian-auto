package com.minwait.hessian.auto.client;

import com.minwait.hessian.auto.BaseHessianScanner;
import com.minwait.hessian.auto.BaseHessianScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.util.Assert;


/**
 * hessian 接口调用端自动扫描注入
 *
 * @author Liuxunming
 * @date 2018/3/3
 */
public class HessianClientScannerConfigurer extends BaseHessianScannerConfigurer {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected BaseHessianScanner getHessianScanner(BeanDefinitionRegistry registry) {
        return new HessianClientScanner(registry, this.url);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        Assert.hasLength(url, "server url is required.");
    }

}
