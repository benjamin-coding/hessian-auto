package com.minwait.hessian.auto.server;

import com.minwait.hessian.auto.BaseHessianScanner;
import com.minwait.hessian.auto.HessianApi;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * Hessian 服务端扫描器
 *
 * @author Liuxunming
 * @date 2018/3/5
 */
public class HessianServerScanner extends BaseHessianScanner {

    public HessianServerScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder holder : beanDefinitions) {
            try {
                GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
                definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
                HessianApi hessianApi = Class.forName(definition.getBeanClassName()).getAnnotation(HessianApi.class);
                String beanName = hessianApi.value();
                if (StringUtils.isEmpty(beanName)) {
                    String[] pathParts = holder.getBeanName().split("/");
                    beanName = pathParts[pathParts.length - 1] + "Impl";
                }
                definition.getPropertyValues().add("service", new RuntimeBeanReference(beanName));
                definition.setBeanClass(HessianServiceExporter.class);
                if (logger.isDebugEnabled()) {
                    logger.debug("Creating Hessian Server Api with name '" + holder.getBeanName()
                            + "' and '" + definition.getBeanClassName() + "' Interface.");
                }
            } catch (Exception e) {
                logger.error("Skipping Hessian Server Api with name '" + holder.getBeanName(), e);
            }
        }
    }

}
