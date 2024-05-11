package com.example.recyclerviewwithfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editTextSearch;
    private YourAdapter adapter;
    private List<YourItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editTextSearch = findViewById(R.id.editTextSearch);

        // Создание списка элементов
        itemList = new ArrayList<>();
        itemList.add(new YourItem("Элемент 1", 10));
        itemList.add(new YourItem("Элемент 2", 20));
        // Добавьте ваши элементы здесь

        // Создание и установка адаптера для RecyclerView
        adapter = new YourAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Слушатель изменения текста для фильтрации элементов
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}