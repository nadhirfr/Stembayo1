package id.ayobantu.simple.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;
import id.ayobantu.simple.R;

/**
 * Created by fachrul on 4/2/17.
 */

public class RecycleViewUserAdapter extends RecyclerView.Adapter<RecycleViewUserAdapter.MyViewHolder> {

    private List<User> ListUser;
    private DatabaseHelper db;
    private RecyclerOnItemClickListener mItemClickListener;

    public RecycleViewUserAdapter(List<User> listUser, RecyclerOnItemClickListener listener) {
        ListUser = listUser;
        mItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final User user = ListUser.get(position);
        holder.id.setText(String.valueOf(user.getId()));
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPassword());

    }

    @Override
    public int getItemCount() {
        return ListUser.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id, username, password;
        public ImageButton ibEdit, ibDelete;
        private DatabaseHelper db;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.tvListID);
            username = (TextView) view.findViewById(R.id.tvListUsername);
            password = (TextView) view.findViewById(R.id.tvListPassword);
            ibEdit = (ImageButton) view.findViewById(R.id.ibListUserEdit);
            ibDelete = (ImageButton) view.findViewById(R.id.ibListUserDelete);
            ibDelete.setTag(this);

            ibEdit.setOnClickListener(this);
            ibDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == ibDelete.getId()) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick("Delete", v, getLayoutPosition());
            } else if (v.getId() == ibEdit.getId()) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick("Edit", v, getLayoutPosition());
            }
        }

//
    }

}
