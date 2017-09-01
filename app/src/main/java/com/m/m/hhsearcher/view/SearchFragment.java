package com.m.m.hhsearcher.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.m.m.hhsearcher.R;

import butterknife.BindView;

/**
 * Created by mac on 29.08.17.
 */

public class SearchFragment extends ViewFragment {
    @BindView(R.id.search_button) Button mSearchButton;
    @BindView(R.id.search_edit_text) EditText mEditText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText.selectAll();
            }
        });
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = mEditText.getText().toString();
                if(searchWord.equals("")){
                    Toast.makeText(getContext(), "Please, enter the search word!", Toast.LENGTH_LONG).show();
                }else{
                    mPresenter.startSearch(searchWord);
                }
            }
        });
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.searchfragment_layout;
    }
}
