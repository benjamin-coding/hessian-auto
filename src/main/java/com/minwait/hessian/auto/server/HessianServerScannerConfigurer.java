package com.minwait.hessian.auto.server;

import com.minwait.hessian.auto.BaseHessianScanner;
import com.minwait.hessian.auto.BaseHessianScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


/**
 * hessian 接口服务端自动扫描注入
 *
 * @author Liuxunming
 * @date 2018/3/3
 */
public class HessianServerScannerConfigurer extends BaseHessianScannerConfigurer {

    @Override
    protected BaseHessianScanner getHessianScanner(BeanDefinitionRegistry registry) {
        return new HessianServerScanner(registry);
    }
}
