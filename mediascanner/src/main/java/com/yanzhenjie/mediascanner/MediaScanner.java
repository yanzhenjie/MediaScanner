/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.mediascanner;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>MediaScanner.</p>
 * Created by YanZhenjie on 17-3-27.
 */
public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mMediaScanConn;
    private ScannerListener mScannerListener;

    private LinkedList<String[]> mLinkedList = new LinkedList<>();
    private String[] mCurrentScanPaths;
    private int mScanCount = 0;

    /**
     * Create scanner.
     *
     * @param context context.
     */
    public MediaScanner(Context context) {
        this.mMediaScanConn = new MediaScannerConnection(context.getApplicationContext(), this);
    }

    /**
     * Create scanner.
     *
     * @param context         context.
     * @param scannerListener {@link ScannerListener}.
     * @deprecated use {@link #MediaScanner(Context)} instead.
     */
    @Deprecated
    public MediaScanner(Context context, ScannerListener scannerListener) {
        this(context);
        this.mScannerListener = scannerListener;
    }

    /**
     * Scanner is running.
     *
     * @return true, other wise false.
     */
    public boolean isRunning() {
        return mMediaScanConn.isConnected();
    }

    /**
     * Scan file.
     *
     * @param filePath file absolute path.
     */
    public void scan(String filePath) {
        scan(new String[]{filePath});
    }

    /**
     * Scan file list.
     *
     * @param filePaths file absolute path list.
     */
    public void scan(List<String> filePaths) {
        scan(filePaths.toArray(new String[filePaths.size()]));
    }

    /**
     * Scan file array.
     *
     * @param filePaths file absolute path array.
     */
    public void scan(String[] filePaths) {
        if (filePaths != null && filePaths.length > 0) {
            this.mLinkedList.add(filePaths);
            executeOnce();
        }
    }

    /**
     * Execute scanner.
     */
    private void executeOnce() {
        if (!isRunning() && mLinkedList.size() > 0) {
            this.mCurrentScanPaths = mLinkedList.remove(0);
            this.mMediaScanConn.connect();
        }
    }

    @Override
    public void onMediaScannerConnected() {
        for (String filePath : mCurrentScanPaths) {
            String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            mMediaScanConn.scanFile(filePath, mimeType);
        }
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        if (mScannerListener != null) mScannerListener.oneComplete(path, uri);
        mScanCount += 1;
        if (mScanCount == mCurrentScanPaths.length) {
            mMediaScanConn.disconnect();
            if (mScannerListener != null) mScannerListener.allComplete(mCurrentScanPaths);
            mScanCount = 0;
            mCurrentScanPaths = null;
            executeOnce();
        }
    }
}