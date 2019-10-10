import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.angs.arcface.R;
import com.angs.arcface.core.WorkStatus;
import com.angs.common.thread.ThreadManager;

/**
 * @Author:hsj
 * @Date:2019-07-30 16:31
 * @Class:FaceSurfaceView
 * @Desc:人脸信息注册圆圈动画
 */
public final class FaceRegisterView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "FaceRegisterView";
    private final Object mSync = new Object();
    private Context context;
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
     * 透明圆圈偏背景中心点向上的距离
     */
    private int circleMarginTop;
    /**
     * 绘制白色背景
     */
    private int bgWhiteColor;
    /**
     * 中心圆屏幕边距
     */
    private int margin;
    /**
     * 圆圈画笔
     */
    private Paint mPaint;
    /**
     * 提示文本
     */
    private String mTipText;
    /**
     * 提示文本颜色
     */
    private int mTipTextColor;
    /**
     * 提示文本颜色
     */
    private int mTipTextSize;
    /**
     * 内圆半径
     */
    private int mRadius;
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
     * 提示语画笔
     */
    private Paint mTextPaint;
    /**
     * 圆弧画笔
     */
    private Paint mArcPaint;
    /**
     * 设置默认转动角度0
     */
    private float currentAngle = 0;


    /**
     * 刻度线条数
     */
    private static final int mDottedLineCount = 120;
    /**
     * 2Π/刻度数
     */
    private float unit;
    /**
     * 线条宽度
     */
    private float mDottedLineWidth = 40f;
    /**
     * 虚线的内部半径
     */
    private float mInsideDottedLineRadius;
    /**
     * 虚线的外部半径
     */
    private float mExternalDottedLineRadius;
    /**
     * 长虚线的外部半径
     */
    private float mExternalDottedLineRadius2;


    /**
     * 脸部所有状态
     */
    private volatile SparseArray<String> faceStatus;
    /**
     * 当前所处的状态
     */
    private volatile @WorkStatus int currentStatus;
    /**
     * 是否显示
     */
    private volatile boolean isPlay = false;

    /**
     * 构造
     *
     * @param context
     */
    public FaceRegisterView(Context context) {
        this(context, null);
    }

    public FaceRegisterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceRegisterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取xml里面的属性值
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FaceRegisterView);
        mTipText = array.getString(R.styleable.FaceRegisterView_tip_text);
        mTipTextColor = array.getColor(R.styleable.FaceRegisterView_tip_text_color, Color.WHITE);
        mTipTextSize = array.getDimensionPixelSize(R.styleable.FaceRegisterView_tip_text_size, (int) context.getResources().getDimension(R.dimen.sp16));
        array.recycle();

        initView(context);
    }

    /**
     * 初始化控件View
     */
    private void initView(Context context) {
        this.context = context;
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
        circleMarginTop = (int) context.getResources().getDimension(R.dimen.dp50);
        bgWhiteColor = ContextCompat.getColor(context, R.color.BgWhiteColor);
        mBgArcWidth = (int) context.getResources().getDimension(R.dimen.dp2);
        mDottedLineWidth = (int) context.getResources().getDimension(R.dimen.dp12);
        // 2pi/120
        unit = (float) (2.0f * Math.PI / mDottedLineCount);

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setColor(ContextCompat.getColor(context, R.color.textBgColor));
        mPaint.setStyle(Paint.Style.FILL);

        //绘制文字画笔
        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(8);
        mTextPaint.setColor(mTipTextColor);
        mTextPaint.setTextSize(mTipTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        // 圆弧背景
        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(true);//消除锯齿
        mBgArcPaint.setColor(ContextCompat.getColor(context, R.color.circleBg2));
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);

        // 圆弧
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);//消除锯齿
        mArcPaint.setColor(ContextCompat.getColor(context, R.color.pbEndColor));
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mBgArcWidth);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        //设置提示文字内容
        initFaceStatus();

        //开启线程
        start();
    }

    /**
     * 点重新开始
     */
    public void initFaceStatus() {
        synchronized (mSync) {
            if (faceStatus == null) {
                faceStatus = new SparseArray<>(9);
            }
            faceStatus.clear();
            faceStatus.put(WorkStatus.FACE_SEARCH, WorkStatus.FACE_HINT_SEARCH);
            faceStatus.put(WorkStatus.FACE_REGISTER_READY, WorkStatus.FACE_HINT_REGISTER);
            faceStatus.put(WorkStatus.FACE_REGISTER_START, WorkStatus.FACE_HINT_FRONT);
            faceStatus.put(WorkStatus.FACE_REGISTERED, WorkStatus.FACE_HINT_REGISTERED);

            faceStatus.put(WorkStatus.FACE_ANGLE_START, WorkStatus.FACE_HINT_UP);
            faceStatus.put(WorkStatus.FACE_ANGLE_UP, WorkStatus.FACE_HINT_RIGHT);
            faceStatus.put(WorkStatus.FACE_ANGLE_RIGHT, WorkStatus.FACE_HINT_DOWN);
            faceStatus.put(WorkStatus.FACE_ANGLE_DOWN, WorkStatus.FACE_HINT_LEFT);
            faceStatus.put(WorkStatus.FACE_ANGLE_LEFT, WorkStatus.FACE_HINT_END);
        }
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

    /**
     * 更新文字状态
     * 更新角度状态
     *
     * @param status
     */
    public void updateStatus(@WorkStatus int status) {
        synchronized (mSync) {
            switch (status) {
                case WorkStatus.FACE_SEARCH:
                case WorkStatus.FACE_REGISTER_READY:
                case WorkStatus.FACE_REGISTER_START:
                case WorkStatus.FACE_REGISTERED:
                case WorkStatus.FACE_ANGLE_START:
                    FaceRegisterView.this.mTipText = faceStatus.get(status);
                    break;

                case WorkStatus.FACE_ANGLE_UP:
                case WorkStatus.FACE_ANGLE_RIGHT:
                case WorkStatus.FACE_ANGLE_DOWN:
                case WorkStatus.FACE_ANGLE_LEFT:
                    postDelayed(() -> FaceRegisterView.this.mTipText = faceStatus.get(status), 60);
                    this.currentStatus = status;
                    break;
                default:
                    break;
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
        // 测量view的宽度(此控件为覆盖控件，宽高为显示内容宽高)
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        }
        // 测量view的高度(此控件为覆盖控件，宽高为显示内容宽高)
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(mViewWidth, mViewHeight);

        // 获取圆的相关参数(圆的中点坐标,半径)
        mCenterPoint.x = mViewWidth / 2;
        mCenterPoint.y = (mViewHeight - circleMarginTop) / 2;
        mRadius = mCenterPoint.x - margin;

        // 绘制背景圆弧的边界
        mBgRectF.left = mCenterPoint.x - mRadius - mBgArcWidth / 2;
        mBgRectF.right = mCenterPoint.x + mRadius + mBgArcWidth / 2;
        mBgRectF.top = mCenterPoint.y - mRadius - mBgArcWidth / 2;
        mBgRectF.bottom = mCenterPoint.y + mRadius + mBgArcWidth / 2;

        // 虚线的外部半径(20圆弧跟虚线之间的距离)
        mExternalDottedLineRadius = (int) (mBgRectF.width() / 2) + 20;
        // 虚线的内部半径
        mInsideDottedLineRadius = mExternalDottedLineRadius - mDottedLineWidth;
        // 长虚线外部半径
        mExternalDottedLineRadius2 = mExternalDottedLineRadius + context.getResources().getDimension(R.dimen.dp8);
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
        //先画提示语
        drawHintText(canvas);
        //绘制人脸识别部分
        drawFaceCircle(canvas);
        //画外边进度条
        drawRoundProgress(canvas);
        //绘制
        canvas.restore();
    }

    /**
     * 绘制人脸识别提示
     *
     * @param canvas canvas
     */
    private void drawHintText(Canvas canvas) {
        //圆视图宽度 （屏幕减去两边距离）
        int cameraWidth = mViewWidth - 2 * margin;
        //x轴起点（文字背景起点）
        int x = margin;
        //y轴起点
        int y = mCenterPoint.y - mRadius;
        //提示框背景高度
        int height = cameraWidth / 4;
        Rect rect = new Rect(x, y, x + cameraWidth, y + height);
        canvas.drawRect(rect, mPaint);

        //计算baseline
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 4;
        float baseline = rect.centerY() + distance;
        canvas.drawText(mTipText, rect.centerX(), baseline, mTextPaint);
    }

    /**
     * 画Camera内容透明圆
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
     * 绘制正脸状态：背景刻度环+完成进度的刻度环
     *
     * @param canvas canvas
     */
    private void drawRoundProgress(Canvas canvas) {
        // 正值顺时针，负值逆时针(从270度开始选择)
        canvas.rotate(-45, mCenterPoint.x, mCenterPoint.y);
        for (int i = 0; i < mDottedLineCount; i++) {
            float degrees = i * unit;
            float startX = mCenterPoint.x + (float) Math.sin(degrees) * mInsideDottedLineRadius;
            float startY = mCenterPoint.y - (float) Math.cos(degrees) * mInsideDottedLineRadius;
            float stopX = mCenterPoint.x + (float) Math.sin(degrees) * mExternalDottedLineRadius;
            float stopY = mCenterPoint.y - (float) Math.cos(degrees) * mExternalDottedLineRadius;
            //画背景刻度环(一次画完)
            canvas.drawLine(startX, startY, stopX, stopY, mBgArcPaint);
        }

        if (currentStatus >= WorkStatus.FACE_ANGLE_UP && currentStatus <= WorkStatus.FACE_ANGLE_LEFT) {//更新刻度
            //画当前进度刻度环(120个刻度，每90度30个刻度，每刻度/3°)
            currentAngle += 3;
            if (currentAngle >= (currentStatus % 10) * 30) {
                currentAngle = (currentStatus % 10) * 30;
            }
            for (int i = 0; i < currentAngle; i++) {
                float degrees = i * unit;
                //起点坐标(环内起点)
                float startX = mCenterPoint.x + (float) Math.sin(degrees) * mInsideDottedLineRadius;
                float startY = mCenterPoint.y - (float) Math.cos(degrees) * mInsideDottedLineRadius;
                //终点坐标(环外起点)
                float stopX = mCenterPoint.x + (float) Math.sin(degrees) * mExternalDottedLineRadius2;
                float stopY = mCenterPoint.y - (float) Math.cos(degrees) * mExternalDottedLineRadius2;
                canvas.drawLine(startX, startY, stopX, stopY, mArcPaint);
            }
        }
    }

}
