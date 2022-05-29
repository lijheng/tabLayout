# tabLayout
可以自定义指示器样式的TabLayout
可用属性：
```
<declare-styleable name="ExtendTabLayout">
    <attr name="extendTabIndicatorHeight" format="dimension" /> <!-- 指示器高度-->
    <attr name="extendTabIndicatorMarginStart" format="dimension" /> <!-- 指示器距离文本左边距-->
    <attr name="extendTabIndicatorMarginEnd" format="dimension" /> <!-- 指示器距离文本右边距-->
    <attr name="extendTabIndicatorSelectColor" format="color|reference" /> <!-- 颜色-->
    <attr name="extendTabTextSize" format="dimension" />  <!-- 文本字体大小-->
    <attr name="extendTabIndicatorDrawable" format="reference" />  <!-- 指示器drwable（设置此项后 extendTabIndicatorSelectColor无效）-->
    <attr name="extendTabSelectTextSize" format="dimension" />  <!--选中文本字体大小(可以不设置，不设置选中文本字体大小不变)-->
       <attr name="extendTabSelectTextStyle" format="enum"> <!--选中字体样式-->
           <enum name="bold" value="1" />
           <enum name="italic" value="2" />
           <enum name="normal" value="0" />
       </attr>
       <attr name="extendTabTextStyle" format="enum"> <!--字体样式-->
           <enum name="bold" value="1" />
           <enum name="italic" value="2" />
           <enum name="normal" value="0" />
       </attr>
</declare-styleable>
```
[https://www.jianshu.com/p/03760526b609](https://www.jianshu.com/p/1cca81465b76)
