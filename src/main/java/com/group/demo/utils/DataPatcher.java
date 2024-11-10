package com.group.demo.utils;

import java.lang.reflect.Field;

public class DataPatcher<T, X> {

    public void patch(T currentData, X updateForm) throws IllegalAccessException {

        Class<?> type = currentData.getClass();
        Field[] fields = type.getDeclaredFields();

        Class<?> formType = updateForm.getClass();
        Field[] formTypeFields = formType.getDeclaredFields();

        for (Field field : fields) {
            // CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            // CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING
            for (Field payloadField : formTypeFields) {
                payloadField.setAccessible(true);

                if (payloadField.getName().equals(field.getName())) {

                    Object value = payloadField.get(updateForm);
                    if (value != null) {
                        field.set(currentData, (Object) value);
                    }

                    // MAKE THE FIELD PRIVATE AGAIN
                    payloadField.setAccessible(false);
                }
            }
            field.setAccessible(false);

        }

    }

}