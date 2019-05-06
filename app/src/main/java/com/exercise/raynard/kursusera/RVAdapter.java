package com.exercise.raynard.kursusera;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter {
    List<CourseData> courses;
    private Context context;

    public RVAdapter(List<CourseData> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == CourseData.COURSE_HEADER) {
            // inflate yg model pertama (judul rv)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_model, viewGroup, false);
            return new CourseHeaderHolder(view);
        } else {
            // inflate main item
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_content_model, viewGroup, false);
            return new CourseViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CourseData item = courses.get(position);

        if (item != null) {
            if (item.type == CourseData.COURSE_HEADER) {
                // masukkan data untuk cardview judul
                ((CourseHeaderHolder) viewHolder).headerTitle.setText(item.getCourseTitle());
            } else {
                // masukkan data untuk cardview course
                ((CourseViewHolder) viewHolder).courseTitle.setText(item.getCourseTitle());
                ((CourseViewHolder) viewHolder).courseDesc.setText(item.getCourseDesc());
//                ((CourseViewHolder) viewHolder).courseImage.setImageResource(item.getCourseImage());

                // library yg bisa nge-fetch / ngedownload dari source (e.g. url/path)
                // lalu hasil yg dia download dimasukkan ke container (imageView, videoview)
            Glide.with(context)
                    .load(item.getCourseImage()) // source
                    .into((((CourseViewHolder) viewHolder).courseImage)); //container
            }
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (courses.get(position).type == 0) {
            return CourseData.COURSE_HEADER;
        } else {
            return CourseData.COURSE_DETAIL;
        }
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;
        TextView courseDesc;
        ImageView courseImage;

//        public CourseViewHolder(@NonNull View itemView, CardView cardView, TextView courseTitle, TextView corseDesc, ImageView courseImage) {
//            super(itemView);
//            this.courseTitle = courseTitle;
//            this.corseDesc = corseDesc;
//            this.courseImage = courseImage;
//        }

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.card_title);
            courseDesc = itemView.findViewById(R.id.card_description);
            courseImage = itemView.findViewById(R.id.card_image);
        }
    }

    public static class CourseHeaderHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public CourseHeaderHolder(@NonNull View itemView) {
            super(itemView);
            headerTitle = itemView.findViewById(R.id.tv_course_header);
        }
    }
}
