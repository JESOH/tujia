package com.it.tujia.module.mine.frag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it.tujia.R;
import com.it.tujia.module.login.activity.LoginActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2015/11/18.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.iv_mine_user)
    private ImageView mImageUser;
    @ViewInject(R.id.tv_mine_order)
    private RelativeLayout order;
    @ViewInject(R.id.tv_mine_score)
    private RelativeLayout score;
    @ViewInject(R.id.tv_mine_balance)
    private RelativeLayout balance;
    @ViewInject(R.id.tv_mine_gift)
    private RelativeLayout gift;
    @ViewInject(R.id.tv_mine_taste)
    private RelativeLayout taste;
    @ViewInject(R.id.tv_mine_repay)
    private RelativeLayout repay;
    @ViewInject(R.id.tv_mine_invite)
    private RelativeLayout invite;
    @ViewInject(R.id.tv_mine_service)
    private RelativeLayout service;
    @ViewInject(R.id.tv_tel)
    private TextView tel;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_mine,container,false);
        ViewUtils.inject(this,view);

        mImageUser.setOnClickListener(this);
        order.setOnClickListener(this);
        score.setOnClickListener(this);
        balance.setOnClickListener(this);
        gift.setOnClickListener(this);
        taste.setOnClickListener(this);
        repay.setOnClickListener(this);
        invite.setOnClickListener(this);
        service.setOnClickListener(this);

        return view;
    }

    /**
     * 全部订单
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        switch (v.getId()){
            case R.id.iv_mine_user:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_order:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_score:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_balance:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_gift:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_taste:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_repay:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_invite:
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_mine_service:
                final String phoneNum = tel.getText().toString().trim();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(phoneNum);
                builder.setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myDial = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNum));
//                        myDial.setAction(Intent.ACTION_VIEW);
                        startActivity(myDial);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
}
