package com.m.m.hhsearcher.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.m.m.hhsearcher.R;
import com.m.m.hhsearcher.presenter.Presenter;
import com.m.m.hhsearcher.presenter.PresenterInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac on 29.08.17.
 */

public class SearchFragment extends Fragment {
    @BindView(R.id.search_button) Button mSearchButton;
    @BindView(R.id.search_edit_text) EditText mEditText;
    private PresenterInterface mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = Presenter.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchfragment_layout,container,false);
        ButterKnife.bind(this,view);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = mEditText.getText().toString();
                if(searchWord.equals("")||searchWord.equals(null)){
                    Toast.makeText(getContext(), "Please, enter the search word!", Toast.LENGTH_LONG).show();
                }else{
                    mPresenter.startSearch(searchWord);
                }
            }
        });
        return view;
    }
}
