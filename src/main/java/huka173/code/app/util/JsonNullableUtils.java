package huka173.code.app.util;

import org.apache.coyote.BadRequestException;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.function.Consumer;

public class JsonNullableUtils {

    public static <T> void setIfPresent(JsonNullable<T> jsonNullable, Consumer<T> setter) {
        if (jsonNullable != null && jsonNullable.isPresent()) {
            setter.accept(jsonNullable.get());
        }
    }
}