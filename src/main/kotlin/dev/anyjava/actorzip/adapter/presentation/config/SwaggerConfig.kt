package dev.anyjava.actorzip.adapter.presentation.config

import com.fasterxml.classmate.TypeResolver
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.AlternateTypeRules
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig(private val typeResolver: TypeResolver) {

    @Bean
    fun api(): Docket {
        val alternateTypeRules = AlternateTypeRules.newRule(
            typeResolver.resolve(Pageable::class.java),
            typeResolver.resolve(Page::class.java)
        )

        return Docket(DocumentationType.SWAGGER_2)
            .alternateTypeRules(alternateTypeRules)
            .groupName("actor-zip API")
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
            .paths(PathSelectors.any())
            .build()
    }
}

@ApiModel
data class Page(
    @ApiModelProperty("페이지번호 (0...N)")
    val page: Int,
    @ApiModelProperty("페이지크기 default=20")
    val size: Int,
    @ApiModelProperty("정렬(사용법: 컬럼명, ASC | DESC)")
    val sort: List<String>
)

