package com.example.warcraft.plugin.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.warcraft.R;

/**
 * <ul>
 * <li>Author: luson.he </li>
 * <li>Time: 2020/3/23 下午9:51</li>
 * <li>Contack: unicorn.luson@gmail.com</li>
 * </ul>
 */
public class PatchResFragment extends Fragment {

    private static final String TAG = "PatchResFragment";
    private View rootView = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Resources resources = getActivity().getApplication().getResources();
//
        int layoutId = resources.getIdentifier("patch_fragment","layout","com.example.warcraft"/*getActivity().getPackageName()*/);
        int drawableId = resources.getIdentifier("war3banner","drawable","com.example.warcraft"/*getActivity().getPackageName()*/);
        Log.i(TAG,"pkg Name:" + getActivity().getPackageName());
        Log.i(TAG,"layoutId:" + layoutId);
        Log.i(TAG,"drawableId:" + drawableId);
        Drawable drawable = resources.getDrawable(drawableId);
        Log.i(TAG,"drawable:" + drawable);
//        rootView =  inflater.inflate(R.layout.patch_fragment,container,false);
//
//        ImageView imageView = rootView.findViewById(R.id.iv);
//        imageView.setImageDrawable(drawable);

        rootView = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.patch_fragment,container,false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        rootView.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"fragment 点击",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
