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
public class ServerErrorException extends MyAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
