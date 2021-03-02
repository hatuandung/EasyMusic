package com.example.easymusic.ui.screen.music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.easymusic.ui.screen.music.album.AlbumFragment;
import com.example.easymusic.ui.screen.music.artist.ArtistFragment;
import com.example.easymusic.ui.screen.music.list.ListFragment;
import com.example.easymusic.ui.screen.music.song.SongFragment;


public class MusicPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments;

    public MusicPagerAdapter(@NonNull FragmentManager fm, Fragment... fragments) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "BÀI HÁT";
            case 1:
                return "NGHỆ SĨ";
            case 2:
                return "ALBUM";
            case 3:
                return "DANH SÁCH";
        }

        return super.getPageTitle(position);
    }
}
