package com.phraselist.gmail.props;

import com.phraselist.gmail.props.entities.Credentials;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 06.08.2016
 * Created by Rodion.
 */
public class EmailPropertiesResolver {
    private static final String ERROR_MESSAGE = "Configuration file '%s' not found.";

    private InputStream inputStream;

    public Credentials getProperties() throws IOException {
        Properties properties = new Properties();
        String fileName = "email_config.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if(inputStream == null) {
            throw new FileNotFoundException(String.format(ERROR_MESSAGE, fileName));
        }

        Credentials result = null;

        try {
            properties.load(inputStream);

            result = new Credentials(properties.getProperty("email"), properties.getProperty("pass"),
                    properties.getProperty("transport"), properties.getProperty("trust"));
        } catch (IOException e) {
            throw e;
        }

        return result;
    }
}
