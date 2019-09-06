package example.krunal.appversion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import example.krunal.appversion.DataBase.LogsEntity;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<LogsEntity> list = new ArrayList<>();

    public LogsAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void AddItems(List<LogsEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.log_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LogsEntity current = list.get(i);
        viewHolder.title.setText(current.getTitle());
        viewHolder.status.setText(current.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, status;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);

        }

    }
}
