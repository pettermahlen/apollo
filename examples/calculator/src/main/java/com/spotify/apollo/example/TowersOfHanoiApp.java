package com.spotify.apollo.example;

import com.spotify.apollo.Environment;
import com.spotify.apollo.Request;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.Status;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;

import java.util.List;
import java.util.concurrent.CompletionStage;

import okio.ByteString;

public class TowersOfHanoiApp {

  public static void main(String[] args) throws LoadingException {
    HttpService.boot(CalculatorApp::init, "towersofhanoi-service", args);
  }

  static void init(Environment environment) {
    environment.routingEngine()
        .registerRoute(Route.async("GET", "/solve", TowersOfHanoiApp::solve));
  }

  private static CompletionStage<String> solve(RequestContext context) {
    Request request = context.request();
    int from = Integer.valueOf(request.parameter("from").get());
    int to = Integer.valueOf(request.parameter("to").get());
    int height = Integer.valueOf(request.parameter("height").get());

    int mid = 6 - from - to;
    Request leftRequest = Request.forUri(String.format("http://localhost:8080/solve?from=%d&to=%d%height=%d", from, mid, height-1);
    Request rightRequest = Request.forUri(String.format("http://localhost:8080/solve?from=%d&to=%d%height=%d", mid, to, height-1);
    CompletionStage<Response<ByteString>> leftResponse = context.requestScopedClient().send(
        leftRequest);
    CompletionStage<Response<ByteString>> rightResponse = context.requestScopedClient().send(
        rightRequest);

    return leftResponse.thenApply(
        orderResponse -> {
          if (orderResponse.status() != Status.OK) {
            return Response.forStatus(Status.INTERNAL_SERVER_ERROR);
          }

          final String orderId = orderResponse.payload().get().utf8();

          return Response.forPayload("your order is " + orderId);
        }

  }
}
