package com.example.googlebooksearch.Model;

import java.util.List;

public class GoogleBookRespond {
    public List<BookItem> getItems() {
        return items;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }

    private List<BookItem> items;
}
