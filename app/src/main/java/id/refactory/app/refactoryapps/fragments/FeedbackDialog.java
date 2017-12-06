package id.refactory.app.refactoryapps.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.refactory.app.refactoryapps.R;

/**
 * Created by dhanialrizky on 06/12/17.
 */

public class FeedbackDialog extends BottomSheetDialog {
    @BindView(R.id.feedback_checkbox) CheckBox checkBox;
    @BindView(R.id.feedback_email) TextInputLayout email;
    @BindView(R.id.feedback_msg) TextInputLayout message;
    @BindView(R.id.feedback_toolbar) Toolbar toolbar;

    public FeedbackDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        email.setVisibility(View.GONE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    email.setVisibility(View.GONE);
                }else {
                    email.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
