package com.minwait.hessian.auto;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.StringUtils;

/**
 * HessianBeanNameGenerator
 * Bean Name 生成器
 *
 * @author by xunmi
 * @version 1.0
 * @date 2020/3/4 14:45
 */
public class HessianBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanClassName = definition.getBeanClassName();
        if (beanClassName == null) {
            throw new IllegalArgumentException("bean class name is null, but expect not.");
        }
        String[] nameParts = beanClassName.split("[.]");
        String beanName = "/" + StringUtils.uncapitalize(nameParts[nameParts.length - 1]);
        if (nameParts.length > 1) {
            beanName = "/" + nameParts[nameParts.length - 2] + beanName;
        }
        return beanName;
    }
}
