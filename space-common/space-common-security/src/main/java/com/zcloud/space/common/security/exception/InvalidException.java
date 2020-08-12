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
public class InvalidException extends MyAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UPGRADE_REQUIRED.value();
	}

}
