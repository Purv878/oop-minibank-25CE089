package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.annotation.Positive;
import model.annotation.MaxLength;

public class AnnotationValidator {

    public static String[] validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Class<?> currentClass = obj.getClass();

        // Check fields in the class and its parent classes
        while (currentClass != null && currentClass != Object.class) {

            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {

                field.setAccessible(true);

                try {
                    Object value = field.get(obj);

                    // Validate @Positive
                    if (field.isAnnotationPresent(Positive.class)) {

                        Positive positive =
                                field.getAnnotation(Positive.class);

                        if (value instanceof Number
                                && ((Number) value).longValue() <= 0) {

                            errors.add(
                                field.getName() + ": " + positive.message()
                            );
                        }
                    }

                    // Validate @MaxLength
                    if (field.isAnnotationPresent(MaxLength.class)) {

                        MaxLength maxLength =
                                field.getAnnotation(MaxLength.class);

                        if (value instanceof String
                                && ((String) value).length()
                                > maxLength.value()) {

                            errors.add(
                                field.getName()
                                + ": maximum length is "
                                + maxLength.value()
                            );
                        }
                    }

                } catch (IllegalAccessException e) {

                    errors.add(
                        "Unable to validate field: " + field.getName()
                    );
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}