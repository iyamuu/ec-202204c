package com.example.ecommerce_c.mail;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

  @Bean
  public VelocityEngine velocityEngine() {
    Properties prop = new Properties();
    prop.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    prop.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    return new VelocityEngine(prop);
  }
}