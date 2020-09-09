package com.adeks.tabapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adeks.tabapp.R;
import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    private List<StudentIq> mStudentIqs;
    private List<StudentHrs> mStudentHrs;
    private boolean isStudentIq;

    public RecyclerViewAdapter(Context context, List<StudentIq> dataList, boolean isStudentIq) {
        if (mContext == null) {
            mContext = context;
            mStudentIqs = dataList;
            mStudentHrs = null;
        } else {
            mStudentIqs = dataList;
            mStudentHrs = null;
            notifyDataSetChanged();
        }
        Log.d(TAG, "RecyclerViewAdapter: dataList ==" + mStudentIqs.toString());
        this.isStudentIq = isStudentIq;
    }

    public RecyclerViewAdapter(Context context, List<StudentHrs> studentHrs) {
        if (mContext == null) {
            mContext = context;
            mStudentHrs = studentHrs;
            mStudentIqs = null;
        } else {
            mStudentHrs = studentHrs;
            mStudentIqs = null;
            notifyDataSetChanged();
        }
        Log.d(TAG, "RecyclerViewAdapter: dataList ==" + mStudentHrs);
        isStudentIq = false;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.learning_view, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        if (isStudentIq) {
            StudentIq student = mStudentIqs.get(position);
            holder.mStudentName.setText(student.getName());
            String val = createTextStringIq(student);
            holder.mStudentDetail.setText(val);
            Picasso.get().load(student.getImageUri()).into(holder.mImage);
        } else {
            StudentHrs student = mStudentHrs.get(position);
            holder.mStudentName.setText(student.getName());
            String val = createTextStringHr(student);
            holder.mStudentDetail.setText(val);
            Picasso.get().load(student.getImageUri()).into(holder.mImage);
        }

    }

    private String createTextStringIq(StudentIq student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getScore())
                .append(" ").append("skill IQ score,")
                .append(student.getCountry());
        return sb.toString();
    }

    private String createTextStringHr(StudentHrs student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getHours())
                .append(" ").append("learning hours,")
                .append(student.getCountry())
                .append(".");
        return sb.toString();
    }


    @Override
    public int getItemCount() {
        if (mContext != null) {
            if (isStudentIq) {
                return mStudentIqs.size();
            } else {
                return mStudentHrs.size();
            }
        }
        return 0;
    }

    public class VHolder extends RecyclerView.ViewHolder {

        private final TextView mStudentName;
        private final TextView mStudentDetail;
        private final ImageView mImage;

        public VHolder(@NonNull View itemView) {

            super(itemView);
            mImage = itemView.findViewById(R.id.image_badge);
            mStudentDetail = itemView.findViewById(R.id.student_detail);
            mStudentName = itemView.findViewById(R.id.student_name);
        }
    }
}
