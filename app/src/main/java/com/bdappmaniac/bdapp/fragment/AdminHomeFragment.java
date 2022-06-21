package com.bdappmaniac.bdapp.fragment;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bdappmaniac.bdapp.R;
import com.bdappmaniac.bdapp.activity.AdminActivity;
import com.bdappmaniac.bdapp.adapter.AdminHomeAdapter;
import com.bdappmaniac.bdapp.databinding.FragmentAdminHomeBinding;
import com.bdappmaniac.bdapp.helper.TextToBitmap;
import com.bdappmaniac.bdapp.model.AdminHomeModel;
import com.bdappmaniac.bdapp.utils.AppBarStateChangeListener;
import com.bdappmaniac.bdapp.utils.SharedPref;
import com.bdappmaniac.bdapp.utils.StatusBarUtils;
import com.bdappmaniac.bdapp.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Locale;

public class AdminHomeFragment extends BaseFragment {
    private final static float EXPAND_AVATAR_SIZE_DP = 90f;
    private final static float COLLAPSED_AVATAR_SIZE_DP = 35f;
    private final float[] mAvatarPoint = new float[2], mSpacePoint = new float[2], mToolbarTextPoint = new float[2], mTitleTextViewPoint = new float[2];
    FragmentAdminHomeBinding binding;
    ArrayList<AdminHomeModel> itemList = new ArrayList<>();
    AdminHomeAdapter adapter;
    private float mTitleTextSize;
    private AppBarStateChangeListener mAppBarStateChangeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_home, container, false);
            SharedPref.init(mContext);
            updateProfile();
            binding.getRoot().requestFocus();
            binding.getRoot().getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
                @Override
                public void onWindowFocusChanged(final boolean hasFocus) {
                    if (!hasFocus) {
                        return;
                    }
                    resetPoints(false);
                }
            });
//           new Handler().postDelayed(new Runnable() {
//           @Override
//            public void run() {
//                setProgress();
//            }
//              }, 1000);
//        binding.exploreTxt.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.loanFragment);
//        });
//        return binding.getRoot();
//    }
//
//    public void setProgress() {
//        binding.semiCircleArcProgressBar.setPercentWithAnimation(45);
//    }
            binding.textViewTitle.setText(SharedPref.getUserDetails().getEmployeeName());
            binding.settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.settingFragment);
                }
            });
            binding.imageViewAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.adminProfile);
                }
            });
            ArrayList<AdminHomeModel> itemList = new ArrayList<>();
            itemList.add(new AdminHomeModel(R.drawable.icn_advance_payment, "Advance Payment"));
            itemList.add(new AdminHomeModel(R.drawable.icn_attendence, "Attendance"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_employee_list, "Employee List"));
            itemList.add(new AdminHomeModel(R.drawable.icn_expense, "Expenses"));
            itemList.add(new AdminHomeModel(R.drawable.icn_holidays, "Holiday"));
            itemList.add(new AdminHomeModel(R.drawable.icn_locks, "Lock / Unlock"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_to_do_list, "OverTime List"));
//        itemList.add(new AdminHomeModel(R.drawable.icn_to_do_list, "Task List"));
            itemList.add(new AdminHomeModel(R.drawable.icn_lef, "Leaves"));

            adapter = new AdminHomeAdapter(itemList, getContext());
            binding.recyclerMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.recyclerMenu.setAdapter(adapter);

            setUpViews();
//            fetchAvatar();
        }
        return binding.getRoot();
    }

    private void setUpViews() {
        mTitleTextSize = binding.textViewTitle.getTextSize();
        setUpToolbar();
        setUpRecyclerView();
        setUpAmazingAvatar();
    }

    private void setUpToolbar() {
        binding.appBars.getLayoutParams().height = Utils.getDisplayMetrics(mContext).widthPixels * 9 / 16;
        binding.appBars.requestLayout();

        ((AdminActivity)mContext).setSupportActionBar(binding.toolbars);
        if ( ((AdminActivity)mContext).getSupportActionBar() != null) {
            ((AdminActivity)mContext).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AdminActivity)mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpRecyclerView() {
        binding.recyclerMenu.setHasFixedSize(true);
        binding.recyclerMenu.setItemAnimator(new DefaultItemAnimator());
    }

    private void setUpAmazingAvatar() {
        mAppBarStateChangeListener = new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
            }

            @Override
            public void onOffsetChanged(AppBarStateChangeListener.State state, float offset) {
                translationView(offset);
            }
        };
        binding.appBars.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    private void translationView(float offset) {
        float newAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, mContext);
        float expandAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, mContext);
        float xAvatarOffset = (mSpacePoint[0] - mAvatarPoint[0] - (expandAvatarSize - newAvatarSize) / 2f) * offset;
        float yAvatarOffset = (mSpacePoint[1] - mAvatarPoint[1] - (expandAvatarSize - newAvatarSize)) * offset;
        binding.imageViewAvatar.getLayoutParams().width = Math.round(newAvatarSize);
        binding.imageViewAvatar.getLayoutParams().height = Math.round(newAvatarSize);
        binding.imageViewAvatar.setTranslationX(xAvatarOffset);
        binding.imageViewAvatar.setTranslationY(yAvatarOffset);

        float newTextSize = mTitleTextSize - (mTitleTextSize - binding.toolbarTitle.getTextSize()) * offset;
        Paint paint = new Paint(binding.textViewTitle.getPaint());
        paint.setTextSize(newTextSize);
        float newTextWidth = Utils.getTextWidth(paint, binding.textViewTitle.getText().toString());
        paint.setTextSize(mTitleTextSize);
        float originTextWidth = Utils.getTextWidth(paint, binding.textViewTitle.getText().toString());
        boolean isRTL = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL || binding.viewContainers.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        float xTitleOffset = ((mToolbarTextPoint[0] + (isRTL ?  binding.toolbarTitle.getWidth() : 0)) - (mTitleTextViewPoint[0] + (isRTL ? binding.textViewTitle.getWidth() : 0)) - (binding.toolbarTitle.getWidth() > newTextWidth ? (originTextWidth - newTextWidth) / 2f : 0)) * offset;
        float yTitleOffset = (mToolbarTextPoint[1] -mTitleTextViewPoint[1]) * offset;
        binding.textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
        binding.textViewTitle.setTranslationX(xTitleOffset);
        binding.textViewTitle.setTranslationY(yTitleOffset);
    }

//    private void fetchAvatar() {
//        NetworkHelper.getAvatar(new AvatarCallback() {
//
//            @Override
//            public void onSuccess(final AvatarModel avatarModel) {
//                super.onSuccess(avatarModel);
//                if (((AdminActivity) mContext).isFinishing()) {
//                    return;
//                }
//                ((AdminActivity) mContext).runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Glide.with(AdminHomeFragment.this).load(avatarModel.url).into(binding.imageViewAvatar);
//                        String name = String.format(Locale.getDefault(), "%s %s", avatarModel.firstName, avatarModel.lastName);
//                        binding.textViewTitle.setText(name);
//                        binding.textViewTitle.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                resetPoints(true);
//                            }
//                        });
//                    }
//                });
//            }
//        });
//    }

    void textProfile() {
        Bitmap bitmap = TextToBitmap.textToBitmap("name", mContext, 10, R.color.black);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        binding.imageViewAvatar.setImageDrawable(d);
    }

    public void updateProfile() {
        if (SharedPref.getUserDetails().getProfile().isEmpty()) {
            textProfile();
        } else {
            Glide.with(this).load(SharedPref.getUserDetails().getProfile()).placeholder(R.drawable.user).into(binding.imageViewAvatar);
        }
    }

    private void resetPoints(boolean isTextChanged) {
        final float offset = mAppBarStateChangeListener.getCurrentOffset();
        float newAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, mContext);
        float expandAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, mContext);
        int[] avatarPoint = new int[2];
        binding.imageViewAvatar.getLocationOnScreen(avatarPoint);
        mAvatarPoint[0] = avatarPoint[0] - binding.imageViewAvatar.getTranslationX() - (expandAvatarSize - newAvatarSize) / 2f;
        mAvatarPoint[1] = avatarPoint[1] - binding.imageViewAvatar.getTranslationY() - (expandAvatarSize - newAvatarSize);

        int[] spacePoint = new int[2];
        binding.space.getLocationOnScreen(spacePoint);
        mSpacePoint[0] = spacePoint[0];
        mSpacePoint[1] = spacePoint[1];

        int[] toolbarTextPoint = new int[2];
        binding.toolbarTitle.getLocationOnScreen(toolbarTextPoint);
        mToolbarTextPoint[0] = toolbarTextPoint[0];
        mToolbarTextPoint[1] = toolbarTextPoint[1];

        Paint paint = new Paint(binding.textViewTitle.getPaint());
        float newTextWidth = Utils.getTextWidth(paint, binding.textViewTitle.getText().toString());
        paint.setTextSize(mTitleTextSize);
        float originTextWidth = Utils.getTextWidth(paint, binding.textViewTitle.getText().toString());
        int[] titleTextViewPoint = new int[2];
        binding.textViewTitle.getLocationOnScreen(titleTextViewPoint);
        mTitleTextViewPoint[0] = titleTextViewPoint[0] - binding.textViewTitle.getTranslationX() - (binding.toolbarTitle.getWidth() > newTextWidth ? (originTextWidth - newTextWidth) / 2f : 0);
        mTitleTextViewPoint[1] = titleTextViewPoint[1] - binding.textViewTitle.getTranslationY();

        if (isTextChanged) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    translationView(offset);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.statusBarColor(getActivity(), R.color.prime);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.getRoot().clearFocus();
    }
}