package za.ac.nwu.ac.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class FetchAccountTypeFlowImplTest {

    private FetchAccountTypeFlowImpl testClass;

    @Before
    public void setUp() throws Exception{
        testClass = new FetchAccountTypeFlowImpl(null);
    }

    @After
    public void tearDown() throws Exception {
        testClass = null;
    }

    @Test
    public void methodToTest() {
        assertTrue(testClass.methodToTest());
    }
}