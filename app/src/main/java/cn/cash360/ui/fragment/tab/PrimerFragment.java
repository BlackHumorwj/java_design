package cn.cash360.ui.fragment.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.cash360.bean.PrimerBean;
import cn.cash360.java_design.R;
import cn.cash360.ui.activity.ainination.MyAnimationActivity;
import cn.cash360.ui.activity.customview.MyViewActivity;
import cn.cash360.ui.activity.sidazujian.ZuJianActivity;

/**
 * @time 2019/4/12 12:53
 * @desc
 */

public class PrimerFragment extends BaseFragment {

    private RecyclerView recyclerView;

    public static PrimerFragment newInstance() {
        Bundle args = new Bundle();
        PrimerFragment fragment = new PrimerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_primer_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = mContentView.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new PrimerAdapter(getList()));
    }

    private ArrayList<PrimerBean> getList() {
        ArrayList<PrimerBean> list = new ArrayList<PrimerBean>();
        list.add(new PrimerBean<ZuJianActivity>(ZuJianActivity.class, "四大组件"));
        list.add(new PrimerBean<MyViewActivity>(MyViewActivity.class, "自定义View"));
        list.add(new PrimerBean<MyAnimationActivity>(MyAnimationActivity.class, "动画和手势"));
        return list;
    }

    public class PrimerAdapter extends RecyclerView.Adapter<PrimerAdapter.Holder> {

        List<PrimerBean> mList;

        public PrimerAdapter(ArrayList<PrimerBean> list) {
            this.mList = list;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_primer, viewGroup, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder viewHolder, int i) {
            final PrimerBean primerBean = mList.get(viewHolder.getAdapterPosition());
            viewHolder.tvName.setText(primerBean.name);
            viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, primerBean.activity);
                    //非静态内部类持有外部内的引用 哈哈哈
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tvName;

            public Holder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
