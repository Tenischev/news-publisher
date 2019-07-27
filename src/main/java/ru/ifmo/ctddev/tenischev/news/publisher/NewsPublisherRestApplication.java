package ru.ifmo.ctddev.tenischev.news.publisher;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Simple Microprofile application that provide page to user where new news for traffic-light could be written and
 * then stored in news-storage.
 */
@ApplicationPath("/news-publisher")
// @LoginConfig(authMethod = "MP-JWT", realmName = "jwt-jaspi")
@DeclareRoles({ "protected" })
public class NewsPublisherRestApplication extends Application {
}
