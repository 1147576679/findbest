package com.example.zjy.fragment.community;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.zjy.bantang.R;
import com.example.zjy.fragment.community.bean.CommunityPostVO;
import com.example.zjy.fragment.community.vm.CommunityPostVM;
import com.example.zjy.niklauslibrary.base.BaseActivity;
import com.example.zjy.niklauslibrary.util.CirImageViewUtils;
import com.example.zjy.niklauslibrary.util.ConvenientBannerUtils;
import com.example.zjy.niklauslibrary.util.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityPostDetailActivity extends BaseActivity {
    private static final String ID = "id";
    private CommunityPostVM mCommunityPostVM;
    @Bind(R.id.post_community_vp)
    ConvenientBanner mConvenientBanner;
    @Bind(R.id.tv_content)
    TextView mTvContent;
    @Bind(R.id.praise_count)
    TextView mPraiseCount;
    @Bind(R.id.user_icon)
    CircleImageView mCircleImageView;
    @Bind(R.id.tv_author)
    TextView mTvAuthor;
    @Bind(R.id.tv_nickname)
    TextView mTvNickname;
    private String mId;
    @Bind(R.id.checkbox_praise)
    CheckBox mCheckboxPraise;

    @Override
    public int getContentId() {
        return R.layout.activity_community_post_detail;
    }

    public static Intent newInstance(Context context,String id){
        Intent intent = new Intent(context,CommunityPostDetailActivity.class);
        intent.putExtra(ID,id);
        return intent;
    }

    @OnClick(R.id.iv_back_arrow)
    public void ivClick(View view){
        finish();
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        mId = intent.getStringExtra(ID);
        mCommunityPostVM = new CommunityPostVM();
        mCommunityPostVM.getData(mId, new CommunityPostVM.CallBack() {
            @Override
            public void callback(CommunityPostVO communityPostVO) {
                mTvContent.setText(communityPostVO.content);
                mTvAuthor.setText(communityPostVO.username);
                mTvNickname.setText(communityPostVO.dataStr);
                mPraiseCount.setText(communityPostVO.praises);
                CirImageViewUtils.loadCirImage(communityPostVO.avatar,CommunityPostDetailActivity.this,mCircleImageView);
                new ConvenientBannerUtils.Builder()
                        .mList(communityPostVO.ivUrl)
                        .orientation(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                        .indicatorSelect(R.drawable.ic_indicator_image_selected)
                        .indicatorUnselect(R.drawable.ic_indicator_image)
                        .time(3000)
                        .build()
                        .ConvenientBanner(mConvenientBanner);
            }
        });

    }

    @Override
    protected void bindListener() {
        mCheckboxPraise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPraiseCount.setText((Integer.parseInt(mPraiseCount.getText().toString())+1)+"");
                    ToastUtils.showToast(CommunityPostDetailActivity.this,"点赞成功");
                }else {
                    mPraiseCount.setText((Integer.parseInt(mPraiseCount.getText().toString())-1)+"");
                }
            }
        });
    }

    @Override
    protected boolean isOpenTranslucent() {
        return false;
    }
}
