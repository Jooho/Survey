package org.jhouse.survey.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by jhouse on 12/18/14.
 */
public class SetCharacterEncodingFilter implements Filter {
        /**
         * 인코딩을 수행할 인코딩 캐릭터 셋 지정
         */
        private String encoding = null;

        /**
         * 필터 설정 관리자
         */
        protected FilterConfig filterConfig = null;

        /* (non-Javadoc)
         * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
         */
        public void init(FilterConfig filterConfig) throws ServletException {
            this.filterConfig = filterConfig;
            this.encoding = filterConfig.getInitParameter("encoding");
        }

        /* (non-Javadoc)
         * @see javax.servlet.Filter#destroy()
         */
        public void destroy() {

            this.encoding = null;
            this.filterConfig = null;

        }

        /* (non-Javadoc)
         * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
         */
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                ServletException {

            if (request.getCharacterEncoding() == null) {
                if (encoding != null) {
                    request.setCharacterEncoding(encoding);
                }
            }

            chain.doFilter(request, response);
        }

        /**
         * @return
         */
        public FilterConfig getFilterConfig() {
            return filterConfig;
        }

        /**
         * @param cfg
         */
        public void setFilterConfig(FilterConfig cfg) {
            filterConfig = cfg;
        }
    }