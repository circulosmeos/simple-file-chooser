simple-file-chooser
===================

Library for choosing file(s) from file system storage in android 2.2+


![](http://androidcustomviews.com/wp-content/uploads/2014/08/68747470733a2f2f6c68342e676f6f676c6575736572636f6e74656e742e636f6d2f2d74494274587757594246632f55636d68337370645f48492f41414141414141414441672f726f44456f4458437241342f773335332d683538382d6e6f2f53637265656e73686f745f32303.png)
![](http://androidcustomviews.com/wp-content/uploads/2014/08/68747470733a2f2f6c68342e676f6f676c6575736572636f6e74656e742e636f6d2f2d4b6276413373316f4748552f55636d694f4165724545492f414141414141414144416f2f4754473966554b683551492f773335332d683538382d6e6f2f53637265656e73686f745f32303.png)
![](http://androidcustomviews.com/wp-content/uploads/2014/08/68747470733a2f2f6c68332e676f6f676c6575736572636f6e74656e742e636f6d2f2d584c567861662d746e50772f55636d694f774b666341492f41414141414141414441772f585f4e74655255704566382f773335332d683538382d6e6f2f53637265656e73686f745f32303.png)


changes in this fork
====================

* multiple file selection with checkboxes
* start directory indication
* back button closes Intent without selection
* all file system available, not just SD
* modified first row: it now indicates actual full path


HOW TO USE
===================

* import the library to your project 

<code>

	import com.orleonsoft.android.simplefilechooser.ui.FileChooserActivity;

</code>

* Add your project's .R import to FileChooserActivity.java and FileArrayAdapter.java 

* Declare FileChooserActivity in AndroidManifest.xml 

<code> 

        <activity
            android:name="com.orleonsoft.android.simplefilechooser.ui.FileChooserActivity"
            android:configChanges="orientation|screenSize"
            android:label="@string/app_name" >
        </activity>

<code> 

Example of use:

<code> 

	import com.orleonsoft.android.simplefilechooser.ui.FileChooserActivity;

	public class MainActivity extends Activity {

	final int FILE_CHOOSER=1; 

	/*
	*/

	// onClick() event for a button, for example:
	public void selectFile(View v) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(MainActivity.this, FileChooserActivity.class);

		// indicates a start directory
		// just comment if not needed
		intent.putExtra(
			Constants.KEY_INITIAL_DIRECTORY,
			"/sdcard/Download"
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
				Toast.makeText(this, "file selected "+ fileSelected.get(0), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "multiple files selected", Toast.LENGTH_SHORT).show();
				for (String file : fileSelected) {
					Toast.makeText(this, file, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

</code> 
