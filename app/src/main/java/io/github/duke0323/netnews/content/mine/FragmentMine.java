package io.github.duke0323.netnews.content.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.github.duke0323.netnews.R;

/**
 * Created by ${Duke} on 2016/6/25.
 */
public class FragmentMine extends Fragment {
    private LinearLayout ll_setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ll_setting = (LinearLayout) view.findViewById(R.id.ll_setting);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConfigActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter_in, R.anim.enter_out);
            }
        });
    }
}
