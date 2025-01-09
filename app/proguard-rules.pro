-dontwarn java.lang.invoke.StringConcatFactory

-keep class com.app.expertsubmissionapp.** { *; }
-keepclassmembers class com.app.expertsubmissionapp.** { *; }

-keep class com.app.expertsubmissionapp.core.adapter.ProductsAdapter { *; }
-keep class com.app.expertsubmissionapp.databinding.** { *; }
-keep class com.app.expertsubmissionapp.presentation.** { *; }

-keepattributes Signature

# Keep attributes used by Android framework
-keepattributes *Annotation*
-keepattributes InnerClasses,EnclosingMethod

-keepclassmembers class com.app.expertsubmissionapp.core.data.Resource$* {
    <init>(...);
    java.lang.Object getData();
}

-keepclassmembers class ** {
    *** get*(...);
    *** set*(...);
}

-keepclassmembers class ** {
    public <init>(...);
}

-keep class com.google.gson.** { *; }

-keep class androidx.fragment.app.FragmentContainerView { *; }
-keep public class * extends androidx.fragment.app.Fragment { *; }
-keep class com.app.expertsubmissionapp.presentation.**Fragment { *; }

# Keep NavHostFragment
-keepnames class androidx.navigation.fragment.NavHostFragment
-keepnames class * extends androidx.navigation.Navigator

# Keep ViewBinding
-keep class * extends androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(...);
}

# Keep your HomeFragment and its constructor
-keepclassmembers class com.app.expertsubmissionapp.presentation.home.HomeFragment {
    <init>();
}

# Keep all classes in your app package
-keep class com.app.expertsubmissionapp.** { *; }
-keep class com.app.expertsubmissionapp.core.** { *; }

# Keep Dynamic Feature Module Components
-keep class androidx.navigation.dynamicfeatures.fragment.DynamicFragmentNavigator { *; }
-keep class androidx.navigation.dynamicfeatures.DynamicGraphNavigator { *; }

# Keep Kotlin Lazy class
-keep class kotlin.LazyKt { *; }
-keep class kotlin.Lazy { *; }

# Keep Kotlin metadata
-keepattributes *Annotation*, InnerClasses
-dontwarn kotlin.**
-keep class kotlin.Metadata { *; }

# If you're using Kotlin Reflection
-keep class kotlin.reflect.** { *; }

# Navigation Component rules
-keepnames class * extends androidx.fragment.app.Fragment
-keep class * extends androidx.fragment.app.Fragment { *; }
-keep class androidx.navigation.fragment.NavHostFragment { *; }

# Keep your FavoriteFragment
-keep class com.app.expertsubmissionapp.** { *; }