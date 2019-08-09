package com.github.nmyphp;

import java.io.IOException;
import org.junit.Test;

public class TomcatTest {

    @Test
    public void start() throws IOException {
        new Tomcat().start();
    }
}