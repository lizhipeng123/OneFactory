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

#保留support下的所有类以及内部类
-keep class android.support.**{*;}

#保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

#保留r下面的资源
-keep class **.R$*{*;}

-libraryjars libs/android-support-v4.jar
-libraryjars libs/AMap_Location_V3.3.0_20170118.jar
-libraryjars libs/AMap_Search_V5.0.0_20170309.jar
-libraryjars libs/Android_Map3D_SDK_V5.0.0_20170311.jar

-dontwarn android.support.v4.**
-dontwarn com.amap.api.**
-dontwarn com.autonavi.**

-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep class com.amap.api.** {*;}
-keep class com.autonavi.** {*;}
-keepclass com.amap.api.mapcore.**{*;}
-keepclass com.amap.api.maps.**{*;}
-keepclass com.autonavi.amap.mapcore.*{*;}
#定位
-keepclass com.amap.api.location.**{*;}
-keepclass com.loc.**{*;}
-keepclass com.amap.api.fence.**{*;}
-keepclass com.autonavi.aps.amapapi.model.**{*;}
#搜索
-keepclass com.amap.api.services.**{*;}


