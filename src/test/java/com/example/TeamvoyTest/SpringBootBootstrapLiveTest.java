package com.example.TeamvoyTest;

import com.example.TeamvoyTest.model.domain.Order;
import com.example.TeamvoyTest.model.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringBootBootstrapLiveTest {
    private static final String API_ROOT = "http://localhost:8080/api/orders";

    @Test
    public void whenGetAllOrders_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCreatedOrderById_thenOK() {
        final Order order = createRandomOrder();
        final String location = createOrderAsUri(order);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(order.getPrice(), response.jsonPath()
                .get("price"));
    }

    @Test
    public void whenGetNotExistOrderById_thenNotFound() {
        final Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNewOrder_thenCreated() {
        final Order order = createRandomOrder();

        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(order)
                .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidOrder_thenError() {
        final Order order = createRandomOrder();
        order.setPrice(null);

        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(order)
                .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedOrder_thenUpdated() {
        final Order order = createRandomOrder();
        final String location = createOrderAsUri(order);

        order.setId(Long.parseLong(location.split("api/orders/")[1]));
        order.setPrice(25.0);
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(order)
                .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newPrice", response.jsonPath()
                .get("price"));

    }

    @Test
    public void whenDeleteCreatedBook_thenOk() {
        final Order order = createRandomOrder();
        final String location = createOrderAsUri(order);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // ===============================

    private Order createRandomOrder() {
        final Order orderEntity = new Order();
        orderEntity.setPrice(20.0);
        orderEntity.setQuantity(15);
        return orderEntity;
    }

    private String createOrderAsUri(Order order) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(order)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath()
                .get("id");
    }
}
