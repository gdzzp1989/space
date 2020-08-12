package com.zcloud.space.common.security.component;

import com.zcloud.space.common.core.constant.Constants;
import com.zcloud.space.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Description 全局异常处理器
 * @Author
 * @Date
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerResolver {

	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public R handleGlobalException(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return R.builder()
			.msg(e.getLocalizedMessage())
			.code(Constants.FAIL)
			.build();
	}

	/**
	 * AccessDeniedException
	 *
	 * @param e the e
	 * @return R
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public R handleAccessDeniedException(AccessDeniedException e) {
		String msg = SpringSecurityMessageSource.getAccessor()
			.getMessage("AbstractAccessDecisionManager.accessDenied"
				, e.getMessage());
		log.error("拒绝授权异常信息 ex={}", msg, e);
		return R.builder()
			.msg(msg)
			.code(Constants.FAIL)
			.build();
	}

	/**
	 * validation Exception
	 *
	 * 请求的 JSON 参数在请求体内的参数校验
	 *
	 * @param exception
	 * @return R
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public R handleBodyValidException(MethodArgumentNotValidException exception) {
		FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
		log.error("参数绑定异常,ex = 字段[{}] {}",fieldError.getField(), fieldError.getDefaultMessage());
		return R.builder()
			.msg(fieldError.getField() + " " +fieldError.getDefaultMessage())
			.code(Constants.FAIL)
			.build();
	}

	/**
	 *  请求的 URL 参数检验
	 *
	 *  @param e 异常信息
	 *  @return 返回提示信息
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public R handleBindException2(ConstraintViolationException e) {
		String str = e.getConstraintViolations().stream().limit(1).map(x-> x.getMessage()).collect(Collectors.joining());
		log.error("url 参数绑定异常,ex = {}",str);
		return R.builder()
				.msg(str)
				.code(Constants.FAIL)
				.build();
	}
}
