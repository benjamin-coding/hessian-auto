package com.minwait.hessian.auto.client;

import com.minwait.hessian.auto.BaseHessianScanner;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import java.util.Set;

/**
 * Hessian客户端bean扫描器
 *
 * @author Liuxunming
 * @date 2018/3/5
 */
public class HessianClientScanner extends BaseHessianScanner {
    private String url;

    public HessianClientScanner(BeanDefinitionRegistry registry, String url) {
        super(registry);
        this.url = url;
    }

    @Override
    protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            try {
                definition = (GenericBeanDefinition) holder.getBeanDefinition();
                if (logger.isDebugEnabled()) {
                    logger.debug("Creating Hessian Client Api with name '" + holder.getBeanName()
                            + "' and '" + definition.getBeanClassName() + "' Interface.");
                }
                definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
                definition.getPropertyValues().add("serviceUrl", this.url + holder.getBeanName());
                definition.setBeanClass(HessianProxyFactoryBean.class);
            } catch (Exception e) {
                logger.warn("Skipping Hessian Api with name '" + holder.getBeanName(), e);
            }
        }
    }
}
