package com.phraselist.validation;

import com.phraselist.validation.annotation.util.VerifyTracker;
import com.phraselist.validation.entities.Verifiable;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 12.07.2016
 * Created by Rodion.
 */
public class ValidationManager {
    private static final Logger LOG = Logger.getLogger(ValidationManager.class);

    public boolean validate(Verifiable obj, Class<?> cl) {
        List<VerifiableField> fields;
        try {
            fields = VerifyTracker.trackVerifyFields(obj, cl);
        } catch (IllegalAccessException e) {
            LOG.error(e);
            return false;
        }
        return fields != null && validateFields(fields);
    }

    private boolean validateFields(List<VerifiableField> fields) {
        boolean result = true;
        for (VerifiableField field : fields) {
            if (!field.getValue().matches(field.getRegexp())) {
                result = false;
                break;
            }
        }
        return result;
    }
}
