package com.ns.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet Filter implementation class BackToPostHttpMethodFilter
 */
public class BackToPostHttpMethodFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获得当前的method
		String method = httpServletRequest.getMethod();
		// 如果是delete或�?�put  转回到POST
		if (method.equalsIgnoreCase("delete") || method.equalsIgnoreCase("put")) {
			method = "POST";
		}
		// 重新实例化一个httpServletRequest
		httpServletRequest = new HttpMethodRequestWrapper(request, method);
		filterChain.doFilter(httpServletRequest, response);
	}

	/**
	 * 必须重写HttpServletRequestWrapper的getMethod方法才能修改请求的method�?
	 * @author Administrator
	 *
	 */
	private static class HttpMethodRequestWrapper extends
			HttpServletRequestWrapper {
		private final String method;

		public HttpMethodRequestWrapper(HttpServletRequest request,
				String method) {
			super(request);
			this.method = method;
		}

		public String getMethod() {
			return this.method;
		}
	}
}
