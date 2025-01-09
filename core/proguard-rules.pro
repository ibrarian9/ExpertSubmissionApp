-dontwarn java.lang.invoke.StringConcatFactory

-keepclassmembers class com.app.expertsubmissionapp.core.** { *; }
-keep class com.app.expertsubmissionapp.core.** { *; }

-keepattributes Signature

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