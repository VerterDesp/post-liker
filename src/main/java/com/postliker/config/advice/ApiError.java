package com.postliker.config.advice;

import java.util.List;

public record ApiError(List<String> errors) { }
