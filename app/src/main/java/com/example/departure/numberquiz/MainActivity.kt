package com.example.departure.numberquiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //選択肢を入れるためのArrayAdapterをセット
        /*val arrayAdapter = ArrayAdapter<Int>(this,android.R.layout.simple_spinner_item)
        arrayAdapter.add(10)
        arrayAdapter.add(20)
        arrayAdapter.add(30)*/
        val arrayAdapter = ArrayAdapter.createFromResource(this,R.array.numberOfQuestion,android.R.layout.simple_spinner_item)
        //spinnerとadaperをつなぐ
        spinner.adapter = arrayAdapter



        button.setOnClickListener {
            //スタートボタンを押したら


            //選択したアイテムをゲット(10,20,30)
            val numberBox: Int = spinner.selectedItem.toString().toInt()     //spinner.selectedItemはAny型だから文字列にして更に整数に代える

            //テスト画面を開く(Spinnerから選んだ問題数を渡す)
            val intent = Intent(this@MainActivity,SubActivity::class.java)  //mainからsubへ。::class.javaはおまじない
            intent.putExtra("mondaisuu",numberBox)  //渡す情報を識別する("キー"、と渡す情報)
            startActivity(intent)

        }
    }
}
