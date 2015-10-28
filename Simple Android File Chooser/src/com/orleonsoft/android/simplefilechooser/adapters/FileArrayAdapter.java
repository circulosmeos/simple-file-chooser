package com.orleonsoft.android.simplefilechooser.adapters;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.orleonsoft.android.simplefilechooser.Constants;
import com.orleonsoft.android.simplefilechooser.FileInfo;
import com.orleonsoft.android.simplefilechooser.ui.FileChooserActivity;

@SuppressLint("DefaultLocale")
public class FileArrayAdapter extends ArrayAdapter<FileInfo> implements
		CompoundButton.OnCheckedChangeListener {

	private Context context;
	private int resorceID;
	private List<FileInfo> items;
	//private SparseBooleanArray bCheckVisibility; // implements checkable items
	private SparseBooleanArray bCheckState; // implements checkable items

	public FileArrayAdapter(Context context, int textViewResourceId,
			List<FileInfo> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.resorceID = textViewResourceId;
		this.items = objects;

		// implements checkable items
		/*// which can be both visible
		this.bCheckVisibility = new SparseBooleanArray(objects.size());
		for (int i=0; i<bCheckVisibility.size(); i++) {
			bCheckVisibility.put(i,items.get(i).getCheckboxVisible());
		}*/
		// and/or checked
		this.bCheckState = new SparseBooleanArray(objects.size());
		for (int i=0; i<bCheckState.size(); i++) {
			bCheckState.put(i,items.get(i).getChecked());
		}
	}

	public FileInfo getItem(int i) {
		return items.get(i);
	}

	public List<FileInfo> getCheckedItems() {
		// extract all files checked, if any
		List<FileInfo> list_of_files = new ArrayList<FileInfo>();

		for ( FileInfo element: this.items ) {
			if ( element.getChecked() ) {
				list_of_files.add(element);
			}
		}

		return list_of_files;
	}

	// implements checkable items
	public void onCheckedChanged(CompoundButton buttonView,
								 boolean isChecked) {
		FileInfo r = (FileInfo) buttonView.getTag();
		r.setChecked(isChecked);
		bCheckState.put(items.indexOf(r), isChecked);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		// implements checkable items
		String showCheckBoxes = ((FileChooserActivity)context).getIntent().
				getStringExtra(Constants.KEY_SHOW_CHECKBOXES_FOR_FILES);
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(resorceID, null);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(android.R.id.icon);
			// implements checkable items
			if ( showCheckBoxes != null &&
					showCheckBoxes.equals(Constants.KEY_SHOW_CHECKBOXES_FOR_FILES) )
				viewHolder.checkedFile = (CheckBox) convertView.findViewById(R.id.checkedFile);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.details = (TextView) convertView
					.findViewById(R.id.details);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		FileInfo option = items.get(position);
		if (option != null) {

			// implements checkable items
			// in order to locate checkbox position in list, set a Tag:
			if ( showCheckBoxes != null )
				viewHolder.checkedFile.setTag(option); // Tag of FileInfo class

			if (option.getData().equalsIgnoreCase(Constants.FOLDER)) {
				viewHolder.icon.setImageResource(R.drawable.folder);
				// implements checkable items
				if ( showCheckBoxes != null )
					viewHolder.checkedFile.setVisibility(View.INVISIBLE);
			} else {
				if (option.getName().equalsIgnoreCase(
						Constants.PARENT_FOLDER)) {
					viewHolder.icon.setImageResource(R.drawable.back);
					// implements checkable items
					if ( showCheckBoxes != null )
						viewHolder.checkedFile.setVisibility(View.INVISIBLE);
				} else {
					// implements checkable items
					if ( showCheckBoxes != null ) {
						// visibility:
						if (option.getCheckboxVisible())
							viewHolder.checkedFile.setVisibility(View.VISIBLE);
						// checkbox checked/unchecked:
						viewHolder.checkedFile.setChecked(bCheckState.get(position, false));
						viewHolder.checkedFile.setOnCheckedChangeListener(this);
					}
					String name = option.getName().toLowerCase();
					if (name.endsWith(Constants.XLS)
							|| name.endsWith(Constants.XLSX))
						viewHolder.icon.setImageResource(R.drawable.xls);
					else if (name.endsWith(Constants.DOC)
							|| name.endsWith(Constants.DOCX))
						viewHolder.icon.setImageResource(R.drawable.doc);
					else if (name.endsWith(Constants.PPT)
							|| option.getName().endsWith(Constants.PPTX))
						viewHolder.icon.setImageResource(R.drawable.ppt);
					else if (name.endsWith(Constants.PDF))
						viewHolder.icon.setImageResource(R.drawable.pdf);
					else if (name.endsWith(Constants.APK))
						viewHolder.icon.setImageResource(R.drawable.apk);
					else if (name.endsWith(Constants.TXT))
						viewHolder.icon.setImageResource(R.drawable.txt);
					else if (name.endsWith(Constants.JPG)
							|| name.endsWith(Constants.JPEG))
						viewHolder.icon.setImageResource(R.drawable.jpg);
					else if (name.endsWith(Constants.PNG))
						viewHolder.icon.setImageResource(R.drawable.png);
					else if (name.endsWith(Constants.ZIP))
						viewHolder.icon.setImageResource(R.drawable.zip);
					else if (name.endsWith(Constants.RTF))
						viewHolder.icon.setImageResource(R.drawable.rtf);
					else if (name.endsWith(Constants.GIF))
						viewHolder.icon.setImageResource(R.drawable.gif);
					else if (name.endsWith(Constants.AVI))
						viewHolder.icon.setImageResource(R.drawable.avi);
					else if (name.endsWith(Constants.MP3))
						viewHolder.icon.setImageResource(R.drawable.mp3);
					else if (name.endsWith(Constants.MP4))
						viewHolder.icon.setImageResource(R.drawable.mp4);
					else if (name.endsWith(Constants.RAR))
						viewHolder.icon.setImageResource(R.drawable.rar);
					else if (name.endsWith(Constants.ACC))
						viewHolder.icon.setImageResource(R.drawable.aac);
					else
						viewHolder.icon.setImageResource(R.drawable.blank);
				}
			}

			viewHolder.name.setText(option.getName());
			viewHolder.details.setText(option.getData());

		}
		return convertView;
	}

	class ViewHolder {
		ImageView icon;
		TextView name;
		TextView details;
		CheckBox checkedFile;
	}

}
