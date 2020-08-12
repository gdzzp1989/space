package com.zcloud.space.common.core.util;

import com.zcloud.space.common.core.constant.Constants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 响应信息主体
 *
 * @param <T>
 *
 * @Author
 * @Date
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code = Constants.SUCCESS;

	@Getter
	@Setter
	private String msg = "操作成功";


	@Getter
	@Setter
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R success(T data, String msg) {
		return R.builder().code(Constants.SUCCESS).data(data).msg(msg).build();
	}

	public R success(T data) {
		return R.builder().code(Constants.SUCCESS).data(data).msg("操作成功").build();
	}

	public R error(String msg) {
		return this.error(null, msg);
	}

	public R error(T data, String msg) {
		return R.builder().code(Constants.FAIL).data(data).msg("操作失败：" + msg).build();
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = Constants.FAIL;
	}
}
