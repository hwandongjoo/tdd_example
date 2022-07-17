package com.nhn.mvc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.HiddenHttpMethodFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class MvcConfig: WebMvcConfigurationSupport() {
    @Bean
    fun httpMethodFilter(): HiddenHttpMethodFilter = HiddenHttpMethodFilter()
}
