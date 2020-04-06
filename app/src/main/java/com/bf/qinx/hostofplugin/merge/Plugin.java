package com.bf.qinx.hostofplugin.merge;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import com.bf.qinx.hostofplugin.utils.ReflectUtil;
import com.bf.qinx.hostofplugin.utils.ShareReflectUtil;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * Created by QinX on 2019-06-28.
 * <p>
 * Description :
 */
public class Plugin {
    private static final String TAG = "Plugin";

    private static final String TEST_ASSETS_VALUE = "only_use_to_test_tinker_resource.txt";
    // apk 名
//     private static final String PLUGIN_APK_NAME = "plugin.apk";
//    private static final String PLUGIN_APK_NAME = "cosplay_plugin.apk";

    private static final String PLUGIN_APK_NAME = "warcraftplugin.apk";

    private static Resources mPluginResources ;

    /**
     * 载入插件dex
     * @param context
     * @param classLoader
     * @throws Exception
     */
    public static void loadPluginDex(Application context, ClassLoader classLoader) throws Exception{

        // 获取插件apk
        String apkPath =  getPatchApkPath(context);
        File apkFile = new File(apkPath);

        // 创建安装插件的Classloader
        DexClassLoader dexClassLoader = new DexClassLoader(apkFile.getAbsolutePath(), context.getExternalCacheDir().getAbsolutePath(), null,classLoader);

        // 获取BaseDexClassLoader.dexPathList
        Object pluginDexPatchList = ReflectUtil.getField(dexClassLoader, "pathList");
        // 获取DexFile.dexElements
        Object pluginDexElements =  ReflectUtil.getField(pluginDexPatchList, "dexElements");

        // 通过反射获取宿主 dexPathList
        Object hostDexPatchList = ReflectUtil.getField(classLoader, "pathList");
        // 通过反射获取宿主 dexElements
        Object hostDexElements =  ReflectUtil.getField( hostDexPatchList, "dexElements");

        // 合并dexElements
        Object array = combineArray(hostDexElements, pluginDexElements);
        ReflectUtil.setField( hostDexPatchList, "dexElements", array);

        // 载入资源文件
        loadPluginResources(context);
    }

    private static Object combineArray(Object host, Object plugin) throws Exception{
        Class<?> componentType = host.getClass().getComponentType();
        int hostSize = Array.getLength(host);
        int pluginSize = Array.getLength(plugin);
        int k = hostSize + pluginSize;

        Object bindArray = Array.newInstance(componentType, k);
        System.arraycopy(plugin, 0, bindArray, 0,pluginSize);
        System.arraycopy(host, 0, bindArray, pluginSize, hostSize);

        return bindArray;

    }

    /**
     * 加载资源文件，并进行替换
     * @param application
     * @throws Exception
     */
    public static void loadPluginResources(Application application) throws Exception{
        //application.getAssets()
        AssetManager newAssetManager = AssetManager.class.newInstance();
        // 获取 AssetManager.addAssetPath() 方法.最终触发Nativie层AssetMananger.cpp.appendPathToResTable()创建ResTable
        Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
        // 载入插件的资源
        addAssetPath.invoke(newAssetManager, getPatchApkPath(application));

        // 创建新的Resource对象
        Resources merResource = new Resources(newAssetManager,
                application.getBaseContext().getResources().getDisplayMetrics(),
                application.getBaseContext().getResources().getConfiguration());

        // 替换 ContextImpl.mResources
        ReflectUtil.setField(
                application.getBaseContext(),
                "mResources",
                merResource);

        int indentify =  application.getResources().getIdentifier("second_activity", "layout", application.getPackageName());
        Log.i("indentify","indentify:" + indentify);



        // 获取 ContextImpl 中的 LoadedApk 类型的 mPackageInfo
        Field mPackageInfoField = application.getBaseContext().getClass().getDeclaredField("mPackageInfo");
        mPackageInfoField.setAccessible(true);
        Object packageInfoO = mPackageInfoField.get(application.getBaseContext());
        // 替换 mPackageInfo.mResources
        ReflectUtil.setField(packageInfoO, "mResources", merResource);
        // 替换 mPackageInfo.mSplitResDirs
        ReflectUtil.setField(packageInfoO, "mSplitResDirs", new String[]{getPatchApkPath(application)});

        // 替换 ContextImpl 中的 Resources.Theme
//        Field themeField = application.getBaseContext().getClass().getDeclaredField("mTheme");
//        themeField.setAccessible(true);
//        themeField.set(application.getBaseContext(), null);




        // original object
//        Collection<WeakReference<Resources>> references = null;
//        Object currentActivityThread = null;
//
//        if (SDK_INT >= KITKAT) {
//            //pre-N
//            // Find the singleton instance of ResourcesManager
//            final Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
//            final Method mGetInstance = ShareReflectUtil.findMethod(resourcesManagerClass, "getInstance");
//            final Object resourcesManager = mGetInstance.invoke(null);
//            try {
//                Field fMActiveResources = ShareReflectUtil.findField(resourcesManagerClass, "mActiveResources");
//                final ArrayMap<?, WeakReference<Resources>> activeResources19 =
//                        (ArrayMap<?, WeakReference<Resources>>) fMActiveResources.get(resourcesManager);
//                references = activeResources19.values();
//            } catch (NoSuchFieldException ignore) {
//                // N moved the resources to mResourceReferences
//                final Field mResourceReferences = ShareReflectUtil.findField(resourcesManagerClass, "mResourceReferences");
//                references = (Collection<WeakReference<Resources>>) mResourceReferences.get(resourcesManager);
//            }
//        } else {
//            // Find the ActivityThread instance for the current thread
//            Class<?> activityThread = Class.forName("android.app.ActivityThread");
//            currentActivityThread = ShareReflectUtil.getActivityThread(application, activityThread);
//            final Field fMActiveResources = ShareReflectUtil.findField(activityThread, "mActiveResources");
//            final HashMap<?, WeakReference<Resources>> activeResources7 =
//                    (HashMap<?, WeakReference<Resources>>) fMActiveResources.get(currentActivityThread);
//            references = activeResources7.values();
//        }
//
//
//
//        //field
//        Field resourcesImplFiled = null;
//        Field assetsFiled = null;
//        Field resDir = null;
//        Field packagesFiled = null;
//        Field resourcePackagesFiled = null;
//
//        final Resources appResources = application.getResources();
//        if (SDK_INT >= 24) {
//            try {
//                // N moved the mAssets inside an mResourcesImpl field
//                resourcesImplFiled = ShareReflectUtil.findField(appResources, "mResourcesImpl");
//            } catch (Throwable ignore) {
//                // for safety
//                assetsFiled = ShareReflectUtil.findField(appResources, "mAssets");
//            }
//        } else {
//            assetsFiled = ShareReflectUtil.findField(appResources, "mAssets");
//        }
//
//        for (WeakReference<Resources> wr : references) {
//            final Resources resources = wr.get();
//            if (resources == null) {
//                continue;
//            }
//            // Set the AssetManager of the Resources instance to our brand new one
//            try {
//                //pre-N
//                assetsFiled.set(resources, newAssetManager);
//            } catch (Throwable ignore) {
//                // N
//                final Object resourceImpl = resourcesImplFiled.get(resources);
//                // for Huawei HwResourcesImpl
//                final Field implAssets = ShareReflectUtil.findField(resourceImpl, "mAssets");
//                implAssets.set(resourceImpl, newAssetManager);
//            }
//
//            clearPreloadTypedArrayIssue(resources);
//
//            resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
//        }
//
//        // Find the ActivityThread instance for the current thread
//        Class<?> activityThread = Class.forName("android.app.ActivityThread");
//        currentActivityThread = ShareReflectUtil.getActivityThread(application, activityThread);
//
//        // API version 8 has PackageInfo, 10 has LoadedApk. 9, I don't know.
//        Class<?> loadedApkClass;
//        try {
//            loadedApkClass = Class.forName("android.app.LoadedApk");
//        } catch (ClassNotFoundException e) {
//            loadedApkClass = Class.forName("android.app.ActivityThread$PackageInfo");
//        }
//
//        resDir = ShareReflectUtil.findField(loadedApkClass, "mResDir");
//        packagesFiled = ShareReflectUtil.findField(activityThread, "mPackages");
//        if (Build.VERSION.SDK_INT < 27) {
//            resourcePackagesFiled = ShareReflectUtil.findField(activityThread, "mResourcePackages");
//        }
//
//
//        final Field[] packagesFields;
//        if (Build.VERSION.SDK_INT < 27) {
//            packagesFields = new Field[]{packagesFiled, resourcePackagesFiled};
//        } else {
//            packagesFields = new Field[]{packagesFiled};
//        }
//        for (Field field : packagesFields) {
//            final Object value = field.get(currentActivityThread);
//
//            for (Map.Entry<String, WeakReference<?>> entry
//                    : ((Map<String, WeakReference<?>>) value).entrySet()) {
//                final Object loadedApk = entry.getValue().get();
//                if (loadedApk == null) {
//                    continue;
//                }
//                final String resDirPath = (String) resDir.get(loadedApk);
//                if (application.getApplicationInfo().sourceDir.equals(resDirPath)) {
//                    //resDir.set(loadedApk, getPatchApkPath(application));
//                }
//            }
//        }

        checkResUpdate(application);
    }

    /**
     * 替换ResourcesManager 内mResourceImpls的assets
     * @param newAssetManager newAssetManager
     * @throws Exception
     */
    public static void replaceResourcesManager_Asset(AssetManager newAssetManager) throws Exception{
        final Class<?> resourcesManagerClass = Class.forName("android.app.ResourcesManager");
        final Method mGetInstance = ShareReflectUtil.findMethod(resourcesManagerClass, "getInstance");
        final Object resourcesManager = mGetInstance.invoke(null);
        try {
            Field fMResourceImpls = ShareReflectUtil.findField(resourcesManagerClass, "mResourceImpls");
            final Object value = fMResourceImpls.get(resourcesManager);

            Field fMAssets = ShareReflectUtil.findField(Class.forName("android.content.res.ResourcesImpl"), "mAssets");
            for (Map.Entry<?, WeakReference<?>> entry
                    : ((Map<?, WeakReference<?>>) value).entrySet()) {
                final Object resourcesImpl = entry.getValue().get();
                if (resourcesImpl == null) {
                    continue;
                }
                final AssetManager originAsset = (AssetManager) fMAssets.get(resourcesImpl);
                Log.i(TAG, "originAsset:" + originAsset);
                fMAssets.set(resourcesImpl, newAssetManager);
            }

        } catch (NoSuchFieldException ignore) {
            ignore.printStackTrace();
        }
    }

    public static void addAssetPath(AssetManager assetManager,Context context) throws Exception{
        Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
        // 载入插件的资源
        addAssetPath.invoke(assetManager, getPatchApkPath(context));
    }

    private static String getPatchApkPath(Context context){
            return context.getExternalCacheDir().getAbsolutePath() +
                "/" + PLUGIN_APK_NAME;
    }

    public static Resources getPluginResources() {
        return mPluginResources;
    }


    /**
     * Why must I do these?
     * Resource has mTypedArrayPool field, which just like Message Poll to reduce gc
     * MiuiResource change TypedArray to MiuiTypedArray, but it get string block from offset instead of assetManager
     */
    private static void clearPreloadTypedArrayIssue(Resources resources) {
        // Perform this trick not only in Miui system since we can't predict if any other
        // manufacturer would do the same modification to Android.
        // if (!isMiuiSystem) {
        //     return;
        // }
        Log.w(TAG, "try to clear typedArray cache!");
        // Clear typedArray cache.
        try {
            final Field typedArrayPoolField = ShareReflectUtil.findField(Resources.class, "mTypedArrayPool");
            final Object origTypedArrayPool = typedArrayPoolField.get(resources);
            final Method acquireMethod = ShareReflectUtil.findMethod(origTypedArrayPool, "acquire");
            while (true) {
                if (acquireMethod.invoke(origTypedArrayPool) == null) {
                    break;
                }
            }
        } catch (Throwable ignored) {
            Log.e(TAG, "clearPreloadTypedArrayIssue failed, ignore error: " + ignored);
        }
    }

    public static boolean checkResUpdate(Context context) {
        InputStream is = null;
        try {
            is = context.getAssets().open(TEST_ASSETS_VALUE);
        } catch (Throwable e) {
            Log.e(TAG, "checkResUpdate failed, can't find test resource assets file " + TEST_ASSETS_VALUE + " e:" + e.getMessage());
            return false;
        } finally {
            SharePatchFileUtil.closeQuietly(is);
        }
        Log.i(TAG, "checkResUpdate success, found test resource assets file " + TEST_ASSETS_VALUE);
        return true;
    }

    public static boolean checkResUpdate(AssetManager assetManager) {
        InputStream is = null;
        try {
            is = assetManager.open(TEST_ASSETS_VALUE);
        } catch (Throwable e) {
            Log.e(TAG, "checkResUpdate failed, can't find test resource assets file " + TEST_ASSETS_VALUE + " e:" + e.getMessage());
            return false;
        } finally {
            SharePatchFileUtil.closeQuietly(is);
        }
        Log.i(TAG, "checkResUpdate success, found test resource assets file " + TEST_ASSETS_VALUE);
        return true;
    }
}
