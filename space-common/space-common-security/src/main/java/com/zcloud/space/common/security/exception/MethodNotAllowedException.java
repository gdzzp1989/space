package com.zcloud.space.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcloud.space.common.security.component.Auth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @Description
 * @Author
 * @Date
 */
@JsonSerialize(using = Auth2ExceptionSerializer.class)
public class MethodNotAllowedException extends MyAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
