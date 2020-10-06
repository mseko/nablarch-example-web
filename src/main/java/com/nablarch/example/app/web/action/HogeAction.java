package com.nablarch.example.app.web.action;

import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpRequestHandler;
import nablarch.fw.web.HttpResponse;

public class HogeAction implements HttpRequestHandler {
    @Override
    public HttpResponse handle(HttpRequest request, ExecutionContext context) {
        HttpResponse response = new HttpResponse(200);
        response.write("Hello");
        return response;
    }

    public void test() {
        // ローカルクラスをアクションにできるか検証
        class LocalAction implements HttpRequestHandler {

            @Override
            public HttpResponse handle(HttpRequest request, ExecutionContext context) {
                HttpResponse response = new HttpResponse(200);
                response.write("LocalAction!!");
                return response;
            }
        }
    }
}
