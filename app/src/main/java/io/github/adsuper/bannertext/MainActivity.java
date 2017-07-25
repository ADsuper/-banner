package io.github.adsuper.bannertext;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.adsuper.bannertext.bannerview.MZBannerView;
import io.github.adsuper.bannertext.bannerview.holder.MZHolderCreator;
import io.github.adsuper.bannertext.bannerview.holder.MZViewHolder;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @BindView(R.id.banner)
    MZBannerView mBanner;
    private ImageView mMImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initBannerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBanner.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBanner.pause();
    }

    List<Integer> mList = new ArrayList<>();

    /**
     * 设置 bannerView
     */
    private void initBannerView() {
        mList.add(R.drawable.gril);
        mList.add(R.drawable.gril1);
        mList.add(R.drawable.gril2);
        mList.add(R.drawable.gril3);
        mList.add(R.drawable.gril4);
        //设置页面点击事件
        mBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }
        });
        //设置数据
        mBanner.setPages(mList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });


    }

    public class BannerViewHolder implements MZViewHolder<Integer> {
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mMImageView = view.findViewById(R.id.banner_image);

            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {

            mMImageView.setImageResource(data);
        }
    }


}
