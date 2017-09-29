package com.frankmoley.util.aspect.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface Loggable {
}
