package com.orleonsoft.android.simplefilechooser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.orleonsoft.android.simplefilechooser.ui.FileChooserActivity;

import java.util.ArrayList;

public class MainActivity extends Activity {

    final int FILE_CHOOSER=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectFile(View v) {
        Intent intent = new Intent(MainActivity.this, FileChooserActivity.class);

        // indicates a start directory
        // just comment if not needed
        intent.putExtra(
                Constants.KEY_INITIAL_DIRECTORY,
                //"/storage/emulated/0/Download/"
                "/sdcard/Download/"
        );

        // shows checkable items
        // just comment if not needed
        intent.putExtra(
                Constants.KEY_SHOW_CHECKBOXES_FOR_FILES,
                Constants.KEY_SHOW_CHECKBOXES_FOR_FILES
        );

        startActivityForResult(intent, FILE_CHOOSER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ArrayList<String> fileSelected = null;

        if ((requestCode == FILE_CHOOSER) && (resultCode == RESULT_OK)) {

            ArrayList<String> list_of_selected_files = null;
            try {
                list_of_selected_files =
                        (ArrayList<String>) data.getStringArrayListExtra(Constants.KEY_FILE_SELECTED);
            } catch (NullPointerException e) {
                return;
            }
            // set file(s) name:
            if (list_of_selected_files != null) {
                fileSelected = list_of_selected_files;
            } else {
                return;
            }

            if (fileSelected.size() == 1) {
                Toast.makeText(this, "file selected " + fileSelected.get(0), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "multiple files selected", Toast.LENGTH_SHORT).show();
                for (String file : fileSelected) {
                    Toast.makeText(this, file, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}