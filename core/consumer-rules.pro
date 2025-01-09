###---------------Begin: General Configuration for App ----------------##
#
## Keep all classes in the specified package and its sub-packages
#-keep class com.app.expertsubmissionapp.** { *; }
#-keep class com.app.expertsubmissionapp.core.** { *; }
#
## Keep all Fragment classes
#-keep public class * extends androidx.fragment.app.Fragment { *; }
#-keep class com.app.expertsubmissionapp.presentation.**Fragment { *; }
#
#
## Avoid leaving Data object members null
#-keepclassmembers,allowobfuscation class * {
#    @com.google.gson.annotations.SerializedName <fields>;
#}
#
###---------------Begin: SQLCipher Configuration ----------------##
#-keep,includedescriptorclasses class net.sqlcipher.** { *; }
#-keep,includedescriptorclasses interface net.sqlcipher.** { *; }
#
###---------------Begin: Koin Configuration ----------------##
#-keep class org.koin.** { *; }
#
#-keep class com.app.expertsubmissionapp.core.di.** { *; }
#
#-dontwarn com.app.expertsubmissionapp.core.**
#
###---------------Begin: Gson Configuration ----------------##
## Preserve generic type information for Gson
#-keepattributes Signature
## Keep fields annotated with Gson annotations
#-keepattributes *Annotation*
## Prevent stripping interface info from TypeAdapter and related classes
#-keep class * extends com.google.gson.TypeAdapter
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
###---------------Begin: Retrofit Configuration ----------------##
## Retain method and parameter annotations for Retrofit
#-keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
## Keep Retrofit interfaces
#-keep,allowobfuscation interface retrofit2.http.* { *; }
#
#
## Retain service method parameters when optimizing.
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#@retrofit2.http.* <methods>;
#}
#
## Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#
## Ignore JSR 305 annotations for embedding nullability information.
#-dontwarn javax.annotation.**
#
## Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
#-dontwarn kotlin.Unit
#
## Top-level functions that can only be used by Kotlin.
#-dontwarn retrofit2.KotlinExtensions
#-dontwarn retrofit2.KotlinExtensions$*
#
## Suppress warnings for Retrofit and related classes
#-dontwarn retrofit2.**
#-dontwarn javax.annotation.**
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
#-dontwarn kotlin.**
#-dontwarn kotlinx.**
#
###---------------Begin: AndroidX and Material Configuration ----------------##
#-keep class com.google.android.material.** { *; }
#-keep class androidx.coordinatorlayout.widget.** { *; }
#-keep class androidx.viewbinding.** { *; }
#
###---------------App Module ----------------##
#-dontwarn com.app.expertsubmissionapp.core.di.AppModuleKt
#
###---------------Begin: Custom App Classes ----------------##
## Keep adapter, data, and model classes to avoid stripping
#-keep class com.app.expertsubmissionapp.core.adapter.ProductsAdapter { *; }
#-keep class com.app.expertsubmissionapp.core.data.Resource { *; }
#-keepclassmembers class com.app.expertsubmissionapp.core.data.Resource { *; }
#-keep class com.app.expertsubmissionapp.core.data.Resource$Error { *; }
#-keep class com.app.expertsubmissionapp.core.data.Resource$Loading { *; }
#-keep class com.app.expertsubmissionapp.core.data.Resource$Success { *; }
#-keep class com.app.expertsubmissionapp.core.databinding.ViewErrorBinding { *; }
#-keep class com.app.expertsubmissionapp.core.domain.model.MyProduct { *; }
#-keep class com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase { *; }
#
#-dontwarn com.app.expertsubmissionapp.core.adapter.ProductsAdapter
#-dontwarn com.app.expertsubmissionapp.core.data.Resource$Error
#-dontwarn com.app.expertsubmissionapp.core.data.Resource$Loading
#-dontwarn com.app.expertsubmissionapp.core.data.Resource$Success
#-dontwarn com.app.expertsubmissionapp.core.data.Resource
#-dontwarn com.app.expertsubmissionapp.core.databinding.ViewErrorBinding
#-dontwarn com.app.expertsubmissionapp.core.domain.model.MyProduct
#-dontwarn com.app.expertsubmissionapp.core.domain.usecase.ProductUseCase
#
###---------------Optional: Debugging and Testing Configuration ----------------##
## Enable debugging by printing ProGuard mappings and usage
#-printmapping mapping.txt
#-printusage unused.txt
#-printconfiguration configuration.txt
#
###---------------Optional: Prevent Issues with R8 Full Mode ----------------##
## Retain necessary generic signatures
#-keep,allowobfuscation class retrofit2.Response
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#
## Keep all model classes that may be serialized by Gson
#-keep class com.app.expertsubmissionapp.core.** { *; }
#-keepclassmembers class com.app.expertsubmissionapp.core.** {
#    <init>(...);
#    @com.google.gson.annotations.SerializedName <fields>;
#}
#
#-keep class androidx.fragment.app.FragmentContainerView { *; }
#-keep public class * extends androidx.fragment.app.Fragment { *; }
#-keep class com.app.expertsubmissionapp.presentation.**Fragment { *; }
#
## Keep RecyclerView adapters
#-keep public class * extends androidx.recyclerview.widget.RecyclerView { *; }
#
## Keep ViewBinding classes
#-keep class * implements androidx.viewbinding.ViewBinding {
#    public static *** bind(android.view.View);
#    public static *** inflate(android.view.LayoutInflater);
#}
#
## Keep Kotlin standard library classes
#-keep class kotlin.LazyKt__LazyJVMKt { *; }
#-keep class kotlin.Lazy { *; }
#-keep class kotlin.jvm.functions.Function0 { *; }
#
## Keep all Kotlin classes and their methods
#-keep class kotlin.** { *; }
#-keepclassmembers class kotlin.** { *; }
#-dontwarn kotlin.**
#
#-keep class androidx.navigation.dynamicfeatures.fragment.** { *; }
#
#-keep class kotlin.LazyThreadSafetyMode { *; }
#
#-dontobfuscate

##---------------Begin: proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}

##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
@retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-dontwarn kotlinx.**

  # With R8 full mode generic signatures are stripped for classes that are not
  # kept. Suspend functions are wrapped in continuations where the type argument
  # is used.
  -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

  # R8 full mode strips generic signatures from return types if not kept.
  -if interface * { @retrofit2.http.* public *** *(...); }
  -keep,allowoptimization,allowshrinking,allowobfuscation class <3>

  # With R8 full mode generic signatures are stripped for classes that are not kept.
  -keep,allowobfuscation,allowshrinking class retrofit2.Response
# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

 -keep class com.app.expertsubmissionapp.core.** { *; }

 -keep class androidx.viewbinding.** { *; }

 -dontwarn com.app.expertsubmissionapp.core.**

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

 # Keep Kotlin Lazy class
 -keep class kotlin.LazyKt { *; }
 -keep class kotlin.Lazy { *; }

 # Keep Kotlin Collections
 -keep class kotlin.collections.** { *; }
 -keep class kotlin.collections.CollectionsKt { *; }
 -keepclassmembers class kotlin.collections.** { *; }

 # Keep Kotlin metadata
 -keepattributes *Annotation*, InnerClasses
 -dontwarn kotlin.**
 -keep class kotlin.Metadata { *; }
 -keepclassmembers class kotlin.** { *; }

 # If you're using Kotlin Reflection
 -keep class kotlin.reflect.** { *; }

 # Navigation Component rules
 -keepnames class * extends androidx.fragment.app.Fragment
 -keep class * extends androidx.fragment.app.Fragment { *; }
 -keep class androidx.navigation.fragment.NavHostFragment { *; }


 # Additional Kotlin stdlib rules
 -keep class kotlin.** { *; }
 -keep class kotlin.Companion.** { *; }
 -keep class kotlin.jvm.internal.** { *; }
 -keep class kotlin.collections.CollectionsKt { *; }

 # Keep kotlin.collections package
 -dontwarn kotlin.collections.**
 -keepclassmembers class kotlin.collections.** {
     public synthetic <methods>;
 }



