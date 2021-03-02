/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.data.Data
import com.example.androiddevchallenge.ui.theme.MyTheme

var lists: List<Data> = arrayListOf(
    Data(
        "black_white_cat",
        R.mipmap.cat1,
        "　　而这些并不是完全重要，更加重要的问题是， 每个人都不得不面对这些问题。 在面对这种问题时， 黑白猫的发生，到底需要如何做到，不黑白猫的发生，又会如何产生。",
        age = 1,
        area = "JoJo",
        phoneNumber = "12345678"
    ),
    Data(
        "coal_mining_cat",
        R.mipmap.cat2,
        "　　要想清楚，挖煤猫，到底是一种怎么样的存在。 海贝尔曾经说过，人生就是学校。在那里，与其说好的教师是幸福，不如说好的教师是不幸。这启发了我。",
        age = 4,
        area = "GioGio",
        phoneNumber = "22222222"
    ),
    Data("cat_woman", R.mipmap.cat3, "　　冯学峰曾经说过，当一个人用工作去迎接光明，光明很快就会来照耀着他。这启发了我， 带着这些问题，我们来审视一下猫女。"),
    Data(
        "nice_cat",
        R.mipmap.cat4,
        "　　带着这些问题，我们来审视一下萝莉猫女。 而这些并不是完全重要，更加重要的问题是， 佚名曾经提到过，感激每一个新的挑战，因为它会锻造你的意志和品格。带着这句话，我们还要更加慎重的审视这个问题。",
        age = 28,
        area = "MiMi",
        phoneNumber = "999999999"
    ),
    Data(
        "oh_my_god_cat",
        R.mipmap.cat5,
        "　　小萝莉猫的发生，到底需要如何做到，不小萝莉猫的发生，又会如何产生。 小萝莉猫，发生了会如何，不发生又会如何。",
        age = 14,
        area = "Rua",
        phoneNumber = "2333323333"
    ),
    Data(
        "long_haired_cat",
        R.mipmap.cat6,
        "　　长毛猫因何而发生？ 总结的来说， 对我个人而言，长毛猫不仅仅是一个重大的事件，还可能会改变我的人生。",
        age = 13,
        area = "MMMMM",
        phoneNumber = "9966666666"
    ),
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    override fun onBackPressed() {
        val viewModel: MyViewModel by viewModels()
        if (viewModel.openModule) {
            viewModel.end()
        } else {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val viewModel: MyViewModel = viewModel()
    Surface(color = MaterialTheme.colors.background) {
        HomePage(lists = lists, viewModel = viewModel)
    }
}

@Composable
fun HomePage(lists: List<Data>, viewModel: MyViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        if (viewModel.openModule) {
            viewModel.currentData?.let {
                DataPage(data = it)
            }
        } else {
            LazyColumn(modifier = Modifier.background(MaterialTheme.colors.onBackground)) {
                itemsIndexed(lists) { index: Int, data: Data ->
                    Row(modifier = Modifier.fillMaxSize().padding(3.dp).clickable(onClick = { viewModel.start(data) }), verticalAlignment = Alignment.CenterVertically,) {
                        Text(
                            text = index.toString(),
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.primary
                        )
                        Image(
                            painter = painterResource(id = data.avatar),
                            contentDescription = data.name,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(4.dp)),
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = data.name,
                                fontSize = 25.sp,
                                color = MaterialTheme.colors.primaryVariant
                            )
                            Text(
                                text = data.message,
                                fontSize = 10.sp,
                                color = MaterialTheme.colors.primarySurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DataPage(data: Data) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = data.avatar),
            contentDescription = data.name
        )
        Text(text = data.name, fontSize = 15.sp, color = MaterialTheme.colors.primary)
        Text(text = data.area, fontSize = 15.sp, color = MaterialTheme.colors.primary)
        Text(text = data.age.toString(), fontSize = 15.sp, color = MaterialTheme.colors.primary)
        Text(text = data.phoneNumber, fontSize = 15.sp, color = MaterialTheme.colors.primary)
        Text(text = data.message, fontSize = 15.sp, color = MaterialTheme.colors.primary)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
