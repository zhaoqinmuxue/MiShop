package com.example.mishop;

import android.os.Bundle;
import android.view.View;

import com.example.aoli_core.delegates.AoliDelegate;

public class ExampleDelegate extends AoliDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
