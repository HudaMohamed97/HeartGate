package dev.cat.mahmoudelbaz.heartgate.map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

    public CustomBottomSheet() {
    }

    @SuppressLint("ValidFragment")
    public CustomBottomSheet(ModelMyConnections object) {
        this.object = object;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_dialog_bottom_sheet, container, false);
        setPresenter();
        setViews(v);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we check statues 0,1,2,3
                presenter.callAddConnection(object);
            }
        });
        return v;
    }

    private void setViews(View v) {
        connectButton = v.findViewById(R.id.connectButton);
        TextView textView = v.findViewById(R.id.txtTitle);
        ImageView imageView = v.findViewById(R.id.imgProfile);
        TextView txtName = v.findViewById(R.id.txtName);
        textView.setText(object.getJobTitle());
        txtName.setText(object.getName());
        Picasso.with(getActivity()).load(object.getImageUrl()).placeholder(R.drawable.profile).error(R.drawable.profile).into(imageView);
    }

    private void setPresenter() {
        presenter = new CustomBottomSheetPresenter(getActivity(), this);
    }

    @Override
    public void openAlertDialog(String massage) {
        dismiss();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
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


