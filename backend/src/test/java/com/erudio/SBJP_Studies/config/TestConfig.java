package com.erudio.SBJP_Studies.config;

public interface TestConfig {

    int SERVER_PORT = 8080;

    String HEADER_PARAM_AUTHORIZATION = "Authorization";
    String HEADER_PARAM_ORIGIN = "origin";

    String ORIGIN_SBPJ = "http://localhost:8080";
    String ORIGIN_NOT_ALLOWED = "http:8081//anyorigin.com";

}
