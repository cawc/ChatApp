package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AsyncRequestHandler extends RequestHandler {
    @Override
    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
