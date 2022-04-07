package com.jb.couponSystem.Advice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Bean for building custom errors for the advice exception interceptor
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
public class ErrorDetail {
    private String errorDetail;
}
