package com.food.core.adapter;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * JWT载体
 */
@Data
@Accessors(chain = true)
public class JwtPayLoad implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

}
