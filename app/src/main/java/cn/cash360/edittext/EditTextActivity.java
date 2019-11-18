package cn.cash360.edittext;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import cn.cash360.java_design.R;

/**
 * @time 2019/4/11 19:34
 * @desc
 */

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().addFlags(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_edit_text);
        AppCompatEditText compatEditText = findViewById(R.id.edit_text);
        compatEditText.setText("dddd");
        compatEditText.append("ddd");
    }
}
