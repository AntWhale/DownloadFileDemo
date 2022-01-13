package com.boo.sample.downloadfiledemo

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.boo.sample.downloadfiledemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var mydownloadid : Long = 0
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            var request = DownloadManager.Request(Uri.parse("http://t2.gstatic.com/licensed-image?q=tbn:ANd9GcQg6tkkZpSzpyK_4Z0MSMoohGhO6onR1QmW337b48loL7Lvvnxk3FxJwTOVfptM"))
                    .setTitle("유재석이미지")
                    .setDescription("유재석 이미지 다운로드합니다")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setAllowedOverMetered(true)

            var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            mydownloadid = dm.enqueue(request)
        }
        var br = object:BroadcastReceiver(){
            override
            fun onReceive(p0: Context?, p1: Intent?) {
                var id: Long? = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if(id==mydownloadid){
                    Toast.makeText(baseContext, "다운로드 성공", Toast.LENGTH_LONG).show()
                }
            }
        }

        registerReceiver(br, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }


}