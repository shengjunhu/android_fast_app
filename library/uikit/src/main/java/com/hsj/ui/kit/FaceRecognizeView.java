import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.angs.arcface.R;
import com.angs.common.thread.ThreadManager;
import com.angs.common.utils.ScreenUtils;

/**
 * @Author:hsj
 * @Date:2019-07-30 16:31
 * @Class:FaceRecognizeView
 * @Desc:识别FaceUI
 */
public final class FaceRecognizeView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "FaceRecognizeView";
    private final Object mSync = new Object();
    /**
     * 默认中间圆的半径从0开始
     */
    private float currentRadius = 0;
    /**
     * 控件的宽度（默认）
     */
    private int mViewWidth = 400;
    /**
     * 控件高度
     */
    private int mViewHeight = 400;
    /**
     * 中心圆屏幕边距
     */
    private int margin;
    /**
     * 内圆半径
     */
    private float mRadius;
    /**
     * 绘制白色背景
     */
    private int bgWhiteColor;
    /**
     * 背景弧宽度
     */
    private float mBgArcWidth;
    /**
     * 圆心点坐标
     */
    private Point mCenterPoint = new Point();
    /**
     * 圆弧边界
     */
    private RectF mBgRectF = new RectF();
    /**
     * 圆弧背景画笔
     */
    private Paint mBgArcPaint;
    /**
     * 是否显示
     */
    private volatile boolean isPlay = false;

    /**
     * 自定View空构造方法
     *
     * @param context
     */
    public FaceRecognizeView(Context context) {
        this(context, null);
    }

    public FaceRecognizeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceRecognizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化控件View
     */
    private void initView(Context context) {
        //设置透明背景
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        //添加回调
        getHolder().addCallback(this);
        //显示顶层
        setZOrderOnTop(true);
        //防止遮住控件
        //setZOrderMediaOverlay(true);
        //屏蔽界面焦点
        setFocusable(true);
        //保持屏幕长亮
        //setKeepScreenOn(true);

        //初始化值
        margin = (int) context.getResources().getDimension(R.dimen.dp100);
        mBgArcWidth = (int) context.getResources().getDimension(R.dimen.dp5);
        bgWhiteColor = ContextCompat.getColor(context, R.color.BgWhiteColor);

        // 圆弧背景
        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(true);//消除锯齿
        mBgArcPaint.setColor(ContextCompat.getColor(context, R.color.circleBg));
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);

        //开启线程
        start();
    }

    /**
     * 开启线程绘制
     */
    public void start() {
        synchronized (mSync) {
            if (!isPlay) {
                isPlay = true;
                ThreadManager.executeOnSubThread(this);
            }
        }
    }

    /**
     * 停止绘制
     */
    public void stop() {
        synchronized (mSync) {
            if (isPlay) {
                isPlay = false;
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量view的宽度
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        }
        //测量view的高度
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(mViewWidth, mViewHeight);

        //获取圆的相关参数
        mCenterPoint.x = mViewWidth / 2;
        //空间总高度
        mCenterPoint.y = mViewHeight / 2;

        //外环圆的半径
        mRadius = mCenterPoint.x - margin;

        //绘制背景圆弧的边界
        mBgRectF.left = mCenterPoint.x - mRadius - mBgArcWidth / 2;
        mBgRectF.top = mCenterPoint.y - mRadius - mBgArcWidth / 2;
        mBgRectF.right = mCenterPoint.x + mRadius + mBgArcWidth / 2;
        mBgRectF.bottom = mCenterPoint.y + mRadius + mBgArcWidth / 2;
    }

    /**
     * 循环绘制画面内容
     */
    @Override
    public void run() {
        while (isPlay) {
            try {
                drawView();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 画控件
     */
    private void drawView() {
        synchronized (mSync) {
            Canvas canvas = null;
            SurfaceHolder holder = null;
            try {
                holder = getHolder();
                if (holder == null || !holder.getSurface().isValid()) return;
                //获得canvas对象
                canvas = holder.lockCanvas();
                if (canvas == null) return;
                //清除画布上面里面的内容
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                //绘制画布内容
                drawContent(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (holder != null && canvas != null) {
                    //释放canvas锁，并且显示视图
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 画内容
     *
     * @param canvas
     */
    private void drawContent(Canvas canvas) {
        //防止save()和restore()方法代码之后对Canvas执行的操作，继续对后续的绘制会产生影响
        canvas.save();
        //绘制人脸识别部分
        drawFaceCircle(canvas);
        //画外边进度条
        drawRoundProgress(canvas);
        //重置
        canvas.restore();
    }

    /**
     * 画圆形
     *
     * @param canvas
     */
    private void drawFaceCircle(Canvas canvas) {
        // 圆形，放大效果
        currentRadius += 20;
        if (currentRadius > mRadius) {
            currentRadius = mRadius;
        }
        //设置画板样式
        Path path = new Path();
        //以（400,200）为圆心，半径为100绘制圆 指创建顺时针方向的矩形路径
        path.addCircle(mCenterPoint.x, mCenterPoint.y, currentRadius, Path.Direction.CW);
        // 是A形状中不同于B的部分显示出来
        canvas.clipPath(path, Region.Op.DIFFERENCE);
        // 半透明背景效果
        canvas.clipRect(0, 0, mViewWidth, mViewHeight);
        //绘制背景颜色
        canvas.drawColor(bgWhiteColor);
    }

    /**
     * 绘制人脸识别界面进度条
     *
     * @param canvas canvas
     */
    private void drawRoundProgress(Canvas canvas) {
        // 逆时针旋转(-90)
        canvas.rotate(0, mCenterPoint.x, mCenterPoint.y);
        // 设置圆环背景(-90)
        canvas.drawArc(mBgRectF, 0, 360, false, mBgArcPaint);
    }

}
