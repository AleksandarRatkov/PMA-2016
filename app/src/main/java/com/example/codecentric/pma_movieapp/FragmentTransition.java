package com.example.codecentric.pma_movieapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentTransition
{
	public static void to(Fragment newFragment, FragmentActivity activity)
	{
		to(newFragment, activity, true);
	}
	
	public static void to(Fragment newFragment, FragmentActivity activity, boolean addToBackstack)
	{
		FragmentTransaction transaction = activity.getSupportFragmentManager()
			.beginTransaction()
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
			.replace(R.id.mainContent, newFragment);
		if(addToBackstack) transaction.addToBackStack(null);
		transaction.commit();
	}
}
