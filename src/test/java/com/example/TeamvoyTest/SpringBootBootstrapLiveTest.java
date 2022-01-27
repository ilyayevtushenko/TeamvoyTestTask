package com.example.TeamvoyTest;

import com.example.TeamvoyTest.model.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpringBootBootstrapLiveTest {
    private static final String API_ROOT = "http://localhost:8080/api/books";

    @Test
    public void whenGetAllOrders_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCreatedOrderById_thenOK() {
        final OrderEntity orderEntity = createRandomOrder();
        final String location = createOrderAsUri(orderEntity);

        final Response response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(orderEntity.getPrice(), response.jsonPath()
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
        final OrderEntity orderEntity = createRandomOrder();

        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderEntity)
                .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidOrder_thenError() {
        final OrderEntity orderEntity = createRandomOrder();
        orderEntity.setPrice(null);

        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderEntity)
                .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedOrder_thenUpdated() {
        final OrderEntity orderEntity = createRandomOrder();
        final String location = createOrderAsUri(orderEntity);

        orderEntity.setId(Long.parseLong(location.split("api/orders/")[1]));
        orderEntity.setPrice(25.0);
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderEntity)
                .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("newPrice", response.jsonPath()
                .get("price"));

    }

    @Test
    public void whenDeleteCreatedBook_thenOk() {
        final OrderEntity orderEntity = createRandomOrder();
        final String location = createOrderAsUri(orderEntity);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // ===============================

    private OrderEntity createRandomOrder() {
        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setPrice(20.0);
        orderEntity.setQuantity(15);
        return orderEntity;
    }

    private String createOrderAsUri(OrderEntity orderEntity) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderEntity)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath()
                .get("id");
    }
}
