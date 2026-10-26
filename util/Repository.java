package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import model.annotation.Id;

public class Repository<T> {

    private final HashMap<String, T> data = new HashMap<>();

    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null.");
        }

        try {
            Field idField = null;

            Class<?> currentClass = entity.getClass();

while (currentClass != null && idField == null) {
    for (Field field : currentClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(Id.class)) {
            idField = field;
            break;
        }
    }

    currentClass = currentClass.getSuperclass();
}

            if (idField == null) {
                throw new IllegalArgumentException(
                    "No field annotated with @Id found."
                );
            }

            idField.setAccessible(true);
            Object idValue = idField.get(entity);

            if (idValue == null) {
                throw new IllegalArgumentException("@Id value cannot be null.");
            }

            String id = idValue.toString();
            data.put(id, entity);

            return entity;

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to read entity ID.", e);
        }
    }

    public T findById(String id) {
        return data.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    public int size() {
        return data.size();
    }

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                "Array cannot be null or empty."
            );
        }

        double sum = 0;

        for (N value : values) {
            sum += value.doubleValue();
        }

        return sum / values.length;
    }
}
