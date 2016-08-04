package com.phraselist.validation.annotation.util;

import com.phraselist.validation.FieldTypes;
import com.phraselist.validation.VerifiableField;
import com.phraselist.validation.annotation.Validate;
import com.phraselist.validation.entities.Verifiable;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * 12.07.2016
 * Created by Rodion.
 */
public class VerifyTracker {
    private static final Logger LOG = Logger.getLogger(VerifyTracker.class);

    public static List<VerifiableField> trackVerifyFields(Verifiable obj, Class<?> cl) throws IllegalAccessException {
        Field[] fields = cl.getDeclaredFields();
        List<VerifiableField> verifiableFields = new ArrayList<VerifiableField>();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Validate.class)) {
                String value = getFieldValue(obj, cl, f);
                FieldTypes tempType = f.getAnnotation(Validate.class).type();
                VerifiableField field;
                if (tempType.name().equalsIgnoreCase("none")) {
                    field = new VerifiableField(f.getAnnotation(Validate.class).regexp(), value);
                } else {
                    field = new VerifiableField(tempType, value);
                }
                verifiableFields.add(field);
            }
        }
        return verifiableFields;
    }

    private static String getFieldValue(Verifiable obj, Class<?> cl, Field f) throws IllegalAccessException {
        Class[] paramTypes = new Class[]{};
        String value;
        Method m;
        try {
            m = cl.getMethod("get" + capitalize(f.getName()), paramTypes);
            value = (String) m.invoke(obj, paramTypes);
        } catch (NoSuchMethodException e) {
            LOG.error(e);
            return null;
        } catch (InvocationTargetException e) {
            LOG.error(e);
            return null;
        }
        return value;
    }

    private static String capitalize(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        CharacterIterator chit = new StringCharacterIterator(s);
        char ch = chit.current(), prev = ' ';
        while (ch != CharacterIterator.DONE) {
            if (Character.isWhitespace(prev) && Character.isLetter(ch)) {
                sb.append(Character.toUpperCase(ch));
            } else {
                sb.append(ch);
            }
            prev = ch;
            ch = chit.next();
        }
        return sb.toString();
    }
}
