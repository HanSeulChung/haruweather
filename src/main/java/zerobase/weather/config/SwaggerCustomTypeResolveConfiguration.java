package zerobase.weather.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.plugin.core.PluginRegistry;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;


@Configuration
public class SwaggerCustomTypeResolveConfiguration {
    @Bean
    public PluginRegistry<TypeNameProviderPlugin, DocumentationType> customTypeNameResolvers() {
        return PluginRegistry.of(new DefaultTypeNameProvider() { // DefaultTypeNameProvider를 상속하여, 불필요한 코드 작성을 줄임
            @Override
            public String nameFor(Class<?> type) {
                return type.getName();
            }
        });
    }

    @Bean
    @Primary // 기존 TypeNameExtractor 대신 해당 Bean을 우선적으로 사용하도록 설정!
    public TypeNameExtractor customTypeNameExtractor(
            TypeResolver resolver,
            @Qualifier("customTypeNameResolvers") PluginRegistry<TypeNameProviderPlugin, DocumentationType> customTypeNameResolvers,
            EnumTypeDeterminer determiner) {
        return new TypeNameExtractor(resolver, customTypeNameResolvers, determiner);
    }
}