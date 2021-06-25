package com.gci.mvvmdemo.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.commlib.util.LogUtil;
import com.gci.mvvmdemo.R;
import com.gci.mvvmdemo.activity.PhotoActivity;
import com.gci.mvvmdemo.databinding.ActivityPhotoBinding;
import com.luck.picture.lib.animators.AnimationType;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.language.LanguageConfig;
import com.luck.picture.lib.style.PictureCropParameterStyle;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureSelectorUIStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.ScreenUtils;


public class PhotoActivityVM extends AndroidViewModel {
    private static ActivityPhotoBinding binding;

    private int themeId;
    private boolean isWeChatStyle;
    private PictureSelectorUIStyle mSelectorUIStyle;
    private PictureParameterStyle mPictureParameterStyle;
    private PictureCropParameterStyle mCropParameterStyle;
    private PictureWindowAnimationStyle mWindowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();

    public String getLanguage() {
        return "语言：" + language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    private int language = -1;
    @SuppressLint("StaticFieldLeak")
    private static PhotoActivity activity;
    public static MutableLiveData<String> text = new MutableLiveData<>();
    //主题样式
    public static MutableLiveData<String> style = new MutableLiveData<>("主题样式：");

    public void setStyle(String style) {
        PhotoActivityVM.style.setValue("主题样式：" + style);
        LogUtil.i("style", PhotoActivityVM.getStyle().getValue());
    }

    public static MutableLiveData<String> getStyle() {
        return style;
    }

    //动画（默认启动动画、相册上弹动画）
    public static MutableLiveData<String> animation = new MutableLiveData<>();


    //列表动画
    public static MutableLiveData<String> listAnimStr = new MutableLiveData<>();
    private static int animationMode = AnimationType.DEFAULT_ANIMATION;
    public static MutableLiveData<Boolean> rb_default = new MutableLiveData<>(true);//默认动画
    public static MutableLiveData<Boolean> rb_alpha = new MutableLiveData<>(false);//列表渐变动画
    public static MutableLiveData<Boolean> rb_slide_in = new MutableLiveData<>(false);//列表底部滑入动画


    //全部、图片、视频、音频
    private int chooseMode = PictureMimeType.ofAll();

    //是否开启点击声音
    public static MutableLiveData<Boolean> cb_voice = new MutableLiveData<>(false);
    public static MutableLiveData<String> clickVoiceStr = new MutableLiveData<>();

    public static void setClickVoiceStr(Boolean clickVoiceStr) {
        PhotoActivityVM.clickVoiceStr.setValue("是否开启点击声音：" + clickVoiceStr);
    }

    public static MutableLiveData<String> getClickVoiceStr() {
        return clickVoiceStr;
    }

    //是否原图
    public static MutableLiveData<Boolean> original = new MutableLiveData<>(true);
    public static MutableLiveData<Integer> originalTipsVisible = new MutableLiveData<>(View.GONE);
    public static MutableLiveData<String> originalStr = new MutableLiveData<>();

    public static MutableLiveData<String> getOriginalStr() {
        return originalStr;
    }

    public static void setOriginalStr(Boolean originalStr) {
        PhotoActivityVM.originalStr.setValue("是否开启原图：" + originalStr);
        if (originalStr)
            originalTipsVisible.setValue(View.VISIBLE);
        else
            originalTipsVisible.setValue(View.GONE);
    }

    //是否分页
    public static MutableLiveData<Boolean> seperatePage = new MutableLiveData<>(false);
    public static MutableLiveData<String> seperatePageStr = new MutableLiveData<>();


    //是否显示蒙层
    public static MutableLiveData<Boolean> enableMask = new MutableLiveData<>(false);
    public static MutableLiveData<String> enableMaskStr = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getAlbumOrTake() {
        return albumOrTake;
    }

    public static void setAlbumOrTake(Boolean albumOrTake) {
        PhotoActivityVM.albumOrTake.setValue(albumOrTake);
    }

    //相册或单独拍照
    public static MutableLiveData<Boolean> albumOrTake = new MutableLiveData<>(true);
    public static MutableLiveData<String> albumOrTakeStr = new MutableLiveData<>();

    public static MutableLiveData<Boolean> getCbChooseMode() {
        return cbChooseMode;
    }

    public static void setCbChooseMode(Boolean cbChooseMode) {
        PhotoActivityVM.cbChooseMode.setValue(cbChooseMode);
    }

    //单选或多选
    public static MutableLiveData<Boolean> cbChooseMode = new MutableLiveData<>(true);
    public static MutableLiveData<String> cbChooseModeStr = new MutableLiveData<>();
    //使用自定义相机
    public static MutableLiveData<Boolean> customCamera = new MutableLiveData<>(true);
    public static MutableLiveData<Boolean> cb_preview_img = new MutableLiveData<>(true);
    public static MutableLiveData<Integer> cb_preview_img_visible = new MutableLiveData<Integer>();

    public static MutableLiveData<Boolean> getEnableMask() {
        return enableMask;
    }

    public static void setEnableMask(Boolean enableMask) {
        PhotoActivityVM.enableMask.setValue(enableMask);
    }

    public static MutableLiveData<Boolean> getSeperatePage() {
        return seperatePage;
    }

    public static void setSeperatePage(Boolean seperatePage) {
        PhotoActivityVM.seperatePage.setValue(seperatePage);
        PhotoActivityVM.seperatePageStr.setValue(seperatePage + "");
    }

    public static MutableLiveData<Integer> getCb_preview_img_visible() {
        return cb_preview_img_visible;
    }

    public static void setCb_preview_img_visible(int cb_preview_img_visible) {
        if (cb_preview_img_visible == View.VISIBLE)
            PhotoActivityVM.cb_preview_img_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_preview_img_visible.setValue(View.GONE);
    }


    public static MutableLiveData<Boolean> cb_preview_video = new MutableLiveData<>();
    public static MutableLiveData<Integer> cb_preview_video_visible = new MutableLiveData<Integer>();

    public static MutableLiveData<Integer> getCb_preview_Video_visible() {
        return cb_preview_video_visible;
    }

    public static void setCb_preview_video_visible(int visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_preview_video_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_preview_video_visible.setValue(View.GONE);
    }


    public static MutableLiveData<Boolean> cb_isGif = new MutableLiveData<>();
    public static MutableLiveData<Boolean> cb_compress = new MutableLiveData<>(true);

    public static MutableLiveData<Integer> getCb_compress_visible() {
        return cb_compress_visible;
    }

    public static void setCb_compress_visible(Integer visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_compress_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_compress_visible.setValue(View.GONE);
    }

    public static MutableLiveData<Integer> cb_compress_visible = new MutableLiveData<Integer>();


    public static MutableLiveData<Boolean> cb_preview_audio = new MutableLiveData<>();
    public static MutableLiveData<Integer> cb_preview_audio_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setCb_preview_audio_visible(Integer visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_preview_audio_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_preview_audio_visible.setValue(View.GONE);
    }

    public static MutableLiveData<Integer> tv_original_tips_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setTv_original_tips_visible(Boolean isVisible) {
        if (isVisible)
            PhotoActivityVM.tv_original_tips_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.tv_original_tips_visible.setValue(View.GONE);
    }

    //是否裁剪图片
    public static MutableLiveData<Boolean> cb_crop = new MutableLiveData<>();
    public static MutableLiveData<Integer> cb_crop_visible = new MutableLiveData<Integer>();

    public static MutableLiveData<Boolean> rgb_crop = new MutableLiveData<>(false);
    public static MutableLiveData<Integer> rgb_crop_visible = new MutableLiveData<Integer>();

    public static void setCb_crop_visible(Integer visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_crop_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_crop_visible.setValue(View.GONE);
    }

    public static void setRgb_crop_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.rgb_crop_visible.setValue(View.GONE);
        else PhotoActivityVM.rgb_crop_visible.setValue(View.VISIBLE);
    }

    //是否显示裁剪菜单栏
    public static MutableLiveData<Integer> cb_hide_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setCb_hide_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.cb_hide_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_hide_visible.setValue(View.GONE);
    }    //圆形头像裁剪模式

    public static MutableLiveData<Boolean> cb_crop_circular = new MutableLiveData<>();
    public static MutableLiveData<Integer> cb_crop_circular_visible = new MutableLiveData<Integer>();

    public static void setCb_crop_circular_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.cb_crop_circular_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_crop_circular_visible.setValue(View.GONE);
    }

    //裁剪框or图片拖动
    public static MutableLiveData<Integer> cb_styleCrop_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setCb_styleCrop_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.cb_styleCrop_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_styleCrop_visible.setValue(View.GONE);
    }  //是否显示裁剪边框

    public static MutableLiveData<Integer> cb_showCropFrame_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setCb_showCropFrame_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.cb_showCropFrame_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_showCropFrame_visible.setValue(View.GONE);
    } //裁剪框or图片拖动

    public static void setCb_showCropGrid(Boolean cb_showCropGrid) {
        PhotoActivityVM.cb_showCropGrid.setValue(cb_showCropGrid);
    }

    public static MutableLiveData<Boolean> cb_showCropGrid = new MutableLiveData<>(true);

    public static MutableLiveData<Boolean> getCb_showCropFrame() {
        return cb_showCropFrame;
    }

    public static void setCb_showCropFrame(Boolean cb_showCropFrame) {
        PhotoActivityVM.cb_showCropFrame.setValue(cb_showCropFrame);
    }

    public static MutableLiveData<Boolean> cb_showCropFrame = new MutableLiveData<>(false);
    public static MutableLiveData<Integer> cb_showCropGrid_visible = new MutableLiveData<Integer>(View.GONE);

    public static void setCb_showCropGrid_visible(boolean visible) {
        if (visible)
            PhotoActivityVM.cb_showCropGrid_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_showCropGrid_visible.setValue(View.GONE);
    }


    public static MutableLiveData<Boolean> getCb_preview_video() {
        return cb_preview_video;
    }

    public static void setCb_preview_video(Boolean cb_preview_video) {
        PhotoActivityVM.cb_preview_video.setValue(cb_preview_video);
    }

    public static MutableLiveData<Boolean> getCb_isGif() {
        return cb_isGif;
    }

    public static void setCb_isGif(Boolean cb_isGif) {
        PhotoActivityVM.cb_isGif.setValue(cb_isGif);
    }

    public static MutableLiveData<Integer> cb_gif_visible = new MutableLiveData<Integer>();

    public static void setCb_gif_visible(Integer visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_gif_visible.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_gif_visible.setValue(View.GONE);
    }

    public static MutableLiveData<Boolean> getCb_compress() {
        return cb_compress;
    }

    public static void setCb_compress(Boolean cb_compress) {
        PhotoActivityVM.cb_compress.setValue(cb_compress);
    }

    public static MutableLiveData<Boolean> getCb_preview_audio() {
        return cb_preview_audio;
    }

    public static void setCb_preview_audio(Boolean cb_preview_audio) {
        PhotoActivityVM.cb_preview_audio.setValue(cb_preview_audio);
    }


    public static MutableLiveData<Boolean> getCb_preview_img() {
        return cb_preview_img;
    }

    public static void setCb_preview_img(Boolean cb_preview_img) {
        PhotoActivityVM.cb_preview_img.setValue(cb_preview_img);
    }

    public static MutableLiveData<Boolean> getCb_single_back() {
        return cb_single_back;
    }

    public static void setCb_single_back(Boolean cb_single_back) {
        PhotoActivityVM.cb_single_back.setValue(cb_single_back);
    }

    //单选或多选
    public static MutableLiveData<Boolean> cb_single_back = new MutableLiveData<Boolean>(false);
    public static MutableLiveData<Integer> cb_single_back_visibility = new MutableLiveData<Integer>();

    public static void setCb_single_back_visible(Integer visible) {
        if (visible == View.VISIBLE)
            PhotoActivityVM.cb_single_back_visibility.setValue(View.VISIBLE);
        else PhotoActivityVM.cb_single_back_visibility.setValue(View.GONE);
    }


    public static MutableLiveData<String> getListAnimStr() {
        listAnimStr.setValue("列表动画：" + listAnimStr);
        return listAnimStr;
    }

    public static void setListAnimStr(String listAnimStr) {
        PhotoActivityVM.listAnimStr.setValue(listAnimStr);
        LogUtil.i("listAnimStr：", "listAnimStr：" + listAnimStr);
    }

    public static void setChooseModeStr(String chooseModeStr) {
        PhotoActivityVM.cbChooseModeStr.setValue(chooseModeStr);
        LogUtil.i("cbChooseModeStr：", "cbChooseModeStr：" + chooseModeStr);
    }

    public static MutableLiveData<String> getCustomCameraStr() {
        return customCameraStr;
    }


    public static MutableLiveData<String> customCameraStr = new MutableLiveData<>();


    public static MutableLiveData<String> getAnimation() {
        return animation;
    }

    public static void setAnimation(String animation) {
        PhotoActivityVM.animation.setValue(animation);
        LogUtil.i("animationMode：", "animationMode：" + animationMode);
    }


    public MutableLiveData<String> getText() {
        return text;
    }

    public void setText(MutableLiveData<String> text) {
        PhotoActivityVM.text = text;
    }


    public static void setSeperatePageStr(Boolean str) {
        PhotoActivityVM.seperatePageStr.setValue("是否开启分页：" + str);
    }

    public static void setEnableMaskStr(Boolean str) {
        PhotoActivityVM.enableMaskStr.setValue("是否开启蒙层：" + str);
    }


    public static void setCustomCameraStr(Boolean customCameraStr) {
        PhotoActivityVM.customCameraStr.setValue("是否使用自定义相机：" + customCameraStr);

    }

    public static void setAlbumOrTakeStr(Boolean customCameraStr) {
        PhotoActivityVM.albumOrTakeStr.setValue("是否使用自定义相机：" + customCameraStr);
    }


    public PhotoActivityVM(@NonNull Application application) {
        super(application);
    }

    public void setBinding(ActivityPhotoBinding binding, PhotoActivity mainActivity) {
        //把binding和mainActivity都赋值给MainVM作为静态变量备用，因为很多绑定的控件都只能用静态方法
        PhotoActivityVM.binding = binding;
        PhotoActivityVM.activity = mainActivity;
    }

    private int x = 0, y = 0;


    private int aspect_ratio_x, aspect_ratio_y;


    public static MutableLiveData<String> getXyStr() {
        return xyStr;
    }

    public static void setXyStr(int x, int y) {
        PhotoActivityVM.xyStr.setValue("x:" + x + ",y:" + y);
    }

    public static MutableLiveData<String> xyStr = new MutableLiveData<>();

    public static MutableLiveData<String> getAspect_ratioStr() {
        return aspect_ratioStr;
    }

    public static void setAspect_ratioStr(int aspect_ratio_x, int aspect_ratio_y) {
        PhotoActivityVM.aspect_ratioStr.setValue("x:" + aspect_ratio_x + ",y:" + aspect_ratio_y);
    }

    public static MutableLiveData<String> aspect_ratioStr = new MutableLiveData<>();
    //按钮的点击事件
    public RadioButton.OnCheckedChangeListener rbListener = new RadioButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.rb_photo_default_animation:
                    if (isChecked) {
                        setAnimation("默认启动动画");
                        mWindowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
                    }
                    break;
            }
        }};


        //按钮的点击事件
        public CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.rb_photo_default_animation:
                        if (isChecked) {
                            setAnimation("默认启动动画");
                            mWindowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
                        }

                        break;
                    case R.id.rb_photo_up_animation:
                        if (isChecked) {
                            setAnimation("相册上弹动画");
                            mWindowAnimationStyle = PictureWindowAnimationStyle.ofCustomWindowAnimationStyle(R.anim.picture_anim_up_in, R.anim.picture_anim_down_out);
                        }
                        break;
                    case R.id.cb_crop:
                        setRgb_crop_visible(isChecked);
                        setCb_hide_visible(isChecked);
                        setCb_crop_circular_visible(isChecked);
                        setCb_styleCrop_visible(isChecked);
                        setCb_showCropFrame_visible(isChecked);
                        setCb_showCropGrid_visible(isChecked);
                        break;
                    case R.id.cb_crop_circular:
                        if (isChecked) {
                            x = aspect_ratio_x;
                            y = aspect_ratio_y;
                            aspect_ratio_x = 1;
                            aspect_ratio_y = 1;
                        } else {
                            aspect_ratio_x = x;
                            aspect_ratio_y = y;
                        }
                        setXyStr(x, y);
                        setAspect_ratioStr(aspect_ratio_x, aspect_ratio_y);
                        setRgb_crop_visible(isChecked);
                        setCb_showCropFrame(!isChecked);
                        setCb_showCropGrid(!isChecked);
                        break;
                    case R.id.cb_original:
                        setOriginalStr(isChecked);
                        setTv_original_tips_visible(isChecked && cb_single_back.getValue());
                        break;

                    case R.id.cb_voice:
                        setClickVoiceStr(isChecked);
                        break;
                    case R.id.cb_custom_camera:
                        setCustomCameraStr(isChecked);
                        break;
                    case R.id.rb_default://默认动画
                        if (isChecked) {
                            animationMode = AnimationType.DEFAULT_ANIMATION;
                            setListAnimStr(animationMode + "");
                        }
                        break;
                    case R.id.rb_alpha://列表渐变动画
                        if (isChecked) {
                            animationMode = AnimationType.ALPHA_IN_ANIMATION;
                            setListAnimStr(animationMode + "");
                        }
                        break;
                    case R.id.rb_slide_in://列表底部滑入动画
                        if (isChecked) {
                            animationMode = AnimationType.SLIDE_IN_BOTTOM_ANIMATION;
                            setListAnimStr(animationMode + "");
                        }
                        break;

                    case R.id.rb_tw://繁体
                        if (isChecked) {
                            language = LanguageConfig.TRADITIONAL_CHINESE;
                        }
                        break;
                    case R.id.rb_us:
                        if (isChecked)
                            language = LanguageConfig.ENGLISH;
                        break;
                    case R.id.rb_ka:
                        if (isChecked)
                            language = LanguageConfig.KOREA;
                        break;
                    case R.id.rb_de:
                        if (isChecked)
                            language = LanguageConfig.GERMANY;
                        break;
                    case R.id.rb_fr:
                        if (isChecked)
                            language = LanguageConfig.FRANCE;
                        break;
                    case R.id.rb_spanish:
                        if (isChecked)
                            language = LanguageConfig.SPANISH;
                        break;
                    case R.id.rb_portugal:
                        if (isChecked)
                            language = LanguageConfig.PORTUGAL;
                        break;
                    case R.id.rb_crop_default:
                        if (isChecked) {
                            aspect_ratio_x = 0;
                            aspect_ratio_y = 0;
                        }
                        break;
                    case R.id.rb_crop_1to1:
                        if (isChecked) {
                            aspect_ratio_x = 1;
                            aspect_ratio_y = 1;
                        }
                        break;
                    case R.id.rb_crop_3to4:
                        if (isChecked) {
                            aspect_ratio_x = 3;
                            aspect_ratio_y = 4;
                        }
                        break;
                    case R.id.rb_crop_3to2:
                        if (isChecked) {
                            aspect_ratio_x = 3;
                            aspect_ratio_y = 2;
                        }
                        break;
                    case R.id.rb_crop_16to9:
                        if (isChecked) {
                            aspect_ratio_x = 16;
                            aspect_ratio_y = 9;
                        }
                        break;

                    case R.id.rb_default_style:
                        if (isChecked) {
                            setStyle("默认");
                            themeId = R.style.picture_default_style;
                            isWeChatStyle = false;
                            mPictureParameterStyle = getDefaultStyle();
                            mSelectorUIStyle = PictureSelectorUIStyle.ofDefaultStyle();
                        }
                        break;
                    case R.id.rb_white_style:
                        if (isChecked) {
                            setStyle("白色");
                            themeId = R.style.picture_white_style;
                            isWeChatStyle = false;
                            mPictureParameterStyle = getWhiteStyle();
                            mSelectorUIStyle = PictureSelectorUIStyle.ofSelectTotalStyle();
                        }
                        break;
                    case R.id.rb_num_style:
                        if (isChecked) {
                            setStyle("主题样式二");
                            themeId = R.style.picture_QQ_style;
                            isWeChatStyle = false;
                            mPictureParameterStyle = getNumStyle();
                            mSelectorUIStyle = PictureSelectorUIStyle.ofSelectNumberStyle();
                        }
                        break;
                    case R.id.rb_sina_style:
                        if (isChecked) {
                            setStyle("主题样式三");
                            themeId = R.style.picture_Sina_style;
                            isWeChatStyle = false;
                            mPictureParameterStyle = getSinaStyle();
                            mSelectorUIStyle = PictureSelectorUIStyle.ofSelectTotalStyle();
                        }
                        break;
                    case R.id.rb_we_chat_style:
                        if (isChecked) {
                            setStyle("主题样式四 (仿微信-全新风格)");
                            themeId = R.style.picture_WeChat_style;
                            isWeChatStyle = true;
                            mPictureParameterStyle = getWeChatStyle();
                            mSelectorUIStyle = PictureSelectorUIStyle.ofNewStyle();
                        }
                        break;

                    default:
                        break;
                }
            }
        };


        //按钮的点击事件
        public View.OnClickListener styleChoice = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {


                    case R.id.cbEnabledMask:
                        setEnableMask(((CheckBox) v).isChecked());
                        setEnableMaskStr(((CheckBox) v).isChecked());
                        break;
                    case R.id.cb_mode:
                        setAlbumOrTake(((CheckBox) v).isChecked());
                        setAlbumOrTakeStr(((CheckBox) v).isChecked());
                        break;
                    case R.id.cbPage:
                        setSeperatePage(((CheckBox) v).isChecked());
                        setSeperatePageStr(((CheckBox) v).isChecked());
                        break;

                    case R.id.cb_choose_mode:
                        setCb_single_back(((CheckBox) v).isChecked());
                        setCb_single_back_visible(getAlbumOrTake().getValue() ? View.GONE : View.VISIBLE);
                        setCb_single_back((!((CheckBox) v).isChecked() && getCb_single_back().getValue()));
                        break;
                    case R.id.rb_all:
                        chooseMode = PictureMimeType.ofAll();
                        setCb_preview_img(true);
                        setCb_preview_video(true);
                        setCb_isGif(true);
                        setCb_preview_img_visible(View.VISIBLE);
                        setCb_preview_video_visible(View.VISIBLE);
                        setCb_preview_video_visible(View.VISIBLE);
                        setCb_compress_visible(View.VISIBLE);
                        setCb_crop_visible(View.VISIBLE);
                        break;
                    case R.id.rb_image:
                        chooseMode = PictureMimeType.ofImage();
                        setCb_preview_img(true);
                        setCb_preview_video(false);
                        setCb_isGif(false);
                        setCb_preview_img_visible(View.VISIBLE);
                        setCb_preview_video_visible(View.GONE);
                        setCb_preview_audio_visible(View.GONE);
                        setCb_compress_visible(View.VISIBLE);
                        setCb_crop_visible(View.VISIBLE);
                        setCb_preview_img_visible(View.VISIBLE);
                        break;
                    case R.id.rb_video:
                        setCb_preview_img(false);
                        setCb_preview_video(true);
                        setCb_isGif(false);
                        setCb_preview_img_visible(View.GONE);
                        setCb_preview_video_visible(View.VISIBLE);
                        setCb_preview_audio_visible(View.GONE);
                        setCb_compress_visible(View.GONE);
                        setCb_crop_visible(View.GONE);
                        setCb_gif_visible(View.GONE);
                        break;
                    case R.id.rb_audio:
                        chooseMode = PictureMimeType.ofAudio();
                        setCb_preview_audio_visible(View.VISIBLE);
                        setCb_preview_img(false);
                        setCb_preview_video(false);
                        setCb_isGif(false);
                        setCb_preview_img_visible(View.GONE);
                        setCb_preview_video_visible(View.GONE);
                        setCb_compress_visible(View.GONE);
                        setCb_crop_visible(View.GONE);
                        setCb_gif_visible(View.GONE);

                        break;
                    default:
                        break;
                }

            }
        };


        private PictureParameterStyle getDefaultStyle() {
            // 相册主题
            PictureParameterStyle mPictureParameterStyle = new PictureParameterStyle();
            // 是否改变状态栏字体颜色(黑白切换)
            mPictureParameterStyle.isChangeStatusBarFontColor = false;
            // 是否开启右下角已完成(0/9)风格
            mPictureParameterStyle.isOpenCompletedNumStyle = false;
            // 是否开启类似QQ相册带数字选择风格
            mPictureParameterStyle.isOpenCheckNumStyle = false;
            // 相册状态栏背景色
            mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e");
            // 相册列表标题栏背景色
            mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e");
            // 相册父容器背景色
            mPictureParameterStyle.pictureContainerBackgroundColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 相册列表标题栏右侧上拉箭头
            mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_arrow_up;
            // 相册列表标题栏右侧下拉箭头
            mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_arrow_down;
            // 相册文件夹列表选中圆点
            mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
            // 相册返回箭头
            mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back;
            // 标题栏字体颜色
            mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
            mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 选择相册目录背景样式
            mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
            // 相册列表勾选图片样式
            mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
            // 相册列表底部背景色
            mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_grey);
            // 已选数量圆点背景样式
            mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
            // 相册列表底下预览文字色值(预览按钮可点击时的色值)
            mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
            mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册列表已完成色值(已完成 可点击色值)
            mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表未完成色值(请选择 不可点击色值)
            mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 预览界面底部背景色
            mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_grey);
            // 外部预览界面删除按钮样式
            mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;
            // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_wechat_checkbox;
            // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(activity, R.color.app_color_white);
            // 外部预览界面是否显示删除按钮
            mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
            // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
            mPictureParameterStyle.pictureNavBarColor = Color.parseColor("#393a3e");
            // 文件夹字体颜色
            mPictureParameterStyle.folderTextColor = Color.parseColor("#4d4d4d");
            // 文件夹字体大小
            mPictureParameterStyle.folderTextSize = 16;
//        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "";
//        // 自定义相册完成文本内容
//        mPictureParameterStyle.pictureCompleteText = "";
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//
//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;

            // 裁剪主题
            mCropParameterStyle = new PictureCropParameterStyle(
                    ContextCompat.getColor(activity, R.color.app_color_grey),
                    ContextCompat.getColor(activity, R.color.app_color_grey),
                    Color.parseColor("#393a3e"),
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    mPictureParameterStyle.isChangeStatusBarFontColor);

            return mPictureParameterStyle;
        }

        private PictureParameterStyle getWhiteStyle() {
            // 相册主题
            PictureParameterStyle mPictureParameterStyle = new PictureParameterStyle();
            // 是否改变状态栏字体颜色(黑白切换)
            mPictureParameterStyle.isChangeStatusBarFontColor = true;
            // 是否开启右下角已完成(0/9)风格
            mPictureParameterStyle.isOpenCompletedNumStyle = false;
            // 是否开启类似QQ相册带数字选择风格
            mPictureParameterStyle.isOpenCheckNumStyle = false;
            // 相册状态栏背景色
            mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#FFFFFF");
            // 相册列表标题栏背景色
            mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#FFFFFF");
            // 相册列表标题栏右侧上拉箭头
            mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_orange_arrow_up;
            // 相册列表标题栏右侧下拉箭头
            mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_orange_arrow_down;
            // 相册文件夹列表选中圆点
            mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
            // 相册返回箭头
            mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back_arrow;
            // 标题栏字体颜色
            mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
            mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 选择相册目录背景样式
            mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
            // 相册列表勾选图片样式
            mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
            // 相册列表底部背景色
            mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_fa);
            // 已选数量圆点背景样式
            mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
            // 相册列表底下预览文字色值(预览按钮可点击时的色值)
            mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
            mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_9b);
            // 相册列表已完成色值(已完成 可点击色值)
            mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表未完成色值(请选择 不可点击色值)
            mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_9b);
            // 预览界面底部背景色
            mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_checkbox;
            // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(activity, R.color.app_color_53575e);
            // 外部预览界面删除按钮样式
            mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_black_delete;
            // 外部预览界面是否显示删除按钮
            mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
//        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "";
//        // 自定义相册完成文本内容
//        mPictureParameterStyle.pictureCompleteText = "";
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";

//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;

            // 裁剪主题
            mCropParameterStyle = new PictureCropParameterStyle(
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    ContextCompat.getColor(activity, R.color.app_color_black),
                    mPictureParameterStyle.isChangeStatusBarFontColor);
            return mPictureParameterStyle;
        }

        private PictureParameterStyle getNumStyle() {
            // 相册主题
            PictureParameterStyle mPictureParameterStyle = new PictureParameterStyle();
            // 是否改变状态栏字体颜色(黑白切换)
            mPictureParameterStyle.isChangeStatusBarFontColor = false;
            // 是否开启右下角已完成(0/9)风格
            mPictureParameterStyle.isOpenCompletedNumStyle = false;
            // 是否开启类似QQ相册带数字选择风格
            mPictureParameterStyle.isOpenCheckNumStyle = true;
            // 相册状态栏背景色
            mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#7D7DFF");
            // 相册列表标题栏背景色
            mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#7D7DFF");
            // 相册列表标题栏右侧上拉箭头
            mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_arrow_up;
            // 相册列表标题栏右侧下拉箭头
            mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_arrow_down;
            // 相册文件夹列表选中圆点
            mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
            // 相册返回箭头
            mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back;
            // 标题栏字体颜色
            mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(activity, R.color.app_color_white);
            // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
            mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(activity, R.color.app_color_white);
            // 选择相册目录背景样式
            mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
            // 相册列表勾选图片样式
            mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_num_selector;
            // 相册列表底部背景色
            mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_fa);
            // 已选数量圆点背景样式
            mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval_blue;
            // 相册列表底下预览文字色值(预览按钮可点击时的色值)
            mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_blue);
            // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
            mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(activity, R.color.app_color_blue);
            // 相册列表已完成色值(已完成 可点击色值)
            mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(activity, R.color.app_color_blue);
            // 相册列表未完成色值(请选择 不可点击色值)
            mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(activity, R.color.app_color_blue);
            // 预览界面底部背景色
            mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_fa);
            // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_blue_checkbox;
            // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(activity, R.color.app_color_blue);
            // 外部预览界面删除按钮样式
            mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;
            // 外部预览界面是否显示删除按钮
            mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
//        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "";
//        // 自定义相册完成文本内容
//        mPictureParameterStyle.pictureCompleteText = "";
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";

//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;

            // 裁剪主题
            mCropParameterStyle = new PictureCropParameterStyle(
                    ContextCompat.getColor(activity, R.color.app_color_blue),
                    ContextCompat.getColor(activity, R.color.app_color_blue),
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    mPictureParameterStyle.isChangeStatusBarFontColor);
            return mPictureParameterStyle;
        }

        private PictureParameterStyle getSinaStyle() {
            // 相册主题
            PictureParameterStyle mPictureParameterStyle = new PictureParameterStyle();
            // 是否改变状态栏字体颜色(黑白切换)
            mPictureParameterStyle.isChangeStatusBarFontColor = true;
            // 是否开启右下角已完成(0/9)风格
            mPictureParameterStyle.isOpenCompletedNumStyle = true;
            // 是否开启类似QQ相册带数字选择风格
            mPictureParameterStyle.isOpenCheckNumStyle = false;
            // 相册状态栏背景色
            mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#FFFFFF");
            // 相册列表标题栏背景色
            mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#FFFFFF");
            // 相册列表标题栏右侧上拉箭头
            mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_orange_arrow_up;
            // 相册列表标题栏右侧下拉箭头
            mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_orange_arrow_down;
            // 相册文件夹列表选中圆点
            mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
            // 相册返回箭头
            mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_back_arrow;
            // 标题栏字体颜色
            mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
            mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 选择相册目录背景样式
            mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
            // 相册列表勾选图片样式
            mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
            // 相册列表底部背景色
            mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_fa);
            // 已选数量圆点背景样式
            mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
            // 相册列表底下预览文字色值(预览按钮可点击时的色值)
            mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
            mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_9b);
            // 相册列表已完成色值(已完成 可点击色值)
            mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_fa632d);
            // 相册列表未完成色值(请选择 不可点击色值)
            mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_9b);
            // 预览界面底部背景色
            mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_fa);
            // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_checkbox;
            // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(activity, R.color.app_color_53575e);
            // 外部预览界面删除按钮样式
            mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_black_delete;
            // 外部预览界面是否显示删除按钮
            mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
//        // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureRightDefaultText = "";
            // 完成文案是否采用(%1$d/%2$d)的字符串，只允许俩个占位符哟
//        mPictureParameterStyle.isCompleteReplaceNum = true;
//        // 自定义相册未完成文本内容
//        mPictureParameterStyle.pictureUnCompleteText = "请选择";
            // 自定义相册完成文本内容，已经支持两个占位符String 但isCompleteReplaceNum必须为true
//        mPictureParameterStyle.pictureCompleteText = getString(R.string.app_wechat_send_num);
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";

//        // 自定义相册标题字体大小
//        mPictureParameterStyle.pictureTitleTextSize = 18;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 14;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 14;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 14;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 14;
            // 裁剪主题
            mCropParameterStyle = new PictureCropParameterStyle(
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    ContextCompat.getColor(activity, R.color.app_color_black),
                    mPictureParameterStyle.isChangeStatusBarFontColor);
            return mPictureParameterStyle;
        }


        private PictureParameterStyle getWeChatStyle() {
            // 相册主题
            PictureParameterStyle mPictureParameterStyle = new PictureParameterStyle();
            // 是否改变状态栏字体颜色(黑白切换)
            mPictureParameterStyle.isChangeStatusBarFontColor = false;
            // 是否开启右下角已完成(0/9)风格
            mPictureParameterStyle.isOpenCompletedNumStyle = false;
            // 是否开启类似QQ相册带数字选择风格
            mPictureParameterStyle.isOpenCheckNumStyle = true;
            // 状态栏背景色
            mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#393a3e");
            // 相册列表标题栏背景色
            mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#393a3e");
            // 相册父容器背景色
            mPictureParameterStyle.pictureContainerBackgroundColor = ContextCompat.getColor(activity, R.color.app_color_black);
            // 相册列表标题栏右侧上拉箭头
            mPictureParameterStyle.pictureTitleUpResId = R.drawable.picture_icon_wechat_up;
            // 相册列表标题栏右侧下拉箭头
            mPictureParameterStyle.pictureTitleDownResId = R.drawable.picture_icon_wechat_down;
            // 相册文件夹列表选中圆点
            mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
            // 相册返回箭头
            mPictureParameterStyle.pictureLeftBackIcon = R.drawable.picture_icon_close;
            // 标题栏字体颜色
            mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册右侧按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
            mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(activity, R.color.picture_color_53575e);
            // 相册右侧按钮字体默认颜色
            mPictureParameterStyle.pictureRightDefaultTextColor = ContextCompat.getColor(activity, R.color.picture_color_53575e);
            // 相册右侧按可点击字体颜色,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureRightSelectedTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册右侧按钮背景样式,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureUnCompleteBackgroundStyle = R.drawable.picture_send_button_default_bg;
            // 相册右侧按钮可点击背景样式,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureCompleteBackgroundStyle = R.drawable.picture_send_button_bg;
            // 选择相册目录背景样式
            mPictureParameterStyle.pictureAlbumStyle = R.drawable.picture_new_item_select_bg;
            // 相册列表勾选图片样式
            mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_wechat_num_selector;
            // 相册标题背景样式 ,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureWeChatTitleBackgroundStyle = R.drawable.picture_album_bg;
            // 微信样式 预览右下角样式 ,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureWeChatChooseStyle = R.drawable.picture_wechat_select_cb;
            // 相册返回箭头 ,只针对isWeChatStyle 为true时有效果
            mPictureParameterStyle.pictureWeChatLeftBackStyle = R.drawable.picture_icon_back;
            // 相册列表底部背景色
            mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_grey);
            // 已选数量圆点背景样式
            mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
            // 相册列表底下预览文字色值(预览按钮可点击时的色值)
            mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
            mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(activity, R.color.picture_color_9b);
            // 相册列表已完成色值(已完成 可点击色值)
            mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_white);
            // 相册列表未完成色值(请选择 不可点击色值)
            mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(activity, R.color.picture_color_53575e);
            // 预览界面底部背景色
            mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(activity, R.color.picture_color_half_grey);
            // 外部预览界面删除按钮样式
            mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_delete;
            // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_wechat_checkbox;
            // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
            mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(activity, R.color.app_color_white);
            // 外部预览界面是否显示删除按钮
            mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
            // 设置NavBar Color SDK Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP有效
            mPictureParameterStyle.pictureNavBarColor = Color.parseColor("#393a3e");
            // 标题栏高度
            mPictureParameterStyle.pictureTitleBarHeight = ScreenUtils.dip2px(activity, 48);
            // 标题栏右侧按钮方向箭头left Padding
            mPictureParameterStyle.pictureTitleRightArrowLeftPadding = ScreenUtils.dip2px(activity, 3);

            // 完成文案是否采用(%1$d/%2$d)的字符串，只允许两个占位符哟
//        mPictureParameterStyle.isCompleteReplaceNum = true;
            // 自定义相册右侧文本内容设置
//        mPictureParameterStyle.pictureUnCompleteText = getString(R.string.app_wechat_send);
            //自定义相册右侧已选中时文案 支持占位符String 但只支持两个 必须isCompleteReplaceNum为true
//        mPictureParameterStyle.pictureCompleteText = getString(R.string.app_wechat_send_num);
//        // 自定义相册列表不可预览文字
//        mPictureParameterStyle.pictureUnPreviewText = "";
//        // 自定义相册列表预览文字
//        mPictureParameterStyle.picturePreviewText = "";
//        // 自定义预览页右下角选择文字文案
//        mPictureParameterStyle.pictureWeChatPreviewSelectedText = "";

//        // 自定义相册标题文字大小
//        mPictureParameterStyle.pictureTitleTextSize = 9;
//        // 自定义相册右侧文字大小
//        mPictureParameterStyle.pictureRightTextSize = 9;
//        // 自定义相册预览文字大小
//        mPictureParameterStyle.picturePreviewTextSize = 9;
//        // 自定义相册完成文字大小
//        mPictureParameterStyle.pictureCompleteTextSize = 9;
//        // 自定义原图文字大小
//        mPictureParameterStyle.pictureOriginalTextSize = 9;
//        // 自定义预览页右下角选择文字大小
//        mPictureParameterStyle.pictureWeChatPreviewSelectedTextSize = 9;

            // 裁剪主题
            mCropParameterStyle = new PictureCropParameterStyle(
                    ContextCompat.getColor(activity, R.color.app_color_grey),
                    ContextCompat.getColor(activity, R.color.app_color_grey),
                    Color.parseColor("#393a3e"),
                    ContextCompat.getColor(activity, R.color.app_color_white),
                    mPictureParameterStyle.isChangeStatusBarFontColor);

            return mPictureParameterStyle;
        }


    }
