package edu.mayo.lpea.cad.cadence3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher.Builder;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@AutoConfiguration
public class WebCommonConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebCommonConfig.class);

  @Bean("mvc")
  public Builder mvc() {
    LOGGER.info("WebCommonConfig mvc() called, MvcRequestMatcher.Builder created...");
    return new MvcRequestMatcher.Builder(new HandlerMappingIntrospector());
  }

  @Bean
  @ConditionalOnMissingBean
  public AuthenticationSuccessHandler authenticationSuccessHandler(){
    return new DefaultLoginHandler();
  }
}
