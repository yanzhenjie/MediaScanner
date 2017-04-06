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
package com.yanzhenjie.mediascanner.sample;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yanzhenjie.mediascanner.MediaScanner;
import com.yanzhenjie.mediascanner.ScannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Sample entrance.</p>
 * Created by YanZhenjie on 17-3-27.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_test).setOnClickListener(v -> scan());
    }

    /**
     * Scan image.
     */
    private void scan() {
        File root = Environment.getExternalStorageDirectory();

        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String file1 = new File(root, "NoHttpSample/image1.jpg").getAbsolutePath();
            filePaths.add(file1);
        }

        // Usage:
        MediaScanner mediaScanner = new MediaScanner(this, mListener);
        mediaScanner.scan(filePaths);
    }

    /**
     * Scanner listener.
     */
    private ScannerListener mListener = new ScannerListener() {
        @Override
        public void oneComplete(String path, Uri uri) {
        }

        @Override
        public void allComplete(String[] filePaths) {
        }
    };
}
