package com.company.cdk;


import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;


@Configuration
	public class WebConfig extends AbstractFlowConfiguration {

//	    @Autowired
//	    private WebMvcConfig webMvcConfig;
//
//	    @Bean
//	    public FlowDefinitionRegistry flowRegistry() {
//	        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
//	          .addFlowLocation("/WEB-INF/flows/activation-flow.xml", "activationFlow")
//	          .build();
//	    }
//
//	    @Bean
//	    public FlowExecutor flowExecutor() {
//	        return getFlowExecutorBuilder(flowRegistry()).build();
//	    }
//
//	    @Bean
//	    public FlowBuilderServices flowBuilderServices() {
//	        return getFlowBuilderServicesBuilder()
//	          .setViewFactoryCreator(mvcViewFactoryCreator())
//	          .setDevelopmentMode(true).build();
//	    }
//
//	    @Bean
//	    public MvcViewFactoryCreator mvcViewFactoryCreator() {
//	        MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
//	        factoryCreator.setViewResolvers(
//	          Collections.singletonList(this.webMvcConfig.viewResolver()));
//	        factoryCreator.setUseSpringBeanBinding(true);
//	        return factoryCreator;
//	    }
	}