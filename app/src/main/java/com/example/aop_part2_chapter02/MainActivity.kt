package com.example.aop_part2_chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //private class 내에서 사용할 수 있는 것
    //by lazy는 immutable 변수에만 적용이 가능하다 val 변수에만 가능
    // 호출 시점에 by lazy에 의해서 초기화를 진행한다. (호출 시점에서 초기화를 하고 이후에는 가져다가 쓰기만함)
    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    // 미리 써두지만 직접 main에서 선언하지 않는 이상 메모리를 차지하지 않는다 lazy

    private val addButton: Button by lazy{
        findViewById<Button>(R.id.addButton)
    }
    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
                findViewById<TextView>(R.id.textView1),
                findViewById<TextView>(R.id.textView2),
                findViewById<TextView>(R.id.textView3),
                findViewById<TextView>(R.id.textView4),
                findViewById<TextView>(R.id.textView5),
                findViewById<TextView>(R.id.textView6)
                )
    }

    private var didRun = false

    private val pickNumberSet = mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue= 1
        numberPicker.maxValue= 45  //numberPicker의 maxvalue와 minvalue를 지정할 수 있다

        initRunButton()
        initAddButton()
        initClearButton()
    }
    private fun initRunButton(){
        runButton.setOnClickListener{
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackgroud(number,textView)

            }

        }
    }

    private fun getRandomNumber(): List<Int>{
        val numberList = mutableListOf<Int>().apply { for(i in 1..45){  //apply로 초기화를 해준다
            if(pickNumberSet.contains((i))){  // 이미pickNumberSet에 있는 값이 포함이 되어 있으면 제외해준다

                continue
            }
            this.add(i)
        } }
        numberList.shuffle()  // 값을 섞어 주는 shuffle

        val newList = pickNumberSet.toList() + numberList.subList(0,6 - pickNumberSet.size)  //list를 짤라주는 subList가 있다.  근데 size만큼은 없애ㅑ야지
        // 선택된 picknumberset을 list로 변환하고 numberList의 random으로 돌린 값의 pickNumberset.size의 갯수를 밴 만큼의 값을 더해준 리스트를 출력한다

        return newList.sorted() // sorted 오름차순 정렬을 해준다.

    }


    private fun initAddButton(){
        addButton.setOnClickListener{
            if (didRun) {  // 초기화 버튼을 누르지 않고 이미 번호를 돌렸을 경우 didRun의 값이 True의 값을 넣어주는데 그럴 경우 메시지 송출
                Toast.makeText(this,"초기화 후에 시도해주세요!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.size >= 5) { // 최대 add 번호는 5개만 가능하기 때문에 set의 size가 5이상이라면 True값을 반환
                Toast.makeText(this,"번호는 5개까지만 선택할 수 있습니다.",Toast.LENGTH_SHORT).show()
            }
            if(pickNumberSet.contains(numberPicker.value)){   //pickNumbe 안에(contains) numberPicker.value가 있으면 true 아니면 False를 반환해줌
                Toast.makeText(this,"이미 선택한 번호입니다",Toast.LENGTH_SHORT).show()  //중복 같은 경우에는 이미 set이 있기 때문에 바뀜
            }

            val textView = numberTextViewList[pickNumberSet.size]  //만약 set의 값을 추가한다면 1,2,3, ~ 인덱스일 것이다 이말인 즉 값을 넣어줘야하는 인덱스의 크기가 pickNumberSet의 size라는 것이다.
            textView.isVisible = true  // 값을 보일 수 있도록 Visible값을 TRUE로 바꾸고
            textView.text = numberPicker.value.toString()  // NumberPicker에 있는 value의 값을 toString의 값으로 변환하여 textView의 text값으로 넣어줌

            setNumberBackgroud(numberPicker.value,textView)




            pickNumberSet.add(numberPicker.value)  //마지막으로 numberPicker에 value를 추가해줌 pickNumberSet에


        }
    }


    private fun setNumberBackgroud(number: Int, textView: TextView){
        when(number) {
            in 1..10 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue) // ContextCompat drawable을 가지고 오는 방법
            in 11..20 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yello) // ContextCompat drawable을 가지고 오는 방법
            in 21..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green) // ContextCompat drawable을 가지고 오는 방법
            in 31..40 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red) // ContextCompat drawable을 가지고 오는 방법
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray) // ContextCompat drawable을 가지고 오는 방법
        }
    }

    private fun initClearButton() {
        clearButton.setOnClickListener {
            pickNumberSet.clear()  //set의 값을 모두 지워준다
            numberTextViewList.forEach {
                it.isVisible = false  //for 문이란 비슷함 it의 numberTextViewList의 값이라 생각해주면 됨
            }
            didRun = false

        }
    }



}