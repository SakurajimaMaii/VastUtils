package com.gcode.vastutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gcode.vasttools.utils.DensityUtils;

import java.util.List;

/**
 * 
 * @author Lajos
 * @date 2016-12-09
 */
public class DelListViewAdapter extends ArrayAdapter {
	private List<String> lists;
	private int resources;
	private Context context;

	public DelListViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
		super(context, resource, objects);
		this.lists = objects;
		this.resources = resource;
		this.context = context;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		TextView tv;

		view = LayoutInflater.from(context).inflate(resources,parent,false);
		tv = (TextView) view.findViewById(R.id.tv_value);

		tv.setTextSize(DensityUtils.INSTANCE.getSp(8f));
		tv.setText(lists.get(position));
		
		return view;
	}
	
	private class ViewHoler{
		TextView tv_value;
	}

}
