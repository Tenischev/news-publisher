package ru.ifmo.ctddev.tenischev.news.publisher;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * This class is
 *
 * @author setenish 07.09.2019.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/hellocucumber")
public class RunCucumberTest {
}
