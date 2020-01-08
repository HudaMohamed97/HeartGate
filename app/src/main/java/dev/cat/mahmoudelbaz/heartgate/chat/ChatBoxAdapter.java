package dev.cat.mahmoudelbaz.heartgate.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.cat.mahmoudelbaz.heartgate.R;

public class ChatBoxAdapter extends RecyclerView.Adapter<ChatBoxAdapter.MyViewHolder> {
    private List<Message> MessageList;
    Context context;
    int userId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myMessage;
        TextView userMessage;
        private LinearLayout myMessageContainer;
        private LinearLayout userMessageContainer;
        private TextView otherMsgTime;
        private TextView myMsgTime;

        public MyViewHolder(View view) {
            super(view);
            myMessage = view.findViewById(R.id.tv_my_msg);
            myMessageContainer = view.findViewById(R.id.container_my_msg);
            userMessageContainer = view.findViewById(R.id.container_other_msg);
            userMessage = view.findViewById(R.id.tv_other_msg);
            otherMsgTime = view.findViewById(R.id.tv_other_msg_time);
            myMsgTime = view.findViewById(R.id.tv_my_msg_time);
        }
    }

    public ChatBoxAdapter(List<Message> MessagesList, Context context, int userID) {
        this.MessageList = MessagesList;
        this.context = context;
        this.userId = userID;
    }

    @Override
    public int getItemCount() {
        return MessageList.size();
    }

    @Override
    public ChatBoxAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ChatBoxAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChatBoxAdapter.MyViewHolder holder, final int position) {
        Message m = MessageList.get(position);
        if (userId == m.getFromUserId()) {
            holder.myMessageContainer.setVisibility(View.VISIBLE);
            holder.userMessageContainer.setVisibility(View.GONE);
           // holder.myMsgTime.setText(m.getDate());
            holder.myMessage.setText(m.getMessage());
        } else {
            holder.myMessageContainer.setVisibility(View.GONE);
            holder.userMessageContainer.setVisibility(View.VISIBLE);
           // holder.otherMsgTime.setText(m.getDate());
            holder.userMessage.setText(m.getMessage());
        }

    }
}