package com.example.sf_demo.version.Q;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @time 2019/11/26 16:41
 * @desc
 */
public class FileUtils {


    public static void getDirName(Context context) {


        final File filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);//---/storage/emulated/0/Android/data/com.example.sf_demo/files/Pictures

        //----
        // 1、  沙箱文件夹内的文件 其他应用不能访问
        Log.e("directory", filesDir == null ? "null" : filesDir.getAbsolutePath());


        final File directory = Environment.getExternalStorageDirectory();

        Log.e("directory", directory == null ? "null" : directory.getAbsolutePath());


        //2、沙箱内的文件拷贝到 外部存储时 需要使用 ContentValue  和 MediaStore 来操作


        //3 、基本概念

        //内部存储 私有目录   其他应用不能访问  /data/data/<包名>/files
        final File filesDir1 = context.getFilesDir();//内部存储文件目录 /data/user/0/com.example.sf_demo/files
        final File cacheDir = context.getCacheDir();//内部存储缓存目录 /data/user/0/com.example.sf_demo/cache
        final File noBackupFilesDir = context.getNoBackupFilesDir();//内部存储不被自动备份的目录 /data/user/0/com.example.sf_demo/no_backup
        final File codeCacheDir = context.getCodeCacheDir();//内部存储代码缓存目录 /data/user/0/com.example.sf_demo/code_cache

        Log.e("directory", getPath(filesDir1) + "---" + getPath(cacheDir) + "---" + getPath(noBackupFilesDir) + "---" + getPath(codeCacheDir) + "---");


        //外部存储 私有文件目录 其他应用程序可以访问这些  /storage/Android/data/<包名>/files
        context.getExternalFilesDir(null);
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES); ///storage/emulated/0/Android/data/com.example.sf_demo/files/Pictures
        context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);


        //公有目录  Q 之前可以使用 Q 已经废弃
        final File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);// /storage/emulated/0/Pictures
        final File file1 = Environment.getExternalStorageDirectory();/// //storage/emulated/0
        Log.e("directory", getPath(file) + "---" + getPath(file1));
        /*
        1. Environment#getExternalStorageDirectory()                /storage/emulated/0
        2. Environment#getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)        /storage/emulated/0/DCIM
        3. Environment#getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)    /storage/emulated/0/Pictures
        4. Environment#getDataDirectory()                           /data
        5. Environment#getDownloadCacheDirectory()                  /data/cache
        6. Environment#getRootDirectory()                           /system

            //判断SD卡是否挂载
            public static boolean isSDCardEnable() {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        }
         */



        //官方推荐多媒体文件存放在系统中有专门的目录：Music、Movies、Pictures等，其他文件一律保存在下载目录中：Downloads


        //当用户卸载 App 的时候，内部存储和外部存储都会被自动删除


        //targetSDK = 29, 默认开启 Scoped Storage, 但可通过在 manifest 里添加 requestLegacyExternalStorage = true 关闭

        //targetSDK < 29, 默认不开启 Scoped Storage, 但可通过在 manifest 里添加requestLegacyExternalStorage = false 打开

        // 外部存储沙箱限制
        //https://juejin.im/post/5d2c36f5e51d4556db694aa3

    }

    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    private static String getPath(File file) {
        return file.getAbsolutePath();

    }

}
