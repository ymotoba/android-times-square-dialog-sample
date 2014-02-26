package com.example.android_times_square_dialog_sample;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.OnInvalidDateSelectedListener;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.startCalendarButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final SelectDateDialogFragment selectDateDialogFragment = SelectDateDialogFragment.newInstance();
				selectDateDialogFragment.onDateSelectedListener = new OnDateSelectedListener() {
					@Override
					public void onDateUnselected(Date date) {
					}
					@Override
					public void onDateSelected(Date date) {
						selectDateDialogFragment.dismissAllowingStateLoss();
						String selectedDate = FastDateFormat.getInstance("yyyy/M/d").format(date);
						Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
					}
				};
				selectDateDialogFragment.onInvalidDateSelectedListener = new OnInvalidDateSelectedListener() {
					@Override
					public void onInvalidDateSelected(Date date) {
						Toast.makeText(getApplicationContext(), "invalid date", Toast.LENGTH_LONG).show();
					}
				};
				selectDateDialogFragment.show(getSupportFragmentManager(), SelectDateDialogFragment.class.getCanonicalName());			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
