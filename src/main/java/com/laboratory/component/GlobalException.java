package com.laboratory.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalException implements HandlerExceptionResolver{

	public final static String	BAD_PARAM			= "파라미터가 형식에 맞지 않음.";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		String msg = String.format(
				"[ERROR] 호출주소 : %s 예외처리 내용 : %s",
				request.getRequestURI(),ex.getMessage());
		System.out.println(msg);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ModelAndView mv = new ModelAndView();
		mv.setStatus(status);
		return mv;
	}


	public void badParamException() throws Exception {
		throw new Exception(BAD_PARAM);
	}
}