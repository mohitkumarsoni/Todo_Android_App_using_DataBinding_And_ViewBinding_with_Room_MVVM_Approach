package com.example.notesnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesnew.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Notes_ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(Notes_ViewModel.class);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Update_Activity.class);
                intent.putExtra("type","insert");
                startActivityForResult(intent, 1);
            }
        });

        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        binding.rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        MyNotesAdapter adapter = new MyNotesAdapter();
        binding.rv.setAdapter(adapter);

        viewModel.getNoteList().observe(this, new Observer<List<Notes_Entity>>() {
            @Override
            public void onChanged(List<Notes_Entity> notesEntities) {
                adapter.submitList(notesEntities);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction== ItemTouchHelper.LEFT){
                    //delete
                    viewModel.deleteNotes(adapter.getPosition(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                } else if (direction== ItemTouchHelper.RIGHT) {
                    //update
                    Intent intent = new Intent(MainActivity.this, Add_Update_Activity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("title", adapter.getPosition(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("desc", adapter.getPosition(viewHolder.getAdapterPosition()).getDesc());
                    intent.putExtra("id", adapter.getPosition(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.rv);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            String title  = data.getStringExtra("title");
            String desc  = data.getStringExtra("desc");
            Notes_Entity notes = new Notes_Entity(title, desc);
            viewModel.insertNotes(notes);
            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
        }
        if (requestCode==2 && resultCode==RESULT_OK){
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Notes_Entity notes = new Notes_Entity(title, desc);
            notes.setId(data.getIntExtra("id",0));
            viewModel.updateNotes(notes);
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }
    }
}