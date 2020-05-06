package com.food.core.util;

import cn.hutool.core.codec.Base64;
import com.food.core.pack.JwtCheckResult;
import com.food.core.pack.SystemConstant;
import io.jsonwebtoken.*;
import org.slf4j.MDC;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * 加密和解密的工具类
 */
public class JwtUtil {

	public static String createJWT(String id, Map<String,Object> claims, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey secretKey = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setClaims(claims)
				.setIssuedAt(now) // 签发时间
				.signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate); // 过期时间
		}
		return builder.compact();
	}

	/**
	 * 验证JWT
	 * 
	 * @param jwtStr
	 * @return
	 */
	public static JwtCheckResult validateJWT(String jwtStr) {
		JwtCheckResult checkResult = new JwtCheckResult();
		Claims claims = null;
		try {
			claims = parseJWT(jwtStr);
			checkResult.setSuccess(true);
			checkResult.setClaims(claims);
		} catch (ExpiredJwtException e) {
			checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
			checkResult.setSuccess(false);
		} catch (SignatureException e) {
			checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		} catch (Exception e) {
			checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
			checkResult.setSuccess(false);
		}
		return checkResult;
	}

	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.decode(SystemConstant.JWT_SECERT);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,
				"AES");
		return key;
	}

	/**
	 * 
	 * 解析JWT字符串
	 * 
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt){
		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt)
				.getBody();
	}
	
	public static void main(String[] args) {

	}

}
