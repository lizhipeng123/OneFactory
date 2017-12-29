# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\androidstudio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-dontwarn net.sf.json.**
-dontwarn org.apache.**
-dontwarn butterknife.internal.**
-dontwarn com.squareup.picasso.**
-dontwarn com.youku.player.**
-dontwarn nl.siegmann.epublib.utilities.**
-dontwarn okio.Okio.**
-dontwarn okio.Segment.**
-dontwarn com.autonavi.**
-dontwarn com.handmark.pulltorefresh.library.**
-dontwarn com.i5tong.epubreaderlib.view.pulltorefresh.**
-dontwarn nl.siegmann.epublib.domain.**
-dontwarn org.joda.time.**
-dontwarn com.amap.apis.utils.**
-dontwarn nl.siegmann.epublib.epub.**
-dontwarn okio.**
-keep class com.daoran.newfactory.onefactory.**
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }