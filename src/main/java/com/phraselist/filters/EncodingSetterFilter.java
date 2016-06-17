package com.phraselist.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * 17.06.2016
 * Created by Rodion.
 */
public class EncodingSetterFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(EncodingSetterFilter.class);

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    private static final String DEFAULT_ENCODING = "cp1251";

    private static final String ENCODING_INIT_PARAMETER_NAME = "encoding";

    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Encoding Filter Initialized.");
        this.encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAMETER_NAME);
        if (this.encoding == null) {
            this.encoding = DEFAULT_ENCODING;
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.startsWith(CONTENT_TYPE)) {
            servletRequest.setCharacterEncoding(this.encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
