package id.ayobantu.simple;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import id.ayobantu.simple.Adapter.RecycleViewUserAdapter;
import id.ayobantu.simple.Adapter.RecyclerOnItemClickListener;
import id.ayobantu.simple.Helper.DatabaseHelper;
import id.ayobantu.simple.Model.User;

public class DaftarUser extends AppCompatActivity implements RecyclerOnItemClickListener {

    RecyclerView recyclerView;
    DatabaseHelper db;
    List<User> ListUser;
    RecycleViewUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        db = new DatabaseHelper(this);
        ListUser = db.getAllUser();

        Log.d("Coba", "onCreate: user" + ListUser.get(1).getUsername());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecycleViewUserAdapter(ListUser, this);
        recyclerView.setAdapter(adapter);

        //ini biar floating action button ilang pas mentok scroll kebawah
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0)
//                    fab.hide();
//                else if (dy < 0)
//                    fab.show();
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarUser.this, AddUser.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onItemClick(String TAG, View childView, int position) {
        User user = ListUser.get(position);
        Log.d(TAG, "onItemClick: posisi: " + position + " id : user" + user.getId() + ":" + user.getUsername());
        if (TAG.equals("Delete")) {
            db.deleteUser(user);
            ListUser.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(0, ListUser.size());
            adapter.notifyDataSetChanged();
        } else if (TAG.equals("Edit")) {
            Intent intent = new Intent(DaftarUser.this, EditUser.class);
            intent.putExtra("id", user.getId());
            intent.putExtra("username", user.getUsername());
            intent.putExtra("password", user.getPassword());
            startActivity(intent);
        }
    }
}
