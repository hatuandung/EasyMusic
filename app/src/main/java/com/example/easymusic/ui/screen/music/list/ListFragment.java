package com.example.easymusic.ui.screen.music.list;

import com.example.easymusic.R;
import com.example.easymusic.databinding.FragmentListBinding;
import com.example.easymusic.ui.base.BaseFragment;

public class ListFragment extends BaseFragment<FragmentListBinding, ListViewModel> {
    @Override
    protected Class<ListViewModel> getViewModelClass() {
        return ListViewModel.class;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_list;
    }
}
