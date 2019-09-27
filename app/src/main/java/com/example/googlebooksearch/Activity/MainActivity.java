package com.example.googlebooksearch.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.googlebooksearch.Adapter.BooksAdapter;
import com.example.googlebooksearch.Model.BookItem;
import com.example.googlebooksearch.Model.GoogleBookRespond;
import com.example.googlebooksearch.R;
import com.example.googlebooksearch.Rest.ApiClient;
import com.example.googlebooksearch.Rest.GoogleBooksApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "AIzaSyCTpvoOZxjMC25uOs9_g9isGQArcpi0AEw";
    EditText et_searchText;
    Button bt_Search;
    List<BookItem> bookList;
    RecyclerView bookDisplay;
    BooksAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Search = findViewById(R.id.bt_search);
        et_searchText = findViewById(R.id.et_search_text);

        bt_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getbookList();
            }
        });

        bookDisplay = findViewById(R.id.bookDisplay);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        bookDisplay.setLayoutManager(llm);

    }

   public void getbookList(){
        List<BookItem> booklist;

        GoogleBooksApi apiService =
                ApiClient.getClient().create(GoogleBooksApi.class);

        Call<GoogleBookRespond> call = apiService.searchBooks(et_searchText.getText().toString(),API_KEY);
        call.enqueue(new Callback<GoogleBookRespond>() {
            @Override
            public void onResponse(Call<GoogleBookRespond> call, Response<GoogleBookRespond> response) {
                GoogleBookRespond respond = response.body();
                bookList = respond.getItems();

                bookAdapter = new BooksAdapter(bookList,getApplicationContext());
                bookDisplay.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<GoogleBookRespond> call, Throwable t) {
                Log.e("Myapp",t.toString());

            }
        });

    }
}
