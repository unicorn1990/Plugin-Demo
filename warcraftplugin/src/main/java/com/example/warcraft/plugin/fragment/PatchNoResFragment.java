package com.example.warcraft.plugin.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warcraft.R;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/3/23 下午9:51</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class PatchNoResFragment extends Fragment {

    private View rootView = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView =  createView();
        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        rootView.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"fragment 点击",Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private View createView(){
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundColor(0xaaD81B60);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(getContext());
        textView1.setTextColor(Color.BLACK);
        textView1.setText("This is patch Fragment without xml res1");

        TextView textView2 = new TextView(getContext());
        textView2.setTextColor(Color.BLACK);
        textView2.setText("This is patch Fragment without xml res2");

        TextView textView3 = new TextView(getContext());
        textView3.setTextColor(Color.BLACK);
        textView3.setText("This is patch Fragment without xml res3");

        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        linearLayout.addView(textView3);

        return linearLayout;
    }
}
