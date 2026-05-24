package com.udemy.spring.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> handlerConstraintViolation(ConstraintViolationException e) {
        Map<String, Set> map = new HashMap<>();
        Set<ConstraintViolation<?>> setConstraints = e.getConstraintViolations();
        Set<String> msg = new HashSet<>();
        msg.addAll(setConstraints.stream()
                .map(constraint -> constraint.getMessage())
                .collect(Collectors.toSet()));
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}