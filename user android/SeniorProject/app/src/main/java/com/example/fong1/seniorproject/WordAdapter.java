package com.example.fong1.seniorproject;

/**
 * Created by fong1 on 5/13/2018.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class WordAdapter extends BaseAdapter {

    private Context mContext;
    private List<Word> mWord;

    //Constructor

    public WordAdapter(Context mContext, List<Word> mProductList) {
        this.mContext = mContext;
        this.mWord = mProductList;
    }

    @Override
    public int getCount() {
        return mWord.size();
    }

    @Override
    public Object getItem(int position) {
        return mWord.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_row, null);
        TextView word = (TextView)v.findViewById(R.id.word);
        TextView pinyin = (TextView)v.findViewById(R.id.pinyin);
        TextView meaning = (TextView)v.findViewById(R.id.meaning);
        //Set text for TextView
        word.setText(mWord.get(position).getWord());
        pinyin.setText(mWord.get(position).getPinyin());
        meaning.setText(mWord.get(position).getMeaning());

        //Save product id to tag
        v.setTag(mWord.get(position).getId());

        return v;
    }
}

