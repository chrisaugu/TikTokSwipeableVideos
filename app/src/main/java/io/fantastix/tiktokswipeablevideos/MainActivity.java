package io.fantastix.tiktokswipeablevideos;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import io.fantastix.tiktokswipeablevideos.databinding.ActivityMainBinding;
import io.fantastix.tiktokswipeablevideos.model.VideoItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ViewPager2 videosViewPager = binding.viewPagerVideos;
        List<VideoItem> videoItems = new ArrayList<>();

        String path = "android.resource://" + getPackageName() + "/";

        VideoItem item = new VideoItem();
        item.videoURL = path + "/raw/a"; //.mp4";
        item.videoTitle = "Women In Tech";
        item.videoDesc = "International Women's Day 2019";
        videoItems.add(item);

        VideoItem item2 = new VideoItem();
        item2.videoURL = path + "/raw/b"; //.mp4";
        item2.videoTitle = "Sasha Solomon";
        item2.videoDesc = "How Sasha Solomon Became a Software Developer at Twitter";
        videoItems.add(item2);

        VideoItem item3 = new VideoItem();
        item3.videoURL = path + "/raw/c"; //.mp4";
        item3.videoTitle = "Happy Hour Wednesday";
        item3.videoDesc = " Depth-First Search Algorithm";
        videoItems.add(item3);

        videosViewPager.setAdapter(new VideoAdapter(videoItems));
    }
}