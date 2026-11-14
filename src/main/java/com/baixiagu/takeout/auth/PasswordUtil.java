package com.baixiagu.takeout.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * 密码工具：SHA-256(salt + password)，与 V4 迁移里的 SHA2(CONCAT(salt, password), 256) 等价
 * 仅教学可用，生产应换 BCrypt / Argon2
 */
public final class PasswordUtil {

    private PasswordUtil() {
    }

    // 密码哈希
    public static String hash(String salt,String rawPassword){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // 确保线程安全
            byte[] bytes = digest.digest((salt + rawPassword).getBytes(StandardCharsets.UTF_8)); // 计算哈希值
            return HexFormat.of().formatHex(bytes); // 格式化为十六进制字符串
        }catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 不可用", e);
        }
    }

    // 密码匹配
    public static boolean matches(String rawPassword,String salt,String expectedHash){
        return hash(salt,rawPassword).equals(expectedHash);
    }

}
