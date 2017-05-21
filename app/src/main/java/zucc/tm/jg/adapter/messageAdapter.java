package zucc.tm.jg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import zucc.tm.jg.R;
import zucc.tm.jg.bean.MessageBean;


public class messageAdapter extends ArrayAdapter<MessageBean> {

    private int resourceId;
    public messageAdapter(Context context, int resource, List<MessageBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageBean msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();

            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout1);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout1);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg1);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg1);
            viewHolder.dateMsgLeft = (TextView) view.findViewById(R.id.date_msg_left);
            viewHolder.dateMsgRight = (TextView) view.findViewById(R.id.date_msg_right);
            viewHolder.leftHead = (ImageView) view.findViewById(R.id.left_head1);
            viewHolder.rightHead = (ImageView) view.findViewById(R.id.right_head1);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if (msg.getType() == MessageBean.RECEIVE) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftHead.setVisibility(View.VISIBLE);
            viewHolder.rightHead.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
            viewHolder.dateMsgRight.setVisibility(View.GONE);
            viewHolder.dateMsgLeft.setVisibility(View.VISIBLE);
            viewHolder.dateMsgLeft.setText(new SimpleDateFormat("MM-dd EEE HH:mm", new Locale("ZH", "CN"))
                    .format(msg.getMsgDate()));
        } else if (msg.getType() == MessageBean.SEND) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.leftHead.setVisibility(View.GONE);
            viewHolder.rightHead.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
            viewHolder.dateMsgLeft.setVisibility(View.GONE);
            viewHolder.dateMsgRight.setVisibility(View.VISIBLE);
            viewHolder.dateMsgRight.setText(new SimpleDateFormat("MM-dd EEE HH:mm", new Locale("ZH", "CN"))
                    .format(msg.getMsgDate()));
        }

        return view;
    }

    class ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
//        TextView dateMsg;
        TextView dateMsgLeft;
        TextView dateMsgRight;
        ImageView leftHead;
        ImageView rightHead;
    }
}
