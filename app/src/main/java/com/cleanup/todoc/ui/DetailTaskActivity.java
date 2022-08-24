package com.cleanup.todoc.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class DetailTaskActivity extends FragmentActivity {

    TaskRepository taskRepoy;
    TasksAdapter adap;
    Task ts;
    updateCallBack callBack;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        Intent bundle = getIntent();
        final int id_task = (int) bundle.getLongExtra("id_task", 0);

        final TaskRepository taskRepository = new TaskRepository(this);

        final EditText name_task = findViewById(R.id.inom_tache);
        final EditText date_task = findViewById(R.id.idate_creationtache);
        final EditText heure_task = findViewById(R.id.iheure_creation);
        final EditText name_projet = findViewById(R.id.inom_projet);

        Button modif = findViewById(R.id.modif);


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ts = taskRepository.getTaskById(id_task);

                final String nameTache = ts.getName();
                final String nameProjet =ts.getProject().getName();
                final Long dateR = ts.getCreationTimestamp();

                runOnUiThread(new Runnable() {
                    public void run() {

                        name_task.setText(nameTache);
                        name_projet.setText(nameProjet);

                        SimpleDateFormat  date1 = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat  date2= new SimpleDateFormat("HH:mm:ss");

                        date1.format(dateR);
                        date_task.setText(""+date1);

                        date2.format(dateR);
                        heure_task.setText(""+date2);




                    }
                });

            }
        });

        modif.setOnClickListener(new View.OnClickListener() {
          //  Task ts1=ts;
            @Override
            public void onClick(View view) {


               ts.setName((name_task.getText().toString()));
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {

                        taskRepository.updateTask(ts);

                    }
                });

               startActivity(new Intent(DetailTaskActivity.this,MainActivity.class));
               finish();

            }
        });

    }

    public updateCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(updateCallBack callBack) {
        this.callBack = callBack;
    }
}