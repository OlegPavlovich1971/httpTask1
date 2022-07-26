package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class App {

    private static final String URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
//        создание httpClient
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // невозможность следовать редиректу в ответе
                        .build())
                .build();
//        создание объекта запроса по заранее заданому адресу URI и с произвольными заголовками:
        HttpGet request = new HttpGet(URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
//        отпрвка запроса и получение ответа:
        CloseableHttpResponse response = httpClient.execute(request);
//        извлечение тела ответа и преобразование его с помощью заранее созданного mapper из json-строки
//        в список java-объектов Fact
        mapper.readValue(response.getEntity()
                        .getContent(), new TypeReference<List<Fact>>() {
                })
                .stream()    // фильтрация в потоке по полю upvotes
                .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                .sorted() // сортировка по возрастанию
                .forEach(System.out::println); // печать в цикле резульатов фильтрации и сортировки
    }
}


