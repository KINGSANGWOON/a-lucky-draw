# A-lucky-draw



## View
### ConstraintLayout 
### numberpicker
### textview
### button
### shape Drawable

## Grammar
### apply
###when
###random
###collection(set,list use)

## Description
#### Lotto number drawing machine
#### Implemented with manual selection from 0 to 5
#### Displays the remaining numbers at random except for manually selected numbers

## Color
### 1~10 -> blue
### 11~20 -> yello
### 21~30 -> green
### 31~40 -> red
### 40~45 -> gray


## Algorithm
```
import java.util.*


fun main() {
    
    //apply를 사용한 random값을 뽑기
    //val random = Random()
    //val list = mutableListOf<Int>().apply {
    //    for (i in 1..45) {
    //        this.add(i)
    //    }
    //}
   	//list.shuffle()
    
    //println(list.subList(0, 6))
    
    
    
    
    //random이라는 package의 객체를 사용하자
    //기본 random 함수의 구조는 fun Random(seed: Int) :random
    //random 은 사실 정말 무작위로 숫자를 뽑는 것이 아니라 seed라는 값을 기준으로 나오는 값이다.(난수를 발생시킬 때 기준이 되는 것 seed)
    //즉 정말 무작위로 뽑고 싶으면 seed 값도 무작위로 해주면 될 것이다.
    //즉 seed 를 시간의 단위(millisecond)로 맞춰두면 될 정말 무작위로 뽑힐 것이다.
    //그럼 random(seed)에 값을 넣어줘야 할까 ? 아니다 그냥 빈 공간으로 두면 kotlin이 알아서 나노세컨드의 단위를 넣어준다.
    
    //random.nextInt() 하는 이유는 Int의 값만 출력해주기 때문이다
    //만약 random.nextboolean,nextLong 하면 뒤에 type 에 맞는 숫자가 나온다
    
    //set을 사용하는 방법
    //val random = Random()
    //val numberSet = mutableSetOf<Int>()
    
    //while(numberSet.size < 6){
    //    val randomNumber = random.nextInt(45) + 1
    //    numberSet.add(randomNumber)
    //}
    //println(numberSet)
    
    
    //list와 random을 사용하는 방법
    //val random = Random()
    //val list = mutableListOf<Int>()
    //while(list.size < 6){
    //    val randomNumber = random.nextInt(45) + 1
    //    if(list.contains(randomNumber)){  // contains 는 list안에 그 값이 있는지 확인하는 것이다 true 또는 false의 값을 출력한다.
    //        continue
    //    }
    //    list.add(randomNumber)
    //}
   	//println(list)
    
    //중복은 되지만 무작위로 숫자를 뽑는 방법
    //for (i in 1..6){
    //	println("${random.nextInt(45) + 1}")
    //} 이 코드는 중복이됨
       
}

```
