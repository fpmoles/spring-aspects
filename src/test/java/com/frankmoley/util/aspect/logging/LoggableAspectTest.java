package com.frankmoley.util.aspect.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=LoggingTestConfig.class)
public class LoggableAspectTest {

    @Autowired
    private TestingClass testingClass;

    private static final MockedHandler mockedHandler = new MockedHandler();
    private static final Logger logger = Logger.getLogger(LoggableAspect.class.getName());

    @BeforeAll
    public static void runBefore(){
        logger.addHandler(mockedHandler);
    }

    @AfterEach
    public void runAfterEach(){
        mockedHandler.flush();
    }

    @Test
    void executeLogging() {
        String returnValue = testingClass.testingThisMethod();
        assertNotNull(returnValue);
        assertEquals(1,mockedHandler.getMessages().size());
    }

    @Test
    void executeLoggingWithException(){
        assertThrows(RuntimeException.class,()->{testingClass.testingException();});
        assertEquals(0, mockedHandler.getMessages().size());
    }

    private static class MockedHandler extends Handler{

        private List<String> messages = new ArrayList<>();

        public List<String> getMessages(){
            return this.messages;
        }

        @Override
        public void publish(LogRecord record) {
            messages.add(record.getMessage());
        }

        @Override
        public void flush() {
            messages = new ArrayList<>();
        }

        @Override
        public void close() throws SecurityException {

        }
    }

}
