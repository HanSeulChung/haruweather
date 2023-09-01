package zerobase.weather.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.plugin.core.PluginRegistry;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;
import zerobase.weather.dto.CreateDiary;
import zerobase.weather.dto.DeleteDiary;
import zerobase.weather.dto.UpdateDiary;

public class CustomTypeNameExtractor extends TypeNameExtractor {

    public CustomTypeNameExtractor(
            TypeResolver typeResolver,
            PluginRegistry<TypeNameProviderPlugin, DocumentationType> typeNameProviders,
            EnumTypeDeterminer enumTypeDeterminer) {
        super(typeResolver, typeNameProviders, enumTypeDeterminer);
    }

    @Override
    public String typeName(ModelContext context) {
        // 특정 타입에 대한 커스텀 타입 이름 설정
        if (context.getType().equals(CreateDiary.Response.class)) {
            return "CustomTypeNameForCreateDiaryResponse";
        } else if (context.getType().equals(DeleteDiary.Response.class)) {
            return "CustomTypeNameForDeleteDiaryResponse";
        } else if (context.getType().equals(UpdateDiary.Response.class)) {
            return "CustomTypeNameForUpdateDiaryResponse";
        }

        // 기본적인 추출 로직 사용
        return super.typeName(context);
    }
}