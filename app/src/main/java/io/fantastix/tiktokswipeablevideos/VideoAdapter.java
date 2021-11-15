package io.fantastix.tiktokswipeablevideos;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.fantastix.tiktokswipeablevideos.model.VideoItem;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<VideoItem> mVideoItems;

    public VideoAdapter(List<VideoItem> videoItems) {
        this.mVideoItems = videoItems;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(mVideoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private final VideoView mVideoView;
        private final TextView txtTitle;
        private final TextView txtDesc;
        private final ProgressBar mProgressBar;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.videoView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            mProgressBar = itemView.findViewById(R.id.progressBar);
        }
        void setVideoData(VideoItem videoItem){
            txtTitle.setText(videoItem.videoTitle);
            txtDesc.setText(videoItem.videoDesc);
            mVideoView.setVideoPath(videoItem.videoURL);
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mProgressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float)mp.getVideoHeight();
                    float screenRatio = mVideoView.getWidth() / (float)mVideoView.getHeight();
                    float scale  = videoRatio / screenRatio;
                    if (scale >= 1f){
                        mVideoView.setScaleX(scale);
                    }
                    else {
                        mVideoView.setScaleY(1f / scale);
                    }
                }
            });
            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    mp.start();
                    mp.stop();
                }
            });
        }
    }
}
