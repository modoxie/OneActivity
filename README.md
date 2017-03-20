[![](https://jitpack.io/v/modoxie/OneActivity.svg)](https://jitpack.io/#modoxie/OneActivity)
# OneActivity
尽量少的使用Activity构建整个项目，理想情况下就一个Activity，使界面转换更流畅。减少Activity被系统杀死后重建出现的各种bug。方便快捷的实现各种炫酷的转场动画。

1 创建一个布局文件并创建一个类继承自UIview，重写onCreateView创建布局。

    @Override
    public View onCreateView(UIControl uiControl) {
        return uiControl.getLayoutInflater().inflate(R.layout.activity_main, uiControl.getRoot(), false);
    }
    
  两个重构方法。
  
    public TestUI(){
    }
    
    public TestUI(String name){
        super(name);
    }
    
  重写onViewCreate开始生命周期。onViewCreate->onShow->[(onRestart)->onStart->onResume->onPause->onStop]->onHint->onDestroy
    
    @Override
    public void onViewCreate() {
        super.onViewCreate();
        this.<TextView>find(R.id.sample_text).setText(getName()+Math.random());
        this.find(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtil.show(new TestUI2());
            }
        });
    }
    
2 创建一个Activity 继承自AContext，重写Oncreate方法 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            UIUtil.show(new TestUI1());
        }
    } 
