package com.minwait.hessian.auto;

import com.minwait.hessian.auto.client.HessianClientScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Liuxunming
 * @date 2018/3/5
 */
public abstract class BaseHessianScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean,
        ApplicationContextAware {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected List<String> basePackages;
    protected ApplicationContext applicationContext;

    public void setBasePackages(List<String> basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(this.basePackages, "basePackages is required.");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BaseHessianScanner scanner = getHessianScanner(registry);
        scanner.setResourceLoader(this.applicationContext);
        scanner.setBeanNameGenerator(new HessianBeanNameGenerator());
        scanner.registerFilters();
        scanner.doScan(this.basePackages.toArray(new String[0]));
    }

    /**
     * 获取扫描器
     *
     * @param registry
     * @return
     */
    protected abstract BaseHessianScanner getHessianScanner(BeanDefinitionRegistry registry);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
