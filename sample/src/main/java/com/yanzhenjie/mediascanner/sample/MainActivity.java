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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yanzhenjie.mediascanner.MediaScanner;

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

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });
    }

    /**
     * Scan image.
     */
    private void scan() {
        // Create Scanner.
        MediaScanner mediaScanner = new MediaScanner(this);

        // List:
        List<String> filePathList = new ArrayList<>();
        mediaScanner.scan(filePathList);

        // Array:
        String[] filePathArray = new String[0];
        mediaScanner.scan(filePathArray);

        // Single:
//        String path = ...;
//        mediaScanner.scan(path);
    }
}
