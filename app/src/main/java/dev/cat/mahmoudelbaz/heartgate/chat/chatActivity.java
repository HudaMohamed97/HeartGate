package dev.cat.mahmoudelbaz.heartgate.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dev.cat.mahmoudelbaz.heartgate.R;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class chatActivity extends AppCompatActivity {
    public static Socket socket;
    private String nickname;
    public RecyclerView myRecylerView;
    public ArrayList<Message> MessageList;
    public ChatBoxAdapter chatBoxAdapter;
    public EditText messagetxt;
    public Button send;
    String url;
    TextView name;
    public RecyclerView userRecylerView;
    public List<User> userslist;
    public UsersAdapter userAdapter;
    public static User reciver;
    SharedPreferences shared;
    private String userID, receiveId, imageUrl, myimageUrl;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messagetxt = findViewById(R.id.message);
        send = findViewById(R.id.send);
        name = findViewById(R.id.name);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        url = "http://heartgate.co/api_heartgate/users/current/" + userID;
        StringRequest loginRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray usersarray = new JSONArray(response);
                    JSONObject res = usersarray.getJSONObject(0);
                    nickname = res.getString("fullname");
                    name.setText(nickname);
                    final String imgstring = res.getString("image_profile");
                    myimageUrl = "http://heartgate.co/api_heartgate/layout/images/" + imgstring;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        Volley.newRequestQueue(this).add(loginRequest);
        // get the nickame of the user
        //connect you socket client to the servertry {
        Bundle bundle = getIntent().getExtras();
        receiveId = String.valueOf(bundle.getInt("receiveId"));
        imageUrl = bundle.getString("imageUrl");

        //    reciver.setId(receiveId);
        //    reciver.setName();
        reciver = new User(receiveId,
                bundle.getString("name"),
                ""
                , ""
                , "");

        IO.Options mOptions = new IO.Options();
        mOptions.query = "id=" + receiveId;
        try {
            socket = IO.socket("http://160.153.246.213:5999", mOptions);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, onConnected);
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.on(Socket.EVENT_MESSAGE, onMessage);
        //socket.on("chatListRes", onChatListRes);
        socket.on("typing", ontyping);
        socket.on("getMessages", getMessages);
        socket.on("addMessageResponse", addMessageResponse);
        socket.connect();
        //socket.emit("chatList", userID);

        initMessagesList();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put("fromUserId", userID);
                map.put("toUserId", reciver.getId());
                map.put("toSocketId", reciver.getSocket_id());
                map.put("message", messagetxt.getText().toString());
                map.put("time", "01:48 PM");
                map.put("type", "text");
                map.put("date", "2019-05-7");
                JSONObject obj = new JSONObject(map);
                Message m = new Message(nickname, messagetxt.getText().toString(), new SimpleDateFormat("hh:mm a").toString(), myimageUrl, Integer.parseInt(userID));
                MessageList.add(m);
                socket.emit("addMessage", obj).on("addMessageResponse", addMessageResponse);
                chatBoxAdapter.notifyDataSetChanged();
                messagetxt.setText("");
            }
        });
        userslist = new ArrayList<>();
        userRecylerView = findViewById(R.id.userslist);
        userAdapter = new UsersAdapter(userslist);
        userAdapter.notifyDataSetChanged();
        userRecylerView.setAdapter(userAdapter);
        RecyclerView.LayoutManager mzLayoutManager = new LinearLayoutManager(getApplicationContext());
        userRecylerView.setLayoutManager(mzLayoutManager);
        userRecylerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initMessagesList() {
        MessageList = new ArrayList<>();
        myRecylerView = findViewById(R.id.messagelist);
        HashMap map = new HashMap();
        map.put("fromUserId", userID);
        map.put("toUserId", reciver.getId());
        JSONObject obj = new JSONObject(map);
        socket.emit("getMessages", obj).on("getMessagesResponse", getMessagesResponse);
        chatBoxAdapter = new ChatBoxAdapter(MessageList, this, Integer.parseInt(userID));
        myRecylerView.setAdapter(chatBoxAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecylerView.setLayoutManager(mLayoutManager);
        myRecylerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        socket.close();
    }

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    };

    private Emitter.Listener onConnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    socket.emit("chatList", receiveId);
                }
            });
        }
    };


    private Emitter.Listener onMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    };

    private Emitter.Listener onChatListRes = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("socket", "run: " + args);
                    JSONObject data = (JSONObject) args[0];
                    try {
                        reciver.setSocket_id(data.getString("socket_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayList<String> listdata = new ArrayList<String>();
                    JSONArray jArray = null;
                    try {
                        jArray = data.getJSONArray("chatList");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (jArray != null) {
                        for (int i = 0; i < jArray.length(); i++) {
                            //extract data from fired event
                            String id = null;
                            try {
                                id = jArray.getJSONObject(i).getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String name = null;
                            try {
                                name = jArray.getJSONObject(i).getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String socket_id = null;
                            try {
                                socket_id = jArray.getJSONObject(i).getString("socket_id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String online = null;
                            try {
                                online = jArray.getJSONObject(i).getString("online");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String updated_at = null;
                            try {
                                updated_at = jArray.getJSONObject(i).getString("updated_at");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            User m = new User(id, name, socket_id, online, updated_at);
                            userslist.add(m);
                            userAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    };

    private Emitter.Listener ontyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("hhhhh", "ontyping" + args.toString());
                }
            });
        }
    };


    private Emitter.Listener getMessagesResponse = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    JSONArray result;
                    try {
                        result = data.getJSONArray("result");
                        if (result != null) {
                            for (int i = 0; i < result.length(); i++) {
                                //extract data from fired event
                                String message;
                                String time;
                                int fromUserId;
                                try {
                                    message = result.getJSONObject(i).getString("message");
                                    time = result.getJSONObject(i).getString("time");
                                    fromUserId = result.getJSONObject(i).getInt("fromUserId");
                                    MessageList.add(
                                            new Message(
                                                    reciver.getName(),
                                                    message,
                                                    time,
                                                    imageUrl,
                                                    fromUserId
                                            ));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    chatBoxAdapter.notifyDataSetChanged();

                }
            });
        }
    };

    private Emitter.Listener getMessages = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("hhhh", "" + args.toString());

                }
            });
        }
    };


    private Emitter.Listener addMessageResponse = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    };
}
