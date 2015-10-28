 package com.orleonsoft.android.simplefilechooser;

import android.annotation.SuppressLint;

@SuppressLint("DefaultLocale")
public class FileInfo implements Comparable<FileInfo>{
	private String name;
	private String data;
	private String path;
	private boolean folder;
	private boolean parent;
	private boolean checked = false; // implements checkable items
    private boolean checkbox_visible = false; // implements checkable items

	public FileInfo(String n,String d,String p, boolean folder, boolean parent, boolean checkbox_visible)
	{
		this.name = n;
		this.data = d;
		this.path = p;
		this.folder = folder;
		this.parent = parent;
        this.checkbox_visible = checkbox_visible; // implements checkable items
	}

	public String getName()
	{
		return name;
	}

	public String getData()
	{
		return data;
	}

	public String getPath()
	{
		return path;
	}

    // implements checkable items
    public boolean getCheckboxVisible()
    {
        return checkbox_visible;
    }

    // implements checkable items
    public void setCheckboxVisible(boolean state)
    {
        this.checkbox_visible=state;
    }

    // implements checkable items
    public boolean getChecked()
    {
        return checked;
    }

    // implements checkable items
    public void setChecked(boolean state)
    {
        this.checked=state;
    }
		
	@Override
	public int compareTo(FileInfo o) {
		if(this.name != null)
			return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
		else
			throw new IllegalArgumentException();
	}

	public boolean isFolder() {
		return folder;
	}

	public boolean isParent() {
		return parent;
	}
}
