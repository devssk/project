package io.project.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdFilter extends OncePerRequestFilter {

    private static final String TRACE_ID_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put(TRACE_ID_KEY, traceId); // 저장

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID_KEY); // 메모리 누수 방지
        }
    }
}
