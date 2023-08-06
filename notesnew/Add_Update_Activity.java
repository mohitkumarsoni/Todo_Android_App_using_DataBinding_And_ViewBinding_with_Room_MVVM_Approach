package com.example.notesnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notesnew.databinding.ActivityAddUpdateBinding;

public class Add_Update_Activity extends AppCompatActivity {

    ActivityAddUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_update);

        String type = getIntent().getStringExtra("type");
        if (type.equals("insert")){
            setTitle("add note");

            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent();
                    intent.putExtra("title", binding.titleEt.getText().toString());
                    intent.putExtra("desc", binding.descEt.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }

        if (type.equals("update")){
            setTitle("update note");

            binding.titleEt.setText(getIntent().getStringExtra("title"));
            binding.descEt.setText(getIntent().getStringExtra("desc"));
            int id = getIntent().getIntExtra("id",0);

            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.titleEt.getText().toString());
                    intent.putExtra("desc", binding.descEt.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }



    }
}