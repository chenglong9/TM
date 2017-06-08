package zucc.tm.jg.Util;

/**
 * Created by 45773 on 2017-05-23.
 */
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AlertDialog;
import zucc.tm.jg.R;

/**
 * UiUtil.
 * Created by admin on 15-12-22.
 */
public class alertdialog {
    private static AlertDialog showDialog(Context context, String title, String message, View contentView,
                                          String positiveBtnText, String negativeBtnText,
                                          DialogInterface.OnClickListener positiveCallback,
                                          DialogInterface.OnClickListener negativeCallback,
                                          boolean cancelable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title == null ? "提示" : title);
        if (message != null) {
            builder.setMessage(message);
        }

        if (contentView != null) {
            builder.setView(contentView);
        }

        if (positiveBtnText != null) {
            builder.setPositiveButton(positiveBtnText, positiveCallback);
        }

        if (negativeBtnText != null) {
            builder.setNegativeButton(negativeBtnText, negativeCallback);
        }

        builder.setCancelable(cancelable);
        return builder.show();
    }

    //普通对话框
    public static AlertDialog showSimpleDialog(Context context, String title, String message,
                                               String positiveBtnText, String negativeBtnText,
                                               DialogInterface.OnClickListener positiveCallback,
                                               DialogInterface.OnClickListener negativeCallback,
                                               boolean cancelable) {

        return showDialog(context, title, message, null, positiveBtnText, negativeBtnText, positiveCallback, negativeCallback, cancelable);
    }
  //带输入的对话框
    public static AlertDialog showeditDialog(Context context, String title, String message, View view,
                                                 String positiveBtnText, String negativeBtnText,
                                                 DialogInterface.OnClickListener positiveCallback,
                                                 DialogInterface.OnClickListener negativeCallback,
                                                 boolean cancelable) {

        return showDialog(context, title, null, view, positiveBtnText, negativeBtnText, positiveCallback, negativeCallback, cancelable);
    }

}
 /* //带ProgressBar的对话框
    public static alertdialog showProgressDialog(Context context, String title, String message,
                                                 String positiveBtnText, String negativeBtnText,
                                                 DialogInterface.OnClickListener positiveCallback,
                                                 DialogInterface.OnClickListener negativeCallback,
                                                 boolean cancelable) {

        View view = LayoutInflater.from(context).inflate(R.layout.circular_progressbar, null);
        if (message != null) {
            final TextView messageTv = (TextView) view.findViewById(R.id.progressbar_msg);
            messageTv.setText(message);
        }

        return showDialog(context, title, null, view, positiveBtnText, negativeBtnText, positiveCallback, negativeCallback, cancelable);
    }*/