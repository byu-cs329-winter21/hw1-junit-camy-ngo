package edu.byu.cs329;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Disabled
@RunWith(JUnitPlatform.class)
@DisplayName("Testing Dijkstra")
public class DijkstraTest {
    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);
    private Dijkstra classUnderTest;
    int [][] g = new int [12][10];

    @BeforeAll
    public static void init() {
        // Do something before ANY test is run in this class
        log.info("@BeforeAll: init()");
    }

    @AfterAll
    public static void done() {
        // Do something after ALL tests in this class are run
        log.info("@AfterAll: done()");
    }

    @BeforeEach
    public void setUp() throws Exception {
        log.info("@BeforeEach: setUp()");
        classUnderTest = new Dijkstra(g);
    }

    @AfterEach
    public void tearDown() throws Exception {
        log.info("@AfterEach: tearDown()");
        classUnderTest = null;
    }

    @Test
    @DisplayName("Testing Positive Number Dijkstra Graph")
    public void testShortestPath() {
        assertNotNull(classUnderTest);
        int [][] graph = { { 0, 2, 4, Dijkstra.M, Dijkstra.M, Dijkstra.M }, { 2, 0, 1, 4, Dijkstra.M, Dijkstra.M },
                { 4, 1, 0, 2, 6, Dijkstra.M }, { Dijkstra.M, 4, 2, 0, 1, 3 }, { Dijkstra.M, Dijkstra.M, 6, 1, 0, 5 },
                { Dijkstra.M, Dijkstra.M, Dijkstra.M, 3, 5, 0 } };
        Dijkstra d = new Dijkstra(graph);
        int sp = d.shortestPath(0, 4);
        assertAll(
            () -> {assumeTrue(sp > 0);},
            () -> {assertEquals(sp, 6);});
    }

    @Nested
    @DisplayName("When graph has number < 0")
    class MixedNumbersTest {


        @BeforeEach
        public void setUp() throws Exception {
          classUnderTest = new Dijkstra(g);
        }
    
        @AfterEach
        public void tearDown() throws Exception {
          classUnderTest = null;
        }

        @Test
        @DisplayName("tests with positive and negative numbers")
        public void testShortestPath(){
            assertNotNull(classUnderTest);
            final int[][] graph = { { 0, -2, -4, Dijkstra.M, Dijkstra.M, Dijkstra.M }, { 2, 0, 1, 4, Dijkstra.M, Dijkstra.M },
            { 4, -1, 0, 2, 6, Dijkstra.M }, { Dijkstra.M, -4, 2, 0, -1, -3 }, { Dijkstra.M, Dijkstra.M, 6, -1, 0, -5 },
            { Dijkstra.M, Dijkstra.M, Dijkstra.M, 3, -5, 0 } };
        Dijkstra d = new Dijkstra(graph);
        int sp = d.shortestPath(0, 5);
        assumeTrue(sp > 0);

        }



    }



}