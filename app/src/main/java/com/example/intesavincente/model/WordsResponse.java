package com.example.intesavincente.model;

import java.util.List;

public class WordsResponse {

    private String status;

    public WordsResponse(String status) {
        this.status = status;
    }

    public WordsResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "status='" + status +
                '}';
    }
}
