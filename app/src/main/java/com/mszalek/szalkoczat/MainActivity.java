package com.mszalek.szalkoczat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    EditText mMessageEditText;
    Firebase mRef = new Firebase("https://szalkoczat.firebaseio.com/testChat");
    ChatAdapter mAdapter;
    DatabaseReference mDbRef;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mRef.push().setValue(new Message("jakistekst", "jakisautor"));

        userName = getIntent().getExtras().getString("user");

        mMessageEditText = (EditText) findViewById(R.id.et_message);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount());
            }
        });

        mDbRef = FirebaseDatabase.getInstance().getReference("testChat");

        mAdapter = new ChatAdapter(Message.class, R.layout.chat_message_item, ChatAdapter.ViewHolder.class, mDbRef);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mRecyclerView.scrollToPosition(positionStart);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        Button mButton = (Button) findViewById(R.id.btn_send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageEditText.getText().toString();
                if (message.isEmpty()) {
                    Toast.makeText(MainActivity.this, "pusta wiadomosc? o co Ci chodzi?", Toast.LENGTH_SHORT).show();
                } else {
                    mRef.push().setValue(new Message(message, userName));
                    mMessageEditText.setText("");
                }
            }
        });
    }

}
