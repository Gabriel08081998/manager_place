module br.com.manager.barber.shop {
    requires spring.security.core;
    requires spring.security.config;
    requires spring.security.web;
    requires spring.context;
    requires spring.beans;
    exports br.com.manager.barber.shop;
    requires dom4j;
}