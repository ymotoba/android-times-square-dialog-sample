package com.example.android_times_square_dialog_sample;


import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.OnInvalidDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;

public class SelectDateDialogFragment extends DialogFragment {
	private CalendarPickerView calendar;
	public OnDateSelectedListener onDateSelectedListener;
	public OnInvalidDateSelectedListener onInvalidDateSelectedListener;
	/**
	 * @return
	 */
	public static SelectDateDialogFragment newInstance() {
		SelectDateDialogFragment instance = new SelectDateDialogFragment();
		Bundle arguments = new Bundle();
		instance.setArguments(arguments);
		return instance;
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.DialogFragment#show(android.support.v4.app.FragmentManager, java.lang.String)
	 */
	@Override
	public final void show(final FragmentManager manager, final String tag) {
		deleteDialogFragment(manager, tag);
		final FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
	}
	/**
	 * @param fragmentManager
	 * @param tag
	 */
	private static void deleteDialogFragment(final FragmentManager fragmentManager, final String tag) {
		final SelectDateDialogFragment prev = (SelectDateDialogFragment) fragmentManager.findFragmentByTag(tag);
		// フラグメントが表示されていなければ処理なし
		if (prev == null) {
			return;
		}
		final Dialog dialog = prev.getDialog();
		// ダイアログがなければ処理なし
		if (dialog == null) {
			return;
		}
		// ダイアログが表示されていなければ処理なし
		if (!dialog.isShowing()) {
			return;
		}
		// ダイアログ消去通知と消去
		prev.dismissAllowingStateLoss();
	}
	/* (non-Javadoc)
	 * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		dialog.setContentView(R.layout.fragment_select_date_dialog);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
	    calendar = (CalendarPickerView) dialog.findViewById(R.id.calendar_view);
	    final Calendar maxDate = Calendar.getInstance();
	    maxDate.add(Calendar.DAY_OF_MONTH, 14);
	    calendar.init(Calendar.getInstance().getTime(), maxDate.getTime())
	        .inMode(SelectionMode.SINGLE)
	        .withSelectedDate(new Date());
	    calendar.setOnDateSelectedListener(onDateSelectedListener);
	    calendar.setOnInvalidDateSelectedListener(onInvalidDateSelectedListener);
		return dialog;
	}
}
