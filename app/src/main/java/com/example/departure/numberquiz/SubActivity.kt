package com.example.departure.numberquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub.*


//SubActivityが開いたら
//問題が出されたら
//数字ボタンを押したら
//こたえあわせボタンが押されたら
//こたえあわせの処理
//もどるボタンが押されたら

class SubActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        //SubActivityが開いたら


        //こたえあわせボタンが押されたら
        btn_ansCheck.setOnClickListener{
            //こたえあわせメソッドを呼ぶ
            answerCheck()

        }

        //もどるボタンが押されたら
        btn_back.setOnClickListener{

        }

        //数字ボタンのクリックリスナー、処理はonClickメソッド
        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
        buttonClear.setOnClickListener(this)


        //1問目を出す
        question()


    }

    //問題を出す処理をするメソッド
    //問題が出されたら(questionメソッド)
    private fun question() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //こたえあわせ処理をするメソッド
    //こたえあわせの処理(answerCheckメソッド)
    private fun answerCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //ボタンが押された時にやることをかく
    //数字ボタンを押したら
    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
