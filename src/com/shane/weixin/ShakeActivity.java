package com.shane.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shane.eventlistener.ShakeListener;
import com.shane.eventlistener.ShakeListener.OnShakeListener;

public class ShakeActivity extends Activity{

	ShakeListener mShakeListener = null;
	Vibrator mVibrator;
	private RelativeLayout mImgUp;
	private RelativeLayout mImgDown;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flashfriend);
		
		mVibrator = (Vibrator)getApplication().getSystemService(VIBRATOR_SERVICE);
		mImgUp = (RelativeLayout)findViewById(R.id.shakeImgUp);
		mImgDown = (RelativeLayout)findViewById(R.id.shakeImgDown);
		
		mShakeListener = new ShakeListener(this);
		mShakeListener.setOnShakeListener(new OnShakeListener(){
			@Override
			public void OnShake() {
				startAnim();
				mShakeListener.stop();
				startVibrator();
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "摇一摇", Toast.LENGTH_SHORT).show();
						mVibrator.cancel();
						mShakeListener.start();
					}}, 2000);
			}});
	}
	
	public void startVibrator(){
		mVibrator.vibrate(new long[]{500, 200, 500, 200}, -1);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mShakeListener != null){
			mShakeListener.stop();
		}
	}

	public void startAnim(){
		AnimationSet animup = new AnimationSet(true);
		TranslateAnimation mTranslateanimUp0 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,-0.5f);
		mTranslateanimUp0.setDuration(1000);
		TranslateAnimation mTranslateanimUp1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,+0.5f);
		mTranslateanimUp1.setDuration(1000);
		mTranslateanimUp1.setStartOffset(1000);
		animup.addAnimation(mTranslateanimUp0);
		animup.addAnimation(mTranslateanimUp1);
		mImgUp.startAnimation(animup);
		
		AnimationSet animDown = new AnimationSet(true);
		TranslateAnimation mTranslateanimDown0 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,0f,
				Animation.RELATIVE_TO_SELF,+0.5f);
		mTranslateanimDown0.setDuration(1000);
		TranslateAnimation mTranslateanimDown1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);
		mTranslateanimDown1.setDuration(1000);
		mTranslateanimDown1.setStartOffset(1000);
		animDown.addAnimation(mTranslateanimDown0);
		animDown.addAnimation(mTranslateanimDown1);
		mImgDown.startAnimation(animDown);	
		
	}
}
