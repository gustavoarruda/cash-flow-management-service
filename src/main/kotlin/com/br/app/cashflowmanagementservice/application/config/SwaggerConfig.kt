package com.br.app.cashflowmanagementservice.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun buildApiDocumentation(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.br.app.cashflowmanagementservice"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(buildMetaDataDocumentation())
    }

    private fun buildMetaDataDocumentation(): ApiInfo? {
        return ApiInfoBuilder()
            .title("Gestão de Lançamentos")
            .description("API de Gestão de Lançamentos")
            .contact(
                Contact(
                    "Gustavo S. Arruda",
                    "https://github.com/gustavoarruda/cash-flow-management-service",
                    "gustavoanalistabr@gmail.com"
                )
            )
            .license("Apache License Version 2.0")
            .build()
    }
}