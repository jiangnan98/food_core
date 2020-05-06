package com.food.core.pack;

import io.jsonwebtoken.Claims;
import lombok.Data;


@Data
public class JwtCheckResult {

	private int errCode;

	private boolean success;

	private Claims claims;
}
