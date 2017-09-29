package com.frankmoley.util.aspect.logging;

import org.springframework.stereotype.Component;

@Component
    public class TestingClass{

        @Loggable
        public String testingThisMethod(){
            return "foo";
        }

        @Loggable
        public String testingException(){throw new RuntimeException("foo");}
    }
