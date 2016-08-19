package com.mszalek.szalkoczat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Marcinus on 2016-08-18.
 */
public class ChatAdapter
        extends FirebaseRecyclerAdapter<Message, ChatAdapter.ViewHolder> {

    public ChatAdapter(Class<Message> modelClass, int modelLayout, Class<ChatAdapter.ViewHolder> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Message model, int position) {
        viewHolder.mAuthorTextView.setText(model.getAuthorName());
        viewHolder.mMessageTextView.setText(model.getText());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mAuthorTextView;
        public final TextView mMessageTextView;

        public ViewHolder(View view) {
            super(view);
            mAuthorTextView = (TextView) view.findViewById(R.id.tv_author);
            mMessageTextView = (TextView) view.findViewById(R.id.tv_message);
        }
    }
}