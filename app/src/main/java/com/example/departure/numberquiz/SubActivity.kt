package com.example.departure.numberquiz

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_sub.*
import java.util.*
import kotlin.concurrent.schedule


//SubActivityが開いたら
//問題が出されたら
//数字ボタンを押したら
//こたえあわせボタンが押されたら
//こたえあわせの処理
//もどるボタンが押されたら

class SubActivity : AppCompatActivity(), View.OnClickListener {

    //残り問題数
    var nokorimondaisuu: Int = 0
    //正解数
    var correctNumber: Int = 0
    //不正解数
    var incorrectAnswer: Int = 0
    //音
    lateinit var soundPool: SoundPool
    //サウンドID初期化
    var soundId1: Int = 0   //正解
    var soundId2: Int = 0   //不正解
    //タイマー
    lateinit var timer: Timer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        //SubActivityが開いたら
        //MainActivityから渡された問題数を画面に表示させる
        val bundle = intent.extras
        val numberBox: Int = bundle.getInt("mondaisuu")     //MainActivityのputExtra情報を識別する("キー"
        txv_remaining.text = numberBox.toString()
        nokorimondaisuu = numberBox
        correctNumber = 0


        //こたえあわせボタンが押されたら
        btn_ansCheck.setOnClickListener {
            //こたえあわせメソッドを呼ぶ
            answerCheck()

        }

        //もどるボタンが押されたら
        btn_back.setOnClickListener {
            val intent = Intent(this@SubActivity, MainActivity::class.java)
            startActivity(intent)

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

    //画面が見える場所
    override fun onResume() {
        super.onResume()

        //音の準備
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            SoundPool.Builder().setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build())
                    .setMaxStreams(1).build()
        } else {
            SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }

        //音ファイルをロード
        soundId1 = soundPool.load(this, R.raw.sound_correct, 1)      //正解
        soundId2 = soundPool.load(this, R.raw.sound_incorrect, 1)    //不正解

        //タイマーの準備
        timer = Timer()
    }

    //画面が見えなくなる場所
    override fun onPause() {
        super.onPause()

        //音ファイルをメモリから消すrelease()メソッド
        soundPool.release()
        //タイマーのキャンセル
        timer.cancel()
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
        when (random.nextInt(2) + 1) {        //0から始まるので+1。１を＋、２をー
            1 -> txv_enzanshi.text = "+"
            2 -> txv_enzanshi.text = "-"
        }

//        ・前の問題で入力した自分の答えを消す
        txv_answer.text = ""
//        ・○ × 画像を見えないようにする     (viewを非表示　View.INVISIBLE、表示View.VISIBLE)
        imageView_maru_batu.visibility = View.INVISIBLE

    }

    //こたえあわせ処理をするメソッド
    //こたえあわせの処理(answerCheckメソッド)
    private fun answerCheck() {

//        ・もどる、こたえあわせ、数字ボタンを使えなくする
        btn_back.isEnabled = false
        btn_ansCheck.isEnabled = false
        button0.isEnabled = false
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false
        buttonMinus.isEnabled = false
        buttonClear.isEnabled = false

//        ・残り問題数を一つ減らして表示させる
        nokorimondaisuu -= 1    //デクリメント
        txv_remaining.text = nokorimondaisuu.toString()
//        ・○、×画像を見えるようにする
        imageView_maru_batu.visibility = View.VISIBLE
//        ・自分の入力した数字と問題の答えと比較する(if文)
        //自分の答え
        val myAnswer: Int = txv_answer.text.toString().toInt()
        //本当の答え
        val realAnswer: Int = if (txv_enzanshi.text == "+") {        //計算が＋だったら
            txv_left.text.toString().toInt() + txv_right.text.toString().toInt()    //左と右を足す
        } else txv_left.text.toString().toInt() - txv_right.text.toString().toInt()       //それ以外(ー)だったら左から右を引く

        //自分の答えと本当の答えを比較
        if (myAnswer == realAnswer) {        //同じ答えになった場合
//        ・正解の場合→正解数を一つ増やす表示、◯画像の表示、正解音
            correctNumber += 1
            txv_correct.text = correctNumber.toString()
            imageView_maru_batu.setImageResource(R.drawable.pic_correct)
            soundPool.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f)
        } else {
//        ・不正解の場合→×不正解数を一つ増やす、×画像の表示、不正解音
            incorrectAnswer += 1
            txv_incorrect.text = incorrectAnswer.toString()
            imageView_maru_batu.setImageResource(R.drawable.pic_incorrect)
            soundPool.play(soundId2, 1.0f, 1.0f, 0, 0, 1.0f)

        }

//        ・問題数がなくなって終了の時→
//        　　　　　　　もどるボタン使える、こたえあわせボタン使えない、
//        　　　　　　　テスト終了の表示
        if (nokorimondaisuu == 0) {      //残り問題数が０になった場合
            btn_back.isEnabled = true
            btn_ansCheck.isEnabled = false
            txv_msg.text = "テスト終了"
        } else {      //・問題数がある場合→１秒後に次の問題を出す
            timer.schedule(1000, { runOnUiThread { question() } })


        }
    }

    //ボタンが押された時にやることをかく
    //数字ボタンを押したら
    override fun onClick(v: View?) {    //vはButtonのこと
//        ・数字ボタンを１文字ずつ表示


        val button: Button = v as Button    //button(1〜9,-,c)   vをButton型に


        when (v?.id) {
        //クリアボタンで消す
            R.id.buttonClear -> txv_answer.text = ""
        //一文字目にーをおす時は
            R.id.buttonMinus -> if (txv_answer.text.isEmpty())      //先頭が空白""だった場合
                txv_answer.text = "-" //ーを押せる
        //一文字目に０をおす時は
            R.id.button0 -> if (txv_answer.text != "0" && txv_answer.text != "-")   //０じゃない　かつ　ーじゃない場合
                txv_answer.append(button.text)                  //１〜９は押せる　appendは後ろにつける

        //それ以外
            else -> txv_answer.append(button.text)          //１〜９を後ろに足してく
        }
    }
}
