package example.krunal.appversion;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import example.krunal.appversion.DataBase.LogsEntity;

public class LogsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LogsActivityViewModel mLogsActivityViewModel;
    LogsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLogsActivityViewModel = ViewModelProviders.of(this)
                .get(LogsActivityViewModel.class);

        mAdapter = new LogsAdapter(LogsActivity.this);
        recyclerView.setAdapter(mAdapter);

        mLogsActivityViewModel.getLogsList().observe(LogsActivity.this, logslist -> {
            if (logslist != null && logslist.size() > 0) {
                mAdapter.AddItems(logslist);
            }
        });

    }
}
