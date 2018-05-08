package com.example.departure.numberquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_sub.*
import java.lang.Math.random
import java.util.*


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
        //MainActivityから渡された問題数を画面に表示させる
        val bundle = intent.extras
        val nunberBox: Int = bundle.getInt("mondaisuu")     //MainActivityのputExtra情報を識別する("キー"
        txv_remaining.text = nunberBox.toString()


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
//        ・もどるボタンを押せなくする(viewを使える、使えない)isEnable ture or false
        btn_back.isEnabled = false
//        ・こたえあわせボタンと数字ボタン,-,Cを押せるようにする
        btn_ansCheck.isEnabled = true
        button0.isEnabled = true
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true
        buttonMinus.isEnabled = true
        buttonClear.isEnabled = true

//        ・問題の数字を表示ランダム表示
        val random = Random()
        val numLeft: Int = random.nextInt(100) + 1      //0から始まるので+1
        val numRight: Int = random.nextInt(100) + 1
        txv_left.text = numLeft.toString()
        txv_right.text = numRight.toString()

//        ・計算方法を+か-でランダム表示
        when(random.nextInt(2) + 1){        //0から始まるので+1。１を＋、２をー
            1 -> txv_enzanshi.text = "+"
            2 -> txv_enzanshi.text = "-"
        }

//        ・前の問題で入力した自分の答えを消す
        txv_answer.text = ""
//        ・○ × 画像を見えないようにする     (viewを非表示　View.INVISIBLE、表示View.VISIBLE)
        imageView.visibility = View.INVISIBLE

    }

    //こたえあわせ処理をするメソッド
    //こたえあわせの処理(answerCheckメソッド)
    private fun answerCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //ボタンが押された時にやることをかく
    //数字ボタンを押したら
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
