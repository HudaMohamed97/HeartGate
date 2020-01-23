package dev.cat.mahmoudelbaz.heartgate.map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

public class CustomBottomSheet extends BottomSheetDialogFragment implements BottomSheetViewInterface {

    private ModelMyConnections object;
    private CustomBottomSheetPresenter presenter;
    private Button connectButton;
    private Context context;
    private Button btnApproveRecieved;
    private int state;

    public CustomBottomSheet() {
    }

    @SuppressLint("ValidFragment")
    public CustomBottomSheet(ModelMyConnections object, Context context) {
        this.object = object;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_dialog_bottom_sheet, container, false);
        if (object != null) {
            state = object.getStateId();
        }
        setPresenter();
        setViews(v);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object != null) {
                    if (object.getStateId() == 0) {
                        presenter.callAddConnection(object);
                    } else if (object.getStateId() == 1) {
                        presenter.cancelConnectionRequest(object);
                    } else if (object.getStateId() == 2) {
                        // this for message
                    } else if (object.getStateId() == 4) {
                        presenter.cancelConnectionRequest(object);
                    }
                }
            }
        });

        btnApproveRecieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object.getStateId() == 2) {
                    presenter.disconnectConnectionRequest(object);
                }
                if (object.getStateId() == 4) {
                    presenter.approveConnectionRequest(object, true);
                }
            }
        });
        return v;
    }

    private void setViews(View v) {
        connectButton = v.findViewById(R.id.btnAdd);
        btnApproveRecieved = v.findViewById(R.id.btnApproveRecieved);
        if (state == 4) {
            connectButton.setBackgroundResource(R.drawable.red_rounded_corner_button);
            connectButton.setText("Cancel");
            //reciever request
            btnApproveRecieved.setVisibility(View.VISIBLE);
        } else if (state == 1) {
            connectButton.setBackgroundResource(R.drawable.red_rounded_corner_button);
            connectButton.setText("Cancel");
            // sender
            btnApproveRecieved.setVisibility(View.GONE);
        } else if (state == 2) {
            //you already in Connection
            connectButton.setBackgroundResource(R.drawable.blue_rounded_button);
            connectButton.setText("message");
            btnApproveRecieved.setBackgroundResource(R.drawable.red_rounded_corner_button);
            btnApproveRecieved.setText("Disconnect");
            btnApproveRecieved.setVisibility(View.VISIBLE);

        } else {
            //0 to add user
            btnApproveRecieved.setVisibility(View.GONE);

        }

        TextView textView = v.findViewById(R.id.txtTitle);
        ImageView imageView = v.findViewById(R.id.imgProfile);
        TextView txtName = v.findViewById(R.id.txtName);
        ImageView shortcut = v.findViewById(R.id.swip_shortcut);
        shortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        textView.setText(object.getJobTitle());
        txtName.setText(object.getName());
        Picasso.with(

                getActivity()).

                load(object.getImageUrl()).

                placeholder(R.drawable.profile).

                error(R.drawable.profile).

                into(imageView);

    }

    private void setPresenter() {
        presenter = new CustomBottomSheetPresenter(getActivity(), this);
    }

    @Override
    public void openAlertDialog(String massage) {
        dismiss();
        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(massage);
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder1.create();
            alert.show();
        }
    }
}


