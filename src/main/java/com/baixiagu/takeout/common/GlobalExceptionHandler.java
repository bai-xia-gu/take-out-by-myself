package com.baixiagu.takeout.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常：HTTP 状态取自错误码，401/403 才能真正生效
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<Result<Object>> handleBiz(BizException e) {
        ErrorCode code = e.getErrorCode();
        return ResponseEntity.status(code.getHttpStatus())
                .body(Result.fail(code, e.getMessage(), e.getData()));
    }

    /**
     * @RequestBody + @Valid 校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<Void>> handleInvalid(MethodArgumentNotValidException e) {
        // ③ 核心逻辑：从异常对象里提取「第一个校验失败的字段 + 错误信息」
        String msg = e.getBindingResult()          // 拿到 Spring 的校验结果容器
                .getFieldErrors()            // 拿到所有字段的校验错误列表 List<FieldError>
                .stream()                    // 转成 Stream 方便处理
                .findFirst()                 // 取第一个错误（前端一次只显示一个提示）
                .map(err -> err.getField() + " " + err.getDefaultMessage()) // 把错误转成 "字段名 错误提示" 格式
                .orElse(ErrorCode.PARAM_INVALID.getMessage()); // 如果没取到错误（异常情况），用默认提示
        return ResponseEntity.ok(Result.fail(ErrorCode.PARAM_INVALID, msg));
    }

    /**
     * 表单/查询参数绑定失败
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result<Void>> handleBind(BindException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse(ErrorCode.PARAM_INVALID.getMessage());
        return ResponseEntity.ok(Result.fail(ErrorCode.PARAM_INVALID, msg));
    }

    /**
     * 缺必填 query 参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result<Void>> handleMissing(MissingServletRequestParameterException e) {
        return ResponseEntity.ok(Result.fail(ErrorCode.PARAM_MISSING, "缺少参数 " + e.getParameterName()));
    }

    /**
     * 路径不存在（Spring 6.1 起静态资源未命中会抛这个）
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Result<Void>> handleNoResource(NoResourceFoundException e) {
        return ResponseEntity.ok(Result.fail(ErrorCode.NOT_FOUND));
    }

    /**
     * 兜底：绝不把堆栈和 SQL 甩给前端
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleOther(Exception e) {
        log.error("未捕获异常", e);
        return ResponseEntity.ok(Result.fail(ErrorCode.INTERNAL_ERROR));
    }
}