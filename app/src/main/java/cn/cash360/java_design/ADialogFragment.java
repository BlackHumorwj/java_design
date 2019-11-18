package cn.cash360.java_design;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import cn.cash360.ui.activity.MainActivity;

/**
 * @time 2019/3/20 12:29
 * @desc
 */

public class ADialogFragment extends DialogFragment implements View.OnClickListener {

    public static ADialogFragment newInstance() {
        Bundle args = new Bundle();
        ADialogFragment fragment = new ADialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Design_BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_dialog_a, container, false);
        inflate.findViewById(R.id.tv_click).setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }
}
